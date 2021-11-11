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

    public enum Token{
        INCREMENT_COMMAND('+'), //calls contructor with char = '+'
        OUTPUT_COMMAND('.');    //calls contructor with char = '.'

        public final char tokenChar;

        private Token(char token){
            this.tokenChar = token;
        }
        public char getToken(){
            return this.tokenChar;
        }
    }

    private String code;
    private int memoryCell;

    public MiniStringFuck(String code) {
        this.code = code;
        this.memoryCell = 0;
    }
    public void resetMemoryCell(){
        this.memoryCell = 0;
    }

    public String interpret() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < code.length(); i++){
            if(this.code.charAt(i) == Token.INCREMENT_COMMAND.getToken()){
                if(this.memoryCell == 256){
                    resetMemoryCell();
                }
                this.memoryCell++;
            }
            else if(this.code.charAt(i) == Token.OUTPUT_COMMAND.getToken()){
                builder.append((char) memoryCell);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String code = "(++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                      "+++++++++++++++++++++++++++++." +
                      "+++++++.." +
                      "+++." +
                      "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                      "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                      "+++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                      "++++++++++++++++++++++++." +
                      "+++." +
                      "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                      "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                      "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.";
        MiniStringFuck interpreter = new MiniStringFuck(code);
        System.out.println(interpreter.interpret()); //returns: "Hello, World!"

        String code2 = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.";
        MiniStringFuck interpreter2 = new MiniStringFuck(code2);
        System.out.println(interpreter2.interpret()); //returns: "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }

}


