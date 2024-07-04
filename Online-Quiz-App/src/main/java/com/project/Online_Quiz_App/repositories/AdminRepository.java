package com.project.Online_Quiz_App.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Online_Quiz_App.entities.Admin;

@Repository
public class AdminRepository {

	@Autowired
	private DataSource dataSource;
	
	public Admin getUserByUsername(String id) throws SQLException {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM admin WHERE id = ?");
						) {
			preparedStatement.setString(1, id);

			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return new Admin(resultSet.getString("id"),
							resultSet.getString("hashed_password"), resultSet.getString("salt"));
				}
			}
		}
		return null;
	}
}
