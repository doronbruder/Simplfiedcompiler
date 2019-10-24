package oop.ex6.variables;

/**
 * This class represents a general Sjava variable
 */
public class Variable {

    private String name;

    private String value;

    private String type;

    private boolean isFinal = false;


    /**
     * Creates new Variable
     * @param name the name of the variable to create
     * @param value the value to assign the variable with(null means was not assigned)
     * @param type the type of the variable
     */
     Variable(String name, String value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    /**
     * To mark if a variable is final (default situation is not final)
     * @param isFinalFlag
     */
     void setIsFinal(boolean isFinalFlag){
        isFinal = isFinalFlag;
    }

    /**
     * To override the Object equality since I refer to variables as equals if they have the same type and name
     * @param other
     * @return
     */
    public boolean equals(Variable other){
        return this.name.equals(other.name)&&this.type.equals(other.type);
    }

    /**
     * To check if a given variable is final
     * @return true if it is final , false otherwise
     */
    public boolean isFinal(){
        return this.isFinal;
    }

    /**
     * To check the name of a given variable
     * @return The name of it
     */
    public String getName() {
        return name;
    }

    /**
     * To check the type of a given variable
     * @return The type of it
     */
    public String getType() {
        return type;
    }

    /**
     * Assign a new value to a given variable
     * @param value
     * @return true if all set ,false if it is impossible
     */
    boolean setValue(String value) {
        if (!isFinal) {
            this.value = value;
            return true;
        }
        return false;
    }

    /**
     * To check the value of a given variable
     * @return the value of it
     */
    public String getValue() { return value; }

}
