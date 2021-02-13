package com.javarush.task.task34.task3404;

/*
Рекурсия для мат. выражения
*/

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recurse(final String expression, int countOperation) {
        String resultExpression, countExpression;
        int count = 0;
        boolean numberInBrackets = false;
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(2);
        String expressionTrim = expression.replaceAll("\\s", "");

        // проверяем, является ли выражение числом и выводим результат
        if (expressionTrim.matches("^-?\\d+(\\.\\d+)?")) {
            if (expressionTrim.equals("-0")) {
                countOperation++;
                System.out.printf("%s %d%n", 0, countOperation);
            } else {
                System.out.printf("%s %d%n", nf.format(Double.parseDouble(expressionTrim)), countOperation);
            }
            return;
        }
//        в случае, если выражение простое без скобок и тригонометрии
        else if (!expressionTrim.contains("(") && expressionTrim.matches("[^a-z]+")
                && !expressionTrim.matches("^-?\\d+(\\.\\d+)?")) {
            resultExpression = "" + calculateOperations(expressionTrim);
            if (expressionTrim.startsWith("--")) countOperation--;
            countExpression = expressionTrim;
        }
//       находим все выражения с тригонометрией и градусами в скобках с одним или двумя цифрами в скобках
        else if (expressionTrim.matches(".*[a-z]{3}+\\(-?[.0-9]*[*+\\-/^.0-9]+\\).*")) {
            Pattern pattern = Pattern.compile("[a-z]{3}+\\(-?[.0-9]*[*+\\-/^.0-9]+\\)");
            Matcher matcher = pattern.matcher(expressionTrim);
            String sqrExp = null;
            if (matcher.find()) {
                sqrExp = matcher.group();
            }
            String partOne = expressionTrim.substring(0, expressionTrim.indexOf(sqrExp.charAt(0)));
            String partTwo = expressionTrim.substring(partOne.length() + sqrExp.length());
            String middle;
            if (sqrExp.matches("[a-z]{3}+\\(.*[*+\\-/^]+.*\\)")) {
                double result = calculateOperations(sqrExp.substring(sqrExp.indexOf("(") + 1, sqrExp.indexOf(")")));
                middle = sqrExp.substring(0, sqrExp.indexOf("(") + 1) + result + ")";
            } else {
                middle = sqrExp;
            }
            String function = middle.substring(0, 3);
            double degree = Double.parseDouble(middle.substring(4, middle.indexOf(")")));
            double result;
            switch (function) {
                case "sin":
                    result = Math.sin(Math.toRadians(degree));
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(degree));
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(degree));
                    break;
                default:
                    result = 0.0;
            }
            String resultExp = "" + result;

            String newExpression;
            if (resultExp.length() > 20) {
                BigDecimal value = new BigDecimal(resultExp).setScale(2, RoundingMode.HALF_EVEN);
                newExpression = partOne + value + partTwo;
            } else {
                newExpression = partOne + resultExp + partTwo;
            }
            if (partOne.equals("-") && partTwo.equals("")) countOperation++;
            if (sqrExp.matches(".*[+\\-/*]-.*")) countOperation--;

            if (newExpression.equals("-0")) resultExpression = "0";
            else resultExpression = newExpression;
            countExpression = sqrExp;
        }

//        в случае, если выражение простое со скобками, но из двух отрицательных чисел без тригонометрии
        else if (expressionTrim.matches("^\\(-[.0-9]+\\)\\^\\(-[.0-9]+\\)$")) {
            String firstNum = expressionTrim.substring(1, expressionTrim.indexOf(")"));
            String secondNum = expressionTrim.substring(expressionTrim.lastIndexOf("(") + 1, expressionTrim.lastIndexOf(")"));
            resultExpression = "" + Math.pow(Double.parseDouble(firstNum), Double.parseDouble(secondNum));
            countExpression = expressionTrim;
        }

//        в иных случаях
        else {
            String partOne, partTwo, inBrackets;

            int firstOpen = expressionTrim.indexOf("(");
            int firstClose = expressionTrim.indexOf(")");
            int secondOpen = expressionTrim.indexOf("(", firstOpen + 1);
            int lastOpen = expressionTrim.lastIndexOf("(");

//            если скобки расположены: sin(2*55) - 2*sin(55)*cos(55)
            if (secondOpen > firstClose) {
                partOne = expressionTrim.substring(0, firstOpen);
                partTwo = expressionTrim.substring(firstClose + 1);
                inBrackets = expressionTrim.substring(firstOpen + 1, firstClose);
//                если скобки расположены: -((1/3)^((5/8)*42.42^0.0002)+2/7)
            } else if (lastOpen > firstClose) {
                partOne = expressionTrim.substring(0, lastOpen);
                partTwo = expressionTrim.substring(expressionTrim.indexOf(")", lastOpen) + 1);
                inBrackets = expressionTrim.substring(partOne.length() + 1, expressionTrim.indexOf(")", lastOpen));
//                если скобки расположены: sin(2*(-5+1.5*4)+28)
            } else {
                partOne = expressionTrim.substring(0, lastOpen);
                partTwo = expressionTrim.substring(firstClose + 1);
                inBrackets = expressionTrim.substring(lastOpen + 1, firstClose);

            }
            String newExpression;
            if (partOne.equals("-") && partTwo.equals("")) countOperation++;
            if (inBrackets.matches(".*[+\\-*/]{2}.*") && !expressionTrim.matches("^.*-?\\(-?[.0-9-+/^]+[+*/^-]-[.0-9-+/^]+\\)$"))
                countOperation--;

//            если в скобках только число, то без увеличения операции просто раскрываем скобки
            if (inBrackets.matches("^-?\\d+(\\.\\d+)?")) {
                newExpression = partOne + inBrackets + partTwo;
                if (partOne.length() == 0 && partTwo.length() == 0 && inBrackets.startsWith("-")) countOperation++;
                count = countOperation;
                numberInBrackets = true;

            } else {
                newExpression = partOne + calculateOperations(inBrackets) + partTwo;
            }
            resultExpression = newExpression;
            countExpression = inBrackets;
        }
        if (numberInBrackets) {
            recurse(resultExpression, count);
        } else {
            Pattern pattern = Pattern.compile("[-+*^/]|[a-z]{3}");
            Matcher matcher = pattern.matcher(countExpression);
            while (matcher.find()) {
                countOperation++;
            }
            recurse(resultExpression, countOperation);
        }
    }

    public double calculateOperations(String inBrackets) {
        String[] numbers = inBrackets.split("[-+*^/]");
//            если перед первым числом стоит -, то в массив первым элементом будет ""
        if (numbers[0].equals("")) numbers[0] = "0";
        List<String> numbersList = new ArrayList<>(Arrays.asList(numbers));

        String[] signs = inBrackets.split("[a-z]{3}|(\\d\\.\\d)+|[0-9]+");
//            если первым элементом в выражении стоит число, то в массив первым элементом будет ""
//            создаем лист для операций
        List<String> signsList = new ArrayList<>();
//            добавляем в лист только непустые элементы
        for (String sign : signs) {
            if (!sign.equals("") && !sign.equals(".")) {
                if (sign.length() > 1) {
                    String str = sign.substring(0, 1);
                    signsList.add(str);
                    int index = signsList.size() + 1; //3
                    String num = numbersList.get(index);
                    numbersList.set(index, "-" + num);
                    numbersList.remove(index - 1);
                } else {
                    signsList.add(sign);
                }
            }
        }

        while (signsList.contains("^")) {
            for (int i = 0; i < signsList.size(); i++) {
                if (signsList.get(i).equals("^"))
                    calculateSqr(signsList, numbersList, i);
            }
        }

        while (signsList.contains("/") && signsList.contains("*") || signsList.contains("/") || signsList.contains("*")) {
            for (int i = 0; i < signsList.size(); i++) {
                if (signsList.get(i).equals("/")) {
                    calculateDev(signsList, numbersList, i);
                    i--;
                } else if (signsList.get(i).equals("*")) {
                    calculateMult(signsList, numbersList, i);
                    i--;
                }
            }
        }
        while (signsList.contains("-") && signsList.contains("+") || signsList.contains("-") || signsList.contains("+")) {
            for (int i = 0; i < signsList.size(); i++) {
                if (signsList.get(i).equals("-")) {
                    calculateMin(signsList, numbersList, i);
                    i--;
                } else if (signsList.get(i).equals("+")) {
                    calculatePlus(signsList, numbersList, i);
                    i--;
                }
            }
        }

        if (numbersList.get(0).length() > 20) {
            BigDecimal value = new BigDecimal(numbersList.get(0)).setScale(2, RoundingMode.HALF_EVEN);
            numbersList.set(0, "" + value);
        }

        return Double.parseDouble(numbersList.get(0));
    }

    public void calculateSqr(List<String> signsList, List<String> numbersList, int i) {
        double result = Math.pow(Double.parseDouble(numbersList.get(i)), Double.parseDouble(numbersList.get(i + 1)));
        numbersList.set(i, String.valueOf(result));
        numbersList.remove(i + 1);
        signsList.remove(i);
    }

    public void calculateDev(List<String> signsList, List<String> numbersList, int i) {
        double result = Double.parseDouble(numbersList.get(i)) / Double.parseDouble(numbersList.get(i + 1));
        numbersList.set(i, String.valueOf(result));
        numbersList.remove(i + 1);
        signsList.remove(i);
    }

    public void calculateMult(List<String> signsList, List<String> numbersList, int i) {
        double result = Double.parseDouble(numbersList.get(i)) * Double.parseDouble(numbersList.get(i + 1));
        numbersList.set(i, String.valueOf(result));
        numbersList.remove(i + 1);
        signsList.remove(i);
    }

    public void calculateMin(List<String> signsList, List<String> numbersList, int i) {
        double result = Double.parseDouble(numbersList.get(i)) - Double.parseDouble(numbersList.get(i + 1));
        numbersList.set(i, String.valueOf(result));
        numbersList.remove(i + 1);
        signsList.remove(i);
    }

    public void calculatePlus(List<String> signsList, List<String> numbersList, int i) {
        double result = Double.parseDouble(numbersList.get(i)) + Double.parseDouble(numbersList.get(i + 1));
        numbersList.set(i, String.valueOf(result));
        numbersList.remove(i + 1);
        signsList.remove(i);
    }

    public Solution() {
        //don't delete
    }
}
