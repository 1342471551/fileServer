package com.transportocol.transfor.converter.baiqishi;


import com.alibaba.fastjson.JSONObject;
import com.transportocol.transfor.common.Result;
import com.transportocol.transfor.common.ResultCode;
import com.transportocol.transfor.converter.Http2ResultConverter;
import org.apache.commons.codec.binary.StringUtils;

/**
 * @author wangyj
 * @Description: (白骑士调用返回参数处理类)
 * @date 2020/12/13 14:13
 */
public class BaiQiShiHttpDataResultConverter extends Http2ResultConverter {

    //具体返回参数再封装
    @Override
    public Result<Object> convert(String s) {
        Result<Object> result = new Result<>();
        JSONObject jsonObject = JSONObject.parseObject(s);
        String code = jsonObject.getString("resultCode");
        if (StringUtils.equals("000000", code)) {
            result.setCode(ResultCode.SUCCESS);
            result.setT(jsonObject.get("resultData"));
            return result;
        }
        result.setCode(ResultCode.FAIL);
        result.setMessage(s);
        return result;
    }
}
