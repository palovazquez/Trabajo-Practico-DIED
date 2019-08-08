package isi.died.tp.estructuras;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GrafoCaminosAux extends JFrame {

	private JPanel contentPane;
	private GrafoCaminos grafoCaminos = new GrafoCaminos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrafoCaminosAux frame = new GrafoCaminosAux();
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
	public GrafoCaminosAux() {
		setTitle("Plantas con Insumo faltante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FaltaInsumo faltaInsumo = new FaltaInsumo();
				faltaInsumo.setVisible(true);
				dispose();
			}
		});
		btnAtrs.setBounds(654, 248, 97, 25);
		contentPane.add(btnAtrs);
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grafoCaminos.inicalizarVertices();
        grafoCaminos.inicalizarAristas();
        grafoCaminos.dibujarVerticesConInsumosFaltantes(g2d);
        grafoCaminos.dibujarAristas(g2d); 
	}

}
