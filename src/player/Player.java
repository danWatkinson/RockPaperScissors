package player;

import selection.Selection;

public interface Player {
    public Selection makeSelection();

    public void informOfOpponentsSelection(Selection player2Selection);
}
