
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import java.awt.event.*;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddChargeBandsFrame {

	protected JFrame frmAddChargeBands;
	private JTextField pricePerNight_txtField;
	private JTextField serviceCharge_txtField;
	private JTextField cleaningCharge_txtField;
	private JLabel startDate_lbl;
	private JLabel endDate_lbl;
	private JButton startDate_btn;
	private JButton endDate_btn;
	private JButton addAnother_btn;
	private JButton saveChanges_btn;
	private JTextField startDate_txtField;
	private JTextField endDate_txtField;

	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.charge_bands (propertyID, startDate, endDate, pricePerNight, serviceCharge, cleaningCharge) VALUES (?,?,?,?,?,?)";

	/**
	 * Create the application.
	 */
	public AddChargeBandsFrame(int propertyId) {
		initialize(propertyId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int propertyId) {
		frmAddChargeBands = new JFrame();
		frmAddChargeBands.getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 295, 294, 0, 233, 99, 0, 0, 78, 223, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 42, 85, 51, 0, 51, 0, 47, 0, 43, 0, 40, 0, 41, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frmAddChargeBands.getContentPane().setLayout(gridBagLayout);

		JLabel addChangeBands_lbl = new JLabel("Add Change Bands");
		addChangeBands_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GridBagConstraints gbc_addChangeBands_lbl = new GridBagConstraints();
		gbc_addChangeBands_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_addChangeBands_lbl.gridx = 6;
		gbc_addChangeBands_lbl.gridy = 1;
		frmAddChargeBands.getContentPane().add(addChangeBands_lbl, gbc_addChangeBands_lbl);

		startDate_lbl = new JLabel("Start date: ");
		startDate_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_startDate_lbl = new GridBagConstraints();
		gbc_startDate_lbl.anchor = GridBagConstraints.WEST;
		gbc_startDate_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_startDate_lbl.gridx = 4;
		gbc_startDate_lbl.gridy = 3;
		frmAddChargeBands.getContentPane().add(startDate_lbl, gbc_startDate_lbl);

		startDate_txtField = new JTextField();
		startDate_txtField.setEditable(false);
		startDate_txtField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_startDate_txtField = new GridBagConstraints();
		gbc_startDate_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_startDate_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_startDate_txtField.gridx = 7;
		gbc_startDate_txtField.gridy = 3;
		frmAddChargeBands.getContentPane().add(startDate_txtField, gbc_startDate_txtField);
		startDate_txtField.setColumns(10);

		startDate_btn = new JButton("Add start date");
		startDate_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel("Selected Date:");
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
						startDate_txtField.setText(new DatePicker(f).setPickedDate());
						f.dispose();
					}
				});
			}
		});
		startDate_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_startDate_btn = new GridBagConstraints();
		gbc_startDate_btn.insets = new Insets(0, 0, 5, 5);
		gbc_startDate_btn.gridx = 6;
		gbc_startDate_btn.gridy = 3;
		frmAddChargeBands.getContentPane().add(startDate_btn, gbc_startDate_btn);

		endDate_lbl = new JLabel("End date:");
		endDate_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_endDate_lbl = new GridBagConstraints();
		gbc_endDate_lbl.anchor = GridBagConstraints.WEST;
		gbc_endDate_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_endDate_lbl.gridx = 4;
		gbc_endDate_lbl.gridy = 5;
		frmAddChargeBands.getContentPane().add(endDate_lbl, gbc_endDate_lbl);

		endDate_txtField = new JTextField();
		endDate_txtField.setEditable(false);
		endDate_txtField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_endDate_txtField = new GridBagConstraints();
		gbc_endDate_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_endDate_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_endDate_txtField.gridx = 7;
		gbc_endDate_txtField.gridy = 5;
		frmAddChargeBands.getContentPane().add(endDate_txtField, gbc_endDate_txtField);
		endDate_txtField.setColumns(10);

		endDate_btn = new JButton("Add end date");
		endDate_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel("Selected Date:");
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
						endDate_txtField.setText(new DatePicker(f).setPickedDate());
						f.dispose();
					}
				});
			}
		});
		endDate_btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_endDate_btn = new GridBagConstraints();
		gbc_endDate_btn.insets = new Insets(0, 0, 5, 5);
		gbc_endDate_btn.gridx = 6;
		gbc_endDate_btn.gridy = 5;
		frmAddChargeBands.getContentPane().add(endDate_btn, gbc_endDate_btn);

		JLabel pricePerNight_lbl = new JLabel("Price per night: ");
		pricePerNight_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_pricePerNight_lbl = new GridBagConstraints();
		gbc_pricePerNight_lbl.anchor = GridBagConstraints.WEST;
		gbc_pricePerNight_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_pricePerNight_lbl.gridx = 4;
		gbc_pricePerNight_lbl.gridy = 7;
		frmAddChargeBands.getContentPane().add(pricePerNight_lbl, gbc_pricePerNight_lbl);

		pricePerNight_txtField = new JTextField();
		pricePerNight_txtField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_pricePerNight_txtField = new GridBagConstraints();
		gbc_pricePerNight_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_pricePerNight_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pricePerNight_txtField.gridx = 6;
		gbc_pricePerNight_txtField.gridy = 7;
		frmAddChargeBands.getContentPane().add(pricePerNight_txtField, gbc_pricePerNight_txtField);
		pricePerNight_txtField.setColumns(10);

		JLabel serviceCharge_lbl = new JLabel("Service charge: ");
		serviceCharge_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_serviceCharge_lbl = new GridBagConstraints();
		gbc_serviceCharge_lbl.anchor = GridBagConstraints.WEST;
		gbc_serviceCharge_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_serviceCharge_lbl.gridx = 4;
		gbc_serviceCharge_lbl.gridy = 9;
		frmAddChargeBands.getContentPane().add(serviceCharge_lbl, gbc_serviceCharge_lbl);

		serviceCharge_txtField = new JTextField();
		serviceCharge_txtField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		serviceCharge_txtField.setColumns(10);
		GridBagConstraints gbc_serviceCharge_txtField = new GridBagConstraints();
		gbc_serviceCharge_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_serviceCharge_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_serviceCharge_txtField.gridx = 6;
		gbc_serviceCharge_txtField.gridy = 9;
		frmAddChargeBands.getContentPane().add(serviceCharge_txtField, gbc_serviceCharge_txtField);

		JLabel cleaningCharge_lbl = new JLabel("Cleaning charge: ");
		cleaningCharge_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_cleaningCharge_lbl = new GridBagConstraints();
		gbc_cleaningCharge_lbl.anchor = GridBagConstraints.WEST;
		gbc_cleaningCharge_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_cleaningCharge_lbl.gridx = 4;
		gbc_cleaningCharge_lbl.gridy = 11;
		frmAddChargeBands.getContentPane().add(cleaningCharge_lbl, gbc_cleaningCharge_lbl);

		cleaningCharge_txtField = new JTextField();
		cleaningCharge_txtField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cleaningCharge_txtField.setColumns(10);
		GridBagConstraints gbc_cleaningCharge_txtField = new GridBagConstraints();
		gbc_cleaningCharge_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_cleaningCharge_txtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cleaningCharge_txtField.gridx = 6;
		gbc_cleaningCharge_txtField.gridy = 11;
		frmAddChargeBands.getContentPane().add(cleaningCharge_txtField, gbc_cleaningCharge_txtField);

		addAnother_btn = new JButton("Add another charge band");
		addAnother_btn.setEnabled(false);
		addAnother_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					stmt.setString(1, String.valueOf(propertyId));
					stmt.setString(2, startDate_txtField.getText());
					stmt.setString(3, endDate_txtField.getText());
					stmt.setString(4, pricePerNight_txtField.getText());
					stmt.setString(5, serviceCharge_txtField.getText());
					stmt.setString(6, cleaningCharge_txtField.getText());

					stmt.executeUpdate();
					frmAddChargeBands.dispose();

					AddChargeBandsFrame window = new AddChargeBandsFrame(propertyId);
					window.frmAddChargeBands.setVisible(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addAnother_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_addAnother_btn = new GridBagConstraints();
		gbc_addAnother_btn.anchor = GridBagConstraints.EAST;
		gbc_addAnother_btn.insets = new Insets(0, 0, 0, 5);
		gbc_addAnother_btn.gridx = 6;
		gbc_addAnother_btn.gridy = 13;
		frmAddChargeBands.getContentPane().add(addAnother_btn, gbc_addAnother_btn);

		saveChanges_btn = new JButton("Save changes");
		saveChanges_btn.setEnabled(false);
		saveChanges_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

					stmt.setString(1, String.valueOf(propertyId));
					stmt.setString(2, startDate_txtField.getText());
					stmt.setString(3, endDate_txtField.getText());
					stmt.setString(4, pricePerNight_txtField.getText());
					stmt.setString(5, serviceCharge_txtField.getText());
					stmt.setString(6, cleaningCharge_txtField.getText());

					stmt.executeUpdate();
					frmAddChargeBands.dispose();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		saveChanges_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_saveChanges_btn = new GridBagConstraints();
		gbc_saveChanges_btn.insets = new Insets(0, 0, 0, 5);
		gbc_saveChanges_btn.gridx = 7;
		gbc_saveChanges_btn.gridy = 13;
		frmAddChargeBands.getContentPane().add(saveChanges_btn, gbc_saveChanges_btn);
		frmAddChargeBands.setBounds(100, 100, 1080, 720);
		frmAddChargeBands.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		startDate_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted()) {
					saveChanges_btn.setEnabled(true);
					addAnother_btn.setEnabled(true);
				} else {
					saveChanges_btn.setEnabled(false);
					addAnother_btn.setEnabled(false);
				}
			}
		});

		endDate_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted()) {
					saveChanges_btn.setEnabled(true);
					addAnother_btn.setEnabled(true);
				} else {
					saveChanges_btn.setEnabled(false);
					addAnother_btn.setEnabled(false);
				}
			}
		});

		cleaningCharge_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted()) {
					saveChanges_btn.setEnabled(true);
					addAnother_btn.setEnabled(true);
				} else {
					saveChanges_btn.setEnabled(false);
					addAnother_btn.setEnabled(false);
				}
			}
		});

		serviceCharge_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted()) {
					saveChanges_btn.setEnabled(true);
					addAnother_btn.setEnabled(true);
				} else {
					saveChanges_btn.setEnabled(false);
					addAnother_btn.setEnabled(false);
				}
			}
		});

		pricePerNight_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (areAllCompleted()) {
					saveChanges_btn.setEnabled(true);
					addAnother_btn.setEnabled(true);
				} else {
					saveChanges_btn.setEnabled(false);
					addAnother_btn.setEnabled(false);
				}
			}
		});
	}

	private boolean areAllCompleted() {
		boolean ok = false;
		if (startDate_txtField.getText().length() == 0 || endDate_txtField.getText().length() == 0
				|| pricePerNight_txtField.getText().length() == 0 || serviceCharge_txtField.getText().length() == 0
				|| cleaningCharge_txtField.getText().length() == 0) {
			ok = false;
		} else {
			ok = true;
		}
		return ok;
	}

}
