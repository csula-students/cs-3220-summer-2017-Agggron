import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board-games/tarot-card-blackjack") 
public class BoardGamesTarotCardBlackjackServlet extends HttpServlet {
	
	public void init() {
		List<Card> tarotCardBlackjackDeck = new ArrayList<>();
		addToTarotCardBlackjackDeck(tarotCardBlackjackDeck, 1, "Cups");
		addToTarotCardBlackjackDeck(tarotCardBlackjackDeck, 15, "Pents");
		addToTarotCardBlackjackDeck(tarotCardBlackjackDeck, 29, "Swords");
		addToTarotCardBlackjackDeck(tarotCardBlackjackDeck, 33, "Wands");
		getServletContext().setAttribute("tarotCardBlackjackDeck", tarotCardBlackjackDeck);
	}

	public void addToTarotCardBlackjackDeck(List<Card> deck, int startingId, String suit) {
		// for each suit, cards 1-9 have values 1-9 (as on the card)
		for (int i = 1; i < 10; i++) {
			deck.add(new Card(startingId, suit, i, "images/deck/" + suit + "0" + i + ".jpg"));
		}
		// for each suit, cards 10-14 (10/Page/Knight/Queen/King) all have value 10
		for (int i = 10; i < 15; i++) {
			deck.add(new Card(startingId + 9, suit, 10, "images/deck/" + suit + i + ".jpg"));
		}
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Card> tarotCardBlackjackDeck = (List<Card>) getServletContext().getAttribute("tarotCardBlackjackDeck");
		
		request.setAttribute("tarotCardBlackjackDeck", tarotCardBlackjackDeck);

		request.getRequestDispatcher("/WEB-INF/tarot-card-blackjack.jsp")
			.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/tarot-card-blackjack.jsp")
			.forward(request, response);		
	}
}