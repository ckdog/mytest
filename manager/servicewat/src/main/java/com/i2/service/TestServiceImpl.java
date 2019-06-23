package com.i2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.i2.dao.TimeDao;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Autowired
	private TimeDao dao;
	
	public String queryTime() {
		String time = this.dao.queryTime();
		System.out.println("time ========= \t"+time);
		return time;
	}

	public void setDao(TimeDao dao) {
		this.dao = dao;
	}
	
	
}
