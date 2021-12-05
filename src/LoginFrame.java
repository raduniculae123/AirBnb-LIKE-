
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;

public class LoginFrame {

	JFrame frmLogin;
	private JLabel password_lbl;
	private JTextField email_txtField;
	private JLabel login_lbl;
	private JButton login_btn;
	private JPasswordField password_field;
	private JButton toRegister_btn;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "Select email, password from team002.users where email=? and password=?";
	private static final String SQL_GET_STATUS = "select host, guest from team002.users where email=?";
	private static final String SQL_GET_ID = "select id from team002.users where email=?";
	private static final String SQL_GET_SALT = "select salt from team002.users where email=?";
	private JButton enquirer_btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame(1, 0);
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame(int fromWhere, int properyID) {
		System.out.println("miau");
		initialize(fromWhere, properyID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int fromWhere, int properyID) {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 1080, 720);
		frmLogin.getContentPane().setBackground(Color.ORANGE);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 0, 0, 0, 139, 210, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 50, 130, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frmLogin.getContentPane().setLayout(gridBagLayout);

		login_lbl = new JLabel("Log in");
		login_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_login_lbl = new GridBagConstraints();
		gbc_login_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_login_lbl.gridx = 6;
		gbc_login_lbl.gridy = 1;
		frmLogin.getContentPane().add(login_lbl, gbc_login_lbl);

		login_btn = new JButton("Log in");
		login_btn.setEnabled(false);
		login_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

					PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);) {
					PreparedStatement stmtsalt= conn.prepareStatement(SQL_GET_SALT);
					byte[] salt = new byte[0];
					stmtsalt.setBytes(1,salt);
					System.out.println(salt);
					String password = String.valueOf(password_field.getPassword());
					String okpassword = HashAndSQLTest.passwordcheck(password);
					String hashpassword = HashAndSQLTest.hashGeneration(okpassword, salt);
					System.out.println(hashpassword);
					stmt.setString(1, email_txtField.getText());
					stmt.setString(2, String.valueOf(hashpassword));

					ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						System.out.println("OK");
						PreparedStatement stmt2 = conn.prepareStatement(SQL_GET_STATUS);
						{
							stmt2.setString(1, email_txtField.getText());
							ResultSet rs2 = stmt2.executeQuery();
							if (rs2.next()) {
								int isHost = rs2.getInt("host");
								int isGuest = rs2.getInt("guest");
								if (isHost == 1 && isGuest == 1) {
									PreparedStatement stmt3 = conn.prepareStatement(SQL_GET_ID);
									{
										stmt3.setString(1, email_txtField.getText());
										ResultSet rs3 = stmt3.executeQuery();
										if (rs3.next()) {
											int userId = ((Number) rs3.getObject(1)).intValue();
											if (fromWhere == 0) {
												ViewPropertyFrame window2 = new ViewPropertyFrame(properyID, 1, userId, 0);
												window2.frmViewProperty.setVisible(true);
											} else {
												HostMenuFrame window = new HostMenuFrame(userId);
												window.hostMenuFrame.setVisible(true);
											}
											frmLogin.dispose();
										}
									}
								} else if (isGuest == 1) {
									PreparedStatement stmt3 = conn.prepareStatement(SQL_GET_ID);
									{
										stmt3.setString(1, email_txtField.getText());
										ResultSet rs3 = stmt3.executeQuery();
										if (rs3.next()) {
											System.out.println("elo");
											int userId = ((Number) rs3.getObject(1)).intValue();
											if (fromWhere == 0) {
												ViewPropertyFrame window2 = new ViewPropertyFrame(properyID, 1, userId, 0);
												window2.frmViewProperty.setVisible(true);
											} else {
												GuestMenuFrame window = new GuestMenuFrame(userId);
												window.guestMenuframe.setVisible(true);
											}
											frmLogin.dispose();
										}
									}
								} else if (isGuest == 0) {
									PreparedStatement stmt3 = conn.prepareStatement(SQL_GET_ID);
									{
										stmt3.setString(1, email_txtField.getText());
										ResultSet rs3 = stmt3.executeQuery();
										if (rs3.next()) {
											System.out.println("elo");
											int userId = ((Number) rs3.getObject(1)).intValue();
											HostMenuFrame window = new HostMenuFrame(userId);
											window.hostMenuFrame.setVisible(true);
											frmLogin.dispose();
										}
									}
								}

							}
						}

					} else {
						System.out.println("Not OK");
					}
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		JLabel email_lbl = new JLabel("Email:");
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_email_lbl = new GridBagConstraints();
		gbc_email_lbl.anchor = GridBagConstraints.EAST;
		gbc_email_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_email_lbl.gridx = 5;
		gbc_email_lbl.gridy = 3;
		frmLogin.getContentPane().add(email_lbl, gbc_email_lbl);

		email_txtField = new JTextField();
		GridBagConstraints gbc_email_txtField = new GridBagConstraints();
		gbc_email_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_email_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_email_txtField.gridx = 6;
		gbc_email_txtField.gridy = 3;
		frmLogin.getContentPane().add(email_txtField, gbc_email_txtField);
		email_txtField.setColumns(10);
		email_txtField.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted())
					login_btn.setEnabled(true);
				else {
					login_btn.setEnabled(false);
				}
			}
		});

		JLabel password_lbl = new JLabel("Password:");
		password_lbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_password_lbl = new GridBagConstraints();
		gbc_password_lbl.anchor = GridBagConstraints.EAST;
		gbc_password_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_password_lbl.gridx = 5;
		gbc_password_lbl.gridy = 5;
		frmLogin.getContentPane().add(password_lbl, gbc_password_lbl);

		password_field = new JPasswordField();
		GridBagConstraints gbc_password_field = new GridBagConstraints();
		gbc_password_field.insets = new Insets(0, 0, 5, 5);
		gbc_password_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_password_field.gridx = 6;
		gbc_password_field.gridy = 5;
		frmLogin.getContentPane().add(password_field, gbc_password_field);
		GridBagConstraints gbc_login_btn = new GridBagConstraints();
		gbc_login_btn.insets = new Insets(0, 0, 5, 5);
		gbc_login_btn.gridx = 6;
		gbc_login_btn.gridy = 7;
		frmLogin.getContentPane().add(login_btn, gbc_login_btn);
		password_field.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted())
					login_btn.setEnabled(true);
				else {
					login_btn.setEnabled(false);
				}
			}
		});

		toRegister_btn = new JButton("Don't have an account?");
		toRegister_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toRegister_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				RegistrationFrame window = new RegistrationFrame(fromWhere, properyID);
				window.frmRegister.setVisible(true);
			}
		});
		GridBagConstraints gbc_toRegister_btn = new GridBagConstraints();
		gbc_toRegister_btn.insets = new Insets(0, 0, 5, 5);
		gbc_toRegister_btn.gridx = 6;
		gbc_toRegister_btn.gridy = 8;
		frmLogin.getContentPane().add(toRegister_btn, gbc_toRegister_btn);

		enquirer_btn = new JButton("Enquirer menu");
		enquirer_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				EnquirerMenuFrame window = new EnquirerMenuFrame();
				window.enquirerMenuFrame.setVisible(true);
			}
		});
		enquirer_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_enquirer_btn = new GridBagConstraints();
		gbc_enquirer_btn.insets = new Insets(0, 0, 0, 5);
		gbc_enquirer_btn.gridx = 6;
		gbc_enquirer_btn.gridy = 10;
		frmLogin.getContentPane().add(enquirer_btn, gbc_enquirer_btn);
		frmLogin.setBackground(Color.GRAY);
		frmLogin.setBounds(100, 100, 1080, 720);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private boolean areAllCompleted() {
		boolean ok = false;
		if (email_txtField.getText().length() == 0 || password_field.getPassword().length == 0) {
			ok = false;
		} else {
			ok = true;
		}

		return ok;
	}

	public void printVar() {
		System.out.println(email_txtField.getText());
		System.out.println(password_field.getPassword());
	}
}
