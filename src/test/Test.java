package test;

import java.util.HashMap;

import model.Matrix;
import model.MatrixInput;

public class Test {
	public void testMatrixInput() {
		MatrixInput mat = new MatrixInput();
		System.out.println(mat.read());
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

	void testG1() {
		Matrix mat = new Matrix(6);
		mat.fillMatrix(0, 0, 0);
		mat.fillMatrix(0, 1, 1);
		mat.fillMatrix(0, 2, 1);
		mat.fillMatrix(0, 3, 1);
		mat.fillMatrix(0, 4, 1);
		mat.fillMatrix(0, 5, 1);
		mat.fillMatrix(1, 0, 1);
		mat.fillMatrix(1, 1, 0);
		mat.fillMatrix(1, 2, 1);
		mat.fillMatrix(1, 3, 0);
		mat.fillMatrix(1, 4, 0);
		mat.fillMatrix(1, 5, 1);
		mat.fillMatrix(2, 0, 1);
		mat.fillMatrix(2, 1, 1);
		mat.fillMatrix(2, 2, 0);
		mat.fillMatrix(2, 3, 1);
		mat.fillMatrix(2, 4, 0);
		mat.fillMatrix(2, 5, 0);
		mat.fillMatrix(3, 0, 1);
		mat.fillMatrix(3, 1, 0);
		mat.fillMatrix(3, 2, 1);
		mat.fillMatrix(3, 3, 0);
		mat.fillMatrix(3, 4, 1);
		mat.fillMatrix(3, 5, 0);
		mat.fillMatrix(4, 0, 1);
		mat.fillMatrix(4, 1, 0);
		mat.fillMatrix(4, 2, 0);
		mat.fillMatrix(4, 3, 1);
		mat.fillMatrix(4, 4, 0);
		mat.fillMatrix(4, 5, 1);
		mat.fillMatrix(5, 0, 1);
		mat.fillMatrix(5, 1, 1);
		mat.fillMatrix(5, 2, 0);
		mat.fillMatrix(5, 3, 0);
		mat.fillMatrix(5, 4, 1);
		mat.fillMatrix(5, 5, 0);
		System.out.println();
		mat.potenzMatrixCreator(1);
		System.out.println();
		mat.distanceMatrixCreator();
		System.out.println(mat);
		mat.showExzentrizitaeten();
	}

	void testG2() {
		Matrix mat = new Matrix(6);
		mat.fillMatrix(0, 0, 0);
		mat.fillMatrix(0, 1, 1);
		mat.fillMatrix(0, 2, 0);
		mat.fillMatrix(0, 3, 1);
		mat.fillMatrix(0, 4, 0);
		mat.fillMatrix(0, 5, 0);
		mat.fillMatrix(1, 0, 1);
		mat.fillMatrix(1, 1, 0);
		mat.fillMatrix(1, 2, 0);
		mat.fillMatrix(1, 3, 0);
		mat.fillMatrix(1, 4, 0);
		mat.fillMatrix(1, 5, 0);
		mat.fillMatrix(2, 0, 0);
		mat.fillMatrix(2, 1, 0);
		mat.fillMatrix(2, 2, 0);
		mat.fillMatrix(2, 3, 0);
		mat.fillMatrix(2, 4, 0);
		mat.fillMatrix(2, 5, 1);
		mat.fillMatrix(3, 0, 1);
		mat.fillMatrix(3, 1, 0);
		mat.fillMatrix(3, 2, 0);
		mat.fillMatrix(3, 3, 0);
		mat.fillMatrix(3, 4, 0);
		mat.fillMatrix(3, 5, 0);
		mat.fillMatrix(4, 0, 0);
		mat.fillMatrix(4, 1, 0);
		mat.fillMatrix(4, 2, 0);
		mat.fillMatrix(4, 3, 0);
		mat.fillMatrix(4, 4, 0);
		mat.fillMatrix(4, 5, 0);
		mat.fillMatrix(5, 0, 0);
		mat.fillMatrix(5, 1, 0);
		mat.fillMatrix(5, 2, 1);
		mat.fillMatrix(5, 3, 0);
		mat.fillMatrix(5, 4, 0);
		mat.fillMatrix(5, 5, 0);
		System.out.println();
		mat.potenzMatrixCreator(1);
		mat.distanceMatrixCreator();
		System.out.println(mat);
		mat.showExzentrizitaeten();
	}

	void testA() {
		Matrix mat = new Matrix(10);
		mat.fillMatrix(0, 0, 0);
		mat.fillMatrix(0, 1, 1);
		mat.fillMatrix(0, 2, 0);
		mat.fillMatrix(0, 3, 0);
		mat.fillMatrix(0, 4, 0);
		mat.fillMatrix(0, 5, 1);
		mat.fillMatrix(0, 6, 0);
		mat.fillMatrix(0, 7, 0);
		mat.fillMatrix(0, 8, 0);
		mat.fillMatrix(0, 9, 0);

		mat.fillMatrix(1, 0, 1);
		mat.fillMatrix(1, 1, 0);
		mat.fillMatrix(1, 2, 1);
		mat.fillMatrix(1, 3, 0);
		mat.fillMatrix(1, 4, 0);
		mat.fillMatrix(1, 5, 1);
		mat.fillMatrix(1, 6, 0);
		mat.fillMatrix(1, 7, 0);
		mat.fillMatrix(1, 8, 0);
		mat.fillMatrix(1, 9, 0);

		mat.fillMatrix(2, 0, 0);
		mat.fillMatrix(2, 1, 1);
		mat.fillMatrix(2, 2, 0);
		mat.fillMatrix(2, 3, 0);
		mat.fillMatrix(2, 4, 1);
		mat.fillMatrix(2, 5, 0);
		mat.fillMatrix(2, 6, 0);
		mat.fillMatrix(2, 7, 0);
		mat.fillMatrix(2, 8, 0);
		mat.fillMatrix(2, 9, 0);

		mat.fillMatrix(3, 0, 0);
		mat.fillMatrix(3, 1, 0);
		mat.fillMatrix(3, 2, 0);
		mat.fillMatrix(3, 3, 0);
		mat.fillMatrix(3, 4, 1);
		mat.fillMatrix(3, 5, 0);
		mat.fillMatrix(3, 6, 1);
		mat.fillMatrix(3, 7, 0);
		mat.fillMatrix(3, 8, 1);
		mat.fillMatrix(3, 9, 0);

		mat.fillMatrix(4, 0, 0);
		mat.fillMatrix(4, 1, 0);
		mat.fillMatrix(4, 2, 1);
		mat.fillMatrix(4, 3, 1);
		mat.fillMatrix(4, 4, 0);
		mat.fillMatrix(4, 5, 0);
		mat.fillMatrix(4, 6, 0);
		mat.fillMatrix(4, 7, 0);
		mat.fillMatrix(4, 8, 1);
		mat.fillMatrix(4, 9, 0);

		mat.fillMatrix(5, 0, 1);
		mat.fillMatrix(5, 1, 1);
		mat.fillMatrix(5, 2, 0);
		mat.fillMatrix(5, 3, 0);
		mat.fillMatrix(5, 4, 0);
		mat.fillMatrix(5, 5, 0);
		mat.fillMatrix(5, 6, 0);
		mat.fillMatrix(5, 7, 0);
		mat.fillMatrix(5, 8, 0);
		mat.fillMatrix(5, 9, 0);

		mat.fillMatrix(6, 0, 0);
		mat.fillMatrix(6, 1, 0);
		mat.fillMatrix(6, 2, 0);
		mat.fillMatrix(6, 3, 1);
		mat.fillMatrix(6, 4, 0);
		mat.fillMatrix(6, 5, 0);
		mat.fillMatrix(6, 6, 0);
		mat.fillMatrix(6, 7, 1);
		mat.fillMatrix(6, 8, 0);
		mat.fillMatrix(6, 9, 1);

		mat.fillMatrix(7, 0, 0);
		mat.fillMatrix(7, 1, 0);
		mat.fillMatrix(7, 2, 0);
		mat.fillMatrix(7, 3, 0);
		mat.fillMatrix(7, 4, 0);
		mat.fillMatrix(7, 5, 0);
		mat.fillMatrix(7, 6, 1);
		mat.fillMatrix(7, 7, 0);
		mat.fillMatrix(7, 8, 0);
		mat.fillMatrix(7, 9, 1);

		mat.fillMatrix(8, 0, 0);
		mat.fillMatrix(8, 1, 0);
		mat.fillMatrix(8, 2, 0);
		mat.fillMatrix(8, 3, 1);
		mat.fillMatrix(8, 4, 1);
		mat.fillMatrix(8, 5, 0);
		mat.fillMatrix(8, 6, 0);
		mat.fillMatrix(8, 7, 0);
		mat.fillMatrix(8, 8, 0);
		mat.fillMatrix(8, 9, 0);

		mat.fillMatrix(9, 0, 0);
		mat.fillMatrix(9, 1, 0);
		mat.fillMatrix(9, 2, 0);
		mat.fillMatrix(9, 3, 0);
		mat.fillMatrix(9, 4, 0);
		mat.fillMatrix(9, 5, 0);
		mat.fillMatrix(9, 6, 1);
		mat.fillMatrix(9, 7, 1);
		mat.fillMatrix(9, 8, 0);
		mat.fillMatrix(9, 9, 0);
		mat.potenzMatrixCreator(1);
		mat.distanceMatrixCreator();
		System.out.println(mat);
		mat.showExzentrizitaeten();
	}

	public static void main(String[] args) {
		Test t = new Test();
		t.testG1();
	}

}
