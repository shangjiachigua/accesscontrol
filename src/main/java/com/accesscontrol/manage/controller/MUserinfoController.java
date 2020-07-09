package com.accesscontrol.manage.controller;

import com.accesscontrol.manage.dto.UserInfoDto;
import com.accesscontrol.manage.pojo.MUserinfo;
import com.accesscontrol.manage.service.MUserinfoService;
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
 * @ClassName MUserinfoController.java
 * @createTime 2020年06月09日 11:17
 **/
@Controller
@RequestMapping("userinfo")
public class MUserinfoController {
    private static final Logger logger = LoggerFactory.getLogger(MUserinfoController.class);

    @Autowired
    private MUserinfoService mUserinfoService;

    /**
     * 功能描述: 跳转门禁用户页
     *
     * @param: []
     * @return: java.lang.String
     * @auther: tian
     * @date: 2020/6/9 16:09
     */
    @RequestMapping("toUserInfoPage")
    public String toUserInfoPage() {
        return "userinfo/userinfo";
    }

    /**
     * 功能描述: 门禁用户列表查询
     *
     * @param: [map]
     * @return: java.lang.String
     * @auther: tian
     * @date: 2020/6/9 16:09
     */
    @PostMapping("getUserInfoList")
    @ResponseBody
    public String getUserInfoList(@RequestBody Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            map = new HashMap<>();
        }
        JSONObject result = new JSONObject();
        try {
            IPage<MUserinfo> mUserinfoList = mUserinfoService.getMUserinfoList(map);
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

    /**
     * 功能描述: 删除门禁用户
     *
     * @param: [userNo]
     * @return: java.lang.String
     * @auther: tian
     * @date: 2020/6/9 16:09
     */
    @PostMapping("deleteUserInfo")
    @ResponseBody
    public String deleteUserInfo(String userNo) {
        JSONObject result = new JSONObject();
        try {
            int i = mUserinfoService.deleteUserinfo(userNo);
            result.put("code", "0000");
            result.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }
    /**
     *
     * 功能描述: 添加门禁用户
     *
     * @param: [userInfoDto]
     * @return: java.lang.String
     * @auther: tian
     * @date: 2020/6/10 14:16
     */
    @PostMapping("saveUserInfo")
    @ResponseBody
    public String saveUserInfo(UserInfoDto userInfoDto) {
        JSONObject result = new JSONObject();
        try {
            int i = mUserinfoService.addUserinfo(userInfoDto);
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

    @PostMapping("updateUserInfo")
    @ResponseBody
    public String updateUserInfo(UserInfoDto userInfoDto) {
        JSONObject result = new JSONObject();
        try {
            int i = mUserinfoService.updateUserinfo(userInfoDto);
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
