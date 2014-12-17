package selection;

public class SelectionBuilder {
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
