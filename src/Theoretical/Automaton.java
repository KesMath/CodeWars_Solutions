package Theoretical;

import java.util.HashMap;

public class Automaton {
    public static final int Q1 = 1;
    public static final int Q2 = 2;
    public static final int Q3 = 3;
    public static final String ZERO = "0";
    public static final String ONE = "1";

    private int state = Q1; //will track current state after each i ∈ cmds
    private static HashMap<Integer, HashMap<String, Integer>> transitions = new HashMap<>();
    public Automaton(){
        transitions.put(Q1,new HashMap<>()
                            {{ put(ZERO, Q1);
                               put(ONE, Q2);}});

        transitions.put(Q2,new HashMap<>()
                            {{ put(ZERO, Q3);
                               put(ONE, Q2);}});

        transitions.put(Q3,new HashMap<>()
                            {{ put(ZERO, Q2);
                               put(ONE, Q2);}});
    }
    public boolean readCommands(String[] cmds){
        for(int i = 0; i < cmds.length; i++){
            state = transitions.get(state).get(cmds[i]);
        }
        return state==2;
    }

    public static void main(String[] args) {
        Automaton a = new Automaton(); //assuming start value is Q1
        System.out.println(a.readCommands(new String[]{"1","0","0","1"})); //Q2 -> Q3 -> Q2 -> Q2 thus True!
        System.out.println(a.readCommands(new String[]{"1","0","0","0"})); //Q2 -> Q3 -> Q2 -> Q3 thus False!
    }
}
