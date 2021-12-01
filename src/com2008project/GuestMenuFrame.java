package com2008project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuestMenuFrame {

	JFrame guestMenuframe;
	private JTable properties_table;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_QUERY = "SELECT userID, shortName FROM team002.properties where isAvailable=1";
	private static final String SQL_QUERY2 = "SELECT title, forename, surname FROM team002.users where id=?";
	private static final String SQL_GET_STATUS = "select host from team002.users where id=?";
	private JButton host_btn;

	

	/**
	 * Create the application.
	 */
	public GuestMenuFrame(int userId) {
		initialize(userId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int userId) {
		guestMenuframe = new JFrame();
		guestMenuframe.setTitle("Guest menu");
		guestMenuframe.setBounds(100, 100, 1080, 720);
		guestMenuframe.getContentPane().setBackground(Color.ORANGE);
		guestMenuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guestMenuframe.setBackground(Color.ORANGE);
		guestMenuframe.getContentPane().setLayout(null);

		JScrollPane table_pane = new JScrollPane();
		table_pane.setBounds(10, 105, 1044, 565);
		guestMenuframe.getContentPane().add(table_pane);

		properties_table = new JTable();
		properties_table.setFont(new Font("Tahoma", Font.PLAIN, 25));
		table_pane.setViewportView(properties_table);
		properties_table.setBackground(new Color(255, 222, 173));

		JLabel properties_lbl = new JLabel("Available properties");
		properties_lbl.setBackground(new Color(255, 127, 80));
		properties_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		properties_lbl.setBounds(10, 11, 358, 75);
		guestMenuframe.getContentPane().add(properties_lbl);

		host_btn = new JButton("Host menu");
		host_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HostMenuFrame window = new HostMenuFrame(userId);
				window.hostMenuFrame.setVisible(true);
				guestMenuframe.dispose();
			}
		});
		host_btn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		host_btn.setEnabled(false);
		host_btn.setBounds(795, 30, 230, 45);
		guestMenuframe.getContentPane().add(host_btn);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt2 = conn.prepareStatement(SQL_GET_STATUS);) {

			stmt2.setString(1, String.valueOf(userId));
			ResultSet rs2 = stmt2.executeQuery();
			if (rs2.next()) {
				if (rs2.getString("host").equals("1")) {
					host_btn.setEnabled(true);
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		model.addColumn("Name");
		model.addColumn("Host");

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement stmt = conn.prepareStatement(SQL_QUERY);
				PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY2);) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				stmt2.setString(1, rs.getString("userID"));
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					model.addRow(new Object[] { rs.getString("shortName"), rs2.getString("title") + " "
							+ rs2.getString("surname") + " " + rs2.getString("forename") });
				}
			}
			properties_table.setModel(model);
			properties_table.setAutoResizeMode(0);
			properties_table.setRowHeight(26);

			properties_table.getColumnModel().getColumn(0).setPreferredWidth(522);
			properties_table.getColumnModel().getColumn(1).setPreferredWidth(522);

		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
