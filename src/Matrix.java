import java.util.Random;

public class Matrix {
	private int[][] M;
	private int num;
	private Random rand;

	public Matrix(int num) {
		this.num = num;
		M = new int[num][num];
		rand = new Random();
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
				M[v][l]=M[l][v];
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
}
