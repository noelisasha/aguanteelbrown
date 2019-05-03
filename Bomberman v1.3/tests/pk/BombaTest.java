package pk;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BombaTest {
	Bomba b;

	@Before
	public void setUp() {
		b = new Bomba(3, 3, 2000, 3);
	}
	
	@Test
	public void queExplote() {
		b.romper();		
		assertEquals(true, b.isRoto());
	}
	
	@Test
	public void colisioneObstaculoDerecha() {
		Obstaculo obs = new ObstaculoRompible(4, 3);
		b.romper();
		assertEquals(true, b.colisiona(obs));
	}
	
	@Test
	public void colisioneObstaculoAdelante() {
		Obstaculo obs = new ObstaculoRompible(3, 4);
		b.romper();
		assertEquals(true, b.colisiona(obs));
	}
	
	@Test
	public void colisioneObstaculoIzq() {
		Obstaculo obs = new ObstaculoRompible(2, 3);
		b.romper();
		assertEquals(true, b.colisiona(obs));
	}
	
	@Test
	public void colisioneObstaculoAtras() {
		Obstaculo obs = new ObstaculoRompible(3, 2);
		b.romper();
		assertEquals(true, b.colisiona(obs));
	}
	
	@Test
	public void noColisioneObstaculoFRDerecha() {
		Obstaculo obs = new ObstaculoRompible(6, 3);
		b.romper();
		assertEquals(false, b.colisiona(obs));
	}
	
	@Test
	public void noColisioneObstaculoFRAdelante() {
		Obstaculo obs = new ObstaculoRompible(3, 6);
		b.romper();
		assertEquals(false, b.colisiona(obs));
	}
	
	@Test
	public void noColisioneObstaculoFRIzq() {
		Obstaculo obs = new ObstaculoRompible(0, 3);

		b.romper();
		assertEquals(false, b.colisiona(obs));
	}
	
	@Test
	public void noColisioneObstaculoFRAtras() {
		Obstaculo obs = new ObstaculoRompible(3, 0);
		b.romper();
		assertEquals(false, b.colisiona(obs));
	}
	
	@Test
	public void noColisioneObstaculoDiagIzqSup() {
		Obstaculo obs = new ObstaculoRompible(2, 2);
		b.romper();
		assertEquals(false, b.colisiona(obs));
	}
	
	@Test
	public void noColisioneObstaculoDiagIzqInf() {
		Obstaculo obs = new ObstaculoRompible(2, 4);
		b.romper();
		assertEquals(false, b.colisiona(obs));
	}
	
	@Test
	public void noColisioneObstaculoDiagDerSup() {
		Obstaculo obs = new ObstaculoRompible(4, 2);
		b.romper();
		assertEquals(false, b.colisiona(obs));
	}
	
	@Test
	public void noColisioneObstaculoDiagDerInf() {
		Obstaculo obs = new ObstaculoRompible(4, 4);
		b.romper();
		assertEquals(false, b.colisiona(obs));
	}
	
	@Test
	public void colisioneBmDerecha() {
		Bomberman bm = new Bomberman(3, 4,"Pj");
		b.romper();
		assertEquals(true, b.colisiona(bm));
	}
	
	@Test
	public void colisioneBmAdelante() {
		Bomberman bm = new Bomberman(3, 4,"Pj");
		b.romper();
		assertEquals(true, b.colisiona(bm));
	}
	
	@Test
	public void colisioneBmIzq() {
		Bomberman bm = new Bomberman(2, 3,"Pj");
		b.romper();
		assertEquals(true, b.colisiona(bm));
	}
	
	@Test
	public void colisioneBmAtras() {
		Bomberman bm = new Bomberman(3, 2,"Pj");
		b.romper();
		assertEquals(true, b.colisiona(bm));
	}
	
	@Test
	public void noColisioneBmFRDerecha() {
		Bomberman bm = new Bomberman(6, 3,"Pj");
		b.romper();
		assertEquals(false, b.colisiona(bm));
	}
	
	@Test
	public void noColisioneBmFRAdelante() {
		Bomberman bm = new Bomberman(3, 6,"Pj");
		b.romper();
		assertEquals(false, b.colisiona(bm));
	}
	
	@Test
	public void noColisioneBmFRIzq() {
		Bomberman bm = new Bomberman(0, 3,"Pj");
		b.romper();
		assertEquals(false, b.colisiona(bm));
	}
	
	@Test
	public void noColisioneBmFRAtras() {
		Bomberman bm = new Bomberman(3, 0,"Pj");
		b.romper();
		assertEquals(false, b.colisiona(bm));
	}
	
	@Test
	public void noColisioneBmDiagIzqSup() {
		Bomberman bm = new Bomberman(2, 2,"Pj");
		b.romper();
		assertEquals(false, b.colisiona(bm));
	}
	
	@Test
	public void noColisioneBmDiagIzqInf() {
		Bomberman bm = new Bomberman(4, 2,"Pj");
		b.romper();
		assertEquals(false, b.colisiona(bm));
	}
	
	@Test
	public void noColisioneBmDiagDerSup() {
		Bomberman bm = new Bomberman(4, 2,"Pj");
		b.romper();
		assertEquals(false, b.colisiona(bm));
	}
	
	@Test
	public void noColisioneBmDiagDerInf() {
		Bomberman bm = new Bomberman(4, 4,"Pj");
		b.romper();
		assertEquals(false, b.colisiona(bm));
	}
}

