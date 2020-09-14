package com.yodaplus.filedownload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.yodaplus.filedownload.service.FileServiceImpl;
import com.yodaplus.filedownload.service.FileService;

@EnableWebMvc
@Configuration
public class AppConfig {
	@Bean
	public FileService fileService() {
		return new FileServiceImpl();
	}
	
	@Bean(name = "/FileService")
	  public RemoteExporter exporter() {
	      HttpInvokerServiceExporter hse = new HttpInvokerServiceExporter();
	      hse.setService(fileService());
	      hse.setServiceInterface(FileService.class);
	      return hse;
	  }
}
