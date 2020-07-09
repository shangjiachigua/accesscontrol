package com.accesscontrol.manage.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName TestImageController.java
 * @createTime 2020年07月02日 13:39
 **/
@Controller
@RequestMapping("image")
public class TestImageController {

    @Value("${Image.IMAGE_URL_PATH}")
    private String imageUrlPath;

    private static final Logger logger = LoggerFactory.getLogger(TestImageController.class);

    @RequestMapping("toImage")
    public String toImage(HttpServletRequest request) {
        System.out.println("request.getRequestURL().toString() = " + request.getRequestURL().toString());
        System.out.println(request.getRequestURI());
        return "image/impimage";
    }

    @PostMapping("uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile file, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();
        String relativePath = null;
        String serverUrl = null;
        //springboot打成jar包图片上传路径 jar:file:/usr/local/accesscontrol/accesscontrol.jar!/BOOT-INF/classes!/
        try {
            String fileName = System.currentTimeMillis() + ".jpg";
            String absolutePath = new File("").getAbsolutePath();
            String filePath = "/img/" + fileName;
            File imageUrl = new File(absolutePath, "/file" + filePath);
            //判断文件是否已经存在
            if (imageUrl.exists()) {
                return "文件已经存在";
            }
            file.transferTo(imageUrl);
            serverUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            String url = serverUrl + filePath;
            result.put("imageUrl", url);
            result.put("imageName", fileName);
            result.put("code", "0000");
        } catch (IOException e) {
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "上传失败");
        }
        return result.toString();
    }

    @GetMapping("download")
    public void downloadFile(String fileName, HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            //获取项目上传文件路径
            String absolutePath = new File("").getAbsolutePath();
            String path = "/file/img/" + fileName;
            File filePath = new File(absolutePath, path);
            fileInputStream = new FileInputStream(filePath);
            byte[] bytes = new byte[1024];
            response.setContentType("application/force-download;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            outputStream = response.getOutputStream();
            int read = fileInputStream.read(bytes);
            while (read != -1) {
                outputStream.write(bytes);
                read = fileInputStream.read(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
            outputStream.close();
        }
    }


    @GetMapping("down")
    public void downloadFile2(HttpServletResponse response) throws IOException {
        // 文件地址，真实环境是存放在数据库中的
        File file = new File("C:\\Users\\76950\\Desktop\\images\\img\\2dcc327b1c8895c78b6abb6c2c3122af.jpg");
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + "2dcc327b1c8895c78b6abb6c2c3122af.jpg");
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }
}
