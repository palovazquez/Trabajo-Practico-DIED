package isi.died.tp.interfaces.crear;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.Stock;
import isi.died.tp.dominio.TipoPlanta;
import isi.died.tp.dominio.Unidad;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.MenuStock;
import isi.died.tp.interfaces.buscar.BuscarPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CrearPlanta extends JFrame {

	private JPanel contentPane;
	private JTextField texto_id;
	private JTextField texto_nombre;
	
	public static ArrayList<Planta> contenedorPlantas = new ArrayList<Planta>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPlanta frame = new CrearPlanta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CrearPlanta() {
		setTitle("Crear Planta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseDatos = new JLabel("Ingrese los datos de la planta");
		lblIngreseDatos.setBounds(26, 13, 180, 20);
		contentPane.add(lblIngreseDatos);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setBounds(26, 49, 21, 14);
		contentPane.add(lblId);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(TipoPlanta.values()));
		comboBox.setBounds(82, 115, 124, 20);
		contentPane.add(comboBox);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(26, 118, 78, 14);
		contentPane.add(lblTipo);
		
		
		texto_id = new JTextField();
		texto_id.setBounds(82, 46, 124, 20);
		contentPane.add(texto_id);
		texto_id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(26, 83, 78, 14);
		contentPane.add(lblNewLabel);
		
		texto_nombre = new JTextField();
		texto_nombre.setBounds(82, 80, 124, 20);
		contentPane.add(texto_nombre);
		texto_nombre.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPlanta planta = new MenuPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(179, 151, 87, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Guardar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id =  texto_id.getText();
				String nombre = texto_nombre.getText();
				Boolean existeAI = false;
				Boolean existeAF = false;
				Boolean existe = false;
				
				for(int i=0; i<contenedorPlantas.size();i++) {
                    if(contenedorPlantas.get(i).getIdPlanta().equals(id))
                            existe = true;
                    else if(contenedorPlantas.get(i).getTipoPlanta().equals((TipoPlanta) comboBox.getSelectedItem())) {
                        if (((TipoPlanta) comboBox.getSelectedItem()).equals(TipoPlanta.FINAL)) 
                            existeAF = true;
                        else if (((TipoPlanta) comboBox.getSelectedItem()).equals(TipoPlanta.INICIAL)) 
                            existeAI = true;
                    }
                }
				
				if(!((TipoPlanta)comboBox.getSelectedItem()).equals(TipoPlanta.INICIAL) && contenedorPlantas.size()==0) {
					JOptionPane.showMessageDialog(null, "Debe crear una Planta de Acopio Inicial.");
					CrearPlanta crearPlanta = new CrearPlanta();
					crearPlanta.setVisible(true);
					dispose();
				}
				else if(!((TipoPlanta)comboBox.getSelectedItem()).equals(TipoPlanta.FINAL) && contenedorPlantas.size()==1) {
					JOptionPane.showMessageDialog(null, "Debe crear una Planta de Acopio Final.");
					CrearPlanta crearPlanta = new CrearPlanta();
					crearPlanta.setVisible(true);
					dispose();
				}
				else if(existeAI) {
					JOptionPane.showMessageDialog(null, "Ya existe una Planta de Acopio Inicial.");
					CrearPlanta crearPlanta = new CrearPlanta();
					crearPlanta.setVisible(true);
					dispose();
				}else if(existeAF) {
					JOptionPane.showMessageDialog(null, "Ya existe una Planta de Acopio Final.");
					CrearPlanta crearPlanta = new CrearPlanta();
					crearPlanta.setVisible(true);
					dispose();
				}else if(existe) {
					JOptionPane.showMessageDialog(null, "Ya existe una planta con ese id.");
					CrearPlanta crearPlanta = new CrearPlanta();
					crearPlanta.setVisible(true);
					dispose();
				}
				else if(id.isEmpty() || nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
					CrearPlanta crearPlanta = new CrearPlanta();
					crearPlanta.setVisible(true);
					dispose();}
				else {
					Planta nuevaPlanta = new Planta(id, nombre, (TipoPlanta) comboBox.getSelectedItem());
					contenedorPlantas.add(nuevaPlanta);
					
					JOptionPane.showMessageDialog(null, "Carga exitosa. ");
					texto_id.setText("");
					texto_nombre.setText("");
					MenuPlanta mp = new MenuPlanta();
					mp.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_1.setBounds(82, 151, 87, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
}