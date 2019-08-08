package isi.died.tp.interfaces.editar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.buscar.PlantasBuscadas;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarPlanta extends JFrame {

	private JPanel contentPane;
	private JTextField texto_id;
	private JTextField texto_nombre;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarPlanta frame = new EditarPlanta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditarPlanta() {
		setTitle("Editar Planta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setBounds(24, 45, 21, 14);
		contentPane.add(lblId);
		
		texto_id = new JTextField();
		texto_id.setBounds(80, 42, 99, 20);
		contentPane.add(texto_id);
		texto_id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(24, 79, 78, 14);
		contentPane.add(lblNewLabel);
		
		texto_nombre = new JTextField();
		texto_nombre.setBounds(80, 76, 99, 20);
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
		btnNewButton.setBounds(208, 109, 87, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Guardar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
					Planta p = CrearPlanta.contenedorPlantas.get(i);
					if(PlantasBuscadas.idSeleccionado.equals(p.getId()) && PlantasBuscadas.nombreSeleccionado.equals(p.getNombre())) {
						if(!texto_id.getText().isEmpty())
							p.setIdPlanta(texto_id.getText());
						if(!texto_nombre.getText().isEmpty())
							p.setNombre(texto_nombre.getText());
					}
				}
				JOptionPane.showMessageDialog(null, "Los cambios han sido guardados.");
				MenuPlanta planta = new MenuPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(109, 109, 87, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos que desea modificar");
		lblIngreseLosDatos.setBounds(25, 13, 227, 16);
		contentPane.add(lblIngreseLosDatos);
	}
}