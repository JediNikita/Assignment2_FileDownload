package com.yodaplus.filedownload.service;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public interface IFileService {
	public void writeFile(String fileName, URL url, String path) throws IOException ;
	
}
