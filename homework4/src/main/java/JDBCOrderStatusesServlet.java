import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup=1, urlPatterns={"/jdbc/orders"}) 
public class JDBCOrderStatusesServlet extends HttpServlet {

	public void init() {
		List<JDBCOrder> orders = new ArrayList<>();
		getServletContext().setAttribute("jdbc-orders", orders);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JDBCOrder> orders = (List<JDBCOrder>) getServletContext().getAttribute("jdbc-orders");

		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/WEB-INF/jdbc/orders.jsp")
			.forward(request, response);
	}
}
