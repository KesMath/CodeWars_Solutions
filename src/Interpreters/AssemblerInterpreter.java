package Interpreters;

import java.util.HashMap;
import java.util.Map;

public class AssemblerInterpreter {
    /**
     * Every inc/dec/jnz on a register will always be preceeded
     * by a mov on the register first,
     * so you don't need to worry about uninitialized registers.
     */
    private HashMap<String, Integer> variables = new HashMap<>();
    private String [] program;
    private int programCounter;

    private boolean isInteger(String a){
        boolean isInteger = true;
        try{
            Integer.valueOf(a);
        }
        catch (NumberFormatException e){
            isInteger = false;
        }
        return isInteger;
    }

    public void mov(String var, String constant){
        if(isInteger(constant)) {
            variables.put(var, Integer.valueOf(constant));
        }
        else{
            //case when value param refers to another register
            int registerVal = variables.get(constant);
            variables.put(var, registerVal);
        }
        programCounter++;
    }

    private void updateValue(String var, char sinage){
        if (sinage == '-'){
            Integer value = variables.get(var);
            value--; //unboxing happens here which compiler increment occurs within primitive int contained within Integer
            variables.put(var, value);
            programCounter++;
        }
        else if (sinage == '+'){
            Integer value = variables.get(var);
            value++;
            variables.put(var, value);
            programCounter++;
        }
    }

    public void inc(String var){ updateValue(var,'+'); }
    public void dec(String var){ updateValue(var,'-'); }


    public void jnz(String var, Integer steps) throws IndexOutOfBoundsException{
        /**APPROACH: jump or goto should probably refer to index in array: where goto = current pointer of jnz - steps
         * this goto step should be executed in a while loop until variable value = 0
         *
         * NOTE: This implementation assumes that register/variable in this jump command (i.e. "jmp a -1")
         * points to a command that contains corresponding register name (i.e. "dec a" and not something like "dec x")
         */

        /** NOTE: need copy variable to preserve state of the main pgrmCout!!
         *  if actual counter was used, then proceeding jnz commands will
         *  essentially pick up wherever previous jnz pointer left off - leading to undesirable faults.
         *  Non-jnz commands (i.e. mov, dec, inc) will have pointers
         *  that do not align to their respective index in program array!
         */

        int programCounterCopy = programCounter;
        int jmpPointer = programCounterCopy + steps;
        int resetValue = jmpPointer;

        if(jmpPointer >= program.length || jmpPointer < 0){
            throw new IndexOutOfBoundsException("Cannot jump "
                    + steps +
                    " steps in program array!");
        }
        /** NOTE: while loop should not only iterate on jmpCmd but execute any program cmd between: jmpCmd and JNZ
         *  until var == 0
         */
        while(variables.get(var) != 0){
            //must use a stale copy of pgmCounter which represents the index of this jnz() call
            // usage of actual 'programCounter' will ensure else statement would never be reached since it increments per execute() call!
            if(jmpPointer < programCounterCopy)
                execute(program[jmpPointer++]);
            else{
                jmpPointer = resetValue;
            }
        }
        //FIXME: this is a temporary patch to finish executing cmds in array after var == 0
        // A better implementation would be to integrate this into previous loop but runtime is still unaffected at O(n)!
        for(;jmpPointer < programCounterCopy; jmpPointer++){execute(program[jmpPointer]);}

        /**NOTE: calls to execute (which calls (1/4) methods)
         *  increases programCounter+=N (where N is iterations until conditional reaches 0)
         *  Hence, resetting it back to it's original value prevents future IndexOOB exception
         *  and allows for program flow to proceed after jnz call!
         */
        programCounter = programCounterCopy + 1;
    }

    public void execute(String line){
        String[] command = line.split(" ");
        String method = command[0];
        String var = command[1];
        switch(method){
            case "mov":
                mov(var, command[2]);
                break;
            case "inc":
                inc(var);
                break;
            case "dec":
                dec(var);
                break;
            case "jnz":
                jnz(var, Integer.valueOf(command[2]));
                break;
        }
    }

    public Map<String, Integer> interpret(String[] program){
        //this method acts as constructor call given assignment design
        this.program = program;
        this.programCounter = 0;
        for(String line : program){
            execute(line);
        }
        return variables;
    }

    public static void main(String[] args) {
        String [] program = new String[]{
                "mov a 5",
                "inc a",
                "dec a",
                "dec a",
                "jnz a -1",
                "inc a"
        };

        String [] program2 = new String[] {
                "mov a -10",
                "mov b a",
                "inc a",
                "dec b",
                "jnz a -2"};
        AssemblerInterpreter interpreter = new AssemblerInterpreter();
        System.out.println(interpreter.interpret(program));
        System.out.println(interpreter.interpret(program2));
    }
}
