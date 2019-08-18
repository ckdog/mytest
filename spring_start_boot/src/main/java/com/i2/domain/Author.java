package com.i2.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * spring boot 1.5版本以上取消了configurationproperties的locations属性
 * 需要将配置的类添加@component注解
 * 并在启动类中取消激活当前配置类
 * 使用@PropertySource设置当前配置文件的位置
 * 
 * @author mike
 *
 */
@Component
@PropertySource(value = {"classpath:application.properties"})
@ConfigurationProperties(prefix="auth")
public class Author {

	private String id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
