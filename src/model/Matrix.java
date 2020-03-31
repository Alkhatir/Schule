package model;

import java.util.Random;
import java.util.ArrayList;

public class Matrix {
	private int[][] m;
	private int[][] dm;
	private int[][] pm;
	private boolean newPm;
	private int num;
	private Random rand;

	public Matrix() {
		num = 9;
		dm = new int[num][num];
		m = new int[num][num];
		pm = new int[num][num];
		rand = new Random();
		newPm = true;

	}

	public Matrix(int num) {
		m = new int[num][num];
		dm = new int[num][num];
		pm = new int[num][num];
		rand = new Random();
		setNum(num);
		newPm = true;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		String str = "Adjazenzmatrix : " + "\n";
		for (int i = 0; i < num; i++) {
			str += "\n";
			for (int j = 0; j < num; j++) {
				str += m[i][j] + " ";
			}
		}
		str += "\n" + "\n" + "Potenzmatrix ist :" + "\n";
		for (int i = 0; i < num; i++) {
			str += "\n";
			for (int j = 0; j < num; j++) {
				str += pm[i][j] + " ";
			}
		}
		str += "\n" + "\n" + "Distanzmatrix ist :" + "\n";
		for (int i = 0; i < num; i++) {
			str += "\n";
			for (int j = 0; j < num; j++) {
				str += dm[i][j] + " ";
			}
		}
		return str;
	}

	public void randomFillMatrix() {
		for (int i = 0; i < num; i++) {
			for (int j = i + 1; j < num; j++) {
				int v = 0;
				v = rand.nextInt(2);
				m[i][j] = v;
			}
		}
		for (int l = 0; l < num; l++) {
			for (int v = 0; v < num; v++) {
				m[v][l] = m[l][v];
			}
		}
	}

	public void fillMatrix(int x, int y, int z) {
		m[x][y] = z;
	}

	public boolean isZero(int[][] matrix) {
		boolean isZero = false;
		for (int i = 0; i < num && !isZero; i++)
			for (int y = 0; y < num && !isZero; y++)
				if (i != y && matrix[i][y] == 0)
					isZero = true;
		return isZero;
	}

	public int sum(int[][] matrix, int x, int y) {
		int c = 0;
		for (int i = 0; i < num; i++) {
			c += matrix[i][y] * matrix[x][i];
		}
		return c;
	}

	public int[][] potenzMatrixCreator(int potenz) {
		int[][] inBetweenArray = new int[num][num];
		for (int c = 0; c < potenz; c++) {
			if (newPm) {
				for (int i = 0; i < num; i++) {
					for (int y = 0; y < num; y++) {
						pm[i][y] = sum(m, i, y);
					}
				}
				newPm = false;
			} else {
				for (int i = 0; i < num; i++) {
					for (int y = 0; y < num; y++) {
						inBetweenArray[i][y] = sum(pm, i, y);
					}
				}
				pm = inBetweenArray;
			}
		}
		return inBetweenArray;

	}

	public int[][] distanceMatrixCreatorHelper(int potenz) {
		int[][] inBetween1 = new int[num][num];
		int[][] inBetween2 = new int[num][num];
		boolean firstTime = true;
		for (int c = 0; c < potenz; c++) {
			if (firstTime) {
				for (int i = 0; i < num; i++) {
					for (int y = 0; y < num; y++) {
						inBetween1[i][y] = sum(m, i, y);
					}
				}
				firstTime = false;
			} else {
				for (int i = 0; i < num; i++) {
					for (int y = 0; y < num; y++) {
						inBetween2[i][y] = sum(inBetween1, i, y);
					}
					inBetween1 = inBetween2;
				}
			}
		}
		return inBetween1;
	}

	public int[][] distanceMatrixCreator() {
		int potential = 1;
		boolean firstTime = true;
		while (potential < 10 && isZero(dm)) {
			if (firstTime) {
				for (int i = 0; i < num; i++)
					for (int y = 0; y < num; y++)
						if (m[i][y] >= potential && i != y) {
							dm[i][y] = potential;
						}
				firstTime = false;
			} else {
				for (int i = 0; i < num; i++)
					for (int y = 0; y < num; y++)
						if (distanceMatrixCreatorHelper(potential - 1)[i][y] >= potential && dm[i][y] == 0 && i != y) {
							dm[i][y] = potential;
						}
			}

			potential++;
		}
		return dm;
	}

	public int[] exzentrizitaeten() {
		int[] l = new int[num];
		for (int i = 0; i < num; i++)
			for (int y = 0; y < num; y++)
				l[i] += distanceMatrixCreator()[i][y];
		return l;
	}

	public void showExzentrizitaeten() {
		for (int i = 0; i < num; i++)
			System.out.println("Knote " + i + " hat die Exzentrizitaet : " + exzentrizitaeten()[i]);
		int max = Integer.MIN_VALUE;
		for (int i : exzentrizitaeten())
			if (max < i)
				max = i;
		ArrayList<Integer> durchmesser = new ArrayList<Integer>();
		for (int i = 0; i < num; i++)
			if (max == exzentrizitaeten()[i])
				durchmesser.add(i);
		System.out.println("die/der Durchmesser ist/sind der Knote: " + durchmesser.toString());
		int min = Integer.MAX_VALUE;
		for (int i : exzentrizitaeten())
			if (min > i)
				min = i;
		ArrayList<Integer> radius = new ArrayList<Integer>();
		for (int i = 0; i < num; i++)
			if (min == exzentrizitaeten()[i])
				radius.add(i);
		System.out.println("die/der Radius ist/sind der Knote : " + radius.toString());
	}

}
