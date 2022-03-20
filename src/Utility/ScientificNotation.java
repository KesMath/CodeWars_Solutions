package Utility;

public class ScientificNotation {

    public static String shorthand(int num){
        return null;
    }

    public static void main(String[] args) {
        System.out.println(ScientificNotation.shorthand(5000000)); // returns "5*10^6"
        System.out.println(ScientificNotation.shorthand(532000));  // returns "5.32*10^5"
        System.out.println(ScientificNotation.shorthand(-2000));   // returns "2*10^3"
    }
}
