package com.xiyoukeji.controller;

import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.AreaService;
import com.xiyoukeji.service.FileService;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Controller
public class AreaController {
    @Resource
    AreaService areaService;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*获取省列表*/
    @RequestMapping(value = "/getProvinceList")
    @ResponseBody
    public Map getProvinceList() {
        return MapTool.Mapok().put("provinces", areaService.getProvinceList());
    }

    /*获取市列表*/
    @RequestMapping(value = "/getCityList")
    @ResponseBody
    public Map getCityList(String provinceid) {
        return MapTool.Mapok().put("cities", areaService.getCityList(provinceid));
    }


}
