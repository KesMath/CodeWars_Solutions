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
     *This function permutes all md5 hashes for a 5 digit pin (ranging from 0 - 9) This exercise
     * is used to simulate one technique of cracking md5 hashing through brute forcing.
     *
     * Given the password policy of a web site at time of password creation,
     * (best bet would be to leverage Wayback Machine: https://archive.org/web/ and try to create account)
     * along with a reference to password hashes (assuming no salt), an attacker can
     * use a function as below to reverse engineer the actual password
     *
     * @param
     * @return - string containing all 100,000 md5 permutations for a pin comprised of 5 digits
     */
     public static String convertByteArrToHex(byte [] arr){
        StringBuilder hex = new StringBuilder();
        for(byte b : arr){
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
    public String permuteAllMD5Hashes() throws NoSuchAlgorithmException {
        StringBuilder builder = new StringBuilder();
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e){
            throw new NoSuchAlgorithmException(e);
        }
        for(int f = 0; f < 10; f++){
            for(int g = 0; g < 10; g++){
                for(int h = 0; h < 10; h++){
                    for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                            byte[] arr = md.digest(("" + f + ""+ g + "" + h + "" + i + "" + j).getBytes());
                            builder.append("" + f +
                                           "" + g +
                                           "" + h +
                                           "" + i +
                                           "" + j +
                                           "," + convertByteArrToHex(arr) + "\n");
                            }
                        }
                    }
                }
            }
        return builder.toString();
    }

    /**
     *
     * @param hash - md5 hash
     * @return 5 digit pin, that when applied through md5 hash function, returns parameter str
     * @throws NoSuchAlgorithmException - if MD5 algorithm is not supported in the environment
     */
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

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        CrackThePin pins = new CrackThePin();
        FileWriter myWriter = new FileWriter("src\\Cryptography\\md5_rainbow_table.csv");
        myWriter.write("5 Digit PIN, MD5 HASH\n");
        myWriter.write(pins.permuteAllMD5Hashes());
        myWriter.close();

        System.out.println(pins.crack("827ccb0eea8a706c4c34a16891f84e7b")); // returns 12345
        System.out.println(pins.crack("86aa400b65433b608a9db30070ec60cd")); // returns 00078
    }
}
