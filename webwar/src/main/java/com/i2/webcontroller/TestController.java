package com.i2.webcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i2.service.TestService;

@Controller
//@ComponentScan("com.i2")
public class TestController {
	@Autowired
	@Qualifier("testService")
	private TestService service;
	@RequestMapping("datetime")
	@ResponseBody
	public String queryTime(HttpServletRequest request) {
		String queryTime = service.queryTime();
		return queryTime;
		
	}
}
