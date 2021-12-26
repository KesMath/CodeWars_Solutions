package Interpreters;

import java.util.Stack;

// https://www.codewars.com/kata/577e9095d648a15b800000d4/java

/**Technique: When operator is discovered, stack pops twice
 * and 'eagerly' performs operator on those two operands and pushes result back to stack
 * so that subsequent operators can operate on this new operand.
 **/
public class PostFixEvaluator {

    public long evaluate(String s) {
        String[] strList = s.split(" ");
        Stack<String> stack = new Stack<>();
        for(String str : strList){
            if(str.equals("+")){
                stack.push("" + (Integer.valueOf(stack.pop()) + Integer.valueOf(stack.pop())));
            }
            else if (str.equals("-")){
                int operand1 = Integer.valueOf(stack.pop());
                stack.push("" + (Integer.valueOf(stack.pop()) - operand1));
            }
            else if (str.equals("*")){
                stack.push("" + (Integer.valueOf(stack.pop()) * Integer.valueOf(stack.pop())));
            }
            else if (str.equals("/")){
                int operand1 = Integer.valueOf(stack.pop());
                stack.push("" + (Integer.valueOf(stack.pop()) / operand1));
            }
            else{
                stack.push(str);
            }
        }
        return Long.parseLong(stack.pop());
    }

    public static void main(String[] args) {
        PostFixEvaluator eval = new PostFixEvaluator();
        System.out.println(eval.evaluate("24 25 +"));
        System.out.println(eval.evaluate("20 40 + 60 *"));
        System.out.println(eval.evaluate("20 40 60 + *"));
        System.out.println(eval.evaluate("2 3 9 4 / + *"));
    }
}
