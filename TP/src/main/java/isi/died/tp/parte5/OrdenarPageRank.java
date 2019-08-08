package isi.died.tp.parte5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.estructuras.OrdenarPorPageRank;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.MenuPrincipal;
import isi.died.tp.interfaces.crear.CrearInsumo;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JButton;

public class OrdenarPageRank extends JFrame {

	private JPanel ordenarPane;
	private JTable table;
	private DefaultTableModel model;
	public static ArrayList<Planta> listaOrdenada = new ArrayList<Planta>();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdenarPageRank frame = new OrdenarPageRank();
					frame.setTitle("Ordenamiento por Page Rank");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public OrdenarPageRank() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ordenamiento por Page Rank");
		setBounds(100, 100, 450, 300);
		ordenarPane = new JPanel();
		ordenarPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ordenarPane);
		ordenarPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 43, 346, 170);
		ordenarPane.add(scrollPane);
		
		table = new JTable();
		String data[][] = {};
		String col[] = {"ID Planta", "Descripcion", "Nro Caminos"};
		model = new DefaultTableModel(data,col);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.setBounds(299, 227, 89, 23);
		ordenarPane.add(btnAtrs);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
		});
		
		mostrarDatos();
	
	}
	
	public void mostrarDatos() {
		//ArrayList<Planta> listaOrdenada = new ArrayList<Planta>();
		listaOrdenada = CrearPlanta.contenedorPlantas;
		Collections.sort(listaOrdenada, new OrdenarPorPageRank());
		Planta i = new Planta();
		for(int j=0; j<listaOrdenada.size(); j++) {
			i = (Planta) listaOrdenada.get(j);
			model.insertRow(0, new Object[]{});
			model.setValueAt(i.getId(), 0, 0);
			model.setValueAt(i.getNombre(), 0, 1);
			model.setValueAt(i.getCantidadCaminos(), 0, 2);
		}
		
	}

	
}
