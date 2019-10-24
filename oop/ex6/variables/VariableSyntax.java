package oop.ex6.variables;
import oop.ex6.scopes.Scope;
import oop.ex6.scopes.ScopeException;
import java.util.LinkedList;
import java.util.regex.Matcher;

/**
 * This class responsible of all the Syntax issues regarding Variables
 */
public class VariableSyntax {

    private Matcher matcher;

    /**
     * Make an assignment syntax test on the given list of variables
     * @param variablesList Variables to test
     * @param type The type of the current line  (declaration or assignment)
     * @param scope The current scope
     * @param isFinal Tells if the current line is final (declaration or assignment)
     * @param isMethodArg Tells if we are in a def of a function
     * @throws VariableException
     * @throws ScopeException
     */
    public void AssignmentSyntaxTest(LinkedList<String> variablesList, String type, Scope scope, boolean isFinal,
                                     boolean isMethodArg) throws VariableException, ScopeException {
        while (variablesList.size()!=0) {

            String[] tempVariables = variablesList.poll().split(VariableMagic.EQUALS);
            // A single variable assignment
            if (tempVariables.length == 1) {
                if (!isFinal) {
                    if (!isMethodArg) {
                        Variable newVariable = new Variable(tempVariables[0], null, type);
                        scope.addVariable(newVariable);
                    } else {
                        Variable newVar = new Variable(tempVariables[0].trim(), VariableMagic.VAR, type);
                        scope.addVariable(newVar);
                    }
                } else {
                    throw new FinalReassigmentException();
                }
            } else {
                // More than a single assignment
                if (tempVariables.length != 2) {
                    continue;
                }
                // Checks if we assign to a final
                Variable tempVal = scope.getVariableRec(tempVariables[1].trim());
                if (tempVal != null) {
                    if (tempVal.getValue() == null) {
                        throw new NoValueAssignmentException();
                    }
                    addIfValidFormat(type, scope, isFinal, tempVariables[0], tempVal);
                }
                else {
                    if (isBadFormat(tempVariables[1].trim(), type)) {
                        throw new InvalidTypeException();
                    } else {
                        // Let it assign
                        if (scope.getVariableRec(tempVariables[0].trim()) != null) {
                            if (scope.getVariable(tempVariables[0].trim()) != null) {
                                scope.getVariable(tempVariables[0].trim()).setValue(tempVariables[1]);
                            }
                        } else {
                            Variable toAdd = new Variable(tempVariables[0].trim(), tempVariables[1].trim(), type);
                            toAdd.setIsFinal(isFinal);
                            scope.addVariable(toAdd);
                        }
                    }
                }
            }
        }
    }

    // Adds the given variable to the current scope if has a legal format.
    private void addIfValidFormat(String type, Scope scope, boolean isFinal, String tempVar, Variable tempVal)
            throws ScopeException {
        if (((tempVal.getType().equals(type)) || (type.equals(VariableMagic.DOUBLE) &&
                tempVal.getType().equals(VariableMagic.INT)) || (type.equals(VariableMagic.BOOLEAN) &&
                (tempVal.getType().equals(VariableMagic.DOUBLE) || tempVal.getType().equals(VariableMagic.INT))))
                && scope.getVariableRec(tempVar.trim()) == null) {
            Variable toAdd = new Variable(tempVar.trim(), tempVal.getValue(), type);
            toAdd.setIsFinal(isFinal);
            scope.addVariable(toAdd);
        }
    }

    /**
     * Checks if a given string represents value is in a valid format for the variable type
     * @param valueString
     * @param type given variable type
     * @return
     */
    public boolean isBadFormat(String valueString, String type) {
        if (VariableMagic.DOUBLE.equals(type)) {
            matcher = VariableMagic.DOUBLE_PATTERN.matcher(valueString);
        } else if (VariableMagic.INT.equals(type)) {
            matcher = VariableMagic.INTEGER_PATTERN.matcher(valueString);
        } else if (VariableMagic.STRING.equals(type)) {
            matcher = VariableMagic.STRING_PATTERN.matcher(valueString);
        } else if (VariableMagic.CHAR.equals(type)) {
            matcher = VariableMagic.CHAR_PATTERN.matcher(valueString);
        } else if (VariableMagic.BOOLEAN.equals(type)) {
            matcher = VariableMagic.BOOLEAN_PATTERN.matcher(valueString);
        }
        return !matcher.matches();
    }

    /**
     * Returns my "Magic representation" of the given type
     * @param currentType
     * @throws VariableException
     */
    public String getTypeMagic(String currentType) throws VariableException {
        switch (currentType) {
            case "double":
                return VariableMagic.DOUBLE;
            case "int":
                return VariableMagic.INT;
            case "String":
                return VariableMagic.STRING;
            case "char":
                return VariableMagic.CHAR;
            case "boolean":
                return VariableMagic.BOOLEAN;
        }
        throw new InvalidTypeException();
    }
}
