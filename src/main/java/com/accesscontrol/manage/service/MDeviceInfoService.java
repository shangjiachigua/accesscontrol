package com.accesscontrol.manage.service;

import com.accesscontrol.manage.common.Utils;
import com.accesscontrol.manage.mapper.MDeviceInfoMapper;
import com.accesscontrol.manage.pojo.MDeviceInfo;
import com.accesscontrol.manage.util.Constance;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName MDeviceInfoService.java
 * @createTime 2020年06月09日 16:07
 **/
@Service
public class MDeviceInfoService {
    @Autowired
    private MDeviceInfoMapper mDeviceInfoMapper;

    public IPage<MDeviceInfo> getMDeviceInfoList(Map<String, Object> map) {
        QueryWrapper<MDeviceInfo> mDeviceInfoQueryWrapper = new QueryWrapper<>();
        //mUserinfoQueryWrapper.ne("state", 9);
        String equipmentName = Utils.isNull(map.get("equipmentName")) ? "" : map.get("equipmentName").toString();
        String assetNo = Utils.isNull(map.get("assetNo")) ? "" : map.get("assetNo").toString();
        String equipmentPro = Utils.isNull(map.get("equipmentPro")) ? "" : map.get("equipmentPro").toString();
        String equipmentAddress = Utils.isNull(map.get("equipmentAddress")) ? "" : map.get("equipmentAddress").toString();
        String currentPage = Utils.isNull(map.get("currentPage")) ? "" : map.get("currentPage").toString();
        String pageSize = Utils.isNull(map.get("pageSize")) ? "" : map.get("pageSize").toString();
        if (Utils.isNotNull(equipmentName)) {
            mDeviceInfoQueryWrapper.eq("equipment_Name", equipmentName);
        }
        if (Utils.isNotNull(assetNo)) {
            mDeviceInfoQueryWrapper.eq("asset_No", assetNo);
        }
        if (Utils.isNotNull(equipmentPro)) {
            mDeviceInfoQueryWrapper.eq("equipment_Pro", equipmentPro);
        }
        if (Utils.isNotNull(equipmentAddress)) {
            mDeviceInfoQueryWrapper.eq("equipment_Address", equipmentAddress);
        }
        Page<MDeviceInfo> page = new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize));
        IPage<MDeviceInfo> mDeviceInfoIPage = mDeviceInfoMapper.selectPage(page, mDeviceInfoQueryWrapper);
        return mDeviceInfoIPage;
    }

    public int addMDeviceInfo(MDeviceInfo mDeviceInfo) {
        mDeviceInfo.setState("1");
        mDeviceInfo.setCreDate(Constance.currentDateTime());
        int insertCount = mDeviceInfoMapper.insert(mDeviceInfo);
        return insertCount;
    }

    public int updateMDeviceInfo(MDeviceInfo mDeviceInfo) {
        int updateCount = mDeviceInfoMapper.updateById(mDeviceInfo);
        return updateCount;

    }

    public int deleteUserinfo(String userNo) {
        MDeviceInfo mDeviceInfo = new MDeviceInfo();
        int deleteCount = mDeviceInfoMapper.updateById(mDeviceInfo);
        return deleteCount;
    }

    public List<MDeviceInfo> getEquipmentName() {
        QueryWrapper<MDeviceInfo> mDeviceInfoQueryWrapper = new QueryWrapper<>();
        mDeviceInfoQueryWrapper.select("equipment_No","Equipment_Name");
        List<MDeviceInfo> mDeviceInfos = mDeviceInfoMapper.selectList(mDeviceInfoQueryWrapper);
        return mDeviceInfos;
    }
}
