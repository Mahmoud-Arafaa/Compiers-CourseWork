package compailerProject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalizer {

    public static ArrayList<String> splitter(String Source) {
        ArrayList<String> Tokens = new ArrayList();
        String Regex = "(([a-zA-Z]+[0-9_]*[a-zA-Z]*)|(\\d*)\\.(\\d+)|(//.*)(\\s)|(/\\*[\\s\\S]*?\\*/)|(\\d+)|(\".+\")|('.+')|(==|>=|<=|!=|>>|<<|&&|\\|\\|)|([{}()=+\\-/*<>~#|&!;,\\[\\]]))";
        
        Pattern pattern = Pattern.compile(Regex);
        Matcher matcher = pattern.matcher(Source);

        while (matcher.find()) {
            Tokens.add(matcher.group());
        }
        return Tokens;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        BufferedReader in = new BufferedReader(new FileReader("InputFile.txt"));
        String str;

        List<String> list_of_lines = new ArrayList<>();

        while ((str = in.readLine()) != null) {
            list_of_lines.add(str);
        }
        String input = "";
        String[] stringArr = list_of_lines.toArray(new String[0]);
        for (int i = 0; i < stringArr.length; i++) {
            input += stringArr[i];
            if (i != stringArr.length - 1) {
                input += "\n";
            }
        }
        ArrayList Tokens = splitter(input);
        Pattern pattern;
        Matcher matcher;
        try (PrintWriter writer = new PrintWriter("OutputFile.txt", "UTF-8")) {
            for (int i = 0; i < Tokens.size(); i++) {
                for (Token t : Token.values()) {
                    pattern = Pattern.compile(t.getRegex());
                    matcher = pattern.matcher((String) Tokens.get(i));
                    if (matcher.find()) {
                        
                        writer.println("<" + t + "> : " + matcher.group() + "\n");
                        break;
                    }
                }
            }
        }
    }
}
