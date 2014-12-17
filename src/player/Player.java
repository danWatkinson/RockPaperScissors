package player;

import selection.Selection;

public interface Player {
    public Selection makeSelection();
    public void informOfOpponentsSelection(final Player player2, Selection opponentsSelection);
    public void informOfResult(final Player winner);
}
