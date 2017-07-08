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

@WebServlet("/admin/foods/edit")
public class EditFoodAdminServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		FoodItem leEntry = null;
		for (FoodItem entry: entries) {
			if (entry.getId() == id) {
				leEntry = entry;
			}
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello edit food!</h1>");

		out.println("<form method=\"post\">");
		out.println("Your name: <input name='name' type='text' value='" + leEntry.getName() + "'/></br>");
		out.println("<textarea name='description'>" + leEntry.getDescription() + "</textarea></br>");
		out.println("<button>Edit</button>");
		out.println("</form>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		FoodItem leEntry = null;
		int index = -1;
		for (int i = 0; i < entries.size(); i ++) {
			if (entries.get(i).getId() == id) {
				leEntry = entries.get(i);
				index = i;
			}
		}
		entries.set(index, new FoodItem(
			leEntry.getId(),
			request.getParameter("name"),
			request.getParameter("description"),
			"", 1
		));
		getServletContext().setAttribute("entries", entries);

		response.sendRedirect("../foods");
	}
}
