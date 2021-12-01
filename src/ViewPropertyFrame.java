

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
		
		JLabel name_lbl = new JLabel("ad");
		name_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		name_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		name_lbl.setBounds(198, 10, 654, 75);
		frmViewProperty.getContentPane().add(name_lbl);
		
		
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
				name_lbl.setText(rsName.getString("shortName"));
			}
			
			// getting the list of facilities
			stmtFacilities.setString(1, String.valueOf(propertyID));
			ResultSet rsFacilities = stmtFacilities.executeQuery();
			if (rsFacilities.next()) {
				int facilityId = rsFacilities.getInt("id");
				
				JLabel facilities_lbl = new JLabel("Facilities:");
				facilities_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
				facilities_lbl.setBounds(66, 126, 127, 40);
				frmViewProperty.getContentPane().add(facilities_lbl);
				
				//getting the sleeping facilities
				stmtSleepings.setString(1, String.valueOf(facilityId));
				ResultSet rsSleepings = stmtSleepings.executeQuery();
				ResultSetMetaData rsmd = rsSleepings.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				
				if (rsSleepings.next()) {
					int sleepingID = rsSleepings.getInt("id");
					
					String sleepingString = "Sleeping: ";
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
				}
				
				//getting the bathing facilities
				stmtBathings.setString(1, String.valueOf(facilityId));
				ResultSet rsBathings = stmtBathings.executeQuery();
				rsmd = rsBathings.getMetaData();
				columnsNumber = rsmd.getColumnCount();
				
				if (rsBathings.next()) {
					int bathingID = rsBathings.getInt("id");
					
					String bathingString = "Bathing: ";
					for (int i = 2; i < columnsNumber; i++) {
						int columnValue = rsBathings.getInt(i);
						if (columnValue == 1) {
							bathingString += rsmd.getColumnName(i);
							bathingString += ", ";
						}
					}
					bathingString = bathingString.substring(0, bathingString.length()-2);
					System.out.println(bathingString);
					
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
				}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
}
