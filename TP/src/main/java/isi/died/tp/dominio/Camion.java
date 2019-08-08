package isi.died.tp.dominio;

public class Camion {
	
	private String idRegistro;
	private String marca;
	private String modelo;
	private String dominio;
	private Integer año;
	private Float costoPorKm;
	private Float capacidad;
	private boolean aptoParaLiquidos;
	
	
	public Camion() {
		
	}
	
	public Camion(String idRegistro, String marca, String modelo, String dominio, Integer año, Float costoPorKm, Float capacidad, boolean esRefrigerado) {
		this.idRegistro = idRegistro;
		this.marca = marca;
		this.modelo = modelo;
		this.dominio = dominio;
		this.año = año;
		this.costoPorKm = costoPorKm;
		this.capacidad = capacidad;
		this.aptoParaLiquidos = esRefrigerado;
	}
	
	/**
	 * @return the idRegistro
	 */
	public String getIdRegistro() {
		return idRegistro;
	}
	/**
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return the dominio
	 */
	public String getDominio() {
		return dominio;
	}
	/**
	 * @param dominio the dominio to set
	 */
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	/**
	 * @return the año
	 */
	public Integer getAño() {
		return año;
	}
	/**
	 * @param año the año to set
	 */
	public void setAño(Integer año) {
		this.año = año;
	}
	/**
	 * @return the costoPorKm
	 */
	public Float getCostoPorKm() {
		return costoPorKm;
	}
	/**
	 * @param costoPorKm the costoPorKm to set
	 */
	public void setCostoPorKm(Float costoPorKm) {
		this.costoPorKm = costoPorKm;
	}
	/**
	 * @return the capacidad
	 */
	public Float getCapacidad() {
		return capacidad;
	}
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(Float capacidad) {
		this.capacidad = capacidad;
	}
	/**
	 * @return the aptoParaLiquidos
	 */
	public boolean isAptoParaLiquidos() {
		return aptoParaLiquidos;
	}
	/**
	 * @param aptoParaLiquidos the aptoParaLiquidos to set
	 */
	public void setAptoParaLiquidos(boolean aptoParaLiquidos) {
		this.aptoParaLiquidos = aptoParaLiquidos;
	}
	
}
