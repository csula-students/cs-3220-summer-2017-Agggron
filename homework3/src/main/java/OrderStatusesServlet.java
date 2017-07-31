import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup=1, urlPatterns={"/orders"}) 
public class OrderStatusesServlet extends HttpServlet {

	public void init() {
		List<Order> orders = new ArrayList<>();
		getServletContext().setAttribute("orders", orders);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/orders.jsp")
			.forward(request, response);
	}
}
