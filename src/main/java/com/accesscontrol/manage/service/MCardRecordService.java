package com.accesscontrol.manage.service;

import com.accesscontrol.manage.common.Utils;
import com.accesscontrol.manage.mapper.MCardRecordMapper;
import com.accesscontrol.manage.pojo.MCardRecord;
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
 * @ClassName MCardRecordService.java
 * @createTime 2020年06月09日 16:07
 **/
@Service
public class MCardRecordService {
    @Autowired
    private MCardRecordMapper mCardRecordMapper;

    public IPage<MCardRecord> getMCardRecordList(Map<String, Object> map) {
        QueryWrapper<MCardRecord> mCardRecordQueryWrapper = new QueryWrapper<>();
        String equipmentName = Utils.isNull(map.get("equipmentName")) ? "" : map.get("equipmentName").toString();
        String type = Utils.isNull(map.get("type")) ? "" : map.get("type").toString();
        String userName = Utils.isNull(map.get("userName")) ? "" : map.get("userName").toString();
        String start = Utils.isNull(map.get("start")) ? "" : map.get("start").toString();
        String end = Utils.isNull(map.get("end")) ? "" : map.get("end").toString();
        String currentPage = Utils.isNull(map.get("currentPage")) ? "" : map.get("currentPage").toString();
        String pageSize = Utils.isNull(map.get("pageSize")) ? "" : map.get("pageSize").toString();
        mCardRecordQueryWrapper.orderByDesc("OPEN_DOOR_DATE").orderByDesc("OPEN_DOOR_TIME");
        if (Utils.isNotNull(equipmentName)) {
            mCardRecordQueryWrapper.eq("equipment_No", equipmentName);
        }
        if (Utils.isNotNull(type)) {
            mCardRecordQueryWrapper.eq("type", type);
        }
        if (Utils.isNotNull(userName)) {
            mCardRecordQueryWrapper.like("user_Name", userName);
        }
        if (Utils.isNotNull(start) && (Utils.isNotNull(end))) {
            String newStart = Constance.simpleDateFormatThree(start);
            String newEnd = Constance.simpleDateFormatThree(end);
            mCardRecordQueryWrapper.between("open_door_date", newStart, newEnd);
        }
        Page<MCardRecord> page = new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize));
        IPage<MCardRecord> mDeviceInfoIPage = mCardRecordMapper.selectPage(page, mCardRecordQueryWrapper);
        List<MCardRecord> records = mDeviceInfoIPage.getRecords();
        for (MCardRecord record : records) {
            String openDoorDate = record.getOpenDoorDate();
            String openDoorTime = record.getOpenDoorTime();
            String newOpenDoorDate = Constance.simpleDate(openDoorDate);
            String newOpenDoorTime = Constance.simpleTime(openDoorTime);
            String appendTime = newOpenDoorDate + " " + newOpenDoorTime;
            record.setOpenDoorTime(appendTime);
        }
        return mDeviceInfoIPage;
    }
}
