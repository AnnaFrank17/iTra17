package navarich.helpers.stringModification;

import java.util.Random;

public abstract class SymbolOperation implements IStringModification {
    protected Random random = new Random();
    protected char extraSymbol = (char) (random.nextInt(26) + 'a');

    public abstract String Modification(String inputString, StringBuilder stringBuilder);
}
