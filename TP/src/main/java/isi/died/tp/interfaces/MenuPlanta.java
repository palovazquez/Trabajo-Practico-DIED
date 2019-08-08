package isi.died.tp.interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Camion;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.Stock;
import isi.died.tp.dominio.TipoPlanta;
import isi.died.tp.interfaces.buscar.BuscarPlanta;
import isi.died.tp.interfaces.crear.AgregarStock;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearCamion;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.interfaces.editar.EditarPlanta;
import isi.died.tp.parte5.GenerarSolucion;
import isi.died.tp.parte5.InsumosFaltantes;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class MenuPlanta extends JFrame {

	private JPanel plantaPane;
	private JTable table;
	private DefaultTableModel model;
	public static ArrayList<Planta> listaPlantas = new ArrayList<Planta>();
	public static ArrayList<Planta> listaPlantasInversa = new ArrayList<Planta>();
	

	private JTable tableC;
	private DefaultTableModel modelC;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPlanta frame = new MenuPlanta();
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
	public MenuPlanta() {
		listaPlantas.clear();
		listaPlantasInversa.clear();
		MenuCamion.listaCamionesInversa.clear();
		setTitle("PLANTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 430);
		plantaPane = new JPanel();
		plantaPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(plantaPane);
		plantaPane.setLayout(null);
		
		JButton btnAgregarPlanta = new JButton("AGREGAR PLANTA");
		btnAgregarPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearPlanta planta = new CrearPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnAgregarPlanta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAgregarPlanta.setBounds(303, 10, 258, 36);
		plantaPane.add(btnAgregarPlanta);
		
		JButton btnBuscarPlanta = new JButton("BUSCAR PLANTA");
		btnBuscarPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPlanta planta = new BuscarPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnBuscarPlanta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarPlanta.setBounds(303, 102, 258, 36);
		plantaPane.add(btnBuscarPlanta);
		
		JButton btnEliminarPlanta = new JButton("ELIMINAR PLANTA");
		btnEliminarPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPlanta planta = new BuscarPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnEliminarPlanta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminarPlanta.setBounds(303, 148, 258, 36);
		plantaPane.add(btnEliminarPlanta);
		
		JButton btnEditarPlanta = new JButton("EDITAR PLANTA");
		btnEditarPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPlanta buscarPlanta = new BuscarPlanta();
				buscarPlanta.setVisible(true);
				dispose();
			}
		});
		btnEditarPlanta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditarPlanta.setBounds(303, 56, 258, 36);
		plantaPane.add(btnEditarPlanta);
		
		JButton btnVolverAlMenu = new JButton("VOLVER AL MENÚ PRINCIPAL");
		btnVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolverAlMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolverAlMenu.setBounds(303, 332, 258, 36);
		plantaPane.add(btnVolverAlMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 281, 189);
		plantaPane.add(scrollPane);
		
		table = new JTable();
		String data[][] = {};
		String col[] = {"ID Planta", "Nombre", "Tipo" };
		model = new DefaultTableModel(data,col);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton boton_InsumosFaltantes = new JButton("VER INSUMOS FALTANTES");
		boton_InsumosFaltantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPlanta buscarPlanta = new BuscarPlanta();
				buscarPlanta.setVisible(true);
				dispose();
			}
		});
		boton_InsumosFaltantes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boton_InsumosFaltantes.setBounds(303, 194, 258, 36);
		plantaPane.add(boton_InsumosFaltantes);
		
		JLabel lblGenerarSolucinPara = new JLabel("— GENERAR SOLUCIÓN PARA ESTA PLANTA —");
		lblGenerarSolucinPara.setBounds(12, 213, 281, 14);
		plantaPane.add(lblGenerarSolucinPara);
		
		JButton boton_GenerarSolucion = new JButton("GENERAR SOLUCIÓN");
		boton_GenerarSolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					Collections.reverse(MenuCamion.listaCamiones);
					MenuCamion.listaCamionesInversa = MenuCamion.listaCamiones; 
					Collections.reverse(MenuPlanta.listaPlantas);
					MenuPlanta.listaPlantasInversa = MenuPlanta.listaPlantas;
					
				//	MenuCamion.listaCamionesInversa.get(tableC.getSelectedRow());
					
					GenerarSolucion generarSolucion = new GenerarSolucion(listaPlantasInversa.get(table.getSelectedRow()), MenuCamion.listaCamionesInversa.get(tableC.getSelectedRow()));
					generarSolucion.setVisible(true);
					dispose(); 
				}
			}
		});
		boton_GenerarSolucion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boton_GenerarSolucion.setBounds(303, 240, 258, 36);
		plantaPane.add(boton_GenerarSolucion);
		
		mostrarDatos();
		
		JScrollPane scrollPaneC = new JScrollPane();
		scrollPaneC.setBounds(10, 240, 283, 129);
		plantaPane.add(scrollPaneC);
		
		tableC = new JTable();
		String dataa[][] = {};
		String coll[] = {"ID Registro", "Costo por Km", "Capacidad"};
		modelC = new DefaultTableModel(dataa,coll);
		tableC.setModel(modelC);
		scrollPaneC.setViewportView(tableC);
		
		JButton btnVerCaminos = new JButton("VER CAMINOS");
		btnVerCaminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuCamino menuCamino = new MenuCamino();
				menuCamino.setVisible(true);
				dispose();
			}
		});
		btnVerCaminos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerCaminos.setBounds(303, 286, 258, 36);
		plantaPane.add(btnVerCaminos);
		
		mostrarCamiones();		
		
	}
	
	public void mostrarDatos() {
		for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
			Planta p = (Planta) CrearPlanta.contenedorPlantas.get(i);
			model.insertRow(0, new Object[]{});
			model.setValueAt(p.getIdPlanta(), 0, 0);
			model.setValueAt(p.getNombre(), 0, 1);
			model.setValueAt(p.getTipoPlanta(), 0, 2);
			MenuPlanta.listaPlantas.add(CrearPlanta.contenedorPlantas.get(i));
		}
	}
	
	
	public void mostrarCamiones() {
		for(int i=0; i<CrearCamion.contenedorCamiones.size(); i++) {
			Camion c = (Camion) CrearCamion.contenedorCamiones.get(i);
			modelC.insertRow(0, new Object[]{});
			modelC.setValueAt(c.getIdRegistro(), 0, 0);
			modelC.setValueAt(c.getCostoPorKm(), 0, 1);
			modelC.setValueAt(c.getCapacidad(), 0, 2);
			MenuCamion.listaCamiones.add(CrearCamion.contenedorCamiones.get(i));
		}
	}
}