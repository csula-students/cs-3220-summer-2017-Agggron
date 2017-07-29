import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup=1, urlPatterns={"admin/foods"}) 
public class FoodItemListAdminServlet extends HttpServlet {

	public void init() {
		List<FoodItem> inventory = new ArrayList<>();
		inventory.add(new FoodItem(0, "Unholy Water", "Potion 1", "../../images/potion1.png", 2.5));
		inventory.add(new FoodItem(1, "Dragon Breath Ale", "Potion 2", "../../images/potion2.png", 2.5));
		getServletContext().setAttribute("inventory", inventory);
	}
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> inventory = (List<FoodItem>) getServletContext().getAttribute("inventory");
		request.setAttribute("inventory", inventory);
		request.getRequestDispatcher("../WEB-INF/admin/inventory.jsp")
            .forward(request, response);
	}
}
