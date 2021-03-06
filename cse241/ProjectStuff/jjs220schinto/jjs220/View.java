import java.util.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;

public class View {
    private Scanner sc;

    public View() {
        sc = new Scanner(System.in);
    }

    public void print(String inMess) {
        System.out.print(inMess);
    }

    public void println(String inMess) {
        System.out.println(inMess);
    }

    public String readInput() {
        return sc.nextLine();
    }

    public String readAlphaNum() {
        while (true) {
            String input = readInput();
            if (input.equals("") || input.matches("[ a-zA-Z0-9]+")) {
                return input;
            }
            print("Invalid input.  Please enter alphanumeric characters only: ");
        }
    }

    public String readNum() {
        while (true) {
            String input = readInput();
            if (input.equals("") || input.matches("[0-9]+")) {
                return input;
            }
            print("Invalid input.  Please enter a number: ");
        }
    }

    public Double readPrice() {
        while (true) {
            String input = readInput();
            if (input.equals("") || input.matches("[.0-9]+")) {
                try {
                    return Math.round(Double.parseDouble(input) * 100.0)
                            / 100.0;
                }
                catch (Exception e) {
                }
            }
            print("Invalid input.  Please enter a price: ");
        }
    }

    public String readNumMonth() {
        String out = readNum();
        while (parseInt(out) == null || parseInt(out) < 1
                || parseInt(out) > 12) {
            print("Invalid input.  Please enter a number between 1 and 12: ");
            out = readNum();
        }
        if (out.length() == 1) {
            return "0" + out;
        }
        return out;
    }

    public String readNumYear() {
        String out = readNum();
        while (parseInt(out) == null) {
            print("Invalid input.  Please enter a number: ");
            out = readNum();
        }
        if (out.length() == 1) {
            return "0" + out;
        }
        return out;
    }

    public boolean rangeCheck(String check, int lower, int upper) {
        if (check.length() >= lower && check.length() <= upper) {
            return true;
        }
        return false;
    }

    public String readYN() {
        while (true) {
            String input = readInput().toUpperCase();
            if (input.equals("Y") || input.equals("N")) {
                return input;
            }
            print("Invalid input.  Please enter Y or N: ");
        }
    }

    public void printLine() {
        println("================================");
    }

    public String readChoice(String prompt, String... choices) {
        if (choices.length == 0) {
            return null;
        }
        if (choices.length == 1) {
            return "0";
        }
        printLine();
        println(prompt);
        for (int i = 1; i <= choices.length; i++) {
            println("[" + i + "] " + choices[i - 1]);
        }
        print("Enter a number (1 - " + choices.length + ") to make a choice: ");
        String input = readInput();
        int numInput;
        try {
            numInput = Integer.parseInt(input);
        }
        catch (Exception e) {
            input = null;
            numInput = 0;
        }

        while (input == null || !input.matches("[0-9]+") || numInput < 1
                || numInput > choices.length) {
            print("Please enter a number between 1 and " + choices.length
                    + ": ");
            input = readInput();
            try {
                numInput = Integer.parseInt(input);
            }
            catch (Exception e) {
                input = null;
                numInput = 0;
            }
        }
        return (Integer.parseInt(input) - 1) + "";
    }

    public Long parseLong(String theNum) {
        try {
            return Long.parseLong(theNum);
        }
        catch (Exception e) {
            return null;
        }
    }

    public Integer parseInt(String theNum) {
        try {
            return Integer.parseInt(theNum);
        }
        catch (Exception e) {
            return null;
        }
    }
}
