import glue.ExpectationNormaliser;
import glue.NormalisedExpectedOutput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import selection.Paper;
import selection.Rock;
import selection.Scissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.*;

public class RockPaperScissorsGameTest {

    Player humanPlayer;
    Player mockComputerPlayer;

    PrintStream mockConsole;
    BufferedReader mockSystemIn;

    RockPaperScissorsGame game;

    @Before
    public void setup() {
        mockConsole = mock(PrintStream.class);
        mockSystemIn = mock(BufferedReader.class);

        humanPlayer = new HumanPlayer(mockSystemIn, mockConsole);
        mockComputerPlayer = mock(ComputerPlayer.class);
        game = new RockPaperScissorsGame(humanPlayer, mockComputerPlayer, mockConsole);
    }

    @Test
    public void playerIsPromptedToSelectRockPaperOrScissors() throws IOException {
        prepareToChoose("Rock");

        game.start();

        expectOutput("Please select 'Rock', 'Paper', or 'Scissors'");
    }

    @Test
    public void playerCanSelectRock() throws IOException {
        prepareToChoose("Rock");

        game.start();

        expectOutput(
                "Please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock"
        );
    }

    @Test
    public void playerCanSelectPaper() throws IOException {
        prepareToChoose("Paper");

        game.start();

        expectOutput(
                "Please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Paper"
        );
    }

    @Test
    public void playerCanSelectScissors() throws IOException {
        prepareToChoose("Scissors");

        game.start();

        expectOutput(
                "Please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Scissors"
        );
    }

    @Test
    public void playerCantSelectAnythingOtherThanRockPaperOrScissors() throws IOException {
        prepareToChoose(
                "Two-handed sword",
                "Rock"
        );

        game.start();

        expectOutput(
                "Please select 'Rock', 'Paper', or 'Scissors'",
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

        game.start();

        expectOutput(
                "Please select 'Rock', 'Paper', or 'Scissors'",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "You selected Rock"
        );
    }

    @Test
    public void oncePlayerHasSelectedTheComputerPlayerMakesASelection() throws IOException {
        prepareToChoose("Rock");

        game.start();

        verify(mockComputerPlayer).makeSelection();
    }

    @Test
    public void theHumanPlayerIsToldAboutTheComputersSelection() throws IOException {
        prepareToChoose("Rock");
        when(mockComputerPlayer.makeSelection()).thenReturn(new Paper());

        game.start();

        expectOutput(
                "Please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock",
                "The computer selected Paper"
        );
    }

    @Test
    public void rockBeatsScissors() throws IOException {
        prepareToChoose("Rock");
        when(mockComputerPlayer.makeSelection()).thenReturn(new Scissors());

        game.start();

        expectOutput(
                "Please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock",
                "The computer selected Scissors",
                "You win!"
        );
    }

    private void prepareToChoose(final String... choices) throws IOException {
        OngoingStubbing<String> whenInputRead = when(mockSystemIn.readLine());
        for(String choice : choices) {
            whenInputRead = whenInputRead.thenReturn(choice);
        }
    }

    private void expectOutput(final String... expectedOutput) {
        List<NormalisedExpectedOutput> normalisedExpectedOutput = new ExpectationNormaliser().normalise(expectedOutput);
        InOrder inOrder = inOrder(mockConsole);
        for (NormalisedExpectedOutput expectation : normalisedExpectedOutput) {
            inOrder.verify(mockConsole, times(expectation.getNumberOfTimes())).println(expectation.getExpectation());
        }
    }

}
