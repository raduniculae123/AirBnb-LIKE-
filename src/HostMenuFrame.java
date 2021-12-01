


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HostMenuFrame {

	JFrame hostMenuFrame;
	private JPanel contentPane;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_QUERY = "SELECT id, shortName, location FROM team002.properties where userID=?";
	private static final String SQL_QUERY_BOOK = "SELECT count(*) as count FROM team002.bookings WHERE propertyID = ?";
	private static final String SQL_GET_STATUS = "select guest from team002.users where id=?";
	private static final String SQL_QUERY2 = "SELECT MAX(id) FROM team002.properties";
	private JTable myProperties_table;

	/**
	 * Create the application.
	 */
	public HostMenuFrame(int userId) {
		System.out.println("you are now in id " + userId + "menu");
		initialize(userId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int userId) {

		hostMenuFrame = new JFrame();
		hostMenuFrame.setTitle("Host menu");
		hostMenuFrame.setBounds(100, 100, 1080, 720);
		hostMenuFrame.getContentPane().setBackground(Color.ORANGE);
		hostMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hostMenuFrame.setBackground(Color.ORANGE);
		hostMenuFrame.getContentPane().setLayout(null);
		JScrollPane table_pane = new JScrollPane();
		table_pane.setBounds(10, 105, 1044, 565);
		hostMenuFrame.getContentPane().add(table_pane);

		myProperties_table = new JTable();
		myProperties_table.setFont(new Font("Tahoma", Font.PLAIN, 25));
		table_pane.setViewportView(myProperties_table);
		myProperties_table.setBackground(new Color(255, 222, 173));

		JLabel myProperties_lbl = new JLabel("My properties");
		myProperties_lbl.setBackground(new Color(255, 127, 80));
		myProperties_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		myProperties_lbl.setBounds(10, 11, 250, 75);
		hostMenuFrame.getContentPane().add(myProperties_lbl);

		JButton addProperty_btn = new JButton("Add property");
		addProperty_btn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		addProperty_btn.setBounds(446, 30, 230, 45);
		hostMenuFrame.getContentPane().add(addProperty_btn);
		addProperty_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int propertyId;
				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt3 = conn.prepareStatement(SQL_QUERY2);) {

					ResultSet rs3 = stmt3.executeQuery();
					if (rs3.next()) {
						if (rs3.getString(1) == null) {
							propertyId = 1;
						} else {
							propertyId = Integer.parseInt(rs3.getString(1)) + 1;
						}
						AddPropertyFrame window = new AddPropertyFrame(propertyId, userId);
						System.out.println("propid " + propertyId);
						window.frmAddProperty.setVisible(true);
						hostMenuFrame.dispose();
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JButton guest_btn = new JButton("Guest menu");
		guest_btn.setEnabled(false);
		guest_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuestMenuFrame window = new GuestMenuFrame(userId);
				window.guestMenuframe.setVisible(true);
				hostMenuFrame.dispose();
			}
		});
		guest_btn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		guest_btn.setBounds(800, 30, 230, 45);
		hostMenuFrame.getContentPane().add(guest_btn);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt2 = conn.prepareStatement(SQL_GET_STATUS);) {

			stmt2.setString(1, String.valueOf(userId));
			ResultSet rs2 = stmt2.executeQuery();
			if (rs2.next()) {
				if (rs2.getString("guest").equals("1")) {
					guest_btn.setEnabled(true);
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		model.addColumn("Name");
		model.addColumn("Location");
		model.addColumn("Number of bookings");
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement stmt = conn.prepareStatement(SQL_QUERY);
				PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY_BOOK);)

		{

			stmt.setString(1, String.valueOf(userId));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				stmt2.setString(1, rs.getString("id"));
				ResultSet rs2 = stmt2.executeQuery();
				rs2.next();
				model.addRow(new Object[] { rs.getString("shortName"), rs.getString("location"),
						rs2.getInt("count")});
			}
			myProperties_table.setModel(model);
			myProperties_table.setAutoResizeMode(0);
			myProperties_table.setRowHeight(26);

			myProperties_table.getColumnModel().getColumn(0).setPreferredWidth(460);
			myProperties_table.getColumnModel().getColumn(1).setPreferredWidth(460);
			myProperties_table.getColumnModel().getColumn(2).setPreferredWidth(124);

		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
