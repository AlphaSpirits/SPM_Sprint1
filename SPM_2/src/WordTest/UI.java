package WordTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.lang.model.SourceVersion;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import javax.swing.table.DefaultTableModel;

public class UI extends javax.swing.JFrame {

    ArrayList<Integer> Ctc = new ArrayList<Integer>();
    ArrayList<Integer> Cnc = new ArrayList<Integer>();
    ArrayList<Integer> lineNumber = new ArrayList<Integer>();
    ArrayList<String> programStatement = new ArrayList<String>();
    ArrayList<Integer> lineComplexity = new ArrayList<Integer>();
    ArrayList<Integer> ArrCtc = new ArrayList<Integer>();
    ArrayList<Integer> ArrCnc = new ArrayList<Integer>();
    ArrayList<Integer> Cps = new ArrayList<Integer>();
    ArrayList<Integer> RecursiveCps = new ArrayList<Integer>();
    ArrayList<Integer> Tw = new ArrayList<Integer>();
    ArrayList<Integer> Cr = new ArrayList<Integer>();
    ArrayList<Integer> Ci = new ArrayList<Integer>();
    int totlComplexity = 0;
    int recursion = 0;
    int maxCases, testMaxCases = 0;

    public UI() {
        initComponents();
        setExtendedState(UI.MAXIMIZED_BOTH);
        ButtonGroup group = new ButtonGroup();
        group.add(btnjava);
        group.add(btnc);
        group.add(btnClear);
        btnClear.setVisible(false);

    }

    public int getSwitchCaseCount() {

        int current_maxCases = 0;
        Scanner fileInput = new Scanner(uploadedContent.getText());

        // Read through file and find words
        while (fileInput.hasNextLine()) {
            String scannedline = fileInput.nextLine();

            if (scannedline.matches("(\\s+)case(.*)")) {
                current_maxCases++;
                // System.out.println(scannedline);
                if (current_maxCases > maxCases) {
                    maxCases = current_maxCases;
                }
            } else if (scannedline.matches("(\\s+)case(.*)")) {
                if (current_maxCases > 0) {
                    current_maxCases--;
                    System.out.println(scannedline);
                }
            }
        }

        return maxCases;

    }

    public ArrayList<Integer> calculateCtc() {
        int CtcCounter = 0;
        int lineNumber = 0;
        int caseCount = 0;

        Scanner fileInput = new Scanner(uploadedContent.getText());

        // Read through file and find words
        while (fileInput.hasNextLine()) {

            lineNumber = lineNumber + 1;

            String scannedline = fileInput.nextLine();

            //******************************IF uploded source code in JAVA language
            if (btnjava.isSelected()) {
                if (scannedline.startsWith("System.out.println") || scannedline.startsWith("(\\s+)\\(.*)") || scannedline.startsWith("(\\s+)//(.*)") || (scannedline.startsWith("(\\s+)/*(.*)")) || scannedline.startsWith("(\\s+)*/(.*)")) {
                    CtcCounter = 0;
                    //continue;
                } else if (scannedline.contains("if")) {
                    CtcCounter = CtcCounter + 1;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 1;
                    }
                } else if (scannedline.contains("for")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("while")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("do")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("catch")) {
                    CtcCounter = CtcCounter + 1;
                } else if (scannedline.matches("(\\s+)switch(.*)")) {
                    getSwitchCaseCount();
                    CtcCounter = maxCases;
                }

                Ctc.add(CtcCounter);

                CtcCounter = 0;
                maxCases = 0;

            } //******************************IF uploded source code in C++ language
            else if (btnc.isSelected()) {

            if (scannedline.contains("cout <<") || scannedline.startsWith("(\\s+)\\(.*)") || scannedline.startsWith("(\\s+)//(.*)") || (scannedline.startsWith("(\\s+)/*(.*)")) || scannedline.startsWith("(\\s+)*/(.*)")) {

                    CtcCounter = 0;
                    //continue;
                } else if (scannedline.contains("if")) {
                    CtcCounter = CtcCounter + 1;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 1;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 1;
                    }
                } else if (scannedline.contains("for")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("while")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("do")) {
                    CtcCounter = CtcCounter + 2;
                    if (scannedline.contains("||")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&&")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("|")) {
                        CtcCounter = CtcCounter + 2;
                    } else if (scannedline.contains("&")) {
                        CtcCounter = CtcCounter + 2;
                    }
                } else if (scannedline.contains("catch")) {
                    CtcCounter = CtcCounter + 1;
                } else if (scannedline.matches("(\\s+)switch(.*)")) {
                    getSwitchCaseCount();
                    CtcCounter = maxCases;
                }

                Ctc.add(CtcCounter);

                CtcCounter = 0;
                maxCases = 0;
            }
        }

        noOfLines.setText("No of Lines : " + lineNumber);

        return Ctc;

    }

    public ArrayList<Integer> calCtc() {

        int CtcCounterTest = 0;

        String contentTest = "public class FibonacciMain {\n"
                + "public static long fibonacci(long number) {\n"
                + "        if ((number == 0) || (number == 1)) { // base cases\n"
                + "            return number;\n"
                + "        } \n"
                + "		else {\n"
                + "            // recursion step\n"
                + "            return fibonacci(number - 1) + fibonacci(number - 2);\n"
                + "        }\n"
                + "    }\n"
                + "public static void main(String args[]) {\n"
                + "        for (int count = 0; count <= 10; count++) {\n"
                + "            System.out.println(\"if Fibonacci of \" + count + \" is \"+ fibonacci(count));\n"
                + "        }\n"
                + "    }\n"
                + "}";

        Scanner fileInput = new Scanner(contentTest);
        while (fileInput.hasNextLine()) {
            String scannedline = fileInput.nextLine();
            if (scannedline.contains("System.out.println") || scannedline.contains("\\") || scannedline.startsWith("//") || (scannedline.startsWith("/*")) || scannedline.startsWith("*/")) {
                CtcCounterTest = 0;
            } else if (scannedline.contains("if")) {
                CtcCounterTest = CtcCounterTest + 1;
                if (scannedline.contains("||")) {
                    CtcCounterTest = CtcCounterTest + 1;
                } else if (scannedline.contains("&&")) {
                    CtcCounterTest = CtcCounterTest + 1;
                } else if (scannedline.contains("|")) {
                    CtcCounterTest = CtcCounterTest + 1;
                } else if (scannedline.contains("&")) {
                    CtcCounterTest = CtcCounterTest + 1;
                }
            } else if (scannedline.contains("for")) {
                CtcCounterTest = CtcCounterTest + 2;
                if (scannedline.contains("||")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("&&")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("|")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("&")) {
                    CtcCounterTest = CtcCounterTest + 2;
                }
            } else if (scannedline.contains("while")) {
                CtcCounterTest = CtcCounterTest + 2;
                if (scannedline.contains("||")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("&&")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("|")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("&")) {
                    CtcCounterTest = CtcCounterTest + 2;
                }
            } else if (scannedline.contains("do")) {
                CtcCounterTest = CtcCounterTest + 2;
                if (scannedline.contains("||")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("&&")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("|")) {
                    CtcCounterTest = CtcCounterTest + 2;
                } else if (scannedline.contains("&")) {
                    CtcCounterTest = CtcCounterTest + 2;
                }
            } else if (scannedline.contains("catch")) {
                CtcCounterTest = CtcCounterTest + 1;
            } else if (scannedline.matches("(\\s+)switch(.*)")) {
                getSwitchCaseCount();
                CtcCounterTest = testMaxCases;
            }
            ArrCtc.add(CtcCounterTest);
            CtcCounterTest = 0;
            testMaxCases = 0;
        }
        return ArrCtc;
    }

    
    public ArrayList<Integer> calculateCs(){//for testing
        
        String testContentCs = "public class FibonacciMain {\n"
                + "public static long fibonacci(long number) {\n"
                + "        if ((number == 0) || (number == 1)) { // base cases\n"
                + "            return number;\n"
                + "        } \n"
                + "		else {\n"
                + "            // recursion step\n"
                + "            return fibonacci(number - 1) + fibonacci(number - 2);\n"
                + "        }\n"
                + "    }\n"
                + "public static void main(String args[]) {\n"
                + "        for (int count = 0; count <= 10; count++) {\n"
                + "            System.out.println(\"if Fibonacci of \" + count + \" is \"+ fibonacci(count));\n"
                + "        }\n"
                + "    }\n"
                + "}";
        
       String[] line = testContentCs.split("\r\n|\r|\n");

        ArrayList<String> variableList = new ArrayList<String>();
        ArrayList<String> arrayToStoreBrackets = new ArrayList<String>();
        int countComplexity = 0;
        String operatorsCs1[] = {
            "+", "-", "*", "/", "%", "++", "--",
            "==", "!=", ">", "<", ">=", "<=",
            "&&", "||", "!",
            "|", "^", "~", "<<", ">>", ">>>", "<<<",
            ",", "->", ".", "::",
            "+=", "-=", "*=", "/=", "=", ">>>=", "|=", "&=", "%=", "<<=", ">>=", "^="
        };
        String cPlusPlusKeywords[] = {
            "printf", "cout", "cin", "include",
            "alignas", "alignof", "and_eq", "asm", "atomic_cancel", "atomic_commit",
            "atomic_noexcept", "bitand", "bitor", "compl", "concept", "consteval",
            "constexpr", "const_cast", "co_await", "co_return", "co_yield", "decltype",
            "dynamic_cast", "explicit", "export", "extern", "friend", "inline", "mutable",
            "noexcept", "not_eq", "nullptr", "operator", "or_eq", "reinterpret_cast",
            "requires", "signed", "sizeof", "static_assert", "static_cast", "struct",
            "template", "thread_local", "typedef", "typeid", "typename", "union",
            "unsigned", "using", "virtual", "wchar_t", "xor", "xor_eq"
        };
        String manipulators[] = {"endl", "\n"};

        String dataTypesToFindVariables[] = {
            "int", "Integer", "long", "float", "String", "void"
        };

        String allExceptions[] = {
            "ArithmeticException", "ArrayIndexOutOfBoundsException", "ClassNotFoundException", "FileNotFoundException", "IOException",
            "InterruptedException", "NoSuchFieldException", "NoSuchMethodException", "NullPointerException", "NumberFormatException",
            "RuntimeException", "StringIndexOutOfBoundsException"
        };

        //find variables
        for (int v = 0; v < line.length; v++) {

            String[] wordsForVariables = line[v].split(" ");
            for (int r = 0; r < wordsForVariables.length; r++) {
                for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {//if variable is after data type
                    if (wordsForVariables[r].equals(dataTypesToFindVariables[dataType])) {
                        String idVariable = wordsForVariables[r + 1];
                        if (!idVariable.contains("(")) {//variable but not a method name
                            variableList.add(idVariable);//add to variable list
                        }
                    }
                }
                if (wordsForVariables[r].contains("(")) {//if variable is after data type which belongs to word with brackets

                    int indexOfBrack = wordsForVariables[r].indexOf("(");
                    int fistIndexAfterBrack = indexOfBrack + 1;
                    String variableName = wordsForVariables[r].substring(fistIndexAfterBrack);

                    for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {
                        if (variableName.equals(dataTypesToFindVariables[dataType])) {//check if the word is data type
                            String variable = wordsForVariables[r + 1];
                            if (!variable.contains("[")) {//if variable is not an array
                                //variableList.add(variable);
                                if (variable.endsWith(")")) {
                                    int index1 = variable.lastIndexOf(')');//get last occured index of )
                                    String variableWithoutBracketAtEnd = variable.substring(0, index1);
                                    variableList.add(variableWithoutBracketAtEnd);
                                } else {
                                    variableList.add(variable);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < line.length; i++) {
            String newString = line[i].trim(); //remove white spaces first occured
            if (newString.startsWith("/") || newString.startsWith("*")) {//set lines with comments to 0 Cs
                countComplexity = 0;
            } else {
                String[] words = line[i].split(" ");
                for (int r = 0; r < words.length; r++) {

                    if (SourceVersion.isKeyword(words[r])) {//java keywords
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].equals("public") || words[r].equals("static") || words[r].equals("else") || words[r].equals("try") || words[r].equals("return")) {
                        countComplexity = countComplexity - 1;
                    }
                    if (words[r].equals("new") || words[r].equals("delete") || words[r].equals("throw") || words[r].equals("throws")) {
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].contains("System") && words[r].contains("out")) {// System and out
                        countComplexity = countComplexity + 2;
                    }
                    if (words[r].contains(".")) {//if . contains in a word like sout
                        char[] chWords = words[r].toCharArray();
                        for (int m = 0; m < chWords.length; m++) {
                            if (chWords[m] == '.') {
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].startsWith("&") || words[r].startsWith("*")) {//reference and dereference operator
                        char[] ch = words[r].toCharArray();
                        if (ch.length > 1) {
                            countComplexity = countComplexity + 2;
                        }
                    }
                    if (words[r].matches(".*\\d.*")) {//check if the value is a number
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].contains("(")) {//if method/object or word within double quotes
                        //Cs for method
                        int indexOfBrack = words[r].indexOf("(");
                        String mn = words[r].substring(0, indexOfBrack);
                        if (!mn.equals(null) && !mn.isEmpty()) {//if method name exists
                            countComplexity = countComplexity + 1;
                        }

                        int indexOfBracket = words[r].indexOf("(");
                        int indexOfQuote = indexOfBracket + 2;
                        String wordWithQuote = words[r].substring(0, indexOfQuote);

                        if (wordWithQuote.contains("\"")) {//words within double quotes 
                            countComplexity = countComplexity + 1;
                        }
                        //Cs for data types with brackets -- ex: (long)
                        int fistIndexAfterBracket = indexOfBracket + 1;
                        String dataTypeName = words[r].substring(fistIndexAfterBracket);
                        for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {
                            if (dataTypeName.contains(dataTypesToFindVariables[dataType])) {//check if the word is data type
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].startsWith("\"")) {//words within double quotes seperately
                        if (!words[r + 1].equals("+")) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].contains("[")) {//if array
                        countComplexity = countComplexity + 1;
                    }
                    for (int cp = 0; cp < cPlusPlusKeywords.length; cp++) {//check if c++ keywords
                        if (words[r].equals(cPlusPlusKeywords[cp])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    for (int opCs1 = 0; opCs1 < operatorsCs1.length; opCs1++) {//according to operators with Cs 1
                        if (words[r].equals(operatorsCs1[opCs1])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].contains("++") || words[r].contains("--")) { //if words contain ++ or --
                        countComplexity = countComplexity + 1;
                    }
                    for (int ex = 0; ex < allExceptions.length; ex++) { //exceptions occurs
                        if (words[r].contains(allExceptions[ex])) {
                            countComplexity = countComplexity + 1;
                            if (words[r].startsWith("catch") || words[r - 1].equals("catch")) {// check for e --ex: catch(FileNotFoundException e) or catch
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].equals("throw")) {//if throw e
                        if (words[r + 1].startsWith("e") && words[r + 1].endsWith("e;")) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].contains("e.")) {//if contains e.--ex:e.getM()--here 2 because of method attach to it
                        countComplexity = countComplexity + 2;
                    }
                    if (words[r].startsWith("catch") && words[r].endsWith(")")) {//if catch within a word -- ex: catch(FileNotFoundException e) 
                        countComplexity = countComplexity + 1;
                    }
                    for (int manip = 0; manip < manipulators.length; manip++) {//check if the value is a manipulator
                        if (words[r].equals(manipulators[manip])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    for (int vl = 0; vl < variableList.size(); vl++) {//check if variable
                        String var = variableList.get(vl);
                        if (words[r].contains(var)) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].equals("new")) {//for class names -- ex: FileReader f = new ....
                        countComplexity = countComplexity + 1;
                    }

                }
            }
            lineComplexity.add(countComplexity);
            countComplexity = 0;
        }
        return (lineComplexity);
    
    }

    
    
    public ArrayList<Integer> calculateCnc() {

        int current_max = 0; // current count  
        int CncCounter = 0; // overall maximum count
        int lineNumber = 0;
        Scanner fileInput = new Scanner(uploadedContent.getText());

        // Read through file and find words
        while (fileInput.hasNextLine()) {
            lineNumber = lineNumber + 1;
            String scannedline = fileInput.nextLine();

            //******************************IF uploded source code in JAVA language
            if (btnjava.isSelected()) {
                if (scannedline.matches("(\\s+)if(.*)") || scannedline.matches("(\\s+)else(.*)") || scannedline.matches("(\\s+)for(.*)")|| scannedline.matches("(\\s+)while(.*)")) {
                    current_max++;
                    ////////System.out.println(scannedline);
                    // update max if required  
                    if (current_max > CncCounter) {
                        CncCounter = current_max;
                    }
                } else if (scannedline.contains("}")) {
                    if (current_max > 0) {
                        current_max--;
                        CncCounter = current_max;
                    }
                }

                Cnc.add(CncCounter);
            }
            //******************************IF uploded source code in C++ language
            if (btnc.isSelected()) {
                if (scannedline.matches("(\\s+)if(.*)") || scannedline.matches("(\\s+)else(.*)") || scannedline.matches("(\\s+)for(.*)")|| scannedline.matches("(\\s+)while(.*)")) {
                    current_max++;
                    System.out.println(scannedline);
                    // update max if required  
                    if (current_max > CncCounter) {
                        CncCounter = current_max;
                    }
                } else if (scannedline.contains("}")) {
                    if (current_max > 0) {
                        current_max--;
                        CncCounter = current_max;
                    }
                }

                Cnc.add(CncCounter);
            }

        }
        noOfLines.setText("No of Lines : " + lineNumber);
        return Cnc;
    }

    public ArrayList<String> readFile() {
        int lineNo = 0;
        Scanner fileInput = new Scanner(uploadedContent.getText());
        // Read through file and find words
        while (fileInput.hasNextLine()) {
            lineNo = lineNo + 1;
            String scannedline = fileInput.nextLine();
            programStatement.add(scannedline);
            lineNumber.add(lineNo);
        }
        return programStatement;
    }

    public ArrayList<Integer> calcCs() {

        String fileInput = uploadedContent.getText();

        //ArrayList<Integer> lineComplexity = new ArrayList<Integer>();
        ArrayList<String> variableList = new ArrayList<String>();
        ArrayList<String> arrayToStoreBrackets = new ArrayList<String>();
        int countComplexity = 0;
        String operatorsCs1[] = {
            "+", "-", "*", "/", "%", "++", "--",
            "==", "!=", ">", "<", ">=", "<=",
            "&&", "||", "!",
            "|", "^", "~", "<<", ">>", ">>>", "<<<",
            ",", "->", ".", "::",
            "+=", "-=", "*=", "/=", "=", ">>>=", "|=", "&=", "%=", "<<=", ">>=", "^="
        };
        String cPlusPlusKeywords[] = {
            "printf", "cout", "cin", "include",
            "alignas", "alignof", "and_eq", "asm", "atomic_cancel", "atomic_commit",
            "atomic_noexcept", "bitand", "bitor", "compl", "concept", "consteval",
            "constexpr", "const_cast", "co_await", "co_return", "co_yield", "decltype",
            "dynamic_cast", "explicit", "export", "extern", "friend", "inline", "mutable",
            "noexcept", "not_eq", "nullptr", "operator", "or_eq", "reinterpret_cast",
            "requires", "signed", "sizeof", "static_assert", "static_cast", "struct",
            "template", "thread_local", "typedef", "typeid", "typename", "union",
            "unsigned", "using", "virtual", "wchar_t", "xor", "xor_eq"
        };
        String manipulators[] = {"endl", "\n"};

        String dataTypesToFindVariables[] = {
            "int", "Integer", "long", "float", "String", "void"
        };

        String allExceptions[] = {
            "ArithmeticException", "ArrayIndexOutOfBoundsException", "ClassNotFoundException", "FileNotFoundException", "IOException",
            "InterruptedException", "NoSuchFieldException", "NoSuchMethodException", "NullPointerException", "NumberFormatException",
            "RuntimeException", "StringIndexOutOfBoundsException"
        };

        String[] line = fileInput.split("\r\n|\r|\n");

        //find variables
        for (int v = 0; v < line.length; v++) {

            String[] wordsForVariables = line[v].split(" ");
            for (int r = 0; r < wordsForVariables.length; r++) {
                for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {//if variable is after data type
                    if (wordsForVariables[r].equals(dataTypesToFindVariables[dataType])) {
                        String idVariable = wordsForVariables[r + 1];
                        if (!idVariable.contains("(")) {//variable but not a method name
                            variableList.add(idVariable);//add to variable list
                        }
                    }
                }
                if (wordsForVariables[r].contains("(")) {//if variable is after data type which belongs to word with brackets

                    int indexOfBrack = wordsForVariables[r].indexOf("(");
                    int fistIndexAfterBrack = indexOfBrack + 1;
                    String variableName = wordsForVariables[r].substring(fistIndexAfterBrack);

                    for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {
                        if (variableName.equals(dataTypesToFindVariables[dataType])) {//check if the word is data type
                            String variable = wordsForVariables[r + 1];
                            if (!variable.contains("[")) {//if variable is not an array
                                //variableList.add(variable);
                                if (variable.endsWith(")")) {
                                    int index1 = variable.lastIndexOf(')');//get last occured index of )
                                    String variableWithoutBracketAtEnd = variable.substring(0, index1);
                                    variableList.add(variableWithoutBracketAtEnd);
                                } else {
                                    variableList.add(variable);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < line.length; i++) {
            String newString = line[i].trim(); //remove white spaces first occured
            if (newString.startsWith("/") || newString.startsWith("*")) {//set lines with comments to 0 Cs
                countComplexity = 0;
            } else {
                String[] words = line[i].split(" ");
                for (int r = 0; r < words.length; r++) {

                    if (SourceVersion.isKeyword(words[r])) {//java keywords
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].equals("public") || words[r].equals("static") || words[r].equals("else") || words[r].equals("try") || words[r].equals("return")) {
                        countComplexity = countComplexity - 1;
                    }
                    if (words[r].equals("new") || words[r].equals("delete") || words[r].equals("throw") || words[r].equals("throws")) {
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].contains("System") && words[r].contains("out")) {// System and out
                        countComplexity = countComplexity + 2;
                    }
                    if (words[r].contains(".")) {//if . contains in a word like sout
                        char[] chWords = words[r].toCharArray();
                        for (int m = 0; m < chWords.length; m++) {
                            if (chWords[m] == '.') {
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].startsWith("&") || words[r].startsWith("*")) {//reference and dereference operator
                        char[] ch = words[r].toCharArray();
                        if (ch.length > 1) {
                            countComplexity = countComplexity + 2;
                        }
                    }
                    if (words[r].matches(".*\\d.*")) {//check if the value is a number
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].contains("(")) {//if method/object or word within double quotes
                        //Cs for method
                        int indexOfBrack = words[r].indexOf("(");
                        String mn = words[r].substring(0, indexOfBrack);
                        if (!mn.equals(null) && !mn.isEmpty()) {//if method name exists
                            countComplexity = countComplexity + 1;
                        }

                        int indexOfBracket = words[r].indexOf("(");
                        int indexOfQuote = indexOfBracket + 2;
                        String wordWithQuote = words[r].substring(0, indexOfQuote);

                        if (wordWithQuote.contains("\"")) {//words within double quotes 
                            countComplexity = countComplexity + 1;
                        }
                        //Cs for data types with brackets -- ex: (long)
                        int fistIndexAfterBracket = indexOfBracket + 1;
                        String dataTypeName = words[r].substring(fistIndexAfterBracket);
                        for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {
                            if (dataTypeName.contains(dataTypesToFindVariables[dataType])) {//check if the word is data type
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].startsWith("\"")) {//words within double quotes seperately
                        if (!words[r + 1].equals("+")) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].contains("[")) {//if array
                        countComplexity = countComplexity + 1;
                    }
                    for (int cp = 0; cp < cPlusPlusKeywords.length; cp++) {//check if c++ keywords
                        if (words[r].equals(cPlusPlusKeywords[cp])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    for (int opCs1 = 0; opCs1 < operatorsCs1.length; opCs1++) {//according to operators with Cs 1
                        if (words[r].equals(operatorsCs1[opCs1])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].contains("++") || words[r].contains("--")) { //if words contain ++ or --
                        countComplexity = countComplexity + 1;
                    }
                    for (int ex = 0; ex < allExceptions.length; ex++) { //exceptions occurs
                        if (words[r].contains(allExceptions[ex])) {
                            countComplexity = countComplexity + 1;
                            if (words[r].startsWith("catch") || words[r - 1].equals("catch")) {// check for e --ex: catch(FileNotFoundException e) or catch
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].equals("throw")) {//if throw e
                        if (words[r + 1].startsWith("e") && words[r + 1].endsWith("e;")) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].contains("e.")) {//if contains e.--ex:e.getM()--here 2 because of method attach to it
                        countComplexity = countComplexity + 2;
                    }
                    if (words[r].startsWith("catch") && words[r].endsWith(")")) {//if catch within a word -- ex: catch(FileNotFoundException e) 
                        countComplexity = countComplexity + 1;
                    }
                    for (int manip = 0; manip < manipulators.length; manip++) {//check if the value is a manipulator
                        if (words[r].equals(manipulators[manip])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    for (int vl = 0; vl < variableList.size(); vl++) {//check if variable
                        String var = variableList.get(vl);
                        if (words[r].contains(var)) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].equals("new")) {//for class names -- ex: FileReader f = new ....
                        countComplexity = countComplexity + 1;
                    }

                }
            }
            lineComplexity.add(countComplexity);
            countComplexity = 0;
        }
//        result.append("Measuring the complexity of a program statement due to size (Cs) \n");
//        for(int l=0; l<lineComplexity.size(); l++){
//                result.append("line "+l+" ---- "+lineComplexity.get(l) + " \n");
//            }
        return (lineComplexity);

    }
//*** for cp start ************************************************************************

    public String FindRecursiveMethod() {
        try {


            String sourceCode = uploadedContent.getText();
            String[] liness = sourceCode.split("\r\n|\r|\n");
            for (int i = 0; i < liness.length; i++) {
                if (liness[i].contains("return")) {
                    String[] words = liness[i].split(" ");
                    for (int r = 0; r < words.length; r++) {
                        if (words[r].contains("(")) {
                            int index = words[r].indexOf("(");
                            String methodName = words[r].substring(0, index);
                            String methodLine = methodName + " " + i;
                            return methodLine;
                        }
                    }

                } else {
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }

    public int CalculateCpForNonRecursive() {
        try {
            ArrayList<Integer> Ci = dumyNonCi();
            ArrayList<Integer> Cs = calcCs();
            ArrayList<Integer> CtcCal = calculateCtc();
            for (int i = 0; i < CtcCal.size(); i++) {
                System.out.println("ctc " + CtcCal.get(i));
            }

            ArrayList<Integer> CncCal = calculateCnc();
            for (int i = 0; i < Ctc.size(); i++) {
                int CtcVal = CtcCal.get(i);
                int CncVal = CncCal.get(i);
                int CiVal = Ci.get(i);
                int TwVal = CtcVal + CncVal + CiVal;
                Tw.add(TwVal);
            }

            for (int i = 0; i < Tw.size(); i++) {
                int cps = Tw.get(i) * Cs.get(i);
                Cps.add(cps);
            }
            String sourceCode = uploadedContent.getText();
            String[] liness = sourceCode.split("\r\n|\r|\n");
            for (int i = 0; i < Cps.size(); i++) {
                totlComplexity = totlComplexity + Cps.get(i);
            }

            return totlComplexity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;


    }

    public int FindRecursiveFirstLine(String mname) {
        String sourceCode = uploadedContent.getText();
        String[] liness = sourceCode.split("\r\n|\r|\n");
        String methodName = mname + "(";
        for (int i = 0; i < liness.length; i++) {
            if (liness[i].contains(methodName)) {
                return i;
            }
        }
        return -1;
    }

    public int CalculateTotalCpsForRecursive(int firstLine, int LastLine) {
        ArrayList<Integer> CncCal = calculateCnc();
        ArrayList<Integer> CtcCal = calculateCtc();
        ArrayList<Integer> Ci = dumyCi();
        ArrayList<Integer> Cs = calcCs();

        String sourceCode = uploadedContent.getText();
        String[] liness = sourceCode.split("\r\n|\r|\n");
        for (int i = 0; i < liness.length; i++) {

            //calculate CPs value for non recursive lines and get total CPs
            int tw = CtcCal.get(i) + CncCal.get(i) + Ci.get(i);
            Tw.add(tw);
            System.out.println("Tw added");
        }
        ;
        for (int i = 0; i < liness.length; i++) {

            int cps = Tw.get(i) * Cs.get(i);

            Cps.add(cps);
            RecursiveCps.add(cps);


        }

        int totalCps = 0;
        for (int i = 0; i < Cps.size(); i++) {
            if (i < firstLine || i > LastLine) {
                System.out.println("Cps... for line " + i + " " + Cps.get(i));
                totalCps = totalCps + Cps.get(i);
            }

        }
        return totalCps;
    }

    public int CalculateTotalCrForRecursive(int firstLine, int LastLine) {
        String sourceCode = uploadedContent.getText();
        String[] liness = sourceCode.split("\r\n|\r|\n");

        ArrayList<Integer> CtcCal = calculateCtc();
        ArrayList<Integer> CncCal = calculateCnc();
        ArrayList<Integer> Ci = dumyCi();
        ArrayList<Integer> Cs = calcCs();
        ArrayList<Integer> Tw = new ArrayList<Integer>();
        ArrayList<Integer> Cps = new ArrayList<Integer>();
        for (int i = 0; i < RecursiveCps.size(); i++) {
            if (i < firstLine || i > LastLine) {
                Cr.add(0);
            } else {
                int cr = 2 * RecursiveCps.get(i);
                Cr.add(cr);
            }
        }

        int totalCr = 0;
        for (int i = 0; i < Cr.size(); i++) {
            totalCr = totalCr + Cr.get(i);
        }
        return totalCr;
    }

    public ArrayList<Integer> dumyCi() {
        int[] data = {0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0};

        for (int i = 0; i < data.length; i++) {
            Ci.add(data[i]);
        }
        return Ci;
    }

    public ArrayList<Integer> dumyNonCi() {
        int[] data = {0, 0, 2, 0, 2, 2, 0, 2, 2, 2, 0, 0, 2, 0, 2, 0, 2, 2, 0, 0, 0};

        for (int i = 0; i < data.length; i++) {
            Ci.add(data[i]);
        }
        return Ci;
    }
//*** for cp ends ************************************************************************    

    public void CalcCp() {
        try {

            String rec = FindRecursiveMethod();
            if (rec == " ") {
                recursion = 0;
                Cr.clear();
                //for non recursive methods
                System.out.println("no recursive");
                totlComplexity = CalculateCpForNonRecursive();
                System.out.println("totlComplexity " + totlComplexity);
                CpValue.setText(Integer.toString(totlComplexity));
            } else {
                //for recursive methods
                recursion = 1;
                String[] result = rec.split(" ");
                String methodName = result[0];
                int LastLine = Integer.parseInt(result[1]);
                System.out.println("method name " + methodName);
                System.out.println("last line number " + LastLine);
                int firstLine = FindRecursiveFirstLine(methodName);
                if (firstLine == -1) {
                    System.err.println("wrong line");
                } else {
                    System.out.println("First Line number " + firstLine);
                    int recStart = firstLine;
                    int recEnd = LastLine;
                    System.out.println("calculate cr value");

                    //Calculate Cps for methods not belongs to recursive method
                    int totalCps = CalculateTotalCpsForRecursive(firstLine, LastLine);
                    System.out.println("total Cps " + totalCps);
                    //calculate cr value and return total cr value
                    int totalCr = CalculateTotalCrForRecursive(firstLine, LastLine);
                    System.out.println("total Cr " + totalCr);

                    int Cp = totalCr + totalCps;
                    CpValue.setText(Integer.toString(Cp));
                    System.out.println("Total complexity of the program " + Cp);
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> calcCnc() {

        int Testcurrent_max = 0;
        int TestCncCounter = 0;
        String contentTestCnc = "public class FibonacciMain {\n"
                + "public static long fibonacci(long number) {\n"
                + "        if ((number == 0) || (number == 1)) { // base cases\n"
                + "            return number;\n"
                + "        } \n"
                + "		else {\n"
                + "            // recursion step\n"
                + "            return fibonacci(number - 1) + fibonacci(number - 2);\n"
                + "        }\n"
                + "    }\n"
                + "public static void main(String args[]) {\n"
                + "        for (int count = 0; count <= 10; count++) {\n"
                + "            System.out.println(\"if Fibonacci of \" + count + \" is \"+ fibonacci(count));\n"
                + "        }\n"
                + "    }\n"
                + "}";

        Scanner fileInput = new Scanner(contentTestCnc);
        while (fileInput.hasNextLine()) {
            String scannedline = fileInput.nextLine();
            if (scannedline.matches("(\\s+)if(.*)") || scannedline.matches("(\\s+)else(.*)") || scannedline.matches("(\\s+)for(.*)")) {
                Testcurrent_max++;
                if (Testcurrent_max > TestCncCounter) {
                    TestCncCounter = Testcurrent_max;
                }
            } else if (scannedline.contains("}")) {
                if (Testcurrent_max > 0) {
                    Testcurrent_max--;
                    TestCncCounter = Testcurrent_max;
                }
            }
            ArrCnc.add(TestCncCounter);
        }
        return ArrCnc;
    }

    public void viewResult() {
        readFile();
        DefaultTableModel model = (DefaultTableModel) result.getModel();
        Object[] CtcObjs = Ctc.toArray();
        Object[] CncObjs = Cnc.toArray();
        Object[] programStatementObjs = programStatement.toArray();
        Object[] lineNumberObjs = lineNumber.toArray();
        Object[] lineComplexityObjs = lineComplexity.toArray();
        Object[] TwObjs = Tw.toArray();
        Object[] CpsObjs = Cps.toArray();
        Object[] CrObjs = Cr.toArray();
        Object[] CiObjs = Ci.toArray();

        model.addColumn("Line No", lineNumberObjs);
        model.addColumn("Program Statements", programStatementObjs);
        model.addColumn("Cs", lineComplexityObjs);
        model.addColumn("Ctc", CtcObjs);
        model.addColumn("Cnc", CncObjs);
        model.addColumn("Ci", CiObjs);
        model.addColumn("Tw", TwObjs);
        model.addColumn("Cps", CpsObjs);
        if (recursion == 1) {
            System.out.println("recursion is " + recursion);
            model.addColumn("Cr", CrObjs);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        btnAttchFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        uploadedContent = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        path = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnjava = new javax.swing.JRadioButton();
        btnc = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        noOfLines = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        testArrayList = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        result = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnCs = new javax.swing.JButton();
        btnCtc = new javax.swing.JButton();
        btnCnc = new javax.swing.JButton();
        btnCi3 = new javax.swing.JButton();
        btnCi = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        btnClear = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        CpValue = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("jMenu3");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        btnAttchFile.setBackground(new java.awt.Color(255, 255, 0));
        btnAttchFile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAttchFile.setText("Attach File");
        btnAttchFile.setBorder(null);
        btnAttchFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttchFileActionPerformed(evt);
            }
        });

        uploadedContent.setEditable(false);
        uploadedContent.setColumns(20);
        uploadedContent.setRows(5);
        jScrollPane1.setViewportView(uploadedContent);

        jLabel2.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel2.setText("Calculate Complexity");

        path.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Inserted Source Code :");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setText("Calculated Complexity :");

        btnjava.setBackground(new java.awt.Color(204, 255, 255));
        btnjava.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnjava.setText("JAVA");

        btnc.setBackground(new java.awt.Color(204, 255, 255));
        btnc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnc.setText("C++");

        jLabel4.setFont(new java.awt.Font("Segoe Print", 2, 36)); // NOI18N
        jLabel4.setText("ABC Solutions");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Choose Language : ");

        noOfLines.setEditable(false);

        btnReset.setBackground(new java.awt.Color(102, 102, 102));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("RESET");
        btnReset.setBorder(null);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        testArrayList.setBackground(new java.awt.Color(0, 204, 51));
        testArrayList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        testArrayList.setForeground(new java.awt.Color(255, 255, 255));
        testArrayList.setText("Display Output");
        testArrayList.setBorder(null);
        testArrayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testArrayListActionPerformed(evt);
            }
        });

        result.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(result);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        btnCs.setBackground(new java.awt.Color(0, 51, 204));
        btnCs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCs.setForeground(new java.awt.Color(255, 255, 255));
        btnCs.setText("Cs");
        btnCs.setBorder(null);
        btnCs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsActionPerformed(evt);
            }
        });

        btnCtc.setBackground(new java.awt.Color(0, 51, 204));
        btnCtc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCtc.setForeground(new java.awt.Color(255, 255, 255));
        btnCtc.setText("Ctc");
        btnCtc.setBorder(null);
        btnCtc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCtcActionPerformed(evt);
            }
        });

        btnCnc.setBackground(new java.awt.Color(0, 51, 204));
        btnCnc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCnc.setForeground(new java.awt.Color(255, 255, 255));
        btnCnc.setText("Cnc");
        btnCnc.setBorder(null);
        btnCnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCncActionPerformed(evt);
            }
        });

        btnCi3.setBackground(new java.awt.Color(0, 51, 204));
        btnCi3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCi3.setForeground(new java.awt.Color(255, 255, 255));
        btnCi3.setText("Cp");
        btnCi3.setBorder(null);
        btnCi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCi3ActionPerformed(evt);
            }
        });

        btnCi.setBackground(new java.awt.Color(0, 51, 204));
        btnCi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCi.setForeground(new java.awt.Color(255, 255, 255));
        btnCi.setText("Ci");
        btnCi.setBorder(null);
        btnCi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCtc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCnc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCi3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCtc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCnc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCi3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCs, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WordTest/logoABC.gif"))); // NOI18N

        btnClear.setBackground(new java.awt.Color(204, 255, 255));
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setText("Complexity of the program (Cp) :");

        CpValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CpValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(noOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnClear))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnjava)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnc)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAttchFile, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(testArrayList, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 238, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(logo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CpValue, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAttchFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnjava)
                    .addComponent(btnc)
                    .addComponent(jLabel5)
                    .addComponent(testArrayList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(noOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CpValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAttchFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttchFileActionPerformed

        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter docFilter = new FileNameExtensionFilter("Word Files", "docx");
            chooser.setFileFilter(docFilter);
            int value = chooser.showOpenDialog(null);

            if (value == JFileChooser.APPROVE_OPTION) {
                XWPFDocument doc = new XWPFDocument(new FileInputStream(chooser.getSelectedFile()));
                XWPFWordExtractor extract = new XWPFWordExtractor(doc);
                uploadedContent.setText(extract.getText());
                File f = chooser.getSelectedFile();
                String fileName = f.getAbsolutePath();
                path.setText(fileName);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_btnAttchFileActionPerformed

    private void btnCtcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCtcActionPerformed

        calculateCtc();

    }//GEN-LAST:event_btnCtcActionPerformed

    private void btnCncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCncActionPerformed

        calculateCnc();

    }//GEN-LAST:event_btnCncActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        path.setText(null);
        noOfLines.setText(null);
        uploadedContent.setText(null);
        DefaultTableModel ClearModel = (DefaultTableModel) result.getModel();
        ClearModel.setRowCount(0);
        btnClear.setSelected(true);
        Cps.clear();
        RecursiveCps.clear();
        Tw.clear();
        Cr.clear();


        /*
         calcCnc();
         for (int i = 0; i < ArrCnc.size(); i++) {
         System.out.println(i + 1 + " : " + ArrCnc.get(i).toString());
         }
         */

    }//GEN-LAST:event_btnResetActionPerformed

    private void testArrayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testArrayListActionPerformed

        viewResult();

    }//GEN-LAST:event_testArrayListActionPerformed

    private void btnCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsActionPerformed
        calcCs();
    }//GEN-LAST:event_btnCsActionPerformed

    private void btnCiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCiActionPerformed

    private void btnCi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCi3ActionPerformed
        // TODO add your handling code here:
        CalcCp();
    }//GEN-LAST:event_btnCi3ActionPerformed

    private void CpValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CpValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CpValueActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                /*
                 if ("Nimbus".equals(info.getName())) {
                 javax.swing.UIManager.setLookAndFeel(info.getClassName());
                 break;
                 }
                 */
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CpValue;
    private javax.swing.JButton btnAttchFile;
    private javax.swing.JButton btnCi;
    private javax.swing.JButton btnCi3;
    private javax.swing.JRadioButton btnClear;
    private javax.swing.JButton btnCnc;
    private javax.swing.JButton btnCs;
    private javax.swing.JButton btnCtc;
    private javax.swing.JButton btnReset;
    private javax.swing.JRadioButton btnc;
    private javax.swing.JRadioButton btnjava;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField noOfLines;
    private javax.swing.JTextField path;
    private javax.swing.JTable result;
    private javax.swing.JButton testArrayList;
    private javax.swing.JTextArea uploadedContent;
    // End of variables declaration//GEN-END:variables
}
