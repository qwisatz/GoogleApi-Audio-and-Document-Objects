package com.sbNlp.GoogleNlp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.TextToSpeechSettings;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;

public class GoogleTextToSpeechApi extends GoogleClientFactory {

	private TextToSpeechClient textToSpeechClient;
	private GoogleCredential googleCredential;

	public GoogleTextToSpeechApi(GoogleCredential googleCredential) throws IOException {
		this.googleCredential = googleCredential;
		buildTextToSpeechSetting();
	}

	public void buildTextToSpeechSetting() throws IOException {

		TextToSpeechSettings textToSpeechSettings = TextToSpeechSettings.newBuilder()
				.setCredentialsProvider(googleCredential.getFixedCredentialsProvider()).build();

		textToSpeechClient = TextToSpeechClient.create(textToSpeechSettings);
	}

	public String TextToSpeechMp3(String text, String outputName) throws FileNotFoundException, IOException {
		String verification = "FAILED TO CREATE MP3 FILE";

		SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

		// Build the voice request, select the language code ("en-US") and the ssml
		// voice gender
		// ("neutral")
		VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("en-US")
				.setSsmlGender(SsmlVoiceGender.NEUTRAL).build();

		// Select the type of audio file you want returned
		AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();

		// Perform the text-to-speech request on the text input with the selected voice
		// parameters and
		// audio file type
		SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

		// Get the audio contents from the response
		ByteString audioContents = response.getAudioContent();

		// Write the response to the output file.
		try (OutputStream out = new FileOutputStream(outputName + ".mp3")) {
			out.write(audioContents.toByteArray());
			System.out.println("SUCCEEDED IN CREATING TO CREATE MP3 FILE");
		}

		return verification;
	}

}
