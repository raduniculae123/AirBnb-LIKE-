import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.plaf.ButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class BookingsFrame {

	JFrame bookingFrame;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_UPDATE = "UPDATE team002.bookings SET status=? WHERE id = ?";
	private static final String SQL_UPDATE2 = "UPDATE team002.bookings SET status=2 WHERE (NOT( (startDate NOT BETWEEN ? AND ? ) AND (endDate NOT BETWEEN ? AND ? ) AND((startDate < ? AND endDate < ?) OR (startDate > ? AND endDate > ? ))))";
	private static final String SQL_QUERY = "SELECT id, userID, startDate, endDate, status FROM team002.bookings WHERE propertyID=? AND status!=2";
	private static final String SQL_QUERY2 = "SELECT title, forename, surname, email FROM team002.users WHERE id=?";
	private static final String SQL_QUERY3 = "SELECT shortName FROM team002.properties WHERE id=?";
	private static final String SQL_QUERY4 = "SELECT startDate, endDate FROM team002.bookings WHERE id=?";
	private static int[] propertyIdArray = new int[10000];
	private JTable table;

	/**
	 * Create the application.
	 */
	public BookingsFrame(int propertyID, int userID) {
		initialize(propertyID, userID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int propertyID, int userID) {
		bookingFrame = new JFrame();
		bookingFrame.getContentPane().setBackground(Color.ORANGE);
		bookingFrame.getContentPane().setLayout(null);

		JLabel bookings_lbl = new JLabel("");
		bookings_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		bookings_lbl.setBounds(10, 11, 755, 49);
		bookingFrame.getContentPane().add(bookings_lbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 1044, 599);
		bookingFrame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		bookingFrame.setBounds(100, 100, 1080, 720);
		bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		model.addColumn("Title");
		model.addColumn("Forename");
		model.addColumn("Surname");
		model.addColumn("Email");
		model.addColumn("Start Date");
		model.addColumn("End Date");
		model.addColumn("Accept");
		model.addColumn("Decline");
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement stmt = conn.prepareStatement(SQL_QUERY);
				PreparedStatement stmt2 = conn.prepareStatement(SQL_QUERY2);
				PreparedStatement stmt3 = conn.prepareStatement(SQL_QUERY3);)

		{
			// SQL_QUERY = "SELECT id, userID, startDate, endDate FROM team002.bookings
			// WHERE propertyID=?";
			// SQL_QUERY2 = "SELECT title, forename, surname FROM team002.users WHERE id=?";
			int row = 0;
			stmt3.setString(1, String.valueOf(propertyID));
			ResultSet rs3 = stmt3.executeQuery();
			if (rs3.next()) {
				bookings_lbl.setText("Bookings for " + rs3.getString("shortName") + " property");
			}
			stmt.setString(1, String.valueOf(propertyID));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				stmt2.setString(1, rs.getString("userID"));
				ResultSet rs2 = stmt2.executeQuery();
				if (rs2.next()) {
					if (rs.getInt("status") == 0) {
						model.addRow(new Object[] { rs2.getString("title"), rs2.getString("forename"),
								rs2.getString("surname"), "Not Available" ,rs.getString("startDate"), rs.getString("endDate"), "Accept",
								"Decline" });
					} else if (rs.getInt("status") == 1) {
						model.addRow(new Object[] { rs2.getString("title"), rs2.getString("forename"),
								rs2.getString("surname"), rs2.getString("email") ,rs.getString("startDate"), rs.getString("endDate"),
								"Accepted", "Accepted" });
					} else if (rs.getInt("status") == 2) {
						model.addRow(new Object[] { rs2.getString("title"), rs2.getString("forename"),
								rs2.getString("surname"), "Not Available" , rs.getString("startDate"), rs.getString("endDate"),
								"Declined", "Declined" });
					}
					propertyIdArray[row] = rs.getInt("id");
					row++;
				}
			}
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.setRowHeight(26);

			JButton host_btn = new JButton("Host menu");
			host_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HostMenuFrame window = new HostMenuFrame(userID);
					window.hostMenuFrame.setVisible(true);
					bookingFrame.dispose();
				}
			});
			host_btn.setFont(new Font("Tahoma", Font.PLAIN, 30));
			host_btn.setBounds(824, 11, 230, 45);
			bookingFrame.getContentPane().add(host_btn);

			table.getColumnModel().getColumn(0).setPreferredWidth(80);
			table.getColumnModel().getColumn(1).setPreferredWidth(196);
			table.getColumnModel().getColumn(2).setPreferredWidth(196);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);
			table.getColumnModel().getColumn(4).setPreferredWidth(85);
			table.getColumnModel().getColumn(5).setPreferredWidth(85);
			table.getColumnModel().getColumn(6).setPreferredWidth(80);
			table.getColumnModel().getColumn(7).setPreferredWidth(80);
			table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));

		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && (!(table.getModel().getValueAt(row, col).toString().equals("Accepted"))
						&& !(table.getModel().getValueAt(row, col).toString().equals("Declined")))) {
					if (col == 6) {
						acceptBooking(row, propertyID, userID);
						System.out.println("coloana accept");
					} else if (col == 7) {
						declineBooking(row, propertyID, userID);
						System.out.println("coloana decline");
					}
				} else {
					System.out.println("conditie gresita");
				}
			}

		});

	}

	private void acceptBooking(int row, int propertyID, int userID) {

		try (Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement stmt2 = conn2.prepareStatement(SQL_UPDATE);
				PreparedStatement stmt3 = conn2.prepareStatement(SQL_UPDATE2);
				PreparedStatement stmt4 = conn2.prepareStatement(SQL_QUERY4);)

		{

			stmt4.setString(1, String.valueOf(propertyIdArray[row]));
			ResultSet rs = stmt4.executeQuery();
			if (rs.next()) {
				stmt3.setString(1, rs.getString("startDate"));
				stmt3.setString(2, rs.getString("endDate"));
				stmt3.setString(3, rs.getString("startDate"));
				stmt3.setString(4, rs.getString("endDate"));
				stmt3.setString(5, rs.getString("startDate"));
				stmt3.setString(6, rs.getString("startDate"));
				stmt3.setString(7, rs.getString("endDate"));
				stmt3.setString(8, rs.getString("endDate"));
			}
			stmt3.executeUpdate();
			stmt2.setInt(1, 1);
			stmt2.setInt(2, propertyIdArray[row]);
			System.out.println("acc bookingID" + propertyIdArray[row] + "set status 1");
			stmt2.executeUpdate();
			bookingFrame.dispose();
			BookingsFrame window = new BookingsFrame(propertyID, userID);
			window.bookingFrame.setVisible(true);

		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	private void declineBooking(int row, int propertyID, int userID) {
		try (Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement stmt2 = conn2.prepareStatement(SQL_UPDATE);
				PreparedStatement stmt3 = conn2.prepareStatement(SQL_UPDATE2);
				PreparedStatement stmt4 = conn2.prepareStatement(SQL_QUERY4);)

		{

			stmt4.setString(1, String.valueOf(propertyIdArray[row]));
			ResultSet rs = stmt4.executeQuery();
			if (rs.next()) {
				stmt3.setString(1, rs.getString("startDate"));
				stmt3.setString(2, rs.getString("endDate"));
				stmt3.setString(3, rs.getString("startDate"));
				stmt3.setString(4, rs.getString("endDate"));
				stmt3.setString(5, rs.getString("startDate"));
				stmt3.setString(6, rs.getString("startDate"));
				stmt3.setString(7, rs.getString("endDate"));
				stmt3.setString(8, rs.getString("endDate"));
			}
			stmt3.executeUpdate();
			stmt2.setInt(1, 2);
			stmt2.setInt(2, propertyIdArray[row]);
			System.out.println("acc bookingID" + propertyIdArray[row] + "set status 2");
			stmt2.executeUpdate();
			bookingFrame.dispose();
			BookingsFrame window = new BookingsFrame(propertyID, userID);
			window.bookingFrame.setVisible(true);

		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
