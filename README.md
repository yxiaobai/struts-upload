# struts-upload
Struts简单的文件上传与下载

# UploadAction 单文件上传
struts2.5.10 单文件上传 maven项目

src/main/webapp/upfile.jsp
-------------------------------------------

	<h3>文件上传</h3>
	<form action="upload.action" method="post" enctype="multipart/form-data">
	    姓名: <input type="text" name="author"><br>
	    文件：<input type="file" name="face"><br>
	    <input type="submit" value="单文件">
	</form>

src/main/java/com/actioin/UploadAction.java
-------------------------------------------
	package com.action;

	import lombok.Data;
	import org.apache.commons.io.FileUtils;
	import org.apache.struts2.ServletActionContext;

	import java.io.File;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	@Data
	public class UploadAction{
	    private String author;
	    private File face;
	    private String faceFileName;
	    private String faceContentType;
	    public String execute(){
		if(!faceFileName.endsWith(".jpg")){
		    ServletActionContext.getContext().put("msg","文件上传格式正确必须为jpg格式");
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
			fp.mkdirs();
		    }
		    File newface = new File(path,faceFileName);
		    FileUtils.copyFile(face,newface);

		}catch(Exception e){

		}
		return null;
	    }


	}
	
	
# struts.xml 添加如下配置

  <package name="reg" extends="struts-default" namespace="/">
        <action name="upload" class="com.action.UploadAction"/>
  </package>


 jsp 代码
-------------------------------------------
	<%@ page language="java" pageEncoding="utf-8" %>
	<%@ taglib prefix="s" uri="/struts-tags" %>
	<!DOCTYPE html>
	<html>
	<head>
	    <meta charset="utf-8">
	    <title>Struts 2.5.10 单文件实现上传功能</title>
	    <meta http-equiv="refresh" content="5;url=upfile.jsp">
	    <style>
		input{padding:5px;margin:5px;}
	    </style>
	</head>
	<body>
	<s:debug/>
	<h3>文件上传-提示</h3>
	${msg}
	<br><br>
	<a href="upfile.jsp">重新上传</a>
	</body>
	</html>

运行测试http://localhost/upfile.jsp
文件实现原名上传到了/src/main/webapp/upload/1702/xxxx.jpg



# struts 2.5.10 多文件上传
---------------------------------------------------
jsp代码
	<h3>多文件上传</h3>
	<form action="uploads.action" method="post" enctype="multipart/form-data">
	    姓名: <input type="text" name="author"><br>
	    文件：<input type="file" name="face" multiple><br>
	    <input type="submit" value="多文件">
	</form>




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
    public String execute(){


        //System.out.println(faceFileName);
        //System.out.println(faceContentType);
        //System.out.println(author);
        //System.out.println(face.length());
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
            String path = ServletActionContext.getServletContext().getRealPath("/upload/"+sdf.format(new Date()));
            File fp = new File(path);
            if(!fp.exists()){
                fp.mkdirs();
            }
            for(int i=0;i<face.length;i++) {
                File newface = new File(path, faceFileName[i]);
                FileUtils.copyFile(face[i], newface);
            }

        }catch(Exception e){

        }
        return null;
    }


}

# struts.xml 添加如下配置


 <action name="uploads" class="com.action.UploadsAction"/>
 
 
 
 # Struts2.x实现文件上传与下载

package com.action;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@Data
public class DownAction extends ActionSupport{
    private String filename;
    private String path;
    public String execute(){
        return this.SUCCESS;
    }

    public InputStream getInputStream() throws FileNotFoundException {
        String ip = ServletActionContext.getServletContext().getRealPath(path)+"/"+filename;
        InputStream is = new FileInputStream(ip);
        return is;
    }
}


struts.xml 配置action

        <!-- 文件下载 -->
        <action name="down" class="com.action.DownAction">
            <param name="path">/download</param>
            <result name="success" type="stream">
                <param name="contentType">application/octet-stream</param>
                <param name="inputName">inputStream</param>
                <param name="contentDisposition">filename=${filename}</param>
                <param name="bufferSize">4096</param>
            </result>
        </action>



使用时 jsp文件
<a href="down.action?filename=a.jpg" download>图片下载</a>  
下载的是服务器中 /download/a.jpg
 
