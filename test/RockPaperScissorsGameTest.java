import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

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

    private void prepareToChoose(final String... choices) throws IOException {
        OngoingStubbing<String> whenInputRead = when(mockSystemIn.readLine());
        for(String choice : choices) {
            whenInputRead = whenInputRead.thenReturn(choice);
        }
    }

    private void expectOutput(final String... expectedOutput) {
        List<NormalisedExpectedOutput> normalisedExpectedOutput = normalise(expectedOutput);
        InOrder inOrder = inOrder(mockConsole);
        for (NormalisedExpectedOutput expectation : normalisedExpectedOutput) {
            inOrder.verify(mockConsole, times(expectation.getNumberOfTimes())).println(expectation.getExpectation());
        }
    }

    private List<NormalisedExpectedOutput> normalise(final String[] expectedOutput) {
        List<NormalisedExpectedOutput> normalisedList = new ArrayList<NormalisedExpectedOutput>();
        for (String theExpectationWereTryingToPutIn: expectedOutput) {
            if (weHaventPutAnythingInOurListYet(normalisedList) || thisIsntARepetition(normalisedList, theExpectationWereTryingToPutIn)) {
                normalisedList.add(new NormalisedExpectedOutput(theExpectationWereTryingToPutIn, 1));
            } else {
                theLastThingWePutIn(normalisedList).addRepetition();
            }
        }

        return normalisedList;
    }

    private boolean thisIsntARepetition(final List<NormalisedExpectedOutput> normalisedList, final String theExpectationWereTryingToPutIn) {
        return !theExpectationWereTryingToPutIn.equals(theExpectationOfTheLastThingWePutIn(normalisedList));
    }

    private String theExpectationOfTheLastThingWePutIn(final List<NormalisedExpectedOutput> normalisedList) {
        return theLastThingWePutIn(normalisedList).getExpectation();
    }

    private NormalisedExpectedOutput theLastThingWePutIn(final List<NormalisedExpectedOutput> normalisedList) {
        return normalisedList.get(normalisedList.size()-1);
    }

    private boolean weHaventPutAnythingInOurListYet(final List<NormalisedExpectedOutput> normalisedList) {
        return normalisedList.size() == 0;
    }

    private class NormalisedExpectedOutput {
        private final String expectation;
        private int numberOfTimes;

        public NormalisedExpectedOutput(final String expectation, final int numberOfTimes) {
            this.expectation = expectation;
            this.numberOfTimes = numberOfTimes;
        }

        public String getExpectation() {
            return expectation;
        }

        public void addRepetition() {
            this.numberOfTimes++;
        }

        public int getNumberOfTimes() {
            return numberOfTimes;
        }
    }
}
