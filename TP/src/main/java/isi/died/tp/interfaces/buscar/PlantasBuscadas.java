package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.Stock;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.MenuStock;
import isi.died.tp.interfaces.crear.AgregarStock;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.interfaces.editar.EditarInsumo;
import isi.died.tp.interfaces.editar.EditarPlanta;
import isi.died.tp.interfaces.eliminar.EliminarPlanta;
import isi.died.tp.parte5.InsumosFaltantes;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PlantasBuscadas extends JFrame {

	private JPanel contentPane;
	private JTable tablaPlantas;
	private DefaultTableModel model;

	public static String idSeleccionado;
	public static String nombreSeleccionado;
	public static ArrayList<Planta> listaRdos = new ArrayList<Planta>();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlantasBuscadas frame = new PlantasBuscadas();
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
	public PlantasBuscadas() {
		listaRdos.clear();
		setTitle("Buscar Planta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 23, 493, 147);
		contentPane.add(scrollPane);
		
	
		tablaPlantas = new JTable();
		tablaPlantas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		String data[][] = {};
		String col[] = {"ID Planta", "Nombre Planta"};
		model = new DefaultTableModel(data,col);
		tablaPlantas.setModel(model);
		scrollPane.setViewportView(tablaPlantas);
				
		mostrarDatos();
		
		JButton btnCancelar = new JButton("Atrás");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPlanta planta = new BuscarPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(33, 189, 70, 25);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Editar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaPlantas.getSelectedRow()!=-1) {
					idSeleccionado = (String) model.getValueAt(tablaPlantas.getSelectedRow(),0);
					nombreSeleccionado = (String) model.getValueAt(tablaPlantas.getSelectedRow(),1);
					EditarPlanta editarPlanta = new EditarPlanta();
					editarPlanta.setVisible(true);
					dispose();
				}
			}
		});
		btnAceptar.setBounds(361, 189, 80, 25);
		contentPane.add(btnAceptar);
		
		JButton btnVerStock = new JButton("Ver Stock");
		btnVerStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaPlantas.getSelectedRow()!=-1) {
				MenuStock stock = new MenuStock(PlantasBuscadas.listaRdos.get(tablaPlantas.getSelectedRow()));
				stock.setVisible(true);	//la otra es q le pase el id no mas y busque en crearplantas.contenedor 
				dispose();}
			}
		});
		btnVerStock.setBounds(259, 189, 92, 25);
		contentPane.add(btnVerStock);
		
		JButton button = new JButton("Eliminar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaPlantas.getSelectedRow()!=-1) {
					idSeleccionado = (String) model.getValueAt(tablaPlantas.getSelectedRow(),0);
					nombreSeleccionado = (String) model.getValueAt(tablaPlantas.getSelectedRow(),1);
					EliminarPlanta eliminarPlanta = new EliminarPlanta();
					eliminarPlanta.setVisible(true);
					dispose();
				}
			}
		});
		button.setBounds(446, 189, 80, 25);
		contentPane.add(button); 
		
		JButton boton_InsumosFaltantes = new JButton("Insumos Faltantes");
		boton_InsumosFaltantes.setBounds(109, 189, 140, 25);
		contentPane.add(boton_InsumosFaltantes);
		
		boton_InsumosFaltantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaPlantas.getSelectedRow()!=-1) {
				//	System.out.println(PlantasBuscadas.listaRdos.get(0).getIdPlanta());
					InsumosFaltantes insumosFaltantes = new InsumosFaltantes(PlantasBuscadas.listaRdos.get(tablaPlantas.getSelectedRow()));
					insumosFaltantes.setVisible(true);
					dispose(); 
				}
			}
		});
		
		
		
	}
	
	public void mostrarDatos() {
		Planta p;
		for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
			p = (Planta) CrearPlanta.contenedorPlantas.get(i);
			if(p.getIdPlanta().equals(BuscarPlanta.idPlanta) && p.getNombre().equals(BuscarPlanta.nombrePlanta) ||
			   BuscarPlanta.idPlanta.isEmpty() && p.getNombre().equals(BuscarPlanta.nombrePlanta) ||
			   p.getIdPlanta().equals(BuscarPlanta.idPlanta) && BuscarPlanta.nombrePlanta.isEmpty()) {
				model.insertRow(0, new Object[]{});
				model.setValueAt(p.getIdPlanta(), 0, 0);
				model.setValueAt(p.getNombre(), 0, 1);
				PlantasBuscadas.listaRdos.add(CrearPlanta.contenedorPlantas.get(i));
			}
		}
	}
}
