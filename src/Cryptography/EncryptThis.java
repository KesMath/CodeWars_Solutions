package Cryptography;

public class EncryptThis {
    public static String encryptThis(String text) {
        String [] words = text.split(" ");
        StringBuilder str = new StringBuilder();
        for(String word: words){
            StringBuilder w = new StringBuilder(word);
            char temp = w.charAt(1);
            char letter = w.charAt(0);
            w.setCharAt(1, w.charAt(w.length()-1));
            w.setCharAt(w.length()-1, temp);
            w.deleteCharAt(0);
            str.append(String.valueOf((int) letter) + w + " ");
        }
        return str.toString().trim();
    }//xKdf98FG6x

    public static String decryptThis(String text) {
        String[] words = text.split(" ");
        StringBuilder str = new StringBuilder();
        for (String word : words) {
            //TODO: use regex to split digits from words!
            StringBuilder digits = new StringBuilder();
            StringBuilder w = new StringBuilder(word);
            for (int i = 0; i < word.length(); i++) {
                if (Character.isDigit(word.charAt(i))) { //also valid using ascii table: if(word.CharAt(i) >= 48 && word.CharAt(i) <= 57)
                    digits.append(word.charAt(i));
                    w.deleteCharAt(0);
                }
            }
            char temp = w.charAt(0);
            w.setCharAt(0, w.charAt(w.length() - 1));
            w.setCharAt(w.length() - 1, temp);
            str.append(Character.toString(Integer.parseInt(digits.toString())) + w + " ");
            }
        return str.toString().trim();
    }

    public static void main(String[] args) {
        String str = "Thank you Piotr for all your help";
        String encryptedStr = EncryptThis.encryptThis(str);
        System.out.println(encryptedStr.equals("84kanh 121uo 80roti 102ro 97ll 121ruo 104ple"));
        //System.out.println(EncryptThis.decryptThis(encryptedStr));
        System.out.println(EncryptThis.decryptThis(encryptedStr).equals(str));
    }
}
