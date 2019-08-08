package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.Stock;
import isi.died.tp.interfaces.MenuStock;
import isi.died.tp.interfaces.crear.AgregarStock;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.interfaces.editar.EditarStock;
import isi.died.tp.interfaces.eliminar.EliminarPlanta;
import isi.died.tp.interfaces.eliminar.EliminarStock;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StocksBuscados extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private static Planta PSeleccionadaBuscarStock;
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StocksBuscados frame = new StocksBuscados(PSeleccionadaBuscarStock);
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
	public StocksBuscados(Planta PSeleccionadaBuscarStock) {
		this.PSeleccionadaBuscarStock = PSeleccionadaBuscarStock;
		setTitle("Buscar Stocks");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 507, 179);
		contentPane.add(scrollPane);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarStock eliminarStock = new EliminarStock();
				eliminarStock.setVisible(true);
				dispose();
			}
		});
		btnEliminar.setBounds(422, 205, 97, 25);
		contentPane.add(btnEliminar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EditarStock editarStock = new EditarStock();
				editarStock.setVisible(true);
				dispose();
			}
		});
		btnEditar.setBounds(313, 205, 97, 25);
		contentPane.add(btnEditar);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuStock menuStock = new MenuStock(PSeleccionadaBuscarStock);
				menuStock.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(12, 205, 97, 25);
		contentPane.add(btnAtras);
		
		table = new JTable();
		String data[][] = {};
		String col[] = {"ID Stock", "ID Insumo", "Cantidad", "Punto de pedido"};
		model = new DefaultTableModel(data,col);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		mostrarDatos();
	}
	
	public void mostrarDatos() {
		Stock s;
		for(int i=0; i<AgregarStock.contenedorStocks.size(); i++) {
			s = (Stock) AgregarStock.contenedorStocks.get(i);
			
			if(s.getIdStock().equals(BuscarStock.idStock) && s.getInsumo().getId().equals(BuscarStock.idInsumo) &&
			   s.getCantidad().equals(BuscarStock.cantidad) && s.getPuntoPedido().equals(BuscarStock.puntoPedido) ||
			   s.getIdStock().equals(BuscarStock.idStock) && s.getInsumo().getId().equals(BuscarStock.idInsumo) &&
			   BuscarStock.cantidad==null && s.getPuntoPedido().equals(BuscarStock.puntoPedido) ||
			   s.getIdStock().equals(BuscarStock.idStock) && s.getInsumo().getId().equals(BuscarStock.idInsumo) &&
			   s.getCantidad().equals(BuscarStock.cantidad) && BuscarStock.puntoPedido==null ||
			   s.getIdStock().equals(BuscarStock.idStock) && s.getInsumo().getId().equals(BuscarStock.idInsumo) &&
			   BuscarStock.cantidad==null && BuscarStock.puntoPedido==null) {
				model.insertRow(0, new Object[]{});
				model.setValueAt(s.getIdStock(), 0, 0);
				model.setValueAt(s.getInsumo().getId(), 0, 1);
				model.setValueAt(s.getCantidad(), 0, 2);
				model.setValueAt(s.getPuntoPedido(), 0, 3);
			}
		}
	}
}
