package selection;

import result.Result;

public class Scissors implements Selection {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Result resolveAgainst(final Selection opponentsSelection) {
        if ("Paper".equals(opponentsSelection.toString())) {
            return Result.WIN;
        }
        if ("Scissors".equals(opponentsSelection.toString())) {
            return Result.DRAW;
        }
        if ("Rock".equals(opponentsSelection.toString())) {
            return Result.LOOSE;
        }
        return Result.ERROR;
    }

    @Override
    public String toString() {
        return "Scissors";
    }
}
