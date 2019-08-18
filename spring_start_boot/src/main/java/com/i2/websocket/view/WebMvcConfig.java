package com.i2.websocket.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 定义websocket的viewcontroller
 * @author mike
 *
 */

@Controller
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
  
//  @Override public void addViewControllers(ViewControllerRegistry registry) {
//  // TODO Auto-generated method stub
//  registry.addViewController("/wss").setViewName("ws");
//  super.addViewControllers(registry); }
  
//  @Override 
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	  registry.addResourceHandler("/es").addResourceLocations("forward:/ws.html"); 
//	  super.addResourceHandlers(registry); 
//  } 

	/**
	 * 配置viewController,提供映射路径
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/webSocket").setViewName("forward:/webSocket.html");
	    registry.addViewController("/es").setViewName("forward:/ws.html");
	    super.addViewControllers(registry); 
	}
 
}