package player;

import selection.Selection;

public abstract class Player {
    protected String name;

    public Player(final String name) {
        this.name = name;
    }

    public abstract Selection makeSelection();

    public void informOfOpponentsSelection(final Player player2, Selection opponentsSelection) {
        //override if you want to do something about a player's selection
    }

    public void informOfResult(final Player winner) {
        //override if you want to do something about the result of the game
    }

    @Override
    public String toString() {
        return this.name;
    }
}
