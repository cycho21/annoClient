package kr.ac.uos.ai.annotator.filemover.filemaker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * This class is written by Chan Yeon, Cho
 * AI-Laboratory, Seoul, Korea
 * 2015. 9. 15. 오후 2:51:47
 */

public class FileMaker {
	
	public FileMaker() {
	
	}

	public void init() {
		
	}
	
	public void makeFileFromByteArray(String pathName, int byteArray) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(pathName);
			fos.write(byteArray);
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not make file to custom path name");
		} catch (IOException e) {
			System.out.println("Byte array error");
		}
	}
}
