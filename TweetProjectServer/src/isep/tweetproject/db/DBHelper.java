package isep.tweetproject.db;

import isep.tweetproject.server.Tweet;
import isep.tweetproject.server.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

public class DBHelper {
	private static Logger log = Logger.getLogger(DBHelper.class);

	public void updateData() {
		Connection con = null;
		Statement stat = null;
		ResultSet res = null;
		String query;
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
	}

	public List<User> getUsers() {
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

	public List<Tweet> getTweets(long userId) {
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
