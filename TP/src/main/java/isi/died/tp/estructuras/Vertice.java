package isi.died.tp.estructuras;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import isi.died.tp.dominio.Planta;

public class Vertice<T> {

	private T valor;
	private int x, y;
	private String nombre;
	public static final int d=20;
	public String id;
	private Shape nodo;
	private Color color;
	
	public Vertice(){}
	
	public Vertice(T v){
		this.valor = v;
	}
	
	public Vertice(String id, int x, int y, String nombre) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.nodo = new Ellipse2D.Double(x, y, d, d);
	}
	
	public Vertice(String id, int x, int y, String nombre, Color color) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.nodo = new Ellipse2D.Double(x, y, d, d);
		this.color = color;
	}
	
	public void setValor(T v){
		this.valor = v;
	}
	
	public T getValor(){
		return this.valor;
	}
	
	public Paint getColor() {
        if(color==null)
        	color = Color.WHITE;
        return color;
    }

    public void setColor(Paint color) {
        this.color = (Color) color;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	/*@Override
	public String toString() {
		return valor.toString();
	}*/

	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nodo
	 */
	public Shape getNodo() {
		return nodo;
	}

	/**
	 * @param nodo the nodo to set
	 */
	public void setNodo(Shape nodo) {
		this.nodo = nodo;
	}
	
	public void update() {
    	this.nodo = new Ellipse2D.Double(x, y, d, d);
    }
	
	 public boolean pertenece(Point2D p){
	        return this.nodo.contains(p);
	    }
	
}
