package org.spm.we_25.complexity_measure_tool;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import static org.junit.Assert.assertEquals;

public class MeasureComplexity extends javax.swing.JFrame implements Serializable {

    private ArrayList<Integer> ctc = new ArrayList<>();
    private ArrayList<Integer> cnc = new ArrayList<>();
    private ArrayList<Integer> cs = new ArrayList<>();
    private ArrayList<String> programStatement = new ArrayList<>();
    private ArrayList<Integer> lineComplexity = new ArrayList<>();
    private ArrayList<Integer> arrCtc = new ArrayList<>();
    private ArrayList<Integer> arrCnc = new ArrayList<>();
    private ArrayList<Integer> cps = new ArrayList<>();
    private ArrayList<Integer> recursiveCps = new ArrayList<>();
    private ArrayList<Integer> tw = new ArrayList<>();
    private ArrayList<Integer> cr = new ArrayList<>();
    private ArrayList<Integer> ci = new ArrayList<>();

    private int totlComplexity = 0;
    private int recursion = 0;
    private int maxCases = 0;
    private int testMaxCases = 0;
    private int complexity = 0;
    private int ctcCounter = 0;
    private int ctcCounterTest = 0;
    private String stringcatch = "catch";
    private String stringfloat = "float";
    private String stringwhile = "while";
    private String stringlinebreak = "\r\n|\r|\n";
    private String stringpublic = "public";
    private String stringsystem = "System";
    private String stringstatic = "static";
    private String stringreturn = "return";
    private String stringthrow = "throw";
    private String stringineNumber = "Line Number";
    private String strigswitchmatch = "(\\s+)switch(.*)";
    private String stringspacematch = "(\\s+)\\(.*)";
    private String stringspacematch2 = "(\\s+)//(.*)";
    private String stringspacematch3 = "(\\s+)/*(.*)";
    private String stringspacematch4 = "(\\s+)*/(.*)";

    private String testContentComplexity = "public class FibonacciMain {\n"
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
            + "            System.out.println(\"Fibonacci of \" + count + \" is \"+ fibonacci(count));\n"
            + "        }\n"
            + "    }\n"
            + "}";

    private static Logger logger = Logger.getLogger(MeasureComplexity.class.getName());

    public MeasureComplexity() {
        initComponents();
        setExtendedState(MeasureComplexity.MAXIMIZED_BOTH);
        ButtonGroup group = new ButtonGroup();
        group.add(btnjava);
        group.add(btnc);
        group.add(btnClear);
        btnClear.setVisible(false);

    }

    public List<String> readFile() {

        int lineNo = 0;
        Scanner fileInput = new Scanner(uploadedContent.getText());
        try {
            while (fileInput.hasNextLine()) {
                lineNo = lineNo + 1;
                String scannedline = fileInput.nextLine();
                programStatement.add(scannedline);
                cs.add(lineNo);
            }

        } catch (Exception e) {
            LogRecord ex = new LogRecord(Level.INFO, String.valueOf(e));
            logger.log(ex);
        } finally {
            fileInput.close();
        }
        return programStatement;
    }

    public void viewResult() {
        readFile();
        DefaultTableModel model = (DefaultTableModel) result.getModel();
        Object[] ctcObjs = ctc.toArray();
        Object[] cncObjs = cnc.toArray();
        Object[] programStatementObjs = programStatement.toArray();
        Object[] lineNumberObjs = cs.toArray();
        Object[] lineComplexityObjs = lineComplexity.toArray();
        Object[] twObjs = tw.toArray();
        Object[] cpsObjs = cps.toArray();
        Object[] crObjs = cr.toArray();
        Object[] ciObjs = ci.toArray();

        model.addColumn("Line No", lineNumberObjs);
        model.addColumn("Program Statements", programStatementObjs);
        model.addColumn("Cs", lineComplexityObjs);
        model.addColumn("Ctc", ctcObjs);
        model.addColumn("Cnc", cncObjs);
        model.addColumn("Ci", ciObjs);
        model.addColumn("Tw", twObjs);
        model.addColumn("Cps", cpsObjs);
        if (recursion == 1) {

            model.addColumn("Cr", crObjs);
        }

    }

    public List<Integer> inheritanceCi() {

        String fileInput = uploadedContent.getText();

        String[] keywords = {"abstract", "assert", "boolean", "break", "byte", "case", stringcatch, "char",
            "const", "continue", "default", "do", "double", "enum", "final",
            "finally", stringfloat, "for", "goto", "if", "instanceof", "int", "interface",
            "native", "new", "package", "short", "strictfp", "super",
            "switch", "synchronized", "this", "transient",
            "volatile", stringwhile, "FileReader"};

        String[] line = fileInput.split(stringlinebreak);

        for (int i = 0; i < line.length; i++) {

            String withoutspace = line[i].trim();
            if (withoutspace.startsWith("/") || withoutspace.startsWith("*")) {
                this.complexity = complexity;

            }
            if (withoutspace.startsWith("import")) {

                this.complexity = complexity;

            }

            if (withoutspace.startsWith("try") || withoutspace.startsWith("else")) {

                complexity = 0;

            } else {
                String[] words = line[i].split("\\s+");
                int r = 0;
                while (r < words.length) {

                    if (words[r].contains(stringpublic) && words[r + 1].contains("class")) {
                        complexity = complexity + 2;

                        if (words[r].contains("extends") || words[r].contains("implements")) {
                            if (words[i].contains(",")) {
                                complexity = complexity + 1;

                            }
                            complexity = complexity + 1;

                        }

                    }

                    if (words[r].contains("}")) {

                        this.complexity = complexity;

                    }
                    if (words[r].contains(stringsystem)) {
                        complexity = complexity + 2;

                    }
                    if (words[r].equals(stringstatic) && words[r + 1].equals("void")) {
                        complexity = complexity + 2;

                    }

                    if (words[r].contains(stringreturn)) {
                        complexity = complexity + 2;

                    }
                    if (words[r].contains(stringthrow) && words[r + 1].startsWith("e") && words[r + 1].endsWith(";")) {

                        complexity = complexity + 2;

                        if (words[r].contains("//")) {
                            complexity = complexity + 2;

                        }
                    }
                    int u = 0;
                    while (u < keywords.length) {

                        if (words[r].equals(keywords[u])) {
                            complexity = complexity + 2;
                            r++;

                        }
                        u++;
                    }
                    r++;
                }

            }
            ci.add(complexity);
            complexity = 0;
        }

        return ci;
    }

    public void generateCtcChart() {

        String ctcComplexity = "Ctc Complexity";
        String ctcStatistics = "Ctc Statistics";
        String lineNumber = stringineNumber;

        DefaultCategoryDataset pieDataset = new DefaultCategoryDataset();

        for (int i = 0; i < ctc.size(); i++) {
            pieDataset.setValue(ctc.get(i), ctcComplexity, String.valueOf(i + 1));

        }

        JFreeChart chart = ChartFactory.createBarChart(ctcStatistics, lineNumber, ctcComplexity, pieDataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame(ctcStatistics, chart);
        frame.setVisible(true);
        frame.setSize(450, 350);

    }

    public void generateCncChart() {

        String cncComplexity = "Cnc Complexity";
        String cncStatistics = "Cnc Statistics";
        String lineNumber = stringineNumber;

        DefaultCategoryDataset pieDataset = new DefaultCategoryDataset();

        for (int i = 0; i < cnc.size(); i++) {
            pieDataset.setValue(cnc.get(i), cncComplexity, String.valueOf(i + 1));

        }

        JFreeChart chart = ChartFactory.createBarChart(cncStatistics, lineNumber, cncComplexity, pieDataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame(cncStatistics, chart);
        frame.setVisible(true);
        frame.setSize(450, 350);

    }

    public void generateCsChart() {

        String csComplexity = "Cs Complexity";
        String cscStatistics = "Csc Statistics";
        String lineNumber = stringineNumber;

        DefaultCategoryDataset pieDataset = new DefaultCategoryDataset();

        for (int i = 0; i < lineComplexity.size(); i++) {
            pieDataset.setValue(lineComplexity.get(i), csComplexity, String.valueOf(i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(cscStatistics, lineNumber, csComplexity, pieDataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame(cscStatistics, chart);
        frame.setVisible(true);
        frame.setSize(450, 350);

    }

    public void generateCiChart() {

        String ciComplexity = "Ci Complexity";
        String ciStatistics = "Ci Statistics";
        String lineNumber = stringineNumber;

        DefaultCategoryDataset pieDataset = new DefaultCategoryDataset();

        for (int i = 0; i < ci.size(); i++) {
            pieDataset.setValue(ci.get(i), ciComplexity, String.valueOf(i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(ciStatistics, lineNumber, ciComplexity, pieDataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame(ciStatistics, chart);
        frame.setVisible(true);
        frame.setSize(450, 350);

    }

    public void generateTwChart() {

        String twComplexity = "TW Complexity";
        String twStatistics = "TW Statistics";
        String lineNumber = stringineNumber;

        DefaultCategoryDataset pieDataset = new DefaultCategoryDataset();

        for (int i = 0; i < tw.size(); i++) {
            pieDataset.setValue(tw.get(i), twComplexity, String.valueOf(i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(twStatistics, lineNumber, twComplexity, pieDataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame(twStatistics, chart);
        frame.setVisible(true);
        frame.setSize(450, 350);

    }

    public void generateCrChart() {

        String crComplexity = "Cr Complexity";
        String crStatistics = "Cr Statistics";
        String lineNumber = stringineNumber;

        DefaultCategoryDataset pieDataset = new DefaultCategoryDataset();

        for (int i = 0; i < cr.size(); i++) {
            pieDataset.setValue(cr.get(i), crComplexity, String.valueOf(i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(crStatistics, lineNumber, crComplexity, pieDataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame(crStatistics, chart);
        frame.setVisible(true);
        frame.setSize(450, 350);

    }

    public int getSwitchCaseCount() {
        maxCases = 0;

        int currentmaxCases = 0;
        Scanner fileInput = new Scanner(uploadedContent.getText());

        try {

            while (fileInput.hasNextLine()) {
                String scannedline = fileInput.nextLine();

                if (scannedline.matches("(\\s+)case(.*)")) {
                    currentmaxCases++;

                    if (currentmaxCases > maxCases) {
                        maxCases = currentmaxCases;
                    } else if (currentmaxCases > 0) {
                        currentmaxCases--;

                    }
                }

            }
        } catch (Exception e) {
            LogRecord ex = new LogRecord(Level.INFO, String.valueOf(e));
            logger.log(ex);
        } finally {
            fileInput.close();
        }
        return maxCases;

    }

    public boolean checkAvailability(String text, String checkString) {
        boolean status = false;
        if (text.contains(checkString)) {
            status = true;
        }
        return status;

    }

    public List<Integer> calculateCtc(String uploadedText) {

        maxCases = 0;

        int lineNumber = 0;

        Scanner fileInput = new Scanner(uploadedText);
        try {

            while (fileInput.hasNextLine()) {

                lineNumber = lineNumber + 1;

                String scannedline = fileInput.nextLine();

                //******************************IF uploded source code in JAVA language
                if (btnjava.isSelected()) {
                    if (scannedline.startsWith("System.out.println") || scannedline.startsWith(stringspacematch) || scannedline.startsWith(stringspacematch2) || (scannedline.startsWith(stringspacematch3)) || scannedline.startsWith(stringspacematch4)) {
                        ctcCounter = 0;

                    }

                } //******************************IF uploded source code in C++ language
                else if (btnc.isSelected()) {

                    if (scannedline.contains("cout <<") || scannedline.startsWith(stringspacematch) || scannedline.startsWith(stringspacematch2) || (scannedline.startsWith(stringspacematch3)) || scannedline.startsWith(stringspacematch4)) {

                        ctcCounter = 0;

                    }
                }

                if (this.checkAvailability(scannedline, "if")) {

                    ctcCounter = ctcCounter + 1;

                    if (this.checkAvailability(scannedline, "||") || this.checkAvailability(scannedline, "|") || this.checkAvailability(scannedline, "&&") || this.checkAvailability(scannedline, "&")) {

                        ctcCounter = ctcCounter + 1;

                    }

                }
                if (this.checkAvailability(scannedline, "for")) {

                    ctcCounter = ctcCounter + 2;

                    if (this.checkAvailability(scannedline, "||") || this.checkAvailability(scannedline, "|") || this.checkAvailability(scannedline, "&&") || this.checkAvailability(scannedline, "&")) {
                        ctcCounter = ctcCounter + 2;
                    }

                }
                if (this.checkAvailability(scannedline, stringwhile)) {

                    ctcCounter = ctcCounter + 2;

                    if (this.checkAvailability(scannedline, "||") || this.checkAvailability(scannedline, "|") || this.checkAvailability(scannedline, "&&") || this.checkAvailability(scannedline, "&")) {
                        ctcCounter = ctcCounter + 2;
                    }

                }
                if (this.checkAvailability(scannedline, "do")) {

                    ctcCounter = ctcCounter + 2;

                    if (this.checkAvailability(scannedline, "||") || this.checkAvailability(scannedline, "|") || this.checkAvailability(scannedline, "&&") || this.checkAvailability(scannedline, "&")) {
                        ctcCounter = ctcCounter + 2;
                    }

                }
                if (this.checkAvailability(scannedline, stringcatch)) {
                    ctcCounter = ctcCounter + 1;
                }
                if (this.checkAvailability(scannedline, strigswitchmatch)) {
                    getSwitchCaseCount();
                    ctcCounter = maxCases;
                }

                ctc.add(ctcCounter);

                ctcCounter = 0;
                maxCases = 0;

            }

            maxCases = 0;
            noOfLines.setText("No of Lines : " + lineNumber);

        } catch (Exception e) {
            LogRecord ex = new LogRecord(Level.INFO, String.valueOf(e));
            logger.log(ex);
        } finally {
            fileInput.close();
        }

        return ctc;

    }

    public List<Integer> calCtc() {

        calculateCtc(testContentComplexity);

        return ctc;
    }

    public List<Integer> calculateCnc(String uploadedText) {

        int currentmax = 0;
        int additonal = 0;
        int cncCounter = 0;
        int lineNumber = 0;
        Scanner fileInput = new Scanner(uploadedText);

        try {

            while (fileInput.hasNextLine()) {
                lineNumber = lineNumber + 1;
                String scannedline = fileInput.nextLine();

                if (scannedline.contains("//") || scannedline.contains("/*") || scannedline.contains("*/")) {
                    cncCounter = 0;

                }

                String[] scannedlineArr = scannedline.split(" ");

                for (int i = 0; i < scannedlineArr.length; i++) {

                    if (scannedlineArr[i].equals("if") || scannedlineArr[i].equals("else") || scannedlineArr[i].equals("for") || scannedlineArr[i].equals(stringwhile)) {

                        currentmax++;

                        if (scannedline.contains("{") && scannedline.contains("}")) {

                            currentmax++;
                            additonal++;

                        }

                        if (currentmax > cncCounter) {

                            cncCounter = currentmax;

                        }

                    } else if (scannedlineArr[i].equals("}") && currentmax > 0) {

                        currentmax--;
                        cncCounter = currentmax;
                        currentmax = currentmax - additonal;
                        additonal = 0;

                    }
                }

                cnc.add(cncCounter);

            }

            additonal = 0;
            noOfLines.setText("No of Lines : " + lineNumber);

        } catch (Exception e) {
            LogRecord ex = new LogRecord(Level.INFO, String.valueOf(e));
            logger.log(ex);
        } finally {
            fileInput.close();
        }

        return cnc;
    }

    public List<Integer> calcCnc() {

        calculateCnc(testContentComplexity);

        return cnc;
    }

    public List<Integer> calculateCs() {

        calcCs(testContentComplexity);
        return (lineComplexity);

    }

    public List<Integer> calcCs(String uploadedText) {

        String fileInput = uploadedText;

        ArrayList<String> variableList = new ArrayList<>();

        int countComplexity = 0;
        String[] operatorsCs1 = {
            "+", "-", "*", "/", "%", "++", "--",
            "==", "!=", ">", "<", ">=", "<=",
            "&&", "||", "!",
            "|", "^", "~", "<<", ">>", ">>>", "<<<",
            ",", "->", ".", "::",
            "+=", "-=", "*=", "/=", "=", ">>>=", "|=", "&=", "%=", "<<=", ">>=", "^="
        };
        String[] cPlusPlusKeywords = {
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
        String[] manipulators = {"endl", "\n"};

        String[] dataTypesToFindVariables = {
            "int", "Integer", "long", stringfloat, "String", "void"
        };

        String[] allExceptions = {
            "ArithmeticException", "ArrayIndexOutOfBoundsException", "ClassNotFoundException", "FileNotFoundException", "IOException",
            "InterruptedException", "NoSuchFieldException", "NoSuchMethodException", "NullPointerException", "NumberFormatException",
            "RuntimeException", "StringIndexOutOfBoundsException"
        };

        String[] line = fileInput.split(stringlinebreak);

        for (int v = 0; v < line.length; v++) {

            String[] wordsForVariables = line[v].split(" ");
            for (int r = 0; r < wordsForVariables.length; r++) {
                for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {
                    if (wordsForVariables[r].equals(dataTypesToFindVariables[dataType])) {
                        String idVariable = wordsForVariables[r + 1];
                        if (!idVariable.contains("(")) {
                            variableList.add(idVariable);
                        }
                    }
                }
                if (wordsForVariables[r].contains("(")) {

                    int indexOfBrack = wordsForVariables[r].indexOf('(');
                    int fistIndexAfterBrack = indexOfBrack + 1;
                    String variableName = wordsForVariables[r].substring(fistIndexAfterBrack);

                    for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {
                        if (variableName.equals(dataTypesToFindVariables[dataType])) {
                            String variable = wordsForVariables[r + 1];
                            if (!variable.contains("[")) {

                                if (variable.endsWith(")")) {
                                    int index1 = variable.lastIndexOf(')');
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
            String newString = line[i].trim();
            if (newString.startsWith("/") || newString.startsWith("*")) {
                countComplexity = 0;
            } else {
                String[] words = line[i].split(" ");
                for (int r = 0; r < words.length; r++) {

                    if (SourceVersion.isKeyword(words[r])) {
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].equals(stringpublic) || words[r].equals(stringstatic) || words[r].equals("else") || words[r].equals("try") || words[r].equals(stringreturn)) {
                        countComplexity = countComplexity - 1;
                    }
                    if (words[r].equals("new") || words[r].equals("delete") || words[r].equals(stringthrow) || words[r].equals("throws")) {
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].contains(stringsystem) && words[r].contains("out")) {
                        countComplexity = countComplexity + 2;
                    }
                    if (words[r].contains(".")) {
                        char[] chWords = words[r].toCharArray();
                        for (int m = 0; m < chWords.length; m++) {
                            if (chWords[m] == '.') {
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].startsWith("&") || words[r].startsWith("*")) {
                        char[] ch = words[r].toCharArray();
                        if (ch.length > 1) {
                            countComplexity = countComplexity + 2;
                        }
                    }
                    if (words[r].matches(".*\\d.*")) {
                        countComplexity = countComplexity + 1;
                    }
                    if (words[r].contains("(")) {
                        int indexOfBrack = words[r].indexOf('(');
                        String mn = words[r].substring(0, indexOfBrack);
                        if (mn != null && !mn.isEmpty()) {
                            countComplexity = countComplexity + 1;
                        }

                        int indexOfBracket = words[r].indexOf('(');
                        int indexOfQuote = indexOfBracket + 2;
                        String wordWithQuote = words[r].substring(0, indexOfQuote);

                        if (wordWithQuote.contains("\"")) {
                            countComplexity = countComplexity + 1;
                        }
                        int fistIndexAfterBracket = indexOfBracket + 1;
                        String dataTypeName = words[r].substring(fistIndexAfterBracket);
                        for (int dataType = 0; dataType < dataTypesToFindVariables.length; dataType++) {
                            if (dataTypeName.contains(dataTypesToFindVariables[dataType])) {
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].startsWith("\"") && !words[r + 1].equals("+")) {

                        countComplexity = countComplexity + 1;

                    }
                    if (words[r].contains("[")) {
                        countComplexity = countComplexity + 1;
                    }
                    for (int cp = 0; cp < cPlusPlusKeywords.length; cp++) {
                        if (words[r].equals(cPlusPlusKeywords[cp])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    for (int opCs1 = 0; opCs1 < operatorsCs1.length; opCs1++) {
                        if (words[r].equals(operatorsCs1[opCs1])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].contains("++") || words[r].contains("--")) {
                        countComplexity = countComplexity + 1;
                    }
                    for (int ex = 0; ex < allExceptions.length; ex++) {
                        if (words[r].contains(allExceptions[ex])) {
                            countComplexity = countComplexity + 1;
                            if (words[r].startsWith(stringcatch) || words[r - 1].equals(stringcatch)) {
                                countComplexity = countComplexity + 1;
                            }
                        }
                    }
                    if (words[r].equals(stringthrow) && words[r + 1].startsWith("e") && words[r + 1].endsWith("e;")) {
                        countComplexity = countComplexity + 1;

                    }
                    if (words[r].contains("e.")) {
                        countComplexity = countComplexity + 2;
                    }
                    if (words[r].startsWith(stringcatch) && words[r].endsWith(")")) {
                        countComplexity = countComplexity + 1;
                    }
                    for (int manip = 0; manip < manipulators.length; manip++) {
                        if (words[r].equals(manipulators[manip])) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    for (int vl = 0; vl < variableList.size(); vl++) {
                        String var = variableList.get(vl);
                        if (words[r].contains(var)) {
                            countComplexity = countComplexity + 1;
                        }
                    }
                    if (words[r].equals("new")) {
                        countComplexity = countComplexity + 1;
                    }

                }
            }
            lineComplexity.add(countComplexity);
            countComplexity = 0;
        }

        return (lineComplexity);

    }
//*** for cp start ************************************************************************

    public String findRecursiveMethod() {
        try {

            String sourceCode = uploadedContent.getText();
            String[] liness = sourceCode.split(stringlinebreak);
            for (int i = 0; i < liness.length; i++) {
                if (liness[i].contains(stringreturn)) {
                    String[] words = liness[i].split(" ");
                    for (int r = 0; r < words.length; r++) {
                        if (words[r].contains("(")) {
                            int index = words[r].indexOf('(');
                            String methodName = words[r].substring(0, index);

                            return methodName + " " + i;
                        }
                    }

                }

            }

        } catch (Exception e) {
            LogRecord ex = new LogRecord(Level.INFO, String.valueOf(e));
            logger.log(ex);
        }
        return " ";
    }

    public int calculateCpForNonRecursive() {
        try {

            List<Integer> ctcCal = calculateCtc(uploadedContent.getText());

            List<Integer> cncCal = calculateCnc(uploadedContent.getText());
            for (int i = 0; i < ctc.size(); i++) {
                int ctcVal = ctcCal.get(i);
                int cncVal = cncCal.get(i);
                int ciVal = ci.get(i);
                int twVal = ctcVal + cncVal + ciVal;
                tw.add(twVal);
            }

            for (int i = 0; i < tw.size(); i++) {
                int pcps = tw.get(i) * cs.get(i);
                this.cps.add(pcps);
            }

            for (int i = 0; i < cps.size(); i++) {
                totlComplexity = totlComplexity + cps.get(i);
            }

            return totlComplexity;
        } catch (Exception e) {
            LogRecord ex = new LogRecord(Level.INFO, String.valueOf(e));
            logger.log(ex);
        }
        return 0;

    }

    public int findRecursiveFirstLine(String mname) {
        String sourceCode = uploadedContent.getText();
        String[] liness = sourceCode.split(stringlinebreak);
        String methodName = mname + "(";
        for (int i = 0; i < liness.length; i++) {
            if (liness[i].contains(methodName)) {
                return i;
            }
        }
        return -1;
    }

    public int calculateTotalCpsForRecursive(int firstLine, int lastLine) {
        List<Integer> cncCal = calculateCnc(uploadedContent.getText());
        List<Integer> ctcCal = calculateCtc(uploadedContent.getText());
        List<Integer> pci = inheritanceCi();
        List<Integer> pcs = calcCs(uploadedContent.getText());

        String sourceCode = uploadedContent.getText();
        String[] liness = sourceCode.split(stringlinebreak);

        for (int i = 0; i < liness.length; i++) {

            int ptw = ctcCal.get(i) + cncCal.get(i) + pci.get(i);
            this.tw.add(ptw);

        }

        for (int i = 0; i < liness.length; i++) {

            int pcps = tw.get(i) * pcs.get(i);

            this.cps.add(pcps);
            recursiveCps.add(pcps);

        }

        int totalCps = 0;
        for (int i = 0; i < cps.size(); i++) {
            if (i < firstLine || i > lastLine) {

                totalCps = totalCps + cps.get(i);
            }

        }
        return totalCps;
    }

    public int calculateTotalCrForRecursive(int firstLine, int lastLine) {

        for (int i = 0; i < recursiveCps.size(); i++) {
            if (i < firstLine || i > lastLine) {
                cr.add(0);
            } else {
                int pcr = 2 * recursiveCps.get(i);
                this.cr.add(pcr);
            }
        }

        int totalCr = 0;
        for (int i = 0; i < cr.size(); i++) {
            totalCr = totalCr + cr.get(i);
        }
        return totalCr;
    }

    public List<Integer> dumyCi() {
        int[] data = {0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0};

        for (int i = 0; i < data.length; i++) {
            ci.add(data[i]);

        }
        return ci;
    }

    public List<Integer> dumyNonCi() {
        int[] data = {0, 0, 2, 0, 2, 2, 0, 2, 2, 2, 0, 0, 2, 0, 2, 0, 2, 2, 0, 0, 0};

        for (int i = 0; i < data.length; i++) {
            ci.add(data[i]);
        }
        return ci;
    }
//*** for cp ends ************************************************************************    

    public void calcCp() {
        try {

            String rec = findRecursiveMethod();
            if (rec.equals(" ")) {
                recursion = 0;
                cr.clear();

                totlComplexity = calculateCpForNonRecursive();

                cpValue.setText(Integer.toString(totlComplexity));
            } else {

                recursion = 1;
                String[] presult = rec.split(" ");
                String methodName = presult[0];
                int lastLine = Integer.parseInt(presult[1]);

                int firstLine = findRecursiveFirstLine(methodName);
                if (firstLine == -1) {
                    LogRecord wrongline = new LogRecord(Level.INFO, "wrong line");
                    logger.log(wrongline);

                } else {

                    int totalCps = calculateTotalCpsForRecursive(firstLine, lastLine);

                    int totalCr = calculateTotalCrForRecursive(firstLine, lastLine);

                    int cp = totalCr + totalCps;
                    cpValue.setText(Integer.toString(cp));

                }

            }

        } catch (Exception e) {
            LogRecord ex = new LogRecord(Level.INFO, String.valueOf(e));
            logger.log(ex);
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
        cpValue = new javax.swing.JTextField();
        btnCtcReport = new javax.swing.JButton();
        btnCncReport = new javax.swing.JButton();
        btnCsReport = new javax.swing.JButton();
        btnCiReport = new javax.swing.JButton();
        btnTWReport = new javax.swing.JButton();
        btnCrReport = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("jMenu3");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        btnAttchFile.setBackground(new java.awt.Color(255, 204, 51));
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

        btnReset.setBackground(new java.awt.Color(153, 153, 153));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReset.setText("RESET");
        btnReset.setBorder(null);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        testArrayList.setBackground(new java.awt.Color(0, 204, 51));
        testArrayList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/spm/we_25/complexity_measure_tool/logoABC.gif"))); // NOI18N

        btnClear.setBackground(new java.awt.Color(204, 255, 255));
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setText("Complexity of the program (Cp) :");

        cpValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpValueActionPerformed(evt);
            }
        });

        btnCtcReport.setBackground(new java.awt.Color(255, 51, 51));
        btnCtcReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCtcReport.setForeground(new java.awt.Color(255, 255, 255));
        btnCtcReport.setText("Ctc Statistics");
        btnCtcReport.setBorder(null);
        btnCtcReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCtcReportActionPerformed(evt);
            }
        });

        btnCncReport.setBackground(new java.awt.Color(255, 51, 51));
        btnCncReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCncReport.setForeground(new java.awt.Color(255, 255, 255));
        btnCncReport.setText("Cnc Statistics");
        btnCncReport.setBorder(null);
        btnCncReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCncReportActionPerformed(evt);
            }
        });

        btnCsReport.setBackground(new java.awt.Color(255, 51, 51));
        btnCsReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCsReport.setForeground(new java.awt.Color(255, 255, 255));
        btnCsReport.setText("Cs Statistics");
        btnCsReport.setBorder(null);
        btnCsReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsReportActionPerformed(evt);
            }
        });

        btnCiReport.setBackground(new java.awt.Color(255, 51, 51));
        btnCiReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCiReport.setForeground(new java.awt.Color(255, 255, 255));
        btnCiReport.setText("Ci Statistics");
        btnCiReport.setBorder(null);
        btnCiReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCiReportActionPerformed(evt);
            }
        });

        btnTWReport.setBackground(new java.awt.Color(255, 51, 51));
        btnTWReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTWReport.setForeground(new java.awt.Color(255, 255, 255));
        btnTWReport.setText("TW Statistics");
        btnTWReport.setBorder(null);
        btnTWReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTWReportActionPerformed(evt);
            }
        });

        btnCrReport.setBackground(new java.awt.Color(255, 51, 51));
        btnCrReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCrReport.setForeground(new java.awt.Color(255, 255, 255));
        btnCrReport.setText("Cr Statistics");
        btnCrReport.setBorder(null);
        btnCrReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrReportActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel7.setText("Generate Charts");

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
                                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(113, 113, 113)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCtcReport)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCncReport)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCsReport, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCiReport, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTWReport, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCrReport, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 147, Short.MAX_VALUE))
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
                                .addComponent(cpValue, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(noOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCncReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCsReport)
                            .addComponent(btnCiReport)
                            .addComponent(btnTWReport)
                            .addComponent(btnCrReport)
                            .addComponent(jLabel7)))
                    .addComponent(btnCtcReport, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cpValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        calculateCtc(uploadedContent.getText());

    }//GEN-LAST:event_btnCtcActionPerformed

    private void btnCncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCncActionPerformed

        calculateCnc(uploadedContent.getText());

    }//GEN-LAST:event_btnCncActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        this.dispose();
        MeasureComplexity ui = new MeasureComplexity();
        ui.setVisible(true);


    }//GEN-LAST:event_btnResetActionPerformed

    private void testArrayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testArrayListActionPerformed

        viewResult();

    }//GEN-LAST:event_testArrayListActionPerformed

    private void btnCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsActionPerformed
        calcCs(uploadedContent.getText());
    }//GEN-LAST:event_btnCsActionPerformed

    private void btnCiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCiActionPerformed

        inheritanceCi();
    }//GEN-LAST:event_btnCiActionPerformed

    private void btnCi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCi3ActionPerformed

        calcCp();
    }//GEN-LAST:event_btnCi3ActionPerformed

    private void cpValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpValueActionPerformed

    }//GEN-LAST:event_cpValueActionPerformed

    private void btnCtcReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCtcReportActionPerformed
        generateCtcChart();
    }//GEN-LAST:event_btnCtcReportActionPerformed

    private void btnCncReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCncReportActionPerformed
        generateCncChart();
    }//GEN-LAST:event_btnCncReportActionPerformed

    private void btnCsReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsReportActionPerformed
        generateCsChart();
    }//GEN-LAST:event_btnCsReportActionPerformed

    private void btnCiReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCiReportActionPerformed
        generateCiChart();
    }//GEN-LAST:event_btnCiReportActionPerformed

    private void btnTWReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTWReportActionPerformed
        generateTwChart();
    }//GEN-LAST:event_btnTWReportActionPerformed

    private void btnCrReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrReportActionPerformed
        generateCrChart();
    }//GEN-LAST:event_btnCrReportActionPerformed

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MeasureComplexity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MeasureComplexity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MeasureComplexity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MeasureComplexity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MeasureComplexity().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttchFile;
    private javax.swing.JButton btnCi;
    private javax.swing.JButton btnCi3;
    private javax.swing.JButton btnCiReport;
    private javax.swing.JRadioButton btnClear;
    private javax.swing.JButton btnCnc;
    private javax.swing.JButton btnCncReport;
    private javax.swing.JButton btnCrReport;
    private javax.swing.JButton btnCs;
    private javax.swing.JButton btnCsReport;
    private javax.swing.JButton btnCtc;
    private javax.swing.JButton btnCtcReport;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTWReport;
    private javax.swing.JRadioButton btnc;
    private javax.swing.JRadioButton btnjava;
    private javax.swing.JTextField cpValue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
