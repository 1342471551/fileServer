package com.transportocol.transfor.afterdone;


import com.transportocol.TransformAfter;
import com.transportocol.transfor.common.Result;
import com.transportocol.transfor.model.BaiQiShiCountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 不用问为什么这么写
 * 接过来就是这个样子的
 */
@Slf4j
@Component
public class BqsCountCallAfterDone implements TransformAfter<Result<Object>, BaiQiShiCountDto> {


    /**
     * 根据Http请求数据返回结果进行处理
     *
     * @param objectResult     http返回结果
     * @param baiQiShiCountDto 请求参数信息
     * @return
     */
    @Override
    public int afterDone(Result<Object> objectResult, BaiQiShiCountDto baiQiShiCountDto) {
        //进行相关数据操作 入库,调用等
        return 0;
    }
}
