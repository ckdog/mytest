package com.i2.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 配置容器  代码方式
 * 实现接口EmbeddedServletContainerCustomizer
 * 如果是在当前已有的配置类中配置，需要将当前方法配置为static
 * 如果想直接配置tomct可以使用TomcatEmbeddedServletContainerFactory
 * @author mike
 *
 */
@Component
public class EmbedContainer implements EmbeddedServletContainerCustomizer{

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
//		container.setPort(9090);
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
		container.setSessionTimeout(30,TimeUnit.MINUTES);
	}

}
