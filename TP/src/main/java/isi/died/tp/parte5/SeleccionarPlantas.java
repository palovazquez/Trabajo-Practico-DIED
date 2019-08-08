package isi.died.tp.parte5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.TipoPlanta;
import isi.died.tp.estructuras.GrafoCaminos;
import isi.died.tp.estructuras.Vertice;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeleccionarPlantas extends JFrame {

	private JPanel contentPane;
	private JTextField texto_origen;
	private JTextField texto_destino;
	public static List<List<Vertice<Planta>>> conjuntoVertices = new ArrayList<List<Vertice<Planta>>>();
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarPlantas frame = new SeleccionarPlantas();
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
	public SeleccionarPlantas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 202);
		setTitle("Seleccionar Plantas para Consultar Caminos"); ///(??????
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos");
		lblIngreseLosDatos.setBounds(23, 11, 121, 14);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblIdPlantaOrigen = new JLabel("ID Planta Origen:");
		lblIdPlantaOrigen.setBounds(39, 52, 105, 14);
		contentPane.add(lblIdPlantaOrigen);
		
		texto_origen = new JTextField();
		texto_origen.setBounds(166, 46, 86, 20);
		contentPane.add(texto_origen);
		texto_origen.setColumns(10);
		
		JLabel lblIdPlantaDestino = new JLabel("ID Planta Destino:");
		lblIdPlantaDestino.setBounds(39, 83, 105, 14);
		contentPane.add(lblIdPlantaDestino);
		
		texto_destino = new JTextField();
		texto_destino.setColumns(10);
		texto_destino.setBounds(166, 77, 86, 20);
		contentPane.add(texto_destino);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuCamino menuPlanta = new MenuCamino();
				menuPlanta.setVisible(true);
				dispose();
			}
		});
		
		btnCancelar.setBounds(199, 123, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean existePlanta1 = false;
				Boolean existePlanta2 = false;
				int posicionPlanta2=0;
				int posicionPlanta1=0;
				Planta planta1 = new Planta();
				Planta planta2 = new Planta();
				if(!texto_origen.getText().isEmpty())
					planta1.setIdPlanta(String.valueOf(texto_origen.getText()));
				if(!texto_destino.getText().isEmpty())
					planta2.setIdPlanta(String.valueOf(texto_destino.getText()));
			
				
				if(texto_origen.getText().isEmpty() && texto_destino.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Ingrese algun dato requerido.");
					SeleccionarPlantas camino = new SeleccionarPlantas();
					camino.setVisible(true);
					dispose();
				}
				else if(texto_origen.getText().equals(texto_destino.getText())) {
					JOptionPane.showMessageDialog(null, "La planta destino no puede ser la misma que la de origen.");
				}
				
				else {
					for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
		               if(texto_origen.getText().equals(CrearPlanta.contenedorPlantas.get(i).getIdPlanta())) {
	                        existePlanta1 = true;
	                        posicionPlanta1=i;
		               		planta1 = CrearPlanta.contenedorPlantas.get(i);
		               }
					}
					
					for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
		                   if(texto_destino.getText().equals(CrearPlanta.contenedorPlantas.get(i).getIdPlanta())) {
		                        existePlanta2 = true;
		                   		posicionPlanta2=i;
		                   		planta2 = CrearPlanta.contenedorPlantas.get(i);
		                   }
					}
					
					if(!existePlanta1 && !existePlanta2) {
						JOptionPane.showMessageDialog(null, "El ID de Planta1 y el de Planta2 no corresponden a una planta existente.");
						SeleccionarPlantas consultar = new SeleccionarPlantas();
						consultar.setVisible(true);
						dispose();	
					}else if(!existePlanta1) {
						JOptionPane.showMessageDialog(null, "El ID de Planta1 no corresponde a ninguna planta existente.");
						SeleccionarPlantas consultar = new SeleccionarPlantas();
						consultar.setVisible(true);
						dispose();	
				    }else if(!existePlanta2) {
						JOptionPane.showMessageDialog(null, "El ID de Planta2 no corresponde a ninguna planta existente.");
						SeleccionarPlantas consultar = new SeleccionarPlantas();
						consultar.setVisible(true);
						dispose();	
					}else if(planta1.getTipoPlanta().equals(TipoPlanta.FINAL)) {
						JOptionPane.showMessageDialog(null, "La Planta Origen no puede ser una Planta de tipo Acopio Final.");
						SeleccionarPlantas consultar = new SeleccionarPlantas();
						consultar.setVisible(true);
						dispose();	
					}else if(planta2.getTipoPlanta().equals(TipoPlanta.INICIAL)) {
						JOptionPane.showMessageDialog(null, "La Planta Destino no puede ser una Planta de tipo Acopio Inicial.");
						SeleccionarPlantas consultar = new SeleccionarPlantas();
						consultar.setVisible(true);
						dispose();	
					}else {
							
						System.out.println("Nombre p1 " + CrearPlanta.contenedorPlantas.get(posicionPlanta1).getNombre() + " Nombre p2 " + 
						CrearPlanta.contenedorPlantas.get(posicionPlanta2).getNombre() ) ;
					     
						Vertice<Planta > vv1 = new Vertice<Planta>();
						Vertice<Planta > vv2 = new Vertice<Planta>();
						
						for(int i = 0; i <GrafoCaminos.getVertices().size(); i++) {
							
							if (GrafoCaminos.getVertices().get(i).getId().equals(planta1.getIdPlanta())) {
								vv1 = GrafoCaminos.getVertices().get(i);
								
							}
							else if( GrafoCaminos.getVertices().get(i).getId().equals(planta2.getIdPlanta())) {
								vv2 =GrafoCaminos.getVertices().get(i);
								
								
							}
						}
						conjuntoVertices = GrafoCaminos.grafo.caminos(vv1, vv2);   
					     
					}
					
				}
				
				ConsultarCaminos consultar = new ConsultarCaminos(planta1, planta2 );
				consultar.setVisible(true);
				dispose();
				
			}
			
		});
		btnBuscar.setBounds(100, 123, 89, 23);
		contentPane.add(btnBuscar);

	}

}

