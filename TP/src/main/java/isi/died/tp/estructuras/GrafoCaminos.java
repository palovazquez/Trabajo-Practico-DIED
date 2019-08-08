package isi.died.tp.estructuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.Stock;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.crear.AgregarStock;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearInsumo;
import isi.died.tp.interfaces.crear.CrearPlanta;


public class GrafoCaminos extends JPanel implements MouseMotionListener {

	private static List<Vertice<Planta>> vertices = new ArrayList<>();
	private List<Arista<Camino>> aristas = new ArrayList<>();
	private Vertice verticeSeleccionado = null;
	private Boolean arrastrando = false;
	private int xRepintado = 0;
    private int yRepintado = 0;
    private int cantidadVertices = 0;
    private int cantidadAristas = 0;
    private int iVertice;
    private Vertice verticeMover;
    
    public static Grafo<Planta> grafo = new Grafo<>();
    
	public GrafoCaminos() {
		this.addMouseMotionListener((MouseMotionListener) this);
		
        this.setVisible(true);
        
        /*addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount()==1 && !event.isConsumed()) {
                    event.consume();
                    Vertice v = clicEnUnNodo(event.getPoint());
                    if(v!=null) {
                    	verticeSeleccionado = v; 
                    	verticeSeleccionado.setColor(Color.CYAN);
                    	actualizarVertice(verticeSeleccionado, event.getPoint());
                    }
                }
            }

            public void mouseReleased(MouseEvent event) {
               if(verticeSeleccionado!=null) {
            	   verticeSeleccionado.setColor(Color.BLUE);
            	   actualizarVertice(verticeSeleccionado, event.getPoint());
               }
               verticeSeleccionado = null;
               arrastrando = false;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
            	System.out.println("verticemovido");
                if(verticeSeleccionado!=null)
                	actualizarVertice(verticeSeleccionado, event.getPoint());
            }
        });*/
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
	
	public void dibujarVerticesConInsumosFaltantes(Graphics2D g2d) {
		for(Vertice v : vertices) {
			g2d.drawString(v.getNombre(), v.getX()+25,v.getY()+25);
			g2d.setStroke(new BasicStroke(5.f));
			boolean pintada = false;
			for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
				Planta p = CrearPlanta.contenedorPlantas.get(i);
				if(p.getIdPlanta().equals(v.getId()) && p.necesitaInsumo(FaltaInsumo.insumoSeleccionado) && !p.getStocks().isEmpty()) {
					g2d.setColor(Color.ORANGE);
					g2d.drawOval(v.getX(), v.getY(), 20, 20);
					g2d.fillOval(v.getX(), v.getY(), 20, 20);
					pintada = true;
				}
			}
			if(!pintada){
				g2d.setPaint(v.getColor());
				g2d.drawOval(v.getX(), v.getY(), 20, 20);
				g2d.fillOval(v.getX(), v.getY(), 20, 20);
				g2d.fill(v.getNodo());
			}
		}
	}
	
	public void dibujarAristasConMenorDistancia(Graphics2D g2d, List<Vertice<Planta>> menorCamino) {
		for(Arista a : aristas) {
			g2d.drawString(a.getNombre(), (a.getxInicio()+a.getxFin())/2, (a.getyInicio()+a.getyFin())/2);
			g2d.setStroke(new BasicStroke(5.f));
			boolean pintada = false;
			
			for(int i=0; i<menorCamino.size()-1; i++) {
				if(a.getInicio().getId().equals(menorCamino.get(i).getId()) && a.getFin().getId().equals(menorCamino.get(i+1).getId())) {
					g2d.setColor(Color.YELLOW);
					g2d.draw(a.getLinea());
		            g2d.drawString(a.getNombre(), (a.getxInicio()+a.getxFin())/2, (a.getyInicio()+a.getyFin())/2);
		            g2d.setPaint(Color.YELLOW);
		            Polygon flecha = new Polygon();
		            flecha.addPoint(a.getFin().getX(), a.getFin().getY()+7);
		            flecha.addPoint(a.getFin().getX()+20, a.getFin().getY()+10);
		            flecha.addPoint(a.getFin().getX(), a.getFin().getY()+18);
		            g2d.fillPolygon(flecha);
					pintada = true;
				}
			}
			if(!pintada){
				g2d.setPaint(Color.BLACK);
				g2d.draw(a.getLinea());
	            g2d.drawString(a.getNombre(), (a.getxInicio()+a.getxFin())/2, (a.getyInicio()+a.getyFin())/2);
	            g2d.setPaint(Color.BLACK);
	            Polygon flecha = new Polygon();
	            flecha.addPoint(a.getFin().getX(), a.getFin().getY()+7);
	            flecha.addPoint(a.getFin().getX()+20, a.getFin().getY()+10);
	            flecha.addPoint(a.getFin().getX(), a.getFin().getY()+18);
	            g2d.fillPolygon(flecha);
			}
		}
	}
	
	public void dibujarAristas(Graphics2D g2d) {
        for (Arista a : aristas) {
            g2d.setPaint(Color.BLACK);
            g2d.setStroke (a.getFormatoLinea());
            g2d.draw(a.getLinea());
            g2d.drawString(a.getNombre(), (a.getxInicio()+a.getxFin())/2, (a.getyInicio()+a.getyFin())/2);
            g2d.setPaint(Color.BLACK);
            Polygon flecha = new Polygon();
            flecha.addPoint(a.getFin().getX(), a.getFin().getY()+7);
            flecha.addPoint(a.getFin().getX()+20, a.getFin().getY()+10);
            flecha.addPoint(a.getFin().getX(), a.getFin().getY()+18);
            g2d.fillPolygon(flecha);
        }
    }

	public void inicalizarVertices() {
		List<Planta> lista = CrearPlanta.contenedorPlantas;
		int y = 100;
		int x = 0;
		int i = 0;
		Color c = null;
		for(Planta p : lista){
			i++;
			x += 30;
			if(i%2==0) {
				y = 100;
				c = Color.BLUE;
			}
			else {
				y = 200;
				c = Color.BLUE;
			}
			Vertice v = new Vertice(p.getIdPlanta(), x, y, p.getNombre(), c);
			grafo.addNodo(v);
			vertices.add(v);
			cantidadVertices++;
		}
	}
	
	public void inicalizarAristas() {
		List<Camino> lista = CrearCamino.contenedorCaminos;
        Color color = null;
        
        for(int c=0; c<lista.size(); c++) {
        	Vertice v1 = null;
            Vertice v2 = null;    
            for(int v=0; v<vertices.size(); v++) {
            	if(lista.get(c).getPlanta1().getIdPlanta().equals(vertices.get(v).getId()))
            		v1 = vertices.get(v);
            	if(lista.get(c).getPlanta2().getIdPlanta().equals(vertices.get(v).getId()))
            		v2 = vertices.get(v);
            }
            Arista a = new Arista(v1, v2, lista.get(c).getIdCamino(), cantidadAristas);
            grafo.addArista(a);
            aristas.add(a);
            cantidadAristas++;
       }
    }
	
	private Vertice clicEnUnNodo(Point p) {
        for (Vertice v : this.vertices) {
        	if (v.pertenece(p)) {
                return v;
            }
        }
        return null;
    }
	
	private void actualizarVertice(Vertice v, Point puntoNuevo) {
        int OFFSET_X = v.getNombre().length()*20;
        int OFFSET_Y = 31;
        repaint(xRepintado-5, yRepintado-5, v.d+OFFSET_X, v.d+OFFSET_Y);
        xRepintado = puntoNuevo.x;
        yRepintado = puntoNuevo.y;
        v.setX(xRepintado);
        v.setY(yRepintado);
        v.update();
        repaint(xRepintado-5, yRepintado-5, v.d+OFFSET_X, v.d+OFFSET_Y);
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

    public void mousePressed(MouseEvent e) {
    	int iV = 0;
    		for (int i = 0; i < vertices.size(); i++) {
    			if(new Rectangle(vertices.get(i).getX()-Vertice.d/2, vertices.get(i).getY()-Vertice.d/2, Vertice.d, Vertice.d ).contains(e.getPoint())) {
    				verticeMover = vertices.get(i);
    				iVertice = iV;
    				break;
    			}
    		}
    		
    	}

        public void mouseReleased(MouseEvent e) {
    		verticeMover = null;
    		iVertice = -1;
    	}

        ///Falta tener la lista de aristas
    	@Override
    	public void mouseDragged(MouseEvent e) {
    	/*	if (verticeMover!=null) {
    			Vertice verticeAux = new Vertice(verticeMover.getId(),e.getX(),e.getY(),verticeMover.getNombre());
    			this.vertices.set(iVertice, verticeAux);
    			int iA = 0;
    			for (int i=0; i < aristas.size(); i++) {
    			//if(new Rectangle(aristas.get(i).get.getX()-Vertice.d/2, vertices.get(i).getY()-Vertice.d/2, Vertice.d, Vertice.d ).contains(e.getPoint()))
    		}
    		}*/
    		
    	}

    	@Override
    	public void mouseMoved(MouseEvent arg0) {
    		// TODO Auto-generated method stub
    		
    	}

		/**
		 * @return the vertices
		 */
		public static List<Vertice<Planta>> getVertices() {
			return vertices;
		}

		/**
		 * @param vertices the vertices to set
		 */
		public void setVertices(List<Vertice<Planta>> vertices) {
			this.vertices = vertices;
		}

		/**
		 * @return the aristas
		 */
		public List<Arista<Camino>> getAristas() {
			return this.aristas;
		}

		/**
		 * @param aristas the aristas to set
		 */
		public void setAristas(List<Arista<Camino>> aristas) {
			this.aristas = aristas;
		}

		
    	
}
