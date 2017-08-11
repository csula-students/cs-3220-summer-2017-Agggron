import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jdbc/admin/foods/create") 
public class JDBCCreateFoodAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jdbc/admin/create-food.jsp")
            .forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JDBCFoodItemDAO dao = new JDBCFoodItemDAO();
		
		int id = dao.list().size() + 1;
		String name = request.getParameter("name");
		String imgURL = request.getParameter("imgURL");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));

		dao.add(new JDBCFoodItem(id, name, description, imgURL, price));

		response.sendRedirect("../foods");
	}
}