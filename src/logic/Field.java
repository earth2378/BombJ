package logic;

public class Field {
	public int[][] field = new int[11][11];

	public Field() {
		for (int i = 0; i <= 10; i++) {
			for (int j = 0; j <= 10; j++) {
				field[i][j] = 0;
			}
		}
	}

	public int getField(int x, int y) {
		return field[y][x];
	}

	public void setField(int x, int y, int value) {
		field[y][x] = value;
	}
}
