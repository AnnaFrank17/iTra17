package navarich.models;

import navarich.helpers.stringModification.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Mistake {
    private Random random = new Random();

    private final List<IStringModification> everyErrorTypeArray = new ArrayList<>() {{
        add(new AddExtraSymbol());
        add(new ChangeSymbol());
        add(new RemoveSymbol());
    }};
    private final int errorTypeCount = 3;

    private int everyTypeErrorCount;
    private int freeTypeErrorCount;

    private List<IStringModification> errorArray;
    private List<Integer> errorRows;

    public Mistake(int errorCount, int iterations) {
        initErrorCount(errorCount);

        errorArray = createErrorArray();
        errorRows = createArrayWithMistakeRows(errorCount, iterations);
    }

    public List<IStringModification> getErrorArray() {
        return errorArray;
    }

    public List<Integer> getErrorRows() {
        return errorRows;
    }

    private void initErrorCount(int errorCount) {
        if (errorCount < errorTypeCount) {
            freeTypeErrorCount = errorCount;
        } else {
            everyTypeErrorCount = (int) Math.ceil(errorCount / errorTypeCount) * errorTypeCount;
            freeTypeErrorCount = errorCount - everyTypeErrorCount;
        }
    }

    private List<IStringModification> createErrorArray() {
        List<IStringModification> everyTypeArray = createEveryTypeArray();
        List<IStringModification> freeTypeArray = createFreeTypeArray();

        List<IStringModification> commonErrorArray = concatArrays(everyTypeArray, freeTypeArray);

        Collections.shuffle(commonErrorArray);

        return commonErrorArray;
    }

    public List<Integer> createArrayWithMistakeRows(int mistakeCount, int itemCount) {
        List<Integer> result = new ArrayList<>();
        int rndNumber = 0;

        for (int i = 0; i < mistakeCount; i++) {
            do {
                rndNumber = random.nextInt(itemCount);
            }
            while (result.contains(rndNumber));

            result.add(rndNumber);
        }

        return result;
    }

    private List<IStringModification> createEveryTypeArray() {
        List<IStringModification> result = new ArrayList<>();
        int iterator = 0;

        for (int i = 0; i < this.everyTypeErrorCount; i++) {
            result.add(everyErrorTypeArray.get(iterator++));

            if (iterator == errorTypeCount)
                iterator = 0;
        }

        return result;
    }

    private List<IStringModification> createFreeTypeArray() {
        List<IStringModification> result = new ArrayList<>();

        for (int i = 0; i < this.freeTypeErrorCount; i++) {
            result.add(everyErrorTypeArray.get(random.nextInt(errorTypeCount)));
        }

        return result;
    }

    private List<IStringModification> concatArrays(List<IStringModification>... lists) {
        List<IStringModification> concatArray = new ArrayList<>();

        for (int i = 0; i < lists.length; i++) {
            concatArray.addAll(lists[i]);
        }

        return concatArray;
    }
}
