package isi.died.tp.interfaces.crear;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camion;
import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Unidad;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuCamion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CrearCamion extends JFrame {

	private JPanel contentPane;
	private JTextField texto_idRegistro;
	private JTextField texto_marca;
	private JTextField texto_año;
	private JTextField texto_dominio;
	private JTextField texto_modelo;
	private JTextField texto_capacidad;
	private JTextField texto_costoPorKm;

	public static ArrayList<Camion> contenedorCamiones = new ArrayList<Camion>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCamion frame = new CrearCamion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CrearCamion() {
		setTitle("Crear Camión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdRegistro = new JLabel("ID registro:");
		lblIdRegistro.setBounds(22, 45, 70, 16);
		contentPane.add(lblIdRegistro);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(22, 83, 56, 16);
		contentPane.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(251, 83, 56, 16);
		contentPane.add(lblModelo);
		
		JLabel lblAo = new JLabel("Año:");
		lblAo.setBounds(22, 121, 56, 16);
		contentPane.add(lblAo);
		
		JLabel lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setBounds(22, 159, 70, 16);
		contentPane.add(lblCapacidad);
		
		JLabel lblDominio = new JLabel("Dominio:");
		lblDominio.setBounds(251, 121, 56, 16);
		contentPane.add(lblDominio);
		
		JLabel lblCostoPorKilometro = new JLabel("Costo por Kilómetro:");
		lblCostoPorKilometro.setBounds(251, 159, 132, 16);
		contentPane.add(lblCostoPorKilometro);
		
		JLabel lblesAptoPara = new JLabel("¿Es apto para líquidos?");
		lblesAptoPara.setBounds(22, 196, 143, 16);
		contentPane.add(lblesAptoPara);
		
		texto_idRegistro = new JTextField();
		texto_idRegistro.setBounds(96, 42, 116, 22);
		contentPane.add(texto_idRegistro);
		texto_idRegistro.setColumns(10);
		
		texto_marca = new JTextField();
		texto_marca.setBounds(96, 80, 116, 22);
		contentPane.add(texto_marca);
		texto_marca.setColumns(10);
		
		texto_año = new JTextField();
		texto_año.setBounds(96, 118, 116, 22);
		contentPane.add(texto_año);
		texto_año.setColumns(10);
		
		texto_dominio = new JTextField();
		texto_dominio.setBounds(378, 118, 116, 22);
		contentPane.add(texto_dominio);
		texto_dominio.setColumns(10);
		
		texto_modelo = new JTextField();
		texto_modelo.setText("");
		texto_modelo.setBounds(378, 80, 116, 22);
		contentPane.add(texto_modelo);
		texto_modelo.setColumns(10);
		
		texto_capacidad = new JTextField();
		texto_capacidad.setBounds(96, 156, 116, 22);
		contentPane.add(texto_capacidad);
		texto_capacidad.setColumns(10);
		
		texto_costoPorKm = new JTextField();
		texto_costoPorKm.setBounds(378, 156, 116, 22);
		contentPane.add(texto_costoPorKm);
		texto_costoPorKm.setColumns(10);
		
		JRadioButton rdbtnSi = new JRadioButton("Sí");
		rdbtnSi.setBounds(161, 192, 43, 25);
		contentPane.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(208, 192, 43, 25);
		contentPane.add(rdbtnNo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean esRefrigerado = false;
				Integer año = null;
				Float costoPorKm = null;
				Float capacidad = null;
				String idRegistro = texto_idRegistro.getText();
				String marca = texto_marca.getText();
				String modelo = texto_modelo.getText();
				String dominio = texto_dominio.getText();
				if(!texto_año.getText().isEmpty())
					año = Integer.valueOf(texto_año.getText());
				if(!texto_costoPorKm.getText().isEmpty())
					costoPorKm = Float.valueOf(texto_costoPorKm.getText());
				if(!texto_capacidad.getText().isEmpty())
					capacidad = Float.valueOf(texto_capacidad.getText());
				boolean existe = false;
				for (int i=0; i<contenedorCamiones.size(); i++) {
					if(contenedorCamiones.get(i).getIdRegistro().equals(texto_idRegistro.getText()))
						existe = true;
				}
				if(existe) {
					JOptionPane.showMessageDialog(null, "Ya existen camiones con ese ID");
					CrearCamion camion = new CrearCamion();
					camion.setVisible(true);
					dispose();
				}
				else {
					idRegistro = texto_idRegistro.getText();
					boolean seleccionCorrecta = false;
					if (rdbtnSi.isSelected() && !rdbtnNo.isSelected()) {
						esRefrigerado = true;
						seleccionCorrecta = true;
					}
					else if (rdbtnNo.isSelected() && !rdbtnSi.isSelected()) {
						esRefrigerado = false;
						seleccionCorrecta = true;
					}
					else if(!rdbtnNo.isSelected() && !rdbtnSi.isSelected()) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una opción");
						CrearCamion camion = new CrearCamion();
						camion.setVisible(true);
						dispose();
					}
					else if(rdbtnNo.isSelected() && rdbtnSi.isSelected()){
						JOptionPane.showMessageDialog(null, "Solo puede seleccionar una opción");
						CrearCamion camion = new CrearCamion();
						camion.setVisible(true);
						dispose();
					}		
					if (seleccionCorrecta && costoPorKm==null || año==null || capacidad==null || idRegistro==null || marca==null || modelo==null || dominio==null) {
						JOptionPane.showMessageDialog(null, "Complete todos los campos");
						CrearCamion camion = new CrearCamion();
						camion.setVisible(true);
						dispose();
					}
					else if(seleccionCorrecta) {
						Camion nuevoCamion = new Camion(idRegistro, marca, modelo, dominio, año, costoPorKm, capacidad, esRefrigerado);
						contenedorCamiones.add(nuevoCamion);
					
						rdbtnSi.setSelected(false);
						rdbtnNo.setSelected(false);
						texto_costoPorKm.setText("");
						texto_año.setText("");
						texto_capacidad.setText("");
						texto_idRegistro.setText("");
						texto_marca.setText("");
						texto_modelo.setText("");
						texto_dominio.setText("");
						MenuCamion camion = new MenuCamion();
						camion.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnGuardar.setBounds(286, 228, 97, 25);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamion camion = new MenuCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(397, 228, 97, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del camión");
		lblIngreseLosDatos.setBounds(22, 13, 190, 16);
		contentPane.add(lblIngreseLosDatos);
	}
}
