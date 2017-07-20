import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("admin/foods/delete") 
public class DeleteFoodAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> inventory = (List<FoodItem>) getServletContext().getAttribute("inventory");
		
		int index = -1;
		for (int i = 0; i < inventory.size(); i ++) {
			if (inventory.get(i).getId() == id) {
				index = i;
			}
		}
		inventory.remove(index);
		getServletContext().setAttribute("inventory", inventory);

		response.sendRedirect("../foods");
	}
}
