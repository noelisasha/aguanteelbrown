package pk;

public enum Movimiento {
	ARRIBA(0, -1), ABAJO(0, 1), DERECHA(1, 0), IZQUIERDA(-1, 0);

	private int x;
	private int y;

	private Movimiento(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
}
