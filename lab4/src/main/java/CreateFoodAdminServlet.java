import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("admin/foods/create") 
public class CreateFoodAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> inventory = (List<FoodItem>) getServletContext().getAttribute("inventory");
		request.setAttribute("inventory", inventory);
		request.getRequestDispatcher("../../WEB-INF/admin/create-food.jsp")
            .forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> inventory = (List<FoodItem>) getServletContext().getAttribute("inventory");
		
		int id = inventory.size();
		String name = request.getParameter("name");
		String imgURL = request.getParameter("imgURL");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));

		inventory.add(new FoodItem(id, name, description, imgURL, price));
		getServletContext().setAttribute("inventory", inventory);

		response.sendRedirect("../foods");
	}
}
