package test;

import java.io.IOException;

import model.Matrix;
import model.MatrixInput;

public class Test {
	public void testMatrixInput() {
		
		try {
			MatrixInput mat = new MatrixInput("C:\\Users\\peerh\\Desktop\\Nicht fertige Aufgaben\\G1.csv");
			Matrix m = new Matrix(mat.rowMatrixLength());
			for (int i = 0; i < mat.rowMatrixLength(); i++)
				for (int y = 0; y <mat.rowMatrixLength(); y++)
					m.fillMatrix(i, y, mat.getMatrix()[i][y]);
			m.distanceMatrixCreator();
			m.potenzMatrixCreator(1);
			System.out.println(m);
			System.out.println(m.verbundeneKnoten(m.getM()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	public void testRandom() {
		Matrix mat = new Matrix(4);
		mat.randomFillMatrix();
		System.out.println(mat);
		System.out.println();
		System.out.println(mat.getNum());
		System.out.println();
		mat.potenzMatrixCreator(4);
		System.out.println(mat);

	}

	public static void main(String[] args) {
		Test t = new Test();
		t.testMatrixInput();
	}

}
