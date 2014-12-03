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

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

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
		users.add(new User(4, "Maxime", "Birouille", d));
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
		tweets.add(new Tweet(6, 3, "Allumez le feu!", d));
		return tweets;
	}

	/**
	 * Fill DB with false Users & Tweets
	 */
	public static void updateData() {
		insertUserAndTweets(getFalseUsers(), getFalseTweets());
	}

	private static void insertUserAndTweets(List<User> users, List<Tweet> tweets) {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet res = null;

		try {
			con = Database.getConnection();

			for (User user : users) {
				String preparedQuery = "INSERT INTO User VALUES (?,?,?,?)";
				stat = con.prepareStatement(preparedQuery);

				stat.setLong(1, user.getId());
				stat.setString(2, user.getName());
				stat.setString(3, user.getTwitterNickname());
				stat.setDate(4, (java.sql.Date) user.getJoinedDate());
				try {
					stat.executeUpdate();
				} catch (SQLException eDuplicateEntry) {
					log.warn("Duplicate Entry in DB (User)!");
				}
			}
			for (Tweet tweet : tweets) {
				String preparedQuery = "INSERT INTO Tweet VALUES (?,?,?,?)";
				stat = con.prepareStatement(preparedQuery);

				stat.setLong(1, tweet.getId());
				stat.setLong(2, tweet.getAuthorId());
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
	 * Fill DB with TRUE Tweets (in the future)
	 */
	public static void updateTrueData() {

		String usersNames[] = { "@StartupVillage", "@GlassFrance", "@altolabs" };
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(false);
		cb.setOAuthConsumerKey("u8jyeLqlrlznLeG0UyoyoS5lF");
		cb.setOAuthConsumerSecret("RvUBlVs3Hqp7Z2zugvX2CMGP40FQGlvOHahtQ2Q8qSxdMSVbxC");
		cb.setOAuthAccessToken("2902427633-5Abyvynt1B8Z6yyaJOpH7xsDGzJ0UzKAMldkq1j");
		cb.setOAuthAccessTokenSecret("6kYEmShShXvUAawJs1LNLmUX8uWHxyGLd5lljZBAJF7v6");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();

		Paging paging = new Paging(1, 10);
		List<Status> statuses;
		List<User> users = new ArrayList<>();
		List<Tweet> tweets = new ArrayList<>();
		try {
			for (String username : usersNames) {
				statuses = twitter.getUserTimeline(username, paging);
				for (Status stat : statuses) {
					twitter4j.User user = stat.getUser();

					users.add(new User((int) user.getId(), user.getName(), user
							.getScreenName(), user.getCreatedAt()));

					log.info("TWEET Id: " + stat.getId());
					log.info("TWEET Date: " + stat.getCreatedAt());
					log.info("TWEET User: " + user.getId() + " "
							+ user.getCreatedAt() + " " + user.getScreenName()
							+ " " + user.getName());
					log.info("TWEET Text: " + stat.getText());
					log.info("----------------------------------------------------------");
				}
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
