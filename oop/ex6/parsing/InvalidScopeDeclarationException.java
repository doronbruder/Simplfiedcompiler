package oop.ex6.parsing;

/**
 * This class represents an error of starting a scope at invalid point in the code
 */
class InvalidScopeDeclarationException extends ParsingException {
    private static final String INVALID_SCOPE_DECLARE_MSG ="Invalid scope declaration";
    InvalidScopeDeclarationException(){
        super(INVALID_SCOPE_DECLARE_MSG);
    }
}
