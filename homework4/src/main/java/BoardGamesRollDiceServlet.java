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

@WebServlet("/board-games/roll-dice") 
public class BoardGamesRollDiceServlet extends HttpServlet {
	
	public void init() {
		
		int startingDiceGameScore = 0;
		getServletContext().setAttribute("diceScore", startingDiceGameScore);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("rolledDiceResult", "start");

		request.getRequestDispatcher("/WEB-INF/roll-dice.jsp")
			.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("roll") != null) {
			// gets the user's guess and the random dice roll
			int guess = Integer.parseInt(request.getParameter("guess"));
			int diceRoll = getRandomDieRoll();

			// logic for if the guess was correct or not 
			int diceScore = (int) getServletContext().getAttribute("diceScore");
			if (guess == diceRoll) {
				diceScore += 1;
				request.setAttribute("rolledDiceResult", "correct");
			} else {
				request.setAttribute("rolledDiceResult", "incorrect");
			}

			// stores the score, as well as the dice roll
			getServletContext().setAttribute("diceScore", diceScore);
			request.setAttribute("diceScore", diceScore);
			request.setAttribute("guess", guess);
			request.setAttribute("dice", new Dice(diceRoll, diceRoll, "images/dice" + diceRoll + ".jpg"));
		}

		else if (request.getParameter("reset") != null) {
			getServletContext().setAttribute("diceScore", 0);
			request.setAttribute("diceScore", 0);
			request.setAttribute("rolledDiceResult", "start");
		}
		
		request.getRequestDispatcher("/WEB-INF/roll-dice.jsp")
			.forward(request, response);		
	}

	public int getRandomDieRoll() {
		return (int) Math.floor((Math.random() * 6) + 1);
	}
}