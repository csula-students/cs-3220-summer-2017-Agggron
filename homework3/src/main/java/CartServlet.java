import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup=1, urlPatterns={"/shopping-cart"})
public class CartServlet extends HttpServlet {

	public void init() {
		List<FoodItem> cart = new ArrayList<>();
		getServletContext().setAttribute("cart", cart);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> cart = (List<FoodItem>) getServletContext().getAttribute("cart");
		request.setAttribute("cart", cart);
		
		request.getRequestDispatcher("/WEB-INF/shopping-cart.jsp")
			.forward(request, response);
	}
}
