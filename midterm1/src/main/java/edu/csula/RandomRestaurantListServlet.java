package edu.csula;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup=1, urlPatterns={"/suggest/restaurants/random/list"})
public class RandomRestaurantListServlet extends HttpServlet {
	public void init() {
		List<Restaurant> restaurants = new ArrayList<>();
		String idToAdd = "";

		for (int i = 0; i < 23; i++) {
			if (i < 10) {
				idToAdd = "0" + Integer.toString(i);
			}
			else {
				idToAdd = Integer.toString(i);
			}
			String nameToAdd = "Student " + idToAdd + "'s Restaurant";
			String urlToAdd = "http://cs3.calstatela.edu:8080/cs3220xstu" + idToAdd + "/menu";
			List<Integer> designRatingsToAdd = new ArrayList<>();
			List<Integer> tasteRatingsToAdd = new ArrayList<>();

			restaurants.add(new Restaurant(idToAdd, nameToAdd, urlToAdd, designRatingsToAdd, tasteRatingsToAdd));
		}
		getServletContext().setAttribute("restaurants", restaurants);
	}
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Restaurant> restaurants = (List<Restaurant>) getServletContext().getAttribute("restaurants"); 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");


		out.println("<head>");
		out.println("<style>body { " +
		"}</style>");
		out.println("</head>");

		out.println("<h1>What's for lunch? List of Restaurants:</h1>");

		out.println("<table>");
		out.println("<thead>" + 
						"<th>Name</th>" + 
						"<th>URL</th>" +
						"<th>Design</th>" +
						"<th>Taste</th>" +
						"<th>Reviewers</th>" +
					"</thead>"
		);

		for (Restaurant restaurant : restaurants) {
			out.println(
				"<tr>" + 
					"<td>" + restaurant.getName() + "</td>" + 
					"<td>" + restaurant.getUrl() + "</td>" +
					"<td>" + restaurant.getDesignRatingsAverage() + "</td>" +
					"<td>" + restaurant.getTasteRatingsAverage() + "</td>" +
					"<td>" + restaurant.getNumberReviewers() + "</td>" +
				"</tr>"
			);
		}
		out.println("</table>");
	}
}
