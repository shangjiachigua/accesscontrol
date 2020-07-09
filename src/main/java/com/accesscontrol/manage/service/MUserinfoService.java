package com.accesscontrol.manage.service;

import com.accesscontrol.manage.common.Utils;
import com.accesscontrol.manage.dto.UserInfoDto;
import com.accesscontrol.manage.mapper.MUserinfoMapper;
import com.accesscontrol.manage.pojo.MUserinfo;
import com.accesscontrol.manage.util.Constance;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName MUserinfoService.java
 * @createTime 2020年06月09日 10:11
 **/
@Service
public class MUserinfoService {
    @Autowired
    private MUserinfoMapper mUserinfoMapper;

    public IPage<MUserinfo> getMUserinfoList(Map<String, Object> map) {
        QueryWrapper<MUserinfo> mUserinfoQueryWrapper = new QueryWrapper<>();
        mUserinfoQueryWrapper.ne("state", 9);
        String userName = Utils.isNull(map.get("userName")) ? "" : map.get("userName").toString();
        String currentPage = Utils.isNull(map.get("currentPage")) ? "" : map.get("currentPage").toString();
        String pageSize = Utils.isNull(map.get("pageSize")) ? "" : map.get("pageSize").toString();
        if (Utils.isNotNull(userName)) {
            mUserinfoQueryWrapper.eq("USER_NAME", userName);
        }
        Page<MUserinfo> page = new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize));
        IPage<MUserinfo> mUserinfoIPage = mUserinfoMapper.selectPage(page, mUserinfoQueryWrapper);
        return mUserinfoIPage;
    }

    public int addUserinfo(UserInfoDto userInfoDto) {
        MUserinfo mUserinfo = new MUserinfo();
        mUserinfo.setUserName(userInfoDto.getUserName());
        mUserinfo.setUserPhoto(userInfoDto.getUserPhoto());
        mUserinfo.setUserPhone(userInfoDto.getUserPhone());
        mUserinfo.setPosition(userInfoDto.getPosition());
        mUserinfo.setUserSex(userInfoDto.getUserSex());
        mUserinfo.setCorporateName(userInfoDto.getCorporateName());
        mUserinfo.setCardNo("test");
        mUserinfo.setState("1");
        mUserinfo.setDownlodeState("0");
        mUserinfo.setDtCreate(Constance.currentDateTime());
        int insertCount = mUserinfoMapper.insert(mUserinfo);
        return insertCount;
    }

    public int updateUserinfo(UserInfoDto userInfoDto) {
        if (Utils.isNotNull(userInfoDto)) {
            MUserinfo mUserinfo = new MUserinfo();
            mUserinfo.setUserNo(userInfoDto.getUserNo());
            mUserinfo.setUserName(userInfoDto.getUserName());
            mUserinfo.setUserPhoto(userInfoDto.getUserPhoto());
            mUserinfo.setUserPhoto(userInfoDto.getUserPhone());
            mUserinfo.setPosition(userInfoDto.getPosition());
            mUserinfo.setUserSex(userInfoDto.getUserSex());
            mUserinfo.setCorporateName(userInfoDto.getCorporateName());
            int updateCount = mUserinfoMapper.updateById(mUserinfo);
            return updateCount;
        }
        return 0;
    }

    public int deleteUserinfo(String userNo) {
        MUserinfo mUserinfo = new MUserinfo();
        mUserinfo.setUserNo(userNo);
        mUserinfo.setState("9");
        int deleteCount = mUserinfoMapper.updateById(mUserinfo);
        return deleteCount;
    }

}
