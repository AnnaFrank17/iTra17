package navarich.helpers.stringModification;

public class ChangeSymbol extends SymbolOperation {
    @Override
    public String Modification(String inputString, StringBuilder stringBuilder) {
        String result;
        int stringLength = inputString.length() - 1;

        stringBuilder.setCharAt(stringLength - this.random.nextInt(stringLength), extraSymbol);
        result = stringBuilder.toString();

        return result;
    }
}
