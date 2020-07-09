package com.accesscontrol.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName ImgHandlerConfig.java
 * @createTime 2020年07月06日 09:57
 **/
@Configuration
public class ImgHandlerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取项目路径
        String absolutePath = new File("").getAbsolutePath();
        File imageUrl = new File(absolutePath, "/file/img");
        //在项目同级目录创建文件夹
        if (!imageUrl.exists()) {
            imageUrl.mkdirs();
        }
        //文件映射路径
        String path = imageUrl + File.separator;
        //文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + path);
    }
}
