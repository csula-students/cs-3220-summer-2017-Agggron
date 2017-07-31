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

@WebServlet("/jdbc/shopping-cart/submit-order") 
public class JDBCSubmitOrderServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JDBCFoodItem> cart = (List<JDBCFoodItem>) getServletContext().getAttribute("jdbc-cart");
		List<JDBCOrder> orders = (List<JDBCOrder>) getServletContext().getAttribute("jdbc-orders");

		orders.add(new JDBCOrder(
			orders.size(),
			cart,
			"Eric",
			"IN QUEUE",
			new Date()
			));

		cart = new ArrayList<>();

		getServletContext().setAttribute("jdbc-orders", orders);
		getServletContext().setAttribute("jdbc-cart", cart);
		
		response.sendRedirect("../orders");
	}
}
