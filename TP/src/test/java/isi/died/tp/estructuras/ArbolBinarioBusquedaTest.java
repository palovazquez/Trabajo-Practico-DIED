package isi.died.tp.estructuras;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import isi.died.tp.dominio.Insumo;

public class ArbolBinarioBusquedaTest {

	@Test
	public void testContiene() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsArbolOfE() {
		fail("Not yet implemented");
	}

	@Test
	public void testAgregar() {
		fail("Not yet implemented");
	}

	@Test
	public void testProfundidad() {
		fail("Not yet implemented");
	}

	@Test
	public void testCuentaNodosDeNivel() {
		fail("Not yet implemented");
	}

	@Test
	public void testEsCompleto() {
		fail("Not yet implemented");
	}

	@Test
	public void testEsLleno() {
		fail("Not yet implemented");
	}

	@Test
	public void rango() {
		Insumo insumo1 = new Insumo(8);
		Insumo insumo2 = new Insumo(3);
		Insumo insumo3 = new Insumo(1);
		Insumo insumo4 = new Insumo(6);
		Insumo insumo5 = new Insumo(4);
		Insumo insumo6 = new Insumo(7);
		Insumo insumo7 = new Insumo(10);
		Insumo insumo8 = new Insumo(14);
		Insumo insumo9 = new Insumo(13);
		ArbolBinarioBusqueda<Insumo> abb = new ArbolBinarioBusqueda<Insumo>(insumo1);
		abb.agregar(insumo2);
		abb.agregar(insumo3);
		abb.agregar(insumo4);
		abb.agregar(insumo5);
		abb.agregar(insumo6);
		abb.agregar(insumo7);
		abb.agregar(insumo8);
		abb.agregar(insumo9);
		List<String> listaReal = new ArrayList<String>();
		listaReal = abb.rango(4,10);
		List<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("Stock: 6");
		listaEsperada.add("Stock: 4");
		listaEsperada.add("Stock: 7");
		listaEsperada.add("Stock: 8");
		listaEsperada.add("Stock: 10");
		assertEquals(listaReal,listaEsperada);
	}
	
}
