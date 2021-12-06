import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
/**
 *a class for adding the address details
 */
public class AddAdressFrame {

	JFrame addAdressFrame;
	private JTextField house_txtField;
	private JTextField street_txtField;
	private JTextField place_txtField;
	private JTextField postcode_txtField;
	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.addresses (house,postcode,street,place,isProperty) VALUES (?,?,?,?,?)";
	
	private static final String SQL_INIT_INSERT = "INSERT INTO team002.properties (id,userID,house,postcode) values (?,?,?,?)";
	/**
	 * Create the application.
	 */
	public AddAdressFrame(int isProperty, int id, int userID) {
		initialize(isProperty, id, userID);
	}

	/**
	 * Initialize the contents of the frame for the given parameters
	 * @param isProperty, id, userId
	 */
	private void initialize(int isProperty, int id, int userId) {
		
		addAdressFrame = new JFrame();
		addAdressFrame.getContentPane().setBackground(Color.ORANGE);
		addAdressFrame.setBounds(100, 100, 1080, 720);
		addAdressFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addAdressFrame.getContentPane().setLayout(null);

		
		JButton submit_btn = new JButton("Submit");
		submit_btn.setEnabled(false);
		submit_btn.addActionListener(new ActionListener() {
			/**
			 * inserts the contents of the text fields in the database
			 */
			public void actionPerformed(ActionEvent e) {

				try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

						PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
						PreparedStatement stmt3 = conn.prepareStatement(SQL_INIT_INSERT);) {

					stmt.setString(1, house_txtField.getText());
					stmt.setString(2, postcode_txtField.getText());
					stmt.setString(3, street_txtField.getText());
					stmt.setString(4, place_txtField.getText());
					stmt.setString(5, String.valueOf(isProperty));

					stmt.executeUpdate();

					addAdressFrame.dispose();

					stmt3.setString(1, String.valueOf(id));
					stmt3.setString(2, String.valueOf(userId));
					stmt3.setString(3, house_txtField.getText());
					stmt3.setString(4, postcode_txtField.getText());
					stmt3.executeUpdate();
					System.out.println("insert prop= " + id + "  userid= " + userId);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		submit_btn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		submit_btn.setBounds(409, 605, 187, 45);
		addAdressFrame.getContentPane().add(submit_btn);

		/**
		 * adding the text fields and labels
		 */
		JLabel addProperty_lbl = new JLabel("Add Address");
		addProperty_lbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		addProperty_lbl.setBounds(378, 0, 327, 87);
		addAdressFrame.getContentPane().add(addProperty_lbl);

		JLabel addressName_lbl = new JLabel("House: ");
		addressName_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		addressName_lbl.setBounds(97, 147, 214, 58);
		addAdressFrame.getContentPane().add(addressName_lbl);

		house_txtField = new JTextField();
		house_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		house_txtField.setBounds(344, 164, 361, 37);
		addAdressFrame.getContentPane().add(house_txtField);
		house_txtField.setColumns(10);
		house_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					submit_btn.setEnabled(true);
				} else {
					submit_btn.setEnabled(false);
				}
			}
		});

		JLabel street_lbl = new JLabel("Street: ");
		street_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		street_lbl.setBounds(97, 268, 241, 58);
		addAdressFrame.getContentPane().add(street_lbl);

		street_txtField = new JTextField();
		street_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		street_txtField.setColumns(10);
		street_txtField.setBounds(344, 278, 361, 36);
		addAdressFrame.getContentPane().add(street_txtField);
		street_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					submit_btn.setEnabled(true);
				} else {
					submit_btn.setEnabled(false);
				}
			}
		});

		place_txtField = new JTextField();
		place_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		place_txtField.setColumns(10);
		place_txtField.setBounds(344, 402, 361, 37);
		addAdressFrame.getContentPane().add(place_txtField);
		place_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					submit_btn.setEnabled(true);
				} else {
					submit_btn.setEnabled(false);
				}
			}
		});

		JLabel place_lbl = new JLabel("Place name: ");
		place_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		place_lbl.setBounds(97, 385, 214, 58);
		addAdressFrame.getContentPane().add(place_lbl);

		JLabel postcode_lbl = new JLabel("Postcode:");
		postcode_lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		postcode_lbl.setBounds(97, 488, 214, 58);
		addAdressFrame.getContentPane().add(postcode_lbl);

		postcode_txtField = new JTextField();
		postcode_txtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		postcode_txtField.setColumns(10);
		postcode_txtField.setBounds(344, 509, 361, 37);
		addAdressFrame.getContentPane().add(postcode_txtField);
		postcode_txtField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // watch for key strokes
				if (areAllCompleted()) {
					submit_btn.setEnabled(true);
				} else {
					submit_btn.setEnabled(false);
				}
			}
		});

	}

	/**
	 * checking whether all the fields have been completed
	 * @return boolean for completion status
	 */
	private boolean areAllCompleted() {
		boolean ok = false;
		if (house_txtField.getText().length() == 0 || street_txtField.getText().length() == 0
				|| place_txtField.getText().length() == 0 || postcode_txtField.getText().length() == 0) {
			ok = false;
		} else {
			ok = true;
		}

		return ok;
	}
}