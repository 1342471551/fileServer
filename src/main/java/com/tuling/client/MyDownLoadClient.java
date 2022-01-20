package com.tuling.client;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangyj
 * @Description: (java客户端分片下载)
 * @date 2021/3/19 14:04
 */
@RestController
public class MyDownLoadClient {

    //定义文件一次的长度
    private final static long per_page = 1024 * 1024 * 50;
    private final static String DOWN_PATH = "D:\\fileItem";
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    //获取文件大小,名称
    //探测获取文件信息
    //多线程分片下载
    //分片下载完成,进行文件合并
    @RequestMapping("/myDownloadFile")
    public String downloadFile() throws Exception {
        //进行文件探测
        FileInfo fileInfo = download(0, 10, -1, null);
        if (fileInfo != null) {
            long pages = fileInfo.fSize / per_page;
            for (long i = 0; i <= pages; i++) {
                executorService.submit(new DownLoad(i * per_page, (i + 1) * per_page - 1, i, fileInfo.fName));
            }
        }
        return "success";
    }

    //开始位置,结束位置,第几个分片,文件名称
    public FileInfo download(long start, long end, long page, String fName) throws Exception {
        File file = new File(DOWN_PATH, page + "-" + fName);
        //判断断点下载
        if (file.exists()) {
            return null;
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/downLoad");
        httpGet.setHeader("Range", "bytes=" + start + "-" + end);
        HttpResponse response = client.execute(httpGet);
        String fSize = response.getFirstHeader("fSize").getValue();
        fName = URLDecoder.decode(response.getFirstHeader("fName").getValue(), "utf-8");
        //获取response中文件流内容
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        FileOutputStream os = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int ch;
        while ((ch = is.read(buffer)) != -1) {
            os.write(buffer, 0, ch);
        }

        is.close();
        os.flush();
        os.close();

        File file1 = new File(DOWN_PATH, -1 + "-null");
        file1.delete();

        if (end - Long.valueOf(fSize) >= 0) {
            mergeFile(fName, page);
        }
        return new FileInfo(Long.valueOf(fSize), fName);
    }

    private void mergeFile(String fName, long page) throws Exception {
        File file = new File(DOWN_PATH, fName);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        for (int i = 0; i <= page; i++) {
            File temFile = new File(DOWN_PATH, i + "-" + fName);
            while (!file.exists() || (i != page && temFile.length() < per_page)) {
                Thread.sleep(100);
            }
            byte[] bytes = FileUtils.readFileToByteArray(temFile);
            os.write(bytes);
            os.flush();
            temFile.delete();
        }
        os.flush();
        os.close();

    }

    class DownLoad implements Runnable {
        long start;
        long end;
        long page;
        String fName;

        public DownLoad(long start, long end, long page, String fName) {
            this.start = start;
            this.end = end;
            this.page = page;
            this.fName = fName;
        }

        public void run() {
            try {
                download(start, end, page, fName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class FileInfo {
        long fSize;
        String fName;

        public FileInfo(long fSize, String fName) {
            this.fSize = fSize;
            this.fName = fName;
        }
    }
}
