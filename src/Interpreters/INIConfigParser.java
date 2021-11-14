package Interpreters;

/**
 * DESCRIPTION: https://www.codewars.com/kata/59d168926bddd2ff46000030
 *
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

    public enum Token{
        LEFT_ANGLE_BRACKET('['),  //for [section] block
        RIGHT_ANGLE_BRACKET(']'), //for [section] block
        EQUAL_SIGN('='),          //for key:value
        COMMENT(';');

        private char token;
        private Token(char ch){
            this.token = ch;
        }

        public char getToken(){
            return this.token;
        }
    }

    public class SyntaxException extends Exception{
        public SyntaxException(String error){ super(error); }
    }

    private int lineNumber;
    private String[] iniStrings;

    public INIConfigParser(String iniString){
        this.iniStrings = iniString.split("\n");
        lineNumber = 1;
    }

    private boolean isValidSectionHeader(String str){
        return (str.charAt(0) == Token.LEFT_ANGLE_BRACKET.getToken()
                &&  str.charAt(str.length()-1) == Token.RIGHT_ANGLE_BRACKET.getToken());
    }

    private boolean isValidComment(String str){
        return str.charAt(0) == Token.COMMENT.getToken();
    }

    /**NOTE: return value of parseConfig() could be a custom wrapper over HashMap
     * so user of this parser can interface with key values in their application without
     * relying on string parsing.
     * The current transformation from ini format to dictionary-like format
     * serves no usability to user's application
     */
    //TODO: refactor name to toString() and create real parser function that adds ini entries as dict values
    // creating this real parser function cannot be elegantly done in Java as there is no support for
    // heterogeneous dictionaries (i.e. dictionaries that can contained nested and non-nested entries)
    // due to strongly-typed nature of this language. This exercise is better suited either Python or Javascript
    public String parseConfig() throws SyntaxException{
        int curlyBracketCounter = 0; //used to determine if there exists uneven curly pairs
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < this.iniStrings.length; i++){
            if(!this.iniStrings[i].equals("")){
                if(isValidSectionHeader(this.iniStrings[i])){
                    builder.append("\"" + this.iniStrings[i].substring(1, this.iniStrings[i].length() - 1) + "\": {\n");
                    curlyBracketCounter++;
                }
                else if(this.iniStrings[i].contains("" + Token.EQUAL_SIGN.getToken())){
                    String[] mapValue = this.iniStrings[i].split("" + Token.EQUAL_SIGN.getToken());
                    //FIXME: stripping commas on last key:value
                    builder.append("\"" + mapValue[0] + "\"" + ": \"" + mapValue[1] + "\",\n");
                }
                else{
                    if(!isValidComment(this.iniStrings[i]))
                        throw new SyntaxException("Parsing Failed!\nRectify syntax on line " + lineNumber);
                }
                lineNumber++;
            }
            else{
                if(builder.toString().contains("{")){
                    builder.append("\t},\n");
                    curlyBracketCounter--;
                }
            }
        }

        if(curlyBracketCounter == 1) { //meaning a left curly bracket was added without matching right bracket
            builder.append("\t}\n");
        }
        //adding overall brackets
        builder.insert(0,"{\n");
        builder.append("}");
        return builder.toString();
    }

    public static void main(String[] args) throws SyntaxException {
        String iniConfig1 =
                "; This is an example of an ini config file!\n" +
                "foo1=bar1\n" +
                "foo2=bar2\n" +
                "\n" +
                "[foobar]\n" +
                "foo3=bar3\n" +
                "foo4=bar4\n";

        String iniConfig2 =
                "[section1]\n" +
                "foo=bar\n" +
                "\n" +
                "[section2]\n" +
                "lorem=ipsum";
        INIConfigParser parser1 = new INIConfigParser(iniConfig1);
        System.out.println(parser1.parseConfig());
        /**
         * RETURNS:
         * {
         * "foo1": "bar1",
         * "foo2": "bar2",
         * "foobar": {
         * "foo3": "bar3",
         * "foo4": "bar4",
         *        }
         * }
         */
        System.out.println("\n");
        INIConfigParser parser2 = new INIConfigParser(iniConfig2);
        System.out.println(parser2.parseConfig());
        /**
         * RETURNS:
         * {
         * "section1": {
         * "foo": "bar",
         *        },
         * "section2": {
         * "lorem": "ipsum",
         *    }
         * }
         *
         */
    }
}
