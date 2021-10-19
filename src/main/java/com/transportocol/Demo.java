package com.transportocol;

import com.transportocol.transfor.afterdone.BqsCountCallAfterDone;
import com.transportocol.transfor.common.HttpPostTypeEnum;
import com.transportocol.transfor.common.Result;
import com.transportocol.transfor.converter.baiqishi.BaiQiShiDataConverter;
import com.transportocol.transfor.converter.baiqishi.BaiQiShiHttpDataResultConverter;
import com.transportocol.transfor.http.HttpPostTransform;
import com.transportocol.transfor.model.BaiQiShiCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author wangyj
 * @Description: (测试类)
 * @date 2020/12/13 13:02
 */
@Service
public class Demo {

    @Autowired
    BqsCountCallAfterDone bqsCountCallAfterDone;

    /**
     * 测试类1(白骑士),测试封装协议
     *
     * @param orgNo  渠道号
     * @param name   姓名
     * @param mobile 手机号
     * @param idNo   身份证号
     * @return 统一返回结果
     */
    public Result<Object> BQSTest(String orgNo, String name, String mobile, String idNo) {
        return (Result<Object>) TransProtocol.build()
                .buildParmConverter(new BaiQiShiDataConverter("id", "key"))
                .buildResultConverter(new BaiQiShiHttpDataResultConverter())
                .buildTransform(new HttpPostTransform(HttpPostTypeEnum.FORM_URLENCODED).build("url").buildHeader(new HashMap<>()))
                .buildAfterDone(Arrays.asList(bqsCountCallAfterDone))
                .transform(new BaiQiShiCountDto(orgNo, name, mobile, idNo));
    }
}
