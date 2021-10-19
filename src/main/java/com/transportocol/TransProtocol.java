package com.transportocol;

import com.alibaba.fastjson.JSONObject;
import com.transportocol.converter.DataConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wangyj
 * @Description: (协议核心转换类)
 * @date 2020/12/9 15:57
 */
@Slf4j
public class TransProtocol<T, R> {

    //入参转换器
    DataConverter tConverter;

    //返回参数转换器
    DataConverter rConverter;

    //协议传输类
    Transform transform;

    //返回结果处理类
    List<TransformAfter<R, T>> afterDoneList;


    //传输协议转换逻辑
    public R transform(T t) {
        R r = null;
        try {
            r = (R) rConverter.convert(transform.transform(tConverter.convert(t)));
            R finalR = r;
            afterDoneList.stream().forEach(e -> e.afterDone(finalR, t));
        } catch (Exception e) {
            log.error("error:TransProtocol {}", JSONObject.toJSONString(t), e);
        }
        return r;
    }


    /**
     * @param tConverter 传入参数转换
     * @param rConverter 返回参数转换
     * @param transform  传输协议
     * @param <T>        传入参数
     * @param <R>        返回参数
     * @return
     */
    public static <T, R> TransProtocol<T, R> build(DataConverter tConverter, DataConverter rConverter, Transform transform) {
        TransProtocol<T, R> transProtocol = new TransProtocol<>();
        transProtocol.tConverter = tConverter;
        transProtocol.rConverter = rConverter;
        transProtocol.transform = transform;
        return transProtocol;
    }

    public static <T, R> TransProtocol build() {
        return new TransProtocol<T, R>();
    }


    public TransProtocol<T, R> buildParmConverter(DataConverter tConverter) {
        this.tConverter = tConverter;
        return this;
    }

    public TransProtocol<T, R> buildResultConverter(DataConverter rConverter) {
        this.rConverter = rConverter;
        return this;
    }

    public TransProtocol<T, R> buildTransform(Transform transform) {
        this.transform = transform;
        return this;
    }

    //完成传输后置处理器,R为封装的返回参数 T为传入参数
    public TransProtocol<T, R> buildAfterDone(List<TransformAfter<R, T>> list) {
        this.afterDoneList = list;
        return this;
    }

}
