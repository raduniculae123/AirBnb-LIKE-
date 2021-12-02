

import java.awt.EventQueue;

import javax.security.auth.callback.ConfirmationCallback;
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
import javax.swing.SwingConstants;

import java.sql.ResultSetMetaData;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class ViewPropertyFrame {
	
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_QUERY_NAME = "SELECT shortName FROM team002.properties where id=?";
	private static final String SQL_QUERY_FACILITIES = "SELECT id, sleeping, bathing, kitchen, living, utility, outdoors FROM team002.facilities WHERE propertyID=?";
	private static final String SQL_QUERY_SLEEPINGS = "SELECT id, bedlinen, towels, bedroomNr, bedsNr, sleepers FROM team002.sleepings WHERE facilityID=?";
	private static final String SQL_QUERY_BEDROOMS = "SELECT bed1Type, bed2Type FROM team002.bedrooms where sleepingID=?";
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
					ViewPropertyFrame window = new ViewPropertyFrame(23);
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
		
		
		
		frmViewProperty.setBounds(100, 100, 1080, 720);
		frmViewProperty.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement stmtName = conn.prepareStatement(SQL_QUERY_NAME);
				PreparedStatement stmtFacilities = conn.prepareStatement(SQL_QUERY_FACILITIES);
				PreparedStatement stmtSleepings = conn.prepareStatement(SQL_QUERY_SLEEPINGS);
				PreparedStatement stmtBedrooms = conn.prepareStatement(SQL_QUERY_BEDROOMS);
				PreparedStatement stmtBathings = conn.prepareStatement(SQL_QUERY_BATHINGS);
				PreparedStatement stmtBathrooms = conn.prepareStatement(SQL_QUERY_BATHROOMS);
				PreparedStatement stmtKitchens = conn.prepareStatement(SQL_QUERY_KITCHENS);
				PreparedStatement stmtLivings = conn.prepareStatement(SQL_QUERY_LIVINGS);
				PreparedStatement stmtOutdoors = conn.prepareStatement(SQL_QUERY_OUTDOORS);
				PreparedStatement stmtUtilities = conn.prepareStatement(SQL_QUERY_UTILITIES);) {

			// setting the name of the property
			stmtName.setString(1, String.valueOf(propertyID));
			ResultSet rsName = stmtName.executeQuery();
			if (rsName.next()) {
				JLabel name_lbl = new JLabel(rsName.getString("shortName"));
				name_lbl.setHorizontalAlignment(SwingConstants.CENTER);
				name_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
				name_lbl.setBounds(198, 10, 654, 75);
				frmViewProperty.getContentPane().add(name_lbl);
			}
			
			// getting the list of facilities
			stmtFacilities.setString(1, String.valueOf(propertyID));
			ResultSet rsFacilities = stmtFacilities.executeQuery();
			if (rsFacilities.next()) {
				int facilityId = rsFacilities.getInt("id");
				
				JLabel facilities_lbl = new JLabel("Facilities:");
				facilities_lbl.setFont(new Font("Tahoma", Font.BOLD, 30));
				facilities_lbl.setBounds(66, 116, 146, 40);
				frmViewProperty.getContentPane().add(facilities_lbl);
				
				//getting the sleeping facilities
				stmtSleepings.setString(1, String.valueOf(facilityId));
				ResultSet rsSleepings = stmtSleepings.executeQuery();
				ResultSetMetaData rsmd = rsSleepings.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				
				if (rsSleepings.next()) {
					int sleepingID = rsSleepings.getInt("id");
					
					JLabel sleeping_lbl = new JLabel("Bedroom amenities:");
					sleeping_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
					sleeping_lbl.setBounds(66, 166, 279, 40);
					frmViewProperty.getContentPane().add(sleeping_lbl);
					
					String sleepingString = "";
					for (int i = 2; i <= columnsNumber; i++) {
						int columnValue = rsSleepings.getInt(i);
						if ((i == 2 || i == 3) && columnValue == 1) {
							sleepingString += rsmd.getColumnName(i);
						} else {
							sleepingString += rsmd.getColumnName(i);
							sleepingString += ": ";
							sleepingString += rsSleepings.getInt(i);
						}
						sleepingString += ", ";
					}
					sleepingString = sleepingString.substring(0, sleepingString.length()-2);
					System.out.println(sleepingString);
					
					JLabel sleepingList_lbl = new JLabel(sleepingString);
					sleepingList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					sleepingList_lbl.setBounds(340, 171, 716, 35);
					frmViewProperty.getContentPane().add(sleepingList_lbl);
					
					
					//getting bedrooms details
					stmtBedrooms.setString(1, String.valueOf(sleepingID));
					ResultSet rsBedrooms = stmtBedrooms.executeQuery();
					
					int bedroomCount = 0;
					String bedroomString = "";
					while (rsBedrooms.next()) {
						bedroomCount++;
						bedroomString += ("Bedroom " + bedroomCount + " bed types: ");
						bedroomString += rsBedrooms.getString("bed1Type");
						bedroomString += ", ";
						if (!rsBedrooms.getString("bed2Type").equals("Empty")) {
							bedroomString += rsBedrooms.getString("bed2Type");
							bedroomString += "; ";
						}
					}
					bedroomString = bedroomString.substring(0, bedroomString.length()-2);
					System.out.println(bedroomString);
					
					JLabel bedroomList_lbl = new JLabel(bedroomString);
					bedroomList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					bedroomList_lbl.setBounds(239, 221, 801, 35);
					frmViewProperty.getContentPane().add(bedroomList_lbl);
				}
				
				//getting the bathing facilities
				stmtBathings.setString(1, String.valueOf(facilityId));
				ResultSet rsBathings = stmtBathings.executeQuery();
				rsmd = rsBathings.getMetaData();
				columnsNumber = rsmd.getColumnCount();
				
				if (rsBathings.next()) {
					int bathingID = rsBathings.getInt("id");
					
					JLabel bathing_lbl = new JLabel("Bathroom amenities:");
					bathing_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
					bathing_lbl.setBounds(66, 300, 293, 40);
					frmViewProperty.getContentPane().add(bathing_lbl);
					
					String bathingString = "";
					for (int i = 2; i < columnsNumber; i++) {
						int columnValue = rsBathings.getInt(i);
						if (columnValue == 1) {
							bathingString += rsmd.getColumnName(i);
							bathingString += ", ";
						}
					}
					bathingString = bathingString.substring(0, bathingString.length()-2);
					System.out.println(bathingString);
					
					JLabel bathingList_lbl = new JLabel(bathingString);
					bathingList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					bathingList_lbl.setBounds(350, 305, 716, 35);
					frmViewProperty.getContentPane().add(bathingList_lbl);
					
					//getting bathroom details
					stmtBathrooms.setString(1, String.valueOf(bathingID));
					ResultSet rsBathrooms = stmtBathrooms.executeQuery();
					rsmd = rsBathrooms.getMetaData();
					columnsNumber = rsmd.getColumnCount();
					
					int bathroomCount = 0;
					String bathroomString = "";
					while (rsBathrooms.next()) {
						bathroomCount++;
						bathroomString += ("Bathroom " + bathroomCount + " facilities: ");
						for(int i = 1; i < columnsNumber; i++) {
							int columnValue = rsBathrooms.getInt(i);
							if (columnValue == 1) {
								bathroomString += rsmd.getColumnName(i);
								if (i == columnsNumber - 1) {
									bathroomString += "; ";
								} else {
									bathroomString += ", ";
								}
							}
						}
						
						if (rsBathrooms.getInt("isShared") == 1) {
							bathroomString = bathroomString.substring(0, bathroomString.length()-2);
							bathroomString += " (shared with the host); ";
						}
					}
					bathroomString = bathroomString.substring(0, bathroomString.length()-2);
					System.out.println(bathroomString);
					
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(68, 354, 988, 35);
					frmViewProperty.getContentPane().add(scrollPane);
					
					JLabel bathroomList_lbl = new JLabel(bathroomString);
					scrollPane.setViewportView(bathroomList_lbl);
					bathroomList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
				}
				
				//getting the kitchen facilities
				stmtKitchens.setString(1, String.valueOf(facilityId));
				ResultSet rsKitchens = stmtKitchens.executeQuery();
				rsmd = rsKitchens.getMetaData();
				columnsNumber = rsmd.getColumnCount();
				
				if (rsKitchens.next()) {
					JLabel kitchen_lbl = new JLabel("Kitchen:");
					kitchen_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
					kitchen_lbl.setBounds(66, 423, 127, 40);
					frmViewProperty.getContentPane().add(kitchen_lbl);
					
					String kitchenString = "";
					for (int i = 1; i <= columnsNumber; i++) {
						int columnValue = rsKitchens.getInt(i);
						if (columnValue == 1) {
							kitchenString += rsmd.getColumnName(i);
							kitchenString += ", ";
						}
					}
					kitchenString = kitchenString.substring(0, kitchenString.length()-2);
					System.out.println(kitchenString);
					
					JLabel kitchenList_lbl = new JLabel(kitchenString);
					kitchenList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					kitchenList_lbl.setBounds(179, 428, 861, 35);
					frmViewProperty.getContentPane().add(kitchenList_lbl);
				}
				
				//getting the living facilities
				stmtLivings.setString(1, String.valueOf(facilityId));
				ResultSet rsLivings = stmtLivings.executeQuery();
				rsmd = rsLivings.getMetaData();
				columnsNumber = rsmd.getColumnCount();
				
				if (rsLivings.next()) {
					JLabel living_lbl = new JLabel("Living room: ");
					living_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
					living_lbl.setBounds(66, 485, 186, 40);
					frmViewProperty.getContentPane().add(living_lbl);
					
					String livingString = "";
					for (int i = 1; i <= columnsNumber; i++) {
						int columnValue = rsLivings.getInt(i);
						if (columnValue == 1) {
							livingString += rsmd.getColumnName(i);
							livingString += ", ";
						}
					}
					livingString = livingString.substring(0, livingString.length()-2);
					System.out.println(livingString);
					
					JLabel livingList_lbl = new JLabel(livingString);
					livingList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					livingList_lbl.setBounds(239, 490, 801, 35);
					frmViewProperty.getContentPane().add(livingList_lbl);
				}
				
				//getting the outdoor facilities
				stmtOutdoors.setString(1, String.valueOf(facilityId));
				ResultSet rsOutdoors = stmtOutdoors.executeQuery();
				rsmd = rsOutdoors.getMetaData();
				columnsNumber = rsmd.getColumnCount();
				
				if (rsOutdoors.next()) {
					JLabel outdoors_lbl = new JLabel("Outdoors:");
					outdoors_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
					outdoors_lbl.setBounds(66, 546, 146, 40);
					frmViewProperty.getContentPane().add(outdoors_lbl);
					
					String outdoorString = "";
					for (int i = 1; i <= columnsNumber; i++) {
						int columnValue = rsOutdoors.getInt(i);
						if (columnValue == 1) {
							outdoorString += rsmd.getColumnName(i);
							outdoorString += ", ";
						}
					}
					outdoorString = outdoorString.substring(0, outdoorString.length()-2);
					System.out.println(outdoorString);
					
					JLabel outdoorList_lbl = new JLabel(outdoorString);
					outdoorList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					outdoorList_lbl.setBounds(202, 551, 838, 35);
					frmViewProperty.getContentPane().add(outdoorList_lbl);
				}
				
				//getting the utilities facilities
				stmtUtilities.setString(1, String.valueOf(facilityId));
				ResultSet rsUtilities = stmtUtilities.executeQuery();
				rsmd = rsUtilities.getMetaData();
				columnsNumber = rsmd.getColumnCount();
				
				if (rsUtilities.next()) {
					JLabel utilities_lbl = new JLabel("Utilities:");
					utilities_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
					utilities_lbl.setBounds(66, 610, 127, 40);
					frmViewProperty.getContentPane().add(utilities_lbl);
					
					String utilitiesString = "";
					for (int i = 1; i <= columnsNumber; i++) {
						int columnValue = rsUtilities.getInt(i);
						if (columnValue == 1) {
							utilitiesString += rsmd.getColumnName(i);
							utilitiesString += ", ";
						}
					}
					utilitiesString = utilitiesString.substring(0, utilitiesString.length()-2);
					System.out.println(utilitiesString);
					
					JLabel utilitiesList_lbl = new JLabel(utilitiesString);
					utilitiesList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					utilitiesList_lbl.setBounds(179, 615, 847, 35);
					frmViewProperty.getContentPane().add(utilitiesList_lbl);
				}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
}
