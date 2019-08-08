package isi.died.tp.parte5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.Camion;
import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.interfaces.MenuCamion;
import isi.died.tp.interfaces.MenuPlanta;
import isi.died.tp.interfaces.crear.CrearCamion;

import javax.swing.JLabel;
import javax.swing.JButton;

public class GenerarSolucion extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private static Planta plantaSeleccionada;
	private static Camion camionSeleccionado;		//ver si sirve, es el camión que seleccionaron p generar solución

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarSolucion frame = new GenerarSolucion(plantaSeleccionada, camionSeleccionado);
					frame.setTitle("Generar Solución");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GenerarSolucion(Planta plantaSeleccionada, Camion camionSeleccionado) {
		this.plantaSeleccionada = plantaSeleccionada;
		this.camionSeleccionado = camionSeleccionado;
		setTitle("Generar Solución");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCamin = new JLabel("La mejor solución para la planta " + plantaSeleccionada.getIdPlanta() + " con el camión " + camionSeleccionado.getIdRegistro() + " es la siguiente combinación: ");
		lblCamin.setBounds(10, 11, 530, 10);
		contentPane.add(lblCamin);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					MenuPlanta menuPlanta = new MenuPlanta();
					menuPlanta.setVisible(true);
					dispose();
			}
		});
		btnAtrs.setBounds(10, 259, 89, 23);
		contentPane.add(btnAtrs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 645, 216);
		contentPane.add(scrollPane);
		
		table = new JTable();
		String data[][] = {};
		String col[] = {"ID Insumo", "Peso Unidad", "Costo Unidad", "Cantidad a Transp.", "Peso Total", "Costo Total"};
		model = new DefaultTableModel(data,col);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		Float total = mostrarRdo(plantaSeleccionada.resolver(camionSeleccionado));

		JLabel lblValorTotalDe = new JLabel("VALOR TOTAL DE LOS INSUMOS A TRANSPORTAR:    $ "+total);
		lblValorTotalDe.setBounds(251, 263, 404, 14);
		contentPane.add(lblValorTotalDe);
	}
	
	public Float mostrarRdo(ArrayList<Insumo> rdos) {
		Float f = 0.0f;
		for(int i=0; i<rdos.size(); i++) {
			Insumo ins = (Insumo) rdos.get(i);
			model.insertRow(0, new Object[]{});
			model.setValueAt(ins.getId(), 0, 0);
			model.setValueAt(ins.getPeso(), 0, 1);
			model.setValueAt(ins.getCosto(), 0, 2);
			model.setValueAt(plantaSeleccionada.cantidadFaltante(ins), 0, 3);
			model.setValueAt((ins.getPeso())*(plantaSeleccionada.cantidadFaltante(ins)), 0, 4);
			model.setValueAt((ins.getCosto())*(plantaSeleccionada.cantidadFaltante(ins)), 0, 5);
			f+=(ins.getCosto())*(plantaSeleccionada.cantidadFaltante(ins));
		}
		return f;
	}
}
