package selection;

public class Rock implements Selection {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "Rock";
    }
}
