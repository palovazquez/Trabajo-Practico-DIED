package isi.died.tp.interfaces.editar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.buscar.CaminosBuscados;
import isi.died.tp.interfaces.buscar.PlantasBuscadas;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCamino extends JFrame {

	private JPanel contentPane;
	private JLabel lblDistanciakm;
	private JLabel label_1;
	private JLabel lblPesoMximoAceptado;
	private JTextField texto_planta2;
	private JTextField texto_planta1;
	private JTextField texto_distancia;
	private JTextField texto_duracion;
	private JTextField texto_pesoMax;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCamino frame = new EditarCamino();
					frame.setTitle("Editar Camino");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EditarCamino() {
		setTitle("Editar Camino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del camino");
		lblIngreseLosDatos.setBounds(37, 11, 191, 17);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblPlanta = new JLabel("ID Planta 1:");
		lblPlanta.setBounds(37, 44, 74, 14);
		contentPane.add(lblPlanta);
		
		JLabel lblIdPlanta = new JLabel("ID Planta 2:");
		lblIdPlanta.setBounds(221, 44, 74, 14);
		contentPane.add(lblIdPlanta);
		
		lblDistanciakm = new JLabel("Distancia (Km):");
		lblDistanciakm.setBounds(37, 86, 115, 14);
		contentPane.add(lblDistanciakm);
		
		label_1 = new JLabel("Duración viaje (min):");
		label_1.setBounds(37, 123, 145, 14);
		contentPane.add(label_1);
		
		lblPesoMximoAceptado = new JLabel("Peso m\u00E1ximo aceptado (T):");
		lblPesoMximoAceptado.setBounds(37, 162, 169, 14);
		contentPane.add(lblPesoMximoAceptado);
		
		texto_planta2 = new JTextField();
		texto_planta2.setColumns(10);
		texto_planta2.setBounds(296, 41, 86, 20);
		contentPane.add(texto_planta2);
		
		texto_planta1 = new JTextField();
		texto_planta1.setColumns(10);
		texto_planta1.setBounds(111, 41, 86, 20);
		contentPane.add(texto_planta1);
		
		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<CrearCamino.contenedorCaminos.size(); i++) {
					Camino c = CrearCamino.contenedorCaminos.get(i);
					if(CaminosBuscados.idCaminoSeleccionado.equals(c.getIdCamino())) {
						if(texto_planta1.getText().equals(texto_planta2.getText()) && !texto_planta1.getText().isEmpty()||
						   texto_planta1.getText().equals(c.getPlanta2().getIdPlanta()) && texto_planta2.getText().isEmpty() ||
						   texto_planta2.getText().equals(c.getPlanta1().getIdPlanta()) && texto_planta1.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "La planta destino no puede ser la misma que la de origen.");
							EditarCamino editarCamino = new EditarCamino();
							editarCamino.setVisible(true);
							dispose();
						}
						else {
							for(int j=0; j<CrearPlanta.contenedorPlantas.size(); j++) {
								Planta p = CrearPlanta.contenedorPlantas.get(j);
								if(!texto_planta1.getText().isEmpty() && p.getIdPlanta().equals(texto_planta1.getText()))
									c.setPlanta1(p);
								if(!texto_planta2.getText().isEmpty() && p.getIdPlanta().equals(texto_planta2.getText()))
									c.setPlanta2(p);
							}
							if(!texto_duracion.getText().isEmpty())
								c.setDuracion(Integer.valueOf(texto_duracion.getText()));
							if(!texto_distancia.getText().isEmpty())
								c.setDistancia(Integer.valueOf(texto_distancia.getText()));
							if(!texto_pesoMax.getText().isEmpty())
								c.setPesoMax(Float.valueOf(texto_pesoMax.getText()));
							JOptionPane.showMessageDialog(null, "Los cambios han sido guardados.");
							MenuCamino menuCamino = new MenuCamino();
							menuCamino.setVisible(true);
							dispose();
						}
					}
				}
			}
		});
		boton_guardar.setBounds(192, 199, 89, 23);
		contentPane.add(boton_guardar);
		
		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamino camino = new MenuCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		boton_cancelar.setBounds(293, 199, 89, 23);
		contentPane.add(boton_cancelar);
		
		texto_distancia = new JTextField();
		texto_distancia.setBounds(206, 82, 116, 22);
		contentPane.add(texto_distancia);
		texto_distancia.setColumns(10);
		
		texto_duracion = new JTextField();
		texto_duracion.setBounds(206, 119, 116, 22);
		contentPane.add(texto_duracion);
		texto_duracion.setColumns(10);
		
		texto_pesoMax = new JTextField();
		texto_pesoMax.setBounds(206, 158, 116, 22);
		contentPane.add(texto_pesoMax);
		texto_pesoMax.setColumns(10);
	}
}

