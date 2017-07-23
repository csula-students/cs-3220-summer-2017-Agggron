import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/shopping-cart/submit-order") 
public class SubmitOrderServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");


		List<Order> orders = (List<Order>) getServletContext().getAttribute("orders");

		orders.add(new Order(
			orders.size(),
			cart,
			"Eric",
			"IN_PROGRESS",
			new Date()
			));

		cart = new ArrayList<>();

		getServletContext().setAttribute("orders", orders);
		getServletContext().setAttribute("cart", cart);
		
		response.sendRedirect("../orders");
	}
}
