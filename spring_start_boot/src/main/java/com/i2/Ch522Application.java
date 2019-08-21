package com.i2;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.i2.domain.Prop;

/**
 * @enableautoconfiguration用于激活自动配置，包含自定义配置类
 * 但是springboot1.5版本开始不支持@propertysource的location属性，需要使用其他配合注册为组件，并在自动配置中删除自定义配置类
 * @author mike
 *
 */
@SpringBootApplication
public class Ch522Application extends WebMvcConfigurerAdapter{


	public static void main(String[] args) {
		SpringApplication.run(Ch522Application.class, args);
	}
	public void test() {
		String string = new String();
	}
	
	/**
	 * 可以通过一下方式设置encodingfilter
	 * 也可以通过application.properties中直接配置以spring.http.encoding开头的键值对属性
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(CharacterEncodingFilter.class)
	public CharacterEncodingFilter characterencoding() {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true);
		return filter;
		
	}
	/**
	 * 通过filterRegisterBean可以注册自动以的filter
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(CharacterEncodingFilter.class)
	public FilterRegistrationBean filterRegister() {
		FilterRegistrationBean reg = new FilterRegistrationBean();
		reg.setFilter(characterencoding());
		reg.addUrlPatterns("/");//添加过滤器过滤路径
		reg.setOrder(1);
		return reg;
		
		
	}
	@RequestMapping("/getdata")
	@ResponseBody
	public List get() {
		List<Prop> list = new ArrayList<Prop>();
		for(int i=0;i<5;i++) {
			Prop p = new Prop();
			p.setVal(String.valueOf(i));
			list.add(p);
		}
		return list;
	}
	/**
	 * 通过代码的方式配置容器需要实现EmbeddedServletContainerCustomizer
	 * 配置tomcat可以使用TomcatEmbeddedServletContainerFactory
	 * 配置jetty可以使用JettyEmbeddedServletContainerFactory
	 * 实现自动从http跳转到https
	 * 
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				// TODO Auto-generated method stub
//				super.postProcessContext(context);
				SecurityConstraint constarint =  new SecurityConstraint();
				constarint.setUserConstraint("CONFIDENTAIL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/");
				constarint.addCollection(collection);
			}
		};
		factory.addAdditionalTomcatConnectors(httpConnector());
		return factory;
	}
	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(9090);
		connector.setSecure(false);
		connector.setRedirectPort(8448);
		return connector;
	}
	
}
