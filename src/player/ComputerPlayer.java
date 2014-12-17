package player;

import selection.*;

public class ComputerPlayer implements Player {
    @Override
    public Selection makeSelection() {
        return new Rock();
    }

    @Override
    public void informOfOpponentsSelection(final Selection opponentsSelection) {
        //dummy
    }
}
