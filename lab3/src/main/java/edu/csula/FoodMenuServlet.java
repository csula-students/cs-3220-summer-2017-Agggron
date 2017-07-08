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

@WebServlet("/menu")
public class FoodMenuServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<head>");
		out.println("<style>body { " +
		"}</style>");
		out.println("</head>");

		out.println("<h1>Hello food!</h1>");

		out.println("<table>");
		for (FoodItem entry: entries) {
			out.println(
				"<tr>" + 
					"<td>" + entry.getName() + "</td>" + 
					"<td>" + entry.getDescription() + "</td>" +
				"</tr>"
			);
		}
		out.println("</table>");
	}
}
