package com.accesscontrol.manage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * M_DEVICE_INFO
 * @author 
 */
@Data
@TableName("M_DEVICE_INFO")
public class MDeviceInfo implements Serializable {
    /**
     * 设备编号(10位)-主键
     */
    @TableId(type = IdType.UUID)
    private String equipmentNo;

    /**
     * 固定资产编号
     */
    private String assetNo;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 设备型号
     */
    private String equipmentModel;

    /**
     * 设备负责人
     */
    private String equipmentPro;

    /**
     * 负责人联系电话
     */
    private String equipmentProPhone;

    /**
     * 设备安装地址
     */
    private String equipmentAddress;

    /**
     * 设备IP
     */
    private String equipmentIp;

    /**
     * 设备序列号
     */
    private String equipmentSeq;

    /**
     * 安装区
     */
    private String areaNo;

    /**
     * 安装街道
     */
    private String streetNo;

    /**
     * 安装居委
     */
    private String committeeNo;

    /**
     * 安装小区
     */
    private String villageNo;

    /**
     * 安装号/栋
     */
    private String building;

    /**
     * 安装单元
     */
    private String entrence;

    /**
     * 终端号
     */
    private String terminalNo;

    /**
     * 预留2
     */
    private String retainTwo;

    /**
     * 预留3
     */
    private String retainThree;

    /**
     * 状态(1.在库；2.部署使用；3.故障；4.维修中；)
     */
    private String state;

    /**
     * 添加日期(8位)
     */
    private String creDate;

    private static final long serialVersionUID = 1L;
}