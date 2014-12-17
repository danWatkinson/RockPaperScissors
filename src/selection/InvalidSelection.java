package selection;

import result.Result;

public class InvalidSelection implements Selection {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Result resolveAgainst(final Selection opponentsSelection) {
        return Result.DRAW; //shouldn't get this far, but if it does - no-one wins??
    }

    @Override
    public String toString() {
        return "Invalid Selection";
    }
}
