package isi.died.tp.interfaces.editar;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Unidad;
import isi.died.tp.interfaces.MenuInsumo;
import isi.died.tp.interfaces.crear.CrearInsumo;
import isi.died.tp.dominio.Insumo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EditarInsumo extends JFrame {
	
	private JPanel contentPane;
	private JTextField texto_descripcion;
	private JTextField texto_id;
	private JTextField texto_costo;
	private JTextField texto_stock;
	private JTextField texto_peso;
	private static Insumo ISeleccionado;
	private JTextField texto_densidad;
	private JTextField texto_volumen;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarInsumo frame = new EditarInsumo(ISeleccionado);
					frame.setTitle("Editar Insumo");
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
	public EditarInsumo (Insumo ISeleccionado) {
	
		this.ISeleccionado = ISeleccionado;
		
		
		setTitle("Editar Insumo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos a modificar");
		lblIngreseLosDatos.setBounds(40, 11, 182, 20);
		contentPane.add(lblIngreseLosDatos);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(40, 53, 46, 14);
		contentPane.add(lblId);
		
		texto_descripcion = new JTextField();
		texto_descripcion.setBounds(118, 86, 86, 20);
		contentPane.add(texto_descripcion);
		texto_descripcion.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(40, 89, 109, 14);
		contentPane.add(lblDescripcin);
		
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
		
		texto_densidad = new JTextField();
		texto_densidad.setColumns(10);
		texto_densidad.setBounds(118, 194, 86, 20);
		contentPane.add(texto_densidad);
		
		texto_volumen = new JTextField();
		texto_volumen.setColumns(10);
		texto_volumen.setBounds(316, 194, 86, 20);
		contentPane.add(texto_volumen);
		
		JLabel lblDensidad = new JLabel("Densidad:");
		lblDensidad.setBounds(40, 197, 46, 14);
		contentPane.add(lblDensidad);
		
		JLabel lblVolumen = new JLabel("Volumen:");
		lblVolumen.setBounds(245, 197, 46, 14);
		contentPane.add(lblVolumen);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Unidad.values()));
		comboBox.setBounds(316, 123, 86, 22);
		contentPane.add(comboBox);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				boolean esRefrigeradoIncorrecto = false;
				boolean esRefrigerado = false;
				Float costo = null;
				Float peso = null;
				Integer stock = null;
				Float densidad = null;
				Float volumen = null;
				String id = texto_id.getText();
				String descripcion = texto_descripcion.getText();
				Unidad unidad = (Unidad) comboBox.getSelectedItem();
				
				if(!texto_peso.getText().isEmpty())
					peso = Float.valueOf(texto_peso.getText());
				else
					peso = Float.valueOf(ISeleccionado.getPeso());
				if(!texto_volumen.getText().isEmpty())
					volumen = Float.valueOf(texto_volumen.getText());
				else
					volumen = ISeleccionado.getVolumen();
				if(!texto_densidad.getText().isEmpty())
					densidad = Float.valueOf(texto_densidad.getText());
				else
					densidad = ISeleccionado.getDensidad();				
				if(!texto_costo.getText().isEmpty())
					costo = Float.valueOf(texto_costo.getText());
				else
					costo = ISeleccionado.getCosto();
				if(!texto_id.getText().isEmpty())
					id = (texto_id.getText());
				else
					id = ISeleccionado.getId();	
				if(!texto_stock.getText().isEmpty())
					stock = Integer.valueOf(texto_stock.getText());
				else
					stock = ISeleccionado.getStock();
				if (rdbtnS.isSelected() && !(rdbtnNo.isSelected())) 
					 esRefrigerado = true;
				else if (rdbtnNo.isSelected() && !(rdbtnS.isSelected()))
					esRefrigerado = false;
				else if(!rdbtnNo.isSelected() && !rdbtnS.isSelected())
					esRefrigerado = ISeleccionado.isEsRefrigerado();
				else {
					esRefrigeradoIncorrecto = true;
					JOptionPane.showMessageDialog(null, "Solo puede seleccionar una opción");
					EditarInsumo editarInsumo = new EditarInsumo(ISeleccionado);
					editarInsumo.setVisible(true);
					dispose();
				}			
				if(costo==null && peso==null && stock==null && id==null && descripcion==null  && densidad==null && volumen==null)
					JOptionPane.showMessageDialog(null, "Complete al menos un campo");			
				else if(!esRefrigeradoIncorrecto){
					int posicion = 0;
					boolean esLiquido = false;
					for(int i=0; i<CrearInsumo.contenedorInsumos.size(); i++) {
						if(CrearInsumo.contenedorInsumos.get(i).equals(ISeleccionado))
							posicion = i;
							esLiquido = CrearInsumo.contenedorInsumos.get(i).getEsLiquido();
					}
					
					if(esLiquido) {
						Insumo ins = new Insumo(id, descripcion, costo, stock, (densidad/(volumen/1000)), esRefrigerado, Unidad.LITRO, densidad, volumen, esLiquido ) ;
						CrearInsumo.contenedorInsumos.set(posicion, ins);}
					else {
						Insumo ins = new Insumo(id, descripcion, costo, stock, peso, esRefrigerado, unidad, 0.0f, 0.0f, esLiquido ) ;
						CrearInsumo.contenedorInsumos.set(posicion, ins);	
					}
					
					JOptionPane.showMessageDialog(null, "Actualización exitosa.");
					MenuInsumo insumo = new MenuInsumo();
					insumo.setVisible(true);
					dispose();
				}
			}
		});

		btnGuardar.setBounds(212, 241, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuInsumo insumo = new MenuInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(313, 241, 89, 23);
		contentPane.add(btnCancelar);
		
		texto_costo = new JTextField();
		texto_costo.setColumns(10);
		texto_costo.setBounds(316, 50, 86, 20);
		contentPane.add(texto_costo);
		
		texto_id = new JTextField();
		texto_id.setBounds(118, 50, 86, 20);
		contentPane.add(texto_id);
		texto_id.setColumns(10);
		
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
