
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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AddPropertyFrame {

	JFrame frmAddProperty;
	private JTextField propertyName_txtField;
	private JLabel description_lbl;
	private JTextArea description_txtArea;
	private JLabel propertyName_lbl_3;
	private JLabel address_lbl;
	private JTextField location_txtField;
	private JButton next_btn;
	private int userId, propertyId;

	private int generatedKey = 0;

	private static int isAddress = 0;
	private static int isCharge = 0;

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
	private JScrollPane scrollPane;

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
		frmAddProperty.getContentPane().setLayout(null);

		JLabel addProperty_lbl = new JLabel("Add Property");
		addProperty_lbl.setBounds(469, 43, 231, 49);
		addProperty_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		frmAddProperty.getContentPane().add(addProperty_lbl);

		JLabel propertyName_lbl = new JLabel("Property's name:");
		propertyName_lbl.setBounds(147, 129, 199, 31);
		propertyName_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmAddProperty.getContentPane().add(propertyName_lbl);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(430, 170, 309, 83);
		frmAddProperty.getContentPane().add(scrollPane);

		JTextArea description_txtArea = new JTextArea();
		scrollPane.setViewportView(description_txtArea);
		description_txtArea.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted())
					next_btn.setEnabled(true);
				else
					next_btn.setEnabled(false);
			}
		});

		propertyName_txtField = new JTextField();
		propertyName_txtField.setBounds(430, 129, 309, 31);
		propertyName_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmAddProperty.getContentPane().add(propertyName_txtField);
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
		description_lbl.setBounds(147, 195, 141, 30);
		description_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmAddProperty.getContentPane().add(description_lbl);

		propertyName_lbl_3 = new JLabel("Location (area):");
		propertyName_lbl_3.setBounds(147, 263, 185, 31);
		propertyName_lbl_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmAddProperty.getContentPane().add(propertyName_lbl_3);

		location_txtField = new JTextField();
		location_txtField.setBounds(430, 263, 309, 31);
		location_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		location_txtField.setColumns(10);
		frmAddProperty.getContentPane().add(location_txtField);
		location_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted())
					next_btn.setEnabled(true);
				else
					next_btn.setEnabled(false);
			}
		});

		address_lbl = new JLabel("Address:");
		address_lbl.setBounds(147, 337, 102, 31);
		address_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmAddProperty.getContentPane().add(address_lbl);

		addAddress_btn = new JButton("Add address");
		addAddress_btn.setBounds(469, 333, 231, 39);
		addAddress_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		addAddress_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// go to add adress frame
				AddAdressFrame window = new AddAdressFrame(1, propertyId, userId);
				window.addAdressFrame.setVisible(true);
				addFacilities_btn.setEnabled(true);
				addChangeBands_btn.setEnabled(true);
				isAddress = 1;
				if (areAllCompleted())
					next_btn.setEnabled(true);
				else
					next_btn.setEnabled(false);
				System.out.println("add " + isAddress);
			}
		});
		frmAddProperty.getContentPane().add(addAddress_btn);

		facilities_lbl = new JLabel("Facilities: ");
		facilities_lbl.setBounds(147, 411, 111, 31);
		facilities_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmAddProperty.getContentPane().add(facilities_lbl);

		addFacilities_btn = new JButton("Add facilities");
		addFacilities_btn.setBounds(469, 407, 231, 39);
		addFacilities_btn.setEnabled(false);
		addFacilities_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacilityFrame window = new FacilityFrame(propertyId);
				window.faciltyFrame.setVisible(true);
			}
		});
		addFacilities_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmAddProperty.getContentPane().add(addFacilities_btn);

		next_btn = new JButton("Next");
		next_btn.setBounds(539, 584, 116, 39);
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
					stmt.setString(2, description_txtArea.getText());
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
		changeBands_lbl.setBounds(147, 455, 172, 31);
		changeBands_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmAddProperty.getContentPane().add(changeBands_lbl);

		addChangeBands_btn = new JButton("Add change bands");
		addChangeBands_btn.setBounds(451, 451, 267, 39);
		addChangeBands_btn.setEnabled(false);
		addChangeBands_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddChargeBandsFrame window = new AddChargeBandsFrame(propertyId);
				window.frmAddChargeBands.setVisible(true);
				isCharge = 1;
				if (areAllCompleted())
					next_btn.setEnabled(true);
				else
					next_btn.setEnabled(false);
				System.out.println("charge " + isCharge);
			}
		});
		addChangeBands_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmAddProperty.getContentPane().add(addChangeBands_btn);

		isBreakfast_chkbox = new JCheckBox("Breakfast included:  ");
		isBreakfast_chkbox.setBounds(147, 502, 275, 39);
		isBreakfast_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isBreakfast_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		isBreakfast_chkbox.setBackground(Color.ORANGE);
		frmAddProperty.getContentPane().add(isBreakfast_chkbox);

		next_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		frmAddProperty.getContentPane().add(next_btn);

	}

	private boolean areAllCompleted() {
		boolean ok = false;
			if (propertyName_txtField.getText().length() == 0 || location_txtField.getText().length() == 0
					|| isAddress == 0 || isCharge == 0) {
				ok = false;
			} else {
				ok = true;
			}
		return ok;
	}
}
