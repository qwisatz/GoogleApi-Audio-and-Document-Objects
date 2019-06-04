package com.sbNlp.GoogleNlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;

public class ReaderDoc extends DocumentReaderFactory {

	public ReaderDoc(String fileName) throws IOException {

		readDoc(fileName);
		
	}

	public String readDoc(String fileName) throws IOException {
		String text = "";
		
		File fin = new File("Cicero.doc");
		FileInputStream fis2 = new FileInputStream(fin);
		HWPFDocument doc = new HWPFDocument(fis2);
		text = doc.getDocumentText();
		System.out.println(text);
		
		return text;
	}
	
}
