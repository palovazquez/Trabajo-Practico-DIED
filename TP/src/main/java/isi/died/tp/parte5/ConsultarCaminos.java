package isi.died.tp.parte5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.TipoPlanta;
import isi.died.tp.estructuras.GrafoCaminos;
import isi.died.tp.estructuras.GrafoConsultarCaminos;
import isi.died.tp.estructuras.Vertice;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.buscar.BuscarCamino;
import isi.died.tp.interfaces.buscar.CaminosBuscados;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ConsultarCaminos extends JFrame {

	private JPanel contentPane;
	private JTable tableC;
	private DefaultTableModel modelC;
	
	private GrafoConsultarCaminos grafoConsultarCaminos = new GrafoConsultarCaminos();
	private JTable table;
	private  static Planta planta1 = new Planta();
	private static  Planta planta2= new Planta();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ConsultarCaminos frame = new ConsultarCaminos(planta1,planta2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ConsultarCaminos(Planta planta1, Planta planta2) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Consultar camino");
		setBounds(100, 100, 689, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInicio = new JLabel("Planta Origen: " + planta1.getIdPlanta() );
		lblInicio.setBounds(24, 21, 137, 14);
		contentPane.add(lblInicio);
		
		JLabel lblDestino = new JLabel("Planta Destino: " + planta2.getIdPlanta());
		lblDestino.setBounds(171, 21, 144, 14);
		contentPane.add(lblDestino);
		
		JPanel grafoConsultarCaminos = new JPanel();
		grafoConsultarCaminos.setBounds(24, 56, 299, 240);
		contentPane.add(grafoConsultarCaminos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 21, 313, 275);
		contentPane.add(scrollPane);
		
		tableC = new JTable();
		scrollPane.setViewportView(table);
		String dataa[][] = {};
		String coll[] = {"Camino", "Duración Total", "Distancia Total", "Cantidad máxima peso"};
		modelC = new DefaultTableModel(dataa,coll);
		tableC.setModel(modelC);
		scrollPane.setViewportView(tableC);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamino caminos = new MenuCamino();
				caminos.setVisible(true);
				dispose();
			}
		});
		
		btnAtrs.setBounds(24, 307, 89, 23);
		contentPane.add(btnAtrs);
		
		
		
		
		mostrarDatos();
	}
	


	public void mostrarDatos() {
	List<Camino> caminosX = new ArrayList<Camino>();
	for(int h = 0; h < SeleccionarPlantas.conjuntoVertices.size(); h++) {
			Integer duracion = 0;
			Integer distancia = 0;
			Float peso = 0.0f;
			String nombre = "";
			for(int f=0 ; f < SeleccionarPlantas.conjuntoVertices.get(h).size()-1; f++ ) {
				Camino c = new Camino();
				for(int i = 0; i < grafoConsultarCaminos.getCaminos().size() ;i ++) {
						if(grafoConsultarCaminos.getCaminos().get(i).getPlanta1().getIdPlanta().equals(SeleccionarPlantas.conjuntoVertices.get(h).get(f).getId())
							&& grafoConsultarCaminos.getCaminos().get(i).getPlanta2().getIdPlanta().equals(SeleccionarPlantas.conjuntoVertices.get(h).get(f+1).getId()) ) {
							
							
							
							c = grafoConsultarCaminos.getCaminos().get(i);
							caminosX.add(c);
							duracion+= c.getDuracion() ;
							distancia+= c.getDistancia() ;
							peso+= c.getPesoMax();
							nombre = nombre + " " + SeleccionarPlantas.conjuntoVertices.get(h).get(f).getId() + " "
									+ SeleccionarPlantas.conjuntoVertices.get(h).get(f+1).getId() ;
							
						
					
						}
						
					
				}

		
			}
			
			modelC.insertRow(0, new Object[]{});
			modelC.setValueAt( nombre  , 0,0);
			modelC.setValueAt(duracion, 0, 1);
			modelC.setValueAt(distancia, 0, 2);
			modelC.setValueAt(peso, 0, 3);
			
		}
	}
	
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        grafoConsultarCaminos.inicalizarVertices();
        grafoConsultarCaminos.inicalizarAristas();
        grafoConsultarCaminos.dibujarVertices(g2d);
        grafoConsultarCaminos.dibujarAristas(g2d);
	}	
}