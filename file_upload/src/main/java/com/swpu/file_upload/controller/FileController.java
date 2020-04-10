package com.swpu.file_upload.controller;

import domain.Result;
import domain.StatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString()+suffix;
            System.out.println("newFileName:"+newFileName);
            // 设置文件上传后的路径
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


    @RequestMapping("/showPicture")
    public void showPicture(@RequestParam("url") String url, HttpServletResponse response){
        System.out.println("图片显示！");
        FileInputStream fis = null;
        OutputStream os = null;
        // 设置文件上传后的路径
        String rootPath = System.getProperty("user.dir");
        //url是你服务器上图片的绝对路径
        String filePath = new File(rootPath+"\\file_upload\\src\\main\\resources\\img").getPath()+"\\"+url;
        File file = new File(filePath);
        if(file.exists()){
            try {
                fis = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                fis.read(temp, 0, (int) size);
                fis.close();
                byte[] data = temp;
                response.setContentType("image/png");
                os = response.getOutputStream();
                os.write(data);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}