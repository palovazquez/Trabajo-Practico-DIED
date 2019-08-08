package isi.died.tp.interfaces.editar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Stock;
import isi.died.tp.interfaces.MenuStock;
import isi.died.tp.interfaces.buscar.BuscarPlanta;
import isi.died.tp.interfaces.buscar.BuscarStock;
import isi.died.tp.interfaces.buscar.PlantasBuscadas;
import isi.died.tp.interfaces.buscar.StocksBuscados;
import isi.died.tp.interfaces.crear.AgregarStock;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarStock extends JFrame {

	private JPanel contentPane;
	private JTextField texto_cantidad;
	private JTextField texto_puntoPedido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarStock frame = new EditarStock();
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
	public EditarStock() {
		setTitle("Editar Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos_1 = new JLabel("Ingrese los datos que desee modificar");
		lblIngreseLosDatos_1.setBounds(24, 23, 234, 16);
		contentPane.add(lblIngreseLosDatos_1);
		
		JLabel lblCantidadDelInsumo = new JLabel("Cantidad a agregar:");
		lblCantidadDelInsumo.setBounds(24, 56, 129, 16);
		contentPane.add(lblCantidadDelInsumo);
		
		JLabel lblPuntoDePedido = new JLabel("Punto de Pedido:");
		lblPuntoDePedido.setBounds(24, 95, 116, 16);
		contentPane.add(lblPuntoDePedido);
		
		texto_cantidad = new JTextField();
		texto_cantidad.setBounds(152, 53, 116, 22);
		contentPane.add(texto_cantidad);
		texto_cantidad.setColumns(10);
		
		texto_puntoPedido = new JTextField();
		texto_puntoPedido.setBounds(152, 92, 116, 22);
		contentPane.add(texto_puntoPedido);
		texto_puntoPedido.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuStock ms = new MenuStock(MenuStock.PSeleccionadaStock);
				ms.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(237, 139, 97, 25);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) { //Modifico solo el stock de esa planta
					if(CrearPlanta.contenedorPlantas.get(i).getIdPlanta().equals(BuscarPlanta.idPlanta)) {
						/*for(int j=0; j<CrearPlanta.contenedorPlantas.get(i).getStocks().size(); j++) { //Modifico la lista stocks de planta
							Stock stock = CrearPlanta.contenedorPlantas.get(i).getStocks().get(j);
							if(stock.getId().equals(BuscarStock.idStock) && stock.getInsumo().getId().equals(BuscarStock.idInsumo)) {
								if(!texto_cantidad.getText().isEmpty())
									stock.setCantidad(stock.getCantidad() + Integer.valueOf(texto_cantidad.getText()));
								if(!texto_puntoPedido.getText().isEmpty())
									stock.setPuntoPedido(Integer.valueOf(texto_puntoPedido.getText()));
							}
						}*/
						for(int j=0; j<AgregarStock.contenedorStocks.size(); j++) { //Modifico el contenedor stocks
							Stock s = (Stock) AgregarStock.contenedorStocks.get(j);
							if(s.getIdStock().equals(BuscarStock.idStock) && s.getInsumo().getId().equals(BuscarStock.idInsumo)) {
								if(!texto_cantidad.getText().isEmpty())
									s.setCantidad(s.getCantidad() + Integer.valueOf(texto_cantidad.getText()));
								if(!texto_puntoPedido.getText().isEmpty())
									s.setPuntoPedido(Integer.valueOf(texto_puntoPedido.getText()));
							}
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Carga exitosa. ");
				texto_cantidad.setText("");
				texto_puntoPedido.setText("");
				MenuStock ms = new MenuStock(MenuStock.PSeleccionadaStock);
				ms.setVisible(true);
				dispose();
			}
		});
		btnGuardar.setBounds(129, 139, 97, 25);
		contentPane.add(btnGuardar);
	}

}
