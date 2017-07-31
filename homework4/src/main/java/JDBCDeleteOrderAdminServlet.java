import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jdbc/admin/orders/delete") 
public class JDBCDeleteOrderAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		List<JDBCOrder> orders = (List<JDBCOrder>) getServletContext().getAttribute("jdbc-orders");
		
		int index = -1;
		for (int i = 0; i < orders.size(); i ++) {
			if (orders.get(i).getId() == id) {
				index = i;
			}
		}
		orders.remove(index);
		getServletContext().setAttribute("jdbc-orders", orders);

		response.sendRedirect("../orders");
	}
}
