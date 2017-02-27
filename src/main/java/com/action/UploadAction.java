package com.action;

import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class UploadAction {
    private String author;
    private File face;
    private String faceFileName;
    private String faceContentType;
    public String execute(){
        if(!faceFileName.endsWith(".jpg")){
            ServletActionContext.getContext().put("msg","文件格式必须为jpg");
            return "err";
        }
        System.out.println(faceFileName);
        System.out.println(faceContentType);
        System.out.println(author);
        System.out.println(face.length());
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
            String path = ServletActionContext.getServletContext().getRealPath("/upload/"+sdf.format(new Date()));

            File fp = new File(path);
            if(!fp.exists()){
                fp.exists();
            }
            File newface = new File(path,faceFileName);
            FileUtils.copyFile(face,newface);
        }catch (Exception e){

        }
        return null;
    }


}
