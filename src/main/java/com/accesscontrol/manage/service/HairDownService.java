package com.accesscontrol.manage.service;

import com.accesscontrol.manage.common.Utils;
import com.accesscontrol.manage.mapper.MDeviceInfoMapper;
import com.accesscontrol.manage.mapper.MUserDownloadMapper;
import com.accesscontrol.manage.mapper.MUserinfoMapper;
import com.accesscontrol.manage.pojo.MDeviceInfo;
import com.accesscontrol.manage.pojo.MUserDownload;
import com.accesscontrol.manage.pojo.MUserinfo;
import com.accesscontrol.manage.util.Constance;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName HairDownService.java
 * @createTime 2020年06月11日 11:19
 **/

@Service
@Transactional
public class HairDownService {

    @Autowired
    private MUserinfoMapper userinfoMapper;

    @Autowired
    private MUserDownloadMapper mUserDownloadMapper;

    @Autowired
    private MDeviceInfoMapper deviceInfoMapper;


    public IPage<MUserinfo> getMUserinfoList(String userName, String pageSize, String currentPage) {
        QueryWrapper<MUserinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DOWNLODE_STATE", 0);
        if (Utils.isNotNull(userName)) {
            queryWrapper.like("user_Name", userName);
        }
        Page<MUserinfo> mUserinfoPage = new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize));
        userinfoMapper.selectPage(mUserinfoPage, queryWrapper);
        return mUserinfoPage;
    }


    public int addUserDownLoad(Map<String, Object> map) {
        String userNo = Utils.isNull(map.get("userNo")) ? "" : map.get("userNo").toString();
        MUserinfo mUserinfo = new MUserinfo();
        mUserinfo.setUserNo(userNo);
        mUserinfo.setDownlodeState("1");
        userinfoMapper.updateById(mUserinfo);

        String userName = Utils.isNull(map.get("userName")) ? "" : map.get("userName").toString();
        String userPhoto = Utils.isNull(map.get("userPhoto")) ? "" : map.get("userPhoto").toString();
        String corporateNo = Utils.isNull(map.get("userName")) ? "" : map.get("corporateNo").toString();
        MUserDownload mUserDownload = new MUserDownload();
        mUserDownload.setUserName(userName);
        mUserDownload.setCorporateNo(corporateNo);
        mUserDownload.setUserPhoto(userPhoto);
        mUserDownload.setCreTime(Constance.currentDateTime());
        mUserDownload.setState("1");
        mUserDownload.setType("1");
        mUserDownload.setUserNo(userNo);
        mUserDownload.setCardNo("nonon");
        List<String> equipmentNos = (List<String>) map.get("equipmentNos");
        for (String equipmentNo : equipmentNos) {
            mUserDownload.setId(Integer.parseInt(Utils.getAppointlenRandom(5)));
            mUserDownload.setEquipmentNo(equipmentNo);
            mUserDownloadMapper.insert(mUserDownload);
        }
        return 0;
    }

    public int addUserDownLoadAll(Map<String, Object> map) {
        List<String> userNos = (List<String>) map.get("userNos");
        UpdateWrapper<MUserinfo> up = new UpdateWrapper<>();
        up.in("USER_NO", userNos);
        MUserinfo mUserinfo = new MUserinfo();
        mUserinfo.setDownlodeState("1");
        userinfoMapper.update(mUserinfo, up);

        QueryWrapper<MUserinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_no", userNos);
        List<MUserinfo> userinfos = userinfoMapper.selectList(queryWrapper);
        //MUserDownload mUserDownload = new MUserDownload();
        List<String> equipmentNos = (List<String>) map.get("equipmentNos");
        for (MUserinfo userinfo : userinfos) {
            MUserDownload mUserDownload = new MUserDownload();
            mUserDownload.setUserName(userinfo.getUserName());
            mUserDownload.setCorporateNo(userinfo.getCorporateNo());
            mUserDownload.setUserPhoto(userinfo.getUserPhoto());
            mUserDownload.setCreTime(Constance.currentDateTime());
            mUserDownload.setState("1");
            mUserDownload.setType("1");
            mUserDownload.setUserNo(userinfo.getUserNo());
            mUserDownload.setCardNo("nonon");
            for (String equipmentNo : equipmentNos) {
                mUserDownload.setId(Integer.parseInt(Utils.getAppointlenRandom(5)));
                mUserDownload.setEquipmentNo(equipmentNo);
                mUserDownloadMapper.insert(mUserDownload);
            }
        }
        return 0;
    }

    public IPage<MUserDownload> getNotDownList(String userName, String pageSize, String currentPage) {
        QueryWrapper<MUserDownload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("STATE", 1);
        if (Utils.isNotNull(userName)) {
            queryWrapper.like("USER_NAME", userName);
        }
        Page<MUserDownload> page = new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize));
        IPage<MUserDownload> mUserDownloadIPage = mUserDownloadMapper.selectPage(page, queryWrapper);

        List<MUserDownload> records = mUserDownloadIPage.getRecords();
        for (MUserDownload record : records) {
            MDeviceInfo mDeviceInfo = deviceInfoMapper.selectById(record.getEquipmentNo());
            record.setEquipmentNo(mDeviceInfo.getEquipmentName());
            record.setCreTime(Constance.simpleDateFormat(record.getCreTime()));
        }
        return mUserDownloadIPage;
    }

    public IPage<MUserDownload> getDownSucess(String userName, String pageSize, String currentPage) {
        QueryWrapper<MUserDownload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("STATE", 2);
        if (Utils.isNotNull(userName)) {
            queryWrapper.like("USER_NAME", userName);
        }
        Page<MUserDownload> page = new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize));
        IPage<MUserDownload> mUserDownloadIPage = mUserDownloadMapper.selectPage(page, queryWrapper);

        List<MUserDownload> records = mUserDownloadIPage.getRecords();
        for (MUserDownload record : records) {
            MDeviceInfo mDeviceInfo = deviceInfoMapper.selectById(record.getEquipmentNo());
            record.setEquipmentNo(mDeviceInfo.getEquipmentName());
            record.setCreTime(Constance.simpleDateFormat(record.getCreTime()));
            record.setDownloadTime(Constance.simpleDateFormat(record.getDownloadTime()));
        }
        return mUserDownloadIPage;
    }

    public IPage<MUserDownload> getDownFailList(String userName, String pageSize, String currentPage) {
        QueryWrapper<MUserDownload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("STATE", 3);
        if (Utils.isNotNull(userName)) {
            queryWrapper.like("USER_NAME", userName);
        }
        Page<MUserDownload> page = new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize));
        IPage<MUserDownload> mUserDownloadIPage = mUserDownloadMapper.selectPage(page, queryWrapper);

        List<MUserDownload> records = mUserDownloadIPage.getRecords();
        for (MUserDownload record : records) {
            MDeviceInfo mDeviceInfo = deviceInfoMapper.selectById(record.getEquipmentNo());
            record.setEquipmentNo(mDeviceInfo.getEquipmentName());
            record.setCreTime(Constance.simpleDateFormat(record.getCreTime()));
            record.setDownloadTime(Constance.simpleDateFormat(record.getDownloadTime()));
        }
        return mUserDownloadIPage;
    }

    public int reHairDown(String id) {
        MUserDownload mUserDownload = new MUserDownload();
        mUserDownload.setId(Integer.parseInt(id));
        mUserDownload.setState("1");
        int update = mUserDownloadMapper.updateById(mUserDownload);
        return update;
    }
}
