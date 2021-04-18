package Hex;


public class HextoDec {
    /**
     * High level Algorithm:
     * Convert Hex to Decimal: Value = ∑ Char * (Radix^n) where: 0<=Char<=15, Radix = 16, 0<=n<=(lenOfhexStr)
     * For Explanation, refer to: https://www.binaryhexconverter.com/hex-to-decimal-converter
     *
     * ONE LINE SOLN: Integer.parseInt(hexString, 16);
     */
    public static final int HEX_A = 10;
    public static final int HEX_B = 11;
    public static final int HEX_C = 12;
    public static final int HEX_D = 13;
    public static final int HEX_E = 14;
    public static final int HEX_F = 15;
    public static final int RADIX_16 = 16;

    public static int hexToDec(final String hexString) {
        double dec = 0.0;
        int pow = 0;
        for(int i = hexString.length()-1; i >=0; i--, pow++){
            switch(Character.toUpperCase(hexString.charAt(i))){
                case 'A':
                    dec+= HEX_A * Math.pow(RADIX_16, pow);
                    break;
                case 'B':
                    dec+= HEX_B * Math.pow(RADIX_16, pow);
                    break;
                case 'C':
                    dec+= HEX_C * Math.pow(RADIX_16, pow);
                    break;
                case 'D':
                    dec+= HEX_D * Math.pow(RADIX_16, pow);
                    break;
                case 'E':
                    dec+= HEX_E * Math.pow(RADIX_16, pow);
                    break;
                case 'F':
                    dec+= HEX_F * Math.pow(RADIX_16, pow);
                    break;
                case '-':
                    dec*=-1;
                    break;
                default:
                    dec+=Integer.valueOf(String.valueOf(hexString.charAt(i))) * Math.pow(RADIX_16, pow);
                    break;
            }
        }

        return (int)dec;
    }

    public static void main(String[] args) {
        System.out.println(HextoDec.hexToDec("-a"));
    }
}
