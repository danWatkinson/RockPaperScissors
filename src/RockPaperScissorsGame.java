import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Created by Dan on 17/12/2014.
 */
public class RockPaperScissorsGame {
    private final BufferedReader in;
    private final PrintStream out;

    public RockPaperScissorsGame(final BufferedReader in, final PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void start() {
        out.println("You selected Rock");
    }
}
