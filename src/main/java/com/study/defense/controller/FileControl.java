package com.study.defense.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.defense.msg.ResInfo;
import com.study.defense.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

/**
 * <b>描述</b>: <blockquote>
 * 
 * <pre>
 * </pre>
 * 
 * </blockquote>
 * 
 * @author caixian_wang@sina.com
 * @date 2017年12月5日 上午9:40:43
 */

@RestController
@RequestMapping(value = "/file")
@CrossOrigin(origins = "*")
public class FileControl implements X509TrustManager {

	private static final Logger log = LoggerFactory.getLogger(FileControl.class);

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/getFiles", method = RequestMethod.GET)
	public ResInfo downloadFile(@RequestParam String yearMonth,@RequestParam String day) throws IOException {
		ResInfo resInfo = new ResInfo();
		List<String> matchingFiles = listFilesWithExtension(yearMonth,day);
		resInfo.setRes(matchingFiles);
		return resInfo;
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadFile(@RequestParam String yearMonth,
			@RequestParam(value = "fileName", required = true, defaultValue = "") String fileName,
			HttpServletResponse response) throws IOException {
//		log.info(fileName);
		this.fileService.downloadFile(yearMonth, fileName, response);
	}

	private List<String> listFilesWithExtension(String yearMonth, String day) {
		String folderPath = FileService.ROOTPATH + "/" + yearMonth;
		String ext = ".txt";
		List<String> matchingFiles = new ArrayList<>();
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isFile() && file.getName().endsWith(ext)) {
						String name = file.getName();
						if (name.indexOf("M") == -1 && name.indexOf(yearMonth + day) >= 0) {
							matchingFiles.add(file.getName());
						}
					}
				}
			}
		}

		return matchingFiles;
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] b = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(b)) != -1) {
			bos.write(b, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/***
	 * 校验https网址是否安全
	 * 
	 * @author solexit06
	 * 
	 */
	public class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			// 直接返回true:默认所有https请求都是安全的
			return true;
		}
	}

	/*
	 * 里面的方法都是空的，当方法为空是默认为所有的链接都为安全，也就是所有的链接都能够访问到 当然这样有一定的安全风险，可以根据实际需要写入内容
	 */
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
