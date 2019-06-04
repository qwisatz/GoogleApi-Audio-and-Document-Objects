package com.sbNlp.GoogleNlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoogleNlpApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GoogleNlpApplication.class, args);

		/*
		 * PUT GOOGLE PROJECT JSON FILE INTO PROJECT FOLDER MAKE SURE GOOGLE API
		 * SERVICES ARE TURNED ON FOR THE GOOGLE PROJECT:
		 * 1. SPEECH API 
		 * 2. TEXT TO SPEECH API
		 * 3. NATURAL LANGUAGE API
		 * 
		 * 2 FACTORY CLASSES: 
		 * 1. GOOGLE API SERVICES 
		 * 2. DOCUMENT OBJECT PARSERS
		 * 
		 * IF READING GOOGLE PROJECT JSON FROM ENVIROMENT VARIABLES
		 * YOU DO NOT NEED TO CREATE AND INJECT CREDENTIALS TO CREATE THE API SERVICES
		 * CAN CREATE: languageServiceClient = LanguageServiceClient.create();
		 * WITHOUT NEEDING TO INPUT THE CLIENT SETTINGS FOR EACH GOOGLE API LIKE:
		 * languageServiceClient = LanguageServiceClient.create(languageServiceSettings);
		 */
		
		System.out.println("==============================================================");
		System.out.println("GOOGLE JSON CREDENTIALS");
		System.out.println("==============================================================");

		String fileNameGoogleJson = "XXXXXXXXXX-GOOGLE-JOSN-XXXXXXXXXX.json";

		System.out.println("==============================================================");
		System.out.println("TEST NATURAL LANGUAGE");
		System.out.println("==============================================================");

		String text = "Hello World! How are you doing today? This is Google Cloud Text-to-Speech Demo!";

		GoogleClientFactory googleClientFactory = new GoogleClientFactory();
		GoogleNaturalLanguageApi nlp = (GoogleNaturalLanguageApi) googleClientFactory
				.getGoogleClientFactory(fileNameGoogleJson, "NATURAL LANGUAGE");

		nlp.analyzeSentimentText(text);
		nlp.analyzeEntitiesText(text);
		nlp.analyzeSyntaxText(text);
		nlp.entitySentimentText(text);

		System.out.println("==============================================================");
		System.out.println("TEST SPEECH API");
		System.out.println("==============================================================");

		GoogleSpeechApi speech = (GoogleSpeechApi) googleClientFactory.getGoogleClientFactory(fileNameGoogleJson,
				"SPEECH");

		speech.audioToText("Google_Gnome.wav");

		System.out.println("==============================================================");
		System.out.println("TEST TEXT-TO-SPEECH API");
		System.out.println("==============================================================");

		GoogleTextToSpeechApi textToSpeech = (GoogleTextToSpeechApi) googleClientFactory
				.getGoogleClientFactory(fileNameGoogleJson, "TEXT TO SPEECH");

		textToSpeech.TextToSpeechMp3(text, "mySound");

		System.out.println("==============================================================");
		System.out.println("TEST PDF");
		System.out.println("==============================================================");

		DocumentReaderFactory documentReaderFactory = new DocumentReaderFactory();

		documentReaderFactory.getDocumentReaderFactory("MonitoringWithPrometheus_sample.pdf", "PDF");

		System.out.println("==============================================================");
		System.out.println("TEST DOCX");
		System.out.println("==============================================================");

		documentReaderFactory.getDocumentReaderFactory("lorem.docx", "DOCX");

		System.out.println("==============================================================");
		System.out.println("TEST DOC");
		System.out.println("==============================================================");

		documentReaderFactory.getDocumentReaderFactory("Cicero.doc", "DOC");

		// ERROR WHEN ANALYSE A LANGUAGE THAT IT DOESN'T SUPPORT, E.G.LATIN
		// ReaderDoc readerDoc = (ReaderDoc)
		// documentReaderFactory.getDocumentReaderFactory("Cicero.doc", "DOC");
		// nlp.analyzeSentimentText(readerDoc.readDoc("Cicero.doc"));

		System.out.println("==============================================================");
		System.out.println("TEST TXT");
		System.out.println("==============================================================");
		documentReaderFactory.getDocumentReaderFactory("Li.txt", "TXT");

		System.out.println("==============================================================");
		System.out.println("TEST RFT");
		System.out.println("==============================================================");
		documentReaderFactory.getDocumentReaderFactory("farfar.rtf", "RTF");

		ReaderRtf readerRtf = (ReaderRtf) documentReaderFactory.getDocumentReaderFactory("farfar.rtf", "RTF");
		nlp.entitySentimentText(readerRtf.readRtf("farfar.rtf"));

	}
}
