package Utility;

import java.util.HashMap;
import java.util.Map;


//DESCRIPTION: https://www.codewars.com/kata/58db721b2f449efaf5000038/
public class LicensePlateRecognition {

    public static String recognize(String s){
        StringBuilder str = new StringBuilder();
        StringBuilder runner = new StringBuilder();
        int newLineIndex = s.indexOf("\n");
        //observation: go 3 characters out to get the correct portion of the number
        // then append that to stringbuilder
        String topPortion = s.substring(0, newLineIndex + 1);
        String middlePortion = s.substring(newLineIndex + 1, 2*(newLineIndex + 1));
        String bottomPortion = s.substring(2*(newLineIndex + 1), 3*(newLineIndex + 1) - 1);
        Map<String, Character> map = new HashMap<>()
                                    {{put(" _  _||_ ",'2');
                                      put(" _  _| _|",'3');
                                      put("   |_|  |",'4');
                                      put(" _ |_  _|",'5');
                                      put(" _ |_ |_|",'6');
                                      put(" _   |  |",'7');
                                      put(" _ |_||_|",'8');
                                      put(" _ |_| _|",'9');
                                    }};

        for(int j = 3; j < topPortion.length(); j+=3) {
            int i = j - 3;
            runner.append(topPortion, i, j);
            runner.append(middlePortion, i, j);
            runner.append(bottomPortion, i, j);
            str.append(map.get(runner.toString()));
            runner.delete(0, runner.length());
            }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(LicensePlateRecognition.recognize(" _  _     _  _  _  _  _ \n"+
                                                               " _| _||_||_ |_   ||_||_|\n"+
                                                               "|_  _|  | _||_|  ||_| _|")); //returns: 23456789
    }
}
