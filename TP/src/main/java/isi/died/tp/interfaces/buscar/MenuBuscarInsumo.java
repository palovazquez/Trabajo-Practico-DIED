package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.interfaces.MenuInsumo;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MenuBuscarInsumo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuBuscarInsumo frame = new MenuBuscarInsumo();
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
	public MenuBuscarInsumo() {
		setTitle("Buscar Insumo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneUnCriterio = new JLabel("Seleccione un criterio de búsqueda");
		lblSeleccioneUnCriterio.setBounds(12, 13, 217, 16);
		contentPane.add(lblSeleccioneUnCriterio);
		
		JButton btnBsquedaPorNombre = new JButton("BÚSQUEDA POR NOMBRE");
		btnBsquedaPorNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBsquedaPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBsquedaPorNombre.setBounds(53, 42, 324, 35);
		contentPane.add(btnBsquedaPorNombre);
		
		JButton btnBusquedaPorCosto = new JButton("BUSQUEDA POR COSTO MÍNIMO");
		btnBusquedaPorCosto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBusquedaPorCosto.setBounds(53, 90, 324, 35);
		contentPane.add(btnBusquedaPorCosto);
		
		JButton btnBsquedaPorCosto = new JButton("BÚSQUEDA POR COSTO MÁXIMO");
		btnBsquedaPorCosto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBsquedaPorCosto.setBounds(53, 138, 324, 35);
		contentPane.add(btnBsquedaPorCosto);
		
		JButton btnBsquedaPorStock = new JButton("BÚSQUEDA POR STOCK MÍNIMO");
		btnBsquedaPorStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBsquedaPorStock.setBounds(53, 186, 324, 35);
		contentPane.add(btnBsquedaPorStock);
		
		JButton btnBsquedaPorStock_1 = new JButton("BÚSQUEDA POR STOCK MÁXIMO");
		btnBsquedaPorStock_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBsquedaPorStock_1.setBounds(53, 234, 324, 35);
		contentPane.add(btnBsquedaPorStock_1);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuInsumo menuInsumo = new MenuInsumo();
				menuInsumo.setVisible(true);
				dispose();
			}
		});
		btnAtrs.setBounds(12, 291, 97, 25);
		contentPane.add(btnAtrs);
	}
}
