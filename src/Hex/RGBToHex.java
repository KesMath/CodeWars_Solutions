package Hex;

public class RGBToHex {
    public static String base10ToBase16(int a) {
        /**
         * Convert Base 10 -> 16:
         * (1) - perform n mod 16 to collect remainders
         * (2) - let n/16 be assigned to n and repeat this until n == 0
         * (3) - map remainders to A = 10, B = 11,... F = 15
         */
        if(a <= 0){ return "00";}
        else if(a >= 1 && a <= 9){return "0" + a;}
        else if(a > 255){a = 255;}
        StringBuilder str = new StringBuilder();
        while (a != 0) { // or (a < HextoDec.RADIX_16) since a % N = a for some N > a! Append string(a) after while loop if this route is taken!
            int remainder = a % HextoDec.RADIX_16;
            switch (remainder) {
                case HextoDec.HEX_A:
                    str.append("A");
                    break;
                case HextoDec.HEX_B:
                    str.append("B");
                    break;
                case HextoDec.HEX_C:
                    str.append("C");
                    break;
                case HextoDec.HEX_D:
                    str.append("D");
                    break;
                case HextoDec.HEX_E:
                    str.append("E");
                    break;
                case HextoDec.HEX_F:
                    str.append("F");
                    break;
                default:
                    str.append(remainder);
                    break;
            }
            a /= HextoDec.RADIX_16;
        }
        return str.reverse().toString();
    }

    public static String rgbToHex(int r, int g, int b){
        StringBuilder str = new StringBuilder();
        str.append(base10ToBase16(r));
        str.append(base10ToBase16(g));
        str.append(base10ToBase16(b));
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(RGBToHex.rgbToHex(255, 255, 255));
        System.out.println(RGBToHex.rgbToHex(255, 255, 300));
        System.out.println(RGBToHex.rgbToHex(-25, -255, 9));
        System.out.println(RGBToHex.rgbToHex(0, 0, 0));
        System.out.println(RGBToHex.rgbToHex(148, 0, 211));
    }
}
