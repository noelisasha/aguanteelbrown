package pk;

public class ObstaculoIrrompible extends Obstaculo {

	public ObstaculoIrrompible(Coordenadas pos) {
		super(pos);
	}
	
	public ObstaculoIrrompible(double xPos, double yPos) {
		super(xPos, yPos);
	}

	@Override
	public boolean colisiona(Colisionable c) {
		return false;
	}

}
