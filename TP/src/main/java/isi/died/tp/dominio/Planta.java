package isi.died.tp.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import isi.died.tp.estructuras.Vertice;
import isi.died.tp.interfaces.crear.CrearPlanta;

public class Planta extends Vertice {
	
	private String idPlanta;
	private String nombre;
	private int cantidadCaminos;
	private TipoPlanta tipoPlanta;
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
		
	public Planta() {
		
	}
	
	/**
	 * @return the tipoPlanta
	 */
	public TipoPlanta getTipoPlanta() {
		return tipoPlanta;
	}

	/**
	 * @param tipoPlanta the tipoPlanta to set
	 */
	public void setTipoPlanta(TipoPlanta tipoPlanta) {
		this.tipoPlanta = tipoPlanta;
	}

	public Planta(String idPlanta, String nombre, TipoPlanta tipoPlanta) {
		this.idPlanta = idPlanta;
		this.nombre = nombre;
		this.cantidadCaminos = 0;      
		this.tipoPlanta = tipoPlanta;
	}
	
	public Planta(String idPlanta, String nombre, ArrayList<Stock> stocks) {
		this.idPlanta = idPlanta;
		this.nombre = nombre;
		this.stocks = stocks;
		this.cantidadCaminos = 0;        
	}
	
	public Planta(String id, String nombre, ArrayList<Stock> stocks, int cantCaminos) {
		this.idPlanta = id;
		this.nombre = nombre;
		this.stocks = stocks;
		this.cantidadCaminos = cantCaminos;
	}
	
	public ArrayList<Insumo> resolver(Camion camion){
		Float capacidadCamion = (1000.0f)*(camion.getCapacidad());
		int capacidadC = (int) (capacidadCamion +1); 
		int cantElementos = 0;
		for(int j=0; j<this.getStocks().size(); j++) {
			if(this.necesitaInsumo(this.getStocks().get(j).getInsumo())) {	
				cantElementos++;}}
		Float[] costoPorInsumo=new Float[cantElementos];
		Float[] pesoPorInsumo=new Float[cantElementos];
		boolean solucion[][] = new boolean[cantElementos][capacidadC];
		int[][] tablaOpciones = new int[cantElementos][capacidadC];	
		
		int i=0;
		for(int h=0; h<this.getStocks().size(); h++) {
			if(this.necesitaInsumo(this.getStocks().get(h).getInsumo())) {	
				costoPorInsumo[i] = (this.getStocks().get(h).getInsumo().getCosto())*(this.cantidadFaltante(this.getStocks().get(h).getInsumo()));
				pesoPorInsumo[i] = (this.getStocks().get(h).getInsumo().getPeso())*(this.cantidadFaltante(this.getStocks().get(h).getInsumo()));
				i++;
			}
		}												//termina obtención de datos necesarios

		   for (int n=0; n<cantElementos; n++) {
		       for (int w=0; w<capacidadC; w++) {
		           int costoSinTomarlo = n < 1 ? n : tablaOpciones[n-1][w]; 
		           int costoTomandolo = Integer.MIN_VALUE;
		               if (pesoPorInsumo[n] <= w) {															
		            	   if(n==0)
		            		   costoTomandolo = (int) (costoPorInsumo[n] + tablaOpciones[n][(int) (w - pesoPorInsumo[n])]);
		            	   else 
		            		   costoTomandolo = (int) (costoPorInsumo[n] + tablaOpciones[n-1][(int) (w - pesoPorInsumo[n])]); 							
		               }
		               
		           tablaOpciones[n][w] = Math.max(costoSinTomarlo, costoTomandolo);
		           solucion[n][w] = (costoTomandolo > costoSinTomarlo);
		       }
		   }
		   
		   ArrayList<Insumo> faltantes = new ArrayList<Insumo>();
		   for(int y=0; y<this.getStocks().size(); y++) {
			   if(this.necesitaInsumo(this.getStocks().get(y).getInsumo()))
				   faltantes.add(this.getStocks().get(y).getInsumo());
		   }
		   ArrayList<Insumo> insumosResultantes = new ArrayList<Insumo>();					
		   for (int n = cantElementos-1, w = capacidadC-1; n >= 0; n--) {
		       if (solucion[n][w]) {
		           insumosResultantes.add(faltantes.get(n));
		           w = (int) (w - pesoPorInsumo[n]);
		       } 
		   }
		   return insumosResultantes;
	}

	public void actualizarCantidadCaminos() {	
		this.cantidadCaminos = this.cantidadCaminos + 1;
	}
	
	
	public int getCantidadCaminos() {			
		return this.cantidadCaminos;
	}
	
	public String getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(String id) {
		this.idPlanta = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public boolean encontrarStock(int idStock) {
		boolean pertenece = false;
		for(int i=0; i<this.stocks.size(); i++) {
			if(this.getStocks().get(i).getIdStock() == idStock)
				pertenece = true;
		}
		return pertenece;
	}
	
	public double costoTotal() {
		return stocks.stream().mapToDouble((s) -> s.getInsumo().getCosto()*s.getCantidad()).sum();
	}
	
	public List<Insumo> stockEntre(Integer s1, Integer s2){
		return stocks.stream().filter(s->s.getCantidad()<s2).filter(s->s.getCantidad()>s1).map(s->s.getInsumo()).collect(Collectors.toList());
	}
	
	public boolean necesitaInsumo(Insumo i) {
		for(Stock s : stocks) {
			if(s.getInsumo().equals(i)) {
				if(s.getPuntoPedido()-s.getCantidad()>0)		
					return true;
			}
		}
		return false;
	}
	
	public int cantidadFaltante(Insumo i) {
		int cantidad = 0;
		for(Stock s : stocks) {
			if(s.getInsumo()==i) {
				cantidad = s.getPuntoPedido()-s.getCantidad();		
			}
		}
		return cantidad;
	}

	public void agregarStock(Stock nuevoStock) {
		this.stocks.add(nuevoStock);
	}	
}
