package src.Text;

public class str {
    public static String simple_clean(String input, boolean oneWord){
        // clean input: lowercase it, remove extra spaces if OneWord
        input = input.toLowerCase();
        if(oneWord) input = input.replaceAll("\\s", "");
        return input;
    }
    public static String toTitleCase(String input){
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c) || c == '_') {  // did you know that " denotes string and ' denotes char? just wild
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c); // Safe for Unicode
                nextTitleCase = false;
            } else {
                c = Character.toLowerCase(c);
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
