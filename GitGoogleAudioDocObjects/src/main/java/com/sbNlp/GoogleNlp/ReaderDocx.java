package com.sbNlp.GoogleNlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ReaderDocx extends DocumentReaderFactory {

	public ReaderDocx(String fileName) throws FileNotFoundException, IOException {

		readDocx(fileName);
		
	}
	
	public String readDocx(String fileName) throws FileNotFoundException, IOException{
		String text = "";
		
		XWPFDocument docx = new XWPFDocument(new FileInputStream("lorem.docx"));

		// using XWPFWordExtractor Class
		XWPFWordExtractor we = new XWPFWordExtractor(docx);
		System.out.println(we.getText());
		
		text = we.getText();
		
		return text;
	}

}
