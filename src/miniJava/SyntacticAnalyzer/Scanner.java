package miniJava.SyntacticAnalyzer;

import java.io.IOException;
import java.io.InputStream;

import miniJava.ErrorReporter;

public class Scanner {
	
	private InputStream inputStream;
	private ErrorReporter errorReporter;
	private char currentChar;
	private StringBuilder currentSpelling;
	private boolean eot = false; 

	
	public Scanner(InputStream inputStream, ErrorReporter errorReporter) {
		this.inputStream = inputStream;
		this.errorReporter = errorReporter;
		readChar();
	}
	
	public Token scan() {
		
		skipSeparators();
		System.out.println(currentChar);
		return new Token();
	}
	
	private void takeIt() {
		currentSpelling.append(currentChar);
		nextChar();
	}
	
	private void skipIt() {
		nextChar();
	}
	
	private void nextChar() {
		if (!eot)
			readChar();
	}
	
	private void scanError(String m) {
		errorReporter.reportError("Scan Error:  " + m);
	}
	
	private void skipSeparators() {
		switch (currentChar) {
		case ' ': case '\t': case '\r': case '\n':
			skipIt();
			skipSeparators();
			break;
		case '/':
			//need to handle comment case
			skipIt();
			if (currentChar == '*') {

			} else if (currentChar == '/') {
				
			}
			
			
			skipSeparators();
			break;
		default:
			break;
		}
	}	
	
	private void readChar() {
		try {
			int c = inputStream.read();
			currentChar = (char) c;
			if (c == -1) {
				eot = true;
			}
		} catch (IOException e) {
			scanError("I/O Exception!");
			eot = true;
		}
	}
}
