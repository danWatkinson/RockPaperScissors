import org.junit.Test;
import org.mockito.InOrder;
import player.Player;
import selection.Selection;

import static glue.PlayerHelper.aPlayerWhoIsGoingToSelect;
import static glue.PlayerHelper.anObserver;
import static org.mockito.Mockito.*;

public class RockPaperScissorsGameTest {

    @Test
    public void givenTwoPlayersAsksEachToMakeSelection() {
        Player player1 = aPlayerWhoIsGoingToSelect(Selection.Rock);
        Player player2 = aPlayerWhoIsGoingToSelect(Selection.Paper);

        RockPaperScissorsGame game = new RockPaperScissorsGame(player1, player2);

        game.start();

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).makeSelection();
        inOrder.verify(player2).makeSelection();
    }

    @Test
    public void informsEachPlayerOfTheirOpponentsDecision() {
        Player player1 = aPlayerWhoIsGoingToSelect(Selection.Rock);
        Player player2 = aPlayerWhoIsGoingToSelect(Selection.Paper);

        RockPaperScissorsGame game = new RockPaperScissorsGame(player1, player2);

        game.start();

        verify(player1).informOfOpponentsSelection(player2, Selection.Paper);
        verify(player2).informOfOpponentsSelection(player1, Selection.Rock);
    }

    @Test
    public void asksTheRulesEngineToDetermineTheWinner() {
        Player player1 = aPlayerWhoIsGoingToSelect(Selection.Rock);
        Player player2 = aPlayerWhoIsGoingToSelect(Selection.Paper);
        RulesEngine rulesEngine = mock(RulesEngine.class);
        when(rulesEngine.determineWinner(player1, Selection.Rock, player2, Selection.Paper)).thenReturn(player2);

        RockPaperScissorsGame game = new RockPaperScissorsGame(player1, player2, rulesEngine);

        game.start();

        verify(rulesEngine).determineWinner(player1, Selection.Rock, player2, Selection.Paper);
    }

    @Test
    public void informsEachPlayerOfTheResult() {
        Player player1 = aPlayerWhoIsGoingToSelect(Selection.Rock);
        Player player2 = aPlayerWhoIsGoingToSelect(Selection.Paper);

        RockPaperScissorsGame game = new RockPaperScissorsGame(player1, player2);

        game.start();

        verify(player1).informOfResult(player2);
        verify(player2).informOfResult(player2);
    }

    @Test
    public void replaysDraws() {
        Player player1 = aPlayerWhoIsGoingToSelect(Selection.Rock, Selection.Paper);
        Player player2 = aPlayerWhoIsGoingToSelect(Selection.Rock, Selection.Scissors);
        RockPaperScissorsGame game = new RockPaperScissorsGame(player1, player2);

        game.start();

        verify(player1, times(2)).makeSelection();
        verify(player2, times(2)).makeSelection();
    }

    @Test
    public void anObserverIsInformedOfEachPlayersDecision() {
        Player player1 = aPlayerWhoIsGoingToSelect(Selection.Rock);
        Player player2 = aPlayerWhoIsGoingToSelect(Selection.Paper);
        Player observer = anObserver();

        RockPaperScissorsGame game = new RockPaperScissorsGame(player1, player2, observer);

        game.start();

        verify(observer).informOfOpponentsSelection(player1, Selection.Rock);
        verify(observer).informOfOpponentsSelection(player2, Selection.Paper);
    }

    @Test
    public void anObserverIsInformedOfTheResult() {
        Player player1 = aPlayerWhoIsGoingToSelect(Selection.Rock);
        Player player2 = aPlayerWhoIsGoingToSelect(Selection.Paper);
        Player observer = anObserver();

        RockPaperScissorsGame game = new RockPaperScissorsGame(player1, player2, observer);

        game.start();

        verify(observer).informOfResult(player2);
    }

}
