package pk;

public abstract class Obstaculo extends Entidad implements Colisionable {
	public Obstaculo(Coordenadas pos) {
		this.pos = pos;
	}

	public Obstaculo(double xPos, double yPos) {
		this.pos = new Coordenadas(xPos, yPos);
	}

	public Coordenadas getPos() {
		return pos;
	}

	public void setPos(Coordenadas pos) {
		this.pos = pos;
	}
}
