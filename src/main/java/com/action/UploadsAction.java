package com.action;

import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class UploadsAction {
    private String author;
    private File[] face;
    private String[] faceFileName;
    private String[] faceContentType;
    public String execute() {


        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
            String path = ServletActionContext.getServletContext().getRealPath("/uploads/"+sdf.format(new Date()));
            File fp = new File(path);
            if(!fp.exists()){
                fp.exists();
            }
            for (int i= 0;i<face.length;i++){
                File newface =new File (path,faceFileName[i]);
                FileUtils.copyFile(face[i],newface);


            }
        }catch (Exception e){

        }

        return null;
    }

}
