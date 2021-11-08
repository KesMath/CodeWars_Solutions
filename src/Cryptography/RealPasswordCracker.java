package Cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//DESCRIPTION: https://www.codewars.com/kata/59146f7b4670ba520900000a
public class RealPasswordCracker {

    /**
     * permutes all character combinations for strings at most 5 characters in length
     * in total, this function will permute:
     * 26^5 + 26^4 + 26^3 + 26^2 + 26^1 = 12,356,630 combinations
     *
     * @param hash SHA-1 hash value
     * @return - the string that generated the SHA-1 hash value
     */
    public static String passwordCracker(String hash) throws NoSuchAlgorithmException {
        String str = null;
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException e){
            throw new NoSuchAlgorithmException(e);
        }

        for(int i = 97; i < 123; i++){
            str = (char) i + "";
            if(CrackThePin.convertByteArrToHex(md.digest(str.getBytes())).equals(hash))
                return str;

            for(int j = 97; j < 123; j++){
                str = (char) i + "" + (char) j;
                if(CrackThePin.convertByteArrToHex(md.digest(str.getBytes())).equals(hash))
                    return str;

                for(int k = 97; k < 123; k++){
                    str = (char) i  + "" + (char) j + "" + (char) k;
                    if(CrackThePin.convertByteArrToHex(md.digest(str.getBytes())).equals(hash))
                        return str;

                    for(int l = 97; l < 123; l++){
                        str = (char) i  + "" + (char) j + "" + (char) k + "" + (char) l;
                        if(CrackThePin.convertByteArrToHex(md.digest(str.getBytes())).equals(hash))
                            return str;

                        for(int m = 97; m < 123; m++){
                            str = (char) i  + "" + (char) j + "" + (char) k + "" + (char) l + (char) m;
                            if(CrackThePin.convertByteArrToHex(md.digest(str.getBytes())).equals(hash))
                                return str;
                        }
                    }
                }
            }
        }
        return str;
    }

    /**
     * permutes all character combinations for strings at most 5 characters in length
     * in total, this function will permute:
     * 26^5 + 26^4 + 26^3 + 26^2 + 26^1 = 12,356,630 combinations
     *
     * @param
     * @return - a string containing all 12,356,630 string permutations (for characters of length 1 <= x <= 5)
     * mapped to it's SHA-1 hash. This will be used to create a rainbow table
     */
    public static String permuteAllSHA_1Hashes() throws NoSuchAlgorithmException {return "";}


    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(RealPasswordCracker.passwordCracker("e6fb06210fafc02fd7479ddbed2d042cc3a5155e"));
        System.out.println(RealPasswordCracker.passwordCracker("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"));
    }
}
