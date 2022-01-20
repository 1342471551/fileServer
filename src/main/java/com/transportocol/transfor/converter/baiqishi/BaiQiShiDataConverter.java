package com.transportocol.transfor.converter.baiqishi;

import com.transportocol.transfor.converter.Bean2HttpDataConverter2;
import com.transportocol.transfor.model.BaiQiShiCountDto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (白骑士传入参数封装类)
 * @date 2020/12/13 13:20
 */
public class BaiQiShiDataConverter extends Bean2HttpDataConverter2<BaiQiShiCountDto> {

    //第三方特殊密钥
    private String partnerId;
    private String verifyKey;

    @Override
    public Map<String, String> convert(BaiQiShiCountDto baiQiShiCountDto) {
        Map map=new HashMap();
        Map<String,String> extParamMap=new HashMap<>();
        extParamMap.put("name",baiQiShiCountDto.getName());
        extParamMap.put("mobile",baiQiShiCountDto.getMobile());
        extParamMap.put("idNo",baiQiShiCountDto.getCertNo());

        map.put("partnerId",this.partnerId);
        map.put("verifyKey",this.verifyKey);
        map.put("extParam",extParamMap);
        return super.convert(baiQiShiCountDto);
    }

    public BaiQiShiDataConverter(String partnerId, String verifyKey) {
        this.partnerId = partnerId;
        this.verifyKey = verifyKey;
    }
}
