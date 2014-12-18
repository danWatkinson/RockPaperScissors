package glue;

import org.mockito.stubbing.OngoingStubbing;
import player.Player;
import selection.Selection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerHelper {

    public static Player aPlayerWhoIsGoingToSelect(final Selection... selections) {
        Player player = aPlayer();

        OngoingStubbing<Selection> whenAskedToMakeSelection = when(player.makeSelection());
        for(Selection selection : selections) {
            whenAskedToMakeSelection = whenAskedToMakeSelection.thenReturn(selection);
        }

        return player;
    }

    public static Player aPlayer() {
        return mock(Player.class);
    }

    public static Player anObserver() {
        return aPlayer();
    }
}
