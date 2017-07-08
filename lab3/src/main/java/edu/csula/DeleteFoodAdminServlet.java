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

@WebServlet("/admin/foods/delete")
public class DeleteFoodAdminServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		int index = -1;
		for (int i = 0; i < entries.size(); i ++) {
			if (entries.get(i).getId() == id) {
				index = i;
			}
		}
		entries.remove(index);
		getServletContext().setAttribute("entries", entries);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello delete food!</h1>");
		out.println("<a href='../foods'>Return to Food Item List</a>");
	}
}
