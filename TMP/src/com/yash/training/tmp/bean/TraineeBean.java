package com.yash.training.tmp.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

@ManagedBean
@SessionScoped
public class TraineeBean {
	
	
	private Part file;

	public Part getFile() {
	return file;
	}

	public void setFile(Part file) {
	this.file = file;
	}

	    
	    
	    public String uploadFileValue(){
	        
	        Part uploadedFile = getFile();

	        File file = new java.io.File("C:/Users/pratik.sethia/workspaceWildFly/TMP/WebContent/upload");  
	           String  abspath=file.getAbsolutePath();
	         
	        final Path destination = Paths.get(abspath+"/" + FilenameUtils.getName(getSubmittedFileName(uploadedFile)));
	        InputStream bytes = null;

	        if (null != uploadedFile) {

	        try {
	            bytes = uploadedFile.getInputStream();
	            Files.copy(bytes, destination);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } //
	        }
	        return null;


	        
	    }
	    
	    
	    //-----------------helper method for file upload---------------------
	     
	     
	    public static String getSubmittedFileName(Part filePart) {
	    String header = filePart.getHeader("content-disposition");
	    if (header == null)
	    return null;
	    for (String headerPart : header.split(";")) {
	    if (headerPart.trim().startsWith("filename")) {
	    return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
	    }
	    }
	    return null;
	    }
	    //-----------------helper method for file upload end---------------------

}
