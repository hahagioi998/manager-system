package com.swpu.file_upload.controller;

import domain.Result;
import domain.StatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@RestController
@RequestMapping("/fileUpload")
public class FileController {

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public Result upload(@RequestPart(value = "file") MultipartFile file) throws IOException {
        //防止上传空文件导致奔溃
        System.out.println("已经进入文件上传！");
        if (file.isEmpty()) {
            return new Result(false, StatusCode.ERROR,"图片为空");
        } else {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println("fileName:"+fileName);
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString()+fileName;
            // 设置文件上传后的路径
            String savePath = "img";
            String rootPath = System.getProperty("user.dir");
//            D:\Project\IdeaProjects\manager_system\file_upload\src\main\resources\img
            String filePath = new File(rootPath+"\\file_upload\\src\\main\\resources\\img").getPath();
            /*创建文件路径*/
            File dest = new File(filePath + "\\" + newFileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            /*将文件上传到服务器上指定的文件*/
            file.transferTo(dest);

            return new Result(true,StatusCode.OK,"上传成功",newFileName,1);
        }
    }
}