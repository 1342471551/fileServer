package com.tuling.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author wangyj
 * @Description: (自定义上传文件)
 * @date 2021/3/18 20:12
 */
@Controller
public class MyUploadController {
    private final static String utf8 = "utf-8";

    @RequestMapping("/myUpload")
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(utf8);
        Integer schunk = null;
        Integer schunks = null;
        String name = null;
        String uploadPath = "D:\\fileItem";
        BufferedOutputStream os = null;
        try {
            //fileUpload API文档进行分片上传
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置磁盘缓冲区
            factory.setSizeThreshold(1024);
            //设置存储临时目录
            factory.setRepository(new File(uploadPath));
            //解析servlet拿到数据,设置总文件大小和分片文件大小
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(101 * 1024 * 1024 * 1024);
            upload.setSizeMax(10 * 1024 * 1024 * 1024);
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    if ("schunk".equals(fileItem.getFieldName())) {
                        schunk = Integer.parseInt(fileItem.getString(utf8));
                    }
                    if ("schunks".equals(fileItem.getFieldName())) {
                        schunks = Integer.parseInt(fileItem.getString(utf8));
                    }
                    if ("name".equals(fileItem.getFieldName())) {
                        name = fileItem.getString(utf8);
                    }
                }
                if (!fileItem.isFormField()) {
                    String fileName = name;
                    if (name != null) {
                        if (schunk != null) {
                            fileName = schunk + "_" + name;
                        }
                        File temFile = new File(uploadPath, fileName);
                        //分片文件要是不存在就写入目录位置
                        if (!temFile.exists()) {
                            fileItem.write(temFile);
                        }
                    }
                }
            }
            //判断是分片上传并且当前分片是最后一个分片了,进行合并
            if (schunk != null && schunk.intValue() == schunks.intValue() - 1) {
                File tempFile = new File(uploadPath, name);
                os = new BufferedOutputStream(new FileOutputStream(tempFile));
                for (int i = 0; i < schunks; i++) {
                    File file = new File(uploadPath, i + "_" + name);
                    while (!file.exists()) {
                        Thread.sleep(100);
                    }
                    byte[] bytes = FileUtils.readFileToByteArray(file);
                    os.write(bytes);
                    os.flush();
                    file.delete();
                }
                os.flush();
            }
            response.getWriter().write("文件上传成功" + name);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
