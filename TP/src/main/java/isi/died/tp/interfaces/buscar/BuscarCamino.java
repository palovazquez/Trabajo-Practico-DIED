package isi.died.tp.interfaces.buscar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import isi.died.tp.interfaces.MenuCamino;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarCamino extends JFrame {

	private JPanel contentPane;
	private JTextField texto_idCamino;
	private JTextField texto_planta1;
	private JTextField texto_planta2;
	private JButton boton_cancelar;
	private JButton boton_buscar;

	public static String idCamino = null;
	public static String idPlanta1 = null;
	public static String idPlanta2 = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarCamino frame = new BuscarCamino();
					frame.setTitle("Buscar Camino");
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
	public BuscarCamino() {
		setTitle("Buscar Camino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInserte = new JLabel("Inserte los datos del camino");
		lblInserte.setBounds(27, 25, 209, 14);
		contentPane.add(lblInserte);
		
		JLabel lblId = new JLabel("ID Camino:");
		lblId.setBounds(27, 65, 64, 14);
		contentPane.add(lblId);
		
		texto_idCamino = new JTextField();
		texto_idCamino.setBounds(103, 62, 86, 20);
		contentPane.add(texto_idCamino);
		texto_idCamino.setColumns(10);
		
		JLabel lblPlanta = new JLabel("ID Planta 1:");
		lblPlanta.setBounds(27, 100, 79, 14);
		contentPane.add(lblPlanta);
		
		texto_planta1 = new JTextField();
		texto_planta1.setBounds(103, 97, 86, 20);
		contentPane.add(texto_planta1);
		texto_planta1.setColumns(10);
		
		JLabel lblPlanta_1 = new JLabel("ID Planta 2:");
		lblPlanta_1.setBounds(212, 100, 79, 14);
		contentPane.add(lblPlanta_1);
		
		texto_planta2 = new JTextField();
		texto_planta2.setBounds(288, 97, 86, 20);
		contentPane.add(texto_planta2);
		texto_planta2.setColumns(10);
		
		boton_cancelar = new JButton("Cancelar");
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamino camino = new MenuCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		boton_cancelar.setBounds(285, 140, 89, 23);
		contentPane.add(boton_cancelar);
		
		boton_buscar = new JButton("Buscar");
		boton_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(texto_idCamino.getText().isEmpty() && texto_planta1.getText().isEmpty() && texto_planta2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese algun dato requerido.");
					BuscarCamino camino = new BuscarCamino();
					camino.setVisible(true);
					dispose();
				}
				else {
					if(texto_idCamino.getText()!=null)
						idCamino = texto_idCamino.getText();
					if(texto_planta1.getText()!=null)
						idPlanta1 = texto_planta1.getText();
					if(texto_planta2.getText()!=null)
						idPlanta2 = texto_planta2.getText();
					CaminosBuscados caminos = new CaminosBuscados();
					caminos.setVisible(true);
					dispose();
				}
			}
		});
		boton_buscar.setBounds(184, 140, 89, 23);
		contentPane.add(boton_buscar);
	}

}
