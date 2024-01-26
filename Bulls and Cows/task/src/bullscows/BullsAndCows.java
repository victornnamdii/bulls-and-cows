package bullscows;

public class BullsAndCows {
    final private String SECRET;
    final public static String ALL_SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz";
    final public static byte MAX_SECRET_LENGTH = 36;

    BullsAndCows(int secretLength, int numberOfSymbols) {
        if (secretLength == 0) {
            throw new GameException(
                    "it's not possible to generate a code with a length of 0."
            );
        }

        if (numberOfSymbols < secretLength) {
            throw new GameException(
                    String.format(
                            "it's not possible to generate a code with a length of %d with %d unique symbols.",
                            secretLength, numberOfSymbols
                    )
            );
        }
        this.SECRET = this.generateSecret(secretLength, numberOfSymbols);

        System.out.print("The secret is prepared: ");
        for (int i = 0; i < this.SECRET.length(); i++) {
            System.out.print("*");
            if (i == this.SECRET.length() - 1) {
                System.out.print(" ");
            }
        }

        if (numberOfSymbols > 10) {
            System.out.printf(
                    "(0-9, a-%s)\n",
                    ALL_SYMBOLS.charAt(numberOfSymbols - 1)
            );
        } else {
            System.out.printf("(0-%d)\n", numberOfSymbols - 1);
        }
    }

    boolean gradeGuess(String guess) {
        if (guess.length() != this.SECRET.length()) {
            throw new GameException(
                    String.format(
                            "your answer should be of length %d.\n",
                            this.SECRET.length()
                    )
            );
        }

        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < SECRET.length(); i++) {
            String symbol = guess.substring(i, i + 1);
            if (!ALL_SYMBOLS.contains(symbol)) {
                throw new GameException("invalid symbols!");
            }

            if (guess.charAt(i) == SECRET.charAt(i)) {
                bulls++;
            } else if (SECRET.contains(guess.substring(i, i + 1))) {
                cows++;
            }
        }

        StringBuilder result = new StringBuilder("Grade: ");
        if (bulls > 0) {
            result.append(String.format("%d bull(s)", bulls))
                    .append(cows > 0
                            ? String.format(" and %d cow(s).", cows)
                            : "."
                    );
        } else if (cows > 0) {
            result.append(String.format("%d cow(s)", cows));
        } else {
            result.append("None.");
        }

        System.out.println(result);

        return bulls == this.SECRET.length();
    }

    private String generateSecret(int length, int numberOfSymbols) {
        if (length > MAX_SECRET_LENGTH) {
            throw new GameException(
                    String.format(
                            "can't generate a secret number with a length of %d because there aren't enough unique digits.",
                            length
                    )
            );
        }

        if (numberOfSymbols > MAX_SECRET_LENGTH) {
            throw new GameException(
                    "maximum number of possible symbols in the code is 36 (0-9, a-z)."
            );
        }

        StringBuilder code = new StringBuilder ();

        while (code.length() < length) {
            int index = (int) (Math.random() * numberOfSymbols);

            String symbol = ALL_SYMBOLS.substring(index, index + 1);
            if (code.indexOf(symbol) == -1) {
                code.append(ALL_SYMBOLS.charAt(index));
            }
        }

        return code.toString();
    }
}