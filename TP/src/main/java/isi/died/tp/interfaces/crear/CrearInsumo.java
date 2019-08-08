package isi.died.tp.interfaces.crear;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Unidad;
import isi.died.tp.interfaces.MenuCamino;
import isi.died.tp.interfaces.MenuInsumo;
import isi.died.tp.dominio.Insumo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CrearInsumo extends JFrame {
	
	private JPanel contentPane;
	private JTextField texto_id;
	private JTextField texto_descripcion;
	private JTextField texto_costo;
	private JTextField texto_stock;
	private JTextField texto_peso;
	private JTextField texto_densidad;
	private JTextField texto_Volumen;

	
	public static ArrayList<Insumo> contenedorInsumos = new ArrayList<Insumo>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearInsumo frame = new CrearInsumo();
					frame.setTitle("Crear Insumo");
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
	public CrearInsumo() {
		
		setTitle("Crear Insumo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del insumo");
		lblIngreseLosDatos.setBounds(40, 11, 182, 20);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(40, 53, 46, 14);
		contentPane.add(lblId);
		
		texto_id = new JTextField();
		texto_id.setBounds(118, 50, 86, 20);
		contentPane.add(texto_id);
		texto_id.setColumns(10);
		
		JLabel lblEsLiquido = new JLabel("¿Es líquido?");
		lblEsLiquido.setBounds(40, 190, 109, 20);
		contentPane.add(lblEsLiquido);
		
		texto_densidad = new JTextField();
		texto_densidad.setColumns(10);
		texto_densidad.setBounds(130, 221, 86, 20);
		contentPane.add(texto_densidad);
		
		JLabel lblDensidad = new JLabel("Densidad:");
		lblDensidad.setBounds(40, 221, 109, 20);
		contentPane.add(lblDensidad);
		
		texto_Volumen = new JTextField();
		texto_Volumen.setColumns(10);
		texto_Volumen.setBounds(316, 221, 86, 20);
		contentPane.add(texto_Volumen);
		
		JLabel lblVolumen = new JLabel("Volumen:");
		lblVolumen.setBounds(245, 221, 109, 20);
		contentPane.add(lblVolumen);
		
		JRadioButton rdbtnS2 = new JRadioButton("S\u00ED");
		rdbtnS2.setBounds(136, 187, 39, 23);
		contentPane.add(rdbtnS2);
		
		JRadioButton rdbtnNo2 = new JRadioButton("No");
		rdbtnNo2.setBounds(171, 187, 51, 23);
		contentPane.add(rdbtnNo2);
		
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setBounds(40, 89, 109, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(251, 53, 46, 14);
		contentPane.add(lblCosto);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(251, 89, 86, 14);
		contentPane.add(lblStock);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(40, 126, 46, 14);
		contentPane.add(lblPeso);
		
		JLabel lblesRefrigerado = new JLabel("\u00BFEs refrigerado?");
		lblesRefrigerado.setBounds(40, 163, 109, 20);
		contentPane.add(lblesRefrigerado);
		
		JRadioButton rdbtnS = new JRadioButton("S\u00ED");
		rdbtnS.setBounds(145, 161, 39, 23);
		contentPane.add(rdbtnS);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(190, 161, 51, 23);
		contentPane.add(rdbtnNo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Unidad.values()));
		comboBox.setBounds(316, 123, 86, 22);
		contentPane.add(comboBox);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean esRefrigerado = false;
				Float costo = null;
				Float densidad = null;
				Float volumen = null;
				Float peso = null;
				Integer stock = null;
				String id = texto_id.getText();
				String descripcion = texto_descripcion.getText();
				Unidad unidad = (Unidad) comboBox.getSelectedItem();
				if(!texto_costo.getText().isEmpty())
					costo = Float.valueOf(texto_costo.getText());
				if(!texto_peso.getText().isEmpty())
					peso = Float.valueOf(texto_peso.getText());
				if(!texto_stock.getText().isEmpty())
					stock = Integer.valueOf(texto_stock.getText());
				if(!texto_densidad.getText().isEmpty())
					densidad = Float.valueOf(texto_densidad.getText());
		
				if(!texto_Volumen.getText().isEmpty())
					volumen = Float.valueOf(texto_Volumen.getText());
				
				
				boolean seleccionCorrecta = false;
				boolean seleccionCorrecta2 = false;
				boolean esLiquido = false;
				boolean existe = false;
				
				for(int i=0; i<contenedorInsumos.size();i++) {
					if(contenedorInsumos.get(i).getId().equals(texto_id.getText()))
						existe = true;
				}
				if(existe) {
					JOptionPane.showMessageDialog(null, "Ya existe un insumo con ese ID.");
					CrearInsumo crearInsumo = new CrearInsumo();
					crearInsumo.setVisible(true);
					dispose();
				}
				else if (rdbtnS.isSelected() && !(rdbtnNo.isSelected())) {
					esRefrigerado = true;
					seleccionCorrecta = true;
				}
				else if (rdbtnNo.isSelected() && !(rdbtnS.isSelected())) {
					esRefrigerado = false;
					seleccionCorrecta = true;
				}
			
				if (rdbtnS2.isSelected() && !(rdbtnNo2.isSelected())) {
					esLiquido = true;
					seleccionCorrecta2 = true;
				}
				else if (rdbtnNo2.isSelected() && !(rdbtnS2.isSelected())) {
					esLiquido = false;
					seleccionCorrecta2 = true;
				}
				else if ( ( (!(rdbtnNo.isSelected()) && !(rdbtnS.isSelected()))    &&   (!(rdbtnNo2.isSelected()) && !(rdbtnS2.isSelected())) )  || (!(rdbtnNo.isSelected()) && !rdbtnS.isSelected())  || (!rdbtnNo2.isSelected() && !rdbtnS2.isSelected() ) ) {   																							
					JOptionPane.showMessageDialog(null, "Debe seleccionar una opción ");
					CrearInsumo crearInsumo = new CrearInsumo();
					crearInsumo.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Solo puede seleccionar una opción");
					CrearInsumo crearInsumo = new CrearInsumo();
					crearInsumo.setVisible(true);
					dispose();
				}
								
				if((seleccionCorrecta && seleccionCorrecta2 && !esLiquido ) && (costo==null || peso==null || stock==null || id==null || descripcion==null)) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
					CrearInsumo crearInsumo = new CrearInsumo();
					crearInsumo.setVisible(true);
					dispose();
				}
				else if((seleccionCorrecta && seleccionCorrecta2 && esLiquido ) && (costo==null || volumen==null || densidad==null || stock==null || id==null || descripcion==null)) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
					CrearInsumo crearInsumo = new CrearInsumo();
					crearInsumo.setVisible(true);
					dispose();
				}
								
				else if(seleccionCorrecta && seleccionCorrecta2) {			
					if(esLiquido) {
						Insumo nuevoInsumo = new Insumo(id, descripcion, costo, stock, (densidad*(volumen/1000)) , esRefrigerado, Unidad.LITRO, densidad, volumen, esLiquido);
						contenedorInsumos.add(nuevoInsumo);
					}
					else {
						Insumo nuevoInsumo = new Insumo(id, descripcion, costo, stock, peso , esRefrigerado, unidad, 0.0f, 0.0f, esLiquido);
						contenedorInsumos.add(nuevoInsumo);}
				
					JOptionPane.showMessageDialog(null, "Carga exitosa.");
					rdbtnS.setSelected(false);
					rdbtnNo.setSelected(false);
					texto_costo.setText(""); 
					texto_stock.setText("");
					texto_id.setText("");
					texto_peso.setText("");
					comboBox.setSelectedItem(Unidad.KILO);
					texto_descripcion.setText("");
					MenuInsumo menuInsumo = new MenuInsumo();
					menuInsumo.setVisible(true);
					dispose();
				}
			}
		});
	
		btnGuardar.setBounds(211, 263, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuInsumo insumo = new MenuInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(312, 263, 89, 23);
		contentPane.add(btnCancelar);
		
		texto_costo = new JTextField();
		texto_costo.setColumns(10);
		texto_costo.setBounds(316, 50, 86, 20);
		contentPane.add(texto_costo);
		
		texto_descripcion = new JTextField();
		texto_descripcion.setBounds(118, 86, 86, 20);
		contentPane.add(texto_descripcion);
		texto_descripcion.setColumns(10);
		
		texto_stock = new JTextField();
		texto_stock.setColumns(10);
		texto_stock.setBounds(316, 86, 86, 20);
		contentPane.add(texto_stock);
		
		JLabel label = new JLabel("Unidad de");
		label.setBounds(245, 120, 86, 14);
		contentPane.add(label);
		
		JLabel lblDeMedida = new JLabel("Medida:");
		lblDeMedida.setBounds(249, 134, 86, 14);
		contentPane.add(lblDeMedida);
		
		texto_peso = new JTextField();
		texto_peso.setColumns(10);
		texto_peso.setBounds(118, 123, 86, 20);
		contentPane.add(texto_peso);

	}
}


