package pk;

public class Bomba extends ObstaculoRompible {
	private int rango;
	private long tiempoDetonacion;

	public Bomba(Coordenadas pos, long tiempoDetonacion, int rango) {
		super(pos);
		this.rango = rango;
		this.tiempoDetonacion = tiempoDetonacion;
	}

	public Bomba(double xPos, double yPos, long tiempoDetonacion, int rango) {
		super(xPos, yPos);
		this.rango = rango;
		this.tiempoDetonacion = tiempoDetonacion;
	}

	@Override
	public boolean colisiona(Colisionable c) {
		if (this.isRoto() && c != this) {
			// COLISION BOMBA - OBSTACULO
			if (c instanceof Obstaculo) {
				if (c instanceof Bomba && ((Bomba) c).isRoto()) {
					return false;
				}

				if (this.getPos().compararY(((Obstaculo) c).getPos()) == 0) {
					if (this.getPos().suma(this.rango - 1, 0).compararX(((Obstaculo) c).getPos()) >= 0) {
						if (((Obstaculo) c).getPos().compararX(this.getPos().suma(-(this.rango - 1), 0)) >= 0) {
							return true;
						}
					}
				} else if (this.getPos().compararX(((Obstaculo) c).getPos()) == 0) {
					if (this.getPos().suma(0, this.rango - 1).compararY(((Obstaculo) c).getPos()) >= 0) {
						if (((Obstaculo) c).getPos().compararY(this.getPos().suma(0, -(this.rango - 1))) >= 0) {
							return true;
						}
					}
				}

				return false;
			}

			// COLISION BOMBA - BOMBERMAN
			if (this.getPos().compararY(((Bomberman) c).getPos()) == 0) {
				if (this.getPos().suma(this.rango - 1, 0).compararX(((Bomberman) c).getPos()) >= 0) {
					if (((Bomberman) c).getPos().compararX(this.getPos().suma(-(this.rango - 1), 0)) >= 0) {
						return true;
					}
				}
			} else if (this.getPos().compararX(((Bomberman) c).getPos()) == 0) {
				if (this.getPos().suma(0, this.rango - 1).compararY(((Bomberman) c).getPos()) >= 0) {
					if (((Bomberman) c).getPos().compararY(this.getPos().suma(0, -(this.rango - 1))) >= 0) {
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}

	public long getTiempoDetonacion() {
		return tiempoDetonacion;
	}

	public void setTiempoDetonacion(long tiempoDetonacion) {
		this.tiempoDetonacion = tiempoDetonacion;
	}
}