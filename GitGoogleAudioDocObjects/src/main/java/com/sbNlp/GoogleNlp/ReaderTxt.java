package com.sbNlp.GoogleNlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderTxt extends DocumentReaderFactory {

	public ReaderTxt(String fileName) throws IOException {

		 readTxt(fileName);
		
	}
	
	
	public String readTxt(String fileName) throws IOException {
		String text = "";
		
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer buff = new StringBuffer();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			buff.append(temp + "\r\n");
		}
		br.close();
		
		text = buff.toString();
		System.out.println(text); 
		
		return text;
	}
	
}
