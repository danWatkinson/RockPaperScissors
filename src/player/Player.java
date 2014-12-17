package player;

import selection.*;

public class Player {
    public Selection makeSelection() {
        return new Rock();
    }
}
