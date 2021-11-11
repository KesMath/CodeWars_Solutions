package Interpreters;

// DESCRIPTION: https://www.codewars.com/kata/586dd26a69b6fd46dd0000c0/train/java


/**
 * MiniStringFuck is a derivative of the famous Brainfuck
 * which contains a memory cell as its only form of data storage
 * as opposed to a memory tape of 30,000 cells in Brainfuck.
 *
 * The memory cell in MiniStringFuck initially starts at 0.
 * MiniStringFuck contains only 2 commands as opposed to 8:
 *
 * + - Increment the memory cell. If it reaches 256, wrap to 0.
 * . - Output the value of the memory cell as a character with code point equal to the value
 */
public class MiniStringFuck {

    public MiniStringFuck(String code) {

    }

    public String interpret() {
        return "TODO";
    }

    public static void main(String[] args) {

        String code = "(\"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+++++++++++++++++++++++++++++.+++++++..+++.+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+++++++++++++++++++++++++++++++++++++++++++++++++++++++.++++++++++++++++++++++++.+++.++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.";
        MiniStringFuck interpreter = new MiniStringFuck(code);
        System.out.println(interpreter.interpret());
    }

}


