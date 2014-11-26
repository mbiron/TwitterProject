package isep.tweetproject.db;


import isep.tweetproject.server.Tweet;
import isep.tweetproject.server.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;


public class DBHelper {
	private static Logger log = Logger.getLogger(DBHelper.class);
	
	public static User getUserForName(String name){
		User user = null;
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			con = Database.getConnection();
			
			// TODO
			String preparedQuery = "SELECT * FROM User WHERE User.twitterNickname LIKE ?";
			stat = con.prepareStatement(preparedQuery);
			
			stat.setString(1, name);
			res = stat.executeQuery();
			
			// analyze results
			while (res.next()) {
				user = new User();
				user.setId(res.getInt("id"));
				user.setName(res.getString("name"));
				user.setTwitterNickname((res.getString("twitterNickname")));
				user.setJoinedDate(res.getDate("joinedDate"));
				
				log.trace("found User: " + user);
			}

		}catch (SQLException e) {
			log.error("error getting user for name : " + name);
			e.printStackTrace();
		} finally {
			try {
				if (res != null)
					res.close();
				if (stat != null)
					stat.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.error("Unable to close connection");
				e.printStackTrace();
			}

		}
		return user;
	}

	public static void updateData() {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			con = Database.getConnection();
			
			String preparedQuery = "INSERT INTO User VALUES (?,?,?,?)";
			stat = con.prepareStatement(preparedQuery);
			
			Date d = new Date(System.currentTimeMillis());
			
			stat.setInt(1, 1);
			stat.setString(2, "Aymeric");
			stat.setString(3, "Ricou");
			stat.setDate(4, d);	
			stat.executeUpdate();

		} catch (SQLException e) {
			log.error("error updating table User");
			e.printStackTrace();
		} finally {
			try {
				if (res != null)
					res.close();
				if (stat != null)
					stat.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.error("Unable to close connection");
				e.printStackTrace();
			}

		}
	}

	public static List<User> getUsers() {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;
		String query;
		List<User> users = null;
		try {
			con = Database.getConnection();
			stat = (Statement) con.createStatement();

			// TODO
			// query = ;
			// res = stat.executeQuery(query);
			// analyze results

		} catch (SQLException e) {
			log.error("error getting list of books");
			e.printStackTrace();
		} finally {
			try {
				if (res != null)
					res.close();
				if (stat != null)
					stat.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.error("Unable to close connection");
				e.printStackTrace();
			}
		}
		return users;
	}

	public static List<Tweet> getTweets(long userId) {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;
		String query;
		List<Tweet> tweets = null;
		try {
			con = Database.getConnection();
			stat = (Statement) con.createStatement();

			// TODO
			// query = ;
			// res = stat.executeQuery(query);
			// analyze results

		} catch (SQLException e) {
			log.error("error getting list of books");
			e.printStackTrace();
		} finally {
			try {
				if (res != null)
					res.close();
				if (stat != null)
					stat.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				log.error("Unable to close connection");
				e.printStackTrace();
			}

		}
		return tweets;
	}

}
