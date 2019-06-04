package com.sbNlp.GoogleNlp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1.AnalyzeEntitySentimentRequest;
import com.google.cloud.language.v1.AnalyzeEntitySentimentResponse;
import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.LanguageServiceSettings;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.language.v1.Token;

public class GoogleNaturalLanguageApi extends GoogleClientFactory {

	private LanguageServiceClient languageServiceClient;
	private GoogleCredential googleCredential;

	public GoogleNaturalLanguageApi(GoogleCredential googleCredential) throws IOException {
		this.googleCredential = googleCredential;
		buildLanguageServiceClient();
	}

	public void buildLanguageServiceClient() throws IOException {
		LanguageServiceSettings.Builder languageServiceSettingsBuilder = LanguageServiceSettings.newBuilder();

		LanguageServiceSettings languageServiceSettings = languageServiceSettingsBuilder
				.setCredentialsProvider(googleCredential.getFixedCredentialsProvider()).build();
		languageServiceClient = LanguageServiceClient.create(languageServiceSettings);
	}

	/*------------------------------------------------------------------*/
	// GET SENTIMENT SCORE
	/*------------------------------------------------------------------*/
	public float analyzeSentimentText(String text) throws Exception {
		Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();

		// Detects the sentiment of the text
		Sentiment sentiment = languageServiceClient.analyzeSentiment(doc).getDocumentSentiment();

		System.out.printf("Text: %s%n", text);
		if (sentiment == null) {
			System.out.println("No sentiment found");
		} else {
			System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
			System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());
		}

		return sentiment.getScore();
	}

	/*------------------------------------------------------------------*/
	// GET KEYWORDS, SALIENCE, METADATA
	/*------------------------------------------------------------------*/
	public HashMap<Integer, Entity> analyzeEntitiesText(String text) throws Exception {

		HashMap<Integer, Entity> mapEntity = new HashMap<Integer, Entity>();
		int i = 0;

		Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();

		AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder().setDocument(doc)
				.setEncodingType(EncodingType.UTF16).build();

		AnalyzeEntitiesResponse response = languageServiceClient.analyzeEntities(request);

		// Print the response
		for (Entity entity : response.getEntitiesList()) {
			System.out.println(entity);

			mapEntity.put(i, entity);
			i = i + 1;
		}
		return mapEntity;
	}

	/*------------------------------------------------------------------*/
	// ANALYSE SYNTAX
	/*------------------------------------------------------------------*/
	public List<Token> analyzeSyntaxText(String text) {
		Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
		AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder().setDocument(doc)
				.setEncodingType(EncodingType.UTF16).build();
		// analyze the syntax in the given text
		AnalyzeSyntaxResponse response = languageServiceClient.analyzeSyntax(request);
		// print the response
		for (Token token : response.getTokensList()) {
			System.out.printf("\tText: %s\n", token.getText().getContent());
			System.out.printf("\tBeginOffset: %d\n", token.getText().getBeginOffset());
			System.out.printf("Lemma: %s\n", token.getLemma());
			System.out.printf("PartOfSpeechTag: %s\n", token.getPartOfSpeech().getTag());
			System.out.printf("\tAspect: %s\n", token.getPartOfSpeech().getAspect());
			System.out.printf("\tCase: %s\n", token.getPartOfSpeech().getCase());
			System.out.printf("\tForm: %s\n", token.getPartOfSpeech().getForm());
			System.out.printf("\tGender: %s\n", token.getPartOfSpeech().getGender());
			System.out.printf("\tMood: %s\n", token.getPartOfSpeech().getMood());
			System.out.printf("\tNumber: %s\n", token.getPartOfSpeech().getNumber());
			System.out.printf("\tPerson: %s\n", token.getPartOfSpeech().getPerson());
			System.out.printf("\tProper: %s\n", token.getPartOfSpeech().getProper());
			System.out.printf("\tReciprocity: %s\n", token.getPartOfSpeech().getReciprocity());
			System.out.printf("\tTense: %s\n", token.getPartOfSpeech().getTense());
			System.out.printf("\tVoice: %s\n", token.getPartOfSpeech().getVoice());
			System.out.println("DependencyEdge");
			System.out.printf("\tHeadTokenIndex: %d\n", token.getDependencyEdge().getHeadTokenIndex());
			System.out.printf("\tLabel: %s\n\n", token.getDependencyEdge().getLabel());
		}
		return response.getTokensList();
	}

	/*------------------------------------------------------------------*/
	// ENTITY SENTIMENT TEXT
	/*------------------------------------------------------------------*/
	public void entitySentimentText(String text) {
		Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
		AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder().setDocument(doc)
				.setEncodingType(EncodingType.UTF16).build();
		// detect entity sentiments in the given string
		AnalyzeEntitySentimentResponse response = languageServiceClient.analyzeEntitySentiment(request);
		// Print the response
		for (Entity entity : response.getEntitiesList()) {
			System.out.printf("Entity: %s\n", entity.getName());
			System.out.printf("Salience: %.3f\n", entity.getSalience());
			System.out.printf("Sentiment : %s\n", entity.getSentiment());
			for (EntityMention mention : entity.getMentionsList()) {
				System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
				System.out.printf("Content: %s\n", mention.getText().getContent());
				System.out.printf("Magnitude: %.3f\n", mention.getSentiment().getMagnitude());
				System.out.printf("Sentiment score : %.3f\n", mention.getSentiment().getScore());
				System.out.printf("Type: %s\n\n", mention.getType());
			}
		}
	}

}
