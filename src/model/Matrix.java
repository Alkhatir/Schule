package model;

import java.util.Random;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;

public class Matrix {
	private final int[][] m;
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

	// public ArrayList<ArrayList<Integer>> conToVertix() {
	// ArrayList<ArrayList<Integer>> komponenten = new
	// ArrayList<ArrayList<Integer>>();
	// for (int i = 0; i < num; i++)
	// for (int y = 0; y < num; y++)
	// if (m[i][y] == 1) {
	// komponenten.get(i).add(new Integer(y));
	// }
	// return komponenten;
	// }

	public void setDm(int[][] dm) {
		this.dm = dm;
	}

	// public ArrayList<ArrayList<Integer>> conToVertix() {
	// ArrayList<ArrayList<Integer>> komponenten = new
	// ArrayList<ArrayList<Integer>>();
	// for (int i = 0; i < num; i++)
	// for (int y = 0; y < num; y++)
	// if (m[i][y] == 1) {
	// komponenten.get(i).add(new Integer(y));
	// }
	// return komponenten;
	// }

	// public ArrayList<ArrayList<Integer>> conToVertix() {
	// ArrayList<ArrayList<Integer>> komponenten = new
	// ArrayList<ArrayList<Integer>>();
	// for (int i = 0; i < num; i++)
	// for (int y = 0; y < num; y++)
	// if (m[i][y] == 1) {
	// komponenten.get(i).add(new Integer(y));
	// }
	// return komponenten;
	// }

	public void setPm(int[][] pm) {
		this.pm = pm;
	}

	public int[][] getPm() {
		return pm;
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

	// public ArrayList<ArrayList<Integer>> conToVertix() {
	// ArrayList<ArrayList<Integer>> komponenten = new
	// ArrayList<ArrayList<Integer>>();
	// for (int i = 0; i < num; i++)
	// for (int y = 0; y < num; y++)
	// if (m[i][y] == 1) {
	// komponenten.get(i).add(new Integer(y));
	// }
	// return komponenten;
	// }

	public int[][] getDm() {
		return dm;
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

	public int sum(int[][] matrix1, int[][] matrix2, int x, int y) {
		int c = 0;
		for (int i = 0; i < num; i++) {
			c += matrix1[i][y] * matrix2[x][i];
		}
		return c;
	}

	public int[][] potenzMatrixCreator(int potenz) {
		int[][] inBetweenArray = new int[num][num];
		for (int c = 0; c < potenz; c++) {
			if (newPm) {
				for (int i = 0; i < num; i++) {
					for (int y = 0; y < num; y++) {
						pm[i][y] = sum(m, m, i, y);
					}
				}
				newPm = false;
			} else {
				for (int i = 0; i < num; i++) {
					for (int y = 0; y < num; y++) {
						inBetweenArray[i][y] = sum(pm, m, i, y);
					}
				}
				pm = Arrays.copyOf(inBetweenArray, num);
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
						inBetween1[i][y] = sum(m, m, i, y);
					}
				}
				firstTime = false;
			} else {
				for (int i = 0; i < num; i++) {
					for (int y = 0; y < num; y++) {
						inBetween2[i][y] = sum(inBetween1, m, i, y);
					}
				}
				for (int i = 0; i < num; i++)
					for (int y = 0; y < num; y++)
						inBetween1[i][y] = inBetween2[i][y];
			}
		}
		return inBetween1;
	}

	public int[][] distanceMatrixCreator() {
		boolean firstTime = true;
		for (int potential = 1; potential < 10 && isZero(dm); potential++) {
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
							if (potential > 2)
								dm[i][y] = potential - 1;
							else
								dm[i][y] = potential;
						}
			}
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
//		int max = Integer.MIN_VALUE;
//		for (int i : exzentrizitaeten())
//			if (max < i)
//				max = i;
		int durchmesser = Integer.MIN_VALUE;
		for (int i = 0; i < num; i++)
			if (durchmesser < exzentrizitaeten()[i])
				durchmesser = exzentrizitaeten()[i];
		str += "der Durchmesser ist : " + durchmesser + "\n";
//		int min = Integer.MAX_VALUE;
//		for (int i : exzentrizitaeten())
//			if (min > i)
//				min = i;
		int radius = Integer.MAX_VALUE;
		for (int i = 0; i < num; i++)
			if (radius > exzentrizitaeten()[i])
				radius = exzentrizitaeten()[i];
		str += "der Radius ist : " + radius + "\n";
		List<Integer> zentrum = new ArrayList<Integer>();
		for (int i = 0; i < num; i++)
			if (exzentrizitaeten()[i] == radius)
				zentrum.add(i);
		str += "Das Zentrum ist/sind der/die Knoten : " + zentrum.toString();
		return str;
	}

//-------------------------------------------------------------------------------------------------
	/*
	 * Diese Methode soll ein Komponent von der Natritze finden und diese
	 * zurückliefern. Ein Set wird für die Knoten initialisiert. mitVerbunden
	 * symbolisiert jeden Knote, der mit Anderen verbunden ist, sodass jede
	 * ArrayList ein Knote ist und der Inhalt von Arraylist die daran hängenden
	 * Knoten ist. die Varianten 1 & 2 sind dafür, dass alle Knoten, die an einem
	 * Knote von SetKnoten anhängen, aufgerufen und in der SetKnoten ab gespeichert
	 * werden.
	 */
	public Set<Integer> verbundeneKnoten(int[][] matrix) {
		Set<Integer> setKnoten = new HashSet<Integer>();
		try {
			ArrayList<ArrayList<Integer>> mitVerbunden = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < matrix.length; i++)
				mitVerbunden.add(new ArrayList<Integer>());
			for (int i = 0; i < matrix.length; i++)
				for (int y = 0; y < matrix.length; y++)
					if (matrix[i][y] == 1)
						mitVerbunden.get(i).add(y);
			for (int i = 0; i < mitVerbunden.size(); i++)
				if (!mitVerbunden.get(i).isEmpty()) {
					setKnoten.addAll(mitVerbunden.get(i));
					break;
				}
			Set<Integer> setKnotenClone = new HashSet<>(setKnoten);
			boolean fertig = false;
			while (!fertig) {
				for (int i : setKnotenClone)
					setKnoten.addAll(mitVerbunden.get(i));
				if (setKnotenClone.containsAll(setKnoten))
					fertig = true;
				for (int i : setKnoten)
					setKnotenClone.add(i);
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "set error" + e.getMessage(), ButtonType.OK).show();
		}
//		setKnoten.addAll(mitVerbunden.get(0));
//		Set<Integer> setKnotenClone = setKnoten;
		// --------------------------------Variante
		// 1-----------------------------------------------------------
//		for (int i : setKnotenClone)
//			setKnoten.addAll(mitVerbunden.get(i));
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

	public List<int[]> komponenten(int[][] matrix) {

		List<int[]> komp = new ArrayList<int[]>();
		try {
			Set<Integer> set1 = verbundeneKnoten(matrix);
			Set<Integer> set2 = new HashSet<>(set1);
			boolean fertig = false;
			boolean firstTime = true;
			int[][] fakeM = new int[matrix.length][matrix.length];
			for (int i = 0; i < matrix.length; i++)
				for (int y = 0; y < matrix.length; y++)
					fakeM[i][y] = matrix[i][y];

			while (!fertig) {
				if (set1.size() != num && firstTime) {
					for (int i : set1)
						for (int y : set1)
							fakeM[i][y] = 0;
//					set2 = set1;
				} else {
					for (int i : set1)
						for (int y : set1)
							fakeM[i][y] = 0;
				}
				if (verbundeneKnoten(fakeM).isEmpty())
					fertig = true;
				set1 = verbundeneKnoten(fakeM);
				if (!set1.containsAll(set2)) {
					if (firstTime) {
						int[] l1 = new int[set1.size()];
						int[] l2 = new int[set2.size()];
						Iterator<Integer> iterator1 = set1.iterator();
						for (int i = 0; i < l1.length; i++)
							l1[i] = iterator1.next();
						komp.add(l1);
						Iterator<Integer> iterator2 = set2.iterator();
						for (int i = 0; i < l2.length; i++)
							l2[i] = iterator2.next();
						komp.add(l2);
						firstTime = false;
					} else {
						int[] l1 = new int[set1.size()];
						Iterator<Integer> iterator1 = set1.iterator();
						for (int i = 0; i < l1.length; i++)
							l1[i] = iterator1.next();
						komp.add(l1);
					}
				} else {
					int[] l = new int[set1.size()];
					Iterator<Integer> iterator = set1.iterator();
					for (int i = 0; i < l.length; i++)
						l[i] = iterator.next();
					komp.add(l);
					fertig = true;
				}
				boolean alleGefunden = false;
				for (int[] i : komp)
					Arrays.sort(i);
				while (!alleGefunden) {
					int c = 0;
					for (int[] i : komp)
						c += i.length;
					int[] l = new int[1];
					l[0] = 0;
					if (c != matrix.length) {
						for (int[] i : komp)
							if (i.length >= 2)
								for (int y = 1; y < i.length; y++) {
									if (i[y] - i[y - 1] == 2 && l[0] == 0) {
										l[0] = i[y - 1] + 1;
									}
									if (i[y] - i[y - 1] > 2 && l[0] == 0) {
										int n = i[y];
										for (int[] s : komp)
											if (s.length >= 2)
												for (int x = 0; x < s.length; x++)
													if (n - s[x] == 2 && l[0] == 0) {
														l[0] = n - 1;
													}
									}
								}
						komp.add(Arrays.copyOf(l, 1));
						l[0] = 0;
					} else {
						alleGefunden = true;
					}
				}
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "set error" + e.getMessage(), ButtonType.OK).show();
		}
		return komp;
	}

	public Set<Integer> artikulationen() {
		Set<Integer> artikulationKn = new HashSet<Integer>();
		List<int[]> komp1 = komponenten(m);
		int[][] fakeM = new int[num][num];
		for (int i = 0; i < num; i++)
			for (int y = 0; y < num; y++)
				fakeM[i][y] = m[i][y];
		int n = 0;
		while (n < num) {
			for (int i = 0; i < num; i++) {
				fakeM[n][i] = 0;
				fakeM[i][n] = 0;
			}
			if (komponenten(fakeM).size() - komp1.size() >= 2) {
				artikulationKn.add(n);
			}
			for (int i = 0; i < num; i++)
				for (int y = 0; y < num; y++)
					fakeM[i][y] = m[i][y];
			n++;
		}
		return artikulationKn;
	}

	public List<IntPairs> bruecken() {
		List<IntPairs> bruecke = new ArrayList<>();
		List<int[]> komp1 = komponenten(m);
		int[][] fakeM = new int[num][num];
		for (int i = 0; i < num; i++)
			for (int y = 0; y < num; y++)
				fakeM[i][y] = m[i][y];
		for (int i = 0; i < num; i++) {
			for (int y = 0; y < num; y++) {
				if (m[i][y] == 1) {
					fakeM[i][y] = 0;
					fakeM[y][i] = 0;
					if (komponenten(fakeM).size() - komp1.size() >= 1)
						bruecke.add(new IntPairs(i, y));
					fakeM[i][y] = 1;
					fakeM[y][i] = 1;
				}
			}
		}
		
		return bruecke;
	}
}
