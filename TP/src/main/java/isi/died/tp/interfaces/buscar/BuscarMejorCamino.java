package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.TipoPlanta;
import isi.died.tp.estructuras.Grafo;
import isi.died.tp.estructuras.GrafoCaminos;
import isi.died.tp.estructuras.MenorDistancia;
import isi.died.tp.estructuras.MenorDuracion;
import isi.died.tp.estructuras.Vertice;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.crear.CrearInsumo;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class BuscarMejorCamino extends JFrame {

	private JPanel contentPane;
	public static Insumo insumoSeleccionado = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarMejorCamino frame = new BuscarMejorCamino();
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
	public BuscarMejorCamino() {
		getContentPane().setLayout(null);
		setTitle("Buscar mejor camino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseUnCriterio = new JLabel("Ingrese un criterio de búsqueda");
		lblIngreseUnCriterio.setBounds(28, 96, 203, 16);
		contentPane.add(lblIngreseUnCriterio);
		
		JRadioButton rdbtnMenorDistancia = new JRadioButton("Menor distancia");
		rdbtnMenorDistancia.setBounds(28, 121, 127, 25);
		contentPane.add(rdbtnMenorDistancia);
		
		JRadioButton rdbtnMenorDuracion = new JRadioButton("Menor duración");
		rdbtnMenorDuracion.setBounds(158, 121, 127, 25);
		contentPane.add(rdbtnMenorDuracion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(100, 53, 116, 22);
		contentPane.add(comboBox);
		
		comboBox.addItem("Seleccione");
		for(int i=0; i<CrearInsumo.contenedorInsumos.size(); i++)
			comboBox.addItem(CrearInsumo.contenedorInsumos.get(i).getId());
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<CrearInsumo.contenedorInsumos.size(); i++) {
					if(CrearInsumo.contenedorInsumos.get(i).getId().equals(comboBox.getSelectedItem().toString()))
						insumoSeleccionado = CrearInsumo.contenedorInsumos.get(i);
				}
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean existeAI = false;
				boolean existeAF = false;
				for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
					if(CrearPlanta.contenedorPlantas.get(i).getTipoPlanta().equals(TipoPlanta.INICIAL))
						existeAI = true;
					if(CrearPlanta.contenedorPlantas.get(i).getTipoPlanta().equals(TipoPlanta.FINAL))
						existeAF = true;
				}
				
				if(rdbtnMenorDuracion.isSelected() && rdbtnMenorDistancia.isSelected()) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una sola opcion.");
					BuscarMejorCamino camino = new BuscarMejorCamino();
					camino.setVisible(true);
					dispose();
				}
				else if(!rdbtnMenorDuracion.isSelected() && !rdbtnMenorDistancia.isSelected()) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una opcion.");
					BuscarMejorCamino camino = new BuscarMejorCamino();
					camino.setVisible(true);
					dispose();
				}
				else if(!existeAI || !existeAF) {
					JOptionPane.showMessageDialog(null, "Aun no existen plantas de acopio iniciales y finales");
					MenuCamino camino = new MenuCamino();
					camino.setVisible(true);
					dispose();
				}
				else if(!rdbtnMenorDuracion.isSelected() && rdbtnMenorDistancia.isSelected()) {
					for(int i=0; i<CrearInsumo.contenedorInsumos.size(); i++) {
						if(CrearInsumo.contenedorInsumos.get(i).getId().equals(comboBox.getSelectedItem().toString()))
							insumoSeleccionado = CrearInsumo.contenedorInsumos.get(i);
					}
					MenorDistancia menorDistancia = new MenorDistancia();
					menorDistancia.setVisible(true);
					dispose();
				}
				else if(rdbtnMenorDuracion.isSelected() && !rdbtnMenorDistancia.isSelected()) {
					MenorDuracion menorDuracion = new MenorDuracion();
					menorDuracion.setVisible(true);
					dispose();
				}
			}
		});
		btnAceptar.setBounds(79, 155, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamino menuCamino = new MenuCamino();
				menuCamino.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(188, 155, 97, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblSeleccioneUnInsumo = new JLabel("Seleccione un insumo");
		lblSeleccioneUnInsumo.setBounds(28, 24, 186, 16);
		contentPane.add(lblSeleccioneUnInsumo);
		
		JLabel lblIdInsumo = new JLabel("ID Insumo:");
		lblIdInsumo.setBounds(28, 56, 72, 16);
		contentPane.add(lblIdInsumo);
	}
}
