package selection;

class Scissors implements Selection {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "Scissors";
    }
}
