package isi.died.tp.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.estructuras.Arista;
import isi.died.tp.estructuras.FaltaInsumo;
import isi.died.tp.estructuras.GrafoCaminos;
import isi.died.tp.estructuras.Vertice;
import isi.died.tp.interfaces.buscar.BuscarCamino;
import isi.died.tp.interfaces.buscar.BuscarMejorCamino;
import isi.died.tp.interfaces.crear.AgregarStock;
import isi.died.tp.interfaces.crear.CrearCamino;
import isi.died.tp.interfaces.crear.CrearInsumo;
import isi.died.tp.interfaces.crear.CrearPlanta;
import isi.died.tp.parte5.FlujoMaximo;
import isi.died.tp.parte5.SeleccionarPlantas;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class MenuCamino extends JFrame {

	private JPanel rutaPane;
	private GrafoCaminos grafoCaminos = new GrafoCaminos();
	//public static Insumo itemSeleccionado = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuCamino frame = new MenuCamino();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuCamino() {
		setLocationRelativeTo(null);
		setTitle("CAMINO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 505);
		rutaPane = new JPanel();
		rutaPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(rutaPane);
		rutaPane.setLayout(null);
		
		JButton btnNewButton = new JButton("AGREGAR CAMINO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearCamino camino = new CrearCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(661, 9, 258, 36);
		rutaPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EDITAR CAMINO");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamino camino = new BuscarCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(661, 54, 258, 36);
		rutaPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("BUSCAR CAMINO");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamino camino = new BuscarCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(661, 99, 258, 36);
		rutaPane.add(btnNewButton_2);
		
		
		
		JButton verCaminos = new JButton("CONSULTAR CAMINOS ");
    	verCaminos.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent arg0) {
            SeleccionarPlantas consulta = new SeleccionarPlantas();
            consulta.setVisible(true);
            dispose();
       	 }
   		 });
     	  verCaminos.setFont(new Font("Tahoma", Font.PLAIN, 15));
     	  verCaminos.setBounds(661, 324, 258, 36);
     	  rutaPane.add(verCaminos);
     	  
     	  
     	JButton boton_FlujoMax = new JButton("CALCULAR FLUJO MÁXIMO");
	    boton_FlujoMax.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent arg0) {
				FlujoMaximo flujoMaximo = new FlujoMaximo();
				flujoMaximo.setVisible(true);
				dispose();
			}
		});
	    boton_FlujoMax.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    boton_FlujoMax.setBounds(661, 369, 258, 36);
	    rutaPane.add(boton_FlujoMax);
		
		JButton btnEliminarRuta = new JButton("ELIMINAR CAMINO");
		btnEliminarRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamino camino = new BuscarCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		btnEliminarRuta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminarRuta.setBounds(661, 144, 258, 36);
		rutaPane.add(btnEliminarRuta);
		
		JButton btnVolverAlMenu = new JButton("VOLVER AL MENÚ PRINCIPAL");
		btnVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolverAlMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolverAlMenu.setBounds(661, 414, 258, 36);
		rutaPane.add(btnVolverAlMenu);
		
		JButton btnIrAMen = new JButton("IR A MENÚ DE PLANTA");
		btnIrAMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPlanta menuPlanta = new MenuPlanta();
				menuPlanta.setVisible(true);
				dispose();
			}
		});
		btnIrAMen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIrAMen.setBounds(661, 189, 258, 36);
		rutaPane.add(btnIrAMen);
		
		JButton btnNewButton_3 = new JButton("VER INSUMOS FALTANTES");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FaltaInsumo faltaInsumo = new FaltaInsumo();
				faltaInsumo.setVisible(true);
				dispose();
			}
		});
		
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBounds(661, 234, 258, 36);
		rutaPane.add(btnNewButton_3);
		
		JButton btnBuscarMejorCamino = new JButton("BUSCAR MEJOR CAMINO");
		btnBuscarMejorCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarMejorCamino camino = new BuscarMejorCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		btnBuscarMejorCamino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarMejorCamino.setBounds(661, 279, 258, 36);
		rutaPane.add(btnBuscarMejorCamino);
		
		/*JButton verCaminos = new JButton("CONSULTAR CAMINOS ");
        verCaminos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SeleccionarPlantas consulta = new SeleccionarPlantas();
                consulta.setVisible(true);
                dispose();
            }
        });
           verCaminos.setFont(new Font("Tahoma", Font.PLAIN, 15));
           verCaminos.setBounds(661, 199, 258, 36);
           rutaPane.add(verCaminos);
		*/
	}

	public void paint(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grafoCaminos.inicalizarVertices();
        grafoCaminos.inicalizarAristas();
        grafoCaminos.dibujarVertices(g2d);
        grafoCaminos.dibujarAristas(g2d); 
	}
}


