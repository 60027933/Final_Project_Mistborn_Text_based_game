package src.Text;

public class str {
    public static String simple_clean(String input, boolean oneWord){
        // clean input: lowercase it, remove extra spaces if OneWord
        input = input.toLowerCase();
        if(oneWord) input = input.replaceAll("\\s", "");
        return input;
    }
}
