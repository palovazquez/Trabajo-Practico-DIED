package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.estructuras.ArbolBinarioBusqueda;
import isi.died.tp.estructuras.OrdenarNombre;
import isi.died.tp.interfaces.MenuInsumo;
import isi.died.tp.interfaces.crear.CrearInsumo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class BuscarInsumo extends JFrame  {

	private JPanel contentPane;
	private JTextField texto_nombreInsumo;
	private JTextField texto_costoMin;
	private JTextField texto_costoMax;
	private JLabel lblIngreseLosDatos;
	private JLabel label_1;
	private JTextField texto_stockMin;
	private JLabel label_2;
	private JTextField texto_stockMax;
	private JRadioButton rdbtnNombre;
	private JRadioButton rdbtnCosto;
	private JRadioButton rdbtnStock;
	
	public static ArrayList<Insumo> listaInsumos = new ArrayList<Insumo>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarInsumo frame = new BuscarInsumo();
					frame.setTitle("Buscar Insumo");
					frame.setVisible(true);
					listaInsumos.clear();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BuscarInsumo() {
		
		setTitle("Buscar Insumo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreInsumo = new JLabel("Nombre:");
		lblNombreInsumo.setBounds(21, 63, 79, 14);
		contentPane.add(lblNombreInsumo);
		
		texto_nombreInsumo = new JTextField();
		texto_nombreInsumo.setBounds(113, 60, 86, 20);
		contentPane.add(texto_nombreInsumo);
		texto_nombreInsumo.setColumns(10);
		
		JLabel lblCostoMnimoars = new JLabel("Costo mínimo:");
		lblCostoMnimoars.setBounds(21, 98, 86, 14);
		contentPane.add(lblCostoMnimoars);
		
		texto_costoMin = new JTextField();
		texto_costoMin.setBounds(113, 95, 86, 20);
		contentPane.add(texto_costoMin);
		texto_costoMin.setColumns(10);
		
		JLabel label = new JLabel("Costo máximo:");
		label.setBounds(223, 98, 120, 14);
		contentPane.add(label);
		
		texto_costoMax = new JTextField();
		texto_costoMax.setColumns(10);
		texto_costoMax.setBounds(319, 95, 86, 20);
		contentPane.add(texto_costoMax);
		
		lblIngreseLosDatos = new JLabel("Ingrese los datos del insumo");
		lblIngreseLosDatos.setBounds(21, 23, 355, 20);
		contentPane.add(lblIngreseLosDatos);
		
		label_1 = new JLabel("Stock mínimo:");
		label_1.setBounds(21, 135, 86, 14);
		contentPane.add(label_1);
		
		texto_stockMin = new JTextField();
		texto_stockMin.setColumns(10);
		texto_stockMin.setBounds(113, 132, 86, 20);
		contentPane.add(texto_stockMin);
		
		label_2 = new JLabel("Stock máximo:");
		label_2.setBounds(223, 135, 120, 14);
		contentPane.add(label_2);
		
		JLabel lblOrdenar = new JLabel("Ordenar:");
		lblOrdenar.setBounds(21, 170, 86, 14);
		contentPane.add(lblOrdenar);
		
		JRadioButton rdbtnAscendente = new JRadioButton("Ascendente");
		rdbtnAscendente.setBounds(110, 168, 109, 23);
		contentPane.add(rdbtnAscendente);
		
		JRadioButton rdbtnDescendente = new JRadioButton("Descendente");
		rdbtnDescendente.setBounds(110, 190, 109, 23);
		contentPane.add(rdbtnDescendente);
		
		JLabel lblSegn = new JLabel("Seg\u00FAn:");
		lblSegn.setBounds(223, 170, 46, 20);
		contentPane.add(lblSegn);
		
		rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setBounds(275, 213, 95, 23);
		contentPane.add(rdbtnNombre);
		
		rdbtnCosto = new JRadioButton("Costo");
		rdbtnCosto.setBounds(275, 190, 109, 23);
		contentPane.add(rdbtnCosto);
		
		rdbtnStock = new JRadioButton("Stock Total");
		rdbtnStock.setBounds(275, 166, 109, 23);
		contentPane.add(rdbtnStock);
	
		texto_stockMax = new JTextField();
		texto_stockMax.setColumns(10);
		texto_stockMax.setBounds(319, 132, 86, 20);
		contentPane.add(texto_stockMax);
		
		
		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuInsumo insumo = new MenuInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		boton_cancelar.setBounds(316, 253, 89, 23);
		contentPane.add(boton_cancelar);
		
		JButton boton_buscar = new JButton("Buscar");                              
		boton_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 if(CrearInsumo.contenedorInsumos.size()==0) {
				 JOptionPane.showMessageDialog(null, "No existen insumos guardados en el sistema");
				 MenuInsumo menuInsumo = new MenuInsumo();
		         menuInsumo.setVisible(true);
			     dispose();
			
			}else if((rdbtnAscendente.isSelected() && rdbtnDescendente.isSelected()) || ( !(rdbtnAscendente.isSelected()) && !(rdbtnDescendente.isSelected()))) {
                 JOptionPane.showMessageDialog(null, "Seleccione una opción de ordenamiento");
                 BuscarInsumo buscarInsumo = new BuscarInsumo();
                 buscarInsumo.setVisible(true);
                 dispose();
             }
			else if((rdbtnStock.isSelected() && rdbtnCosto.isSelected() && rdbtnNombre.isSelected()) ||
					( !(rdbtnStock.isSelected()) && !(rdbtnNombre.isSelected()) && !(rdbtnCosto.isSelected()))) {
                JOptionPane.showMessageDialog(null, "Seleccione un creiterio de búsqueda");
                BuscarInsumo buscarInsumo = new BuscarInsumo();
                buscarInsumo.setVisible(true);
                dispose();
			 
			}
			else {
					int cantInsumos = (CrearInsumo.contenedorInsumos).size();
					
				 	if(!((texto_nombreInsumo.getText()).isEmpty()) && rdbtnNombre.isSelected() ) {
					
						ArbolBinarioBusqueda<Insumo> arbolInsumos = new ArbolBinarioBusqueda<Insumo>(CrearInsumo.contenedorInsumos.get(0));
			
						for (int i=0; i <cantInsumos; i++ ) {
							if(CrearInsumo.contenedorInsumos.get(i).getDescripcion().startsWith(texto_nombreInsumo.getText()))
								arbolInsumos.agregar(CrearInsumo.contenedorInsumos.get(i));
						}
				
						listaInsumos = arbolInsumos.preOrden();  //lista con insumos ordenados de menos a mayor en base al stock
						listaInsumos.remove(0);
						
						if(rdbtnAscendente.isSelected()) 
							Collections.sort(listaInsumos, new OrdenarNombre(-1));
						else if(rdbtnDescendente.isSelected()) 
							Collections.sort(listaInsumos, new OrdenarNombre(1));
						
						
						if (listaInsumos.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No se encontraron resultados");
					 		texto_nombreInsumo.setText("");
					 		BuscarInsumo insumo = new BuscarInsumo();
                            insumo.setVisible(true);
                            dispose();
						}
						else {	
							InsumosBuscados insumos = new InsumosBuscados();
							insumos.setVisible(true);
							dispose();
					 
						}
						
				    }
				 	else if((!((texto_costoMin.getText()).isEmpty()) && !(texto_costoMax.getText().isEmpty()) && rdbtnCosto.isSelected()) 
				 			|| (((texto_costoMin.getText()).isEmpty()) && !(texto_costoMax.getText().isEmpty()) && rdbtnCosto.isSelected()) 
				 			|| (!((texto_costoMin.getText()).isEmpty()) && (texto_costoMax.getText().isEmpty()) && rdbtnCosto.isSelected())){
				 		
				 		ArbolBinarioBusqueda<Insumo> arbolCostos = new ArbolBinarioBusqueda<Insumo>(CrearInsumo.contenedorInsumos.get(0));
				 		for (int i=1; i <cantInsumos; i++ ) {
								arbolCostos.agregar(CrearInsumo.contenedorInsumos.get(i));
						}
				 		
				 		if((!((texto_costoMin.getText()).isEmpty()) && !(texto_costoMax.getText().isEmpty()) && rdbtnCosto.isSelected())) { 
				 			
				 			listaInsumos = arbolCostos.rangoCostos(Float.valueOf(texto_costoMin.getText()), Float.valueOf(texto_costoMax.getText()));
				 		
				 	    }else if((((texto_costoMin.getText()).isEmpty()) && !(texto_costoMax.getText().isEmpty()) && rdbtnCosto.isSelected()) ) {
				 	    	listaInsumos = arbolCostos.rangoCostos(0, Float.valueOf(texto_costoMax.getText()));
				 		}
				 		else if((!((texto_costoMin.getText()).isEmpty()) && (texto_costoMax.getText().isEmpty()) && rdbtnCosto.isSelected())){
				 			listaInsumos = arbolCostos.rangoCostos(Float.valueOf(texto_costoMin.getText()), 1000000);
						}
				 		
				 		if(rdbtnAscendente.isSelected()) 
							Collections.sort(listaInsumos, new OrdenarNombre(-1));
						else if(rdbtnDescendente.isSelected()) 
							Collections.sort(listaInsumos, new OrdenarNombre(1));
				 		
						if (listaInsumos.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No se encontraron resultados");
					 		texto_nombreInsumo.setText("");
					 		BuscarInsumo insumo = new BuscarInsumo();
                            insumo.setVisible(true);
                            dispose();
					         
						}
						else {	
							InsumosBuscados insumos = new InsumosBuscados();
							insumos.setVisible(true);
							dispose();
					 
						}
						
				 	}
				 	else if((!((texto_stockMin.getText()).isEmpty()) && !(texto_stockMax.getText().isEmpty()) && rdbtnStock.isSelected()) 
				 			|| (((texto_stockMin.getText()).isEmpty()) && !(texto_stockMax.getText().isEmpty()) && rdbtnStock.isSelected()) 
				 			|| (!((texto_stockMin.getText()).isEmpty()) && (texto_stockMax.getText().isEmpty()) && rdbtnStock.isSelected())){
				 		
				 		ArbolBinarioBusqueda<Insumo> arbolStocks = new ArbolBinarioBusqueda<Insumo>(CrearInsumo.contenedorInsumos.get(0));
				 		for (int i=1; i <cantInsumos; i++ ) {
								arbolStocks.agregar(CrearInsumo.contenedorInsumos.get(i));
						}
						
				 		if((!((texto_stockMin.getText()).isEmpty()) && !(texto_stockMax.getText().isEmpty()) && rdbtnStock.isSelected())) { 
				 			
				 			listaInsumos = arbolStocks.rangoStocks(Integer.valueOf(texto_stockMin.getText()), Integer.valueOf(texto_stockMax.getText()));
				 		
				 	    }else if((((texto_stockMin.getText()).isEmpty()) && !(texto_stockMax.getText().isEmpty()) && rdbtnStock.isSelected()) ) {
				 	    	listaInsumos = arbolStocks.rangoStocks(0, Integer.valueOf(texto_stockMax.getText()));
				 		}
				 		else if((!((texto_stockMin.getText()).isEmpty()) && (texto_stockMax.getText().isEmpty()) && rdbtnStock.isSelected())){
				 			listaInsumos = arbolStocks.rangoStocks(Integer.valueOf(texto_stockMin.getText()), 1000000);
						}
				 		
				 		if(rdbtnAscendente.isSelected()) 
							Collections.sort(listaInsumos, new OrdenarNombre(-1));
						else if(rdbtnDescendente.isSelected()) 
							Collections.sort(listaInsumos, new OrdenarNombre(1));
				 		
				 		if (listaInsumos.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No se encontraron resultados");
					 		texto_nombreInsumo.setText("");
					 		BuscarInsumo insumo = new BuscarInsumo();
                            insumo.setVisible(true);
                            dispose();}
                            
						else {	
							InsumosBuscados insumos = new InsumosBuscados();
							insumos.setVisible(true);
							dispose();
					 
						}
						
				 	}
				 	
				 	
			 	 }
			
			}
		});
		boton_buscar.setBounds(223, 253, 89, 23);
		contentPane.add(boton_buscar);
		
	
}
	}