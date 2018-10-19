//project C not bonus
//name:Zhijia Lu Id:921715 username:zhijial

import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class Nimsys {
	// create constructor of NimPlayer
	ArrayList<NimPlayer> userList = new ArrayList<NimPlayer>();

	public static void main(String[] args) {

		System.out.println("Welcome to Nim");

		new Nimsys().gameProcess();
	}

	public void gameProcess() {

		if (userList.size() > 100) {
			System.out.println("Sorry, the maximum player number should be 100, you need to delete"
					+ (userList.size() - 100) + "players.");
		}
		Scanner inputStream = null;

		//get history from file
		try {
			NimHumanPlayer newHumanplayer = new NimHumanPlayer();
			NimAIPlayer newAIplayer = new NimAIPlayer();
			inputStream = new Scanner(new FileInputStream("players.dat"));
			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				String[] history = line.split("[\\s|,]+");
				if (Boolean.parseBoolean(history[6]) == true) {
					newAIplayer = new NimAIPlayer(history[0], history[1], history[2], Integer.parseInt(history[3]),
							Integer.parseInt(history[4]), Double.parseDouble(history[5]),
							Boolean.parseBoolean(history[6]));
					userList.add(newAIplayer);
				}
				if (Boolean.parseBoolean(history[6]) == false) {
					newHumanplayer = new NimHumanPlayer(history[0], history[1], history[2],
							Integer.parseInt(history[3]), Integer.parseInt(history[4]), Double.parseDouble(history[5]),
							Boolean.parseBoolean(history[6]));
					userList.add(newHumanplayer);
				}

			}
			inputStream.close();

		}

		catch (FileNotFoundException e3) {

		}

		Scanner keyboard = new Scanner(System.in);
		while (true) {

			try {
				System.out.print("\n$");

				//get and split input
				String state = keyboard.nextLine();
				String[] command = state.split("\\s+");

				if (command[0].equals("addplayer")) {
					NimHumanPlayer newplayer = new NimHumanPlayer();
					boolean check = false;
					try {
						if (command.length == 1) {
							throw new Exception("Incorrect number of arguments supplied to command.");
						} else {
							String[] arg = command[1].split(",+");
							if (arg.length < 3) {

								throw new Exception("Incorrect number of arguments supplied to command.");
							}

							newplayer = new NimHumanPlayer(arg[0], arg[1], arg[2], 0, 0, 0, false);
							
							//check whether player exist
							for (int i = 0; i < userList.size(); i++) {
								if (userList.get(i).getUserName().equals(arg[0])) {
									System.out.println("The player already exists.");
									check = true;
									break;
								}
							}
							// store the new player
							if (check == false) {
								userList.add(newplayer);
							}

						}
					} catch (Exception e1) {
						String message = e1.getMessage();
						System.out.println(message);

					}
				}

				if (command[0].equals("addaiplayer")) {

					NimAIPlayer newplayer = new NimAIPlayer();
					boolean check = false;
					try {
						if (command.length == 1) {
							throw new Exception("Incorrect number of arguments supplied to command.");
						} else {
							String[] arg = command[1].split(",+");
							if (arg.length < 3) {
								throw new Exception("Incorrect number of arguments supplied to command.");
							}
							newplayer = new NimAIPlayer(arg[0], arg[1], arg[2], 0, 0, 0, true);
							for (int i = 0; i < userList.size(); i++) {
								if (userList.get(i).getUserName().equals(arg[0])) {
									System.out.println("The player already exists.");
									check = true;
									break;
								}
							}
							// store the new player
							if (check == false) {
								userList.add(newplayer);
							}

						}
					} catch (Exception e1) {
						String message = e1.getMessage();
						System.out.println(message);

					}
				}

				if (command[0].equals("removeplayer")) {

					try {

						if (command.length != 1) {
							String[] arg = command[1].split(",+");
							int rem;
							loop: while (true) {
								for (int j = 0; j < userList.size(); j++) {
									// if input player exists
									if (userList.get(j).getUserName().equals(arg[0])) {
										userList.remove(j);
										break loop;
									}

								}
								// if input player does not exist
								System.out.println("The player does not exist.");
								break loop;
							}

						}

						// when the input is in the form "removeplayer"
						else {
							System.out.println("Are you sure you want to remove all players? (y/n)");
							String answer = keyboard.next().toLowerCase();
							String junk = keyboard.nextLine();
							if (answer.equals("y")) {

								userList.clear();

							}

						}

					} catch (Exception e1) {
						String message = e1.getMessage();
						System.out.println(message);

					}
				}

				if (command[0].equals("editplayer")) {

					try {
						if (command.length == 1) {
							throw new Exception("Incorrect number of arguments supplied to command.");
						} else {
							String[] arg = command[1].split(",+");
							if (arg.length < 3) {
								throw new Exception("Incorrect number of arguments supplied to command.");
							}
							loop: while (true) {
								for (int i = 0; i < userList.size(); i++) {

									// input player exists
									if (userList.get(i).getUserName().equals(arg[0])) {
										userList.get(i).setFamilyName(arg[1]);
										userList.get(i).setGivenName(arg[2]);
										userList.get(i).setGameNumber(0);
										userList.get(i).setWonNumber(0);
										break loop;
									}
								}

								// input player does not exist
								System.out.println("The player does not exist.");
								break loop;
							}
						}
					} catch (Exception e1) {
						String message = e1.getMessage();
						System.out.println(message);

					}
				}

				if (command[0].equals("resetstats")) {

					try {

						if (command.length != 1) {
							String[] arg = command[1].split(",+");
							loop: while (true) {
								for (int i = 0; i < userList.size(); i++) {

									// when input player exists
									if (userList.get(i).getUserName().equals(arg[0])) {
										userList.get(i).setGameNumber(0);
										userList.get(i).setWonNumber(0);
										break loop;
									}
								}

								// when input player does not exist
								System.out.println("The player does not exist.");
								break loop;
							}
						}

						// when input is like "resetstats"
						else {
							System.out.println("Are you sure you want to reset all player statistics? (y/n)");
							String answer = keyboard.next().toLowerCase();
							String junk = keyboard.nextLine();
							if (answer.equals("y")) {
								for (int i = 0; i < userList.size(); i++) {
									userList.get(i).setGameNumber(0);
									userList.get(i).setWonNumber(0);
								}
							}
						}
					} catch (Exception e1) {
						String message = e1.getMessage();
						System.out.println(message);

					}
				}

				if (command[0].equals("displayplayer")) {

					try {

						//check whether player exist
						if (command.length != 1) {
							String[] arg = command[1].split(",+");
							loop: while (true) {
								for (int i = 0; i < userList.size(); i++) {
									if (userList.get(i).getUserName().equals(arg[0])) {
										System.out.println(userList.get(i).getUserName() + ","
												+ userList.get(i).getGivenName() + "," + userList.get(i).getFamilyName()
												+ "," + userList.get(i).getGameNumber() + " games,"
												+ userList.get(i).getWonNumber() + " wins");
										break loop;
									}
								}
								System.out.println("The player does not exist.");
								break loop;
							}
						} else {

							//display all
							new NimSort().sort(userList);
							for (int i = 0; i < userList.size(); i++) {
								System.out.println(userList.get(i).getUserName() + "," + userList.get(i).getGivenName()
										+ "," + userList.get(i).getFamilyName() + "," + userList.get(i).getGameNumber()
										+ " games," + userList.get(i).getWonNumber() + " wins");

							}
						}
					} catch (Exception e1) {
						String message = e1.getMessage();
						System.out.println(message);

					}
				}

				if (command[0].equals("rankings")) {
					DecimalFormat df = new DecimalFormat("00");
					DecimalFormat DF = new DecimalFormat("0%");
					try {

						boolean check = false;
						if (command.length > 1) {
							String[] arg = command[1].split(",+");

							if (command.length == 2 && arg[0].equals(new String("asc"))) {

								check = true;
								// apply ranking asc method
								new NimSort().ratioSortAsc(userList);
								for (int i = 0; i < Math.min(10, userList.size()); i++) {

									System.out.print(String.format("%-4s", DF.format(userList.get(i).getRatio())));
									System.out.println(" | " + df.format(userList.get(i).getGameNumber()) + " games"
											+ " | " + userList.get(i).getGivenName() + " "
											+ userList.get(i).getFamilyName());

								}

							}

						}

						if (check == false) {
							// apply ranking desc method
							new NimSort().ratioSortDesc(userList);

							for (int i = 0; i < Math.min(10, userList.size()); i++) {

								System.out.print(String.format("%-4s", DF.format(userList.get(i).getRatio())));
								System.out.println(" | " + df.format(userList.get(i).getGameNumber()) + " games" + " | "
										+ userList.get(i).getGivenName() + " " + userList.get(i).getFamilyName());

							}
						}

					} catch (Exception e1) {
						String message = e1.getMessage();
						System.out.println(message);

					}
				}

				if (command[0].equals("startgame")) {

					try {
						if (command.length == 1) {
							throw new Exception("Incorrect number of arguments supplied to command.");
						} else {
							String[] arg = command[1].split(",+");
							if (arg.length < 4) {
								throw new Exception("Incorrect number of arguments supplied to command.");

							}

							if (!arg[0].matches("[0-9]+") || !arg[1].matches("[0-9]+")) {
								throw new Exception(
										"Invalid input of initial number or upper bound, both need to be integer!");
							}
							int initialStone = Integer.parseInt(arg[0]);
							int upperBound = Integer.parseInt(arg[1]);

							NimPlayer p1 = null;
							NimPlayer p2 = null;
							for (int i = 0; i < userList.size(); i++) {
								if (userList.get(i).getUserName().equals(arg[2])) {
									p1 = userList.get(i);
								}
								if (userList.get(i).getUserName().equals(arg[3])) {
									p2 = userList.get(i);
								}
							}

							if (p1 != null && p2 != null) {

								System.out.println("\nInitial stone count: " + initialStone);
								System.out.println("Maximum stone removal: " + upperBound);
								System.out.println("Player 1: " + p1.getGivenName() + " " + p1.getFamilyName());
								System.out.println("Player 2: " + p2.getGivenName() + " " + p2.getFamilyName() + "\n");

								NimGame gameInfo = new NimGame(initialStone, upperBound, "default", "default");

								// apply game process
								gameInfo.startGame(keyboard, p1, p2);

								// update the statics of each player after game
								for (int k = 0; k < userList.size(); k++) {
									if (userList.get(k).getUserName().equals(gameInfo.getWinner())) {
										userList.get(k).addWonNumber();
										userList.get(k).addGameNumber();

									}

									if (userList.get(k).getUserName().equals(gameInfo.getLoser())) {
										userList.get(k).addGameNumber();
									}

									if (userList.get(k).getGameNumber() != 0) {
										userList.get(k).setRatio();
									}
								}

							}

							else {
								// players does not exist
								System.out.println("One of the players does not exist.");

							}
						}
					} catch (Exception e1) {
						String message = e1.getMessage();
						System.out.println(message);

					}

				}

				if (command[0].equals("exit")) {
					try {

						PrintWriter outputStream = null;

						//write players
						outputStream = new PrintWriter(new FileOutputStream("players.dat"));
						for (int i = 0; i < userList.size(); i++) {
							if (!userList.get(i).getUserName().equals("default")) {
								outputStream.println(userList.get(i).getUserName() + ","
										+ userList.get(i).getFamilyName() + "," + userList.get(i).getGivenName() + ","
										+ userList.get(i).getGameNumber() + "," + userList.get(i).getWonNumber() + ","
										+ userList.get(i).getRatio() + "," + userList.get(i).getCheck());
							}
						}

						outputStream.close();
						System.out.print("\n");

						System.exit(0);
					} catch (FileNotFoundException e2) {
						System.out.println("Mistake on output,please try again.");
					}

				}

				if (!command[0].equals("addplayer") && !command[0].equals("addaiplayer")
						&& !command[0].equals("editplayer") && !command[0].equals("removeplayer")
						&& !command[0].equals("displayplayer") && !command[0].equals("resetstats")
						&& !command[0].equals("rankings") && !command[0].equals("startgame")
						&& !command[0].equals("exit")) {
					throw new Exception("'" + command[0] + "' is not a valid command.");
				}
			} catch (Exception e) {
				String message = e.getMessage();
				System.out.println(message);

			}

		}
	}
}
		