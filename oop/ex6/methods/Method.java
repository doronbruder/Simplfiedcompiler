package oop.ex6.methods;
import oop.ex6.parsing.Parser;
import oop.ex6.variables.VariableException;

import java.util.ArrayList;

/**
 * This class represents a Sjava method
 */
public class Method {

    // Method name
    private String name;

    // Method parameters types
    private ArrayList<String> argsTypes;

    /**
     * Creates a new method
     * @throws VariableException
     */
    public Method(String methodName, String methodVarLine) throws VariableException {
        name = methodName;
        argsTypes = Parser.parseMethodArgsTypes(methodVarLine);
    }

    /**
     * Gets the method parameters-types list
     * @return
     */
    public ArrayList<String> getArgsTypes() {
        return argsTypes;
    }

    /**
     * Gets the name of the method
     * @return
     */
    public String getName(){return name;}
}
