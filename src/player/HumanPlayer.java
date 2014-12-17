package player;

import selection.Selection;
import selection.SelectionBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class HumanPlayer implements Player {

    private final BufferedReader in;
    private final PrintStream out;

    public HumanPlayer(final BufferedReader in, final PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public Selection makeSelection() {
        out.println("Please select 'Rock', 'Paper', or 'Scissors'");
        SelectionBuilder selectionBuilder = new SelectionBuilder();
        Selection selection = Selection.Other;

        while (selection == Selection.Other) {
            try {
                selection = selectionBuilder.parse(in.readLine());
                if (selection == Selection.Other) {
                    out.println("Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors");
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to read input.", e);
            }
        }
        out.println("You selected " + selection);
        return selection;
    }

    @Override
    public void informOfOpponentsSelection(final Selection opponentsSelection) {
        out.println("The computer selected " + opponentsSelection);
    }

    @Override
    public void informOfResult(final Player winner) {
        if (winner == null) {
            out.println("draw");
        } else if (winner.equals(this)) {
            out.println("You win!");
        } else {
            out.println("You loose!");
        }
    }
}
