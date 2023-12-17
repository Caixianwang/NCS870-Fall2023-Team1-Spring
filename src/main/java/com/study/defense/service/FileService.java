package com.study.defense.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.study.defense.controller.FileControl;
import com.study.defense.utils.StringUtil;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class FileService {
	
	private static final Logger log = LoggerFactory.getLogger(FileService.class);
	public static String ROOTPATH="/mnt/mydisk/870/datas";

	public void downloadFile(String yearMonth, String fileName,HttpServletResponse response) throws IOException {
		
		FileInputStream file = new FileInputStream(ROOTPATH + "/" + yearMonth+"/"+fileName);
		OutputStream os = response.getOutputStream();
		
		String ext = StringUtil.getExtName(fileName);
		fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		if (ext != null && ext.equals("pdf")) {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline;filename=" + fileName);
		} else if(ext != null && (ext.equals("jpg")||ext.equals("png")||ext.equals("jpeg"))){
			response.setContentType("image/jpeg");
			response.setHeader("Content-Disposition", "inline;filename=" + fileName);
		} else {
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		} // String docPath = env.getProperty("path.document");

		byte[] b = new byte[1024];
		int len = 0;
		while ((len = file.read(b)) != -1) {
			os.write(b, 0, len);
		}
		file.close();

		os.flush();
		os.close();

	}
	
}
