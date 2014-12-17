package selection;

public class SelectionBuilder {
    public Selection parse(final String input) {
        if ("Rock".equals(input)) {
            return Selection.Rock;
        } else if ("Paper".equals(input)) {
            return Selection.Paper;
        } else if ("Scissors".equals(input)) {
            return Selection.Scissors;
        } else {
            return Selection.Other;
        }
    }
}
