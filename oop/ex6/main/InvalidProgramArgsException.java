package oop.ex6.main;

/**
 * This class represents an error of invalid args to the program
 */
class InvalidProgramArgsException extends Exception {
    private static final String INVALID_PROGRAM_ARGS_MSG = "Program args invalid amount or format";
    InvalidProgramArgsException(){
        super(INVALID_PROGRAM_ARGS_MSG);
    }

}
