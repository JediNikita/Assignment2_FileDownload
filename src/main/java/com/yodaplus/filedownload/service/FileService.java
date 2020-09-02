package com.yodaplus.filedownload.service;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.yodaplus.filedownload.model.Message;


public class FileService implements IFileService{
	
	@Autowired
	private Message message;
	private static final int BUFFER_SIZE = 4096;
	
	public void save( Message message) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(message);
		
		em.getTransaction().commit();

		em.close();
		entityManagerFactory.close();

	}
	
	@Override
	public void writeFile(String fileName, URL url, String path) throws IOException {
		String saveFilePath= path +"/" + fileName;
		String success;
		
		
		/*
		 * ReadableByteChannel readableByteChannel =
		 * Channels.newChannel(url.openStream()); FileOutputStream fileOutputStream =
		 * new FileOutputStream(saveFilePath); FileChannel fileChannel =
		 * fileOutputStream.getChannel();
		 * 
		 * fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		 */

		  HttpURLConnection URLConnection= (HttpURLConnection) url.openConnection();
		  int responseCode= URLConnection.getResponseCode();
		  
		  if(responseCode==HttpURLConnection.HTTP_OK) { 
			  int contentLength =  URLConnection.getContentLength();
			  
			  InputStream inputStream = URLConnection.getInputStream(); 
			  
			  FileOutputStream outputStream = new FileOutputStream(saveFilePath);
			  
			  int bytesRead = -1; byte[] buffer = new byte[BUFFER_SIZE]; 
			  while ((bytesRead= inputStream.read(buffer)) != -1) {
				  outputStream.write(buffer, 0,bytesRead); 
				  }
			  
			  outputStream.close(); 
			  inputStream.close(); 
			  success= "file downloaded";
			  message.setFileSize(contentLength);
		  
		  } 
		  else {
			  success= "error "+ responseCode; 
			  message.setFileSize(0); 
		  }
		  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		  message.setCurrentTime(sdf.format(Calendar.getInstance().getTime()));
		  message.setFileName(fileName); message.setSuccess(success);
		  
		  save(message); 
		  URLConnection.disconnect();
		 
	}
	
	
}
