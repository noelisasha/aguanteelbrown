package pk;

public class ObstaculoRompible extends Obstaculo {
	private boolean roto;

	public ObstaculoRompible(Coordenadas pos) {
		super(pos);
		this.roto = false;
	}

	public ObstaculoRompible(double xPos, double yPos) {
		super(xPos, yPos);
		this.roto = false;
	}

	@Override
	public boolean colisiona(Colisionable c) {
		return false;
	}

	public final boolean isEscondido(Bomba bomba, Entidad obst) {
		if (!(obst instanceof Bomba)) {
			if (obst.getPos().compararX(bomba.getPos()) > 0 && this.getPos().compararX(obst.getPos()) > 0) {
				return true;
			}

			if (obst.getPos().compararX(bomba.getPos()) < 0 && this.getPos().compararX(obst.getPos()) < 0) {
				return true;
			}

			if (obst.getPos().compararY(bomba.getPos()) > 0 && this.getPos().compararY(obst.getPos()) > 0) {
				return true;
			}

			if (obst.getPos().compararY(bomba.getPos()) < 0 && this.getPos().compararY(obst.getPos()) < 0) {
				return true;
			}
		}
		
		return false;
	}

	public void romper() {
		this.roto = true;
	}

	public boolean isRoto() {
		return this.roto;
	}
}
