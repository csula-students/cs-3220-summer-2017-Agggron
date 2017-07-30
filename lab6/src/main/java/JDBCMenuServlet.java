import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("jdbc/menu")
public class JDBCMenuServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JDBCFoodItemDAO dao = new JDBCFoodItemDAO();

		request.setAttribute("inventory", dao.list());
		request.getRequestDispatcher("/WEB-INF/jdbc/menu.jsp")
			.forward(request, response);
	}
}
