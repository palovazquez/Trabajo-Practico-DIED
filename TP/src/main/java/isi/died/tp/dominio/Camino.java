package isi.died.tp.dominio;

import isi.died.tp.estructuras.Arista;
import isi.died.tp.estructuras.Vertice;

public class Camino extends Arista<Planta> {
	private Integer distancia;
	private Integer duracion;
	private Float pesoMax;
	private Planta planta1;
	private Planta planta2;
	private String id;
	
	public Camino() {
		
	}
	
	/**
	 * @return the id
	 */
	public String getIdCamino() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setIdCamino(String id) {
		this.id = id;
	}

	public Camino(String id, Integer distancia, Integer duracion, Float pesoMax, Planta planta1, Planta planta2) {
		this.id = id;
		this.distancia = distancia;
		this.duracion = duracion;
		this.pesoMax = pesoMax;
		this.planta1 = planta1;
		this.planta2 = planta2;		
	}
	
	public Integer getDistancia() {
		return distancia;
	}

	/**
	 * @return the planta1
	 */
	public Planta getPlanta1() {
		return planta1;
	}

	/**
	 * @param planta1 the planta1 to set
	 */
	public void setPlanta1(Planta planta1) {
		this.planta1 = planta1;
	}

	/**
	 * @return the planta2
	 */
	public Planta getPlanta2() {
		return planta2;
	}

	/**
	 * @param planta2 the planta2 to set
	 */
	public void setPlanta2(Planta planta2) {
		this.planta2 = planta2;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}
	
	public Integer getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Float getPesoMax() {
		return pesoMax;
	}
	
	public void setPesoMax(Float pesoMax) {
		this.pesoMax = pesoMax;
	}
	
	public Planta getOrigen() {
		return planta1;
	}
	
	public void setOrigen(Planta origen) {
		this.planta1 = origen;
	}
	
	public Planta getDestino() {
		return planta2;
	}
	
	public void setDestino(Planta destino) {
		this.planta2 = destino;
	}
	
	
}
