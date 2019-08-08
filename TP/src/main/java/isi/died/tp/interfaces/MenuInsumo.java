package isi.died.tp.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camion;
import isi.died.tp.dominio.Insumo;
import isi.died.tp.interfaces.buscar.BuscarCamion;
import isi.died.tp.interfaces.buscar.BuscarInsumo;
import isi.died.tp.interfaces.crear.CrearCamion;
import isi.died.tp.interfaces.crear.CrearInsumo;
import isi.died.tp.interfaces.editar.EditarInsumo;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuInsumo extends JFrame {

	private JPanel insumoPane;
	private DefaultTableModel model;
	

	private JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInsumo frame = new MenuInsumo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MenuInsumo() {
			
		setTitle("INSUMO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 948, 300);
		insumoPane = new JPanel();
		insumoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(insumoPane);
		insumoPane.setLayout(null);
		
		JButton btnNewButton = new JButton("AGREGAR INSUMO");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearInsumo insumo = new CrearInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(660, 13, 258, 36);
		insumoPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EDITAR INSUMO");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarInsumo buscarInsumo = new BuscarInsumo();
				buscarInsumo.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(660, 60, 258, 36);
		insumoPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("BUSCAR INSUMO");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarInsumo insumo = new BuscarInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(660, 108, 258, 36);
		insumoPane.add(btnNewButton_2);
		
		JButton btnEliminarInsumo = new JButton("ELIMINAR INSUMO");
		btnEliminarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarInsumo insumo = new BuscarInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		btnEliminarInsumo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminarInsumo.setBounds(660, 156, 258, 36);
		insumoPane.add(btnEliminarInsumo);
		
		JButton btnVolverAlMenu = new JButton("VOLVER AL MENU PRINCIPAL");
		btnVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolverAlMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolverAlMenu.setBounds(660, 204, 258, 36);
		insumoPane.add(btnVolverAlMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 636, 227);
		insumoPane.add(scrollPane);
		
		table = new JTable();
		String data[][] = {};
		String col[] = {"ID Insumo", "Descripcion", "Costo", "Peso", "Unidad De Medida", "Stock", "Es refrigerado?", "Densidad"};
		model = new DefaultTableModel(data,col);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		mostrarDatos();
	}
	
	public void mostrarDatos() {
		for(int j=0; j<CrearInsumo.contenedorInsumos.size(); j++) {
			Insumo i = (Insumo) CrearInsumo.contenedorInsumos.get(j);
			model.insertRow(0, new Object[]{});
			model.setValueAt(i.getId(), 0, 0);
			model.setValueAt(i.getDescripcion(), 0, 1);
			model.setValueAt(i.getCosto(), 0, 2);
			model.setValueAt(i.getPeso(), 0, 3);
			model.setValueAt(i.getUnidadDeMedida(), 0, 4);
			model.setValueAt(i.getStock(), 0, 5);
			model.setValueAt(i.isEsRefrigerado(), 0, 6);
			model.setValueAt(i.getDensidad(),0,7);
		}
	}
	
}
