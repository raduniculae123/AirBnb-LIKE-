
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RegistrationFrame {

	JFrame frmRegister;
	private JTextField email_txtField;
	private JTextField mobileNumber_txtField;
	private JTextField forename_txtField;
	private JPasswordField password_txtField;
	private JTextField title_txtField;
	private JTextField surname_txtField;
	private JCheckBox isGuest_chkbox = new JCheckBox("Guest  ");
	private JCheckBox isHost_chkbox = new JCheckBox("Host  ");
	private int generatedKey = 0;

	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_EMAIL = "SELECT id from team002.users WHERE email = ?";
	private static final String SQL_INSERT = "INSERT INTO team002.users (title,forename,surname,email,password,mobilenumber,guest,host,house,postcode,salt) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_INSERT2 = "INSERT INTO team002.addresses (house,postcode,street,place,isProperty) VALUES (?,?,?,?,?)";
	private static final String SQL_GET = "SELECT salt FROM team002.users where id = (SELECT max(id) FROM team002.users)";
	private JTextField house_txtField;
	private JTextField street_txtField;
	private JTextField place_txtField;
	private JTextField postcode_txtField;

	/**
	 * Create the application.
	 */
	public RegistrationFrame(int fromWhere, int properyID) {
		initialize(fromWhere, properyID);
	}

	public String get_prop() {
		return email_txtField.getText();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int fromWhere, int properyID) {
		frmRegister = new JFrame();
		frmRegister.setTitle("Register");
		frmRegister.getContentPane().setBackground(Color.ORANGE);
		frmRegister.setBounds(100, 100, 1080, 720);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 0, 0, 0, 230, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 12, 38, 0, 0, 0, 0, 0, 44, 35, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		frmRegister.getContentPane().setLayout(gridBagLayout);

		JLabel register_lbl = new JLabel("Register");
		register_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_register_lbl = new GridBagConstraints();
		gbc_register_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_register_lbl.gridx = 5;
		gbc_register_lbl.gridy = 1;
		frmRegister.getContentPane().add(register_lbl, gbc_register_lbl);

		JButton register_btn = new JButton("Register");
		register_btn.setEnabled(false);
		register_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try (Connection conn4 = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt4 = conn4.prepareStatement(SQL_EMAIL);) {

					stmt4.setString(1, email_txtField.getText());
					ResultSet rs4 = stmt4.executeQuery();
					if (rs4.next()) {
						JOptionPane.showMessageDialog(null, "Email already registered");
					} else {
						if (Validators.isEmailValid(email_txtField.getText())
								&& Validators.isPasswordValid(password_txtField.getText())) {

							String isGuestString = String.valueOf(isGuest_chkbox.isSelected() ? 1 : 0);
							String isHostString = String.valueOf(isHost_chkbox.isSelected() ? 1 : 0);

							try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

									PreparedStatement stmt = conn.prepareStatement(SQL_INSERT,
											Statement.RETURN_GENERATED_KEYS);
									PreparedStatement stmt2 = conn.prepareStatement(SQL_INSERT2,
											Statement.RETURN_GENERATED_KEYS);
									PreparedStatement stmt3 = conn.prepareStatement(SQL_GET,
											Statement.RETURN_GENERATED_KEYS);) {

								String password = String.valueOf(password_txtField.getPassword());
								byte[] salt = HashAndSQLTest.salt();
								String hashpassword = HashAndSQLTest.hashGeneration(password, salt);
								stmt.setString(1, title_txtField.getText());
								stmt.setString(2, forename_txtField.getText());
								stmt.setString(3, surname_txtField.getText());
								stmt.setString(4, email_txtField.getText());
								stmt.setString(5, hashpassword);
								stmt.setString(6, mobileNumber_txtField.getText());
								stmt.setString(7, isGuestString);
								stmt.setString(8, isHostString);
								stmt.setString(9, house_txtField.getText());
								stmt.setString(10, postcode_txtField.getText());
								stmt.setString(11, new String(salt));
								stmt.executeUpdate();

								ResultSet rs = stmt.getGeneratedKeys();

								stmt2.setString(1, house_txtField.getText());
								stmt2.setString(2, postcode_txtField.getText());
								stmt2.setString(3, street_txtField.getText());
								stmt2.setString(4, place_txtField.getText());
								stmt2.setString(5, "0");
								stmt2.executeUpdate();

								if (rs.next()) {
									generatedKey = rs.getInt(1);
								}

								System.out.println("Inserted record's ID: " + generatedKey);
								frmRegister.dispose();
								LoginFrame window = new LoginFrame(fromWhere, properyID);
								window.frmLogin.setVisible(true);

							} catch (SQLException e1) {
								e1.printStackTrace();
							}

						} else {
							JOptionPane.showMessageDialog(null,
									"Email must be valid. Password must contain at least 8 character (both capital and small letters)");
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JLabel house_lbl = new JLabel("House");
		house_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_house_lbl = new GridBagConstraints();
		gbc_house_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_house_lbl.gridx = 4;
		gbc_house_lbl.gridy = 8;
		frmRegister.getContentPane().add(house_lbl, gbc_house_lbl);

		house_txtField = new JTextField();
		house_txtField.setColumns(10);
		GridBagConstraints gbc_house_txtField = new GridBagConstraints();
		gbc_house_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_house_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_house_txtField.gridx = 5;
		gbc_house_txtField.gridy = 8;
		frmRegister.getContentPane().add(house_txtField, gbc_house_txtField);

		JLabel street_lbl = new JLabel("Street");
		street_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_street_lbl = new GridBagConstraints();
		gbc_street_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_street_lbl.gridx = 4;
		gbc_street_lbl.gridy = 9;
		frmRegister.getContentPane().add(street_lbl, gbc_street_lbl);

		street_txtField = new JTextField();
		street_txtField.setColumns(10);
		GridBagConstraints gbc_street_txtField = new GridBagConstraints();
		gbc_street_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_street_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_street_txtField.gridx = 5;
		gbc_street_txtField.gridy = 9;
		frmRegister.getContentPane().add(street_txtField, gbc_street_txtField);

		JLabel place_lbl = new JLabel("Place name");
		place_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_place_lbl = new GridBagConstraints();
		gbc_place_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_place_lbl.gridx = 4;
		gbc_place_lbl.gridy = 10;
		frmRegister.getContentPane().add(place_lbl, gbc_place_lbl);

		place_txtField = new JTextField();
		place_txtField.setColumns(10);
		GridBagConstraints gbc_place_txtField = new GridBagConstraints();
		gbc_place_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_place_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_place_txtField.gridx = 5;
		gbc_place_txtField.gridy = 10;
		frmRegister.getContentPane().add(place_txtField, gbc_place_txtField);

		JLabel postcode_lbl = new JLabel("Postcode");
		postcode_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_postcode_lbl = new GridBagConstraints();
		gbc_postcode_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_postcode_lbl.gridx = 4;
		gbc_postcode_lbl.gridy = 11;
		frmRegister.getContentPane().add(postcode_lbl, gbc_postcode_lbl);

		postcode_txtField = new JTextField();
		postcode_txtField.setColumns(10);
		GridBagConstraints gbc_postcode_txtField = new GridBagConstraints();
		gbc_postcode_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_postcode_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_postcode_txtField.gridx = 5;
		gbc_postcode_txtField.gridy = 11;
		frmRegister.getContentPane().add(postcode_txtField, gbc_postcode_txtField);
		register_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_register_btn = new GridBagConstraints();
		gbc_register_btn.insets = new Insets(0, 0, 5, 5);
		gbc_register_btn.gridx = 5;
		gbc_register_btn.gridy = 14;
		frmRegister.getContentPane().add(register_btn, gbc_register_btn);

		JLabel title_lbl = new JLabel("Title");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_title_lbl = new GridBagConstraints();
		gbc_title_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_title_lbl.gridx = 4;
		gbc_title_lbl.gridy = 2;
		frmRegister.getContentPane().add(title_lbl, gbc_title_lbl);

		title_txtField = new JTextField();
		GridBagConstraints gbc_title_txtField = new GridBagConstraints();
		gbc_title_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_title_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_title_txtField.gridx = 5;
		gbc_title_txtField.gridy = 2;
		frmRegister.getContentPane().add(title_txtField, gbc_title_txtField);
		title_txtField.setColumns(10);
		title_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					register_btn.setEnabled(true);
				} else {
					register_btn.setEnabled(false);
				}
			}
		});

		JLabel email_lbl = new JLabel("Email");
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_email_lbl = new GridBagConstraints();
		gbc_email_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_email_lbl.gridx = 4;
		gbc_email_lbl.gridy = 3;
		frmRegister.getContentPane().add(email_lbl, gbc_email_lbl);

		email_txtField = new JTextField();
		GridBagConstraints gbc_email_txtField = new GridBagConstraints();
		gbc_email_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_email_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_email_txtField.gridx = 5;
		gbc_email_txtField.gridy = 3;
		frmRegister.getContentPane().add(email_txtField, gbc_email_txtField);
		email_txtField.setColumns(10);
		email_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					register_btn.setEnabled(true);
				} else {
					register_btn.setEnabled(false);
				}
			}
		});

		JLabel password_lbl = new JLabel("Password");
		password_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_password_lbl = new GridBagConstraints();
		gbc_password_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_password_lbl.gridx = 4;
		gbc_password_lbl.gridy = 4;
		frmRegister.getContentPane().add(password_lbl, gbc_password_lbl);

		password_txtField = new JPasswordField();
		GridBagConstraints gbc_password_txtField = new GridBagConstraints();
		gbc_password_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_password_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_password_txtField.gridx = 5;
		gbc_password_txtField.gridy = 4;
		frmRegister.getContentPane().add(password_txtField, gbc_password_txtField);
		password_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					register_btn.setEnabled(true);
				} else {
					register_btn.setEnabled(false);
				}
			}
		});

		JLabel mobileNumber_lbl = new JLabel("Mobile Number");
		mobileNumber_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_mobileNumber_lbl = new GridBagConstraints();
		gbc_mobileNumber_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_mobileNumber_lbl.gridx = 4;
		gbc_mobileNumber_lbl.gridy = 5;
		frmRegister.getContentPane().add(mobileNumber_lbl, gbc_mobileNumber_lbl);

		mobileNumber_txtField = new JTextField();
		mobileNumber_txtField.setColumns(10);
		GridBagConstraints gbc_mobileNumber_txtField = new GridBagConstraints();
		gbc_mobileNumber_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_mobileNumber_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_mobileNumber_txtField.gridx = 5;
		gbc_mobileNumber_txtField.gridy = 5;
		frmRegister.getContentPane().add(mobileNumber_txtField, gbc_mobileNumber_txtField);
		mobileNumber_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					register_btn.setEnabled(true);
				} else {
					register_btn.setEnabled(false);
				}
			}
		});

		JLabel forename_lbl = new JLabel("Forename ");
		forename_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_forename_lbl = new GridBagConstraints();
		gbc_forename_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_forename_lbl.gridx = 4;
		gbc_forename_lbl.gridy = 6;
		frmRegister.getContentPane().add(forename_lbl, gbc_forename_lbl);

		forename_txtField = new JTextField();
		forename_txtField.setColumns(10);
		GridBagConstraints gbc_forename_txtField = new GridBagConstraints();
		gbc_forename_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_forename_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_forename_txtField.gridx = 5;
		gbc_forename_txtField.gridy = 6;
		frmRegister.getContentPane().add(forename_txtField, gbc_forename_txtField);
		forename_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					register_btn.setEnabled(true);
				} else {
					register_btn.setEnabled(false);
				}
			}
		});

		JLabel surname_lbl = new JLabel("Surname");
		surname_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_surname_lbl = new GridBagConstraints();
		gbc_surname_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_surname_lbl.gridx = 4;
		gbc_surname_lbl.gridy = 7;
		frmRegister.getContentPane().add(surname_lbl, gbc_surname_lbl);

		surname_txtField = new JTextField();
		GridBagConstraints gbc_surname_txtField = new GridBagConstraints();
		gbc_surname_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_surname_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_surname_txtField.gridx = 5;
		gbc_surname_txtField.gridy = 7;
		frmRegister.getContentPane().add(surname_txtField, gbc_surname_txtField);
		surname_txtField.setColumns(10);
		surname_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					register_btn.setEnabled(true);
				} else {
					register_btn.setEnabled(false);
				}
			}
		});

		isGuest_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isGuest_chkbox.setBackground(Color.ORANGE);
		isGuest_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_isGuest_chkbox = new GridBagConstraints();
		gbc_isGuest_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isGuest_chkbox.gridx = 5;
		gbc_isGuest_chkbox.gridy = 12;
		frmRegister.getContentPane().add(isGuest_chkbox, gbc_isGuest_chkbox);

		isHost_chkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		isHost_chkbox.setFont(new Font("Tahoma", Font.PLAIN, 30));
		isHost_chkbox.setBackground(Color.ORANGE);
		GridBagConstraints gbc_isHost_chkbox = new GridBagConstraints();
		gbc_isHost_chkbox.insets = new Insets(0, 0, 5, 5);
		gbc_isHost_chkbox.gridx = 5;
		gbc_isHost_chkbox.gridy = 13;
		frmRegister.getContentPane().add(isHost_chkbox, gbc_isHost_chkbox);

		JButton toLogin_btn = new JButton("Already have an account?");
		toLogin_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegister.dispose();
				LoginFrame window = new LoginFrame(1, 0);
				window.frmLogin.setVisible(true);
			}
		});
		toLogin_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_toLogin_btn = new GridBagConstraints();
		gbc_toLogin_btn.insets = new Insets(0, 0, 0, 5);
		gbc_toLogin_btn.gridx = 4;
		gbc_toLogin_btn.gridy = 15;
		frmRegister.getContentPane().add(toLogin_btn, gbc_toLogin_btn);

		JButton enquirer_btn = new JButton("Enquirer menu");
		enquirer_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegister.dispose();
				EnquirerMenuFrame window = new EnquirerMenuFrame();
				window.enquirerMenuFrame.setVisible(true);
			}
		});
		enquirer_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_enquirer_btn = new GridBagConstraints();
		gbc_enquirer_btn.insets = new Insets(0, 0, 0, 5);
		gbc_enquirer_btn.gridx = 6;
		gbc_enquirer_btn.gridy = 15;
		frmRegister.getContentPane().add(enquirer_btn, gbc_enquirer_btn);

		isHost_chkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (areAllCompleted()) {
					register_btn.setEnabled(true);
				} else {
					register_btn.setEnabled(false);
				}
			}
		});

		isGuest_chkbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (areAllCompleted()) {
					register_btn.setEnabled(true);
				} else {
					register_btn.setEnabled(false);
				}
			}
		});

	}

	private boolean areAllCompleted() {
		boolean ok = false;
		if (title_txtField.getText().length() == 0 || forename_txtField.getText().length() == 0
				|| surname_txtField.getText().length() == 0 || email_txtField.getText().length() == 0
				|| password_txtField.getPassword().length == 0 || mobileNumber_txtField.getText().length() == 0
				|| (isHost_chkbox.isSelected() == false && isGuest_chkbox.isSelected() == false)) {
			ok = false;
		} else {
			ok = true;
		}

		return ok;
	}

}
