import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup=1, urlPatterns={"/admin/foods"}) 
public class FoodItemListAdminServlet extends HttpServlet {

	public void init() {
		List<FoodItem> inventory = new ArrayList<>();
		inventory.add(new FoodItem(0, "Unholy Water", 
			"Cursed and hexed by numerous witches, this potion has been known to induce aggression and spite in its drinkers.", 
			"images/potion1.png", 6.6));
		inventory.add(new FoodItem(1, "Dragon Breath Ale", 
			"Since the days of old, knights have been known to consume the scorched liquor of dragons to acquire immunity to flame.", 
			"images/potion2.png", 8.0));
		inventory.add(new FoodItem(2, "Matrix Mead", 
			"What happens if you mix a blue pill and a red pill together? Only the truly brave would dare to find out...", 
			"images/potion3.png", 2.5));
		inventory.add(new FoodItem(3, "Stealth Swill", 
			"Want to spy on your mortal enemy? A swig of this swill will turn you invisible. Even the bottle itself is disappearing!", 
			"images/potion4.png", 4.0));
		inventory.add(new FoodItem(4, "Gnomish Gin", 
			"This refreshing concoction has been imbued with secretive gnomish medicinal herbs, perfect for all your combat ailments.", 
			"images/potion5.png", 1.5));
		getServletContext().setAttribute("inventory", inventory);
	}
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodItem> inventory = (List<FoodItem>) getServletContext().getAttribute("inventory");
		request.setAttribute("inventory", inventory);
		request.getRequestDispatcher("/WEB-INF/admin/inventory.jsp")
            .forward(request, response);
	}
}
