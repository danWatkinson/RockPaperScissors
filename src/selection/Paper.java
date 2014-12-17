package selection;

import result.Result;

public class Paper implements Selection {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Result resolveAgainst(final Selection opponentsSelection) {
        if ("Paper".equals(opponentsSelection.toString())) {
            return Result.DRAW;
        }
        if ("Scissors".equals(opponentsSelection.toString())) {
            return Result.LOOSE;
        }
        if ("Rock".equals(opponentsSelection.toString())) {
            return Result.WIN;
        }
        return Result.ERROR;
    }

    @Override
    public String toString() {
        return "Paper";
    }
}
