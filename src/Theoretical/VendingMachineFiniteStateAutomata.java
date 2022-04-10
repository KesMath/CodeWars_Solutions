package Theoretical;

import java.util.HashMap;

public class VendingMachineFiniteStateAutomata {
    private HashMap<String, Integer> wordsToNumbers = new HashMap<>();
    private HashMap<Integer, HashMap<Integer,String>> finiteMachineStates = new HashMap<>();
    private int candyPriceBalance = 25;

    public VendingMachineFiniteStateAutomata(){
        wordsToNumbers.put("five", 5);
        wordsToNumbers.put("ten", 10);
        wordsToNumbers.put("twenty", 20);

        finiteMachineStates.put(25, new HashMap<>()
                                {{
                                    put(5, "twenty");
                                    put(10, "fifteen");
                                    put(20, "five");
                                }});

        finiteMachineStates.put(20, new HashMap<>()
                                {{
                                    put(5, "fifteen");
                                    put(10, "ten");
                                    put(20, "Take your candy");
                                }});

        finiteMachineStates.put(15, new HashMap<>()
                                {{
                                    put(5, "ten");
                                    put(10, "five");
                                }});

        finiteMachineStates.put(10, new HashMap<>()
                                {{
                                    put(5, "five");
                                    put(10, "Take your candy");
                                }});

        finiteMachineStates.put(5, new HashMap<>()
                                {{
                                    put(5, "Take your candy");
                                }});
    }

    public String vendingMachine(String amount){
        if(!wordsToNumbers.containsKey(amount)){
            return "I don't know what you put inside of me, but I'm keeping it";
        }
        Integer insertedValue = wordsToNumbers.get(amount);
        String newBalance = finiteMachineStates.get(candyPriceBalance).get(insertedValue);
        candyPriceBalance -= insertedValue;
        if(candyPriceBalance == 0){
            return newBalance;
        }
        return "Give me " + newBalance + " more";
    }

    public static void main(String[] args) {
        VendingMachineFiniteStateAutomata vm = new VendingMachineFiniteStateAutomata();
        System.out.println(vm.vendingMachine("twenty"));
        System.out.println(vm.vendingMachine("five"));
        //System.out.println(vm.vendingMachine("o"));
    }
}
