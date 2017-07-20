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

@WebServlet(loadOnStartup=1, urlPatterns={"admin/orders"}) 
public class OrderStatusesAdminServlet extends HttpServlet {

	public void init() {
		List<Order> orders = new ArrayList<>();
		FoodItem unholyWater = new FoodItem(0, "Unholy Water", "Potion 1", "../../images/potion1.png", 2.5);
		List<FoodItem> foodsInOrder = new ArrayList<>();
		foodsInOrder.add(unholyWater);
		orders.add(new Order(0, foodsInOrder, "Eric", "IN_PROGRESS", new Date(7, 18, 95)));

		getServletContext().setAttribute("orders", orders);
	}
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> orders = (List<Order>) getServletContext().getAttribute("orders");
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("../WEB-INF/admin/order-statuses.jsp")
            .forward(request, response);
	}
}
