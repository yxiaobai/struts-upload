package com.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by 易小白 on 2017/2/27.
 */
@Data
public class DownAction extends ActionSupport {
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
