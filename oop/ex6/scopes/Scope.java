package oop.ex6.scopes;
import oop.ex6.methods.Method;
import oop.ex6.variables.Variable;
import java.util.ArrayList;

/**
 * This class represents a Sjava Scope
 */
public class Scope {

    // 1th outer scope of the current
    private Scope superScope;

    // The methods in the current scope
    private static ArrayList<Method> scopeMethods = new ArrayList<>();

    // The methods in the current scope
    private ArrayList<Variable> scopeVariables;

    /**
     * Creates a new scope
     * @param superScope
     */
    public Scope(Scope superScope) {
        this.superScope = superScope;
        scopeVariables = new ArrayList<>();
    }

    /**
     * Adds a new methods to the scope if possible
     * @param toAdd
     * @throws ScopeException
     */
    public void addMethod (Method toAdd)throws ScopeException{
        if(scopeMethods.contains(toAdd))
                throw new SameNameMethodsException();


        scopeMethods.add(toAdd);
    }

    /**
     * Returns the method with the given name if it's found
     * @param toFind
     */
    public Method getMethod(String toFind) {
        return scopeMethods.stream().filter(curMethod ->
                toFind.equals(curMethod.getName())).findFirst().orElse(null);
    }

    /**
     * Adds a new variable to the scope if possible
     * @param toAdd
     * @throws ScopeException
     */
    public void addVariable(Variable toAdd) throws ScopeException {
        if(scopeVariables.contains(toAdd))
                throw new SameNameVarsException();
        scopeVariables.add(toAdd);
    }

    /**
     * Returns the variable with the given name if it's found in the current scope
     * @param VariableName
     */
    public Variable getVariable(String VariableName) {
        return scopeVariables.stream().filter(var ->
                var.getName().equals(VariableName)).findFirst().orElse(null);
    }

    /**
     * Returns the super scope of the current
     */
    public Scope getSuperScope(){
        return superScope;
    }

    /**
     * Returns the variable with the given name if it's found in any scope in the program
     * @param varName
     */
    public Variable getVariableRec(String varName) {
        Scope other = this;
        while (true) {
            int i = 0, scopeVariablesSize = other.scopeVariables.size();
            while (i < scopeVariablesSize) {
                Variable variable = other.scopeVariables.get(i);
                if (!variable.toString().equals(varName)) {
                    i++;
                } else {
                    return variable;
                }
            }
            if (other.superScope == null) return null;
            other = other.superScope;
        }
    }

    public static void clearMethods(){
        scopeMethods.clear();
    }

}
