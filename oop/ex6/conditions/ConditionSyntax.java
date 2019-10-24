package oop.ex6.conditions;
import oop.ex6.scopes.Scope;
import oop.ex6.variables.*;
import java.util.Objects;

public class ConditionSyntax {
    private final static String BOOLEAN_OPERATOR = "\\|\\||&&";

    private VariableSyntax VarSyntaxTester =new VariableSyntax();

    /**
     * Test for validness of a given condition
     * @throws VariableException
     * @throws ConditionException
     */
    public void conditionSyntaxTest(String condition, Scope curScope) throws VariableException,ConditionException {
        String[] conditionArgs = condition.split(BOOLEAN_OPERATOR);
        for (String conditionArg : conditionArgs) {
            String curCondition = conditionArg;
            curCondition = curCondition.trim();
            Variable currentConditionVar = curScope.getVariableRec(curCondition);
            if (currentConditionVar != null && currentConditionVar.getValue() == null) {
                throw new NoValueAssignmentException();
            }
            formatCheck(curCondition, currentConditionVar);
        }
    }
    // Checks the general structure of the condition
    private void formatCheck(String currentCondition, Variable tempVariable) throws ConditionException {
        if (VarSyntaxTester.isBadFormat(currentCondition, VariableMagic.BOOLEAN) &&
                (Objects.equals(tempVariable.getType(), VariableMagic.CHAR) ||
                        tempVariable.getType().equals(VariableMagic.STRING))) {
            throw new ConditionException();
        }
    }
}
