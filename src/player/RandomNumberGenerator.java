package player;

public class RandomNumberGenerator {

    public int intBetween(final int min, final int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
