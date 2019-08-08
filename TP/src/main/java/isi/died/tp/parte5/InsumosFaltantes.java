package isi.died.tp.parte5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.interfaces.eliminar.EliminarPlanta;

import javax.swing.JButton;

public class InsumosFaltantes extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private static Planta PSeleccionada;
	private JButton btnAtrs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsumosFaltantes frame = new InsumosFaltantes(PSeleccionada);
					frame.setTitle("Insumos Faltantes");
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
	public InsumosFaltantes(Planta PSeleccionada) {
		this.PSeleccionada = PSeleccionada;
		
		setTitle("Insumos Faltantes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 24, 558, 192);
		contentPane.add(scrollPane);
		
		table = new JTable();
		String data[][] = {};
		String col[] = {"ID Insumo", "Descripción", "Cantidad Actual", "Punto de pedido", "Unidades Faltantes"};
		model = new DefaultTableModel(data,col);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(15, 227, 89, 23);
		contentPane.add(btnAtrs);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					MenuPlanta menuPlanta = new MenuPlanta();
					menuPlanta.setVisible(true);
					dispose();
			}
		});
		
		mostrarDatos();
	}
	
	public void mostrarDatos() {
		for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
			if(CrearPlanta.contenedorPlantas.get(i).getIdPlanta() == PSeleccionada.getIdPlanta()) {
				Planta p = (Planta) CrearPlanta.contenedorPlantas.get(i);
				for(int j=0; j<p.getStocks().size(); j++) {
					if(p.necesitaInsumo(p.getStocks().get(j).getInsumo())) {	
						
						model.insertRow(0, new Object[]{});
						model.setValueAt(p.getStocks().get(j).getInsumo().getId(), 0, 0);
						model.setValueAt(p.getStocks().get(j).getInsumo().getDescripcion(), 0, 1);
						model.setValueAt(p.getStocks().get(j).getCantidad(), 0, 2);
						model.setValueAt(p.getStocks().get(j).getPuntoPedido(), 0, 3);
						model.setValueAt(p.cantidadFaltante(p.getStocks().get(j).getInsumo()), 0, 4);
					}
				}
			}
			
			
		}
	}
}
