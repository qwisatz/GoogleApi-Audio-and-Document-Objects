package com.sbNlp.GoogleNlp;

import java.io.IOException;

public class GoogleClientFactory {

	private GoogleCredential googleCredential;
	
	/*------------------------------------------------------------------*/
	// GENERATE THE GOOGLE API CLIENT NEEDED
	/*------------------------------------------------------------------*/
	public GoogleClientFactory getGoogleClientFactory(String fileNameGoogleJson, String googleClient) throws IOException {
		
		//CREATE GOOGLE CREDENTIALS
		googleCredential = new GoogleCredential(fileNameGoogleJson);
		
		if (googleClient == null) {
			return null;
		}
		if (googleClient.equalsIgnoreCase("NATURAL LANGUAGE")) {
			return new GoogleNaturalLanguageApi(googleCredential);

		} else if (googleClient.equalsIgnoreCase("SPEECH")) {
			return new GoogleSpeechApi(googleCredential);

		} else if (googleClient.equalsIgnoreCase("TEXT TO SPEECH")) {
			return new GoogleTextToSpeechApi(googleCredential);
		}

		return null;
	}

}
