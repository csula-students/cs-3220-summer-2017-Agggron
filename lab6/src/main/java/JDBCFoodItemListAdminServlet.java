import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("jdbc/admin/foods") 
public class JDBCFoodItemListAdminServlet extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        JDBCFoodItemDAO dao = new JDBCFoodItemDAO();

        request.setAttribute("inventory", dao.list());

        request.getRequestDispatcher("/WEB-INF/admin/inventory.jsp")
            .forward(request, response);
	}
}
