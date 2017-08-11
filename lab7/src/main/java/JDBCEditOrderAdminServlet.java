import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jdbc/admin/orders/edit") 
public class JDBCEditOrderAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		/*List<JDBCOrder> orders = (List<JDBCOrder>) getServletContext().getAttribute("jdbc-orders");

		JDBCOrder orderToEdit = null;
		for (JDBCOrder order : orders) {
			if (order.getId() == id) {
				orderToEdit = order;
			}
		}*/

		JDBCOrderDAO dao = new JDBCOrderDAO();
		JDBCOrder orderToEdit = dao.get(id).get();

		request.setAttribute("orderToEdit", orderToEdit);
		request.getRequestDispatcher("/WEB-INF/jdbc/admin/edit-order.jsp")
			.forward(request, response);
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		/*List<JDBCOrder> orders = (List<JDBCOrder>) getServletContext().getAttribute("jdbc-orders");
		
		JDBCOrder orderToEdit = null;
		int index = -1;
		for (int i = 0; i < orders.size(); i ++) {
			if (orders.get(i).getId() == id) {
				orderToEdit = orders.get(i);
				index = i;
			}
		}
		
		orders.set(index, new JDBCOrder(
			orderToEdit.getId(),
			orderToEdit.getItems(),
			orderToEdit.getCustomerName(),
			request.getParameter("new_status"),
			orderToEdit.getOrderTime()
		));

		getServletContext().setAttribute("jdbc-orders", orders);*/

		JDBCOrderDAO dao = new JDBCOrderDAO();

		JDBCOrder orderToUpdate = new JDBCOrder(
												dao.get(id).get().getId(),
												dao.get(id).get().getItems(),
												dao.get(id).get().getCustomerName(),
												request.getParameter("new_status"),
												dao.get(id).get().getOrderTime()
												);
		dao.update(orderToUpdate);

		response.sendRedirect("../orders");
	}
}
