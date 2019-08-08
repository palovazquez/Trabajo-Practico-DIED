package isi.died.tp.estructuras;

import static org.junit.Assert.*;

import org.junit.Test;

import isi.died.tp.dominio.Liquido;

public class TestLiquido {
	Liquido liq = new Liquido();

	@Test
	public void testCalcularPeso() {
		liq.setDensidad(1141.0f);
		Float resultadoReal = liq.calcularPeso(1000);
		Float resultadoEsperado = 1141.0f;
		assertEquals(resultadoReal,resultadoEsperado);
	}

}
