package isep.tweetproject.client;

import java.io.IOException;
import java.util.List;

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

		if (action.equals("List Users")) {
			log.info("List Users");
			url = SERVER_URL + "users";

			// Get datas from server
			List<User> users = client.target(url)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<User>>() {
					});

			// Display result
			request.setAttribute("users", users);
			String jsp = JSP_LOCATION + "Users.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);

		} else if (action.equals("Get Users tweets")) {
			String username = request.getParameter("username");
			log.info("Get Users Tweets for username " + username);
			if (username == null || username.isEmpty()) {
				request.setAttribute("errorNickname",
						"You must Specify an username!");
				doGet(request, response);
			} else {
				url = SERVER_URL + "tweets?username=" + username;

				List<Tweet> tweets = client.target(url)
						.request(MediaType.APPLICATION_JSON)
						.get(new GenericType<List<Tweet>>() {
						});

			}
		} else if (action.equals("List All Tweets")) {
			log.info("List All Tweets");
			url = SERVER_URL + "tweets";
			List<Tweet> tweets = client.target(url)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Tweet>>() {
					});
		} else if (action.equals("Fill DB")) {
			log.info("Fill DB");
			url = SERVER_URL + "update";
			client.target(url).request().get();

			// Display result
			String jsp = JSP_LOCATION + "UpdateDB.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		} else {
			// Unknow : throw error
		}

	}

}
