package com.accesscontrol.manage.controller;

import com.accesscontrol.manage.pojo.MDeviceInfo;
import com.accesscontrol.manage.service.MDeviceInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName MDeviceInfoController.java
 * @createTime 2020年06月09日 16:22
 **/
@Controller
@RequestMapping("mDeviceInfo")
public class MDeviceInfoController {

    private static final Logger logger = LoggerFactory.getLogger(MUserinfoController.class);

    @Autowired
    private MDeviceInfoService mDeviceInfoService;

    @RequestMapping("toMDeviceInfoPage")
    public String toMDeviceInfoPage() {
        return "deviceinfo/deviceinfo";
    }

    @PostMapping("getMDeviceInfoList")
    @ResponseBody
    public String getMDeviceInfoList(@RequestBody Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            map = new HashMap<>();
        }
        JSONObject result = new JSONObject();
        try {
            IPage<MDeviceInfo> mDeviceInfoList = mDeviceInfoService.getMDeviceInfoList(map);
            result.put("code", "0000");
            result.put("pageList", mDeviceInfoList.getRecords());
            result.put("totalCount", mDeviceInfoList.getTotal());
            result.put("currentPage", mDeviceInfoList.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }

    @PostMapping("saveMDeviceInfo")
    @ResponseBody
    public String saveUserInfo(MDeviceInfo mDeviceInfo) {
        JSONObject result = new JSONObject();
        try {
            int i = mDeviceInfoService.addMDeviceInfo(mDeviceInfo);
            result.put("code", "0000");
            result.put("msg", "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }

    @PostMapping("updateMDeviceInfo")
    @ResponseBody
    public String updateMDeviceInfo(MDeviceInfo mDeviceInfo) {
        JSONObject result = new JSONObject();
        try {
            int i = mDeviceInfoService.updateMDeviceInfo(mDeviceInfo);
            result.put("code", "0000");
            result.put("msg", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }

}
