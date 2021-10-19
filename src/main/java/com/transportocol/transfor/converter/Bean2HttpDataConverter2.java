package com.transportocol.transfor.converter;

import com.alibaba.fastjson.JSONObject;
import com.transportocol.converter.DataConverter;

import java.util.Map;

/**
 * @author wangyj
 * @Description: (传入参数转换Http请求参数封装类)
 * @date 2020/12/13 13:25
 */
public class Bean2HttpDataConverter2<T> implements DataConverter<T, Map<String,String>> {

    //进行第一次封装,把传入参数进行JsonToStringToMap转换
    @Override
    public Map<String, String> convert(T t) {
        return (Map<String, String>) JSONObject.toJSON(t);
    }
}
