package selection;

public class InvalidSelection implements Selection {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String toString() {
        return "Invalid Selection";
    }
}
