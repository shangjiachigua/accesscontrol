package com.accesscontrol.manage.dto;

import lombok.Data;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName HairDownDto.java
 * @createTime 2020年06月11日 15:37
 **/
@Data
public class HairDownDto {
    /**
     * 用户编号-主键
     */
    private String userNo;
    /**
     * 姓名
     */
    private String userName;

    /**
     * 人脸照片
     */
    private String userPhoto;
    /**
     * 公司编号
     */
    private String corporateNo;
    /**
     * 设备数组
     */
    private String[] equipmentNos;
}
