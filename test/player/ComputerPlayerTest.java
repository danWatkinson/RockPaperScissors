package player;

import org.junit.Test;
import selection.Selection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ComputerPlayerTest {
    final String NAME = "aName";

    @Test
    public void itIsCreatedWithAName() {

        ComputerPlayer computerPlayer = new ComputerPlayer(NAME);

        assertEquals(NAME, computerPlayer.toString());
    }

    @Test
    public void whenAskedToMakeSelectionItDelegatesToRandomNumberGenerator() {
        RandomNumberGenerator rng = mock(RandomNumberGenerator.class);
        ComputerPlayer computerPlayer = new ComputerPlayer(NAME, rng);

        computerPlayer.makeSelection();

        verify(rng).intBetween(0, 2);
    }

    @Test
    public void maps0ToRock() {
        RandomNumberGenerator rng = mock(RandomNumberGenerator.class);
        when(rng.intBetween(0,2)).thenReturn(0);
        ComputerPlayer computerPlayer = new ComputerPlayer(NAME, rng);

        assertEquals(Selection.Rock, computerPlayer.makeSelection());
    }

    @Test
    public void maps1ToPaper() {
        RandomNumberGenerator rng = mock(RandomNumberGenerator.class);
        when(rng.intBetween(0,2)).thenReturn(1);
        ComputerPlayer computerPlayer = new ComputerPlayer(NAME, rng);

        assertEquals(Selection.Paper, computerPlayer.makeSelection());
    }

    @Test
    public void maps2ToScissors() {
        RandomNumberGenerator rng = mock(RandomNumberGenerator.class);
        when(rng.intBetween(0,2)).thenReturn(2);
        ComputerPlayer computerPlayer = new ComputerPlayer(NAME, rng);

        assertEquals(Selection.Scissors, computerPlayer.makeSelection());
    }
}
