import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dan on 17/12/2014.
 */
public class RockPaperScissorsGameTest {

    PrintStream mockConsole;
    BufferedReader mockSystemIn;

    RockPaperScissorsGame game;

    @Before
    public void setup() {
        mockConsole = mock(PrintStream.class);
        mockSystemIn = mock(BufferedReader.class);

        game = new RockPaperScissorsGame(mockSystemIn, mockConsole);
    }

    @Test
    public void playerCanSelectRock() throws IOException {
        when(mockSystemIn.readLine()).thenReturn("Rock");

        game.start();

        verify(mockConsole).println("You selected Rock");
    }

    @Test
    public void playerCanSelectPaper() throws IOException {
        when(mockSystemIn.readLine()).thenReturn("Paper");

        game.start();

        verify(mockConsole).println("You selected Paper");
    }

    @Test
    public void playerCanSelectScissors() throws IOException {
        when(mockSystemIn.readLine()).thenReturn("Scissors");

        game.start();

        verify(mockConsole).println("You selected Scissors");
    }
}
