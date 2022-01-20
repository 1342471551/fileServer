package com.transportocol.transfor.http;

import com.alibaba.fastjson.JSONObject;
import com.transportocol.Transform;
import com.transportocol.transfor.common.HttpPostTypeEnum;
import com.transportocol.transfor.common.httpUtils.MSXFHttpClient;
import com.transportocol.transfor.converter.Bean2HttpDataConverter2;
import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (Http相关的传输模板类)
 * @date 2020/12/14 9:45
 */
public class HttpPostTransform<T> implements Transform<T, String> {

    //进行传入数据T2string过滤
    Bean2HttpDataConverter2<T> converter = new Bean2HttpDataConverter2<T>();

    private HttpPostTypeEnum httpPostTypeEnum = HttpPostTypeEnum.FORM_URLENCODED;

    private String url;

    //请求参数头
    private Map<String, String> headers = new HashMap<>();

    private final int defaultTime = 20000;

    //根据传入模板T转换为rConverter后置处理类接收的json2string类型
    @Override
    @SneakyThrows
    public String transform(T t) {
        //json类型的请求
        if (httpPostTypeEnum == HttpPostTypeEnum.JSON) {
            return MSXFHttpClient.postToJson(url, JSONObject.toJSONString(converter.convert(t)), defaultTime, defaultTime);
        }
        //有自定义请求头的
        if (!CollectionUtils.isEmpty(headers)) {
            return MSXFHttpClient.postToJson(url, JSONObject.toJSONString(converter.convert(t)), headers, defaultTime, defaultTime);
        }
        //默认请求
        return MSXFHttpClient.post(url, converter.convert(t), defaultTime, defaultTime);
    }


    public static <T> HttpPostTransform<T> build(String url, Bean2HttpDataConverter2<T> converter2) {
        HttpPostTransform<T> httpPostTransform = new HttpPostTransform<>();
        httpPostTransform.converter = converter2;
        httpPostTransform.url = url;
        return httpPostTransform;
    }

    public HttpPostTransform(HttpPostTypeEnum type) {
        this.httpPostTypeEnum = type;
    }

    public HttpPostTransform() {
    }

    public HttpPostTransform build(String url) {
        this.url = url;
        return this;
    }

    public HttpPostTransform buildHeader(Map<String, String> headers) {
        headers.put("Accept", "application/json");
        this.headers = headers;
        return this;
    }

}
