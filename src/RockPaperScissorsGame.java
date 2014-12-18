import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import selection.Selection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RockPaperScissorsGame {
    private Player player1;
    private Player player2;
    private final Player observer;
    private final RulesEngine rulesEngine;

    public static void main(String[] args) {
        RockPaperScissorsGame game;

        if (args.length > 0 && "watch".equals(args[0])) {
            Player player1 = new ComputerPlayer("Computer Player 1");
            Player player2 = new ComputerPlayer("Computer Player 2");
            Player observer = new HumanPlayer("observer", new BufferedReader(new InputStreamReader(System.in)), System.out);
            game = new RockPaperScissorsGame(player1, player2, observer);
        } else {
            Player player1 = new HumanPlayer("Player 1", new BufferedReader(new InputStreamReader(System.in)), System.out);
            Player player2 = new ComputerPlayer("Computer Player 2");
            game = new RockPaperScissorsGame(player1, player2);
        }

        game.start();
    }

    public RockPaperScissorsGame(final Player player1, final Player player2) {
        this(player1, player2, new ComputerPlayer("dummyObserver"), new RulesEngine());
    }

    public RockPaperScissorsGame(final Player player1, final Player player2, final Player observer) {
        this(player1, player2, observer, new RulesEngine());
    }

    public RockPaperScissorsGame(final Player player1, final Player player2, final RulesEngine rulesEngine) {
        this(player1, player2, new ComputerPlayer("observer"), rulesEngine);
    }

    public RockPaperScissorsGame(final Player player1, final Player player2, final Player observer, final RulesEngine rulesEngine) {
        this.player1 = player1;
        this.player2 = player2;
        this.observer = observer;
        this.rulesEngine = rulesEngine;
    }

    public void start() {
        Player winner = null;
        while (winner == null) {
            Selection player1Selection = player1.makeSelection();
            Selection player2Selection = player2.makeSelection();
            player1.informOfOpponentsSelection(player2, player2Selection);
            player2.informOfOpponentsSelection(player1, player1Selection);
            observer.informOfOpponentsSelection(player1, player1Selection);
            observer.informOfOpponentsSelection(player2, player2Selection);
            winner = rulesEngine.determineWinner(player1, player1Selection, player2, player2Selection);

            player1.informOfResult(winner);
            player2.informOfResult(winner);
            observer.informOfResult(winner);
        }
    }

}
