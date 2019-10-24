package oop.ex6.variables;
import java.util.regex.Pattern;

/**
 * This class contains all the magic numbers and useful regex & patterns regarding Variables
 */
public class VariableMagic {

    public static final String FINAL = "final";

    public static final String STRING = "STRING";

    public static final String INT = "INT";

    public static final String DOUBLE = "DOUBLE";

    public static final String CHAR = "CHAR";

    public static final String BOOLEAN = "BOOLEAN";

    public static final String VAR="";

    public final static String EQUALS= "=";

    public final static String DOUBLE_REGEX = "[+-]?(\\d+(\\.\\d+)?)";

    public final static String INT_REGEX = "[+-]?\\d+";

    public final static String STRING_REGEX = "\"(.*)\"";

    public final static String CHAR_REGEX = "'.'";

    public final static String BOOLEAN_REGEX = "(true|false|[+-]?(\\d+(\\.\\d+)?))";

    public final static String VARIABLE_NAME_REGEX = "(\\s*[a-zA-Z]\\w*|_\\w+)(\\s*=\\s*(.)+)?";


    public final static Pattern DOUBLE_PATTERN = Pattern.compile(DOUBLE_REGEX);

    public final static Pattern INTEGER_PATTERN = Pattern.compile(INT_REGEX);

    public final static Pattern STRING_PATTERN = Pattern.compile(STRING_REGEX);

    public final static Pattern CHAR_PATTERN = Pattern.compile(CHAR_REGEX);

    public final static Pattern BOOLEAN_PATTERN = Pattern.compile(BOOLEAN_REGEX);

    public final static Pattern VAR_NAME_PATTERN = Pattern.compile(VARIABLE_NAME_REGEX);


}
