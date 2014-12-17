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
        try {
            out.println("You selected " + in.readLine());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input.", e);
        }
    }
}
