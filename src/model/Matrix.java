package model;

import java.util.Random;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ist eine Klasse, die eine Adjatzenzmatrix uebernimmt, rechnet daraus die
 * Potenzmatrix und die Distanzmatrix und findet alle Komponenten, Bruecken und
 * Artikulationen
 * 
 * @author Peer Hanna
 *
 */

public class Matrix {
	private final int[][] m;
	private int[][] dm;
	private int[][] pm;
	private boolean newPm;
	private int num;
	private Random rand;

	/**
	 * Erzeugt ein Matrix Objekt mit einer Adjazenzmatrix nach dem Zufallsprinzip
	 */
	public Matrix() {
		num = 9;
		dm = new int[num][num];
		m = new int[num][num];
		pm = new int[num][num];
		rand = new Random();
		newPm = true;

	}

	/**
	 * Erzeugt ein Matrix Objekt nach einer bestimmten Laenge
	 * 
	 * @param num ist die Laenge der Matrize
	 */

	public Matrix(int num) {
		m = new int[num][num];
		dm = new int[num][num];
		pm = new int[num][num];
		rand = new Random();
		setNum(num);
		newPm = true;
	}

	/**
	 * liefert die Potenzmatrix zurueck
	 * 
	 * @return ein zweidimensionale Array
	 */
	public int[][] getPm() {
		return pm;
	}

	/**
	 * liefert die Laenge der Matritze
	 * 
	 * @return int
	 */
	public int getNum() {
		return num;
	}

	/**
	 * setzt die Laenge der Matrize
	 * 
	 * @param num
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * Liefert die Adajazenzmatrix zurueck
	 * 
	 * @return ein zweidimensionale Array
	 */
	public int[][] getM() {
		return m;
	}

	/**
	 * Liefert die Distanzmatrix zurueck
	 * 
	 * @return ein zweidimensionale Array
	 */

	public int[][] getDm() {
		return dm;
	}

	/**
	 * hier werden die Adjanzenzmatrix, die Potenzmatrix und die Distanzmatrix
	 */
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

	/**
	 * Eine Methode fuellt die Ajatzenzmatrix nach dem Zufallsprinzip ab
	 */
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

	/**
	 * Eine Methode fuellt die Ajatzenzmatrix ab
	 * 
	 * @param x ist die Spalte
	 * @param y ist die Zeile
	 * @param z ist der Wert (1 , 0)
	 */
	public void fillMatrix(int x, int y, int z) {
		m[x][y] = z;
	}

	/**
	 * Eine Methode liefert TRUE zurueck, wenn eine Matrize eine Null nicht in der
	 * Hauptdiagonale hat.
	 * 
	 * @param matrix
	 * @return True oder False
	 */
	public boolean hasZero(int[][] matrix) {
		boolean isZero = false;
		for (int i = 0; i < num && !isZero; i++)
			for (int y = 0; y < num && !isZero; y++)
				if (i != y && matrix[i][y] == 0)
					isZero = true;
		return isZero;
	}

	/**
	 * Eine Methode berechnet das Podukt aus einer Zeile von Matrix1 und einer
	 * Spalte von Matrix2
	 * 
	 * @param matrix1
	 * @param matrix2
	 * @param x       ist die Zeile von Matrix2
	 * @param y       ist die Spalte von Matrix1
	 * @return das Produkt
	 */
	public int sum(int[][] matrix1, int[][] matrix2, int x, int y) {
		int c = 0;
		for (int i = 0; i < num; i++) {
			c += matrix1[i][y] * matrix2[x][i];
		}
		return c;
	}

	/**
	 * Eine Methode rechnet die Potenzmatrix mit Hilfe der sum Methode
	 * 
	 * @param potenz
	 * @return ein zweidimensionale Array
	 */

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

	/**
	 * eine Methode rechnet die Potenzmatrix nach der distanceMatrixCreator Methode.
	 * 
	 * @param potenz
	 * @return ein zweidimensionale Array
	 */
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

	/**
	 * eine Methode rechnet die Distanzmatrix aus der Adjatzenzmatrix mit Hilfe der
	 * distanceMatrixCreatorHelper Methode. Zuerste werden alle Einsen in der
	 * Adjatzentmatrix uebernommen. Dann wird die Potenz um Eins erhoeht und mit
	 * dieser wird der Potenzmatrix ausgerechnet. Die Distanzmatrix wird den Wert
	 * der Potenz uebernehmen nur dann, wenn der entsprechende Nuller in der
	 * Distanzmatrix einen neuen Wert in der Potenzmatrix betraegt.
	 * 
	 * @return ein zweidimensionale Array
	 */

	public int[][] distanceMatrixCreator() {
		boolean firstTime = true;
		for (int potenz = 1; potenz < 10 && hasZero(dm); potenz++) {
			if (firstTime) {
				for (int i = 0; i < num; i++)
					for (int y = 0; y < num; y++)
						if (m[i][y] >= potenz && i != y) {
							dm[i][y] = potenz;
						}
				firstTime = false;
			} else {
				for (int i = 0; i < num; i++)
					for (int y = 0; y < num; y++)
						if (distanceMatrixCreatorHelper(potenz)[i][y] >= potenz && dm[i][y] == 0 && i != y) {
							// DAS UNTEN IST NICHT LOGISCH !!!!!!!!!!!!!!!!!!!!!!
							if (potenz > 2)
								dm[i][y] = potenz - 1;
							else
								dm[i][y] = potenz;
						}
			}
		}
		return dm;
	}

	/**
	 * eine Methode rechnet die Exzentrizitaeten aller Knoten aus der Distanzmatrix
	 * aus.
	 * 
	 * @return int[], wobei das Index der Knote ist.
	 */
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

	/**
	 * eine Methode rechnet den Durchmesser, den Radius und das Zentrum mit Hilfe
	 * der exzentrizitaeten Methode aus und wirft diese und die Exzentrizitaeten
	 * aller Knoten in ein String hinein
	 * 
	 * @return String
	 */
	public String showExzentrizitaeten() {
		String str = "";
		for (int i = 0; i < num; i++)
			str += "Knote " + i + " hat die Exzentrizitaet : " + exzentrizitaeten()[i] + "\n";
		int durchmesser = Integer.MIN_VALUE;
		for (int i = 0; i < num; i++)
			if (durchmesser < exzentrizitaeten()[i])
				durchmesser = exzentrizitaeten()[i];
		str += "der Durchmesser ist : " + durchmesser + "\n";
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

	/**
	 * eine Methode leifert eine Set von Integers zurueck, die eine Menge von
	 * Knoten, die miteinander verbunden sind, enhaelt.
	 * 
	 * @param matrix ist ein zweidimensionale Array
	 * @return eine Set von Konten
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
		return setKnoten;
	}

	/**
	 * eine Methode liefert alle Komponenten in der Matrize mit Hilfe der
	 * verbundeneKnoten Methode zurueck, sodass entweder ein Komponente im Fall vom
	 * zusammenhaengenden Graph oder zwei Komponenten rausgefunden und von der
	 * uebernommenen Matrize geloescht werden, um den naechsten Komponente durch die
	 * verbundeneKnoten Methode rauszufinden ...usw
	 * 
	 * @param matrix ist ein zweidimensionale Array
	 * @return Liste vom int[], wobei int[] der Komponente ist
	 */
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

	/**
	 * eine Methode liefert die Artikulationen zurueck, die dadurch ausgesucht
	 * werden, dass ein Konte entfernt und ueberprueft wird, ob ein neuer Komponente
	 * erzeugt wird.
	 * 
	 * @return die Artikulationen in Form einer Set Collection von Integers
	 */
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

	/**
	 * eine Methode liefert die Bruecken zurueck, die dadurch ausgesucht werden,
	 * dass eine Kante entfernt und ueberprueft wird, ob ein neuer Komponente
	 * erzeugt wird. Es koennte die selbe Kante zwei mals in der Liste wegen der
	 * Reihenfolge von den Knoten in IntPairs geben.
	 * 
	 * @return eine Liste von IntPairs
	 */
	public List<IntPairs> bruecken() {
		List<IntPairs> bruecken = new ArrayList<>();
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
						bruecken.add(new IntPairs(i, y));
					fakeM[i][y] = 1;
					fakeM[y][i] = 1;
				}
			}
		}
//		bruecke = noDuplicate(bruecke);
		return bruecken;
	}

//	public List<IntPairs> noDuplicate(List<IntPairs> lip) {
//		boolean hasDuplicate = true;
//		List<IntPairs> newBruecke = new ArrayList<>(lip);
//		int n = 0;
//		while (hasDuplicate) {
//			IntPairs ip = lip.get(n);
//			for (int i = 0; i < lip.size(); i++) {
//				if (lip.get(i).contains(ip.getY(), ip.getX())) {
//					newBruecke.remove(i);
//				}
//			}
//			n++;
//		}
//
//		return newBruecke;
//	}
}
