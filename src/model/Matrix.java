package model;

import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
public class Matrix {
	private int[][] M;
	private int num;
	private Random rand;
	private File fl;
	private Scanner sc;
	
	public Matrix () {
		num = 9;
		M = new int[num][num];
		rand = new Random();
		fl = new File ("C:\\Users\\peerh\\Desktop\\MAtric.txt");
		try {
			sc = new Scanner(fl);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public File getFl() {
		return fl;
	}
	public Matrix(int num) {
		this();
		setNum(num);
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < num; i++) {
			str += "\n";
			for (int j = 0; j < num; j++) {
				str += M[i][j] + " ";
			}
		}
		return str;
	}

	public void randomFillMatrix() {
		for (int i = 0; i < num; i++) {
			for (int j = i + 1; j < num; j++) {
				int v = 0;
				v = rand.nextInt(2);
				M[i][j] = v;
			}
		}
		for (int l = 0; l < num; l++) {
			for (int v = 0; v < num; v++) {
				M[v][l] = M[l][v];
			}
		}
	}

	public void fillMatrix(int x, int y, int z) {
		M[x][y] = z;
	}

	public boolean zeroDetecter() {
		int x = 0;
		int y = 0;
		boolean habibi = true;
		while (x < num && y < num && habibi) {
			if (M[x][y] == 0) {
				habibi = false;
			} else {
				x++;
				while (y < num && habibi) {
					if (M[x][y] == 0) {
						habibi = false;
					} else {
						y++;
					}
				}

			}
		}
		return habibi;
	}

	public int Sum(int x, int y) {
		int c = 0;
		for (int i = 0; i < num; i++) {
			c += M[i][y] * M[x][i];
		}
		return c;
	}

	public void powerOfMatrix() {
		int[][] h = new int[num][num];
		for (int i = 0; i < num; i++) {
			for (int y = 0; y < num; y++) {
				h[i][y] = Sum(i, y);
			}
		}
		for (int l = 0; l < num; l++) {
			for (int c = 0; c < num; c++) {
				M[l][c] = h[l][c];
			}
		}

	}

	public void timeOfPowerOfMatrix(int x) {
		for (int c = 0; c < x; c++) {
			powerOfMatrix();
		}
	}
	public void setNum(int num) {
		this.num = num;
	}
	/*
	 * Here I am trying to read the file 
	 * */
	public String read() {
		String re = "";
		while (sc.hasNextBigInteger()|| sc.hasNextLine()) {
			re = Integer.toString(sc.nextInt());
		}
		sc.close();
		return re;	
	}
	/*
	 * Here I am trying to see if the file had been already imported
	 * */
	public String trFile() {
		String trf = "";
		if  (fl.canRead()) {
			trf = "it is working";
		}
		return trf;
	}
}
