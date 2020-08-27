package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Message;
import com.example.demo.service.FileService;

@RestController
public class FileController {

	@Autowired
	private Message message;
	
	@Autowired
	private FileService fs;
	private static final int BUFFER_SIZE = 4096;
	String success;

	@RequestMapping(value="downloadFile", method= RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void downloadFile(@RequestBody Map<String, String> paramMap) 
			throws IOException {
		String fileName= paramMap.get("fileName");
		URL url= new URL (paramMap.get("url"));
		String path= paramMap.get("path");
		HttpURLConnection URLConnection= (HttpURLConnection) url.openConnection();
		int responseCode= URLConnection.getResponseCode();

		if(responseCode==HttpURLConnection.HTTP_OK) {
			int contentLength = URLConnection.getContentLength();

			InputStream inputStream = URLConnection.getInputStream();
			String saveFilePath = path +"/" + fileName;

			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();
			success= "file downloaded";
			message.setFileSize(contentLength);

		} else {
			success= "error "+ responseCode;
			message.setFileSize(0);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		message.setCurrentTime(sdf.format(Calendar.getInstance().getTime()));
		message.setFileName(fileName);
		message.setSuccess(success);
		
		fs.save(message);
		
		URLConnection.disconnect();
		
		
	}
	
	
}
