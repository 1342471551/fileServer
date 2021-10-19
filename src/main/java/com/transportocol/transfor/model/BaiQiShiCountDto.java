package com.transportocol.transfor.model;

import lombok.Data;

/**
 * @author wangyj
 * @Description: (白骑士统计接口入参)
 * @date 2020/12/13 13:38
 */
@Data
public class BaiQiShiCountDto {

    /**
     * 组织机构号
     */
    private String orgNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 身份证号
     */
    private String certNo;


    public BaiQiShiCountDto(String orgNo, String name, String mobile, String certNo) {
        this.name = name;
        this.mobile = mobile;
        this.certNo = certNo;
        this.orgNo = orgNo;
    }
}
