import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        prepareToChoose("Rock");

        game.start();

        expectOutput("You selected Rock");
    }

    @Test
    public void playerCanSelectPaper() throws IOException {
        prepareToChoose("Paper");

        game.start();

        expectOutput("You selected Paper");
    }

    @Test
    public void playerCanSelectScissors() throws IOException {
        prepareToChoose("Scissors");

        game.start();

        expectOutput("You selected Scissors");
    }

    private void prepareToChoose(final String choice) throws IOException {
        when(mockSystemIn.readLine()).thenReturn(choice);
    }

    private void expectOutput(final String expectedOutput) {
        verify(mockConsole).println(expectedOutput);
    }
}
