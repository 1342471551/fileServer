package com.transportocol.transfor.converter;

import com.transportocol.converter.DataConverter;
import com.transportocol.transfor.common.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyj
 * @Description: (Http返回参数转换处理类)
 * @date 2020/12/13 14:16
 */
@Slf4j
public class Http2ResultConverter implements DataConverter<String, Result<Object>> {
    @Override
    public Result<Object> convert(String s) {
        log.info("第三方返回参数"+s);
        return null;
    }
}
