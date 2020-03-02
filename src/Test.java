
public class Test {
public void testNormel()
{
	Matrix mat = new Matrix(3);
	mat.fillMatrix(0, 0, 0);
	mat.fillMatrix(1, 0, 1);
	mat.fillMatrix(2, 0, 0);
	mat.fillMatrix(0, 1, 1);
	mat.fillMatrix(1, 1, 0);
	mat.fillMatrix(2, 1, 1);
	mat.fillMatrix(0, 2, 0);
	mat.fillMatrix(1, 2, 1);
	mat.fillMatrix(2, 2, 0);
	System.out.println(mat);
	System.out.println();
	System.out.println(mat.getNum());
	System.out.println();
	mat.timeOfPowerOfMatrix(3);
	System.out.println(mat);
}
public void testRandom() {
	Matrix mat = new Matrix (4);
	mat.randomFillMatrix();
	System.out.println(mat);
	System.out.println();
	System.out.println(mat.getNum());
	System.out.println();
	mat.timeOfPowerOfMatrix(2);
	System.out.println(mat);

}
	public static void main(String[] args) {
		Test t = new Test();
		t.testRandom();
		
	}

}
