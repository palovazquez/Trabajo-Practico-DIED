package isi.died.tp.estructuras;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Arista<T> {
	private Vertice<T> inicio;
	private Vertice<T> fin;
	private int id;
	private Number valor;
	private String nombre;
	private int xInicio, xFin, yInicio, yFin;
	private Stroke formatoLinea;
    private Shape linea = null;

	public Arista(){
		valor=1.0;
	}
	
	public Arista(T p1, T p2, String nombre, int id) {
		this.inicio = new Vertice(p1);
		this.fin = new Vertice(p2);
		this.xInicio = inicio.getX();
		this.xFin = fin.getX();
		this.yInicio = inicio.getY();
		this.yFin = fin.getY();
		this.nombre = nombre;
		this.id = id;
	}
	
	public Arista(Vertice<T> ini,Vertice<T> fin){
		this();
		this.inicio = ini;
		this.fin = fin;
		this.xInicio = inicio.getX();
		this.xFin = fin.getX();
		this.yInicio = inicio.getY();
		this.yFin = fin.getY();
	}

	public Arista(Vertice<T> ini, Vertice<T> fin, Number val){
		this(ini, fin);
		this.valor = val;
		this.xInicio = inicio.getX();
		this.xFin = fin.getX();
		this.yInicio = inicio.getY();
		this.yFin = fin.getY();
	}
	
	public Arista(Vertice<T> inicio, Vertice<T> fin, String nombre, int id) {
		this.xInicio = inicio.getX();
		this.xFin = fin.getX();
		this.yInicio = inicio.getY();
		this.yFin = fin.getY();
		this.inicio = inicio;
		this.fin = fin;
		this.nombre = nombre;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getxInicio() {
		return xInicio;
	}

	public void setxInicio(int xInicio) {
		this.xInicio = xInicio;
	}

	public int getxFin() {
		return xFin;
	}

	public void setxFin(int xFin) {
		this.xFin = xFin;
	}

	public int getyInicio() {
		return yInicio;
	}

	public void setyInicio(int yInicio) {
		this.yInicio = yInicio;
	}

	public int getyFin() {
		return yFin;
	}

	public void setyFin(int yFin) {
		this.yFin = yFin;
	}

	public Stroke getFormatoLinea() {
        if(this.formatoLinea==null)
        	this.formatoLinea = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);       
        return formatoLinea;
    }

    public void setFormatoLinea(Stroke formatoLinea) {
        this.formatoLinea = formatoLinea;
    }
    
    public Shape getLinea() {
        if(this.linea==null) 
        	this.linea = new Line2D.Double(xInicio+10, yInicio+10, xFin+10, yFin+10);
        return linea;
    }

    public void setLinea(Shape linea) {
        this.linea = linea;
    }
    
	public Vertice<T> getInicio() {
		return inicio;
	}
	
	public void setInicio(Vertice<T> inicio) {
		this.inicio = inicio;
	}
	
	public Vertice<T> getFin() {
		return fin;
	}
	
	public void setFin(Vertice<T> fin) {
		this.fin = fin;
	}

	public Number getValor() {
		return valor;
	}

	public void setValor(Number valor) {
		this.valor = valor;
	}
	
	
	@Override
	public String toString() {
		return "( "+this.inicio.getId()+" --> "+this.fin.getId()+" )";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Arista<?>) && ((Arista<?>)obj).getValor().equals(this.valor); 
	}
	
	  public boolean pertenece(Point2D p) {
	        return this.linea.contains(p);
	    }
}