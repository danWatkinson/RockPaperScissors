import player.Player;
import selection.InvalidSelection;
import selection.Selection;
import selection.SelectionBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RockPaperScissorsGame {
    private final BufferedReader in;
    private final PrintStream out;
    private Player player2;

    public static void main(String[] args) {
        new RockPaperScissorsGame(new BufferedReader(new InputStreamReader(System.in)), System.out)
                .start();
    }

    public RockPaperScissorsGame(final BufferedReader in, final PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void start() {
        Selection playersSelection = getSelection();
        out.println("You selected " + playersSelection);
        if (player2 != null) {
            player2.makeSelection();
        }
    }

    private Selection getSelection() { //TODO - this looks like something to pop out into another class, but I'm going to go ahead and
                                       // look at the next acceptance criteria and see where that takes me, right now it's just one
                                       // 'slightly rogue' method
        out.println("Please select 'Rock', 'Paper', or 'Scissors'");
        SelectionBuilder selectionBuilder = new SelectionBuilder();
        Selection selection = new InvalidSelection();

        while (!selection.isValid()) {
            try {
                selection = selectionBuilder.parse(in.readLine());
                if (!selection.isValid()) {
                    out.println("Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors");
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to read input.", e);
            }
        }
        return selection;
    }

    public void setPlayer2(final Player player2) {
        this.player2 = player2;
    }
}
