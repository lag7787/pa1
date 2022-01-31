package miniJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import miniJava.SyntacticAnalyzer.Parser;
import miniJava.SyntacticAnalyzer.Scanner;


public class Compiler {
	
	public static void main(String[] args) throws IOException {
		
		int rc = 4;
		String sourceFilePath = args[0];
		
		if (!(sourceFilePath.endsWith(".java") || sourceFilePath.endsWith(".mjava"))) {
			System.exit(rc);
		}
		
		InputStream sourceFileStream = readFile(sourceFilePath);
		ErrorReporter errorReporter = new ErrorReporter();
		Scanner scanner = new Scanner(sourceFileStream, errorReporter);
		scanner.scan();
		Parser parser = new Parser(scanner, errorReporter);
		
		parser.parse();
		// testing
		if (!errorReporter.hasErrors()) {
			rc = 0;
		}
				
		System.exit(rc);
	}
	
	private static InputStream readFile(String sourceFilePath) {
		
		try {
			File sourceFile = new File(sourceFilePath);
			InputStream sourceFileReader = new FileInputStream(sourceFile);
			return sourceFileReader;
		} catch (FileNotFoundException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		}
		
	}
}
