package isi.died.tp.interfaces.buscar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.interfaces.MenuCamion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class BuscarCamion extends JFrame {

	private JPanel contentPane;
	private JTextField texto_id;
	private JLabel lblId;

	public static String idCamion;
	public static boolean esRefrigerado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarCamion frame = new BuscarCamion();
					frame.setTitle("Buscar Camión");
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
	public BuscarCamion() {
		setTitle("Buscar Camión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseElId = new JLabel("Ingrese los datos del camión");
		lblIngreseElId.setBounds(20, 11, 179, 20);
		contentPane.add(lblIngreseElId);
		
		texto_id = new JTextField();
		texto_id.setBounds(50, 40, 109, 20);
		contentPane.add(texto_id);
		texto_id.setColumns(10);
		
		lblId = new JLabel("ID:");
		lblId.setBounds(20, 43, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblesAptoPara = new JLabel("¿Es apto para líquidos?");
		lblesAptoPara.setBounds(20, 76, 159, 14);
		contentPane.add(lblesAptoPara);
		
		JRadioButton rdbtnSi = new JRadioButton("Sí");
		rdbtnSi.setBounds(160, 72, 39, 23);
		contentPane.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(200, 72, 109, 23);
		contentPane.add(rdbtnNo);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamion camion = new MenuCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(244, 110, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnAceptar = new JButton("Buscar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(texto_id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese los campos requeridos.");
					BuscarCamion camion = new BuscarCamion();
					camion.setVisible(true);
					dispose();
				}
				else if(!rdbtnSi.isSelected() && !rdbtnNo.isSelected()) {
					JOptionPane.showMessageDialog(null, "Seleccione una opcion.");
					BuscarCamion camion = new BuscarCamion();
					camion.setVisible(true);
					dispose();
				}
				else if(rdbtnSi.isSelected() && rdbtnNo.isSelected()) {
					JOptionPane.showMessageDialog(null, "Seleccione una sola opcion.");
					BuscarCamion camion = new BuscarCamion();
					camion.setVisible(true);
					dispose();
				}
				else {
					idCamion = texto_id.getText();
					if(rdbtnSi.isSelected())
						esRefrigerado = true;
					else
						esRefrigerado = false;
					CamionesBuscados camiones = new CamionesBuscados();
					camiones.setVisible(true);
					dispose();
				}
				
			}
		});
		btnAceptar.setBounds(143, 110, 89, 23);
		contentPane.add(btnAceptar);
		

	}
}