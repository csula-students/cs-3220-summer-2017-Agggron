import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup=1, urlPatterns={"/jdbc/shopping-cart"})
public class JDBCCartServlet extends HttpServlet {

	public void init() {
		List<JDBCFoodItem> cart = new ArrayList<>();
		getServletContext().setAttribute("jdbc-cart", cart);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JDBCFoodItem> cart = (List<JDBCFoodItem>) getServletContext().getAttribute("jdbc-cart");
		request.setAttribute("cart", cart);
		
		request.getRequestDispatcher("/WEB-INF/jdbc/shopping-cart.jsp")
			.forward(request, response);
	}
}
