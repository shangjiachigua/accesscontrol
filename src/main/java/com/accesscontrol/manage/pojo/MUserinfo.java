package com.accesscontrol.manage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * M_USERINFO
 *
 * @author
 */
@Data
@TableName("M_USERINFO")
public class MUserinfo implements Serializable {
    /**
     * 用户编号-主键
     */
    @TableId(type = IdType.UUID)
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
     * 联系电话
     */
    private String userTele;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 彩付卡号
     */
    private String caifuCardNo;

    /**
     * 证件类型(1.身份证)
     */
    private String certificateType;

    /**
     * 证件号
     */
    private String certificateNo;

    /**
     * 所属区
     */
    private String areaNo;

    /**
     * 所属街道
     */
    private String streetNo;

    /**
     * 所属居委
     */
    private String committeeNo;

    /**
     * 所属小区
     */
    private String villageNo;

    /**
     * 号/栋
     */
    private String building;

    /**
     * 单元
     */
    private String entrence;

    /**
     * 室
     */
    private String room;

    /**
     * 居住地址
     */
    private String address;

    /**
     * 用户状态（1.正常 9.删除）
     */
    private String state;

    /**
     * 下载状态（0.待下载 1.已下载）
     */
    private String downlodeState;

    /**
     * 创建时间
     */
    private String dtCreate;

    /**
     * 公司名称
     */
    private String corporateName;

    /**
     * 职位
     */
    private String position;

    /**
     * 公司编号
     */
    private String corporateNo;

    private static final long serialVersionUID = 1L;
}