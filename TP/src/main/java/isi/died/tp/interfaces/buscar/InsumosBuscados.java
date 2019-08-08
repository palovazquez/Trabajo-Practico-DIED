package isi.died.tp.interfaces.buscar;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Insumo;

import isi.died.tp.interfaces.MenuInsumo;
import isi.died.tp.interfaces.editar.EditarCamino;
import isi.died.tp.interfaces.editar.EditarInsumo;
import isi.died.tp.interfaces.eliminar.EliminarCamino;
import isi.died.tp.interfaces.eliminar.EliminarInsumo;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsumosBuscados extends JFrame {

	private JPanel contentPane;
	//private JTabltableos;
	private JTable table;
	private DefaultTableModel model; 
	
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsumosBuscados frame = new InsumosBuscados();
					frame.setTitle("Buscar Insumo");
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	
	public InsumosBuscados() {
		
		setTitle("Buscar Insumo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(27, 10, 674, 206);
	
		JButton boton_atras = new JButton("Atrás");
		boton_atras.setBounds(440, 229, 79, 25);
		boton_atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarInsumo insumo = new BuscarInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		
		JButton boton_editar = new JButton("Editar");
		boton_editar.setBounds(531, 229, 79, 25);
		JButton boton_eliminar = new JButton("Eliminar");
		boton_eliminar.setBounds(622, 229, 79, 25);
		
		boton_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					EliminarInsumo eliminarInsumo = new EliminarInsumo(BuscarInsumo.listaInsumos.get(table.getSelectedRow()));
					eliminarInsumo.setVisible(true);
					dispose();
				}
			}
		});
		
		boton_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					EditarInsumo editarInsumo = new EditarInsumo(BuscarInsumo.listaInsumos.get(table.getSelectedRow()));
					editarInsumo.setVisible(true);
					dispose();
				}
			}
		});
		
		table = new JTable();
		String data[][]= {};
		String col[] = {"Nombre", "ID", "Costo Unidad", "Peso Unidad", "Unidad De Medida", "Stock Total", "Refrigerado", "Densidad"};
		model = new DefaultTableModel (data, col);
		contentPane.setLayout(null);
		table.setModel(model);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		contentPane.add(boton_atras);
		contentPane.add(boton_editar);
		contentPane.add(boton_eliminar);
		
		mostrarDatos();
	}
	
	
	public void mostrarDatos() {
		Insumo ins = new Insumo();
		for (int i =0; i<BuscarInsumo.listaInsumos.size();i++) {
			
				ins = BuscarInsumo.listaInsumos.get(i);
				model.insertRow(0, new Object [] {});
				model.setValueAt(ins.getDescripcion(),0 , 0);
				model.setValueAt(ins.getId(),0 , 1);
				model.setValueAt(ins.getCosto(),0 , 2);
				model.setValueAt(ins.getPeso(),0 , 3);
				model.setValueAt(ins.getUnidadDeMedida(),0 , 4);
				model.setValueAt(ins.getStockTotal(),0 , 5);
				model.setValueAt(ins.isEsRefrigerado(),0 , 6);
				model.setValueAt(ins.getDensidad(),0,7);
			}
			
		}
}


