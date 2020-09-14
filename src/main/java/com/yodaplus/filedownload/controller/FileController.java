package com.yodaplus.filedownload.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yodaplus.filedownload.service.FileService;

import yodaplus.resource.File;

@RestController
public class FileController {
	@Autowired
	private FileService fs;

	@RequestMapping(value="/downloadFile", method= RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void downloadFile(@RequestBody Map<String, String> paramMap)
			throws IOException {
		
		String fileName= paramMap.get("fileName");
		String url= paramMap.get("url");
		String path= paramMap.get("path");

		fs.writeFile(new File(fileName, url, path));
	}

}
