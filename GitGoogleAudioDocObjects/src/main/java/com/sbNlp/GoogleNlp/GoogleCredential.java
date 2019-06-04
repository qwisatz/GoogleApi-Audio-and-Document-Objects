package com.sbNlp.GoogleNlp;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;

public class GoogleCredential {

	// TAKE IN JSON
	// ENVIROMENT VARIABLE IF NO JSON
	private String fileNameGoogleJson;
	private FileInputStream credentialsStream ;
	private GoogleCredentials googleCredentials;
	private FixedCredentialsProvider fixedCredentialsProvider;
	
	
	// CONSTRUCTOR FOR JSON
	public GoogleCredential(String fileNameGoogleJson) throws IOException{
		 credentialsStream = new FileInputStream(fileNameGoogleJson);
		 googleCredentials = GoogleCredentials.fromStream(credentialsStream);
		 fixedCredentialsProvider = FixedCredentialsProvider.create(googleCredentials);
		 
	}
	
	public String getFileNameGoogleJson() {
		return fileNameGoogleJson;
	}

	public void setFileNameGoogleJson(String fileNameGoogleJson) {
		this.fileNameGoogleJson = fileNameGoogleJson;
	}

	public FileInputStream getCredentialsStream() {
		return credentialsStream;
	}

	public void setCredentialsStream(FileInputStream credentialsStream) {
		this.credentialsStream = credentialsStream;
	}

	public GoogleCredentials getGoogleCredentials() {
		return googleCredentials;
	}

	public void setGoogleCredentials(GoogleCredentials googleCredentials) {
		this.googleCredentials = googleCredentials;
	}

	public FixedCredentialsProvider getFixedCredentialsProvider() {
		return fixedCredentialsProvider;
	}

	public void setFixedCredentialsProvider(FixedCredentialsProvider fixedCredentialsProvider) {
		this.fixedCredentialsProvider = fixedCredentialsProvider;
	}

	
	
	
	
}
