package com.xiyoukeji.controller;

import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.FileService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.WxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Controller
public class WxController {


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


}
