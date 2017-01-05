package com.xiyoukeji.service;

import com.mysql.jdbc.log.Log;
import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class FileService {
    @Resource
    ServletContext servletContext;
    @Resource
    BaseDao<File> baseDao;
    @Resource
    BaseDao<FileEvaluate> fileEvaluateBaseDao;
    final Map<String, String> mimeType = new HashMap<>();

    public FileService() {
        mimeType.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
        mimeType.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
        mimeType.put("application/msword", "doc");
        mimeType.put("application/vnd.ms-excel", "xls");
        mimeType.put("application/pdf", "pdf");
        mimeType.put("video/mp4", "mp4");
        mimeType.put("audio/mp3", "mp3");
        mimeType.put("image/jpeg", "jpeg");
        mimeType.put("image/png", "png");
        mimeType.put("image/gif", "gif");
        mimeType.put("image/jpg", "jpg");

    }

    @Transactional
    public Map saveorupdatePhoto(MultipartFile file) {
        System.out.print(file.getOriginalFilename());
        Map map = new HashMap<>();
        File file1 = upload(file);
        baseDao.save(file1);
        map.put("id", file1.getId());
        map.put("url", file1.getUrl());
        map.put("fileName", file1.getFileName());
        return map;
    }

    public File upload(MultipartFile file) {
        if (mimeType.containsKey(file.getContentType())) {
            String path = servletContext.getRealPath("/") + "Uploads/", suffix = "." + mimeType.get(file.getContentType());
            String file_name = UUID.randomUUID().toString() + suffix;
            String url = "/Uploads/" + file_name;
            return saveToFile(file, path, file_name, url);
        } else
            throw new MultipartException("type error");
    }

    private File saveToFile(MultipartFile file, String path, String file_name, String url) {
        File file1 = new File();
        file1.setUrl(url);
        file1.setFileName(file.getOriginalFilename());
        java.io.File dir = new java.io.File(path);
        if (!dir.exists()) dir.mkdir();
        String all_name = path + file_name;
        java.io.File all = new java.io.File(all_name);
        try {
            file.transferTo(all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file1;
    }

    @Transactional
    public Integer saveVideoFile(String url) {
        File file = new File();
        file.setUrl(url);
        baseDao.saveOrUpdate(file);
        return file.getId();
    }

    @Transactional
    public Map saveorupdateEvaluate(MultipartFile file) {
        Map map = new HashMap<>();
        FileEvaluate file1 = uploadEvaluate(file);
        fileEvaluateBaseDao.save(file1);
        map.put("id", file1.getId());
        map.put("url", file1.getUrl());
        map.put("fileName", file1.getFileName());
        return map;
    }

    public FileEvaluate uploadEvaluate(MultipartFile file) {
        if (mimeType.containsKey(file.getContentType())) {
            String path = servletContext.getRealPath("/") + "Evaluates/", suffix = "." + mimeType.get(file.getContentType());
            String file_name = UUID.randomUUID().toString() + suffix;
            String url = "/Evaluates/" + file_name;

            return saveToEvaluateFile(file, path, file_name, url);
        } else
            throw new MultipartException("type error");
    }

    private FileEvaluate saveToEvaluateFile(MultipartFile file, String path, String file_name, String url) {
        FileEvaluate file1 = new FileEvaluate();
        file1.setUrl(url);
        file1.setFileName(file.getOriginalFilename());
        java.io.File dir = new java.io.File(path);
        if (!dir.exists()) dir.mkdir();
        String all_name = path + file_name;
        java.io.File all = new java.io.File(all_name);
        try {
            file.transferTo(all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file1;
    }
}
