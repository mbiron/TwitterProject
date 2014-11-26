package isep.tweetproject.server;

import isep.tweetproject.db.DBHelper;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

@Path("/res")
public class RESTService {

	private static Logger log = Logger.getLogger(RESTService.class);

	// TOREMOVE : just fort testing
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testText() {
		return "just a text test";
	}

	@POST
	@Path("/update")
	public void updateData() {
		log.info("updateData");
		DBHelper.updateData();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users")
	public List<User> getUsers() {
		List<User> users = null;

		log.info("getUsers");
		users = DBHelper.getUsers();
		
		return users;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tweets")
	public List<Tweet> getTweets(@QueryParam("nickname") String nickname) {
		List<Tweet> tweets = null;

		try {
			log.info("getTweets");
			log.info("name = " + nickname);
			if (nickname == null) {
				log.error("Need a nickname");
				return null;
			}
			User user = DBHelper.getUserForName(nickname);
			if (user == null) {
				log.error("No User for specified name");
				return null;
			}
			log.info("User = " + user);
			tweets = DBHelper.getTweets(user.getId());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		// TODO
		return tweets;
	}

}
