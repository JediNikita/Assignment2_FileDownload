package com.yodaplus.filedownload.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yodaplus.filedownload.service.IFileService;

@RestController
public class FileController {
	@Autowired
	private IFileService fs;

	@RequestMapping(value="/downloadFile", method= RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void downloadFile(@RequestBody Map<String, String> paramMap)
			throws IOException {
		
		String fileName= paramMap.get("fileName");
		URL url= new URL (paramMap.get("url"));
		String path= paramMap.get("path");

		fs.writeFile(fileName, url, path);
	}

}
