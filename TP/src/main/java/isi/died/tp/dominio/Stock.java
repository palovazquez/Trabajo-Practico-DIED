package isi.died.tp.dominio;

public class Stock {
	private Integer idStock;
	private Integer cantidad;
	private Integer puntoPedido;
	private Insumo insumo;
	
	public Stock() {
		
	}
	
	public Stock(Integer idStock, Integer cantidad, Integer puntoPedido, Insumo insumo) {
		this.idStock = idStock;
		this.cantidad = cantidad;
		this.puntoPedido = puntoPedido;
		this.insumo = insumo;
	}
	
	public Integer getIdStock() {
		return idStock;
	}
	public void setId(Integer id) {
		this.idStock = id;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getPuntoPedido() {
		return puntoPedido;
	}
	public void setPuntoPedido(Integer puntoPedido) {
		this.puntoPedido = puntoPedido;
	}
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	
	
}
