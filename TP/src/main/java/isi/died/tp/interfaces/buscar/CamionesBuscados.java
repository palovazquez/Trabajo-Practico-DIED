package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Camion;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.MenuCamion;
import isi.died.tp.interfaces.crear.CrearCamion;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.interfaces.editar.EditarCamino;
import isi.died.tp.interfaces.editar.EditarCamion;
import isi.died.tp.interfaces.eliminar.EliminarCamino;
import isi.died.tp.interfaces.eliminar.EliminarCamion;

import javax.swing.JLabel;

public class CamionesBuscados extends JFrame {

	private JPanel contentPane;
	private JTable tablaCamiones;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CamionesBuscados frame = new CamionesBuscados();
					frame.setTitle("Buscar Camión");
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
	public CamionesBuscados() {
		setTitle("Buscar Camión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 539, 132);
		contentPane.add(scrollPane);
		
		tablaCamiones = new JTable();
		tablaCamiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		String data[][] = {};
		String col[] = {"ID", "Marca", "Modelo", "Dominio", "A\u00F1o", "Costo por km", "Capacidad","Apto"};
		model = new DefaultTableModel(data,col) {
			Class[] columnTypes = new Class[] {Integer.class, String.class, String.class, String.class, Integer.class, Float.class, Float.class, Boolean.class};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tablaCamiones.setModel(model);
		tablaCamiones.getColumnModel().getColumn(0).setPreferredWidth(53);
		tablaCamiones.getColumnModel().getColumn(1).setPreferredWidth(69);
		tablaCamiones.getColumnModel().getColumn(2).setPreferredWidth(69);
		tablaCamiones.getColumnModel().getColumn(3).setPreferredWidth(60);
		tablaCamiones.getColumnModel().getColumn(4).setPreferredWidth(49);
		tablaCamiones.getColumnModel().getColumn(5).setMinWidth(16);
		tablaCamiones.getColumnModel().getColumn(6).setPreferredWidth(47);
		scrollPane.setViewportView(tablaCamiones);
		
		JButton boton_eliminar = new JButton("Eliminar");
		boton_eliminar.setBounds(455, 168, 97, 25);
		contentPane.add(boton_eliminar);
		
		JButton boton_atras = new JButton("Atrás");
		boton_atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamion buscarCamion = new BuscarCamion();
				buscarCamion.setVisible(true);
				dispose();
			}
		});
		boton_atras.setBounds(10, 168, 97, 25);
		contentPane.add(boton_atras);
		
		JButton boton_editar = new JButton("Editar");
		boton_editar.setBounds(356, 169, 89, 23);
		contentPane.add(boton_editar);
		
		
		ListSelectionModel model = tablaCamiones.getSelectionModel();
		
		boton_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaCamiones.getSelectedRow()!=-1) {
					EliminarCamion eliminarCamion = new EliminarCamion();
					eliminarCamion.setVisible(true);
					dispose();
				}
			}
		});
		
		boton_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaCamiones.getSelectedRow()!=-1) {
					EditarCamion editarCamion = new EditarCamion();
					editarCamion.setVisible(true);
					dispose();
				}
			}
		});
		
		mostrarDatos();
		
	}
	
	public void mostrarDatos() {
		Camion c;
		for(int i=0; i<CrearCamion.contenedorCamiones.size(); i++) {
			c = (Camion) CrearCamion.contenedorCamiones.get(i);
			if(c.getIdRegistro().equals(BuscarCamion.idCamion)) {
				model.insertRow(0, new Object[]{});
				model.setValueAt(c.getIdRegistro(), 0, 0);
				model.setValueAt(c.getMarca(), 0, 1);
				model.setValueAt(c.getModelo(), 0, 2);
				model.setValueAt(c.getDominio(), 0, 3);
				model.setValueAt(c.getAño(), 0, 4);
				model.setValueAt(c.getCostoPorKm(), 0, 5);
				model.setValueAt(c.getCapacidad(), 0, 6);
				model.setValueAt(c.isAptoParaLiquidos(), 0, 7);
			}
		}
	}
}
