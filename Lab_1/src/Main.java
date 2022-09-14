import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private final static String PATH = "Test.txt";

    private final static int MAX_LENGTH = 30;

    private static final String DELIMETERS_REGEX = "[ |\t\n()+\\-,!?.:\"']";

    public static void main(String[] args) {
        String content;
        try {
            content = Files.readString(Paths.get(PATH), StandardCharsets.US_ASCII );
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        var words = content.split(DELIMETERS_REGEX);
        Map<String, Integer> table = new HashMap<String, Integer>();
        int maxLength = 0;
        for (String word : words)
        {
            if (!word.isEmpty()) {
                if (word.length() > MAX_LENGTH) {
                    word = word.substring(0, MAX_LENGTH);
                }
                if (word.length() > maxLength) {
                    table = new HashMap<String, Integer>();
                    maxLength = word.length();
                }
                if (word.length() == maxLength) {
                    table.putIfAbsent(word, 1);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : table.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}