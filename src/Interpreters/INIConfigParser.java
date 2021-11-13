package Interpreters;

//DESCRIPTION: https://www.codewars.com/kata/59d168926bddd2ff46000030


/**
 * In this kata, you'll have to parse an INI file, a common configuration format used by many applications.
 * In an INI file, config data is stored in key-value pairs with optional sections, like so:
 *
 * TRANSFORM INI FILE AS SUCH:
 * ****************
 * key1=value1   **
 * key2=value2   **
 *               **
 * [section]     **
 * key3=value3   **
 * key4=value4   **
 * ****************
 *
 * INTO:
 * ************************
 * {                     **
 *   "key1": "value1",   **
 *   "key2": "value2",   **
 *   "section": {        **
 *     "key3": "value3", **
 *     "key4": "value4"  **
 *   }                   **
 * }                     **
 * ************************
 *
 * Additionally, INI files also support comments, delimited by a semicolon at the start of a line:
 * i.e. "; this is a comment!!"
 *
 */
public class INIConfigParser implements ConfigParser {



    public INIConfigParser(){

    }

    public String parseConfig(){
        return "";
    }

    public static void main(String[] args) {

    }
}
