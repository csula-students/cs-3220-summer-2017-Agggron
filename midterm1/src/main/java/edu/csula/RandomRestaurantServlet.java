package edu.csula;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/suggest/restaurants/random")
public class RandomRestaurantServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (request.getParameter("designRating") != null) {
			String restaurantId = request.getParameter("restaurantId");
			int designRating = (int) Integer.parseInt(request.getParameter("designRating"));
			int tasteRating = (int) Integer.parseInt(request.getParameter("tasteRating"));
			out.println("You gave Restaurant " + restaurantId + " a design rating of " + designRating + " and taste rating of " + tasteRating);


			List<Restaurant> restaurants = (List<Restaurant>) getServletContext().getAttribute("restaurants");
			Restaurant leEntry = null;
			int index = -1;
			for (int i = 0; i < restaurants.size(); i ++) {
				//out.println("Checking" + restaurants.get(i).getId());
				if (restaurants.get(i).getId() == restaurantId) {

					leEntry = restaurants.get(i);
					index = i;
				}
			}
			if (leEntry != null) {
				List<Integer> newDesignRatings = leEntry.designRatings;
				newDesignRatings.add(designRating);
				List<Integer> newTasteRatings = leEntry.tasteRatings;
				newTasteRatings.add(tasteRating);
				restaurants.set(index, new Restaurant(
					leEntry.getId(), 
					leEntry.getName(),
					leEntry.getUrl(),
					newDesignRatings,
					newTasteRatings
				));
				getServletContext().setAttribute("restaurants", restaurants);
			}



			//out.println(randomRestaurantId);
		}

		out.println("<head>");
		out.println("<style>body { " +
		"}</style>");
		out.println("</head>");

		out.println("<h1>What's for lunch?</h1>");

		out.println("<form method=\"post\">");
		out.println("<input type='submit' name='random_restaurant' value='Feeling Lucky?'>");
		out.println("</form>");

		out.println("<form method=\"post\">");
		out.println("<input type='submit' name='restaurant_list' value='See the list!'>");
		out.println("</form>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Restaurant> restaurants = (List<Restaurant>) getServletContext().getAttribute("restaurants");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		if (request.getParameter("random_restaurant") != null) {
			// go to the ratings creen for the individual restaraunt
			Restaurant randomRestaurant = getRandomRestaurant(restaurants);
			String randomRestaurantId = randomRestaurant.getId();

			out.println("<head>");
			out.println("<style>body { " +
			"}</style>");
			out.println("</head>");

			out.println("<h1>You should go to: </h1>");

			out.println("<img src='https://raw.githubusercontent.com/csula/cs3220-summer-2017/master/notes/imgs/restaurant-placeholder.png'><br>");

			out.println("<a href=" + randomRestaurant.getUrl() + ">" + randomRestaurant.getName() + "</a><br><br>");

			out.println("<form method='get'>");
			out.println("<input type='text' readonly='true' name='restaurantId' value=" + randomRestaurantId + ">RATE ME</input><br><br>");
			out.println("Design Rating:<br>");
			out.println("<input type='radio' name='designRating' id='dr1' value='1' checked>1<br>");
			out.println("<input type='radio' name='designRating' id='dr2' value='2'>2<br>");
			out.println("<input type='radio' name='designRating' id='dr3' value='3'>3<br>");
			out.println("<input type='radio' name='designRating' id='dr4' value='4'>4<br>");
			out.println("<input type='radio' name='designRating' id='dr5' value='5'>5<br><br>");

			out.println("Taste Rating:<br>");
			out.println("<input type='radio' name='tasteRating' id='tr1' value='1' checked>1<br>");
			out.println("<input type='radio' name='tasteRating' id='tr1' value='2'>2<br>");
			out.println("<input type='radio' name='tasteRating' id='tr3' value='3'>3<br>");
			out.println("<input type='radio' name='tasteRating' id='tr4' value='4'>4<br>");
			out.println("<input type='radio' name='tasteRating' id='tr5' value='5'>5<br><br>");

			out.println("<input type='submit' name='submit_rating'></input>");
			out.println("</form>");	

			out.println("<form method=\"post\">");
			out.println("<input type='submit' name='random_restaurant' value='Feeling Lucky?'>");
			out.println("</form>");

			out.println("<form method=\"post\">");
			out.println("<input type='submit' name='restaurant_list' value='See the list!'>");
			out.println("</form>");
		} 

		else if (request.getParameter("restaurant_list") != null) {
			// go to the list of restaurants servlet page
			response.sendRedirect("cs3220xstu19/suggest/restaurants/random/list");
		} 

}

	public Restaurant getRandomRestaurant(List<Restaurant> list) {
		return list.get(new Random().nextInt(list.size()));
	}
}


