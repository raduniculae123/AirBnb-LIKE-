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

public class AddLivingFrame {

	protected JFrame frmAddLiving;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.livings (facilityID, wifi, TV, satellite, streaming, DVDplayer, boardGames) VALUES (?,?,?,?,?,?,?)";
	/**
	 * Create the application.
	 */
	public AddLivingFrame(int facilityId) {
		initialize(facilityId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int facilityId) {
		frmAddLiving = new JFrame();
		frmAddLiving.getContentPane().setEnabled(false);
		frmAddLiving.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 94, 148, 207, 0, 217, 0, 64, 0, 0, 0, 180, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{57, 111, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAddLiving.getContentPane().setLayout(gridBagLayout);
		
		JLabel addLiving_lbl = new JLabel("Add Living Facilities");
		addLiving_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addLiving_lbl = new GridBagConstraints();
		gbc_addLiving_lbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_addLiving_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addLiving_lbl.gridx = 6;
		gbc_addLiving_lbl.gridy = 1;
		frmAddLiving.getContentPane().add(addLiving_lbl, gbc_addLiving_lbl);
		
		JCheckBox isWifi_chkbox = new JCheckBox("Wi-fi ");
		isWifi_chkbox.setBackground(new Color(255, 200, 0));
		isWifi_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isWifi_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_isWifi_chkbox = new GridBagConstraints();
		gbc_isWifi_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isWifi_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isWifi_chkbox.gridx = 6;
		gbc_isWifi_chkbox.gridy = 3;
		frmAddLiving.getContentPane().add(isWifi_chkbox, gbc_isWifi_chkbox);
		
		JCheckBox isTv_chkbox = new JCheckBox("TV ");
		isTv_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isTv_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isTv_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isTv_chkbox = new GridBagConstraints();
		gbc_isTv_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isTv_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isTv_chkbox.gridx = 6;
		gbc_isTv_chkbox.gridy = 4;
		frmAddLiving.getContentPane().add(isTv_chkbox, gbc_isTv_chkbox);
		
		JCheckBox isSatellite_chkbox = new JCheckBox("Satellite ");
		isSatellite_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isSatellite_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isSatellite_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isSatellite_chkbox = new GridBagConstraints();
		gbc_isSatellite_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isSatellite_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isSatellite_chkbox.gridx = 6;
		gbc_isSatellite_chkbox.gridy = 5;
		frmAddLiving.getContentPane().add(isSatellite_chkbox, gbc_isSatellite_chkbox);
		
		JCheckBox isStreaming_chkbox = new JCheckBox("Streaming ");
		isStreaming_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isStreaming_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isStreaming_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isStreaming_chkbox = new GridBagConstraints();
		gbc_isStreaming_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isStreaming_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isStreaming_chkbox.gridx = 6;
		gbc_isStreaming_chkbox.gridy = 6;
		frmAddLiving.getContentPane().add(isStreaming_chkbox, gbc_isStreaming_chkbox);
		
		JCheckBox isDvdplayer_chkbox = new JCheckBox("DVD player ");
		isDvdplayer_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isDvdplayer_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isDvdplayer_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isDvdplayer_chkbox = new GridBagConstraints();
		gbc_isDvdplayer_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isDvdplayer_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isDvdplayer_chkbox.gridx = 6;
		gbc_isDvdplayer_chkbox.gridy = 7;
		frmAddLiving.getContentPane().add(isDvdplayer_chkbox, gbc_isDvdplayer_chkbox);
		
		JCheckBox isBoardgames_chkbox = new JCheckBox("Boardgames ");
		isBoardgames_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isBoardgames_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isBoardgames_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isBoardgames_chkbox = new GridBagConstraints();
		gbc_isBoardgames_chkbox.anchor = GridBagConstraints.WEST;
		gbc_isBoardgames_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isBoardgames_chkbox.gridx = 6;
		gbc_isBoardgames_chkbox.gridy = 8;
		frmAddLiving.getContentPane().add(isBoardgames_chkbox, gbc_isBoardgames_chkbox);
		
		JButton submit_btn = new JButton("Submit");
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					String isWifiString = String.valueOf(isWifi_chkbox.isSelected() ? 1 : 0);
					String isTvString = String.valueOf(isTv_chkbox.isSelected() ? 1 : 0);
					String isSatelliteString = String.valueOf(isSatellite_chkbox.isSelected() ? 1 : 0);
					String isStreamingString = String.valueOf(isStreaming_chkbox.isSelected() ? 1 : 0);
					String isDvdplayerString = String.valueOf(isDvdplayer_chkbox.isSelected() ? 1 : 0);
					String isBoardgamesString = String.valueOf(isBoardgames_chkbox.isSelected() ? 1 : 0);
					
					stmt.setString(1, String.valueOf(facilityId));
					stmt.setString(2, isWifiString);
					stmt.setString(3, isTvString);
					stmt.setString(4, isSatelliteString);
					stmt.setString(5, isStreamingString);
					stmt.setString(6, isDvdplayerString);
					stmt.setString(7, isBoardgamesString);
					
					stmt.executeUpdate();
					frmAddLiving.dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		submit_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_submit_btn = new GridBagConstraints();
		gbc_submit_btn.anchor = GridBagConstraints.WEST;
		gbc_submit_btn.insets = new Insets(0, 0, 5, 5);
		gbc_submit_btn.gridx = 7;
		gbc_submit_btn.gridy = 9;
		frmAddLiving.getContentPane().add(submit_btn, gbc_submit_btn);
		frmAddLiving.setBounds(100, 100, 1080, 720);
		frmAddLiving.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}