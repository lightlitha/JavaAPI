package com.fresh.startup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlienRepository {
	
	Connection dbcon = null;
	
	private void DBConnect() {
		String[] dbcred = {"jdbc:mysql://localhost/java_web_service", "faceless", "24685"};
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.dbcon = DriverManager.getConnection(dbcred[0], dbcred[1], dbcred[2]);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public AlienRepository() {
		if(Objects.isNull(this.dbcon))
			this.DBConnect();
	}
	
	public List<Alien> getAliens() {
		if(Objects.isNull(this.dbcon))
			this.DBConnect();
		List<Alien> aliens = new ArrayList<>();
		String query = "SELECT * FROM aliens";
		try {
			Statement stmt = this.dbcon.createStatement();
			ResultSet rslt = stmt.executeQuery(query);
			while(rslt.next()) {
				Alien a = new Alien();
				a.setId(rslt.getInt("id"));
				a.setName(rslt.getString("name"));
				a.setPoints(rslt.getInt("points"));
				
				aliens.add(a);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return aliens;
	}
	
	public Alien getAlien(int id) {
		if(Objects.isNull(this.dbcon))
			this.DBConnect();
		Alien alien = new Alien();
		String query = "SELECT * FROM aliens WHERE id = " + id;
		try {
			Statement stmt = this.dbcon.createStatement();
			ResultSet rslt = stmt.executeQuery(query);
			if(rslt.next()) {
				alien.setId(rslt.getInt("id"));
				alien.setName(rslt.getString("name"));
				alien.setPoints(rslt.getInt("points"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return alien;
	}

	public void createAlien(Alien alien) {
		if(Objects.isNull(this.dbcon))
			this.DBConnect();
		String dml = "INSERT INTO aliens values (?,?,?)";
		try {
			PreparedStatement stmt = this.dbcon.prepareStatement(dml);
			stmt.setInt(1, alien.getId());
			stmt.setString(2, alien.getName());
			stmt.setInt(3, alien.getPoints());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
