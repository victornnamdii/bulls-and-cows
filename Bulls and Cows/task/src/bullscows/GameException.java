package bullscows;

public class GameException extends RuntimeException {
    GameException(String message) {
        super("Error: " + message);
    }
}