package com.accesscontrol.manage.dto;

import lombok.Data;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName UserInfoDto.java
 * @createTime 2020年06月09日 10:24
 **/
@Data
public class UserInfoDto {
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
     * 性别(0.男 1.女)
     */
    private String userSex;

    /**
     * 手机号
     */
    private String userPhone;
    /**
     * 公司名称
     */
    private String corporateName;

    /**
     * 职位
     */
    private String position;
}
