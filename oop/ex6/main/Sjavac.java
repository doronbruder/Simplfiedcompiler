package oop.ex6.main;
import oop.ex6.Compiler;
import oop.ex6.parsing.Parser;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;
import oop.ex6.scopes.Scope;

/**
 * The main driver of the program , gets a path of a sJava file and compile it, it returns 0 if it's completely valid
    file , 2 if the argument of the program is Invalid , and 1 if there was a compile  Error - together with a short
    textual massage that Explains the Error

 */
public class Sjavac {


    public static void main(String[] args){
        try {
            // Checks if the program parameters are valid and if it does- starts to compile
            if (args.length == 1) {
                Scope.clearMethods();
                LinkedList<String> fileLines = readFileLines(args);

                try {
                    // Start to compile
                    Parser mainBlock = new Parser(null, false, fileLines);
                    Compiler.compileFile(mainBlock);
                    System.out.println("0");
                } catch (Exception e) {
                    System.out.println("1");
                    System.err.print(e.getMessage());
                }
            } else {
                throw new InvalidProgramArgsException();
            }
            // If we got here the program args are invalid
        } catch (Exception e){
            System.out.println("2");
            System.err.print(e.getMessage());
        }
    }

    // Helper method to read the sJava file
    private static LinkedList<String> readFileLines(String[] args)
            throws FileNotFoundException {
        LinkedList<String> fileLines = new LinkedList<>();
        Scanner scan = new Scanner(new File(args[0]));

        while(scan.hasNextLine()){
            String line = scan.nextLine();
            fileLines.add(line);
        }
        return fileLines;
    }



}
