package com2008project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddOutdoorFrame {

	protected JFrame frmAddOutdoor;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.outdoors (facilityID, onSiteParking, onRoadParking, paidParking, patio, barbeque) VALUES (?,?,?,?,?,?)";

	/**
	 * Create the application.
	 */
	public AddOutdoorFrame(int facilityId) {
		initialize(facilityId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int facilityId) {
		frmAddOutdoor = new JFrame();
		frmAddOutdoor.getContentPane().setEnabled(false);
		frmAddOutdoor.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 94, 148, 207, 0, 217, 0, 64, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{57, 111, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAddOutdoor.getContentPane().setLayout(gridBagLayout);
		
		JLabel addOutdoor_lbl = new JLabel("Add Outdoor Facilities");
		addOutdoor_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addOutdoor_lbl = new GridBagConstraints();
		gbc_addOutdoor_lbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_addOutdoor_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addOutdoor_lbl.gridx = 6;
		gbc_addOutdoor_lbl.gridy = 1;
		frmAddOutdoor.getContentPane().add(addOutdoor_lbl, gbc_addOutdoor_lbl);
		
		JCheckBox isOnSiteParking_chkbox = new JCheckBox("On-site parking (free) ");
		isOnSiteParking_chkbox.setVerticalAlignment(SwingConstants.BOTTOM);
		isOnSiteParking_chkbox.setBackground(new Color(255, 200, 0));
		isOnSiteParking_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isOnSiteParking_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_isOnSiteParking_chkbox = new GridBagConstraints();
		gbc_isOnSiteParking_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isOnSiteParking_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isOnSiteParking_chkbox.gridx = 6;
		gbc_isOnSiteParking_chkbox.gridy = 3;
		frmAddOutdoor.getContentPane().add(isOnSiteParking_chkbox, gbc_isOnSiteParking_chkbox);
		
		JCheckBox isOnRoadParking_chkbox = new JCheckBox("On-road parking");
		isOnRoadParking_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isOnRoadParking_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isOnRoadParking_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isOnRoadParking_chkbox = new GridBagConstraints();
		gbc_isOnRoadParking_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isOnRoadParking_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isOnRoadParking_chkbox.gridx = 6;
		gbc_isOnRoadParking_chkbox.gridy = 4;
		frmAddOutdoor.getContentPane().add(isOnRoadParking_chkbox, gbc_isOnRoadParking_chkbox);
		
		JCheckBox isPaidParking_chkbox = new JCheckBox("Paid parking ");
		isPaidParking_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isPaidParking_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isPaidParking_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isPaidParking_chkbox = new GridBagConstraints();
		gbc_isPaidParking_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isPaidParking_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isPaidParking_chkbox.gridx = 6;
		gbc_isPaidParking_chkbox.gridy = 5;
		frmAddOutdoor.getContentPane().add(isPaidParking_chkbox, gbc_isPaidParking_chkbox);
		
		JCheckBox isPatio_chkbox = new JCheckBox("Patio ");
		isPatio_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isPatio_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isPatio_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isPatio_chkbox = new GridBagConstraints();
		gbc_isPatio_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isPatio_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isPatio_chkbox.gridx = 6;
		gbc_isPatio_chkbox.gridy = 6;
		frmAddOutdoor.getContentPane().add(isPatio_chkbox, gbc_isPatio_chkbox);
		
		JCheckBox isBarbeque_chkbox = new JCheckBox("Barbeque ");
		isBarbeque_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isBarbeque_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isBarbeque_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isBarbeque_chkbox = new GridBagConstraints();
		gbc_isBarbeque_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isBarbeque_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isBarbeque_chkbox.gridx = 6;
		gbc_isBarbeque_chkbox.gridy = 7;
		frmAddOutdoor.getContentPane().add(isBarbeque_chkbox, gbc_isBarbeque_chkbox);
		
		JButton submit_btn = new JButton("Submit");
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					String isOnSiteParkingString = String.valueOf(isOnSiteParking_chkbox.isSelected() ? 1 : 0);
					String isOnRoadParkingString = String.valueOf(isOnRoadParking_chkbox.isSelected() ? 1 : 0);
					String isPaidParkingString = String.valueOf(isPaidParking_chkbox.isSelected() ? 1 : 0);
					String isPatioString = String.valueOf(isPatio_chkbox.isSelected() ? 1 : 0);
					String isBarbequeString = String.valueOf(isBarbeque_chkbox.isSelected() ? 1 : 0);
					
					stmt.setString(1, String.valueOf(facilityId));
					stmt.setString(2, isOnSiteParkingString);
					stmt.setString(3, isOnRoadParkingString);
					stmt.setString(4, isPaidParkingString);
					stmt.setString(5, isPatioString);
					stmt.setString(6, isBarbequeString);
					
					stmt.executeUpdate();
					frmAddOutdoor.dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		submit_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_submit_btn = new GridBagConstraints();
		gbc_submit_btn.anchor = GridBagConstraints.EAST;
		gbc_submit_btn.insets = new Insets(0, 0, 5, 5);
		gbc_submit_btn.gridx = 6;
		gbc_submit_btn.gridy = 9;
		frmAddOutdoor.getContentPane().add(submit_btn, gbc_submit_btn);
		frmAddOutdoor.setBounds(100, 100, 1080, 720);
		frmAddOutdoor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
