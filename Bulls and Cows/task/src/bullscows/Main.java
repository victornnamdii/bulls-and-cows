package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String line = null;
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Input the length of the secret code:");
            line = scanner.nextLine();
            int secretLength = Integer.parseInt(line);

            System.out.println("Input the number of possible symbols in the code:");
            line = scanner.nextLine();
            int numberOfSymbols = Integer.parseInt(line);

            BullsAndCows game = new BullsAndCows(secretLength, numberOfSymbols);
            System.out.println("Okay, let's start a game!");

            int turn = 1;
            while (true) {
                System.out.printf("Turn %d:\n", turn);
                String guess = scanner.nextLine();

                if (game.gradeGuess(guess)) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                }

                turn++;
            }
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", line);
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }
    }
}
