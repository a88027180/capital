package com.xiyoukeji.controller;

import com.xiyoukeji.beans.WeixinOauth2Token;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.UserService;
import com.xiyoukeji.service.WXService;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Constant;
import com.xiyoukeji.utils.WxUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dasiy on 16/12/20.
 */
@Controller
public class WxController {
    @Resource
    HttpSession session;

    @Resource
    BaseDao<User> userBaseDao;

    @Resource
    WXService wxService;

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();


//    @ExceptionHandler
//    @ResponseBody
//    public Map exception(RuntimeException runtimeException) {
//        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
//    }

    /*上传文件*/
    @RequestMapping(value = "/signature")
    @ResponseBody
    public String signature(String signature, String timestamp, String nonce, String echostr) {
        boolean b = false;
        try {
            String token = "abcdefg";
            b = WxUtils.checkSingature(signature, timestamp, nonce, token);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (b == true) {
            return echostr;
        } else
            return "";
    }

    @RequestMapping(value = "/getAccessToken")
    @ResponseBody
    public String getAccessToken() {
        String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String requestUrl = access_token_url.replace("APPID", Constant.appID).replace("APPSECRET", Constant.appsecret);
        JSONObject json = new JSONObject(sendHttpGet(requestUrl));
        String access_token = json.getString("access_token");
        long expires_in = json.getLong("expires_in");
        System.out.print(access_token);
        System.out.print(expires_in + "");
        Constant.access_token = access_token;
        Constant.expires_in = expires_in * 1000;
        Constant.current = System.currentTimeMillis();
        return access_token;
    }


    @RequestMapping(value = "/getMaterialList")
    @ResponseBody
    public Map getMaterialList(int page, int line) {
        HttpPost httpPost = null;
        if (System.currentTimeMillis() - Constant.current > Constant.expires_in) {
            getAccessToken();
        }

        String access_token_url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
        String requestUrl = access_token_url.replace("ACCESS_TOKEN", Constant.access_token);

        httpPost = new HttpPost(requestUrl);// 创建httpPost
        try {
            //设置参数
            String params = "{\"type\":\"news\",\"offset\":" + (page - 1) * line + ",\"count\":" + line + "}";
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded?charset=UTF-8");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data", sendHttpPost(httpPost));
        result.put("code", "0");

        return result;
    }

    @RequestMapping(value = "/getMaterial")
    @ResponseBody
    public Map getMaterial(String media_id) {
        HttpPost httpPost = null;
        if (System.currentTimeMillis() - Constant.current > Constant.expires_in) {
            getAccessToken();
        }

        String access_token_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
        String requestUrl = access_token_url.replace("ACCESS_TOKEN", Constant.access_token);

        httpPost = new HttpPost(requestUrl);// 创建httpPost
        try {
            //设置参数
            String params = "{\"media_id\":\"" + media_id + "\"}";
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/json?charset=UTF-8");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("data", sendHttpPost(httpPost));
        result.put("code", "0");
        return result;
    }

    @RequestMapping(value = "/getStateString")
    @ResponseBody
    public Map getStateString() {
        String state = UUID.randomUUID().toString();
        wxService.getStateString(state);
        return MapTool.Mapok().put("state", state);

    }

    @RequestMapping(value = "/bindorlogin")
    @ResponseBody
    public Map bindorlogin(String code, String state, Integer type) {
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", Constant.appIDText);
        requestUrl = requestUrl.replace("SECRET", Constant.appsecretText);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject json = new JSONObject(sendHttpGet(requestUrl));
        Map map = null;
        if (json.has("access_token")) {
            String openid = json.getString("openid");/*用户唯一标识*/
            switch (type) {
                case 0:
                /*绑定*/
                    if (openid != null && !openid.equals("")) {
                        map = wxService.saveWXUser(openid,state);
                    }
                    break;
                case 1:
                /*登录*/
                    map = wxService.login(openid);
                    break;
            }
        } else
            map = MapTool.Map().put("code", "7");/*code无效*/
        return map;

    }

//    @RequestMapping(value = "/getWXUserInfo")
//    @ResponseBody
//    public String getUserInfo(String code) {
//        // 拼接请求地址
//        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
//        requestUrl = requestUrl.replace("APPID", Constant.appID);
//        requestUrl = requestUrl.replace("SECRET", Constant.appsecret);
//        requestUrl = requestUrl.replace("CODE", code);
//        // 获取网页授权凭证
//        JSONObject json = new JSONObject(sendHttpGet(requestUrl));
//        String access_token = json.getString("access_token");
//        String openid = json.getString("openid");
//
//        // 拼接请求地址
//        String _requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
//        _requestUrl = _requestUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
//        if (_requestUrl != null && !_requestUrl.equals("")) {
//            User user = (User) session.getAttribute("user");
//            user.setIsBand(1);
//            userBaseDao.saveOrUpdate(user);
//        }
//        return sendHttpGet(_requestUrl);
//    }


    public String sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpGet(httpGet);
    }

    private String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }


    public HttpPost sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return httpPost;
    }

    private String sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
//            responseContent = EntityUtils.toString(entity, "UTF-8");

            InputStream content = entity.getContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] x = new byte[2048];
            int size;
            while ((size = content.read(x)) != -1) {
                byteArrayOutputStream.write(x, 0, size);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            responseContent = new String(bytes, "utf8");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }


}
