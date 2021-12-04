
import java.awt.EventQueue;

import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPropertyFrame {

	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.bookings (userID, propertyID, startDate, endDate, status) VALUES (?,?,?,?,?)";
	private static final String SQL_QUERY_SEARCH1 = "SELECT id from team002.bookings where status=1 AND NOT(((startDate NOT BETWEEN ? AND ? ) AND (endDate NOT BETWEEN ? AND ?) AND((startDate < ? AND endDate <?) OR (startDate > ? AND endDate > ?)))) AND propertyID=? ";
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
	JFrame frmViewProperty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPropertyFrame window = new ViewPropertyFrame(23, 0, 0);
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
	public ViewPropertyFrame(int propertyID, int fromWhere, int userID) {
		initialize(propertyID, fromWhere, userID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int propertyID, int fromWhere, int userID) {
		frmViewProperty = new JFrame();
		frmViewProperty.getContentPane().setBackground(Color.ORANGE);
		frmViewProperty.getContentPane().setLayout(null);

		JButton book_btn = new JButton("BOOK");
		book_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fromWhere == 0) {
					LoginFrame window = new LoginFrame(0, propertyID);
					window.frmLogin.setVisible(true);
					frmViewProperty.dispose();
				} else {
					// calendar
					System.out.println("calendar");
					JLabel label = new JLabel("Start date:");
					label.setFont(new Font("Tahoma", Font.PLAIN, 25));
					JButton b = new JButton("Show calendar");
					b.setFont(new Font("Tahoma", Font.PLAIN, 20));
					JPanel p = new JPanel();
					p.add(label);
					p.add(b);
					final JFrame f = new JFrame();
					f.getContentPane().add(p);
					f.pack();
					f.setVisible(true);
					b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							String startDate = new DatePicker(f).setPickedDate();
							f.dispose();
							JLabel label1 = new JLabel("End date:");
							label1.setFont(new Font("Tahoma", Font.PLAIN, 25));
							JButton b1 = new JButton("Show calendar");
							b1.setFont(new Font("Tahoma", Font.PLAIN, 20));
							JPanel p1 = new JPanel();
							p1.add(label1);
							p1.add(b1);
							final JFrame f1 = new JFrame();
							f1.getContentPane().add(p1);
							f1.pack();
							f1.setVisible(true);
							b1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									String endDate = new DatePicker(f1).setPickedDate();
									// -----
									f1.dispose();
									try (Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
											PreparedStatement stmt0 = conn1.prepareStatement(SQL_QUERY_SEARCH1);
											PreparedStatement stmt1 = conn1.prepareStatement(SQL_INSERT);) {

										stmt0.setString(1, startDate);
										stmt0.setString(2, endDate);
										stmt0.setString(3, startDate);
										stmt0.setString(4, endDate);
										stmt0.setString(5, startDate);
										stmt0.setString(6, startDate);
										stmt0.setString(7, endDate);
										stmt0.setString(8, endDate);
										stmt0.setString(9, String.valueOf(propertyID));
										ResultSet rs0 = stmt0.executeQuery();

										if (rs0.next()) {
											JOptionPane.showMessageDialog(null, "Unavailable Date");
											System.out.println(rs0.getString("propertyID"));
										} else {
											stmt1.setString(1, String.valueOf(userID));
											stmt1.setString(2, String.valueOf(propertyID));
											stmt1.setString(3, startDate);
											stmt1.setString(4, endDate);
											stmt1.setString(5, String.valueOf(0));

											stmt1.executeUpdate();

											System.out.println("start date " + startDate + "   end date " + endDate);
											frmViewProperty.dispose();
											GuestMenuFrame window = new GuestMenuFrame(userID);
											window.guestMenuframe.setVisible(true);
											JOptionPane.showMessageDialog(null, "Book request submited");
										}

									}

									catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

								}
							});
						}
					});

				}
			}
		});
		book_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		book_btn.setBounds(66, 35, 144, 48);
		frmViewProperty.getContentPane().add(book_btn);

		JButton back_btn = new JButton("BACK");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fromWhere == 0) {
					EnquirerMenuFrame window = new EnquirerMenuFrame();
					window.enquirerMenuFrame.setVisible(true);
					frmViewProperty.dispose();
				} else {
					// show guest menu
					GuestMenuFrame window = new GuestMenuFrame(userID);
					window.guestMenuframe.setVisible(true);
					frmViewProperty.dispose();
				}
			}
		});
		back_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		back_btn.setBounds(857, 35, 144, 48);
		frmViewProperty.getContentPane().add(back_btn);

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

				// getting the sleeping facilities
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
					sleepingString = sleepingString.substring(0, sleepingString.length() - 2);
					System.out.println(sleepingString);

					JLabel sleepingList_lbl = new JLabel(sleepingString);
					sleepingList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					sleepingList_lbl.setBounds(340, 171, 716, 35);
					frmViewProperty.getContentPane().add(sleepingList_lbl);

					// getting bedrooms details
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
					bedroomString = bedroomString.substring(0, bedroomString.length() - 2);
					System.out.println(bedroomString);

					JScrollPane scrollPane_1 = new JScrollPane();
					scrollPane_1.setBounds(72, 222, 984, 58);
					frmViewProperty.getContentPane().add(scrollPane_1);

					JTextArea bedroom_lbl = new JTextArea();
					scrollPane_1.setViewportView(bedroom_lbl);
					bedroom_lbl.setBackground(Color.ORANGE);
					bedroom_lbl.setText(bedroomString);
					bedroom_lbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
					bedroom_lbl.setEditable(false);
				}

				// getting the bathing facilities
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
					bathingString = bathingString.substring(0, bathingString.length() - 2);
					System.out.println(bathingString);

					JLabel bathingList_lbl = new JLabel(bathingString);
					bathingList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					bathingList_lbl.setBounds(350, 305, 716, 35);
					frmViewProperty.getContentPane().add(bathingList_lbl);

					// getting bathroom details
					stmtBathrooms.setString(1, String.valueOf(bathingID));
					ResultSet rsBathrooms = stmtBathrooms.executeQuery();
					rsmd = rsBathrooms.getMetaData();
					columnsNumber = rsmd.getColumnCount();

					int bathroomCount = 0;
					String bathroomString = "";
					while (rsBathrooms.next()) {
						bathroomCount++;
						bathroomString += ("Bathroom " + bathroomCount + " facilities: ");
						for (int i = 1; i < columnsNumber; i++) {
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
							bathroomString = bathroomString.substring(0, bathroomString.length() - 2);
							bathroomString += " (shared with the host); ";
						}
					}
					bathroomString = bathroomString.substring(0, bathroomString.length() - 2);
					System.out.println(bathroomString);
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(69, 355, 987, 65);
					frmViewProperty.getContentPane().add(scrollPane);

					JTextArea bathroom_lbl = new JTextArea();
					bathroom_lbl.setBackground(Color.ORANGE);
					bathroom_lbl.setText(bathroomString);
					bathroom_lbl.setEditable(false);
					bathroom_lbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
					scrollPane.setViewportView(bathroom_lbl);

				}

				// getting the kitchen facilities
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
					kitchenString = kitchenString.substring(0, kitchenString.length() - 2);
					System.out.println(kitchenString);

					JLabel kitchenList_lbl = new JLabel(kitchenString);
					kitchenList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					kitchenList_lbl.setBounds(179, 428, 861, 35);
					frmViewProperty.getContentPane().add(kitchenList_lbl);
				}

				// getting the living facilities
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
					livingString = livingString.substring(0, livingString.length() - 2);
					System.out.println(livingString);

					JLabel livingList_lbl = new JLabel(livingString);
					livingList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					livingList_lbl.setBounds(239, 490, 801, 35);
					frmViewProperty.getContentPane().add(livingList_lbl);
				}

				// getting the outdoor facilities
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
					outdoorString = outdoorString.substring(0, outdoorString.length() - 2);
					System.out.println(outdoorString);

					JLabel outdoorList_lbl = new JLabel(outdoorString);
					outdoorList_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
					outdoorList_lbl.setBounds(202, 551, 838, 35);
					frmViewProperty.getContentPane().add(outdoorList_lbl);
				}

				// getting the utilities facilities
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
					utilitiesString = utilitiesString.substring(0, utilitiesString.length() - 2);
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
