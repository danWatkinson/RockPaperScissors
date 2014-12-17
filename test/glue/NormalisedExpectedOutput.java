package glue;

public class NormalisedExpectedOutput {
    private final String expectation;
    private int numberOfTimes;

    public NormalisedExpectedOutput(final String expectation, final int numberOfTimes) {
        this.expectation = expectation;
        this.numberOfTimes = numberOfTimes;
    }

    public String getExpectation() {
        return expectation;
    }

    public void addRepetition() {
        this.numberOfTimes++;
    }

    public int getNumberOfTimes() {
        return numberOfTimes;
    }
}
