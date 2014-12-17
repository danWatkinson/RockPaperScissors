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
        Selection playersSelection = getSelection();
        out.println("You selected " + playersSelection);
    }

    private Selection getSelection() {
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

    private class SelectionBuilder {
        public Selection parse(final String input) {
            if ("Rock".equals(input)) {
                return new Rock();
            } else if ("Paper".equals(input)) {
                return new Paper();
            } else if ("Scissors".equals(input)) {
                return new Scissors();
            } else {
                return new InvalidSelection();
            }
        }
    }

    private interface Selection {
        public abstract boolean isValid();
    }

    private class Rock implements Selection {
        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public String toString() {
            return "Rock";
        }
    }

    private class Paper implements Selection {
        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public String toString() {
            return "Paper";
        }
    }

    private class Scissors implements Selection {
        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public String toString() {
            return "Scissors";
        }
    }

    private class InvalidSelection implements Selection {
        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public String toString() {
            return "Invalid Selection";
        }
    }
}
