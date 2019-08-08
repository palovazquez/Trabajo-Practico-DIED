package isi.died.tp.interfaces.editar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camion;
import isi.died.tp.interfaces.MenuCamion;
import isi.died.tp.interfaces.buscar.BuscarCamion;
import isi.died.tp.interfaces.crear.CrearCamion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCamion extends JFrame {

	private JPanel contentPane;
	private JTextField texto_idRegistro;
	private JTextField texto_marca;
	private JTextField texto_año;
	private JTextField texto_dominio;
	private JTextField texto_modelo;
	private JTextField texto_capacidad;
	private JTextField texto_costoPorKm;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCamion frame = new EditarCamion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditarCamion() {
		setTitle("Editar Camión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdRegistro = new JLabel("ID registro:");
		lblIdRegistro.setBounds(23, 22, 70, 16);
		contentPane.add(lblIdRegistro);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(23, 60, 56, 16);
		contentPane.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(252, 60, 56, 16);
		contentPane.add(lblModelo);
		
		JLabel lblAo = new JLabel("Año:");
		lblAo.setBounds(23, 98, 56, 16);
		contentPane.add(lblAo);
		
		JLabel lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setBounds(23, 136, 70, 16);
		contentPane.add(lblCapacidad);
		
		JLabel lblDominio = new JLabel("Dominio:");
		lblDominio.setBounds(252, 98, 56, 16);
		contentPane.add(lblDominio);
		
		JLabel lblCostoPorKilometro = new JLabel("Costo por Kilometro:");
		lblCostoPorKilometro.setBounds(252, 136, 132, 16);
		contentPane.add(lblCostoPorKilometro);
		
		JLabel lblesAptoPara = new JLabel("¿Es apto para líquidos?");
		lblesAptoPara.setBounds(23, 173, 143, 16);
		contentPane.add(lblesAptoPara);
		
		texto_idRegistro = new JTextField();
		texto_idRegistro.setBounds(97, 19, 116, 22);
		contentPane.add(texto_idRegistro);
		texto_idRegistro.setColumns(10);
		
		texto_marca = new JTextField();
		texto_marca.setBounds(97, 57, 116, 22);
		contentPane.add(texto_marca);
		texto_marca.setColumns(10);
		
		texto_año = new JTextField();
		texto_año.setBounds(97, 95, 116, 22);
		contentPane.add(texto_año);
		texto_año.setColumns(10);
		
		texto_dominio = new JTextField();
		texto_dominio.setBounds(379, 95, 116, 22);
		contentPane.add(texto_dominio);
		texto_dominio.setColumns(10);
		
		texto_modelo = new JTextField();
		texto_modelo.setText("");
		texto_modelo.setBounds(379, 57, 116, 22);
		contentPane.add(texto_modelo);
		texto_modelo.setColumns(10);
		
		texto_capacidad = new JTextField();
		texto_capacidad.setBounds(97, 133, 116, 22);
		contentPane.add(texto_capacidad);
		texto_capacidad.setColumns(10);
		
		texto_costoPorKm = new JTextField();
		texto_costoPorKm.setBounds(379, 133, 116, 22);
		contentPane.add(texto_costoPorKm);
		texto_costoPorKm.setColumns(10);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setBounds(162, 169, 43, 25);
		contentPane.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(209, 169, 43, 25);
		contentPane.add(rdbtnNo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0; i<CrearCamion.contenedorCamiones.size(); i++) {
					Camion c = CrearCamion.contenedorCamiones.get(i);
					if(BuscarCamion.idCamion.equals(c.getIdRegistro())) {
						if(!texto_idRegistro.getText().isEmpty())
							c.setIdRegistro(texto_idRegistro.getText());
						if(!texto_marca.getText().isEmpty())
							c.setMarca(texto_marca.getText());
						if(!texto_modelo.getText().isEmpty())
							c.setModelo(texto_modelo.getText());
						if(!texto_dominio.getText().isEmpty())
							c.setDominio(texto_dominio.getText());
						if(!texto_año.getText().isEmpty())
							c.setAño(Integer.valueOf(texto_año.getText()));
						if(!texto_capacidad.getText().isEmpty())
							c.setCapacidad(Float.valueOf(texto_capacidad.getText()));
						if(!texto_costoPorKm.getText().isEmpty())
							c.setCostoPorKm(Float.valueOf(texto_costoPorKm.getText()));
						if(!rdbtnSi.isSelected() && rdbtnNo.isSelected())
							c.setAptoParaLiquidos(false);
						else if(rdbtnSi.isSelected() && !rdbtnNo.isSelected())
							c.setAptoParaLiquidos(true);
						else if(rdbtnSi.isSelected() && rdbtnNo.isSelected()) {
							JOptionPane.showMessageDialog(null, "Ingrese una sola opcion.");
							EditarCamion editarCamion = new EditarCamion();
							editarCamion.setVisible(true);
							dispose();
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Cambios realizados correctamente.");
				MenuCamion camion = new MenuCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnGuardar.setBounds(294, 215, 97, 25);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamion camion = new MenuCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(403, 215, 97, 25);
		contentPane.add(btnCancelar);
	}
}