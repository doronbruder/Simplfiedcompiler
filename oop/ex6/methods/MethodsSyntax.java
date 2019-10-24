package oop.ex6.methods;
import oop.ex6.scopes.Scope;
import oop.ex6.variables.VariableSyntax;
import java.util.ArrayList;
import java.util.Objects;

public class MethodsSyntax {
    private static final String COMMA =",";

    // Object to deal with Variables Syntax issues
    private VariableSyntax varSyn = new VariableSyntax();

    /**
     * Checks if a given method is with valid syntax
     * @param line the starting line of the method
     * @param curMethod the method to check
     * @param scope the scope the method in
     * @throws MethodsException
     */
    public void isLegalMethod(String line, Method curMethod, Scope scope) throws MethodsException{
        ArrayList<String> methodVarTypes = curMethod.getArgsTypes();
        String[] lineVarArr = line.split(COMMA);
        if (methodVarTypes.size() == lineVarArr.length) {
            int i = 0;
            while (i < methodVarTypes.size()) {
                if (scope.getVariableRec(lineVarArr[i]) == null ||
                        !Objects.equals(scope.getVariableRec(lineVarArr[i]).getType(), methodVarTypes.get(i))) {
                    if (varSyn.isBadFormat(lineVarArr[i].trim(), methodVarTypes.get(i)))
                        throw new InvalidArgsFormatException();
                } else {
            i++;
            continue;
        }
                i++;
            }
        } else throw new InvalidArgsAmountException();
    }
}
