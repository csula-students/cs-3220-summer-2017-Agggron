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

@WebServlet("/jdbc/admin/orders") 
public class JDBCOrderStatusesAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JDBCOrder> orders = (List<JDBCOrder>) getServletContext().getAttribute("jdbc-orders");
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/WEB-INF/jdbc/admin/order-statuses.jsp")
            .forward(request, response);
	}
}
