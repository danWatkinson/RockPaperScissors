package selection;

import result.Result;

public class Rock implements Selection {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Result resolveAgainst(final Selection opponentsSelection) {
        if ("Paper".equals(opponentsSelection.toString())) {
            return Result.LOOSE;
        }
        if ("Scissors".equals(opponentsSelection.toString())) {
            return Result.WIN;
        }
        if ("Rock".equals(opponentsSelection.toString())) {
            return Result.DRAW;
        }
        return Result.ERROR;
    }

    @Override
    public String toString() {
        return "Rock";
    }
}
