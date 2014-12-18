package player;

import selection.*;

public class ComputerPlayer extends Player {

    public ComputerPlayer(final String name) {
        super(name);
    }

    @Override
    public Selection makeSelection() {
        return Selection.Rock;
    }
}
