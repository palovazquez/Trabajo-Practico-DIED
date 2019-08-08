package isi.died.tp.estructuras;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.crear.CrearInsumo;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class FaltaInsumo extends JFrame {

	private JPanel contentPane;
	public static Insumo insumoSeleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FaltaInsumo frame = new FaltaInsumo();
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
	public FaltaInsumo() {
		setTitle("Insumo Faltante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngresarInsumoPara = new JLabel("Seleccione un insumo");
		lblIngresarInsumoPara.setBounds(24, 16, 297, 16);
		contentPane.add(lblIngresarInsumoPara);
		
		JLabel lblIdInusmo = new JLabel("ID Inusmo:");
		lblIdInusmo.setBounds(24, 52, 68, 16);
		contentPane.add(lblIdInusmo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(100, 49, 142, 22);
		contentPane.add(comboBox);
		
		comboBox.addItem("Seleccione");
		for(int i=0; i<CrearInsumo.contenedorInsumos.size(); i++)
			comboBox.addItem(CrearInsumo.contenedorInsumos.get(i).getId());
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<CrearInsumo.contenedorInsumos.size(); i++) {
					if(CrearInsumo.contenedorInsumos.get(i).getId().equals(comboBox.getSelectedItem().toString()))
						insumoSeleccionado = CrearInsumo.contenedorInsumos.get(i);
				}
			}
		});
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GrafoCaminosAux grafoAux = new GrafoCaminosAux();
				grafoAux.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(115, 84, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamino menuCamino = new MenuCamino();
				menuCamino.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(224, 84, 97, 25);
		contentPane.add(btnCancelar);
	}

}
