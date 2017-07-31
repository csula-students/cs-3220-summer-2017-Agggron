import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menu/add")
public class AddItemToCartServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodItem> inventory = (List<FoodItem>) getServletContext().getAttribute("inventory");
		
		FoodItem itemToAdd = null;
		for (FoodItem item : inventory) {
			if (item.getId() == id) {
				itemToAdd = item;
			}
		}

		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");
		boolean item_already_in_cart = false;
		for (FoodItem item : cart) {
			if (item.getId() == itemToAdd.getId()) {
				item_already_in_cart = true;
				int current_quantity = item.getQuantity();
				item.setQuantity(current_quantity + 1);
			}
		}
		if (item_already_in_cart == false) {
			cart.add(itemToAdd);
		}

		getServletContext().setAttribute("cart", cart);

		response.sendRedirect("../shopping-cart");
	}
}
