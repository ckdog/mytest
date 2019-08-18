package com.i2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.i2.domain.AricMessage;
import com.i2.domain.AricResponse;
import com.i2.domain.Author;
import com.i2.domain.WiselyMessage;
import com.i2.domain.WiselyResponse;

/**
 * websocket controller 类
 * @author mike
 *
 */
@Controller
public class WebSocketController{

	@Autowired
	private Author auth;
	
	public Author getAuth() {
		return auth;
	}

	public void setAuth(Author auth) {
		this.auth = auth;
	}
	

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")		//服务器返回浏览器信息路径
	public WiselyResponse getresponse(WiselyMessage message) throws InterruptedException {
		System.out.println("1111111111111111111111");
		Thread.sleep(3000);
		return new WiselyResponse("welcome "+message.getName()+"!");
	}

  @MessageMapping("/welcome") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
  @SendTo("/topic/getResponse")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
  public AricResponse say(AricMessage message) {
      try {
          //睡眠1秒
          Thread.sleep(1000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      return new AricResponse("welcome," + message.getName() + "!");
  }
	
	
}
