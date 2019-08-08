package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.MenuStock;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarStock extends JFrame {

	private JPanel contentPane;
	private JTextField texto_idStock;
	private JTextField texto_idInsumo;
	private JTextField texto_cantidad;
	private JTextField texto_puntoPedido;
	
	public static Integer idStock;
	public static String idInsumo;
	public static Integer cantidad;
	public static Integer puntoPedido;
	private static Planta PlantaSeleccionada = new Planta();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarStock frame = new BuscarStock(PlantaSeleccionada);
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
	public BuscarStock(Planta PlantaSeleccionada) {
		this.PlantaSeleccionada = PlantaSeleccionada;
		setTitle("Buscar Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del stock");
		lblIngreseLosDatos.setBounds(31, 13, 176, 16);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblIdStock = new JLabel("ID Stock (*):");
		lblIdStock.setBounds(31, 52, 74, 16);
		contentPane.add(lblIdStock);
		
		JLabel lblIdInsumo = new JLabel("ID Insumo (*):");
		lblIdInsumo.setBounds(31, 91, 85, 16);
		contentPane.add(lblIdInsumo);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(258, 52, 56, 16);
		contentPane.add(lblCantidad);
		
		JLabel lblPuntoDePedido = new JLabel("Punto de Pedido:");
		lblPuntoDePedido.setBounds(258, 91, 110, 16);
		contentPane.add(lblPuntoDePedido);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuStock menuStock = new MenuStock(MenuStock.PSeleccionadaStock);
				menuStock.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(381, 134, 97, 25);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Buscar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(texto_idStock.getText().isEmpty() || texto_idInsumo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese los campos requeridos.");
					BuscarStock stock = new BuscarStock(PlantaSeleccionada);
					stock.setVisible(true);
					dispose();
				}
				else {
					idStock = Integer.valueOf(texto_idStock.getText());
					idInsumo = texto_idInsumo.getText();
					if(texto_cantidad.getText().isEmpty())
						cantidad = null;
					else
						cantidad = Integer.valueOf(texto_cantidad.getText());
					if(texto_puntoPedido.getText().isEmpty())
						puntoPedido = null;
					else
						puntoPedido = Integer.valueOf(texto_puntoPedido.getText());					
					StocksBuscados stocks = new StocksBuscados(PlantaSeleccionada);
					stocks.setVisible(true);
					dispose();
				}
			}
		});
		btnGuardar.setBounds(271, 134, 97, 25);
		contentPane.add(btnGuardar);
		
		texto_idStock = new JTextField();
		texto_idStock.setBounds(123, 49, 116, 22);
		contentPane.add(texto_idStock);
		texto_idStock.setColumns(10);
		
		texto_idInsumo = new JTextField();
		texto_idInsumo.setBounds(123, 88, 116, 22);
		contentPane.add(texto_idInsumo);
		texto_idInsumo.setColumns(10);
		
		texto_cantidad = new JTextField();
		texto_cantidad.setBounds(362, 49, 116, 22);
		contentPane.add(texto_cantidad);
		texto_cantidad.setColumns(10);
		
		texto_puntoPedido = new JTextField();
		texto_puntoPedido.setBounds(362, 88, 116, 22);
		contentPane.add(texto_puntoPedido);
		texto_puntoPedido.setColumns(10);
		
	}
}
