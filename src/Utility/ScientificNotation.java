package Utility;

public class ScientificNotation {

    public static String formatter(float num){
        /**
         * If no significant digits after decimal,
         * floating point string is formatted to integer:
         * i.e. 5.0 -> 5
         *
         * If significant digits after decimal,
         * floating point string is rounded to 3 decimal places:
         * i.e. 5.432745242 -> 5.433
         *
         * Additionally, leading '1's are excluded out of scientific notation
         * due to redundancy as being an identity operand
         * i.e. 1*10^3 -> 10^3
         */

        if(num - (int)num == 0){ // determine if significant digits exist after decimal
            int integer = (int)num;
            return integer == 1 ? "" : integer + "*";

        }
        else{
            //TODO: must round to 3 decimal places
            return num + "*";
        }
    }

    public static String shorthand(float num){
        int cout = 0;
        if (num > 0){
            while(num >= 10){
                num /= 10;
                cout++;
            }
        }
        else{
            while(num <= -10){
                num /= 10;
                cout++;
            }
        }

        String s = formatter(num) + "10^" + cout;
        return s;
    }

    public static void main(String[] args) {
        System.out.println(ScientificNotation.shorthand(5000000)); // returns "5*10^6"
        System.out.println(ScientificNotation.shorthand(532000));  // returns "5.32*10^5"
        System.out.println(ScientificNotation.shorthand(-2000));   // returns "-2*10^3"
        System.out.println(ScientificNotation.shorthand(1000));   // returns "10^3"
        System.out.println(ScientificNotation.shorthand(-1000));   // returns "-1*10^3"
        //System.out.println(ScientificNotation.shorthand(532452000));  // returns "5.325*10^8"
    }
}
