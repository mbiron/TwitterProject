package isep.tweetproject.db;

import isep.tweetproject.server.Tweet;
import isep.tweetproject.server.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DBHelper {
	private static Logger log = Logger.getLogger(DBHelper.class);

	/**
	 * Return a user for the specified name
	 * 
	 * @param name
	 *            User name in Database
	 * @return An User JSON object
	 * @see User
	 */
	public static User getUserForName(String name) {
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

		} catch (SQLException e) {
			log.error("error getting user for name : " + name);
			e.printStackTrace();
		} finally {
			try {
				log.debug("Closing connection");
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

	private static List<User> getFalseUsers() {
		List<User> users = new ArrayList<User>();

		Date d = new Date(System.currentTimeMillis());
		users.add(new User(1, "Aymeric", "Ricou", d));
		users.add(new User(2, "Titi", "Toto", d));
		users.add(new User(3, "Johny", "Hallyday", d));
		return users;
	}

	private static List<Tweet> getFalseTweets() {
		List<Tweet> tweets = new ArrayList<Tweet>();
		Date d = new Date(System.currentTimeMillis());
		tweets.add(new Tweet(1, 1, "Coucou c'est Ricou", d));
		tweets.add(new Tweet(2, 1, "Coucou c'est ENCORE Ricou", d));
		tweets.add(new Tweet(3, 1, "Coucou c'est TOUJOURS Ricou", d));
		tweets.add(new Tweet(4, 2, "YOLOOOOOOOO", d));
		tweets.add(new Tweet(5, 2, "YOLOOOOOOOO again!", d));
		return tweets;
	}

	/**
	 * Fill DB with false Users & Tweets
	 */
	public static void updateData() {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;

		try {
			con = Database.getConnection();

			for (User user : getFalseUsers()) {
				String preparedQuery = "INSERT INTO User VALUES (?,?,?,?)";
				stat = con.prepareStatement(preparedQuery);

				stat.setInt(1, user.getId());
				stat.setString(2, user.getName());
				stat.setString(3, user.getTwitterNickname());
				stat.setDate(4, (java.sql.Date) user.getJoinedDate());
				try {
					stat.executeUpdate();
				} catch (SQLException eDuplicateEntry) {
					log.warn("Duplicate Entry in DB (User)!");
				}
			}
			for (Tweet tweet : getFalseTweets()) {
				String preparedQuery = "INSERT INTO Tweet VALUES (?,?,?,?)";
				stat = con.prepareStatement(preparedQuery);

				stat.setInt(1, tweet.getId());
				stat.setInt(2, tweet.getAuthorId());
				stat.setString(3, tweet.getMessage());
				stat.setDate(4, (java.sql.Date) tweet.getDate());
				try {
					stat.executeUpdate();
				} catch (SQLException eDuplicateEntry) {
					log.warn("Duplicate Entry in DB (Tweet)!");
				}
			}
		} catch (SQLException e) {
			log.error("error updating table User");
			e.printStackTrace();
		} finally {
			try {
				log.debug("Closing connection");
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

	/**
	 * Return all users in DB
	 * 
	 * @return List of User JSON object
	 * @see User
	 */
	public static List<User> getUsers() {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		List<User> users = new ArrayList<User>();
		try {
			con = Database.getConnection();

			String preparedQuery = "SELECT * FROM User";
			stat = con.prepareStatement(preparedQuery);
			res = stat.executeQuery();

			// analyze results
			while (res.next()) {
				User user = new User();
				user.setId(res.getInt("id"));
				user.setName(res.getString("name"));
				user.setTwitterNickname((res.getString("twitterNickname")));
				user.setJoinedDate(res.getDate("joinedDate"));

				log.trace("found User: " + user);
				users.add(user);
			}

		} catch (SQLException e) {
			log.error("error getting list of books");
			e.printStackTrace();
		} finally {
			try {
				log.debug("Closing connection");
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

	/**
	 * Return all tweets in DB for the specified userId
	 * 
	 * @param userId
	 *            User Id in Database
	 * @return List of Tweet JSON object
	 * @see Tweet
	 */
	public static List<Tweet> getTweets(long userId) {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		List<Tweet> tweets = new ArrayList<Tweet>();
		try {
			con = Database.getConnection();

			String preparedQuery = "SELECT * FROM Tweet WHERE authorId LIKE ?";
			stat = con.prepareStatement(preparedQuery);
			stat.setLong(1, userId);
			res = stat.executeQuery();

			// analyze results
			while (res.next()) {
				Tweet tweet = new Tweet();
				tweet.setId(res.getInt("tweetId"));
				tweet.setAuthorId(res.getInt("authorId"));
				tweet.setMessage((res.getString("message")));
				tweet.setDate((res.getDate("date")));

				log.trace("found Tweet: " + tweet);
				tweets.add(tweet);
			}
		} catch (SQLException e) {
			log.error("error getting list of tweets");
			e.printStackTrace();
		} finally {
			try {
				log.debug("Closing connection");
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
