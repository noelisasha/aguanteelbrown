package pk;

import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Mapa {
	private int ancho;
	private int alto;
	private int cantObstaculosR;
	private double porcentajeObstR;
	private char[][] mapa;
	private ArrayList<Bomberman> bombermans;
	private ArrayList<Bomba> bombas;
	private ArrayList<Obstaculo> obstaculos;

	public Mapa(int ancho, int alto, int cantJugadores, double porcentajeObstR) {
		if(cantJugadores > 4) {
			cantJugadores=4;
		}
		if( porcentajeObstR > 1 || porcentajeObstR < 0) {
			porcentajeObstR=0.6;
		}
		if( ancho < 5 || alto < 5) {
			ancho =5;
			alto = 5;
		}
		this.ancho = ancho + 2;
		this.alto = alto + 2;
		this.cantObstaculosR = 0;
		this.porcentajeObstR = porcentajeObstR;
		this.bombas = new ArrayList<Bomba>();
		this.bombermans = new ArrayList<Bomberman>();
		this.obstaculos = new ArrayList<Obstaculo>();
		for (int i = 0; i < cantJugadores; i++) {
			this.bombermans.add(new Bomberman(0, 0, ""));
		}
		this.mapa=new char [this.alto+2][this.ancho+2];
		calcularObstaculosIrrompibles();
		calcularPosIniBombermans();
		calcularObstaculosRompibles();
	}

	private void calcularObstaculosRompibles() {
		double random;
		for (int i = 0; i <= 2; i++) {
			for (int j = 3; j < this.ancho - 3; j++) {
				random = random();
				// SI mapa[i][j] = '\u0000' QUIERE DECIR QUE NO HAY NADA EN ESA POSICION
				if (random <= this.porcentajeObstR  && String.valueOf(this.mapa[i][j]).equals("\u0000")) {
					this.obstaculos.add(new ObstaculoRompible(i, j));
					this.mapa[i][j] = 'X';
					this.cantObstaculosR++;
				}
			}
		}
		for (int i = 3; i < this.alto - 3; i++) {
			for (int j = 0; j < this.ancho - 1; j++) {
				random = random();
				// SI mapa[i][j] = '\u0000' QUIERE DECIR QUE NO HAY NADA EN ESA POSICION
				if (random <= this.porcentajeObstR && String.valueOf(this.mapa[i][j]).equals("\u0000")) {
					this.obstaculos.add(new ObstaculoRompible(i, j));
					this.mapa[i][j] = 'X';
					this.cantObstaculosR++;
				}
			}
		}
		
		for (int i = this.alto - 3; i < this.alto - 1; i++) {
			for (int j = 3; j < this.ancho - 3; j++) {
				random = random();
				// SI mapa[i][j] = '\u0000' QUIERE DECIR QUE NO HAY NADA EN ESA POSICION
				if (random <= this.porcentajeObstR && String.valueOf(this.mapa[i][j]).equals("\u0000")) {
					this.obstaculos.add(new ObstaculoRompible(i, j));
					this.mapa[i][j] = 'X';
					this.cantObstaculosR++;
				}
			}
		}
		
		
	}

	private void calcularObstaculosIrrompibles() {
		// AGREGO LAS PAREDES EXTERIORES EN EL ANCHO
		for (int i = 0; i < this.ancho; i++) {
			this.obstaculos.add(new ObstaculoIrrompible(i, 0));
			this.obstaculos.add(new ObstaculoIrrompible(i, this.alto - 1));
			this.mapa[0][i]= 'O';
			this.mapa[this.alto - 1][i] = 'O';
		}

		// AGREGO LAS PAREDES EXTERIORES EN EL ALTO
		for (int j = 1; j < this.alto - 1; j++) {
			this.obstaculos.add(new ObstaculoIrrompible(0, j));
			this.obstaculos.add(new ObstaculoIrrompible(this.ancho - 1, j));
			this.mapa[j][0] = 'O';
			this.mapa[j][this.ancho - 1] = 'O';
		}

		// AGREGO LAS PAREDES INTERIORES
		for (int i = 2; i < this.alto - 2; i += 2) {
			for (int j = 2; j < this.ancho - 2; j += 2) {
				this.obstaculos.add(new ObstaculoIrrompible(i, j));
				this.mapa[i][j] = 'O';
			}
		}
	}

	private void calcularPosIniBombermans() {
		for (int i = 0; i < this.bombermans.size(); i++) {
			switch (i) {
			case 0:
				this.bombermans.get(0).setPos(new Coordenadas(1.5, 1.5));
				this.mapa[1][1] = '1';
				break;
			case 1:
				this.bombermans.get(1).setPos(new Coordenadas(1.5, this.alto - 1.5));
				this.mapa[1][this.ancho-2] = '2';
				break;
			case 2:
				this.bombermans.get(2).setPos(new Coordenadas(this.ancho - 1.5, 1.5));
				this.mapa[this.alto-2][1] = '3';
				break;
			case 3:
				this.bombermans.get(3).setPos(new Coordenadas(this.ancho - 1.5, this.alto - 1.5));
				this.mapa[this.alto-2][this.ancho-2] = '4';
				break;
			default:
				break;
			}
		}
	}

	public void chequearColisiones() {
		// HashSet NO PERMITE DUPLICADOS. SI DOS BOMBAS COLISIONAN EL MISMO OBSTACULO
		// SOLO SE GUARDARÁ UNA SOLA VEZ EL OBSTACULO.
		HashSet<Entidad> colisionados = new HashSet<Entidad>();

		for (Bomba bomba : this.bombas) {
			// BOMBA-BOMBA
			if (bomba.isRoto()) {
				for (Bomba bomba2 : this.bombas) {
					if (bomba.colisiona(bomba2)) {
						colisionados.add(bomba2);
					}
				}

				// BOMBA-OBSTACULO
				for (Obstaculo obst : this.obstaculos) {
					if (bomba.colisiona(obst)) {
						colisionados.add(obst);
					}
				}

				for (Bomberman bm : bombermans) {
					if (bomba.colisiona(bm)) {
						colisionados.add(bm);
					}
				}

				for (Entidad e : colisionados) {
					if (e instanceof ObstaculoRompible && bomba.colisiona((Colisionable) e)) {
						boolean escondido = false;

						for (Entidad e2 : colisionados) {
							if (!escondido && bomba.colisiona((Colisionable) e2)) {
								escondido = ((ObstaculoRompible) e).isEscondido(bomba, e2);
							}
						}

						if (!escondido) {
							((ObstaculoRompible) e).romper();
						}
					}
				}
			}
		}

		for (Bomba bomba : bombas) {
			// BOMBA-BOMBERMAN
			for (Bomberman bm : bombermans) {
				if (!bm.isMuerto() && bomba.colisiona(bm)) {
					boolean escondido = false;

					for (Entidad e : colisionados) {
						if (!escondido && bomba.colisiona((Colisionable) e)) {
							escondido = bm.isEscondido(bomba, e);
						}
					}

					if (!escondido) {
						bm.morir();
					}
				}
			}
		}

		// BOMBERMAN-OBSTACULO
		for (Bomberman bm : bombermans) {
			for (Obstaculo obst : obstaculos) {
				if (!(bm.isMuerto()) && bm.colisiona(obst)) {
					// PARTE DECIMAL DE xPos E yPos
					double xDec = bm.getPos().getxPos() - obst.getPos().getxPos();
					double yDec = bm.getPos().getyPos() - obst.getPos().getyPos();

					// GUARDAN CUANTO COLISIONO EN x Y EN y
					double offsetX = abs(round(xDec) - xDec);
					double offsetY = abs(round(yDec) - yDec);

					if (offsetX < offsetY) {
						bm.getPos().setxPos(round(bm.getPos().getxPos()));
					} else {
						bm.getPos().setyPos(round(bm.getPos().getyPos()));
					}
				}
			}
		}
	}

	public void quitarBmMuertos() {
		ArrayList<Bomberman> aBorrar = new ArrayList<Bomberman>();

		for (Bomberman b : bombermans) {
			if (b.isMuerto()) {
				aBorrar.add(b);
			}
		}

		bombermans.removeAll(aBorrar);
	}

	public void quitarObstaculosRotos() {
		ArrayList<Obstaculo> aBorrar = new ArrayList<Obstaculo>();

		for (Obstaculo o : obstaculos) {
			if (o instanceof ObstaculoRompible) {
				if (((ObstaculoRompible) o).isRoto()) {
					aBorrar.add(o);
					cantObstaculosR--;
				}
			}
		}

		obstaculos.removeAll(aBorrar);
	}

	public void quitarBombasExplotadas() {
		ArrayList<Bomba> aBorrar = new ArrayList<Bomba>();

		for (Bomberman b : bombermans) {
			aBorrar.clear();

			for (Bomba bomba : b.getBombas())
				if (bomba.isRoto()) {
					aBorrar.add(bomba);
				}

			b.getBombas().removeAll(aBorrar);
		}
	}

	public void mostrarMapa() {
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				if (mapa[i][j] == '\u0000') {
					System.out.print("  ");
				} else {
					System.out.print(mapa[i][j] + " ");
				}
			}

			System.out.println();
		}
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getCantObstaculosR() {
		return cantObstaculosR;
	}

	public void setCantObstaculosR(int cantObstaculosR) {
		this.cantObstaculosR = cantObstaculosR;
	}

	public ArrayList<Bomberman> getBombermans() {
		return bombermans;
	}

	public void setBomberman(Bomberman bomberman) {
		this.bombermans.add(bomberman);
	}

	public ArrayList<Bomba> getBombas() {
		return bombas;
	}

	public void setBomba(Bomba bomba) {
		this.bombas.add(bomba);
	}

	public ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public void setObstaculo(Obstaculo obstaculo) {
		if (obstaculo instanceof ObstaculoRompible && !(obstaculo instanceof Bomba)) {
			cantObstaculosR++;
		}

		this.obstaculos.add(obstaculo);
	}

	public double getPorcentajeObstR() {
		return porcentajeObstR;
	}

	public void setPorcentajeObstR(double porcentajeObstR) {
		this.porcentajeObstR = porcentajeObstR;
	}
}
