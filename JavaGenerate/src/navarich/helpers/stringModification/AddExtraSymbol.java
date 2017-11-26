package navarich.helpers.stringModification;

public class AddExtraSymbol extends SymbolOperation {
    @Override
    public String Modification(String inputString, StringBuilder stringBuilder) {
        String result;
        int stringLength = inputString.length() - 1;

        result = stringBuilder.insert(stringLength - this.random.nextInt(stringLength), extraSymbol).toString();

        return result;
    }
}
