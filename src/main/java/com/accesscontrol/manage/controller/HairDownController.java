package com.accesscontrol.manage.controller;

import com.accesscontrol.manage.pojo.MUserDownload;
import com.accesscontrol.manage.pojo.MUserinfo;
import com.accesscontrol.manage.service.HairDownService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName HairDownController.java
 * @createTime 2020年06月11日 10:40
 **/
@Controller
@RequestMapping("hairdown")
public class HairDownController {

    private static final Logger logger = LoggerFactory.getLogger(HairDownController.class);
    @Autowired
    private HairDownService hairDownService;

    @RequestMapping("toHairdownPage")
    public String toHairdownPage() {
        return "hairdown/hairdown";
    }


    @GetMapping("getUserInfoList")
    @ResponseBody
    public String getUserInfoList(String userName, String pageSize, String currentPage) {
        JSONObject result = new JSONObject();
        try {
            IPage<MUserinfo> mUserinfoList = hairDownService.getMUserinfoList(userName, pageSize, currentPage);
            result.put("code", "0000");
            result.put("pageList", mUserinfoList.getRecords());
            result.put("totalCount", mUserinfoList.getTotal());
            result.put("currentPage", mUserinfoList.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }


    @PostMapping("addUserDownLoad")
    @ResponseBody
    public String addUserDownLoad(@RequestBody Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            map = new HashMap<>();
        }
        JSONObject result = new JSONObject();
        try {
            hairDownService.addUserDownLoad(map);
            result.put("code", "0000");
            result.put("msg", "下发成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }

    @PostMapping("addUserDownLoadAll")
    @ResponseBody
    public String addUserDownLoadAll(@RequestBody Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            map = new HashMap<>();
        }
        JSONObject result = new JSONObject();
        try {
            hairDownService.addUserDownLoadAll(map);
            result.put("code", "0000");
            result.put("msg", "下发成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }


    @GetMapping("getNotDownList")
    @ResponseBody
    public String getNotDownList(String userName, String pageSize, String currentPage) {
        JSONObject result = new JSONObject();
        try {
            IPage<MUserDownload> notDownList = hairDownService.getNotDownList(userName, pageSize, currentPage);
            result.put("code", "0000");
            result.put("pageList", notDownList.getRecords());
            result.put("totalCount", notDownList.getTotal());
            result.put("currentPage", notDownList.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }


    @GetMapping("getDownSucess")
    @ResponseBody
    public String getDownSucess(String userName, String pageSize, String currentPage) {
        JSONObject result = new JSONObject();
        try {
            IPage<MUserDownload> downSucess = hairDownService.getDownSucess(userName, pageSize, currentPage);
            result.put("code", "0000");
            result.put("pageList", downSucess.getRecords());
            result.put("totalCount", downSucess.getTotal());
            result.put("currentPage", downSucess.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }

    @GetMapping("getDownFailList")
    @ResponseBody
    public String getDownFailList(String userName, String pageSize, String currentPage) {
        JSONObject result = new JSONObject();
        try {
            IPage<MUserDownload> downFailList = hairDownService.getDownFailList(userName, pageSize, currentPage);
            result.put("code", "0000");
            result.put("pageList", downFailList.getRecords());
            result.put("totalCount", downFailList.getTotal());
            result.put("currentPage", downFailList.getCurrent());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }

    @PutMapping("reHairDown")
    @ResponseBody
    public String reHairDown(String id) {
        JSONObject result = new JSONObject();
        try {
            int i = hairDownService.reHairDown(id);
            result.put("code", "0000");
            result.put("msg", "下发成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }
}
