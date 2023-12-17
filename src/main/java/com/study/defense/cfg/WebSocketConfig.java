package com.study.defense.cfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Configuration
public class WebSocketConfig implements ServletContextInitializer {
	private static final Logger log = LoggerFactory.getLogger(WebSocketConfig.class);
    /**
     * 这个bean的注册,用于扫描带有@ServerEndpoint的注解成为websocket,如果你使用外置的tomcat就不需要该配置文件
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }

}
