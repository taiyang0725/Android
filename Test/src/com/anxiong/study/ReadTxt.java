package com.anxiong.study;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Handler;

public class ReadTxt {
	private Reader reader;
	private BufferedReader br;
	private String content;
	private StringBuffer stringBuffer;

	private static ReadTxt readTxt;

	private ReadTxt() {
	};

	public static void main(String[] args) {
		
		System.out.println(ReadTxt.getReadTxt().read());
	}

	
	public static ReadTxt getReadTxt() {
		if (readTxt == null) {
			readTxt = new ReadTxt();
		}
		return readTxt;
	}

	public String read() {
		File file = new File("E:\\Android\\TestViewPage\\book.txt");
		stringBuffer = new StringBuffer();

		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			while ((content = br.readLine()) != null) {

				stringBuffer.append(content+"\n" );
//				System.out.println("+++++++++++++++++++++++++++++++");
				

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return stringBuffer.toString();

	}

}