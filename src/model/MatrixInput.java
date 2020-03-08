package model;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class MatrixInput {
	private File fl;
	private Scanner sc;
	public MatrixInput() {
		fl = new File("C:\\Users\\peerh\\Desktop\\MAtric.txt");
		try {
			sc = new Scanner(fl);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Here I am trying to read the file
	 */
	public String read() {
		String re = "";
		while (sc.hasNextBigInteger() || sc.hasNextLine()) {
			re = Integer.toString(sc.nextInt());
		}
		sc.close();
		return re;
	}

	/*
	 * Here I am trying to see if the file had been already imported
	 */
	public String trFile() {
		String trf = "";
		if (fl.canRead()) {
			trf = "it is working";
		}
		return trf;

	}
}