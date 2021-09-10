package Utility;

public class VinChecker {
    public static boolean checkVin(String vin) {
        if(vin.length() != 17) return false;
        else{
            int accum = 0;
            int conversion;
            int [] weights = new int[]{8,7,6,5,4,3,2,10,0,9,8,7,6,5,4,3,2};
            for(int i = 0; i < vin.length(); i++){
                if(Character.isDigit(vin.charAt(i)))
                    accum += Character.getNumericValue(vin.charAt(i)) * weights[i];
                else{
                    switch(vin.charAt(i)){
                        case 'A':
                        case 'J':
                            conversion = 1;
                            break;

                        case 'B':
                        case 'K':
                        case 'S':
                            conversion = 2;
                            break;

                        case 'C':
                        case 'L':
                        case 'T':
                            conversion = 3;
                            break;

                        case 'D':
                        case 'M':
                        case 'U':
                            conversion = 4;
                            break;

                        case 'E':
                        case 'N':
                        case 'V':
                            conversion = 5;
                            break;

                        case 'F':
                        case 'W':
                            conversion = 6;
                            break;

                        case 'G':
                        case 'P':
                        case 'X':
                            conversion = 7;
                            break;

                        case 'H':
                        case 'Y':
                            conversion = 8;
                            break;

                        case 'R':
                        case 'Z':
                            conversion = 9;
                            break;

                        default:
                            return false;
                    }
                accum += conversion * weights[i];
                }
            }
            if (accum % 11 == Character.getNumericValue(vin.charAt(8))
                    || (accum % 11 == 10 && vin.charAt(8) == 'X')) return true;
        }
        return false;
    }
        public static void main(String args[]) {
            System.out.println(VinChecker.checkVin("5YJ3E1EA7HF000337")); //true
            System.out.println(VinChecker.checkVin("5YJ3E1EAXHF000347")); //true
            System.out.println(VinChecker.checkVin("5VGYMVUX7JV764512")); //true
            System.out.println(VinChecker.checkVin("7WDMMTDV9TG739741")); //false
            System.out.println(VinChecker.checkVin("7JTRH08L5EJ234829")); //false
        }
    }
