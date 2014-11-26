package isep.tweetproject.server;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

	// TODO : annotations ? Peut pas etre un get! un post ?
	public void updateData(){
		log.info("updateData");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users")
	public List<User> getUsers(){
		List<User> users = null;
		
		log.info("getUsers");
		
		//TODO
		return users;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tweets")
	public List<Tweet> getTweets(@PathParam("nickname") String nickname){
		List<Tweet> tweets = null;
		
		log.info("getTweets");
		
		//TODO
		return tweets;
	}
	
}
