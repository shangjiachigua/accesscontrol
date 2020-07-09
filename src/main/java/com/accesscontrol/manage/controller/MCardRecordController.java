package com.accesscontrol.manage.controller;

import com.accesscontrol.manage.pojo.MCardRecord;
import com.accesscontrol.manage.pojo.MDeviceInfo;
import com.accesscontrol.manage.service.MCardRecordService;
import com.accesscontrol.manage.service.MDeviceInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName MCardRecordController.java
 * @createTime 2020年06月09日 16:45
 **/
@Controller
@RequestMapping("cardrecord")
public class MCardRecordController {
    private static final Logger logger = LoggerFactory.getLogger(MUserinfoController.class);

    @Autowired
    private MCardRecordService mCardRecordService;

    @Autowired
    private MDeviceInfoService mDeviceInfoService;

    @RequestMapping("toCardRecordPage")
    public String toUserInfoPage() {
        return "cardrecord/cardrecord";
    }

    @PostMapping("getUserInfoList")
    @ResponseBody
    public String getUserInfoList(@RequestBody Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            map = new HashMap<>();
        }
        JSONObject result = new JSONObject();
        try {
            IPage<MCardRecord> mCardRecordList = mCardRecordService.getMCardRecordList(map);
            result.put("code", "0000");
            result.put("pageList", mCardRecordList.getRecords());
            result.put("totalCount", mCardRecordList.getTotal());
            result.put("currentPage", mCardRecordList.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }

    @GetMapping("getequipmentName")
    @ResponseBody
    public String getequipmentName() {
        JSONObject result = new JSONObject();
        try {
            List<MDeviceInfo> equipmentName = mDeviceInfoService.getEquipmentName();
            result.put("code", "0000");
            result.put("pageList", equipmentName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }

}
