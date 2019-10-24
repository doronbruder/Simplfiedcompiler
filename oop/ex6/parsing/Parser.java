package oop.ex6.parsing;
import oop.ex6.methods.MethodsException;
import oop.ex6.scopes.NoSuchMethodException;
import oop.ex6.scopes.Scope;
import oop.ex6.methods.Method;
import oop.ex6.methods.MethodsSyntax;
import oop.ex6.scopes.ScopeException;
import oop.ex6.variables.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;

/**
 * This class represents a Parsing object that can get a staring  scope and can read and save methods and variables
    within the scope and all its super scopes
 */
public class Parser {

    // Object to deal with Variables Syntax
    private final static VariableSyntax variableSyntaxTester = new VariableSyntax();

    // Object to deal with Methods syntax
    private static MethodsSyntax methodSyntaxTester = new MethodsSyntax();

    // Our current scope
    private Scope currentScope;

    private String condition;

    // Tells if the Scope we are currently in is a method
    private boolean methodFlag;

    // Lines to deal with( to parse)
    private LinkedList<String> lines;

    // Holds all the sub Scopes of the scope we are currently in
    private ArrayList<Parser> subScopes;


    /**
     * Creates a new Parser object
     * @param superScope The super scope of the scope we are starting our parsing process with
     * @param isMethod Tells if the current scope is method
     * @param scopeLines Lines of the scope
     * @throws VariableException
     * @throws ScopeException
     * @throws ParsingException
     * @throws MethodsException
     */
    public Parser(Scope superScope, boolean isMethod, LinkedList<String> scopeLines) throws VariableException,
            ScopeException,ParsingException,MethodsException {
        this.methodFlag = isMethod;
        this.currentScope = new Scope(superScope);
        this.lines = new LinkedList<>();
        this.subScopes = new ArrayList<>();
        parse(scopeLines);
    }

    /**
     * A default constructor - useful for compose Parser in other classes
     */
    public Parser(){}


    /**
     * Parser main method it parses the scopes, method ,variables within the given lines
     * @param fileLines Lines to parse
     * @throws ParsingException
     * @throws MethodsException
     * @throws VariableException
     * @throws ScopeException
     */
    private void parse(LinkedList<String> fileLines) throws ParsingException,MethodsException
            ,VariableException,ScopeException {
        Matcher openMatcher, closedMatcher, emptyMatcher, returnMatcher;
        String currentLine;
        Parser newScopeParser;
        // Checks if we basically deal with an empty file, so we have nothing to parse
        if (fileLines.size() == 0) {
            return;
        }
        do {
            currentLine = fileLines.poll();
            emptyMatcher = ParsingMagic.EMPTY_LINE_PATTERN.matcher(currentLine);
            // If we are not in an empty line or a comment line
            if (!currentLine.startsWith(ParsingMagic.OPEN_COMMENT) && !emptyMatcher.matches()) {
                returnMatcher = ParsingMagic.RETURN_LINE_PATTERN.matcher(currentLine);
                openMatcher = ParsingMagic.OPEN_SCOPE_PATTERN.matcher(currentLine);
                closedMatcher = ParsingMagic.CLOSE_SCOPE_PATTERN.matcher(currentLine);
                // Checks if we are in the beginning of a new scope. If we are- we will parse it
                if (openMatcher.matches()) {
                    newScopeParser = singleScopeParse(currentLine, currentScope, fileLines);
                    subScopes.add(newScopeParser);
                 // If we are in a return statement line we will check its validity
                } else if (returnMatcher.matches()) {
                    if (currentScope.getSuperScope() == null) {
                        throw new InvalidPlaceForReturnException();
                    } else if (methodFlag) {
                        lines.add(currentLine);
                    }
                    // If we are in the end of a scope we will keep moving to the next line
                } else if (closedMatcher.matches()) {
                    break;
                } else {
                    lines.add(currentLine);
                }
            } else {
                if (fileLines.peek() == null) {
                    break;
                }
            }
        } while (fileLines.size() != 0);
    }


    // Method to parse a single scope
    private Parser singleScopeParse(String ScopeFirstLine, Scope curScope, LinkedList<String> fileLines) throws
            ParsingException,VariableException, ScopeException,MethodsException {
        Matcher methodMatcher = ParsingMagic.METHOD_SCOPE_PATTERN.matcher(ScopeFirstLine);
        Matcher conditionMatcher = ParsingMagic.CONDITION_SCOPE_PATTERN.matcher(ScopeFirstLine);
        if (!methodMatcher.matches()) {
            Parser newScopeParser = conditionParse(curScope, fileLines, conditionMatcher);
            if (newScopeParser != null) return newScopeParser;
        } else {
            return methodScopeParsing(curScope, fileLines, methodMatcher);
        }
        throw new InvalidScopeDeclarationException();
    }

    // Method to parse a condition scope
    private Parser conditionParse(Scope curScope, LinkedList<String> fileLines, Matcher conditionMatcher)
            throws VariableException, ScopeException, ParsingException, MethodsException {
        Parser newScopeParser;
        if (conditionMatcher.matches()) {
            newScopeParser = new Parser(curScope, false, fileLines);
            newScopeParser.condition = conditionMatcher.group(1);
            return newScopeParser;
        }
        return null;
    }

    // Method to parse a method-scope
    private Parser methodScopeParsing(Scope curScope, LinkedList<String> fileLines, Matcher methodMatcher)
            throws VariableException, ScopeException, ParsingException, MethodsException {
        Parser newScopeParser;
        String methodName = methodMatcher.group(1), methodVarLine = methodMatcher.group(2);
        Method newMethod = new Method(methodName, methodVarLine);
        curScope.addMethod(newMethod);
        newScopeParser = new Parser(curScope, true, fileLines);
        newScopeParser.addToCurrentScope(methodVarLine, newScopeParser.getCurrentScope());
        return newScopeParser;
    }

    // Adds a given variable to the scope if it is valid
    private void addToCurrentScope(String variableLine, Scope currentScope)  throws
            VariableException,ScopeException,ParsingException, MethodsException {
        if (variableLine == null) {
            return;
        }
        String[] separatedVariables = variableLine.split(ParsingMagic.COMMA);
        int i = 0;
        int separatedVariablesLength = separatedVariables.length;
        while (i < separatedVariablesLength) {
            String variable = separatedVariables[i];
            if (variable.equals("")) {
                break;
            }
            variablesParser(variable + ParsingMagic.STATEMENT_SUFFIX, currentScope, true);
            i++;
        }
    }

    /**
     * Gets the condition we are inside
     */
    public String getCurrentCondition() { return condition; }

    /**
     * Gets the current scope
     */
    public Scope getCurrentScope() {
        return currentScope;
    }

    /**
     * Tells if we are inside a method scope
     */
    public boolean isMethodFlag(){
        return methodFlag;
    }

    /**
     * Gets all the sub-scopes of the scope we are in
     */
    public ArrayList<Parser> getSubScopes() {
        return subScopes;
    }

    /**
     * Gets all the lines we deals with
     */
    public LinkedList<String> getLines() {
        return lines;
    }



    /**
     * A method to parse variables from an assignment / declaration line
     * @param currFileLine The line to parse variables from
     * @param scope The scope of that line
     * @param funcArgFlag Tells if it's parameters declaration
     * @throws VariableException
     * @throws ScopeException
     * @throws ParsingException
     * @throws MethodsException
     */
    public void variablesParser(String currFileLine, Scope scope, boolean funcArgFlag)
            throws VariableException,ScopeException,ParsingException, MethodsException {
        boolean isFinalFlag = false;
        LinkedList<String> variablesList = new LinkedList<>();
        Matcher methodMatcher=ParsingMagic.METHOD_CALL_PATTERN.matcher(currFileLine);
        // If we are not in a method parameters
        if (!methodMatcher.matches()) {
            String tempAssignment = currFileLine.split(VariableMagic.EQUALS)[0];
            Variable tempVariable = scope.getVariableRec(tempAssignment.trim());
            // Checks if the variable is already assigned , and if it does and it is final - Error
            if (tempVariable != null) {
                if (tempVariable.isFinal()) {
                    throw new FinalReassigmentException();
                }
                variablesList.add(removeSpaces(currFileLine.split(ParsingMagic.STATEMENT_SUFFIX)[0]));
                variableSyntaxTester.AssignmentSyntaxTest(variablesList, tempVariable.getType(), scope,
                        isFinalFlag, funcArgFlag);
            }
            currFileLine = currFileLine.trim();
            String[] lineParts = currFileLine.split("\\s+", 2);
            // Takes into account a final variable declaration
            if (lineParts[0] != null && lineParts[0].trim().equals(VariableMagic.FINAL)) {
                isFinalFlag = true;
                lineParts = lineParts[1].trim().split("\\s+", 2);
            }
            // Start to test our variable syntax
            String curType = variableSyntaxTester.getTypeMagic(lineParts[0] != null ? lineParts[0].trim() : null);
            String[] varArr = lineParts[1].trim().split("[,;]");
            for (String s : varArr) {
                Matcher matcher = VariableMagic.VAR_NAME_PATTERN.matcher(s.trim());
                if (!matcher.matches()) {
                    throw new InvalidTypeException();
                } else {
                    variablesList.add(s);
                }
            }
            variableSyntaxTester.AssignmentSyntaxTest(variablesList, curType, scope, isFinalFlag, funcArgFlag);
        } else {
            // We are actually in a method call (parameters line)
            if (scope.getSuperScope() == null) {
                throw new MainScopeCallingException();
            } else {
                Method curMethod = scope.getMethod(methodMatcher.group(1));
                if (curMethod == null) {
                    throw new NoSuchMethodException();
                } else {
                    if (curMethod.getArgsTypes() != null) {
                        methodSyntaxTester.isLegalMethod(methodMatcher.group(2), curMethod, scope);
                    }
                }
            }
        }
    }

    /**
     * A method to parse types of variables from a parameters declaration line
     * @param line to parse
     * @return list of the variables types we parsed
     * @throws VariableException
     */
    public static ArrayList<String> parseMethodArgsTypes(String line)
            throws VariableException {
        if (line != null) {
            String[] methodVarList = line.split(ParsingMagic.COMMA);
            ArrayList<String> variablesTypes = new ArrayList<>();
            for (String tempStr : methodVarList) {
                variablesTypes.add(variableSyntaxTester.getTypeMagic(tempStr.trim().split("\\s+")[0]));
            }
            return variablesTypes;
        } else {
            return null;
        }
    }

    // Helper method to remove spaces
    private String removeSpaces(String string) {
        return string.replaceAll(ParsingMagic.SPACE, ParsingMagic.EMPTY_STRING);


        }

    }




