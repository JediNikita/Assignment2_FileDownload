package com.yodaplus.filedownload.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import yodaplus.resource.File;

@Service
public interface FileService {


	public void writeFile(File file) throws IOException;
	
}
