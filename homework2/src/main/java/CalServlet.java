import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/cal")
public class CalServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/cal.jsp")
            .forward(request, response);
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int a = Integer.parseInt(request.getParameter("a"));
	    int b = Integer.parseInt(request.getParameter("b"));

        int sum = a + b;

        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("sum", sum);

        request.getRequestDispatcher("WEB-INF/cal-result.jsp")
            .forward(request, response);
	}
}