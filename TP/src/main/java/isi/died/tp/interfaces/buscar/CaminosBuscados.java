package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.interfaces.editar.EditarCamino;
import isi.died.tp.interfaces.eliminar.EliminarCamino;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class CaminosBuscados extends JFrame {

	private JPanel contentPane;
	private JTable tablaCaminos;
	private DefaultTableModel model;
	
	public static String idCaminoSeleccionado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaminosBuscados frame = new CaminosBuscados();
					frame.setTitle("Buscar Camino");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CaminosBuscados() {
		setTitle("Buscar Camino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton boton_atras = new JButton("Atrás");
		boton_atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamino camino = new BuscarCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		
		JButton boton_eliminar = new JButton("Eliminar");
		
		JButton boton_editar = new JButton("Editar");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(boton_atras)
							.addPreferredGap(ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
							.addComponent(boton_editar, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(boton_eliminar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(boton_atras)
						.addComponent(boton_eliminar)
						.addComponent(boton_editar))
					.addContainerGap())
		);
		
		boton_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaCaminos.getSelectedRow()!=-1) {
					idCaminoSeleccionado = (String) model.getValueAt(tablaCaminos.getSelectedRow(),0);
					EliminarCamino eliminarCamino = new EliminarCamino();
					eliminarCamino.setVisible(true);
					dispose();
				}
			}
		});
		
		boton_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaCaminos.getSelectedRow()!=-1) {
					idCaminoSeleccionado = (String) model.getValueAt(tablaCaminos.getSelectedRow(),0);
					EditarCamino editarCamino = new EditarCamino();
					editarCamino.setVisible(true);
					dispose();
				}
			}
		});
		tablaCaminos = new JTable();
		tablaCaminos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String data[][] = {};
		String col[] = {"ID Camino", "Planta 1", "Planta 2", "Distancia ", "Duraci\u00F3n ", "Peso M\u00E1ximo "};
		model = new DefaultTableModel(data,col);
		tablaCaminos.setModel(model);
		scrollPane.setViewportView(tablaCaminos);
		contentPane.setLayout(gl_contentPane);
		
		mostrarDatos();
		
	}
	
	public void mostrarDatos() {
		Camino c;
		for(int i=0; i<CrearCamino.contenedorCaminos.size(); i++) {
			c = (Camino) CrearCamino.contenedorCaminos.get(i);	
			
			if(BuscarCamino.idCamino.isEmpty()) {
				System.out.println("idcamino es nulo");
				if(BuscarCamino.idPlanta1.isEmpty() && BuscarCamino.idPlanta2.equals(c.getPlanta2().getIdPlanta())) {
					System.out.println("idNulo y planta1 nula");
					model.insertRow(0, new Object[]{});
					model.setValueAt(c.getIdCamino(), 0, 0);
					model.setValueAt(c.getPlanta1().getIdPlanta(), 0, 1);
					model.setValueAt(c.getPlanta2().getIdPlanta(), 0, 2);
					model.setValueAt(c.getDistancia(), 0, 3);
					model.setValueAt(c.getDuracion(), 0, 4);
					model.setValueAt(c.getPesoMax(), 0, 5);
				}
				else if(BuscarCamino.idPlanta1.equals(c.getPlanta1().getIdPlanta()) && BuscarCamino.idPlanta2.isEmpty()) {
					System.out.println("id nulo y planta2 nula");
					model.insertRow(0, new Object[]{});
					model.setValueAt(c.getIdCamino(), 0, 0);
					model.setValueAt(c.getPlanta1().getIdPlanta(), 0, 1);
					model.setValueAt(c.getPlanta2().getIdPlanta(), 0, 2);
					model.setValueAt(c.getDistancia(), 0, 3);
					model.setValueAt(c.getDuracion(), 0, 4);
					model.setValueAt(c.getPesoMax(), 0, 5);
				}
				else if(BuscarCamino.idPlanta1.equals(c.getPlanta1().getIdPlanta()) && BuscarCamino.idPlanta2.equals(c.getPlanta2().getIdPlanta())){
					System.out.println("solo id nulo");
					model.insertRow(0, new Object[]{});
					model.setValueAt(c.getIdCamino(), 0, 0);
					model.setValueAt(c.getPlanta1().getIdPlanta(), 0, 1);
					model.setValueAt(c.getPlanta2().getIdPlanta(), 0, 2);
					model.setValueAt(c.getDistancia(), 0, 3);
					model.setValueAt(c.getDuracion(), 0, 4);
					model.setValueAt(c.getPesoMax(), 0, 5);
				}
			}
			else if (BuscarCamino.idCamino.equals(c.getIdCamino())){
				System.out.println("los id camino son iguales");
				if(BuscarCamino.idPlanta1.isEmpty() && BuscarCamino.idPlanta2.equals(c.getPlanta2().getIdPlanta())) {
					System.out.println("id iguales y planta1 nula");
					model.insertRow(0, new Object[]{});
					model.setValueAt(c.getIdCamino(), 0, 0);
					model.setValueAt(c.getPlanta1().getIdPlanta(), 0, 1);
					model.setValueAt(c.getPlanta2().getIdPlanta(), 0, 2);
					model.setValueAt(c.getDistancia(), 0, 3);
					model.setValueAt(c.getDuracion(), 0, 4);
					model.setValueAt(c.getPesoMax(), 0, 5);
				}
				else if(BuscarCamino.idPlanta1.equals(c.getPlanta1().getIdPlanta()) && BuscarCamino.idPlanta2.isEmpty()) {
					System.out.println("id iguales y planta2 nula");
					model.insertRow(0, new Object[]{});
					model.setValueAt(c.getIdCamino(), 0, 0);
					model.setValueAt(c.getPlanta1().getIdPlanta(), 0, 1);
					model.setValueAt(c.getPlanta2().getIdPlanta(), 0, 2);
					model.setValueAt(c.getDistancia(), 0, 3);
					model.setValueAt(c.getDuracion(), 0, 4);
					model.setValueAt(c.getPesoMax(), 0, 5);
				}
				else if(BuscarCamino.idPlanta1.equals(c.getPlanta1().getIdPlanta()) && BuscarCamino.idPlanta2.equals(c.getPlanta2().getIdPlanta())){
					System.out.println("todo completo");
					model.insertRow(0, new Object[]{});
					model.setValueAt(c.getIdCamino(), 0, 0);
					model.setValueAt(c.getPlanta1().getIdPlanta(), 0, 1);
					model.setValueAt(c.getPlanta2().getIdPlanta(), 0, 2);
					model.setValueAt(c.getDistancia(), 0, 3);
					model.setValueAt(c.getDuracion(), 0, 4);
					model.setValueAt(c.getPesoMax(), 0, 5);
				}
				else {
					System.out.println("inserta datos");
					model.insertRow(0, new Object[]{});
					model.setValueAt(c.getIdCamino(), 0, 0);
					model.setValueAt(c.getPlanta1().getIdPlanta(), 0, 1);
					model.setValueAt(c.getPlanta2().getIdPlanta(), 0, 2);
					model.setValueAt(c.getDistancia(), 0, 3);
					model.setValueAt(c.getDuracion(), 0, 4);
					model.setValueAt(c.getPesoMax(), 0, 5);
				}
			}
		}
	}
}
