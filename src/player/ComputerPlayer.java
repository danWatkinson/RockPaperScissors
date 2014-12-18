package player;

import selection.*;

public class ComputerPlayer extends Player {

    private final RandomNumberGenerator rng;

    public ComputerPlayer(final String name) {
        this(name, new RandomNumberGenerator());
    }

    public ComputerPlayer(final String name, final RandomNumberGenerator rng) {
        super(name);
        this.rng = rng;
    }

    @Override
    public Selection makeSelection() {
        int randomNumber = rng.intBetween(0,2);
        switch(randomNumber) {
            case 0: return Selection.Rock;
            case 1: return Selection.Paper;
            case 2: return Selection.Scissors;
            default: return Selection.Other;
        }
    }
}
