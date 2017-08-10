import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup=1, urlPatterns={"/board-games"}) 
public class BoardGamesServlet extends HttpServlet {

	public void init() {
		List<Card> minorArcanaDeck = new ArrayList<>();
		getServletContext().setAttribute("minorArcanaDeck", minorArcanaDeck);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Card> minorArcanaDeck = (List<Card>) getServletContext().getAttribute("minorArcanaDeck");

		request.setAttribute("minorArcanaDeck", minorArcanaDeck);

		request.getRequestDispatcher("/WEB-INF/board-games.jsp")
			.forward(request, response);
	}
}