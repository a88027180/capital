package com.xiyoukeji.controller;

import com.xiyoukeji.entity.File;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.FileService;
import com.xiyoukeji.service.UserService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.ErrCodeExcetion;
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
public class FileController {
    @Resource
    FileService fileService;
    @Resource
    HttpSession session;
    @Resource
    UserService userService;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        if (runtimeException instanceof ErrCodeExcetion)
            return MapTool.Map().put("code", ((ErrCodeExcetion) runtimeException).getCode()).put("msg", runtimeException.getMessage());
        else return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*上传文件*/
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public Map saveorupdatePhoto(MultipartFile file) {
        Map map = null;
        if (userService.isLog()) {
            map = MapTool.Mapok().put("data", fileService.saveorupdatePhoto(file));
        }
        return map;
    }

    /*保存视频文件*/
    @RequestMapping(value = "/saveVideoFile")
    @ResponseBody
    public Map saveVideoFile(String url) {
        Map map = null;
        if (userService.isLog()) {
            map = MapTool.Mapok().put("data", MapTool.Map().put("fileId", fileService.saveVideoFile(url)));
        }
        return map;
    }

    /*上传项目评级文件  管理员权限*/
    @RequestMapping(value = "/uploadEvaluateFile")
    @ResponseBody
    public Map saveorupdateEvaluate(MultipartFile file, Integer fileId) {
        Map map = null;
        if (userService.isLog()) {
            map = MapTool.Mapok().put("data", fileService.saveorupdateEvaluate(file, fileId));
        }
        return map;
    }

}
