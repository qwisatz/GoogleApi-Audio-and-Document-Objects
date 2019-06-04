package com.sbNlp.GoogleNlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class ReaderRtf extends DocumentReaderFactory {

	public ReaderRtf(String fileName) throws IOException, BadLocationException {

		readRtf(fileName);

	}

	public String readRtf(String fileName) throws IOException, BadLocationException {
		
		String result = null;
		File file4 = new File(fileName);
		DefaultStyledDocument styledDoc = new DefaultStyledDocument();
		InputStream is = new FileInputStream(file4);
		new RTFEditorKit().read(is, styledDoc, 0);
		result = new String(styledDoc.getText(0, styledDoc.getLength()).getBytes("ISO8859_1"));
		// Extract the text, read Chinese needs to use ISO8859_1 encoding, otherwise
		// there will be garbled
		System.out.println(result);

		return result;
	}
}
