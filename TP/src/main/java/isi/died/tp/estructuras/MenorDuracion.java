package isi.died.tp.estructuras;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.TipoPlanta;
import isi.died.tp.interfaces.buscar.BuscarMejorCamino;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenorDuracion extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;

	private GrafoCaminos grafoCaminos = new GrafoCaminos();
	private static List<Vertice<Planta>> menorCamino = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenorDistancia frame = new MenorDistancia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenorDuracion() {
		setTitle("Camino con menor duracion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarMejorCamino camino = new BuscarMejorCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		btnAtrs.setBounds(594, 211, 97, 25);
		contentPane.add(btnAtrs);
		
		//Guarda en variables cuales son las plantas de acopio inicial y final
		Planta plantaAI = null;
		Planta plantaAF = null;
		for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
			if(CrearPlanta.contenedorPlantas.get(i).getTipoPlanta().equals(TipoPlanta.INICIAL))
				plantaAI = CrearPlanta.contenedorPlantas.get(i);
			if(CrearPlanta.contenedorPlantas.get(i).getTipoPlanta().equals(TipoPlanta.FINAL))
				plantaAF = CrearPlanta.contenedorPlantas.get(i);
		}
		
		//Guardo en un arreglo que plantas necesitaran el insumo seleccionado
		ArrayList<Vertice<Planta>> plantasSinInsumo = new ArrayList<>();
		
		for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
			Planta p = CrearPlanta.contenedorPlantas.get(i);
			if(p.necesitaInsumo(BuscarMejorCamino.insumoSeleccionado)) {
				for(int v=0; v<GrafoCaminos.grafo.getVertices().size(); v++) {
					if(GrafoCaminos.grafo.getVertices().get(v).getId().equals(p.getIdPlanta())) {
						plantasSinInsumo.add(GrafoCaminos.grafo.getVertices().get(v));
					}
				}
			}
		}
		
		//busca los vertices de las plantas
		Vertice<Planta> vi =  null;
		Vertice<Planta> vf =  null;
		for(int i=0; i<GrafoCaminos.getVertices().size(); i++) {
			if(GrafoCaminos.getVertices().get(i).getId().equals(plantaAI.getIdPlanta()))
				vi = GrafoCaminos.getVertices().get(i);
			if(GrafoCaminos.getVertices().get(i).getId().equals(plantaAF.getIdPlanta()))
				vf = GrafoCaminos.getVertices().get(i);
		}
		
		//Lista con los caminos desde el acopio inicial al final
		List<List<Vertice<Planta>>> caminos = GrafoCaminos.grafo.caminos(vi,vf);
		
		//Guarda los caminos que contengan a TODAS las plantas que necesiten el insumo
		List<List<Vertice<Planta>>> caminosConInsumos = new ArrayList<List<Vertice<Planta>>>();
		
		for(int i=0; i<caminos.size(); i++) {
			int tiene = 0;
			for(int j=0; j<plantasSinInsumo.size(); j++) {
				for(int c=0; c<caminos.get(i).size(); c++) {
					if(caminos.get(i).get(c).getId().equals(plantasSinInsumo.get(j).getId())) {
						tiene++;
					}
				}
			}
			if(tiene==plantasSinInsumo.size())
				caminosConInsumos.add(caminos.get(i));
		}
		
		//Almacenara el menor camino
		int sumaMinima = 10000000;
		//va iterando sobre todos los caminos que contienen todas las plantas sin insumo
		for(int i=0; i<caminosConInsumos.size(); i++) {
			int sumaDuracion = 0;
			//itera en cada planta del camino anterior
			for(int j=0; j<caminosConInsumos.get(i).size()-1; j++) {
				Vertice<Planta> vertice = caminosConInsumos.get(i).get(j);
				//itera sobre todos los caminos que existen para buscarlo con su arista
				for(int c=0; c<CrearCamino.contenedorCaminos.size(); c++) {
					Camino cam = CrearCamino.contenedorCaminos.get(c);
					//verifica que el nombre de la arista entre los dos vertices sea igual al id del camino
					if(GrafoCaminos.grafo.buscarArista(vertice,caminosConInsumos.get(i).get(j+1)).getNombre().equals(cam.getIdCamino())) {
						sumaDuracion += cam.getDuracion();
					}
				}			
			}
			if(sumaMinima>sumaDuracion) {
				sumaMinima = sumaDuracion;
				menorCamino = caminosConInsumos.get(i);
			}
		}
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grafoCaminos.inicalizarVertices();
        grafoCaminos.inicalizarAristas();
        grafoCaminos.dibujarVertices(g2d);
        grafoCaminos.dibujarAristasConMenorDistancia(g2d,menorCamino); 
	}
}