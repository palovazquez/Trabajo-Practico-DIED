package isi.died.tp.estructuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.parte5.ConsultarCaminos;
import isi.died.tp.parte5.SeleccionarPlantas;

public class GrafoConsultarCaminos extends JPanel {

	private List<Vertice<Planta>> vertices = new ArrayList<Vertice<Planta>>();
	private List<Planta> plantas = new ArrayList<Planta>();
	private ArrayList<Arista> aristas = new ArrayList<Arista>();
	
	private ArrayList<Camino> caminos = new ArrayList<Camino>();
	
	private Grafo grafo = new Grafo();
	private int cantidadVertices = 0;
	private int cantidadAristas = 0;
	
	
	public GrafoConsultarCaminos() {
		this.setVisible(true);
		
		if(caminos.size()==0) {
			System.out.print("caminos vacios " ) ;
		}else {
		for(int i=0; i < caminos.size(); i ++)
		System.out.print( "id camino en caminos: " + caminos.get(i).getIdCamino());}
		
		if(plantas.size()==0) {
			System.out.print("plantas vacios " ) ;
		}else {
		for(int i=0; i < plantas.size(); i ++)
		System.out.print( "id planta en plantas: " + plantas.get(i).getIdPlanta());}
		
		
		
		if(vertices.size()==0) {
		System.out.println(" vertices vacios ");
		}
		else {
		for(int i=0; i < vertices.size(); i ++) {
			System.out.print( "id camino en vertices: " + vertices.get(i).getId());}}
		
		for(int h = 0; h < SeleccionarPlantas.conjuntoVertices.size(); h++) {
			
			for(int f = 0 ; f < SeleccionarPlantas.conjuntoVertices.get(h).size()  ; f++ ) {
			
				for( int i = 0 ; i < CrearPlanta.contenedorPlantas.size(); i++) {
						
					if(SeleccionarPlantas.conjuntoVertices.get(h).get(f).getId().equals(CrearPlanta.contenedorPlantas.get(i).getIdPlanta())){
						Boolean esta = false;
						for (int d = 0; d < plantas.size(); d ++) {
							if(plantas.get(d).getIdPlanta().equals(CrearPlanta.contenedorPlantas.get(i).getIdPlanta())) {
								esta = true;
						    }
						}
						if (!esta) {
						plantas.add(CrearPlanta.contenedorPlantas.get(i));
						System.out.println("Planta agregada: " + CrearPlanta.contenedorPlantas.get(i).getIdPlanta());
						}
					}
						
				}
				
			}
		}
		
		for(int k = 0; k < SeleccionarPlantas.conjuntoVertices.size(); k++) { 
			
			for(int j = 0; j < SeleccionarPlantas.conjuntoVertices.get(k).size()-1 ; j++) {
				
				for (int i=0; i<CrearCamino.contenedorCaminos.size(); i++) {
			
					if(((CrearCamino.contenedorCaminos.get(i).getPlanta1().getIdPlanta()).equals(SeleccionarPlantas.conjuntoVertices.get(k).get(j).getId())) && 
							((CrearCamino.contenedorCaminos.get(i).getPlanta2().getIdPlanta()).equals(SeleccionarPlantas.conjuntoVertices.get(k).get(j+1).getId()))) {
							
							
						Boolean esta = false;
						for (int d = 0; d < caminos.size(); d ++) {
							if(caminos.get(d).getIdCamino().equals(CrearCamino.contenedorCaminos.get(i).getIdCamino())) {
								esta = true;
						   }
						}
												
						if(!esta) {
						caminos.add(CrearCamino.contenedorCaminos.get(i));
						
						System.out.println("Camino agregado: " + CrearCamino.contenedorCaminos.get(i).getIdCamino());
						}
					
					}
				} 
			}
		
		}
	}		
		
	
		public List<Vertice<Planta>> getVertices() {
		return vertices;
	}


	public void setVertices(List<Vertice<Planta>> vertices) {
		this.vertices = vertices;
	}


	public ArrayList<Camino> getCaminos() {
		return caminos;
	}


	public void setCaminos(ArrayList<Camino> caminos) {
		this.caminos = caminos;
	}


		public void dibujarVertices(Graphics2D g2d) {
			for (Vertice v : vertices) {
	        	g2d.drawString(v.getNombre(), v.getX()+25,v.getY()+25);
		        g2d.setStroke(new BasicStroke(5.f));
		        g2d.setPaint(v.getColor());
		        g2d.drawOval(v.getX(), v.getY(), 20, 20);
		        g2d.fill(v.getNodo());
		    }
	    }
		
		public void dibujarAristas(Graphics2D g2d) {
	        for (Arista a : aristas) {
	            g2d.setPaint(Color.BLACK);
	            g2d.setStroke (a.getFormatoLinea());
	            g2d.draw(a.getLinea());
	            
	            g2d.setPaint(Color.BLACK);
	            Polygon flecha = new Polygon();  
	            flecha.addPoint(a.getFin().getX(), a.getFin().getY()+7);
	            flecha.addPoint(a.getFin().getX()+20, a.getFin().getY()+10);
	            flecha.addPoint(a.getFin().getX(), a.getFin().getY()+18);
	            g2d.fillPolygon(flecha);
	        }
	    }

		public void inicalizarVertices() {
			
			int y = 100;
			int x = 0;
			int i = 0;
			Color c = null;
			for(Planta p : plantas){
				i++;
				x += 30;
				if(i%2==0) {
					y = 100;
					c = Color.BLUE;
				}
				else {
					y = 200;
					c = Color.RED;
				}
				Vertice<Planta> v = new Vertice(p.getIdPlanta(), x, y, p.getNombre(), c);
				grafo.addNodo(v);
				vertices.add(v);
				cantidadVertices++;
			}
		}
		
		public void inicalizarAristas() {
			List<Camino> lista = caminos; 
			int y = 100;
	        int x = 0;
	        int m = 0;
	        Color color = null;
	        for(int c=0; c<lista.size(); c++) {
	        	m++;
	            x += 30;
	            if(m%2==0) {
	            	y = 100;
	                color = Color.BLUE;
	            }
	            else {
	            	y = 200;
	                color = Color.RED;
	            }
	            
	            Vertice<Planta> v1 = null;
	            Vertice<Planta> v2 = null;
	            
	            for(int v=0; v<vertices.size(); v++) {
	            	if(lista.get(c).getPlanta1().getIdPlanta().equals(vertices.get(v).getId()))
	            		v1 = vertices.get(v);
	            	if(lista.get(c).getPlanta2().getIdPlanta().equals(vertices.get(v).getId()))
	            		v2 = vertices.get(v);
	            }
	    
	            Arista<Arista> a = new Arista(v1, v2, "C"+cantidadAristas, cantidadAristas);
	            grafo.addArista(a);
	            aristas.add(a);
	            cantidadAristas++;
	       }
	    }
	
		private void dibujarFlecha(Graphics2D g2, Point tip, Point tail, Color color){
	        double phi;
	        int barb;
	        phi = Math.toRadians(40);
	        barb = 20;

	        g2.setPaint(color);
	        double dy = tip.y - tail.y;
	        double dx = tip.x - tail.x;
	        double theta = Math.atan2(dy, dx);
	        double x, y, rho = theta + phi;
	        for(int j=0; j<2; j++) {
	            x = tip.x - barb * Math.cos(rho);
	            y = tip.y - barb * Math.sin(rho);
	            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
	            rho = theta-phi;
	        }
	    }
	
	

}
