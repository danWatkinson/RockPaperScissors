package player;

import selection.Selection;
import selection.SelectionBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class HumanPlayer extends Player {

    private final BufferedReader in;
    private final PrintStream out;

    public HumanPlayer(final String name, final BufferedReader in, final PrintStream out) {
        super(name);
        this.in = in;
        this.out = out;
    }

    @Override
    public Selection makeSelection() {
        out.println(this.toString() + ", please select 'Rock', 'Paper', or 'Scissors'");
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
    public void informOfOpponentsSelection(final Player opponent, final Selection opponentsSelection) {
        out.println(opponent + " selected " + opponentsSelection);
    }

    @Override
    public void informOfResult(final Player winner) {
        if (winner == null) {
            out.println("draw");
        } else {
            out.println(winner + " wins!");
        }
    }

}
