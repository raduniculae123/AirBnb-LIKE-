


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class FacilityFrame {

	JFrame faciltyFrame;
	private int numOfFacilities = 0;
	private int numOfAddedFacilities = 0;
	private int s = 1, b = 1, k = 1, l = 1, u = 1, o = 1;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";

	private static final String SQL_INSERT = "UPDATE team002.facilities SET sleeping = ?,  bathing = ?, kitchen = ?, living = ?, utility = ?, outdoors = ? WHERE propertyID = ?";
	private static final String SQL_INIT_INSERT = "INSERT INTO team002.facilities (propertyID) values (?)";
	private static final String SQL_QUERY = "SELECT MAX(id) FROM team002.facilities";

	/**
	 * Create the application.
	 */
	public FacilityFrame(int propertyId) {
		initialize(propertyId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int propertyId) {
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement stmt2 = conn.prepareStatement(SQL_INIT_INSERT, Statement.RETURN_GENERATED_KEYS);) {

			stmt2.setString(1, String.valueOf(propertyId));
			stmt2.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		faciltyFrame = new JFrame();
		faciltyFrame.getContentPane().setBackground(Color.ORANGE);
		faciltyFrame.setBounds(100, 100, 1080, 720);
		faciltyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 116, 0, 56, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 154, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 76, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		faciltyFrame.getContentPane().setLayout(gridBagLayout);

		JLabel facility_lbl = new JLabel("What facilities does it include?");
		facility_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_facility_lbl = new GridBagConstraints();
		gbc_facility_lbl.insets = new Insets(0, 0, 5, 0);
		gbc_facility_lbl.gridx = 11;
		gbc_facility_lbl.gridy = 0;
		faciltyFrame.getContentPane().add(facility_lbl, gbc_facility_lbl);

		JButton submit_btn = new JButton("Submit");
		submit_btn.setEnabled(true);
		submit_btn.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_submit_btn = new GridBagConstraints();
		gbc_submit_btn.gridx = 11;
		gbc_submit_btn.gridy = 13;
		faciltyFrame.getContentPane().add(submit_btn, gbc_submit_btn);
		areFacilitiesAdded(submit_btn);

		JCheckBox sleeping_chkBox = new JCheckBox("Sleeping");
		sleeping_chkBox.setBackground(Color.ORANGE);
		sleeping_chkBox.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_sleeping_chkBox = new GridBagConstraints();
		gbc_sleeping_chkBox.anchor = GridBagConstraints.WEST;
		gbc_sleeping_chkBox.insets = new Insets(0, 0, 5, 5);
		gbc_sleeping_chkBox.gridx = 1;
		gbc_sleeping_chkBox.gridy = 1;
		faciltyFrame.getContentPane().add(sleeping_chkBox, gbc_sleeping_chkBox);

		JButton addSleeping_btn = new JButton("Add sleeping");
		addSleeping_btn.setEnabled(false);
		addSleeping_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// To other frame
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);) {

					ResultSet rs1 = stmt2.executeQuery();
					int facilityId;
					if (rs1.next()) {
						if (rs1.getString(1) == null) {
							facilityId = 1;
						} else {
							facilityId = Integer.parseInt(rs1.getString(1));
						}
						AddSleepingFrame window = new AddSleepingFrame(facilityId);
						window.frmAddSleeping.setVisible(true);
					}

				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				numOfAddedFacilities += s;
				s = 0;
				areFacilitiesAdded(submit_btn);
			}
		});
		addSleeping_btn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_addSleeping_btn = new GridBagConstraints();
		gbc_addSleeping_btn.insets = new Insets(0, 0, 5, 0);
		gbc_addSleeping_btn.gridx = 11;
		gbc_addSleeping_btn.gridy = 1;
		faciltyFrame.getContentPane().add(addSleeping_btn, gbc_addSleeping_btn);
		sleeping_chkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (sleeping_chkBox.isSelected()) {
					addSleeping_btn.setEnabled(true);
					numOfFacilities++;
					areFacilitiesAdded(submit_btn);
				} else {
					addSleeping_btn.setEnabled(false);
					if (s == 0) {
						numOfAddedFacilities--;
					}
					s = 1;
					numOfFacilities--;
					areFacilitiesAdded(submit_btn);
				}
			}
		});

		JCheckBox bathing_chkBox = new JCheckBox("Bathing");
		bathing_chkBox.setFont(new Font("Tahoma", Font.PLAIN, 35));
		bathing_chkBox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_bathing_chkBox = new GridBagConstraints();
		gbc_bathing_chkBox.anchor = GridBagConstraints.WEST;
		gbc_bathing_chkBox.insets = new Insets(0, 0, 5, 5);
		gbc_bathing_chkBox.gridx = 1;
		gbc_bathing_chkBox.gridy = 3;
		faciltyFrame.getContentPane().add(bathing_chkBox, gbc_bathing_chkBox);

		JButton addBathing_btn = new JButton("Add bathing");
		addBathing_btn.setEnabled(false);
		addBathing_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);) {

					ResultSet rs1 = stmt2.executeQuery();
					int facilityId;
					if (rs1.next()) {
						if (rs1.getString(1) == null) {
							facilityId = 1;
						} else {
							facilityId = Integer.parseInt(rs1.getString(1));
						}
						AddBathingFrame window = new AddBathingFrame(facilityId);
						window.frmAddBathing.setVisible(true);
					}

				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				numOfAddedFacilities += b;
				b = 0;
				areFacilitiesAdded(submit_btn);
			}
		});
		addBathing_btn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_addBathing_btn = new GridBagConstraints();
		gbc_addBathing_btn.insets = new Insets(0, 0, 5, 0);
		gbc_addBathing_btn.gridx = 11;
		gbc_addBathing_btn.gridy = 3;
		faciltyFrame.getContentPane().add(addBathing_btn, gbc_addBathing_btn);
		bathing_chkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (bathing_chkBox.isSelected()) {
					addBathing_btn.setEnabled(true);
					numOfFacilities++;
					areFacilitiesAdded(submit_btn);
				} else {
					addBathing_btn.setEnabled(false);
					if (b == 0) {
						numOfAddedFacilities--;
					}
					b = 1;
					numOfFacilities--;

					areFacilitiesAdded(submit_btn);
				}
			}
		});

		JCheckBox kitchen_chkBox = new JCheckBox("Kitchen");
		kitchen_chkBox.setFont(new Font("Tahoma", Font.PLAIN, 35));
		kitchen_chkBox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_kitchen_chkBox = new GridBagConstraints();
		gbc_kitchen_chkBox.anchor = GridBagConstraints.WEST;
		gbc_kitchen_chkBox.insets = new Insets(0, 0, 5, 5);
		gbc_kitchen_chkBox.gridx = 1;
		gbc_kitchen_chkBox.gridy = 5;
		faciltyFrame.getContentPane().add(kitchen_chkBox, gbc_kitchen_chkBox);

		JButton addKitchen_btn = new JButton("Add kitchen");
		addKitchen_btn.setEnabled(false);
		addKitchen_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);) {

					ResultSet rs1 = stmt2.executeQuery();
					if (rs1.next()) {
						int facilityId = Integer.parseInt(rs1.getString(1));
						AddKitchenFrame window = new AddKitchenFrame(facilityId);
						window.frmAddKitchen.setVisible(true);
					}

				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				numOfAddedFacilities += k;
				k = 0;
				areFacilitiesAdded(submit_btn);
			}
		});
		addKitchen_btn.setEnabled(false);
		addKitchen_btn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_addKitchen_btn = new GridBagConstraints();
		gbc_addKitchen_btn.insets = new Insets(0, 0, 5, 0);
		gbc_addKitchen_btn.gridx = 11;
		gbc_addKitchen_btn.gridy = 5;
		faciltyFrame.getContentPane().add(addKitchen_btn, gbc_addKitchen_btn);
		kitchen_chkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (kitchen_chkBox.isSelected()) {
					addKitchen_btn.setEnabled(true);
					numOfFacilities++;
					areFacilitiesAdded(submit_btn);
				} else {
					addKitchen_btn.setEnabled(false);
					if (k == 0) {
						numOfAddedFacilities--;
					}
					k = 1;
					numOfFacilities--;
					areFacilitiesAdded(submit_btn);
				}
			}
		});

		JCheckBox living_chkBox = new JCheckBox("Living");
		living_chkBox.setFont(new Font("Tahoma", Font.PLAIN, 35));
		living_chkBox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_living_chkBox = new GridBagConstraints();
		gbc_living_chkBox.anchor = GridBagConstraints.WEST;
		gbc_living_chkBox.insets = new Insets(0, 0, 5, 5);
		gbc_living_chkBox.gridx = 1;
		gbc_living_chkBox.gridy = 7;
		faciltyFrame.getContentPane().add(living_chkBox, gbc_living_chkBox);

		JButton addLiving_btn = new JButton("Add living");
		addLiving_btn.setEnabled(false);
		addLiving_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);) {

					ResultSet rs1 = stmt2.executeQuery();
					if (rs1.next()) {
						int facilityId = Integer.parseInt(rs1.getString(1));
						AddLivingFrame window = new AddLivingFrame(facilityId);
						window.frmAddLiving.setVisible(true);
					}

				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				numOfAddedFacilities += l;
				l = 0;
				areFacilitiesAdded(submit_btn);
			}
		});
		addLiving_btn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_addLiving_btn = new GridBagConstraints();
		gbc_addLiving_btn.insets = new Insets(0, 0, 5, 0);
		gbc_addLiving_btn.gridx = 11;
		gbc_addLiving_btn.gridy = 7;
		faciltyFrame.getContentPane().add(addLiving_btn, gbc_addLiving_btn);
		living_chkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (living_chkBox.isSelected()) {
					addLiving_btn.setEnabled(true);
					numOfFacilities++;
					areFacilitiesAdded(submit_btn);
				} else {
					addLiving_btn.setEnabled(false);
					if (l == 0) {
						numOfAddedFacilities--;
					}
					l = 1;
					numOfFacilities--;
					areFacilitiesAdded(submit_btn);
				}
			}
		});

		JCheckBox utility_chkBox = new JCheckBox("Utility");
		utility_chkBox.setFont(new Font("Tahoma", Font.PLAIN, 35));
		utility_chkBox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_utility_chkBox = new GridBagConstraints();
		gbc_utility_chkBox.anchor = GridBagConstraints.WEST;
		gbc_utility_chkBox.insets = new Insets(0, 0, 5, 5);
		gbc_utility_chkBox.gridx = 1;
		gbc_utility_chkBox.gridy = 9;
		faciltyFrame.getContentPane().add(utility_chkBox, gbc_utility_chkBox);

		JButton addUtility_btn = new JButton("Add utility");
		addUtility_btn.setEnabled(false);
		addUtility_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);) {

					ResultSet rs1 = stmt2.executeQuery();
					if (rs1.next()) {
						int facilityId = Integer.parseInt(rs1.getString(1));
						AddUtilityFrame window = new AddUtilityFrame(facilityId);
						window.frmAddUtility.setVisible(true);
					}

				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				numOfAddedFacilities += u;
				u = 0;
				areFacilitiesAdded(submit_btn);
			}
		});
		addUtility_btn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_addUtility_btn = new GridBagConstraints();
		gbc_addUtility_btn.insets = new Insets(0, 0, 5, 0);
		gbc_addUtility_btn.gridx = 11;
		gbc_addUtility_btn.gridy = 9;
		faciltyFrame.getContentPane().add(addUtility_btn, gbc_addUtility_btn);
		utility_chkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (utility_chkBox.isSelected()) {
					addUtility_btn.setEnabled(true);
					numOfFacilities++;
					areFacilitiesAdded(submit_btn);
				} else {
					addUtility_btn.setEnabled(false);
					if (u == 0) {
						numOfAddedFacilities--;
					}
					u = 1;
					numOfFacilities--;
					areFacilitiesAdded(submit_btn);
				}
			}
		});

		JCheckBox outdoors_chkBox = new JCheckBox("Outdoors");
		outdoors_chkBox.setFont(new Font("Tahoma", Font.PLAIN, 35));
		outdoors_chkBox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_outdoors_chkBox = new GridBagConstraints();
		gbc_outdoors_chkBox.anchor = GridBagConstraints.WEST;
		gbc_outdoors_chkBox.insets = new Insets(0, 0, 5, 5);
		gbc_outdoors_chkBox.gridx = 1;
		gbc_outdoors_chkBox.gridy = 11;
		faciltyFrame.getContentPane().add(outdoors_chkBox, gbc_outdoors_chkBox);

		JButton addOutdoors_btn = new JButton("Add outdoors");
		addOutdoors_btn.setEnabled(false);
		addOutdoors_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);) {

					ResultSet rs1 = stmt2.executeQuery();
					if (rs1.next()) {
						int facilityId = Integer.parseInt(rs1.getString(1));
						AddOutdoorFrame window = new AddOutdoorFrame(facilityId);
						window.frmAddOutdoor.setVisible(true);
					}

				}

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				numOfAddedFacilities += o;
				o = 0;
				areFacilitiesAdded(submit_btn);
			}
		});
		addOutdoors_btn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_addOutdoors_btn = new GridBagConstraints();
		gbc_addOutdoors_btn.insets = new Insets(0, 0, 5, 0);
		gbc_addOutdoors_btn.gridx = 11;
		gbc_addOutdoors_btn.gridy = 11;
		faciltyFrame.getContentPane().add(addOutdoors_btn, gbc_addOutdoors_btn);
		outdoors_chkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (outdoors_chkBox.isSelected()) {
					addOutdoors_btn.setEnabled(true);
					numOfFacilities++;
					areFacilitiesAdded(submit_btn);

				} else {
					addOutdoors_btn.setEnabled(false);
					if (o == 0) {
						numOfAddedFacilities--;
					}
					o = 1;
					numOfFacilities--;
					areFacilitiesAdded(submit_btn);
				}
			}
		});
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// To next frame
				String isSleepingString = String.valueOf(sleeping_chkBox.isSelected() ? 1 : 0);
				String isBathString = String.valueOf(bathing_chkBox.isSelected() ? 1 : 0);
				String isKitchenString = String.valueOf(kitchen_chkBox.isSelected() ? 1 : 0);
				String isLivingString = String.valueOf(living_chkBox.isSelected() ? 1 : 0);
				String isUtilityString = String.valueOf(utility_chkBox.isSelected() ? 1 : 0);
				String isOutdoorsString = String.valueOf(outdoors_chkBox.isSelected() ? 1 : 0);

				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					stmt.setString(1, isSleepingString);
					stmt.setString(2, isBathString);
					stmt.setString(3, isKitchenString);
					stmt.setString(4, isLivingString);
					stmt.setString(5, isUtilityString);
					stmt.setString(6, isOutdoorsString);
					stmt.setString(7, String.valueOf(propertyId));

					stmt.executeUpdate();
					faciltyFrame.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	private void areFacilitiesAdded(JButton submit_btn) {
		if (numOfAddedFacilities == numOfFacilities) {
			submit_btn.setEnabled(true);
		} else {
			submit_btn.setEnabled(false);
		}
	}

}
