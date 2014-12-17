package selection;

import result.Result;

public interface Selection {
    public abstract boolean isValid();

    public Result resolveAgainst(Selection opponentsSelection);
}
