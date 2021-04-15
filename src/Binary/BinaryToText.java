package Binary;

public class BinaryToText {
    /**
     * High level Algorithm:
     * A character = 8 bits = 1 byte
     * Convert 1 byte to decimal value by: Value = ∑ BitValue * (Radix^n) where 0<=n<=8
     * Find corresponding Value in ASCII Table: https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
     * Repeat steps for all bytes to get string representation
     */
    public static String binaryToText(String binary) {
        // a less optimal but cleaner approach
        // would be to break down binary string to list of bytes which alleviates modulo cycling
        char[] bitArray = binary.toCharArray();
        StringBuilder str = new StringBuilder();
        int asciiValue = 0;
        double bitIndex = 7.0;
        for(int i = 1, k = 0; i <= bitArray.length; i++, k++){
            //decimal conversion = bitValue * 2^(bitIndex) where <=0 bitIndex <=8
            if(bitArray[k] == '1'){ // Zero Property of Multiplication implies don't waste time doing computation on 0 bit values
                asciiValue += (int)(Math.pow(2.0, bitIndex));
            }
            bitIndex--;
            if( i % 8 == 0){
                str.append(Character.toString(asciiValue));
                asciiValue = 0;
                bitIndex = 7.0;
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(BinaryToText.binaryToText("0100100001100101011011000110110001101111"));
    }
}
