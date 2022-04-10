package Theoretical;
import java.util.HashMap;

//DESCRIPTION: https://www.codewars.com/kata/59267e389b424dcd3f0000c9/train/javascript
public class VendingMachineFiniteStateAutomata {
    private HashMap<String, Integer> wordsToNumbers = new HashMap<>();
    private HashMap<Integer, String> numbersToWords = new HashMap<>();
    private HashMap<Integer, HashMap<Integer,String>> finiteMachineStates = new HashMap<>();
    private int candyPriceBalance = 25;

    public VendingMachineFiniteStateAutomata(){
        //indicates which amounts are accepted by machine
        wordsToNumbers.put("five", 5);
        wordsToNumbers.put("ten", 10);
        wordsToNumbers.put("twenty", 20);

        //for state transitions until a balance reaches 0
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
                                    put(20, "zero");
                                }});

        finiteMachineStates.put(15, new HashMap<>()
                                {{
                                    put(5, "ten");
                                    put(10, "five");
                                }});

        finiteMachineStates.put(10, new HashMap<>()
                                {{
                                    put(5, "five");
                                    put(10, "zero");
                                }});

        finiteMachineStates.put(5, new HashMap<>()
                                {{
                                    put(5, "zero");
                                }});

        //required in cases if user gives more than balance
        numbersToWords.put(5, "five");
        numbersToWords.put(10, "ten");
        numbersToWords.put(15, "fifteen");
    }

    public void resetBalance(){
        this.candyPriceBalance = 25;
    }

    public String vendingMachine(String amount){
        if(!wordsToNumbers.containsKey(amount)){
            return "I don't know what you put inside of me, but I'm keeping it";
        }
        Integer insertedValue = wordsToNumbers.get(amount);
        String newBalance = finiteMachineStates.get(candyPriceBalance).get(insertedValue);
        candyPriceBalance -= insertedValue;
        if(candyPriceBalance == 0){
            resetBalance();
            return "Take your candy";
        }
        else if(candyPriceBalance < 0){
            String debtOwed = numbersToWords.get(candyPriceBalance * -1);
            resetBalance();
            return "Take your candy and " + debtOwed + " back";
        }
        return "Give me " + newBalance + " more";
    }

    public static void main(String[] args) {
        VendingMachineFiniteStateAutomata vm = new VendingMachineFiniteStateAutomata();
        System.out.println(vm.vendingMachine("five"));
        System.out.println(vm.vendingMachine("five"));
        System.out.println(vm.vendingMachine("twenty"));
        System.out.println(vm.vendingMachine("twenty"));
        System.out.println(vm.vendingMachine("o"));
        System.out.println(vm.vendingMachine("ten"));
    }
}
