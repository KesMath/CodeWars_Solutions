package Theoretical;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//https://www.codewars.com/kata/59923f1301726f5430000059/train/
public class FSMCompiler {

    public class Traveler {
        private List<String> traversal = new ArrayList<>();
        private int finalValue;

        public List<String> getTraversedPath(){return traversal;}
        public int getFinalValue(){return finalValue;}
        public String getFinalState(){return traversal.get(traversal.size()-1);}

        private void setFinalValue(int val){this.finalValue = val;}
    }

    public Traveler state;
    private Map<String, Map<Integer, String>> transitions = new HashMap<>();

    public FSMCompiler(String instructions){
        String[] instruction = instructions.split("\n");
        for (String path: instruction){
            String[] states = path.split(";"); //["S1", "S1, S2", "9"]
            String[] route = states[1].split(","); //["S1","S2"]
            transitions.put(states[0], new HashMap<>(){{ put(0, route[0]);
                                                         put(1, route[1]);
                                                         put(-1, states[2]);}});
        }
    }

    public Traveler runFSM(String start, int[] sequences){
        Traveler traveler = new Traveler();
        traveler.getTraversedPath().add(start);
        for(int step: sequences){
            traveler.getTraversedPath().add(transitions.get(start).get(step));
        }
        traveler.setFinalValue(Integer.valueOf(transitions.get(traveler.getFinalState()).get(-1)));
        return traveler;
    }

    public static void main(String[] args) {
        FSMCompiler fsmCompiler = new FSMCompiler("S1; S1, S2; 9\n" +
                                                             "S2; S1, S3; 10\n" +
                                                             "S3; S4, S3; 8\n" +
                                                             "S4; S4, S1; 0");
        fsmCompiler.runFSM("S1", new int[]{0, 1, 1, 0, 1});
        System.out.println(fsmCompiler.state.getFinalState());
        System.out.println(fsmCompiler.state.getFinalValue());
        System.out.println(fsmCompiler.state.getTraversedPath());
    }
}
