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

@WebServlet(loadOnStartup=1, urlPatterns={"/admin/foods"})
public class FoodItemListAdminServlet extends HttpServlet {

	public void init() {
		// starts application scope with some food values
		List<FoodItem> entries = new ArrayList<>();
		entries.add(new FoodItem(entries.size(), "Unholy Water", "Potion 1", "../images/potion1.png", 1));
		entries.add(new FoodItem(entries.size(), "Dragon Breath Ale", "Potion 2", "../images/potion2.png", 1));
		getServletContext().setAttribute("entries", entries);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		response.setContentType("text/html");

		out.println("<head>");
		out.println("<style>body { " +
		"}</style>");
		out.println("</head>");

		out.println("<h1> Hello food item list! </h1>");
		out.println("<table>");
		for (FoodItem entry: entries) {
			out.println(
				"<tr>" + 
					"<td>" + entry.getName() + "</td>" + 
					"<td>" + entry.getDescription() + "</td>" +
					"<td><a href='foods/edit?id=" + entry.getId() + "'>Edit</a> " + 
					"<a href='foods/delete?id=" + entry.getId() + "'>Delete</a></td>" +
				"</tr>"
			);
		}
		out.println("</table>");
		out.println("<a href='foods/create'>Add Food Here!</a>");
	}
}
