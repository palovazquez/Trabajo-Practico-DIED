package isi.died.tp.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.Stock;
import isi.died.tp.interfaces.buscar.BuscarPlanta;
import isi.died.tp.interfaces.buscar.BuscarStock;
import isi.died.tp.interfaces.crear.AgregarStock;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;

public class MenuStock extends JFrame {

	private JPanel stockPane;
	private JTable table;
	private DefaultTableModel model;
	public static Planta PSeleccionadaStock = new Planta();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuStock frame = new MenuStock(PSeleccionadaStock);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuStock(Planta PSeleccionadaStock) {
		this.PSeleccionadaStock = PSeleccionadaStock;
		
		setTitle("STOCK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 300);
		stockPane = new JPanel();
		stockPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(stockPane);
		stockPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PLANTA "+BuscarPlanta.idPlanta);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 13, 143, 25);
		stockPane.add(lblNewLabel);
		
		JButton btnAgregarStock = new JButton("AGREGAR STOCK");
		btnAgregarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarStock stock = new AgregarStock(PSeleccionadaStock);
				stock.setVisible(true);
				dispose();
			}
		});
		btnAgregarStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAgregarStock.setBounds(537, 13, 258, 36);
		stockPane.add(btnAgregarStock);
		
		JButton btnEditarStock = new JButton("EDITAR STOCK");
		btnEditarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarStock buscarStock = new BuscarStock(PSeleccionadaStock);
				buscarStock.setVisible(true);
				dispose();
			}
		});
		btnEditarStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditarStock.setBounds(537, 60, 258, 36);
		stockPane.add(btnEditarStock);
		
		JButton btnBuscarStock = new JButton("BUSCAR STOCK");
		btnBuscarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarStock buscarStock = new BuscarStock(PSeleccionadaStock);
				buscarStock.setVisible(true);
				dispose();
			}
		});
		btnBuscarStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarStock.setBounds(537, 108, 258, 36);
		stockPane.add(btnBuscarStock);
		
		JButton btnEliminarStock = new JButton("ELIMINAR STOCK");
		btnEliminarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarStock buscarStock = new BuscarStock(PSeleccionadaStock);
				buscarStock.setVisible(true);
				dispose();
			}
		});
		btnEliminarStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminarStock.setBounds(537, 156, 258, 36);
		stockPane.add(btnEliminarStock);
		
		JButton btnVolverAlMenu = new JButton("ATRÁS");
		btnVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPlanta planta = new MenuPlanta();
				planta.setVisible(true);
				dispose();				
			}
		});
		btnVolverAlMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolverAlMenu.setBounds(537, 204, 258, 36);
		stockPane.add(btnVolverAlMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 38, 513, 202);
		stockPane.add(scrollPane);
		
		table = new JTable();
		String data[][] = {};
		String col[] = {"ID Stock", "ID Insumo", "Cantidad", "Punto de pedido"};
		model = new DefaultTableModel(data,col);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		mostrarDatos();
	}
	
	public void mostrarDatos() {
		
		for(int k=0; k<PSeleccionadaStock.getStocks().size(); k++) {
			Stock s = (Stock) PSeleccionadaStock.getStocks().get(k);
			model.insertRow(0, new Object[]{});
			model.setValueAt(s.getIdStock(), 0, 0);
			model.setValueAt(s.getInsumo().getId(), 0, 1);
			model.setValueAt(s.getCantidad(), 0, 2);
			model.setValueAt(s.getPuntoPedido(), 0, 3);
		}
		
		/*for(int i=0; i<AgregarStock.contenedorStocks.size(); i++) {
			Stock s = (Stock) AgregarStock.contenedorStocks.get(i);
			if(PSeleccionadaStock.encontrarStock(s.getIdStock())) {
				model.insertRow(0, new Object[]{});
				model.setValueAt(s.getIdStock(), 0, 0);
				model.setValueAt(s.getInsumo().getId(), 0, 1);
				model.setValueAt(s.getCantidad(), 0, 2);
				model.setValueAt(s.getPuntoPedido(), 0, 3);
			}
		}*/
	}	
	
	
}
