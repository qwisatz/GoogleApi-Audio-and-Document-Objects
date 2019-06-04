package com.sbNlp.GoogleNlp;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReaderPdf extends DocumentReaderFactory {

	public ReaderPdf(String fileName) throws InvalidPasswordException, IOException {
		readPdf(fileName);
	}
	
	public String readPdf(String fileName) throws InvalidPasswordException, IOException {
		String text = "";
		
		PDDocument document = PDDocument.load(new File(fileName));
		if (!document.isEncrypted()) {
			PDFTextStripper stripper = new PDFTextStripper();
			text = stripper.getText(document);
			System.out.println("Text:" + text);
		}
		document.close();
		
		return text;
	}

}
