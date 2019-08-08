package isi.died.tp.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camion;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.buscar.BuscarCamion;
import isi.died.tp.interfaces.buscar.PlantasBuscadas;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearCamion;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.interfaces.editar.EditarCamion;
import isi.died.tp.interfaces.eliminar.EliminarPlanta;
import isi.died.tp.parte5.GenerarSolucion;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuCamion extends JFrame {

	private JPanel camionPane;
	private JTable table;
	private DefaultTableModel model;
	public static ArrayList<Camion> listaCamiones = new ArrayList<Camion>();
	public static ArrayList<Camion> listaCamionesInversa = new ArrayList<Camion>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuCamion frame = new MenuCamion();
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
	public MenuCamion() {
		listaCamiones.clear();
		listaCamionesInversa.clear();
		setTitle("CAMIÓN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1016, 284);
		camionPane = new JPanel();
		camionPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(camionPane);
		camionPane.setLayout(null);
		
		JButton btnAgregarCamion = new JButton("AGREGAR CAMIÓN");
		btnAgregarCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearCamion camion = new CrearCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnAgregarCamion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAgregarCamion.setBounds(732, 13, 258, 36);
		camionPane.add(btnAgregarCamion);
		
		JButton btnEditarCamion = new JButton("EDITAR CAMIÓN");
		btnEditarCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamion camion = new BuscarCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnEditarCamion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditarCamion.setBounds(732, 60, 258, 36);
		camionPane.add(btnEditarCamion);
		
		JButton btnBuscarCamion = new JButton("BUSCAR CAMIÓN");
		btnBuscarCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamion camion = new BuscarCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnBuscarCamion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarCamion.setBounds(732, 107, 258, 36);
		camionPane.add(btnBuscarCamion);
		
		JButton btnEliminarCamion = new JButton("ELIMINAR CAMIÓN");
		btnEliminarCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamion camion = new BuscarCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnEliminarCamion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminarCamion.setBounds(732, 154, 258, 36);
		camionPane.add(btnEliminarCamion);
		
		JButton btnVolverAlMenu = new JButton("VOLVER AL MENÚ PRINCIPAL");
		btnVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolverAlMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolverAlMenu.setBounds(732, 201, 258, 36);
		camionPane.add(btnVolverAlMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 704, 224);
		camionPane.add(scrollPane);
		
		table = new JTable();
		String data[][] = {};
		String col[] = {"ID Registro", "Marca", "Modelo", "Dominio", "Año", "Costo por Km", "Capacidad", "Apto Liquidos"};
		model = new DefaultTableModel(data,col);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		/*			
		JButton boton_GenerarSolucion = new JButton("GENERAR SOLUCIÓN");
		boton_GenerarSolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					Collections.reverse(MenuCamion.listaCamiones);
					MenuCamion.listaCamionesInversa = MenuCamion.listaCamiones; 
					GenerarSolucion generarSolucion = new GenerarSolucion(listaCamionesInversa.get(table.getSelectedRow()));
					generarSolucion.setVisible(true);
					dispose(); 
				}
			}
		});
		boton_GenerarSolucion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boton_GenerarSolucion.setBounds(728, 14, 258, 36);
		camionPane.add(boton_GenerarSolucion);
		*/
		mostrarDatos();
	}
	
	public void mostrarDatos() {
		for(int i=0; i<CrearCamion.contenedorCamiones.size(); i++) {
			Camion c = (Camion) CrearCamion.contenedorCamiones.get(i);
			model.insertRow(0, new Object[]{});
			model.setValueAt(c.getIdRegistro(), 0, 0);
			model.setValueAt(c.getMarca(), 0, 1);
			model.setValueAt(c.getModelo(), 0, 2);
			model.setValueAt(c.getDominio(), 0, 3);
			model.setValueAt(c.getAño(), 0, 4);
			model.setValueAt(c.getCostoPorKm(), 0, 5);
			model.setValueAt(c.getCapacidad(), 0, 6);
			model.setValueAt(c.isAptoParaLiquidos(), 0, 7);
			MenuCamion.listaCamiones.add(CrearCamion.contenedorCamiones.get(i));
		}
	}
}
