package com.accesscontrol.manage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * M_USER_DOWNLOAD
 *
 * @author
 */
@Data
@TableName("m_user_download")
public class MUserDownload implements Serializable {
    /**
     * 主键ID(user_download_seq)
     */
    @TableId(type = IdType.NONE)
    private int id;

    /**
     * 设备编号
     */
    private String equipmentNo;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 人脸照片
     */
    private String userPhoto;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 添加时间
     */
    private String creTime;

    /**
     * 下载时间
     */
    private String downloadTime;

    /**
     * 状态(1.待下载；2.下载成功；3.下载失败；)
     */
    private String state;

    /**
     * 类型(1.添加；2.修改；3.删除；)
     */
    private String type;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 家长照片
     */
    private String parentPhoto;

    /**
     * 公司编号
     */
    private String corporateNo;

    private static final long serialVersionUID = 1L;
}