package isi.died.tp.interfaces.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.interfaces.MenuInsumo;
import isi.died.tp.interfaces.crear.CrearInsumo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarInsumo extends JFrame {

	private JPanel contentPane;
	private static Insumo ISeleccionado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarInsumo frame = new EliminarInsumo(ISeleccionado);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EliminarInsumo(Insumo ISeleccionado) {
		this.ISeleccionado = ISeleccionado;
		
		setTitle("Eliminar Insumo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblestSeguroDe = new JLabel("¿Está seguro de que desea eliminar este insumo?");
		lblestSeguroDe.setBounds(53, 27, 284, 16);
		contentPane.add(lblestSeguroDe);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicion = 0;
				for(int i=0; i < CrearInsumo.contenedorInsumos.size(); i++) {
					if(CrearInsumo.contenedorInsumos.get(i).equals(ISeleccionado))
						posicion = i;
					}
				
				/////ver si dentro de remove.() va el insumo o el indice
				
				CrearInsumo.contenedorInsumos.remove(CrearInsumo.contenedorInsumos.get(posicion));
				
				JOptionPane.showMessageDialog(null, "Insumo eliminado exitosamente.");
				
				MenuInsumo insumo = new MenuInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		
		btnAceptar.setBounds(131, 56, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuInsumo insumo = new MenuInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(240, 56, 97, 25);
		contentPane.add(btnCancelar);
	}

}