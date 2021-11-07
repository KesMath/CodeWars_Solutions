package Cryptography;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * DESCRIPTION: https://www.codewars.com/kata/5efae11e2d12df00331f91a6/
 *
 * RESOURCE: https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm
 * RESOURCE: https://mkyong.com/java/java-how-to-convert-bytes-to-hex/
 */

public class CrackThePin {
    /**
     *This function permutes all md5 hashes for a 5 digit pin (each digit ranging from 0 - 9)
     * and returns the pin corresponding to the parametrized string. This exercise
     * is used to simulate one technique of cracking md5 hashing through brute forcing.
     *
     * The real world context of this exercise can be used to create rainbow tables
     * which are precomputed tables for caching passwords to their hash functions.
     *
     * Given the password policy of a web site at time of password creation,
     * along with a reference to password hashes (assuming no salt), an attacker can
     * use a function as below to reverse engineer the actual password
     *
     * @param hash - md5 hash
     * @return 5 digit pin, that when applied through md5 hash function, returns parameter str
     */
    private String convertByteArrToHex(byte [] arr){
        StringBuilder hex = new StringBuilder();
        for(byte b : arr){
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
    public String crack(String hash) throws NoSuchAlgorithmException {
        String pin = null;
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e){
            throw new NoSuchAlgorithmException(e);
        }
        label:
        for(int f = 0; f < 10; f++){
            for(int g = 0; g < 10; g++){
                for(int h = 0; h < 10; h++){
                    for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                            byte[] arr = md.digest(("" + f + ""+ g + "" + h + "" + i + "" + j).getBytes());
                            if(convertByteArrToHex(arr).equals(hash)){
                                pin = ("" + f + ""+ g + "" + h + "" + i + "" + j);
                                break label;
                            }
                        }
                    }
                }
            }
        }
        return pin;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        CrackThePin cracker = new CrackThePin();
        cracker.crack("827ccb0eea8a706c4c34a16891f84e7b");
        cracker.crack("86aa400b65433b608a9db30070ec60cd");
    }
}
