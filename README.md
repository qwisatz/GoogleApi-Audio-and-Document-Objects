# GoogleApi-Audio-and-Document-Objects
This Java project uses Google Cloud Speech and Cloud Speech-to-Text and Cloud Natural Language. Popular document file formats are also parsed to be able to be used with the Cloud API's 

A Google Project must be created to retrieve the Google Project JSON and placed into the root of the project folder.
The API's for the 3 Cloud services must be activated in order to use the services.

Features include:
1. Transcribing audio to text
2. Text to Mp3 files
3. Analysing Text (Sentiment, category, syntax, entities etc...)

In Eclipse, import the project as an existing Maven project.
The main class is	GoogleNlpApplication.java. 2 Factory classes handles the creation of the Google clients and document file format parsers (pdf, docx, doc, txt, rtf).
