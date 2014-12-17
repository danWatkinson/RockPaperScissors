package glue;

import java.util.ArrayList;
import java.util.List;

public class ExpectationNormaliser {

    public List<NormalisedExpectedOutput> normalise(final String[] expectedOutput) {
        List<NormalisedExpectedOutput> normalisedList = new ArrayList<NormalisedExpectedOutput>();
        for (String theExpectationWereTryingToPutIn: expectedOutput) {
            if (weHaventPutAnythingInOurListYet(normalisedList) || thisIsntARepetition(normalisedList, theExpectationWereTryingToPutIn)) {
                normalisedList.add(new NormalisedExpectedOutput(theExpectationWereTryingToPutIn, 1));
            } else {
                theLastThingWePutIn(normalisedList).addRepetition();
            }
        }

        return normalisedList;
    }

    private boolean thisIsntARepetition(final List<NormalisedExpectedOutput> normalisedList, final String theExpectationWereTryingToPutIn) {
        return !theExpectationWereTryingToPutIn.equals(theExpectationOfTheLastThingWePutIn(normalisedList));
    }

    private String theExpectationOfTheLastThingWePutIn(final List<NormalisedExpectedOutput> normalisedList) {
        return theLastThingWePutIn(normalisedList).getExpectation();
    }

    private NormalisedExpectedOutput theLastThingWePutIn(final List<NormalisedExpectedOutput> normalisedList) {
        return normalisedList.get(normalisedList.size()-1);
    }

    private boolean weHaventPutAnythingInOurListYet(final List<NormalisedExpectedOutput> normalisedList) {
        return normalisedList.size() == 0;
    }

}
