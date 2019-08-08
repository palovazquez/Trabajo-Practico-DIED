package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.MenuInsumo;
import isi.died.tp.interfaces.MenuPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class BuscarPlanta extends JFrame {

	private JPanel contentPane;
	private JTextField texto_id;
	private static JTextField texto_nombre;

	public static String idPlanta = new String();		
	public static String nombrePlanta = new String();		
	//public static ArrayList<Planta> listaPlantas = new ArrayList<Planta>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarPlanta frame = new BuscarPlanta();
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
	public BuscarPlanta() {
		setTitle("Buscar Planta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 213);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscarPlantaPor = new JLabel("ID:");
		lblBuscarPlantaPor.setBounds(41, 49, 67, 16);
		contentPane.add(lblBuscarPlantaPor);
		
		JLabel lblNombrePlanta = new JLabel("Nombre:");
		lblNombrePlanta.setBounds(41, 86, 101, 16);
		contentPane.add(lblNombrePlanta);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos de la planta");
		lblIngreseLosDatos.setBounds(31, 13, 176, 16);
		contentPane.add(lblIngreseLosDatos);
		
		texto_id = new JTextField();
		texto_id.setBounds(108, 46, 116, 22);
		contentPane.add(texto_id);
		texto_id.setColumns(10);
		
		texto_nombre = new JTextField();
		texto_nombre.setBounds(108, 83, 116, 22);
		contentPane.add(texto_nombre);
		texto_nombre.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(texto_id.getText().isEmpty() && texto_nombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese los campos requeridos.");
					BuscarPlanta plantas = new BuscarPlanta();
					plantas.setVisible(true);
					dispose();
				}
				else {
					idPlanta = texto_id.getText();
					nombrePlanta = texto_nombre.getText();
					PlantasBuscadas plantas = new PlantasBuscadas();
					plantas.setVisible(true);
					dispose();
				}
			}
		});
		btnBuscar.setBounds(108, 128, 97, 25);
		contentPane.add(btnBuscar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPlanta planta = new MenuPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(217, 128, 97, 25);
		contentPane.add(btnCancelar);
	}
}
