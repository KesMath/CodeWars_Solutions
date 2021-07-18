package Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Uniq {
    /**
     * Implement a function which behaves like the 'uniq -c' command in UNIX.
     *
     * It takes as input a sequence and returns a sequence in which all duplicate
     * elements following each other have been reduced to one instance together
     * with the number of times a duplicate elements occurred in the original array.
     * @param arr of characters that should be received via CLI
     * @return list of dictionaries containing number of consecutive occurrences
     */
    public static List<HashMap<Character, Integer>> uniq(char[] arr){
        List<HashMap<Character, Integer>> list = new ArrayList();
        int cout = 1;
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] == arr[i+1]){
                cout++;
            }
            else{
                HashMap<Character, Integer> map = new HashMap<>();
                map.put(arr[i], cout);
                list.add(map);
                cout = 1;
            }
        }
        //need to add trailing array dict
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(arr[arr.length - 1], cout);
        list.add(map);
        return list;
    }
    public static void main(String[] args) {
        System.out.println(Uniq.uniq(new char[]{'a','a','b','b','c','a','b','c'})); // RESULT: [{a=2}, {b=2}, {c=1}, {a=1}, {b=1}, {c=1}]
        System.out.println(Uniq.uniq(new char[]{'a','a','a','b','b','b','c','c','c'})); // RESULT: [{a=3}, {b=3}, {c=3}]
    }
}
