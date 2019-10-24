package oop.ex6.parsing;
import oop.ex6.variables.VariableMagic;
import java.util.regex.Pattern;

/**
 * This class contains all the "Magic numbers" & patterns regarding Parsing
 */
public class ParsingMagic {
    static final String SPACE = "\\s";

    static final String EMPTY_STRING ="";

    static final String OPEN_COMMENT ="//";

    static final String STATEMENT_SUFFIX =";";

    private final static String NEW_SCOPE_REGEX = ".*\\{\\s*";

    private final static String CLOSE_SCOPE_REGEX = "\\s*}\\s*";

    private final static String METHOD_SCOPE_REGEX = "\\s*void\\s+([a-zA-Z]\\w*)\\s*\\(" +
            "\\s*((double|int|String|char|boolean)\\s+" + VariableMagic.VARIABLE_NAME_REGEX + "\\s*" +
            "(\\s*,\\s*(double|int|String|char|boolean)\\s+" + VariableMagic.VARIABLE_NAME_REGEX + ")*)?" +
            "\\s*\\)\\s*\\{\\s*";

    private final static String CONDITION_SCOPE_REGEX = "\\s*(?:while|if)\\s*\\(\\s*(.*)\\s*\\)" +
            "\\s*\\{\\s*";

    static final String COMMA =",";

    private final static String EMPTY_LINE_REGEX = "\\s*";

    private final static String RETURN_LINE_REGEX = "\\s*(return)\\s*;";

    final static Pattern METHOD_SCOPE_PATTERN = Pattern.compile(METHOD_SCOPE_REGEX);

    final static Pattern OPEN_SCOPE_PATTERN = Pattern.compile(NEW_SCOPE_REGEX);

    final static Pattern CLOSE_SCOPE_PATTERN = Pattern.compile(CLOSE_SCOPE_REGEX);

    final static Pattern CONDITION_SCOPE_PATTERN = Pattern.compile(CONDITION_SCOPE_REGEX);

    final static Pattern EMPTY_LINE_PATTERN = Pattern.compile(EMPTY_LINE_REGEX);

    public final static Pattern RETURN_LINE_PATTERN = Pattern.compile(RETURN_LINE_REGEX);

    final static Pattern METHOD_CALL_PATTERN= Pattern.compile("\\s*([a-zA-Z]\\w*)\\s*\\(\\s*(.*)\\)\\s*;");

}
