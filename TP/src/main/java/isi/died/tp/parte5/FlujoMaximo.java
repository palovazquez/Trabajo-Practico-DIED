package isi.died.tp.parte5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Planta;
import isi.died.tp.estructuras.Arista;
import isi.died.tp.estructuras.Grafo;
import isi.died.tp.estructuras.GrafoCaminos;
import isi.died.tp.estructuras.Vertice;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class FlujoMaximo extends JFrame {
	private JPanel contentPane;
	private Float rdo; 
	
	Float flujoMax = 0.0f;
	public static Planta acopioI = CrearPlanta.contenedorPlantas.get(0);
	public static Planta acopioF = CrearPlanta.contenedorPlantas.get(1);
	public static Vertice<Planta> acopioInicial = new Vertice<Planta>();	
	public static Vertice<Planta> acopioFinal = new Vertice<Planta>();
	public static List<List<Vertice<Planta>>> nodosCaminos = new ArrayList<List<Vertice<Planta>>>();
	public static List<Camino> caminosAux = new ArrayList<Camino>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlujoMaximo frame = new FlujoMaximo();
					frame.setTitle("FLUJO MÁXIMO");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}}});}

	public FlujoMaximo() {
		setTitle("FLUJO MÁXIMO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);					  
		contentPane.setLayout(null);

		acopioInicial = (Vertice<Planta>) GrafoCaminos.grafo.getVertices().get(0);
		acopioFinal = (Vertice<Planta>) GrafoCaminos.grafo.getVertices().get(1);
		nodosCaminos = GrafoCaminos.grafo.caminos(acopioInicial, acopioFinal);
		
		Grafo grafoAux = new Grafo();
		grafoAux.setVertices(GrafoCaminos.grafo.vertices);	
		grafoAux.setAristas(GrafoCaminos.grafo.getAristas()); 
		
		for(int k=0; k<grafoAux.getAristas().size(); k++) {			
			for(int m=0; m<CrearCamino.contenedorCaminos.size(); m++) {			
				if(((Arista<Camino>) grafoAux.getAristas().get(k)).getNombre().equals(CrearCamino.contenedorCaminos.get(m).getIdCamino())) {
					caminosAux.add(CrearCamino.contenedorCaminos.get(m));
				}
			}
		}

		for(int i=0; i<nodosCaminos.size(); i++) { //por cada cjto de nodos q forman 1 posible camino
			List<Arista<Camino>> ramasDelCamino = new ArrayList<Arista<Camino>>();	//crea una lista de las aristas q lo componen
			ramasDelCamino.clear();
			List<Camino> caminosDelCamino = new ArrayList<Camino>();
			caminosDelCamino.clear();
			Float min = 0.0f;
			for(int j=0; j<nodosCaminos.get(i).size()-1; j++) { //x cada par de nodos encuentra la arista q corresponde y la mete a la lista ramas
				ramasDelCamino.add(grafoAux.buscarArista(nodosCaminos.get(i).get(j), nodosCaminos.get(i).get(j+1)));
			}
			for(int k=0; k<ramasDelCamino.size(); k++) {			//x cada rama del camino le encuentra el camino q corresponde
				for(int m=0; m<caminosAux.size(); m++) {			//y lo agrega a caminos
					if(ramasDelCamino.get(k).getNombre().equals(caminosAux.get(m).getIdCamino())) {
						caminosDelCamino.add(caminosAux.get(m));
					}
				}
			}
			for(int l=0; l<caminosDelCamino.size(); l++) {	//busca para cada camino su peso, y lo compara con el del q sigue eligiendo min
				if(l==0)
					min = caminosDelCamino.get(l).getPesoMax();
				else if(caminosDelCamino.get(l).getPesoMax()<min) {
					min = caminosDelCamino.get(l).getPesoMax();
				}
			}
			
			for(int y=0; y<caminosDelCamino.size(); y++) {			
				for(int z=0; z<caminosAux.size(); z++) {	
					if(caminosDelCamino.get(y).getIdCamino().equals(caminosAux.get(z).getIdCamino())) {
						if((caminosAux.get(y).getPesoMax()-min)<0)
							caminosAux.get(y).setPesoMax(0.0f);
						else
							caminosAux.get(y).setPesoMax(caminosAux.get(y).getPesoMax()-min);						
					}
				}
			}
			flujoMax += min;
		}	
		
		JLabel lblElFlujoMximo = new JLabel("El flujo m\u00E1ximo que se puede enviar desde el");
		lblElFlujoMximo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblElFlujoMximo.setBounds(10, 28, 281, 14);
		contentPane.add(lblElFlujoMximo);
		JLabel lblNewLabel = new JLabel("acopio inicial al acopio final es: "+ flujoMax*1000 + " kg.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 44, 281, 14);
		contentPane.add(lblNewLabel);

		JButton boton_Atras = new JButton("Atr\u00E1s");
		boton_Atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					MenuCamino menuCamino = new MenuCamino();
					menuCamino.setVisible(true);
					dispose();
			}
		});
		boton_Atras.setBounds(10, 87, 89, 23);
		contentPane.add(boton_Atras);

	}
}
