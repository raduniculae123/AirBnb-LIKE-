

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class EnquirerMenuFrame {

	private JFrame enquirerMenuFrame;
	private JTable enquirer_table;
	private JButton endDate_btn;
	private JTextField startDate_txtField;
	private JTextField endDate_txtField;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	static final String SQL_QUERY = "SELECT shortName, description, location, breakfast from team002.properties where isAvailable=1";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnquirerMenuFrame window = new EnquirerMenuFrame();
					window.enquirerMenuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnquirerMenuFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		enquirerMenuFrame = new JFrame();
		enquirerMenuFrame.getContentPane().setBackground(Color.ORANGE);
		enquirerMenuFrame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 134, 1046, 539);
		enquirerMenuFrame.getContentPane().add(scrollPane);

		enquirer_table = new JTable();
		scrollPane.setViewportView(enquirer_table);
		enquirer_table.setFont(new Font("Tahoma", Font.PLAIN, 20));

		

		JLabel properties_lbl = new JLabel("Available properties");
		properties_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		properties_lbl.setBackground(new Color(255, 127, 80));
		properties_lbl.setBounds(10, 10, 353, 49);
		enquirerMenuFrame.getContentPane().add(properties_lbl);

		TextField location_txtField = new TextField();
		location_txtField.setFont(new Font("Dialog", Font.PLAIN, 20));
		location_txtField.setBounds(369, 47, 270, 36);
		enquirerMenuFrame.getContentPane().add(location_txtField);

		JLabel location_lbl = new JLabel("Location\r\n");
		location_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		location_lbl.setBounds(461, 10, 87, 31);
		enquirerMenuFrame.getContentPane().add(location_lbl);

		startDate_txtField = new JTextField();
		startDate_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		startDate_txtField.setBounds(694, 47, 146, 36);
		enquirerMenuFrame.getContentPane().add(startDate_txtField);
		startDate_txtField.setColumns(10);

		JButton startDate_btn = new JButton("Start date");
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
		startDate_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		startDate_btn.setBounds(694, 10, 146, 36);
		enquirerMenuFrame.getContentPane().add(startDate_btn);

		endDate_txtField = new JTextField();
		endDate_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		endDate_txtField.setColumns(10);
		endDate_txtField.setBounds(896, 47, 146, 36);
		enquirerMenuFrame.getContentPane().add(endDate_txtField);

		endDate_btn = new JButton("End date");
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
		endDate_btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		endDate_btn.setBounds(896, 10, 146, 36);
		enquirerMenuFrame.getContentPane().add(endDate_btn);

		JButton search_btn = new JButton("Search");
		search_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		search_btn.setBounds(896, 88, 146, 36);
		enquirerMenuFrame.getContentPane().add(search_btn);

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		enquirer_table.setModel(model);
		enquirer_table.setAutoResizeMode(0);
		enquirer_table.setRowHeight(26);

		model.addColumn("Name");
		model.addColumn("Description");
		model.addColumn("Location");
		model.addColumn("Breakfast");

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement stmt = conn.prepareStatement(SQL_QUERY);)

		{

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String hasBreakfast;
				if (rs.getInt("breakfast") == 1) {
					hasBreakfast = "Yes";
				} else {
					hasBreakfast = "No";
				}
				model.addRow(new Object[] { rs.getString("shortName"), rs.getString("description"),
						rs.getString("location"), hasBreakfast });
			}
			enquirer_table.setModel(model);
			
			enquirer_table.getColumnModel().getColumn(0).setPreferredWidth(200);
			enquirer_table.getColumnModel().getColumn(1).setPreferredWidth(544);
			enquirer_table.getColumnModel().getColumn(2).setPreferredWidth(200);
			enquirer_table.getColumnModel().getColumn(3).setPreferredWidth(100);

		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}
		   enquirer_table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		enquirerMenuFrame.setBounds(100, 100, 1080, 720);
		enquirerMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
