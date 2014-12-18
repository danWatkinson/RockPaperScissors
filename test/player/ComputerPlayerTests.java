package player;

import org.junit.Test;
import selection.Selection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ComputerPlayerTests {
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
        ComputerPlayer computerPlayer = new ComputerPlayer(NAME, aRandomNumberGeneratorPrimedWith(0));

        assertEquals(Selection.Rock, computerPlayer.makeSelection());
    }

    @Test
    public void maps1ToPaper() {
        ComputerPlayer computerPlayer = new ComputerPlayer(NAME, aRandomNumberGeneratorPrimedWith(1));

        assertEquals(Selection.Paper, computerPlayer.makeSelection());
    }

    @Test
    public void maps2ToScissors() {
        ComputerPlayer computerPlayer = new ComputerPlayer(NAME, aRandomNumberGeneratorPrimedWith(2));

        assertEquals(Selection.Scissors, computerPlayer.makeSelection());
    }

    private RandomNumberGenerator aRandomNumberGeneratorPrimedWith(int primedRandomNumber) {
        RandomNumberGenerator rng = mock(RandomNumberGenerator.class);
        when(rng.intBetween(0,2)).thenReturn(primedRandomNumber);
        return rng;
    }
}
