import player.Player;
import selection.Selection;

public class RulesEngine {
    public Player determineWinner(final Player player1, final Selection player1Selection, final Player player2, final Selection player2Selection) {
        if (player1Selection == player2Selection) {
            return null;
        }

        if (player1Selection == Selection.Rock     && player2Selection == Selection.Paper
                || player1Selection == Selection.Paper    && player2Selection == Selection.Scissors
                || player1Selection == Selection.Scissors && player2Selection == Selection.Rock) {

            return player2;
        }
        return player1;
    }
}
