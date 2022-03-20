package Utility;

public class ScientificNotation {

    public static String shorthand(float num){
        int cout = 0;
        if (num > 0){
            while(num > 10){
                num /= 10;
                cout++;
            }
        }
        else{

        }

        String s = Float.toString(num) + "*10^" + Integer.toString(cout);
        return s;
    }

    public static void main(String[] args) {
        System.out.println(ScientificNotation.shorthand(5000000)); // returns "5*10^6"
        System.out.println(ScientificNotation.shorthand(532000));  // returns "5.32*10^5"
        //System.out.println(ScientificNotation.shorthand(-2000));   // returns "-2*10^3"
    }
}
