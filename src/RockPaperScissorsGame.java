import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RockPaperScissorsGame {
    private final BufferedReader in;
    private final PrintStream out;

    public static void main(String[] args) {
        new RockPaperScissorsGame(new BufferedReader(new InputStreamReader(System.in)), System.out)
                .start();
    }

    public RockPaperScissorsGame(final BufferedReader in, final PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void start() {
        out.println("Please select 'Rock', 'Paper', or 'Scissors'");
        try {
            boolean gotSomeValidInput = false;
            while (!gotSomeValidInput) {
                String input = in.readLine();
                if (isValidInput(input)) {
                    gotSomeValidInput = true;
                    out.println("You selected " + input);
                } else {
                    out.println("Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input.", e);
        }
    }

    private boolean isValidInput(final String input) {
        return "Rock".equals(input) || "Paper".equals(input) || "Scissors".equals(input);
    }
}
