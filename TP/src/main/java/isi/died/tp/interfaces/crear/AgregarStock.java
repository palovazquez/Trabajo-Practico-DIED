package isi.died.tp.interfaces.crear;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.Stock;
import isi.died.tp.dominio.Unidad;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuStock;
import isi.died.tp.interfaces.buscar.BuscarPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AgregarStock extends JFrame {

	private JPanel contentPane;
	private JTextField texto_idStock;
	private JTextField texto_insumo;
	private JTextField texto_cantidadInsumo;
	private JTextField texto_puntoPedido;
	private static Planta PSeleccionadaAgregarStock;
	public static ArrayList<Stock> contenedorStocks = new ArrayList<Stock>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarStock frame = new AgregarStock(PSeleccionadaAgregarStock);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AgregarStock(Planta PSeleccionadaAgregarStock) {
		this.PSeleccionadaAgregarStock = PSeleccionadaAgregarStock;
		setTitle("Agregar Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlanta = new JLabel("ID Stock:");
		lblPlanta.setBounds(31, 54, 56, 16);
		contentPane.add(lblPlanta);
		
		JLabel lblInsumoAAgregar = new JLabel("ID Insumo a agregar:");
		lblInsumoAAgregar.setBounds(31, 89, 138, 16);
		contentPane.add(lblInsumoAAgregar);
		
		JLabel lblNewLabel = new JLabel("Cantidad del Insumo:");
		lblNewLabel.setBounds(31, 124, 130, 16);
		contentPane.add(lblNewLabel);
		
		texto_idStock = new JTextField();
		texto_idStock.setBounds(167, 51, 116, 22);
		contentPane.add(texto_idStock);
		texto_idStock.setColumns(10);
		
		texto_insumo = new JTextField();
		texto_insumo.setBounds(167, 86, 116, 22);
		contentPane.add(texto_insumo);
		texto_insumo.setColumns(10);
		
		texto_cantidadInsumo = new JTextField();
		texto_cantidadInsumo.setBounds(167, 121, 116, 22);
		contentPane.add(texto_cantidadInsumo);
		texto_cantidadInsumo.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer idStock = null;
				Integer cantidad = null;
				Integer puntoPedido = null;
				Insumo insumo = new Insumo();
				
				if(!texto_idStock.getText().isEmpty())
					idStock = Integer.valueOf(texto_idStock.getText());
				if(!texto_cantidadInsumo.getText().isEmpty())
					cantidad = Integer.valueOf(texto_cantidadInsumo.getText());
				if(!texto_puntoPedido.getText().isEmpty())
					puntoPedido = Integer.valueOf(texto_puntoPedido.getText());
				boolean insumoCargado = false;
				if(!texto_insumo.getText().isEmpty()) {
					for(int i=0; i<CrearInsumo.contenedorInsumos.size(); i++) {
						Insumo in = (Insumo) CrearInsumo.contenedorInsumos.get(i);
						if(in.getId().equals(String.valueOf(texto_insumo.getText()))) {
							insumo = in;
							insumoCargado = true;
						}
					}
				}
				if(!insumoCargado) {
					JOptionPane.showMessageDialog(null, "El ID de insumo no corresponde a ningun insumo válido.");
					AgregarStock agregarStock = new AgregarStock(PSeleccionadaAgregarStock);
					agregarStock.setVisible(true);
					dispose();
				}
				boolean existeInsumo = false;
				for(int i=0; i<PSeleccionadaAgregarStock.getStocks().size(); i++) {
					if(texto_insumo.getText().equals(PSeleccionadaAgregarStock.getStocks().get(i).getInsumo().getId()))
						existeInsumo = true;
				}	
				boolean existeId = false;
				for(int m=0; m<PSeleccionadaAgregarStock.getStocks().size(); m++) {
					if(texto_idStock.getText().equals(PSeleccionadaAgregarStock.getStocks().get(m).getIdStock()))
						existeId = true;
				}
				
				if(insumoCargado && idStock==null || cantidad==null || puntoPedido==null || insumo==null) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
					AgregarStock agregarStock = new AgregarStock(PSeleccionadaAgregarStock);
					agregarStock.setVisible(true);
					dispose();
				}else if(existeId) {
					JOptionPane.showMessageDialog(null, "Ya existe un stock con ese id.");
					AgregarStock agregarStock = new AgregarStock(PSeleccionadaAgregarStock);
					agregarStock.setVisible(true);
					dispose();
				}else if(existeInsumo && insumoCargado) {
					JOptionPane.showMessageDialog(null, "Ya existe stock en esa planta para ese insumo. "
							+ "Si desea agregar mas cantidad, por favor edite el stock");
					MenuStock ms = new MenuStock(PSeleccionadaAgregarStock);
					ms.setVisible(true);
					dispose();
				}else if(insumoCargado && !existeInsumo){
					Stock nuevoStock = new Stock(idStock,cantidad,puntoPedido,insumo);
					contenedorStocks.add(nuevoStock);
					PSeleccionadaAgregarStock.agregarStock(nuevoStock);
					/*for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
						Planta p = CrearPlanta.contenedorPlantas.get(i); 
						if(p.getIdPlanta().equals(BuscarPlanta.idPlanta)) {
							p.getStocks().add(nuevoStock);
						}
					}*/
					JOptionPane.showMessageDialog(null, "Carga exitosa. ");
					texto_idStock.setText("");
					texto_cantidadInsumo.setText("");
					texto_puntoPedido.setText("");
					texto_insumo.setText("");
					MenuStock ms = new MenuStock(PSeleccionadaAgregarStock);
					ms.setVisible(true);
					dispose();
				}
			}
		});
		btnGuardar.setBounds(155, 194, 97, 25);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuStock stock = new MenuStock(PSeleccionadaAgregarStock);
				stock.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(262, 194, 97, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del stock a agregar");
		lblIngreseLosDatos.setBounds(31, 19, 219, 16);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblPuntoDePedido = new JLabel("Punto de pedido:");
		lblPuntoDePedido.setBounds(31, 159, 116, 16);
		contentPane.add(lblPuntoDePedido);
		
		texto_puntoPedido = new JTextField();
		texto_puntoPedido.setBounds(167, 156, 116, 22);
		contentPane.add(texto_puntoPedido);
		texto_puntoPedido.setColumns(10);
	}
}
