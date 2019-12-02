package com.soft863.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-10-25 13:04
 **/
@RestController
@RequestMapping("/file")
public class UploadController {



    @PostMapping("/uploadFile")
    public String upload(MultipartFile multipartFile, HttpServletRequest request) {

        // 得到上传的路径
        String path = "C:\\upload\\";
        System.out.println(path);
        if (!multipartFile.isEmpty()) {
            // 得到文件名称
            String fileName = multipartFile.getOriginalFilename();
            // 得到文件类型
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            // 在服务器上面新建一个文件 , 文件名称以系统时间方式命名
            File uploadFile = new File(path, new Date().getTime() + fileType);

            BufferedOutputStream stream = null;
            try {
                stream = new BufferedOutputStream(new FileOutputStream( uploadFile ));
                stream.write(multipartFile.getBytes());
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stream != null)
                        stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "success";
    }
   /* @RequestMapping(value = "/file/uploadFile.action")
    @ResponseBody
    public ModelAndView uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        //随机文件名
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String path = "C:\\upload\\";
     *//*   String path = request.getServletContext().getRealPath("/img/");*//*
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String newPath = path + fileName + "." + extension;
        File file = new File(newPath);

        multipartFile.transferTo(file);

        ModelAndView mv = new ModelAndView();
        mv.addObject("resultpath", newPath);
        mv.setViewName ("uploadresult");
        return mv;
    }*/

}
