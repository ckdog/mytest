package com.i2.domain;
/**
 * websocket 服务器向浏览器发送消息使用
 * @author mike
 *
 */
public class WiselyResponse {

	private String responseMessage;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public WiselyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WiselyResponse(String responseMessage) {
		super();
		this.responseMessage = responseMessage;
	}
	
	
}
