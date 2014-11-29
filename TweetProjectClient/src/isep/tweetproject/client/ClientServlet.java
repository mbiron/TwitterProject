package isep.tweetproject.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JSP_LOCATION = "/WEB-INF/JSP/";
	private static final String SERVER_URL = "http://localhost:8080/TweetProjectServer/rest/res/";

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
			url = SERVER_URL + "users";
		} else if (action.equals("Get Users tweets")) {
			String username = request.getParameter("username");
			if (username == null) {
				// TODO
			} else {
				url = SERVER_URL + "tweets?username=" + username;
			}
		} else if (action.equals("List All Tweets")) {
			url = SERVER_URL + "tweets";
		} else if (action.equals("Fill DB")) {
			url = SERVER_URL + "update";
		} else {
			// Unknow : throw error
		}
		client.target(url).request(MediaType.APPLICATION_JSON);
	}

}
