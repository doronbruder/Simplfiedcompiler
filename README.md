# Simplfiedcompiler
Ex6 final project from OOP Course
doronbruder

doron bruder‬

‪=============================‬
‪=      File description     =‬
‪=============================
The jar files contains the follow :
2 README 3
3 oop/ex6/Compiler.java 5
4 oop/ex6/conditions/ConditionException.java 7
5 oop/ex6/conditions/ConditionSyntax.java 8
6 oop/ex6/main/InvalidProgramArgsException.java 9
7 oop/ex6/main/Sjavac.java 10
8 oop/ex6/methods/InvalidArgsAmountException.java 11
9 oop/ex6/methods/InvalidArgsFormatException.java 12
10 oop/ex6/methods/Method.java 13
11 oop/ex6/methods/MethodsException.java 14
12 oop/ex6/methods/MethodsSyntax.java 15
13 oop/ex6/parsing/InvalidPlaceForReturnException.java 16
14 oop/ex6/parsing/InvalidScopeDeclarationException.java 17
15 oop/ex6/parsing/MainScopeCallingException.java 18
16 oop/ex6/parsing/Parser.java 19
17 oop/ex6/parsing/ParsingException.java 24
18 oop/ex6/parsing/ParsingMagic.java 25
19 oop/ex6/scopes/NoSuchMethodException.java 26
20 oop/ex6/scopes/SameNameMethodsException.java 27
21 oop/ex6/scopes/SameNameVarsException.java 28
22 oop/ex6/scopes/Scope.java 29
23 oop/ex6/scopes/ScopeException.java 31
1
24 oop/ex6/variables/FinalReassigmentException.java 32
25 oop/ex6/variables/InvalidTypeException.java 33
26 oop/ex6/variables/NoValueAssignmentException.java 34
27 oop/ex6/variables/Variable.java 35
28 oop/ex6/variables/VariableException.java 37
29 oop/ex6/variables/VariableMagic.java 38
30 oop/ex6/variables/VariableSyntax.java


‪=============================‬
‪=          Design           =‬
‪=============================‬
I decided to spilt my program into 6 different modules as follows:
 
Main-which includes the program driver and the args exception
‪_____________________________________________________________‬
Methods-Includes a Method object ,a method Syntax tester
, and all the Exceptions regarding methods
‪_____________________________________________________________‬
Variables -Includes a Variable Object ,
 a Variable useful regex class , Variables Syntax Tester,
 and all the exceptions regarding Variables
‪_________________________________________________________________‬
Scopes- Includes a Scope object ,and all the exceptions regarding Scopes
‪________________________________________________________________________‬
Parsing- Includes a parser object that composes
 a Variables Syntax tester and a Method Syntax Tester,a useful regex
 and magic class, and all the exceptions regarding  parsing
‪___________________________________________________________________________‬
Compiler class -Composes A Parser Object, and fully run a compile process


‪=============================‬
‪=  Implementation details   =‬
‪=============================‬
I first parsed&splited the code into 
different scopes and blocks
And than I went through out all the scopes 
recursively until the end line of the file
Each line we treat accordingly
 and found all different syntax errors
‪=============================‬
‪=    Answers to questions   =‬
‪=============================‬
1.how you handled s-Java code errors 
in this exercise, and why you chose to do so:
I created different Exceptions classes for
 each of the different modulus mentioned above
For each of them I created a "super" abstract
 class and used inheritance when created the specific Exceptions
In addition I created one more Exception 
Class in the main package that only deals with invalid program args.
I did it that way in order to use the advantage
 of inheritance and get Exceptions that "document itself"
In addition I save the encapsulation concept
 since the main module only knows the 
abstract exception classes

‪***‬

  2.How would you modify your code to add 
new types of variables (e.g., float)?
According to my design I can only
 add/change data members in the Variable Class
That takes care of all the variables in the 
program , so I would just add new Magics to 
the VariablesMagic class and add them as well in few methods like getMagic and isValidFormt

 -Below are four features your program 
currently does not support. Please select two of
  them, and describe which modifications/extensions
 you would have to make in your code
  in order to support them. Please briefly 
describe which classes you would add to your code,
  which methods you would add to existing classes
, and which classes you would modify. You
  are not required to implement these features.
Different methods’ types (i.e int foo()) :
I would have to change mostly the Parser 
class that it will consider also a prefix of 
int as valid. , In addition I would need to 
refer to the return statement-to  add 
return+ValidVairableName as a valid return in the
 end of a function -in case it starts with int instead of void.

Using methods of standart java (i.e System.out.println).:
I might save a set of all possible methods
 and I will have to completely change my access
 in the parser and MethodsSyntax classes - 
I won't be able anymore to just add conditions
 -I would have to add a field to the parser that holds the set of functions 

‪***‬
3.In your README file, please describe two of the main regular expressions you used in your code:

"(\\s*[a-zA-Z]\\w*|_\\w+)"
That regex recognizes valid name of Variable - as described in the EX


"\\s*(return)\\s*;"
I used that regex to recognize return statements , 
and to check if they are valid or not  
every method should end with a return 
statement. Moreover I used this regex 
to check if there is a 
return Statement in the main scope which is invalid
 
  
