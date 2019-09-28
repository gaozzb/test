package com.medicine.image;


import java.io.File;

import com.medicine.model.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class UploadController {


    @Autowired
    private   HttpServletRequest request;

    @Value("${}")

    @PostMapping("/upload") // 等价于 @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public R uplaod(@RequestParam("files") MultipartFile[] files) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file

        if( files==null || files.length == 0){
            return  R.error(-1,"图片信息为空");
        }


//        if(files.isEmpty()){
//
//        }
//
//        try {
//            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
//            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
//            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
//            String destFileName = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
//            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
//            File destFile = new File(destFileName);
//            destFile.getParentFile().mkdirs();
//            //5.把浏览器上传的文件复制到希望的位置
//            file.transferTo(destFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return R.error(-1,"上传失败," + e.getMessage());
//        }

        return R.ok();
    }

}
