package com.project.Online_Quiz_App.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.Online_Quiz_App.entities.User;

@Repository
public class UserRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Transactional
	public int save(User user) throws SQLException {
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO user VALUES(?,?,?)")) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getHashedPassword());
			preparedStatement.setString(3, user.getSalt());
			
			return preparedStatement.executeUpdate();
		}
	}
	
	public User getUserByUsername(String username) throws SQLException {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM user WHERE username = ?");
						) {
			preparedStatement.setString(1, username);

			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return new User(resultSet.getString("username"),
							resultSet.getString("hashed_password"), resultSet.getString("salt"));
				}
			}
		}
		return null;
	}
}
