package selection;

public class Paper implements Selection {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "Paper";
    }
}
