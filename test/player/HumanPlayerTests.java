package player;

import glue.ExpectationNormaliser;
import glue.NormalisedExpectedOutput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;
import selection.Selection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class HumanPlayerTests {

    String name;
    BufferedReader in;
    PrintStream out;

    HumanPlayer player;

    @Before
    public void setUp() {
        name = "Player 1";
        in = mock(BufferedReader.class);
        out = mock(PrintStream.class);

        player = new HumanPlayer(name, in, out);
    }

    @Test
    public void itIsCreatedWithANameAnInputSourceAndAnOutput() {
        assertEquals(name, player.toString());
        assertEquals(in, player.getIn());
        assertEquals(out, player.getOut());
    }

    @Test
    public void whenAskedToMakeSelectionItPromptsThePlayerForTheirSelection() throws IOException {
        prepareToChoose("Rock");

        player.makeSelection();

        expectOutput("Player 1, please select 'Rock', 'Paper', or 'Scissors'");

    }

    @Test
    public void playerCanSelectRock() throws IOException {
        prepareToChoose("Rock");

        player.makeSelection();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock"
        );
    }

    @Test
    public void playerCanSelectPaper() throws IOException {
        prepareToChoose("Paper");

        player.makeSelection();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Paper"
        );
    }

    @Test
    public void playerCanSelectScissors() throws IOException {
        prepareToChoose("Scissors");

        player.makeSelection();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Scissors"
        );
    }

    @Test
    public void playerCantSelectAnythingOtherThanRockPaperOrScissors() throws IOException {
        prepareToChoose(
                "Two-handed sword",
                "Rock"
        );

        player.makeSelection();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors"
        );
    }

    @Test
    public void playerCanKeepTryingUntilTheyCorrectlyManageToTypeOneOfTheWords() throws IOException {
        prepareToChoose(
                "Two-handed sword",
                "Photon Torpedo",
                "One of those amazing rotary machine guns, like the one Blaine used in Predator",
                "Rock"
        );

        player.makeSelection();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "You selected Rock"
        );
    }

    @Test
    public void echoesPlayersSelectionsIntoOurOutput() throws IOException {
        Player opponent = new ComputerPlayer("opponent");

        player.informOfOpponentsSelection(opponent, Selection.Paper);

        expectOutput("opponent selected Paper");
    }

    @Test
    public void echoesResultIntoOurOutput() throws IOException {
        Player opponent = new ComputerPlayer("opponent");

        player.informOfResult(opponent);

        expectOutput("opponent wins!");
    }

    private void prepareToChoose(final String... choices) throws IOException {
        OngoingStubbing<String> whenInputRead = when(in.readLine());
        for(String choice : choices) {
            whenInputRead = whenInputRead.thenReturn(choice);
        }
    }

    private void expectOutput(final String... expectedOutput) {
        List<NormalisedExpectedOutput> normalisedExpectedOutput = new ExpectationNormaliser().normalise(expectedOutput);
        InOrder inOrder = inOrder(out);
        for (NormalisedExpectedOutput expectation : normalisedExpectedOutput) {
            inOrder.verify(out, times(expectation.getNumberOfTimes())).println(expectation.getExpectation());
        }
    }

}
