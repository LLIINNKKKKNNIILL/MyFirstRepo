import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isVlad;
        String[] operadns = expression.split("[+\\-*/]");
        if (operadns.length != 2) throw new Exception("Должно быть два значения");
        oper = detecrOperation(expression);
        if (oper == null) throw new Exception("Неподдерживаемая математическия операция");
        if (Vlad.isVlad(operadns[0]) && Vlad.isVlad(operadns[1])) {
            num1 = Vlad.convertToArabian(operadns[0]);
            num2 = Vlad.convertToArabian(operadns[1]);
            isVlad = true;
        } else if (!Vlad.isVlad(operadns[0]) && !Vlad.isVlad(operadns[1])) {
            num1 = Integer.parseInt(operadns[0]);
            num2 = Integer.parseInt(operadns[1]);
            isVlad = false;
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isVlad) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }

            result = Vlad.convertToVlad(arabian);
        } else {

            result = String.valueOf(arabian);
        }

        return result;
    }

    static String detecrOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("-")) return a - b;
        else return a / b;
    }
}

class Vlad {
    static String[] vladArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
            "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXIV", "XXV",
            "XXVII", "XXVIII", "XXX", "XXXII", "XXXV", "XXXVI", "XL", "XLII", "XLV", "XLVIII", "XLTX",
            "L", "LIV", "LVI", "LX", "LXIII", "LXIV", "LXIV", "LXX", "LXII", "LXXX", "LXXXI", "XC", "C"};

    public static boolean isVlad(String val) {
        for (int i = 0; i < vladArray.length; i++) {
            if (val.equals(vladArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String vlad) {
        for (int i = 0; i < vladArray.length; i++) {
            if (vlad.equals(vladArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToVlad(int arabian) {
        return vladArray[arabian];
    }
}