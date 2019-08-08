package isi.died.tp.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import isi.died.tp.parte5.OrdenarPageRank;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class MenuPrincipal extends JFrame {

	private JPanel menuPrincipalPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setForeground(SystemColor.inactiveCaption);
		setTitle("MENÚ PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 404);
		menuPrincipalPane = new JPanel();
		menuPrincipalPane.setBackground(SystemColor.menu);
		menuPrincipalPane.setForeground(SystemColor.info);
		menuPrincipalPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(menuPrincipalPane);
		menuPrincipalPane.setLayout(null);
		
		JButton btnInsumo = new JButton("MENÚ INSUMO");
		btnInsumo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuInsumo insumo = new MenuInsumo();
				insumo.setVisible(true);
				dispose();
			}
		});
		btnInsumo.setBounds(66, 88, 376, 44);
		menuPrincipalPane.add(btnInsumo);
		
		JButton btnPlanta = new JButton("MENÚ PLANTA");
		btnPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPlanta planta = new MenuPlanta();
				planta.setVisible(true);
				dispose();
			}
		});
		btnPlanta.setIcon(null);
		btnPlanta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPlanta.setBounds(66, 22, 376, 44);
		menuPrincipalPane.add(btnPlanta);
		
		JButton btnRuta = new JButton("MENÚ CAMINO");
		btnRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamino camino = new MenuCamino();
				camino.setVisible(true);
				dispose();
			}
		});
		btnRuta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRuta.setBounds(66, 220, 376, 44);
		menuPrincipalPane.add(btnRuta);
		
		JButton btnCamin = new JButton("MENÚ CAMIÓN");
		btnCamin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCamion camion = new MenuCamion();
				camion.setVisible(true);
				dispose();
			}
		});
		btnCamin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCamin.setBounds(66, 154, 376, 44);
		menuPrincipalPane.add(btnCamin);
		
		JButton boton_PageRank = new JButton("ORDENAR PLANTAS POR PAGE RANK");
		boton_PageRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdenarPageRank ordenarPageRank = new OrdenarPageRank();
				ordenarPageRank.setVisible(true);
				dispose();
			}
		});
		boton_PageRank.setFont(new Font("Tahoma", Font.PLAIN, 15));
		boton_PageRank.setBounds(66, 286, 376, 44);
		menuPrincipalPane.add(boton_PageRank);
	}

}
