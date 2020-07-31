package com.ntels.ccbs.batch.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CFile {

	public static File fd;
	public static FileReader rd;
	public static FileWriter wd;
	public static BufferedReader brd;
	public static BufferedWriter bwd;

	CFile() {
		fd = null;
		rd = null;
		wd = null;
		brd = null;
		bwd = null;
	}

	public boolean fileOpen(String filename) throws IOException {
		boolean bRet = true;
		fd = new File(filename);
//		System.out.println("fileOpen filename" + filename+" FD = "+fd+" RD ="+rd+" WD ="+wd);

		if (fd.exists() == false) {
			System.out.println(filename + " : fail-no such file.");
			bRet = fd.createNewFile();

			if (bRet == false)
				return false;
		}
//		if (fd.length() > 1) {
//			System.out.println(filename + " : fail-one more file.");
//			return false;
//		}
		try {
			rd = new FileReader(fd);
			wd = new FileWriter(fd, true);
		} catch (FileNotFoundException e) {
			System.out.println(filename + " : fail-File Open Error.");
			e.printStackTrace();
		}
//		System.out.println("File Open FD = "+fd+" RD ="+rd+" WD ="+wd);

		return true;
	}

	public static void fileClose() {
		try {

			if (brd != null)
				brd.close();
			if (bwd != null)
				bwd.close();
			if (rd != null)
				rd.close();
			if (wd != null)
				wd.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String fileReadByte(int offset, int length) {
		char[] buffer = new char[4096];
		int nlen = 0;
		String ret = null;

		try {
			nlen = rd.read(buffer, offset, length);
			ret = buffer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static String fileReadLine() {
		String ret = null;

		if (brd == null)
			brd = new BufferedReader(rd);
		try {
			ret = brd.readLine();
			brd.close();
			if (ret == null)
				brd.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static void fileWriteByte(char[] st, int offset, int length) {
		try {
			wd.write(st, offset, length);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void fileWriteLine(String st) throws Exception {
//System.out.println("fileWriteLine WD="+wd);
		if (bwd == null)
			bwd = new BufferedWriter(wd);
		// System.out.println("fileWriteLine : " + st + ":" + fd.canWrite() +
		// "bwd :" + bwd.getClass() );

		try {
			bwd.write(st);
			bwd.newLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("fileWriterLine Error");
			e.printStackTrace();
		}

	}

}
