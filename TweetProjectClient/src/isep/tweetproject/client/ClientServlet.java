package isep.tweetproject.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

//TODO commentaire pour tester le GIT

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JSP_LOCATION = "/WEB-INF/JSP/";
	private static final String SERVER_URL = "http://localhost:8080/TweetProjectServer/rest/res/";
	private static Logger log = Logger.getLogger(ClientServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String jsp = JSP_LOCATION + "MainJSP.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}

	private static List<Tweet> getTweetListFromServer(String username) {
		Client client = ClientBuilder.newClient();

		log.info("Get Users Tweets for username " + username);

		// Building URL
		String url = SERVER_URL + "tweets?nickname=" + username;

		// Get datas from server
		List<Tweet> tweets = client.target(url)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Tweet>>() {
				});

		return tweets;
	}

	private static List<User> getUserListFromServer() {
		Client client = ClientBuilder.newClient();

		log.info("List Users");

		// Building URL
		String url = SERVER_URL + "users";

		// Get datas from server
		List<User> users = client.target(url)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<User>>() {
				});

		return users;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("Send");
		if (action == null)
			throw new ServletException();

		Client client = ClientBuilder.newClient();
		String url = null;
		String jsp = null;
		if (action.equals("List Users")) {

			List<User> users = getUserListFromServer();

			request.setAttribute("users", users);
			jsp = JSP_LOCATION + "Users.jsp";

		} else if (action.equals("Get Users tweets")) {
			String username = request.getParameter("username");

			if (username == null || username.isEmpty()) {
				request.setAttribute("errorNickname",
						"You must Specify an username!");
				doGet(request, response);
			} else {

				List<Tweet> tweets = getTweetListFromServer(username);

				if (tweets == null) {
					request.setAttribute("errorNickname", "Unkown user "
							+ username);
					doGet(request, response);
				} else if (tweets.isEmpty()) {
					request.setAttribute("errorNickname", "No tweets for user "
							+ username);
					doGet(request, response);
				} else {
					Map<String, List<Tweet>> map = new HashMap<String, List<Tweet>>();
					map.put(username, tweets);
					request.setAttribute("map", map);

					jsp = JSP_LOCATION + "Tweets.jsp";
				}
			}
		} else if (action.equals("List All Tweets")) {
			log.info("List All Tweets");

			List<User> users = getUserListFromServer();
			Map<String, List<Tweet>> map = new HashMap<String, List<Tweet>>();

			for (User user : users) {
				log.info(user);
				map.put(user.getTwitterNickname(),
						getTweetListFromServer(user.getTwitterNickname()));
			}

			request.setAttribute("map", map);
			jsp = JSP_LOCATION + "Tweets.jsp";

		} else if (action.equals("Fill DB")) {
			log.info("Fill DB");
			url = SERVER_URL + "update";
			client.target(url).request().get();

			jsp = JSP_LOCATION + "UpdateDB.jsp";

		} else {
			throw new ServletException("Unknow action requested");
		}

		// Display result
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);
	}
}
