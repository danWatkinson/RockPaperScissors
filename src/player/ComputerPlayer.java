package player;

import selection.*;

public class ComputerPlayer implements Player {
    @Override
    public Selection makeSelection() {
        return Selection.Rock;
    }

    @Override
    public void informOfOpponentsSelection(final Selection opponentsSelection) {
        //dummy
    }

    @Override
    public void informOfResult(final Player winner) {
        //dummy
    }
}
