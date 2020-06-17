package model;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;

/**
 * diese Klasse liest eine Adjazenzmatrix aus eine CSV Datei ein.s
 * 
 * @author Peer Hanna
 *
 */
public class MatrixInput {
	private ArrayList<String[]> rowMatrix;

	/**
	 * Ein MatrixInput Objekt wird durch die uebernommene CSV Datei erzeugt
	 * 
	 * @param matrix ist die CSV Datei
	 * @throws IOException
	 */

	public MatrixInput(File matrix) throws IOException {
		try {
			rowMatrix = new ArrayList<String[]>();
			BufferedReader br = new BufferedReader(new FileReader(matrix));
			String zeile = br.readLine();
			while (zeile != null) {
				rowMatrix.add(zeile.split(","));
				zeile = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Ein MatrixInput wird durch den Pfad der CSV Datei erzeugt
	 * 
	 * @param pfad der CSV Datei
	 * @throws IOException
	 */

	public MatrixInput(String pfad) throws IOException {
		try {
			rowMatrix = new ArrayList<String[]>();
			BufferedReader br = new BufferedReader(new FileReader(pfad));
			String zeile = br.readLine();
			while (zeile != null) {
				rowMatrix.add(zeile.split(","));
				zeile = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * durch diese Methode wird das ArrayList rowMatrix zum zweidimensionalen Array
	 * umgewandelt
	 * 
	 * @return ein zweidimensionale Array
	 */
	public int[][] getMatrix() {
		int[][] matrix = new int[rowMatrix.size()][rowMatrix.size()];
		for (int i = 0; i < matrix.length; i++)
			for (int y = 0; y < matrix.length; y++)
				matrix[i][y] = Integer.parseInt(rowMatrix.get(i)[y]);
		return matrix;
	}

	/**
	 * Liefert die Laenge der Matrix bzw. die Anzahl der Knoten im Graph
	 * 
	 * @return int
	 */
	public int rowMatrixLength() {
		return rowMatrix.size();
	}

}