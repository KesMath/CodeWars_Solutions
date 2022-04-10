package Theoretical;

import java.util.HashMap;

public class VendingMachineFiniteStateAutomata {
    private HashMap<String, Integer> wordsToNumbers = new HashMap<>();
    int candyPrice = 25;

    public VendingMachineFiniteStateAutomata(){
        wordsToNumbers.put("five", 5);
        wordsToNumbers.put("ten", 10);
        wordsToNumbers.put("twenty", 20);
    }

    public String vendingMachine(String amount){
        if(!wordsToNumbers.containsKey(amount)){
            return "I don't know what you put inside of me, but I'm keeping it";
        }
        return "";

    }

    public static void main(String[] args) {
        VendingMachineFiniteStateAutomata vm = new VendingMachineFiniteStateAutomata();
        System.out.println(vm.vendingMachine("five"));
        System.out.println(vm.vendingMachine("o"));
    }
}
