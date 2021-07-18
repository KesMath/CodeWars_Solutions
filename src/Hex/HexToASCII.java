package Hex;

public class HexToASCII {

    public static String hextoASCII(String[] array){
        StringBuilder s = new StringBuilder();
        for (String hex : array) { s.append(Character.toString(HextoDec.hexToDec(hex)) + " ");}
        return s.toString().stripTrailing();
    }

    public static void main(String[] args) {
        System.out.println(HexToASCII.hextoASCII(new String[]{"50", "be","4e","3a","6d","f5","63","f2","6b","86","f4","d7","fb","44","27","09","45","61","dc","85"}));
    }
}
