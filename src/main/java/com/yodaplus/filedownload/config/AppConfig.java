package com.yodaplus.filedownload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.yodaplus.filedownload.service.FileService;
import com.yodaplus.filedownload.service.IFileService;

@EnableWebMvc
@Configuration
public class AppConfig {
	@Bean
	public IFileService fileService() {
		return new FileService();
	}
	
	@Bean(name = "/IFileService")
	  public RemoteExporter exporter() {
	      HttpInvokerServiceExporter hse = new HttpInvokerServiceExporter();
	      hse.setService(fileService());
	      hse.setServiceInterface(IFileService.class);
	      return hse;
	  }
}
