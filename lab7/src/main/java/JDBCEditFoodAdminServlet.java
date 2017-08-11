import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jdbc/admin/foods/edit") 
public class JDBCEditFoodAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		JDBCFoodItemDAO dao = new JDBCFoodItemDAO();
		JDBCFoodItem itemToEdit = dao.get(id).get();

		request.setAttribute("itemToEdit", itemToEdit);

		request.getRequestDispatcher("/WEB-INF/jdbc/admin/edit-food.jsp")
            .forward(request, response);
	}

	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JDBCFoodItem entryToUpdate = new JDBCFoodItem(
			Integer.parseInt(request.getParameter("id")),
			request.getParameter("name"),
			request.getParameter("description"),
			request.getParameter("imgURL"),
			Double.parseDouble(request.getParameter("price"))
			);

		JDBCFoodItemDAO dao = new JDBCFoodItemDAO();
		dao.update(entryToUpdate);

		response.sendRedirect("../foods");
	}
}
