package model;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class MatrixInput {
	private ArrayList<String[]> rowMatrix;

	public MatrixInput(String pfad) throws IOException {
		try {
		rowMatrix = new ArrayList<String[]>();
		BufferedReader br = new BufferedReader(new FileReader(pfad));
		String zeile = br.readLine();
		while(zeile != null) {
			rowMatrix.add(zeile.split(","));
			 zeile = br.readLine();
		}
		br.close();
		} catch (IOException e ) {
				throw e;
				}
	}

	public int [][] getMatrix(){
		int [][] matrix = new int[rowMatrix.size()][rowMatrix.size()];
		for(int i = 0; i< matrix.length; i++)
			for (int y = 0 ; y< matrix.length; y++)
				matrix [i][y]= Integer.parseInt(rowMatrix.get(i)[y]);
		return matrix;
	}
	public int rowMatrixLength() {
		return rowMatrix.size();
	}

}