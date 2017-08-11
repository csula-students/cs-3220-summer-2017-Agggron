import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jdbc/menu/add")
public class JDBCAddItemToCartServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		JDBCFoodItemDAO dao = new JDBCFoodItemDAO();
		JDBCFoodItem itemToAdd = dao.get(id).get();

		List<JDBCFoodItem> cart = (List<JDBCFoodItem>) getServletContext().getAttribute("jdbc-cart");
		PrintWriter out = response.getWriter();
		boolean item_already_in_cart = false;
		for (JDBCFoodItem item : cart) {
			if (item.getId() == itemToAdd.getId()) {
				item_already_in_cart = true;
				int current_quantity = item.getQuantity();
				item.setQuantity(current_quantity + 1);
			}
		}
		if (item_already_in_cart == false) {
			cart.add(itemToAdd);
		}

		getServletContext().setAttribute("jdbc-cart", cart);

		response.sendRedirect("../shopping-cart");
	}
}
