package com.tuling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author wangyj
 * @Description: (自定义下载方法)
 * @date 2021/3/19 11:12
 */
@Controller
public class MyDownLoadController {

    public final static String utf8 = "utf-8";

    @RequestMapping("/downLoad")
    @ResponseBody
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File("D:\\wyj\\第8节课\\20181018_3、netty进阶和实战02~1.mp4");
        response.setCharacterEncoding(utf8);
        InputStream is = null;
        OutputStream os = null;
        try {
            //分片下载
            long fSize = file.length();
            response.setContentType("application/x-download");
            String fileName = URLEncoder.encode(file.getName(), utf8);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setHeader("Accept-Range", "bytes");
            //自定义传输头,给前端传递文件大小和名称
            response.setHeader("fSize", String.valueOf(fSize));
            response.setHeader("fName", fileName);
            //记录读取开始位置,结束位置,当前读取多少
            long pos = 0, last = fSize - 1, sum = 0;
            //判断前端传的响应头是否需要分片下载 Range  bytes=100-1000(Range请求头中分片数据字节传递形式)
            if (null != request.getHeader("Range")) {
                //设置传输状态 206代表分片下载
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String numRange = request.getHeader("Range").replaceAll("bytes=", "");
                String[] strRange = numRange.split("-");
                if (strRange.length == 2) {
                    pos = Long.parseLong(strRange[0].trim());
                    last = Long.parseLong(strRange[1].trim());
                    if (last > fSize - 1) {
                        last = fSize - 1;
                    }
                } else {
                    pos = Long.parseLong(numRange.replaceAll("-", "").trim());
                }
            }
            //获取这次总共读取文件长度 给前端传递这两个请求头,可用于断点续传
            Long rangeLength = last - pos + 1;
            String contentRange = new StringBuffer("bytes ").append(pos).append("-").append(last).append("/").append(fSize).toString();
            response.setHeader("Content-Range", contentRange);
            response.setHeader("Content-Length", String.valueOf(rangeLength));
            os = new BufferedOutputStream(response.getOutputStream());
            is = new BufferedInputStream(new FileInputStream(file));
            //读取文件跳过读取的长度
            is.skip(pos);
            byte[] buffer = new byte[1024];
            int lenght = 0;
            while (sum < rangeLength) {
                lenght = is.read(buffer, 0, (rangeLength - sum) <= buffer.length ? (int) (rangeLength - sum) : (int) buffer.length);
                sum = sum + lenght;
                os.write(buffer, 0, lenght);
            }
            System.out.println("下载完成");

        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }

    }
}
