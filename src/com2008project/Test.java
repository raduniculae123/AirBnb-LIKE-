package com2008project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team002";
	static final String USER = "team002";
	static final String PASS = "38695e46";
	private static final String SQL_INSERT = "INSERT INTO team002.properties (id,userID,shortName,addressID,description,location,breakfast,guests) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_GET = "SELECT MAX(ID) FROM team002.addresses";

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		int i = 0;

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt2 = conn.prepareStatement(SQL_GET, Statement.RETURN_GENERATED_KEYS);) {

			stmt2.executeQuery();
			ResultSet rs2 = stmt2.getGeneratedKeys();
			String addressId = rs2.toString();
			System.out.println("muie");
			System.out.println(addressId);

		} catch (SQLException e1) {
			System.out.println("sadas");
			e1.printStackTrace();
		}
	}

}
