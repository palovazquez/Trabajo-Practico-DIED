package isi.died.tp.interfaces.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Planta;
import isi.died.tp.estructuras.GrafoCaminos;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.MenuStock;
import isi.died.tp.interfaces.buscar.BuscarPlanta;
import isi.died.tp.interfaces.buscar.InsumosBuscados;
import isi.died.tp.interfaces.buscar.PlantasBuscadas;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarPlanta extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarPlanta frame = new EliminarPlanta();
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
	public EliminarPlanta() {
		setTitle("Eliminar Planta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblestSeguroDe = new JLabel("¿Está seguro de que desea eliminar esta planta?");
		lblestSeguroDe.setBounds(53, 27, 284, 16);
		contentPane.add(lblestSeguroDe);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<CrearPlanta.contenedorPlantas.size(); i++) {
					Planta p = CrearPlanta.contenedorPlantas.get(i);
					if(PlantasBuscadas.idSeleccionado.equals(p.getIdPlanta()) && PlantasBuscadas.nombreSeleccionado.equals(p.getNombre())) {
						CrearPlanta.contenedorPlantas.remove(i);
						for(int j=0; j<GrafoCaminos.grafo.getAristas().size(); j++) {
							if(GrafoCaminos.grafo.getVertices().get(j).getNombre().equals(p.getIdPlanta()))
								GrafoCaminos.grafo.getVertices().remove(j);							
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Eliminado exitosamente. ");
				MenuPlanta menuPlanta = new MenuPlanta();
				menuPlanta.setVisible(true);
				dispose();
			}
		});
		btnAceptar.setBounds(131, 56, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPlanta planta = new MenuPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(240, 56, 97, 25);
		contentPane.add(btnCancelar);
	}

}
