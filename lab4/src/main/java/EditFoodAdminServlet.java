import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("admin/foods/edit") 
public class EditFoodAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> inventory = (List<FoodItem>) getServletContext().getAttribute("inventory");
		
		FoodItem itemToEdit = null;
		for (FoodItem item : inventory) {
			if (item.getId() == id) {
				itemToEdit = item;
			}
		}
		request.setAttribute("itemToEdit", itemToEdit);
		request.getRequestDispatcher("WEB-INF/edit-food.jsp")
            .forward(request, response);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("../foods");
	}
}
