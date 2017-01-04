package com.xiyoukeji.controller;

import com.xiyoukeji.entity.File;
import com.xiyoukeji.entity.User;
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
public class FileController {
    @Resource
    FileService fileService;
    @Resource
    HttpSession session;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*上传文件*/
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public Map saveorupdatePhoto(MultipartFile file) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("data", fileService.saveorupdatePhoto(file));
        }
    }

    /*保存视频文件*/
    @RequestMapping(value = "/saveVideoFile")
    @ResponseBody
    public Map saveVideoFile(String url) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("data", MapTool.Map().put("fileId", fileService.saveVideoFile(url)));
        }
    }

    /*上传项目评级文件  管理员权限*/
    @RequestMapping(value = "/saveorupdateEvaluateFile")
    @ResponseBody
    public Map saveorupdateEvaluate(MultipartFile file) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
        return MapTool.Mapok().put("data", fileService.saveorupdateEvaluate(file));
        }
    }

}
