package com.yodaplus.filedownload.service;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.yodaplus.filedownload.DAO.FileDAO;
import com.yodaplus.filedownload.model.Message;

import yodaplus.resource.File;


public class FileServiceImpl implements FileService{

	@Autowired
	private FileDAO filedao;
	private static final int BUFFER_SIZE = 4096;

	@Override
	public void writeFile(File file) throws IOException {
		String saveFilePath= file.getPath() +"/" + file.getFileName();
		String success;
		Message message= new Message();
		
		HttpURLConnection URLConnection= (HttpURLConnection) new URL(file.getUrl()).openConnection();
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
		message.setFileName(file.getFileName()); 
		message.setSuccess(success);

		filedao.save(message); 
		URLConnection.disconnect();

	}


}
