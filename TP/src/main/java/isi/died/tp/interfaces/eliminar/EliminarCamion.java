package isi.died.tp.interfaces.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Camion;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.MenuCamion;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.buscar.BuscarCamion;
import isi.died.tp.interfaces.buscar.BuscarPlanta;
import isi.died.tp.interfaces.buscar.InsumosBuscados;
import isi.died.tp.interfaces.crear.CrearCamion;
import isi.died.tp.interfaces.crear.CrearPlanta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarCamion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarCamion frame = new EliminarCamion();
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
	public EliminarCamion() {
		setTitle("Eliminar Camión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblestSeguroDe = new JLabel("¿Está seguro de que desea eliminar este camión?");
		lblestSeguroDe.setBounds(53, 27, 284, 16);
		contentPane.add(lblestSeguroDe);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0; i<CrearCamion.contenedorCamiones.size(); i++) {
					Camion c = CrearCamion.contenedorCamiones.get(i);
					if(BuscarCamion.idCamion.equals(c.getIdRegistro()) && BuscarCamion.esRefrigerado && c.isAptoParaLiquidos() ||
					   BuscarCamion.idCamion.equals(c.getIdRegistro()) && !BuscarCamion.esRefrigerado && !c.isAptoParaLiquidos()) {
						CrearCamion.contenedorCamiones.remove(i);
					}
				}
				JOptionPane.showMessageDialog(null, "Eliminado exitosamente. ");
				MenuCamion menuCamion = new MenuCamion();
				menuCamion.setVisible(true);
				dispose();
			}
		});
		btnAceptar.setBounds(131, 56, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamion camion = new MenuCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(240, 56, 97, 25);
		contentPane.add(btnCancelar);
	}

}
