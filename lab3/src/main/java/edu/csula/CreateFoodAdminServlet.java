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

@WebServlet("/admin/foods/create")
public class CreateFoodAdminServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello create food!</h1>");
		
		out.println("<form method=\"post\">");
		out.println("Food Name: <input name='name' type='text'/></br>");
		out.println("<textarea name='description'></textarea></br>");
		out.println("<button>Add</button>");
		out.println("</form>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> entries = (List<FoodItem>) getServletContext().getAttribute("entries");
		entries.add(new FoodItem(entries.size(), request.getParameter("name"), request.getParameter("description"), "", 1));
		getServletContext().setAttribute("entries", entries);
		PrintWriter out = response.getWriter();
		out.println("<a href='../foods'>Return to Food Item List</a>");
}
}
