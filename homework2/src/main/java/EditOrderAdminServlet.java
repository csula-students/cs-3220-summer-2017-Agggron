import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/orders/edit") 
public class EditOrderAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		List<Order> orders = (List<Order>) getServletContext().getAttribute("orders");

		Order orderToEdit = null;
		for (Order order : orders) {
			if (order.getId() == id) {
				orderToEdit = order;
			}
		}

		request.setAttribute("orderToEdit", orderToEdit);
		request.getRequestDispatcher("/WEB-INF/admin/edit-order.jsp")
			.forward(request, response);
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		List<Order> orders = (List<Order>) getServletContext().getAttribute("orders");
		
		Order orderToEdit = null;
		int index = -1;
		for (int i = 0; i < orders.size(); i ++) {
			if (orders.get(i).getId() == id) {
				orderToEdit = orders.get(i);
				index = i;
			}
		}
		
		orders.set(index, new Order(
			orderToEdit.getId(),
			orderToEdit.getItems(),
			orderToEdit.getCustomerName(),
			request.getParameter("new_status"),
			orderToEdit.getOrderTime()
		));

		getServletContext().setAttribute("orders", orders);

		response.sendRedirect("../orders");
	}
}
