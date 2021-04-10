package Cryptography;

public class EncryptThis {
    public static String encryptThis(String text) {
        //FIXME: NOT FULLY TESTED FOR ALL EDGE CASES
        if(text.length() == 0){ return ""; }
        String [] words = text.split(" ");
        StringBuilder str = new StringBuilder();
        for(String word: words){
            StringBuilder w = new StringBuilder(word);
            if(w.length() >= 2) {
                char temp = w.charAt(1);
                char letter = w.charAt(0);
                w.setCharAt(1, w.charAt(w.length() - 1));
                w.setCharAt(w.length() - 1, temp);
                w.deleteCharAt(0);
                str.append(String.valueOf((int) letter) + w + " ");
            }
            else{
                str.append(Integer.toString(text.charAt(0)) + " ");
            }
        }
        return str.toString().trim();
    }

    public static String decryptThis(String text) {
        //FIXME: NOT FULLY TESTED FOR ALL EDGE CASES
        if(text.length() == 0){ return ""; }
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
                else{
                    break;
                }
            }
            if(w.length() >= 1) {
                char temp = w.charAt(0);
                w.setCharAt(0, w.charAt(w.length() - 1));
                w.setCharAt(w.length() - 1, temp);
            }
            str.append(Character.toString(Integer.parseInt(digits.toString())) + w + " ");
        }
        return str.toString().trim();
    }

    public static void main(String[] args) {
        String str = "A wise old owl lived in an oak";
        String encryptedStr = EncryptThis.encryptThis(str);
        System.out.println(encryptedStr.equals("65 119esi 111dl 111lw 108dvei 105n 97n 111ka"));
        System.out.println(EncryptThis.decryptThis(encryptedStr));
        System.out.println(EncryptThis.decryptThis(encryptedStr).equals(str));
    }
}
