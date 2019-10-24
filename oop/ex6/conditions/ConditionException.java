package oop.ex6.conditions;

/**
 * This class represents the Exception of some sort of invalid syntax  Condition
 */
public  class ConditionException extends Exception {
    private static final String INVALID_CONDITION_MSG= "Invalid condition format";

    public ConditionException(){
        super(INVALID_CONDITION_MSG);
    }
}
