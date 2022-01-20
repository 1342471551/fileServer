package com.excel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangyj
 * @Description: (多线程导出方法)
 * @date 2022/1/6 9:16
 */
public class MultiWrite {

    public static void exec(int max, int threadMax, List<Map<String, Object>> datas, String type) throws Exception {
        XSSFWorkbookWrapper workbookWrapper = new XSSFWorkbookWrapper();
        workbookWrapper.initTitile(type);
        //封装数据库的查询结果
        final List<List<String>> values = WriteDataUtils.getValuesOut(datas, max, type);
        //将获取到的结果根据线程标记进行分组
        List<List<List<String>>> item = WriteDataUtils.groupData(values, threadMax);
        Executor executor = new ThreadPoolExecutor(threadMax, threadMax,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < item.size(); i++) {
            final List<List<String>> lists = item.get(i);
            int finalI = i * item.get(0).size() + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "执行");
                    WritePOIUtils utils = new WritePOIUtils();
                    utils.setWorkbookData(workbookWrapper.getWorkbook(), lists, finalI);
                    integer.addAndGet(1);
                }
            };
            executor.execute(runnable);
        }
        while (integer.get() < threadMax) {

        }
        ((ThreadPoolExecutor) executor).shutdown();
        System.out.println("数据执行完毕");
        WritePOIUtils.writeFile(workbookWrapper.getWorkbook());
        System.out.println("执行完毕");
    }

}