package com.xiyoukeji.controller;

import com.xiyoukeji.entity.File;
import com.xiyoukeji.service.FileService;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Controller
public class FileController {
    @Resource
    FileService fileService;

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*上传文件*/
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public Map saveorupdatePhoto(MultipartFile file) {
        return MapTool.Mapok().put("data", MapTool.Map().put("fileId", fileService.saveorupdatePhoto(file)));
    }

    /*保存视频文件*/
    @RequestMapping(value = "/saveVideoFile")
    @ResponseBody
    public Map saveVideoFile(String url) {
        return MapTool.Mapok().put("data", MapTool.Map().put("fileId", fileService.saveVideoFile(url)));
    }


}
