package com.accesscontrol.manage.controller;


import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class ImageController {

    @Value("${Image.IMAGE_UPLOAD_PATH}")
    private String ImageUpLoadPath;
    @Value("${Image.IMAGE_UPLOAD_BACKUP_PATH}")
    private String ImageUpLoadBackUpPath;
    @Value("${Image.IMAGE_FILE_PATH}")
    private String imageFilePath;
    @Value("${Image.IMAGE_URL_PATH}")
    private String imageUrlPath;
    @Value("${Image.IMG_PROJECT_PATH}")
    private String imgProjectPath;
    @Value("${Image.IMG_SOCIAL_PATH}")
    private String ImgSocialPath;
    @Value("${Image.QR_CODE_PATH}")
    private String qrCodePqth;
    @Value("${Image.qr_code_path}")
    private String qrCodePath2;


    //图片上传
    @RequestMapping("/pic/uploadImage")
    @ResponseBody
    public String ajaxUploadImage(@RequestParam(value = "file", required = false) MultipartFile uploadFile, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            String fileName = System.currentTimeMillis()+ ".png";
            //测试用
            String filePath = ImgSocialPath + "/user";
            String imgPath = imageUrlPath + "/user";
            uploadFile(uploadFile.getBytes(), filePath, fileName);
            System.out.println(imgPath+"/"+fileName);
            result.put("code", "0000");
            result.put("msg", imgPath+"/"+fileName);
//            System.out.println(result.toString());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "9999");
            result.put("msg", "图片上传失败");
            return result.toString();
        }
    }


    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
       try{
           File targetFile = new File(filePath);
           if (!targetFile.exists()) {
               targetFile.mkdirs();
           }
           FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
           out.write(file);
           out.flush();
           out.close();
       } catch(Exception e){
           e.printStackTrace();
       }
    }


    @RequestMapping("/pic/uploadImageTest")
    @ResponseBody
    public String uploadImageTest(@RequestParam(value = "file", required = false) MultipartFile uploadFile, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            String fileName = System.currentTimeMillis()+ ".png";
            //测试用
            String filePath = ImgSocialPath + "/user";
            String imgPath = imageUrlPath + "/user";
            uploadFile(uploadFile.getBytes(), filePath, fileName);
            System.out.println(imgPath+"/"+fileName);
            result.put("code", "0000");
            result.put("msg", imgPath+"/"+fileName);
            //            System.out.println(result.toString());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "9999");
            result.put("msg", "图片上传失败");
            return result.toString();
        }
    }


}
