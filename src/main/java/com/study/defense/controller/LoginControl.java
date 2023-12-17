package com.study.defense.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.study.defense.msg.ResInfo;
import com.study.defense.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public class LoginControl {
	private static final Logger log = LoggerFactory.getLogger(LoginControl.class);
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResInfo login(HttpServletRequest request, @RequestBody UserVO userVO) throws Exception {
		log.info(userVO.getUserId());
		ResInfo resInfo = new ResInfo();
		resInfo.setRes(userVO);
		return resInfo;
	}
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	public ResInfo login1(HttpServletRequest request) throws Exception {
		log.info(request.getParameter("id"));
		ResInfo resInfo = new ResInfo();
		
		return resInfo;
	}

	
}
