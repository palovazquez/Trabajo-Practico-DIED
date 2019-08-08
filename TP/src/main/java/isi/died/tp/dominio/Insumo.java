package isi.died.tp.dominio;

import java.util.ArrayList;

import isi.died.tp.interfaces.crear.CrearPlanta;


public class Insumo implements Comparable<Insumo> {

	private String id;
	private String descripcion;
	protected Unidad unidadDeMedida;
	private Float costo;
	private Integer stock;
	private Float peso;
	private boolean esRefrigerado;
	private boolean esLiquido ;
	private Float densidad;
	private Float volumen;
	
	public Insumo() {
		
	}
	public Insumo(String id1, String descripcion1, Float costo1, Integer stock1, Float peso1, boolean esRefrigerado1, Unidad unidadDeMedida, Float densidad, Float volumen, Boolean esLiquido){
		this.id = id1;
		this.descripcion = descripcion1;
		this.unidadDeMedida = unidadDeMedida;
		this.costo = costo1;
		this.stock = stock1;
		this.peso = peso1;
		this.esRefrigerado = esRefrigerado1;
		this.densidad = densidad;
		this.volumen = volumen;
		this.esLiquido = esLiquido;
	}
	public float getDensidad() {
		return densidad;
	}
	public float getVolumen() {
		return volumen;
	}
	
	public int getStockTotal() {
		ArrayList<Planta> plantas = new ArrayList<Planta>();
		plantas = CrearPlanta.contenedorPlantas;
		int stockTotalInsumo = 0;
		
		for(int i=0; i <plantas.size(); i++) {
			for (int j=0; j<plantas.get(i).getStocks().size(); j++) {
				if (plantas.get(i).getStocks().get(j).getInsumo().equals(this))
					stockTotalInsumo += plantas.get(i).getStocks().get(j).getCantidad();
			}
		}
		return stockTotalInsumo;
	}

	public Insumo(float costo) {
		this.costo = costo;
	}
	public boolean getEsLiquido(){
		return esLiquido;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Unidad getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(Unidad unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	@Override
	public int compareTo(Insumo otroInsumo) {
		return (this.stock-otroInsumo.stock);
	}
	
	public String getId() {
		return id;
	}
	public Unidad getUnidad() {
		return unidadDeMedida;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public boolean isEsRefrigerado() {
		return esRefrigerado;
	}
	public void setEsRefrigerado(boolean esRefrigerado) {
		this.esRefrigerado = esRefrigerado;
	}
	public String toString() {
		return ("Stock: "+this.stock);
	}
	
}