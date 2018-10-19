import java.util.Scanner;

public class NimGame {
	private String winner, loser;
	private int upperBound, initial, remove;

	public NimGame(int theInitial, int theUpperBound, String theWinner, String theLoser) {
		initial = theInitial;
		upperBound = theUpperBound;
		winner = theWinner;
		loser = theLoser;

	}

	public NimGame() {

	}

	public String getWinner() {
		return winner;
	}

	public String getLoser() {
		return loser;
	}

	public void startGame(Scanner keyboard, NimPlayer player1, NimPlayer player2) {
		NimAIPlayer user = new NimAIPlayer();
		String input;
		int left = initial;
		int i = 1;
		loop: while (i > 0) {
			
			try {
				System.out.print(left + " stones left:");
				for (int j = 1; j <= left; j++) {
					System.out.print(" *");
				}
				System.out.println();
				
				// let player1 play
				if (i % 2 != 0 && left >= 0) {
					System.out.println(player1.getGivenName() + "'s turn - remove how many?\n");
					//humanplayer
					if (player1.getCheck() == false) {
						loop1: while (true) {
						
							input = keyboard.nextLine();

							if (!input.matches("[0-9]+")) {
								throw new Exception("Invalid remove. You must remove integer!\n");

							}
							remove = Integer.parseInt(input);
							if (remove < 1 || remove > Math.min(upperBound, left)) {
								throw new Exception("Invalid move. You must remove between 1 and "
										+ Math.min(upperBound, left) + " stones.\n");
							} else {
								left = left - remove;
								// check if player2 wins
								if (left == 0) {
									System.out.println("Game Over");
									System.out
											.print(player2.getGivenName() + " " + player2.getFamilyName() + " wins!\n");
									winner = player2.getUserName();
									loser = player1.getUserName();
									break loop;
								}

								if (left > 0) {

									break loop1;
								}
							}
						}
					}

					//ai player
					if (player1.getCheck() == true) {
						loop2: while (true) {

							user.removeStone(upperBound, left);
							
							//ai remove automatically
							remove = user.getRemove();
							left = left - remove;
							// check if player2 wins
							if (left == 0) {
								System.out.println("Game Over");
								System.out.print(player2.getGivenName() + " " + player2.getFamilyName() + " wins!\n");
								winner = player2.getUserName();
								loser = player1.getUserName();
								break loop;
							}
							if (left > 0) {
								break loop2;
							}
						}
					}

				}
				// let player2 play

				if (i % 2 == 0 && left >= 0) {
					System.out.println(player2.getGivenName() + "'s turn - remove how many?\n");
					//human player
					if (player2.getCheck() == false) {
						loop3: while (true) {

							
							input = keyboard.nextLine();

							if (!input.matches("[0-9]+")) {
								throw new Exception("Invalid remove. You must remove integer!\n");
							}
							remove = Integer.parseInt(input);
							if (remove < 1 || remove > Math.min(upperBound, left)) {
								throw new Exception("Invalid move. You must remove between 1 and "
										+ Math.min(upperBound, left) + " stones.\n");
							} else {					
								left = left - remove;
								// check if player1 wins
								if (left == 0) {
									System.out.println("Game Over");
									System.out
											.print(player1.getGivenName() + " " + player1.getFamilyName() + " wins!\n");
									winner = player1.getUserName();
									loser = player2.getUserName();
									break loop;
								}

								if (left > 0) {

									break loop3;
								}
							}

						}
					} 
					
					//ai player
					if (player2.getCheck() == true) {
						loop4: while (true) {


							user.removeStone(upperBound, left);
							
							//ai remove automatically
							remove = user.getRemove();
							left = left - remove;
							// check if player2 wins
							if (left == 0) {
								System.out.println("Game Over");
								System.out.print(player1.getGivenName() + " " + player1.getFamilyName() + " wins!\n");
								winner = player1.getUserName();
								loser = player2.getUserName();
								break loop;
							}
							if (left > 0) {

								break loop4;
							}
						}
					}
				}
				i++;
			}

			catch (Exception e1) {
				String message = e1.getMessage();
				System.out.println(message);

			}
			
		}
	}
}