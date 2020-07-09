package com.accesscontrol.manage.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * M_CARD_RECORD
 * @author 
 */
@Data
public class MCardRecord implements Serializable {
    /**
     * 主键ID(card_record_seq)
     */
    private BigDecimal id;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 事件源(设备名称,eg:1期1栋1单元)
     */
    private String event;

    /**
     * 状态(1.正常开门;2.开门失败;)
     */
    private String state;

    /**
     * 类型((1.刷卡开门;2.人脸开门;)
     */
    private String type;

    /**
     * 开门日期
     */
    private String openDoorDate;

    /**
     * 开门时间
     */
    private String openDoorTime;

    /**
     * 联动信息(抓拍的照片)
     */
    private String linkageInfo;

    /**
     * 设备编号
     */
    private String equipmentNo;

    /**
     * 0.未同步 1.已同步
     */
    private String syncFlag;

    /**
     * 用户名
     */
    private String userName;

    private static final long serialVersionUID = 1L;
}