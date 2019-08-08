package isi.died.tp.interfaces.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Stock;
import isi.died.tp.interfaces.MenuStock;
import isi.died.tp.interfaces.buscar.BuscarPlanta;
import isi.died.tp.interfaces.buscar.BuscarStock;
import isi.died.tp.interfaces.crear.AgregarStock;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarStock extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarStock frame = new EliminarStock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EliminarStock() {
		setTitle("Eliminar Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblestSeguroDe = new JLabel("¿Está seguro de que desea eliminar este stock?");
		lblestSeguroDe.setBounds(36, 27, 284, 16);
		contentPane.add(lblestSeguroDe);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) { //Modifico solo el stock de esa planta
					if(CrearPlanta.contenedorPlantas.get(i).getIdPlanta().equals(BuscarPlanta.idPlanta)) {
						for(int j=0; j<AgregarStock.contenedorStocks.size(); j++) {//Modifico el contenedor stocks
							Stock s = (Stock) AgregarStock.contenedorStocks.get(j);
							if(s.getIdStock().equals(BuscarStock.idStock) && s.getInsumo().getId().equals(BuscarStock.idInsumo))
								AgregarStock.contenedorStocks.remove(j);
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Eliminado exitosamente. ");
				MenuStock ms = new MenuStock(MenuStock.PSeleccionadaStock);
				ms.setVisible(true);
				dispose();
			}
		});
		btnAceptar.setBounds(106, 56, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuStock stock = new MenuStock(MenuStock.PSeleccionadaStock);
				stock.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(215, 56, 97, 25);
		contentPane.add(btnCancelar);
	}

}
