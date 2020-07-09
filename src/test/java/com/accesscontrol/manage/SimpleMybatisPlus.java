package com.accesscontrol.manage;

import com.accesscontrol.manage.common.Utils;
import com.accesscontrol.manage.dto.UserInfoDto;
import com.accesscontrol.manage.exception.MyException;
import com.accesscontrol.manage.mapper.MUserinfoMapper;
import com.accesscontrol.manage.mapper.SysMenuMapper;
import com.accesscontrol.manage.mapper.SysUserMapper;
import com.accesscontrol.manage.pojo.MUserinfo;
import com.accesscontrol.manage.pojo.SysMenu;
import com.accesscontrol.manage.pojo.SysUser;
import com.accesscontrol.manage.service.MDeviceInfoService;
import com.accesscontrol.manage.service.MUserinfoService;
import com.accesscontrol.manage.service.MenuService;
import com.accesscontrol.manage.util.Constance;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName SimpleMybatisPlus.java
 * @createTime 2020年06月06日 12:40
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleMybatisPlus {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private MUserinfoService mUserinfoService;

    @Autowired
    private MDeviceInfoService mDeviceInfoService;

    @Autowired
    private MUserinfoMapper mUserinfoMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private MenuService menuService;


    @Test
    public void testMenuMapper() {
        List<SysMenu> sysMenuList = menuMapper.getSysMenuList("admin");
        for (SysMenu sysMenu : sysMenuList) {
            System.out.println("sysMenu = " + sysMenu);
        }
    }

    @Test
    public void test() {
        System.out.println("true = " + true);
        String s = "194a7da71a83727dc61fdf6d8a8590c5";
        System.out.println("s.length() = " + s.length());
    }

    @Test
    public void testSelect() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        SysUser whereUser = new SysUser();
        whereUser.setUserId("admin");
        whereUser.setPassWord("1234567");
        queryWrapper.setEntity(whereUser);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        System.out.println("sysUser = " + sysUser);
    }


    @Test
    public void testInsert() {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setCorporateName("test");
        userInfoDto.setPosition("test");
        userInfoDto.setUserPhone("test");
        userInfoDto.setUserSex("1");
        userInfoDto.setUserName("test");
        int i = mUserinfoService.addUserinfo(userInfoDto);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdate() {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserNo("eee104d9ec56887719d7642c26be9c86");
        userInfoDto.setUserPhone("18751567788");
        userInfoDto.setUserSex("0");
        int i = mUserinfoService.updateUserinfo(userInfoDto);
        System.out.println("i = " + i);
    }

    @Test
    public void testDelete() {
        String userNo = "eee104d9ec56887719d7642c26be9c86";
        int i = mUserinfoService.deleteUserinfo(userNo);
        System.out.println("i = " + i);
    }

    @Test
    public void testSelect2() {
        String userName = "阿姨";
        IPage<MUserinfo> mUserinfoList = mUserinfoService.getMUserinfoList(new HashMap<>());
        List<MUserinfo> records = mUserinfoList.getRecords();
        for (MUserinfo record : records) {
            System.out.println("record = " + record);
        }
        System.out.println("mUserinfoList = " + mUserinfoList.getPages());
        System.out.println("mUserinfoList = " + mUserinfoList.getTotal());
    }


    @Test
    public void testDate() {
        String s = Constance.currentDateTime();
        System.out.println("s = " + s);
    }

    @Test
    public void testDateFormate() throws ParseException {
        String date = "164557";
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        Date parse = format.parse(date);
        String format1 = format2.format(parse);
        System.out.println(format1);
    }

    @Test
    public void testDanLie() {
        String s = Constance.randNumber(5);
        System.out.println("s = " + s);
        String appointlenRandom = Utils.getAppointlenRandom(5);
        System.out.println("appointlenRandom = " + appointlenRandom);
    }

    @Test
    public void testIn() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ca48b232362d46f49d8d");
        strings.add("c78edfb6997c4c199c25");
        QueryWrapper<MUserinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("USER_NO", strings);
        List<MUserinfo> userinfos = mUserinfoMapper.selectList(queryWrapper);
        for (MUserinfo userinfo : userinfos) {
            System.out.println("userinfo = " + userinfo);
        }
    }

    @Test
    public void testUpdate1() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("638cc05d92f4cb439c14e353e9dbf535");
        strings.add("181c6e87f3fd3f413248349817552aca");
        UpdateWrapper<MUserinfo> up = new UpdateWrapper<>();
        up.in("USER_NO", strings);
        MUserinfo mUserinfo = new MUserinfo();
        mUserinfo.setDownlodeState("1");
        int update = mUserinfoMapper.update(mUserinfo, up);
        System.out.println("update = " + update);
    }

    @Test
    public void testMenuService() {
        List<Map<String, Object>> admin = menuService.getMenuList("admin");
        for (Map<String, Object> stringObjectMap : admin) {
            for (Map.Entry<String, Object> stringObjectEntry : stringObjectMap.entrySet()) {
                System.out.println("stringObjectEntry.getValue() = " + stringObjectEntry.getValue());
            }
        }
    }

    @Test
    public void testIO() throws Exception {
        File file = new File("C:\\Users\\76950\\Desktop\\images\\2dcc327b1c8895c78b6abb6c2c3122af.jpg");
        System.out.println("file.length() = " + file.length());
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] arr = new byte[(int) file.length()];
        System.out.println("arr.length = " + arr.length);
        fileInputStream.read(arr);
        System.out.println("arr.length = " + arr.length);
        fileInputStream.close();

        File file1 = new File("E:\\test\\test\\11.png");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(arr);
        fileOutputStream.close();
    }

    @Test
    public void testImage() {
        File file = new File("C:\\Users\\76950\\Desktop\\images\\2dcc327b1c8895c78b6abb6c2c3122af.jpg");
        System.out.println("file = " + file);
        //文件是否存在
        System.out.println("file.exists() = " + file.exists());
        //是否是文件夹
        System.out.println("file.isDirectory() = " + file.isDirectory());
        //是否是文件
        System.out.println("file.isFile() = " + file.isFile());
        //文件长度
        System.out.println("file.length() = " + file.length());
        System.out.println("=======================================================================================");
    }

    @Test
    public void testFile() throws IOException {
        File file = new File("D:\\testImage\\image\\1");
        /*String[] list = file.list();
        for (String s : list) {
            System.out.println("s = " + s);
        }
        System.out.println("=======================================================================================");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println("file1 = " + file1);
        }

        System.out.println("file.getParent() = " + file.getParent());

        System.out.println("file.getParentFile() = " + file.getParentFile());*/

        //file.mkdir();
        //file.mkdirs();
        System.out.println("file.length() = " + file.length());
    }

    @Test
    public void testFileForeach() {
        File file = new File("C:\\Windows");
        //System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        File[] files = file.listFiles();
        long maxNum = 0;
        long minNum = Integer.MAX_VALUE;
        File maxFile = null;
        File minFile = null;

        for (File f : files) {
            if (f.isDirectory()) {
                continue;
            }

            if (f.length() > maxNum) {
                maxFile = f;
                maxNum = f.length();
            }

            if (f.length() != 0 && f.length() < minNum) {
                minFile = f;
                minNum = f.length();
            }
        }

        System.out.println("minFile = " + minFile + " " + minNum);
        System.out.println("maxFile = " + maxFile + " " + maxNum);
    }

    @Test
    public void testFileForeach2() {
        File file = new File("C:\\Windows");
        //System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        listFile(file);
    }


    public static void listFile(File file) {
        long maxL = 0;
        long minL = Integer.MAX_VALUE;
        File minF = null;
        File maxF = null;
        if (file.isFile()) {
            if (file.length() > maxL) {
                maxL = file.length();
                maxF = file;
            }
            if (file.length() != 0 & file.length() < minL) {
                minL = file.length();
                minF = file;
            }
        }
        if (file.isDirectory()) {
            File[] fs = file.listFiles();
            if (fs != null) {
                for (File f : fs) {
                    listFile(f);
                }
            }
        }

    }

    @Test
    public void testFIle3() throws Exception {
        File file = new File("D:\\testImage\\image\\1\\aaa.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[4];
        while (true) {
            int read = fileInputStream.read(bytes);
           /* System.out.println("read = " + read);
            for (byte aByte : bytes) {
                System.out.println("aByte = " + aByte);
            }*/
            if (read == -1) {
                break;
            }
        }
        File file1 = new File("D:\\testImage\\image\\1\\bbb.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(bytes);
        fileInputStream.close();
        fileOutputStream.close();
    }

    @Test
    public void test111() {
        String s = "";
        String s1 = null;
        boolean blank = StringUtils.isBlank(s);
        boolean notBlank = StringUtils.isNotBlank(s1);
        System.out.println("blank = " + blank);
        System.out.println("notBlank = " + notBlank);
    }

    @Test
    public void testPath() throws Exception {
        File file = ResourceUtils.getFile("classpath:");
        System.out.println("file.toString() = " + file.toString());
        String absolutePath = file.getAbsolutePath();
        System.out.println("absolutePath = " + absolutePath);
        String canonicalPath = file.getCanonicalPath();
        System.out.println("canonicalPath = " + canonicalPath);
    }

    @Test
    public void testFile2() {
        File file = new File("c:\\", "hello");
        System.out.println("file = " + file.toString());
    }

    @Test
    public void testFile3() {
        File file1 = new File("classpath:");
        System.out.println("file1 = " + file1.toString());
        File file = new File("/File");
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Test
    public void testFile4() {
        File file1 = new File("File/test.txt");
        System.out.println("file1.getAbsolutePath() = " + file1.getAbsolutePath());
    }

    @Test
    public void testFile5() throws IOException {
        File file1 = new File("");

        String canonicalPath = file1.getCanonicalPath();
        System.out.println("canonicalPath = " + canonicalPath);
        System.out.println("file1.getAbsolutePath() = " + file1.getAbsolutePath());
    }

    @Test
    public void testFile6() {
        File file = new File("");
        String absolutePath = file.getAbsolutePath();
        File file1 = new File(absolutePath, "/File");
        if (!file1.exists()) {
            file1.mkdirs();
        }
    }


    @Test
    public void testFile7() throws IOException {
        File file = new File("C:\\Users\\76950\\Desktop\\images");
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());
        System.out.println("file.getPath() = " + file.getPath());
        File file1 = new File("l0l.text");
        System.out.println("file.getAbsolutePath() = " + file1.getAbsolutePath());
        System.out.println("file.getCanonicalPath() = " + file1.getCanonicalPath());
        System.out.println("file.getPath() = " + file1.getPath());
    }

    @Test
    public void testFile8() throws Exception {
        try {
            File file = new File("C:\\Users\\76950\\Desktop\\images\\img");
            System.out.println("file.exists() = " + file.exists());
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFile9() throws Exception {
        File file = new File("D:\\testImage\\image\\1\\aaa.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[2];
        int read = fileInputStream.read(bytes);

        File file1 = new File("C:\\Users\\76950\\Desktop\\test\\images\\bbb.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        while (read != -1) {
            fileOutputStream.write(bytes);
            read = fileInputStream.read(bytes);
        }
        for (byte aByte : bytes) {
            System.out.println("aByte = " + aByte);
        }
    }

    @Test
    public void testFile10() throws Exception {
        File file = new File("D:\\testImage\\image\\1\\aaa.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] bytes = new byte[2];
        int read = bufferedInputStream.read(bytes);
        File file1 = new File("C:\\Users\\76950\\Desktop\\test\\images\\ccc.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        while (read != -1) {
            bufferedOutputStream.write(bytes);
            read = bufferedInputStream.read(bytes);
            bufferedOutputStream.flush();
        }
    }

    @Test
    public void test11() throws MyException {
        throw new MyException("aaa", "bbb");
    }

    @Test
    public void test12() {
        try {
            test11();
        } catch (MyException e) {
            System.out.println("e.getMessage() = " + e.getMessage() + "e" + e.getSays());
            e.printStackTrace();
        }
    }


    @Test
    public void testFile11() throws Exception {
        File file = new File("C:\\Users\\76950\\Desktop\\images\\img\\2dcc327b1c8895c78b6abb6c2c3122af.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024 * 100];
        int read = fileInputStream.read(bytes);
        int i = 0;
        System.out.println("file.getParentFile() = " + file.getParentFile());
        File parentFile = file.getParentFile();
        File newFile = null;
        FileOutputStream fileOutputStream = null;
        while (read != -1) {
            newFile = new File(parentFile, "img_" + i);
            fileOutputStream = new FileOutputStream(newFile);
            fileOutputStream.write(bytes);
            i++;
            read = fileInputStream.read(bytes);
            fileOutputStream.close();
        }
        fileInputStream.close();
    }

    @Test
    public void testFile12() {

    }

}
