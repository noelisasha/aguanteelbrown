package pk;

import java.util.ArrayList;
import static java.lang.Math.*;

public class Bomberman extends Entidad implements Colisionable {
	private double velocidad;
	private double puntaje;
	private long tiempoBomba;
	private int rangoBomba;
	private int maxBombas;
	private boolean muerto;
	private final static Movimiento ARRIBA = Movimiento.ARRIBA;
	private final static Movimiento ABAJO = Movimiento.ABAJO;
	private final static Movimiento DERECHA = Movimiento.DERECHA;
	private final static Movimiento IZQUIERDA = Movimiento.IZQUIERDA;
	private String nombre;
	private ArrayList<Bomba> bombas;

	public Bomberman(double xPos, double yPos, String nombre) {
		this.pos = new Coordenadas(xPos, yPos);
		this.velocidad = 0.1;
		this.tiempoBomba = 2000; // 2 seg
		this.puntaje = 0;
		this.muerto = false;
		this.nombre = nombre;
		this.bombas = new ArrayList<Bomba>();
		this.maxBombas = 3;
	}

	public void mover(Movimiento m) {
		this.pos = new Coordenadas(this.pos.getxPos() + m.x() * this.velocidad,
				this.pos.getyPos() + m.y() * this.velocidad);
	}

	public boolean ponerBomba() {
		if (this.bombas.size() < maxBombas) {

			this.bombas.add(new Bomba(this.pos, this.tiempoBomba, this.rangoBomba));

			return true;

		}

		return false;
	}

	@Override
	public boolean colisiona(Colisionable c) {
		if (this.pos.compararX(((Obstaculo) c).getPos().suma(1, 0)) < 0) {
			if (this.pos.compararX(((Obstaculo) c).getPos()) > 0) {
				if (this.pos.compararY(((Obstaculo) c).getPos().suma(0, 1)) < 0) {
					if (this.pos.compararY(((Obstaculo) c).getPos()) > 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public final boolean isEscondido(Bomba bomba, Entidad obst) {
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

		return false;
	}
	
	public void morir() {
		this.muerto = true;
	}

	public boolean isMuerto() {
		return muerto;
	}

	public Coordenadas getPos() {
		return pos;
	}

	public void setPos(Coordenadas pos) {
		this.pos = pos;
	}

	public static Movimiento getArriba() {
		return ARRIBA;
	}

	public static Movimiento getAbajo() {
		return ABAJO;
	}

	public static Movimiento getDerecha() {
		return DERECHA;
	}

	public static Movimiento getIzquierda() {
		return IZQUIERDA;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public long getTiempoBomba() {
		return tiempoBomba;
	}

	public void setTiempoBomba(long tiempoBomba) {
		this.tiempoBomba = tiempoBomba;
	}

	public int getRangoBomba() {
		return rangoBomba;
	}

	public void setRangoBomba(int rangoBomba) {
		this.rangoBomba = rangoBomba;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Bomba> getBombas() {
		return bombas;
	}

	public void setBombas(ArrayList<Bomba> bombas) {
		this.bombas = bombas;
	}
}


//PRIVATE DOUBLE DISTANCIA(BOMBERMAN BM) {
//	RETURN SQRT(POW(BM.GETPOS().GETXPOS()-THIS.GETPOS().GETXPOS(),2) +POW(BM.GETPOS().GETYPOS()-THIS.GETPOS().GETYPOS(),2));
//}
//
//PUBLIC DOUBLE PROMEDIODISTANCIA(ARRAYLIST<BOMBERMAN> BOMBERMANS) {
//	DOUBLE DISTANCIATOTAL=0;
//	FOR (BOMBERMAN BM : BOMBERMANS) {
//		IF(THIS != BM ){
//			DISTANCIATOTAL+= THIS.DISTANCIA(BM);
//		}
//	}
//	RETURN DISTANCIATOTAL / (BOMBERMANS.SIZE() - 1);
//}
