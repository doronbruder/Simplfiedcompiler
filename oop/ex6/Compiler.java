package oop.ex6;
import oop.ex6.conditions.ConditionException;
import oop.ex6.conditions.ConditionSyntax;
import oop.ex6.methods.MethodsException;
import oop.ex6.parsing.InvalidPlaceForReturnException;
import oop.ex6.parsing.Parser;
import oop.ex6.parsing.ParsingException;
import oop.ex6.parsing.ParsingMagic;
import oop.ex6.scopes.ScopeException;
import oop.ex6.variables.VariableException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;

/**
 * This class is the main Sjava tester of the program which checks both syntax and scoping issues
 */
public  class Compiler {
    private static ConditionSyntax conditionSyntaxTester = new ConditionSyntax();
    private static Parser ScopesParser = new Parser();

    /**
     * Compile the given Scope(if call with the main scope it's actually compile the entire file)
     * @param currentScopeParser ScopeParser object of the current Scope
     * @throws ParsingException
     * @throws ConditionException
     * @throws MethodsException
     * @throws ScopeException
     * @throws VariableException
     */
    public static  void compileFile(Parser currentScopeParser) throws ParsingException,
            ConditionException, MethodsException, ScopeException, VariableException {
        LinkedList<String> currentScopeLines=currentScopeParser.getLines();

        if (currentScopeParser.getCurrentCondition() != null) {
            conditionCompile(currentScopeParser);
        }
        if (currentScopeParser.isMethodFlag()) {
            methodCompile(currentScopeParser);
        }
        for(String line:currentScopeLines){
            ScopesParser.variablesParser(line,currentScopeParser.getCurrentScope(),false);
        }
        for (Iterator<Parser> iterator = currentScopeParser.getSubScopes().iterator(); iterator.hasNext(); ) {
            Parser subScopeParser = iterator.next();
            compileFile(subScopeParser);
        }
    }
    // Deals with conditions compiling issues
    private static void conditionCompile(Parser currentScopeParser)
            throws VariableException, ConditionException {
        conditionSyntaxTester.conditionSyntaxTest(currentScopeParser.getCurrentCondition()
                , currentScopeParser.getCurrentScope());
    }

    // Deals with methods compiling issues
    private static void methodCompile(Parser currentScopeParser) throws InvalidPlaceForReturnException {
        String lastLine = currentScopeParser.getLines().peekLast();
        Matcher matcher;
        matcher = ParsingMagic.RETURN_LINE_PATTERN.matcher(lastLine);
        if (!matcher.matches()) {
            throw new InvalidPlaceForReturnException();
        } else {
            currentScopeParser.getLines().pollLast();
        }
    }
}
