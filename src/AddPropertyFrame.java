

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddPropertyFrame {

	JFrame frmAddProperty;
	private JTextField propertyName_txtField;
	private JLabel description_lbl;
	private JLabel propertyName_lbl_3;
	private JLabel address_lbl;
	private JTextField description_txtField;
	private JTextField location_txtField;
	private JButton next_btn;
	private int userId, propertyId;

	private int generatedKey = 0;

	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "UPDATE team002.properties SET shortname = ?,  description = ?, location = ?, breakfast = ?, isAvailable = 1 WHERE house = ? AND postcode = ?";
	private static final String SQL_QUERY = "SELECT house, postcode FROM team002.addresses ORDER BY addedTime DESC LIMIT 1";
	private static final String SQL_INIT_INSERT = "INSERT INTO team002.properties (id,userID) values (?,?)";
	private JButton addAddress_btn;
	private JLabel facilities_lbl;
	private JButton addFacilities_btn;

	private JCheckBox isBreakfast_chkbox;

	private JLabel changeBands_lbl;
	private JButton addChangeBands_btn;

	


	public AddPropertyFrame(int propertyId, int userId) {
		System.out.println("propid=" + propertyId + "  userId=" + userId);
		initialize(propertyId, userId);
		this.propertyId = propertyId;
		this.userId = userId;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int propertyId, int userId) {

		frmAddProperty = new JFrame();
		frmAddProperty.getContentPane().setBackground(Color.ORANGE);
		frmAddProperty.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 40));
		frmAddProperty.setBounds(100, 100, 1080, 720);
		frmAddProperty.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 92, 30, 0, 0, 81, 283, 430, 30, 30, 314, -49, 0, 0, 0, 0, 11, 0 };

		gridBagLayout.rowHeights = new int[] { 48, 0, 32, 33, 24, 75, 33, 0, 34, 27, 18, 0, -22, 35, 59, 0, 0, 0, 0 };

		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0 };
		frmAddProperty.getContentPane().setLayout(gridBagLayout);

		JLabel addProperty_lbl = new JLabel("Add Property");
		addProperty_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addProperty_lbl = new GridBagConstraints();
		gbc_addProperty_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addProperty_lbl.gridx = 6;
		gbc_addProperty_lbl.gridy = 1;
		frmAddProperty.getContentPane().add(addProperty_lbl, gbc_addProperty_lbl);

		JLabel propertyName_lbl = new JLabel("Property's name:");
		propertyName_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_propertyName_lbl = new GridBagConstraints();
		gbc_propertyName_lbl.anchor = GridBagConstraints.WEST;
		gbc_propertyName_lbl.fill = GridBagConstraints.VERTICAL;
		gbc_propertyName_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_propertyName_lbl.gridx = 5;
		gbc_propertyName_lbl.gridy = 3;
		frmAddProperty.getContentPane().add(propertyName_lbl, gbc_propertyName_lbl);

		propertyName_txtField = new JTextField();
		propertyName_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_propertyName_txtField = new GridBagConstraints();
		gbc_propertyName_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_propertyName_txtField.fill = GridBagConstraints.BOTH;
		gbc_propertyName_txtField.gridx = 6;
		gbc_propertyName_txtField.gridy = 3;
		frmAddProperty.getContentPane().add(propertyName_txtField, gbc_propertyName_txtField);
		propertyName_txtField.setColumns(10);
		propertyName_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted())
					next_btn.setEnabled(true);
				else
					next_btn.setEnabled(false);
			}
		});

		description_lbl = new JLabel("Description:");
		description_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_description_lbl = new GridBagConstraints();
		gbc_description_lbl.anchor = GridBagConstraints.NORTHWEST;
		gbc_description_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_description_lbl.gridx = 5;
		gbc_description_lbl.gridy = 5;
		frmAddProperty.getContentPane().add(description_lbl, gbc_description_lbl);

		description_txtField = new JTextField();
		description_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		description_txtField.setColumns(10);
		GridBagConstraints gbc_description_txtField = new GridBagConstraints();
		gbc_description_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_description_txtField.fill = GridBagConstraints.BOTH;
		gbc_description_txtField.gridx = 6;
		gbc_description_txtField.gridy = 5;
		frmAddProperty.getContentPane().add(description_txtField, gbc_description_txtField);
		description_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted())
					next_btn.setEnabled(true);
				else
					next_btn.setEnabled(false);
			}
		});

		propertyName_lbl_3 = new JLabel("Location (area):");
		propertyName_lbl_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_propertyName_lbl_3 = new GridBagConstraints();
		gbc_propertyName_lbl_3.anchor = GridBagConstraints.WEST;
		gbc_propertyName_lbl_3.insets = new Insets(0, 0, 5, 5);
		gbc_propertyName_lbl_3.gridx = 5;
		gbc_propertyName_lbl_3.gridy = 7;
		frmAddProperty.getContentPane().add(propertyName_lbl_3, gbc_propertyName_lbl_3);

		location_txtField = new JTextField();
		location_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		location_txtField.setColumns(10);
		GridBagConstraints gbc_location_txtField = new GridBagConstraints();
		gbc_location_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_location_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_location_txtField.gridx = 6;
		gbc_location_txtField.gridy = 7;
		frmAddProperty.getContentPane().add(location_txtField, gbc_location_txtField);
		location_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted())
					next_btn.setEnabled(true);
				else
					next_btn.setEnabled(false);
			}
		});

		address_lbl = new JLabel("Address:");
		address_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_address_lbl = new GridBagConstraints();
		gbc_address_lbl.anchor = GridBagConstraints.WEST;
		gbc_address_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_address_lbl.gridx = 5;
		gbc_address_lbl.gridy = 9;
		frmAddProperty.getContentPane().add(address_lbl, gbc_address_lbl);

		addAddress_btn = new JButton("Add address");
		addAddress_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		addAddress_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// go to add adress frame
				AddAdressFrame window = new AddAdressFrame(1, propertyId, userId);
				window.addAdressFrame.setVisible(true);
				addFacilities_btn.setEnabled(true);
				addChangeBands_btn.setEnabled(true);
			}
		});
		GridBagConstraints gbc_addAddress_btn = new GridBagConstraints();
		gbc_addAddress_btn.insets = new Insets(0, 0, 5, 5);
		gbc_addAddress_btn.gridx = 6;
		gbc_addAddress_btn.gridy = 9;
		frmAddProperty.getContentPane().add(addAddress_btn, gbc_addAddress_btn);

		facilities_lbl = new JLabel("Facilities: ");
		facilities_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_facilities_lbl = new GridBagConstraints();
		gbc_facilities_lbl.anchor = GridBagConstraints.WEST;
		gbc_facilities_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_facilities_lbl.gridx = 5;
		gbc_facilities_lbl.gridy = 11;
		frmAddProperty.getContentPane().add(facilities_lbl, gbc_facilities_lbl);

		addFacilities_btn = new JButton("Add facilities");
		addFacilities_btn.setEnabled(false);
		addFacilities_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacilityFrame window = new FacilityFrame(propertyId);
				window.faciltyFrame.setVisible(true);
			}
		});
		addFacilities_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_addFacilities_btn = new GridBagConstraints();
		gbc_addFacilities_btn.insets = new Insets(0, 0, 5, 5);
		gbc_addFacilities_btn.gridx = 6;
		gbc_addFacilities_btn.gridy = 11;
		frmAddProperty.getContentPane().add(addFacilities_btn, gbc_addFacilities_btn);

		next_btn = new JButton("Next");
		next_btn.setEnabled(false);
		next_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String isBreakfastString = String.valueOf(isBreakfast_chkbox.isSelected() ? 1 : 0);

				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
						PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY);) {

					// printVar();

					ResultSet rs2 = stmt2.executeQuery();
					String house = "", postcode = "";
					if (rs2.next()) {
						house = rs2.getString("house");
						postcode = rs2.getString("postcode");
					}
					System.out.println("prop id din : " + propertyId);
					stmt.setString(1, propertyName_txtField.getText());
					stmt.setString(2, description_txtField.getText());
					stmt.setString(3, location_txtField.getText());
					stmt.setString(4, isBreakfastString);
					stmt.setString(5, house);
					stmt.setString(6, postcode);
					
					stmt.executeUpdate();
					ResultSet rs = stmt.getGeneratedKeys();

					if (rs.next()) {
						generatedKey = rs.getInt(1);
					}

					System.out.println("Inserted record's ID: " + generatedKey);
					frmAddProperty.dispose();
					HostMenuFrame window = new HostMenuFrame(userId);
					window.hostMenuFrame.setVisible(true);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		changeBands_lbl = new JLabel("Change bands:");
		changeBands_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_changeBands_lbl = new GridBagConstraints();
		gbc_changeBands_lbl.anchor = GridBagConstraints.WEST;
		gbc_changeBands_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_changeBands_lbl.gridx = 5;
		gbc_changeBands_lbl.gridy = 13;
		frmAddProperty.getContentPane().add(changeBands_lbl, gbc_changeBands_lbl);
		
		addChangeBands_btn = new JButton("Add change bands");
		addChangeBands_btn.setEnabled(false);
		addChangeBands_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddChargeBandsFrame window = new AddChargeBandsFrame(propertyId);
				window.frmAddChargeBands.setVisible(true);
			}
		});
		addChangeBands_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_addChangeBands_btn = new GridBagConstraints();
		gbc_addChangeBands_btn.insets = new Insets(0, 0, 5, 5);
		gbc_addChangeBands_btn.gridx = 6;
		gbc_addChangeBands_btn.gridy = 13;
		frmAddProperty.getContentPane().add(addChangeBands_btn, gbc_addChangeBands_btn);

				
				isBreakfast_chkbox = new JCheckBox("Breakfast included:  ");
				isBreakfast_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
				isBreakfast_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
				isBreakfast_chkbox.setBackground(Color.ORANGE);
				GridBagConstraints gbc_isBreakfast_chkbox = new GridBagConstraints();
				gbc_isBreakfast_chkbox.anchor = GridBagConstraints.WEST;
				gbc_isBreakfast_chkbox.insets = new Insets(0, 0, 5, 5);
				gbc_isBreakfast_chkbox.gridx = 5;
				gbc_isBreakfast_chkbox.gridy = 14;
				frmAddProperty.getContentPane().add(isBreakfast_chkbox, gbc_isBreakfast_chkbox);
		
				
		next_btn.setFont(new Font("Tahoma", Font.BOLD, 25));

		GridBagConstraints gbc_next_btn = new GridBagConstraints();
		gbc_next_btn.insets = new Insets(0, 0, 5, 5);
		gbc_next_btn.gridx = 6;
		gbc_next_btn.gridy = 16;
		frmAddProperty.getContentPane().add(next_btn, gbc_next_btn);
		
		

	}

	private boolean areAllCompleted() {
		boolean ok = false;
		if (propertyName_txtField.getText().length() == 0 || description_txtField.getText().length() == 0
				|| location_txtField.getText().length() == 0) {
			ok = false;
		} else {
			ok = true;
		}

		return ok;
	}
	
	

}
