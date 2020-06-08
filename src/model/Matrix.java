package model;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;

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

	public int[][] getM() {
		return m;
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
		str += "\n" + "\n" + "Potenzmatrix ist : " + "\n";
		for (int i = 0; i < num; i++) {
			str += "\n";
			for (int j = 0; j < num; j++) {
				str += pm[i][j] + " ";
			}
		}
		str += "\n" + "\n" + "Distanzmatrix ist : " + "\n";
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
				}
				inBetween1 = inBetween2;
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
						if (distanceMatrixCreatorHelper(potential)[i][y] >= potential && dm[i][y] == 0 && i != y) {
							dm[i][y] = potential;
						}
			}

			potential++;
		}
		return dm;
	}

	public int[] exzentrizitaeten() {
		int[] l = new int[num];
		for (int i = 0; i < num; i++) {
			l[i] = 0;
			for (int y = 0; y < num; y++)
				if (l[i] < distanceMatrixCreator()[i][y])
				l[i] = distanceMatrixCreator()[i][y];
		}
		return l;
	}

	public String showExzentrizitaeten() {
		String str = "";
		for (int i = 0; i < num; i++)
			str += "Knote " + i + " hat die Exzentrizitaet : " + exzentrizitaeten()[i] + "\n";
		int max = Integer.MIN_VALUE;
		for (int i : exzentrizitaeten())
			if (max < i)
				max = i;
		ArrayList<Integer> durchmesser = new ArrayList<Integer>();
		for (int i = 0; i < num; i++)
			if (max == exzentrizitaeten()[i])
				durchmesser.add(i);
		str += "die/der Durchmesser ist/sind der Knote : " + durchmesser.toString() + "\n";
		int min = Integer.MAX_VALUE;
		for (int i : exzentrizitaeten())
			if (min > i)
				min = i;
		ArrayList<Integer> radius = new ArrayList<Integer>();
		for (int i = 0; i < num; i++)
			if (min == exzentrizitaeten()[i])
				radius.add(i);
		str += "die/der Radius ist/sind der Knote : " + radius.toString() + "\n";
		return str;
	}

	/*
	 * Diese Methode soll ein Komponent von der Natritze finden und diese
	 * zurückliefern. Ein Set wird für die Knoten initialisiert. mitVerbunden
	 * symbolisiert jeden Knote, der mit Anderen verbunden ist, sodass jede
	 * ArrayList ein Knote ist und der Inhalt von Arraylist die daran hängenden
	 * Knoten ist. die Varianten 1 & 2 sind dafür, dass alle Knoten, die an einem
	 * Knote von SetKnoten anhängen, aufgerufen und in der SetKnoten ab gespeichert
	 * werden.
	 */
	public Set<Integer> verbundeneKnoten(int[][] komp) {
		Set<Integer> setKnoten = new HashSet<Integer>();
		ArrayList<ArrayList<Integer>> mitVerbunden = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < komp.length; i++)
			mitVerbunden.add(new ArrayList<Integer>());
		for (int i = 0; i < komp.length; i++)
			for (int y = 0; y < komp.length; y++)
				if (komp[i][y] == 1)
					mitVerbunden.get(i).add(y);
		setKnoten.addAll(mitVerbunden.get(0));
		Set<Integer> setKnotenClone = setKnoten;
		// --------------------------------Variante
		// 1-----------------------------------------------------------
		for (int i : setKnotenClone)
			setKnoten.addAll(mitVerbunden.get(i));
		// ---------------------------------Variante
		// 2-----------------------------------------------------------
//		try {
//			boolean geaendert = false;
//			while (!geaendert) {
//				Iterator<Integer> itr = setKnoten.iterator();
//				Set<Integer> setKnotenClone = setKnoten;
//				while (itr.hasNext()) {
//					setKnotenClone.addAll(mitVerbunden.get(itr.next()));
//				}
//				if (setKnotenClone.equals(setKnoten))
//					geaendert = true;
//				setKnoten = setKnotenClone;
//			}
//		} catch (ConcurrentModificationException e) {
//			System.out.println(e.getMessage());
//		}
		// --------------------------------------------------------------------------------------------------------
		return setKnoten;
	}

	public Set<Integer> artikulationen() {
		Set<Integer> artikulationKn = new HashSet<Integer>();
		Set<Integer> komp1 = verbundeneKnoten(m);
		int[][] fakeM = m;
		int n = 0;
		while (n < num) {
			for (int i = 0; i < num; i++) {
				fakeM[n][i] = 0;
				fakeM[i][n] = 0;
			}
			if (!komp1.containsAll(verbundeneKnoten(fakeM))) {
				artikulationKn.add(n);
			}
			n++;
		}
		return artikulationKn;
	}

	public List<LinkedList<Integer>> komponenten() {
		List<LinkedList<Integer>> knotengruppen = new ArrayList<LinkedList<Integer>>();

		return knotengruppen;
	}

	LinkedList<Integer> komponent(int[][] matr) {
		LinkedList<Integer> komp = new LinkedList<Integer>();
		komp.addAll(verbundeneKnoten(matr));
		return komp;
	}
//	public ArrayList<ArrayList<Integer>> conToVertix() {
//		ArrayList<ArrayList<Integer>> komponenten = new ArrayList<ArrayList<Integer>>();
//		for (int i = 0; i < num; i++)
//			for (int y = 0; y < num; y++)
//				if (m[i][y] == 1) {
//					komponenten.get(i).add(new Integer(y));
//				}
//		return komponenten;
//	}

	public int[][] getDm() {
		return dm;
	}

	public void setDm(int[][] dm) {
		this.dm = dm;
	}

	public int[][] getPm() {
		return pm;
	}

	public void setPm(int[][] pm) {
		this.pm = pm;
	}

//	public ArrayList<ArrayList<Integer>> komponenten() {
//		ArrayList<ArrayList<Integer>> komponenten = new ArrayList<ArrayList<Integer>>();
//		boolean zusammenhaengend = false;
//		for (int i = 0; i < num; i++)
//			for (int y = 0; y < num; y++)
//				if (conToVertix().get(i).get(y))
//		for (int i = 0; i < num; i++)
//			for (int y = 0; y < num; y++) {
//				komponenten.add(conToVertix().get(i));
//				
//			}
//		return komponenten;
//	}

}
