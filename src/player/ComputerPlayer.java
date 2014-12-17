package player;

import selection.*;

public class ComputerPlayer implements Player {
    private String name;

    public ComputerPlayer(final String name) {
        this.name = name;
    }

    @Override
    public Selection makeSelection() {
        return Selection.Rock;
    }

    @Override
    public void informOfOpponentsSelection(final Player player2, final Selection opponentsSelection) {
        //dummy
    }

    @Override
    public void informOfResult(final Player winner) {
        //dummy
    }

    @Override
    public String toString() {
        return this.name;
    }
}
