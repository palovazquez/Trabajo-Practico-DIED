package isi.died.tp.interfaces.crear;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.TipoPlanta;
import isi.died.tp.dominio.Unidad;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuInsumo;
import isi.died.tp.interfaces.MenuStock;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CrearCamino extends JFrame {

	private JPanel contentPane;
	private JLabel lblDistanciakm;
	private JLabel label_1;
	private JLabel lblPesoMximoAceptado;
	private JTextField texto_planta2;
	private JTextField texto_planta1;

	public static ArrayList<Camino> contenedorCaminos = new ArrayList<Camino>();
	private JTextField texto_duracion;
	private JTextField texto_distancia;
	private JTextField texto_pesoMax;
	private JTextField texto_idCamino;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCamino frame = new CrearCamino();
					frame.setTitle("Crear Camino");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CrearCamino() {
		setTitle("Crear Camino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del camino");
		lblIngreseLosDatos.setBounds(37, 22, 191, 17);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblPlanta = new JLabel("ID Planta 1:");
		lblPlanta.setBounds(37, 93, 75, 14);
		contentPane.add(lblPlanta);
		
		JLabel lblIdPlanta = new JLabel("ID Planta 2:");
		lblIdPlanta.setBounds(218, 93, 75, 14);
		contentPane.add(lblIdPlanta);
		
		lblDistanciakm = new JLabel("Distancia (Km):");
		lblDistanciakm.setBounds(37, 135, 115, 14);
		contentPane.add(lblDistanciakm);
		
		label_1 = new JLabel("Duración viaje (min):");
		label_1.setBounds(37, 171, 145, 14);
		contentPane.add(label_1);
		
		lblPesoMximoAceptado = new JLabel("Peso máximo aceptado (T):");
		lblPesoMximoAceptado.setBounds(37, 207, 169, 14);
		contentPane.add(lblPesoMximoAceptado);
		
		texto_planta2 = new JTextField();
		texto_planta2.setColumns(10);
		texto_planta2.setBounds(289, 89, 86, 22);
		contentPane.add(texto_planta2);
		
		texto_planta1 = new JTextField();
		texto_planta1.setColumns(10);
		texto_planta1.setBounds(110, 89, 86, 22);
		contentPane.add(texto_planta1);
		
		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = texto_idCamino.getText();
				Integer distancia = null;
				Integer duracion = null;
				Float pesoMax = null;
				Planta planta1 = new Planta();
				Planta planta2 = new Planta();
				if(!texto_distancia.getText().isEmpty())
					distancia = Integer.valueOf(texto_distancia.getText());
				if(!texto_duracion.getText().isEmpty())
					duracion = Integer.valueOf(texto_duracion.getText());
				if(!texto_pesoMax.getText().isEmpty())
					pesoMax = Float.valueOf(texto_pesoMax.getText());
				if(!texto_planta1.getText().isEmpty())
					planta1.setIdPlanta(String.valueOf(texto_planta1.getText()));
				if(!texto_planta2.getText().isEmpty())
					planta2.setIdPlanta(String.valueOf(texto_planta2.getText()));
				
				boolean existeCamino = false;
				for (int i=0; i<contenedorCaminos.size(); i++) {
					if(contenedorCaminos.get(i).getIdCamino().equals(texto_idCamino.getText())) {
						existeCamino = true;
						JOptionPane.showMessageDialog(null, "Ya existen caminos con ese ID");
						CrearCamino camino = new CrearCamino();
						camino.setVisible(true);
						dispose();
					}
					else if(contenedorCaminos.get(i).getPlanta1().getIdPlanta().equals(planta1.getIdPlanta()) &&
							contenedorCaminos.get(i).getPlanta2().getIdPlanta().equals(planta2.getIdPlanta())) {
						existeCamino = true;
						JOptionPane.showMessageDialog(null, "Ya existe un camino desde la planta "+planta1.getNombre()+" a la planta "+planta2.getNombre());
						CrearCamino camino = new CrearCamino();
						camino.setVisible(true);
						dispose();
					}
				}
				
				if(!existeCamino) {
					boolean existePlanta1 = false;
					boolean existePlanta2 = false;
					int posicionPlanta2=0;
					
					if(id==null || distancia==null || duracion==null || pesoMax==null || texto_planta1.getText().isEmpty() || texto_planta2.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Complete todos los campos");
						CrearCamino crearCamino = new CrearCamino();
						crearCamino.setVisible(true);
						dispose();
					}
					else if(texto_planta1.getText().equals(texto_planta2.getText())) {
						JOptionPane.showMessageDialog(null, "La planta destino no puede ser la misma que la de origen.");
					}
					else {
						for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
			               if(texto_planta1.getText().equals(CrearPlanta.contenedorPlantas.get(i).getIdPlanta())) {
		                        existePlanta1 = true;
			               		planta1 = CrearPlanta.contenedorPlantas.get(i);
			               }
			               if(texto_planta2.getText().equals(CrearPlanta.contenedorPlantas.get(i).getIdPlanta())) {
		                        existePlanta2 = true;
		                   		posicionPlanta2=i;
		                   		planta2 = CrearPlanta.contenedorPlantas.get(i);
		                   }
						}
						
						if(!existePlanta1 && !existePlanta2)
							JOptionPane.showMessageDialog(null, "El ID de Planta1 y el de Planta2 no corresponden a una planta existente.");
						else if(!existePlanta1)
							JOptionPane.showMessageDialog(null, "El ID de Planta1 no corresponde a ninguna planta existente.");
						else if(!existePlanta2)
							JOptionPane.showMessageDialog(null, "El ID de Planta2 no corresponde a ninguna planta existente.");
						else if(planta1.getTipoPlanta().equals(TipoPlanta.FINAL))
							JOptionPane.showMessageDialog(null, "La Planta Origen no puede ser una Planta de tipo Acopio Final.");
						else if(planta2.getTipoPlanta().equals(TipoPlanta.INICIAL))
							JOptionPane.showMessageDialog(null, "La Planta Destino no puede ser una Planta de tipo Acopio Inicial.");
						else {		
							Camino camino = new Camino(id, distancia, duracion, pesoMax, planta1, planta2);
							contenedorCaminos.add(camino);
						
							CrearPlanta.contenedorPlantas.get(posicionPlanta2).actualizarCantidadCaminos();  	 
	 
							JOptionPane.showMessageDialog(null, "Carga exitosa.");
							texto_distancia.setText(""); 
							texto_duracion.setText("");
							texto_pesoMax.setText("");
							texto_planta1.setText("");
							texto_planta2.setText("");
							texto_idCamino.setText("");
						}
						MenuCamino menuCamino = new MenuCamino();
						menuCamino.setVisible(true);
						dispose();
					}
			}}
		});
		boton_guardar.setBounds(185, 239, 89, 23);
		contentPane.add(boton_guardar);
		
		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamino camino = new MenuCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		boton_cancelar.setBounds(286, 239, 89, 23);
		contentPane.add(boton_cancelar);
		
		texto_duracion = new JTextField();
		texto_duracion.setBounds(200, 167, 116, 22);
		contentPane.add(texto_duracion);
		texto_duracion.setColumns(10);
		
		texto_distancia = new JTextField();
		texto_distancia.setBounds(200, 131, 116, 22);
		contentPane.add(texto_distancia);
		texto_distancia.setColumns(10);
		
		texto_pesoMax = new JTextField();
		texto_pesoMax.setBounds(200, 203, 116, 22);
		contentPane.add(texto_pesoMax);
		texto_pesoMax.setColumns(10);
		
		JLabel lblIdCamino = new JLabel("ID Camino:");
		lblIdCamino.setBounds(37, 55, 75, 16);
		contentPane.add(lblIdCamino);
		
		texto_idCamino = new JTextField();
		texto_idCamino.setBounds(110, 52, 116, 22);
		contentPane.add(texto_idCamino);
		texto_idCamino.setColumns(10);
	}
}
