package player;

import result.Result;
import selection.Selection;

public interface Player {
    public Selection makeSelection();
    public void informOfOpponentsSelection(Selection opponentsSelection);
    public void informOfResult(final Result winner);
}
