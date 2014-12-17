import glue.ExpectationNormaliser;
import glue.NormalisedExpectedOutput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import selection.Selection;
import selection.SelectionBuilder;

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

        humanPlayer = new HumanPlayer("Player 1", mockSystemIn, mockConsole);
        mockComputerPlayer = mock(ComputerPlayer.class);
        when(mockComputerPlayer.toString()).thenReturn("Player 2");
        game = new RockPaperScissorsGame(humanPlayer, mockComputerPlayer);
    }

    @Test
    public void playerIsPromptedToSelectRockPaperOrScissors() throws IOException {
        prepareToChoose("Rock");
        computerChooses("Paper");

        game.start();

        expectOutput("Player 1, please select 'Rock', 'Paper', or 'Scissors'");
    }

    @Test
    public void playerCanSelectRock() throws IOException {
        prepareToChoose("Rock");
        computerChooses("Paper");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock"
        );
    }

    @Test
    public void playerCanSelectPaper() throws IOException {
        prepareToChoose("Paper");
        computerChooses("Scissors");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Paper"
        );
    }

    @Test
    public void playerCanSelectScissors() throws IOException {
        prepareToChoose("Scissors");
        computerChooses("Paper");

        game.start();

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
        computerChooses("Paper");

        game.start();

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
        computerChooses("Paper");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "Sorry, I didn't recognise that. Please try again: Rock Paper or Scissors",
                "You selected Rock"
        );
    }

    @Test
    public void oncePlayerHasSelectedTheComputerPlayerMakesASelection() throws IOException {
        prepareToChoose("Rock");
        computerChooses("Paper");

        game.start();

        verify(mockComputerPlayer).makeSelection();
    }

    @Test
    public void theHumanPlayerIsToldAboutTheComputersSelection() throws IOException {
        prepareToChoose("Rock");
        computerChooses("Paper");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock",
                "Player 2 selected Paper"
        );
    }

    @Test
    public void rockBeatsScissors() throws IOException {
        prepareToChoose("Rock");
        computerChooses("Scissors");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock",
                "Player 2 selected Scissors",
                "Player 1 wins!"
        );
    }

    @Test
    public void scissorsLooseToRock() throws IOException {
        prepareToChoose("Scissors");
        computerChooses("Rock");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Scissors",
                "Player 2 selected Rock",
                "Player 2 wins!"
        );
    }

    @Test
    public void scissorsBeatPaper() throws IOException {
        prepareToChoose("Scissors");
        computerChooses("Paper");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Scissors",
                "Player 2 selected Paper",
                "Player 1 wins!"
        );
    }

    @Test
    public void paperBeatsRock() throws IOException {
        prepareToChoose("Rock");
        computerChooses("Paper");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock",
                "Player 2 selected Paper",
                "Player 2 wins!"
        );
    }

    @Test
    public void tiesAreReplayed() throws IOException {
        prepareToChoose("Rock", "Rock");
        computerChooses("Rock", "Scissors");

        game.start();

        expectOutput(
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock",
                "Player 2 selected Rock",
                "draw",
                "Player 1, please select 'Rock', 'Paper', or 'Scissors'",
                "You selected Rock",
                "Player 2 selected Scissors",
                "Player 1 wins!"
        );
    }

    @Test
    public void iWantToObserveTwoComputerPlayersPlaying() {
        Player mockPlayer1 = mock(ComputerPlayer.class);
        when(mockPlayer1.makeSelection()).thenReturn(Selection.Rock);
        when(mockPlayer1.toString()).thenReturn("Computer Player 1");

        Player mockPlayer2 = mock(ComputerPlayer.class);
        when(mockPlayer2.makeSelection()).thenReturn(Selection.Paper);
        when(mockPlayer2.toString()).thenReturn("Computer Player 2");

        Player observer = new HumanPlayer("Player 1", mockSystemIn, mockConsole);

        game = new RockPaperScissorsGame(mockPlayer1, mockPlayer2, observer);

        game.start();

        expectOutput(
                "Computer Player 1 selected Rock",
                "Computer Player 2 selected Paper",
                "Computer Player 2 wins!"
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

    private void computerChooses(String... computerSelections) {
        OngoingStubbing<Selection> whenInputRead = when(mockComputerPlayer.makeSelection());
        for(String computerSelection : computerSelections) {
            whenInputRead = whenInputRead.thenReturn(new SelectionBuilder().parse(computerSelection));
        }

    }
}
