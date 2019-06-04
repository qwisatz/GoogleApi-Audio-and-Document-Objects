package com.sbNlp.GoogleNlp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.protobuf.ByteString;

public class GoogleSpeechApi extends GoogleClientFactory {

	private SpeechClient speechClient;
	private GoogleCredential googleCredential;

	public GoogleSpeechApi(GoogleCredential googleCredential) throws IOException {
		this.googleCredential = googleCredential;

		buildSpeechClient();
	}

	public void buildSpeechClient() throws IOException {
		SpeechSettings speechSettings = SpeechSettings.newBuilder()
				.setCredentialsProvider(googleCredential.getFixedCredentialsProvider()).build();

		speechClient = SpeechClient.create(speechSettings);
	}

	/*------------------------------------------------------------------*/
	// AUDIO TO STRING TRANSCRIPT
	/*------------------------------------------------------------------*/
	public String audioToText(String fileName) throws IOException {
		String transcript = "";

		// Reads the audio file into memory
		Path path = Paths.get(fileName);
		byte[] data = Files.readAllBytes(path);
		ByteString audioBytes = ByteString.copyFrom(data);

		// Builds the sync recognize request
		RecognitionConfig config = RecognitionConfig.newBuilder().setEncoding(AudioEncoding.LINEAR16)
				.setSampleRateHertz(16000).setLanguageCode("en-US").build();
		RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();

		// Performs speech recognition on the audio file
		RecognizeResponse response = speechClient.recognize(config, audio);
		List<SpeechRecognitionResult> results = response.getResultsList();

		for (SpeechRecognitionResult result : results) {
			List<SpeechRecognitionAlternative> alternatives = result.getAlternativesList();
			for (SpeechRecognitionAlternative alternative : alternatives) {
				// alternative.getTranscript() = STRING CLASS
				System.out.printf(alternative.getTranscript());
				transcript = transcript + alternative.getTranscript();
			}
		}
		return transcript;
	}

}
