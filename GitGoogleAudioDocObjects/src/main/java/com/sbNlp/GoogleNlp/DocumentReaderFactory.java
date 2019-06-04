package com.sbNlp.GoogleNlp;

import java.io.IOException;

import javax.swing.text.BadLocationException;

public class DocumentReaderFactory {

	
	
	public DocumentReaderFactory getDocumentReaderFactory(String fileName, String documentReaderFactory) throws IOException, BadLocationException {

		if (fileName == null) {
			return null;
		}
		if (documentReaderFactory.equalsIgnoreCase("PDF")) {
			return new ReaderPdf(fileName);

		} else if (documentReaderFactory.equalsIgnoreCase("DOC")) {
			return new ReaderDoc(fileName);

		} else if (documentReaderFactory.equalsIgnoreCase("DOCX")) {
			return new ReaderDocx(fileName);
			
		} else if (documentReaderFactory.equalsIgnoreCase("TXT")) {
			return new ReaderTxt(fileName);
			
		} else if (documentReaderFactory.equalsIgnoreCase("RTF")) {
			return new ReaderRtf(fileName);
		}

		return null;
	}

}
