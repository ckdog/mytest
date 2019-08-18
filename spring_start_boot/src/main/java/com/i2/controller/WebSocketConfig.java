package com.i2.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
/**
 * 使用enableWebsocketMessageBroker开启对stomp协议支持
 * 这时控制器支持@messagingmapping注解
 * @author mike
 *
 */
@EnableWebSocketMessageBroker
public class WebSocketConfig  extends AbstractWebSocketMessageBrokerConfigurer {

	//注册一个stomp协议节点并映射大指定的url
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		// TODO Auto-generated method stub
//		//注册一个endpoint。并使用sockjs
//		registry.addEndpoint("/endpointsock").withSockJS();
//		
//	}
//	//配置消息代理
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		// TODO Auto-generated method stub
//		//广播式配置一个消息代理
//		super.configureMessageBroker(registry);
//		registry.enableSimpleBroker("/topic");
//	}
	/*
	 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver view
	 * = new InternalResourceViewResolver(); view.setPrefix("/templates/");
	 * view.setSuffix(".html"); return view; }
	 */
	 @Override
     public void registerStompEndpoints(StompEndpointRegistry registry) {//注册STOMP协议的节点(endpoint),并映射指定的url
         //注册一个STOMP的endpoint,并指定使用SockJS协议
         registry.addEndpoint("/endpointAric").withSockJS();

     }

     @Override
     public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(Message Broker)
         //广播式应配置一个/topic消息代理
         registry.enableSimpleBroker("/topic");

     }
}
