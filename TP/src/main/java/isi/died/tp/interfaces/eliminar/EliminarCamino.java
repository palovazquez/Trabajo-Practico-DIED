package isi.died.tp.interfaces.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camino;
import isi.died.tp.dominio.Camion;
import isi.died.tp.estructuras.GrafoCaminos;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuCamion;
import isi.died.tp.interfaces.buscar.BuscarCamion;
import isi.died.tp.interfaces.buscar.CaminosBuscados;
import isi.died.tp.interfaces.buscar.InsumosBuscados;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearCamion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarCamino extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarCamino frame = new EliminarCamino();
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
	public EliminarCamino() {
		setTitle("Eliminar Camino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblestSeguroDe = new JLabel("¿Está seguro de que desea eliminar este camino?");
		lblestSeguroDe.setBounds(53, 27, 284, 16);
		contentPane.add(lblestSeguroDe);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<CrearCamino.contenedorCaminos.size(); i++) {
					Camino c = CrearCamino.contenedorCaminos.get(i);
					if(CaminosBuscados.idCaminoSeleccionado.equals(c.getIdCamino())) {
						CrearCamino.contenedorCaminos.remove(i);
						for(int j=0; j<GrafoCaminos.grafo.getAristas().size(); j++) {
							if(GrafoCaminos.grafo.getAristas().get(j).getNombre().equals(c.getIdCamino()))
								GrafoCaminos.grafo.getAristas().remove(j);							
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Eliminado exitosamente. ");
				MenuCamino menuCamino = new MenuCamino();
				menuCamino.setVisible(true);
				dispose();
			}
		});
		btnAceptar.setBounds(131, 56, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamino camino = new MenuCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(240, 56, 97, 25);
		contentPane.add(btnCancelar);
	}

}
