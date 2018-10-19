
/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/
import java.util.Random;

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the tasks.
	private int removenum;

	public NimAIPlayer() {
		super();

	}

	public NimAIPlayer(String theUserName, String theFamilyName, String theGivenName, int theGameNumber,
			int theWonNumber, double theRatio, boolean theCheckAI) {
		super(theUserName, theFamilyName, theGivenName, theGameNumber, theWonNumber, theRatio, theCheckAI);

	}

	//ai strategy
	public void removeStone(int upper, int lef) {

		//final remove
		if (lef == 1) {
			removenum = 1;
		}

		//remove so that only one left, make rival lose
		if (lef >= 2) {
			if (lef >= 2 && lef <= upper + 1) {
				removenum = lef - 1;
			}

			//randon output if meet k(M+1)+1
			if ((lef - 1) % (upper + 1) == 0) {
				while (true) {
					Random r = new Random();
					removenum = r.nextInt(Math.min(upper, lef) + 1);
					if (removenum != 0) {
						break;
					}

				}
			}

			//remove so that k(M+1)+1 stones left
			if ((lef - 1) % (upper + 1) != 0) {
				int j = 1;
				while (j <= Math.min(upper, lef)) {

					if ((lef - j - 1) % (upper + 1) == 0) {
						removenum = j;
						break;
					}
					j++;
				}

			}

		}
	}

	public int getRemove() {
		return removenum;
	}

	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";

		return move;
	}
}
