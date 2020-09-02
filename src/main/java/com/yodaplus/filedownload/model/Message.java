package com.yodaplus.filedownload.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="message")
public class Message {
	@Id
	private String fileName;
	private float fileSize;
	private String timeOfDownload;
	private String success;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public float getFileSize() {
		return fileSize;
	}

	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}

	public String getCurrentTime() {
		return timeOfDownload;
	}

	public void setCurrentTime(String string) {
		this.timeOfDownload = string;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	
}
