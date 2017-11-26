package navarich.helpers.stringModification;

public class RemoveSymbol extends SymbolOperation {
    @Override
    public String Modification(String inputString, StringBuilder stringBuilder) {
        String result;
        int stringLength = inputString.length() - 1;

        result = stringBuilder.deleteCharAt(this.random.nextInt(stringLength)).toString();

        return result;
    }
}
