package isi.died.tp.estructuras;

import java.util.ArrayList;
import java.util.List;

import isi.died.tp.dominio.Insumo;

public class ArbolBinarioBusqueda<E extends Comparable<E>> extends Arbol<E> {

	protected Arbol<E> izquierdo;
	protected Arbol<E> derecho;
	
	public ArbolBinarioBusqueda(){
		this.valor=null;
		this.izquierdo=new ArbolVacio<E>();
		this.derecho=new ArbolVacio<E>();
	}
	
	public ArbolBinarioBusqueda(E e){
		this.valor=e;
		this.izquierdo=new ArbolVacio<E>();
		this.derecho=new ArbolVacio<E>();
	}
	
	public ArbolBinarioBusqueda(E e,Arbol<E> i,Arbol<E> d){
		this.valor=e;
		this.izquierdo=i;
		this.derecho=d;
	}
	
	@Override
	public ArrayList<E> preOrden() {
		ArrayList<E> lista = new ArrayList<E>();
		lista.add(this.valor);
		lista.addAll(this.izquierdo.preOrden());
		lista.addAll(this.derecho.preOrden());
		return lista;
	}
	@Override
	public ArrayList<E> inOrden() {
		ArrayList<E> lista = new ArrayList<E>();
		lista.addAll(this.izquierdo.preOrden());
		lista.add(this.valor);
		lista.addAll(this.derecho.preOrden());
		return lista;
	}
	@Override
	public ArrayList<E> posOrden() {
		ArrayList<E> lista = new ArrayList<E>();
		lista.addAll(this.izquierdo.preOrden());
		lista.addAll(this.derecho.preOrden());
		lista.add(this.valor);
		return lista;

	}
	@Override
	public boolean esVacio() {
		return false;
	}
        
	@Override
	public E valor() {
		return this.valor;
	}
	
	@Override
	public Arbol<E> izquierdo() {
		return this.izquierdo;
	}
	
	@Override
	public Arbol<E> derecho() {
		return this.derecho;
	}


	@Override
	public void agregar(E a) {
		if(this.valor.compareTo(a)<1) {
			if (this.derecho.esVacio())
				this.derecho = new ArbolBinarioBusqueda<E>(a);
			else this.derecho.agregar(a);
		}else {
			if (this.izquierdo.esVacio()) this.izquierdo= new ArbolBinarioBusqueda<E>(a);
			else this.izquierdo.agregar(a);
		}
	}
	
	@Override
	public boolean equals(Arbol<E> unArbol) {
		return this.valor.equals(unArbol.valor()) && this.izquierdo.equals(unArbol.izquierdo()) && this.derecho.equals(unArbol.derecho());
	}

	@Override
	public boolean contiene(E unValor) {
		return this.valor.equals(unValor) ||
				this.izquierdo.contiene(unValor) ||
				this.derecho.contiene(unValor);
	}
	
    
 
	@Override
	public int profundidad() {
		return 1 + Math.max(izquierdo.profundidad(), derecho.profundidad());
	}

	@Override
	public int cuentaNodosDeNivel(int nivel) {
		if (this.profundidad() == nivel)
			return 1;
		else
			return this.izquierdo.cuentaNodosDeNivel(nivel) + this.derecho.cuentaNodosDeNivel(nivel);
	}

	@Override
	public boolean esCompleto() {
		int m = izquierdo.profundidad()-derecho.profundidad();
			if (izquierdo.esVacio() && derecho.esVacio())
				return true;
			else if (m == 1 && izquierdo.esCompleto() && derecho.esLleno())
				return true;
			else if (m == 0 && izquierdo.esLleno() && derecho.esCompleto())
				return true;
			else
				return false;
		}

	@Override
	public boolean esLleno() {
		for(int i=0; i<=this.profundidad(); i++) {
			if(this.cuentaNodosDeNivel(i)!= Math.pow(2, this.profundidad()))
				return false;
		}
		return true;
	}
	
	
	///El original 
	
	/*public ArrayList<String> rango(int i1, int i2) {
        ArrayList<String> insumos = new ArrayList<String>();
        Insumo insumo1 = new Insumo(i1);
        Insumo insumo2 = new Insumo(i2);
        return rangoAux(insumo1,insumo2,insumos);
    }

    public ArrayList<String> rangoAux(Insumo insumo1, Insumo insumo2, ArrayList<String> insumos) {
            for(E e: this.inOrden()) {
                if((((Insumo)e).getStock())>=insumo1.getStock() && (((Insumo)e).getStock())<=insumo2.getStock()) 
                    insumos.add(((Insumo)e).toString());
            }
            return insumos;
    }*/
	
	 public ArrayList<Insumo> rangoCostos(float c1, float c2) {
	 
		ArrayList<Insumo> listaResultado = new ArrayList<Insumo>();
		Insumo I1 = new Insumo(c1);
		Insumo I2 = new Insumo(c2);
	
		return rangoAuxCostos(I1,I2,listaResultado);
	}
	
	public ArrayList<Insumo> rangoAuxCostos(Insumo I1, Insumo I2, ArrayList<Insumo> listaResultado) {
		for(E e: this.inOrden()) {
				if(((Insumo)e).getCosto()>=I1.getCosto() && ((Insumo)e).getCosto()<=I2.getCosto()) 
					 listaResultado.add((Insumo)e);
			}
			return listaResultado;
	}
	
	
	public ArrayList<Insumo> rangoStocks(int s1, int s2) {
		ArrayList<Insumo> listaResultado = new ArrayList<Insumo>();
		return rangoAuxStocks(s1,s2,listaResultado);
	}

	public ArrayList<Insumo> rangoAuxStocks(int s1, int s2, ArrayList<Insumo> listaResultado) {
		for(E e: this.inOrden()) {
			if(((Insumo)e).getStockTotal()>=s1 && ((Insumo)e).getStockTotal()<=s2)
				listaResultado.add((Insumo)e);
		}
        return listaResultado;
   }
}
	

	
			