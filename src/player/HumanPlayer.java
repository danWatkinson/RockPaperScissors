package player;

import result.Result;
import selection.InvalidSelection;
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
        out.println("You selected " + selection);
        return selection;
    }

    @Override
    public void informOfOpponentsSelection(final Selection opponentsSelection) {
        out.println("The computer selected " + opponentsSelection);
    }

    @Override
    public void informOfResult(final Result result) {
        switch (result) {
            case WIN:
                out.println("You win!");
                break;
            case LOOSE:
                out.println("You loose!");
                break;
            case DRAW:
                out.println("draw");
                break;
            case ERROR:
                out.println("goodness knows");
                break;
        }
    }
}
