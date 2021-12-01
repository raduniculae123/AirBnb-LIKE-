package com2008project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewPropertyFrame {
	
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_QUERY_NAME = "SELECT shortName FROM team002.properties where id=?";
	private static final String SQL_QUERY_FACILITIES = "SELECT id, sleeping, bathing, kitchen, living, utility, outdoors FROM team002.facilities WHERE propertyID=?";
	private static final String SQL_QUERY_SLEEPINGS = "SELECT id, bedlinen, towels, bedroomsNr, bedsNr, sleepers FROM team002.sleepings WHERE facilityID=?";
	private static final String SQL_QUERY_BEDROOMS = "SELECT bed1Type, bed2Type, location FROM team002.bedrooms where sleepingID=?";
	private static final String SQL_QUERY_BATHINGS = "SELECT id, hairdryer, toiletpaper, shampoo, bathroomNr FROM team002.bathings WHERE facilityID=?";
	private static final String SQL_QUERY_BATHROOMS = "SELECT toilet, shower, bath, isShared FROM team002.bathrooms where bathingID=?";
	private static final String SQL_QUERY_KITCHENS = "SELECT refrigerator, microwave, stove, oven, dishwasher, pan, pot, plates, cutlery, oil, salt, pepper, sugar FROM team002.kitchens WHERE facilityID=?";
	private static final String SQL_QUERY_LIVINGS = "SELECT wifi, TV, satellite, streaming, DVDplayer, boardGames FROM team002.livings WHERE facilityID=?";
	private static final String SQL_QUERY_OUTDOORS = "SELECT onSiteParking, onRoadParking, paidParking, patio, barbeque FROM team002.outdoors WHERE facilityID=?";
	private static final String SQL_QUERY_UTILITIES = "SELECT heating, washingMachine, dryingMachine, fireExtinguisher, smokeAlarm, firstAidKit FROM team002.utilities WHERE facilityID=?";
	private JFrame frmViewProperty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPropertyFrame window = new ViewPropertyFrame(1);
					window.frmViewProperty.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewPropertyFrame(int propertyID) {
		initialize(propertyID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int propertyID) {
		frmViewProperty = new JFrame();
		frmViewProperty.getContentPane().setBackground(Color.ORANGE);
		frmViewProperty.getContentPane().setLayout(null);
		
		JLabel name_lbl = new JLabel("");
		name_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		name_lbl.setBounds(195, 0, 654, 75);
		frmViewProperty.getContentPane().add(name_lbl);
		frmViewProperty.setBounds(100, 100, 1080, 720);
		frmViewProperty.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
}
