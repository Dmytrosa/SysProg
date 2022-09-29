import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class Automat {
    public int state = 0;
    ArrayList<Integer> final_states = new ArrayList<Integer>();
    Map<Integer, ArrayList<Transition>> transitions = new HashMap<Integer, ArrayList<Transition>>();

    private static class Transition {
        public char ch;
        public int to;

        Transition(char ch, int to) {
            this.ch = ch;
            this.to = to;
        }
    }

    public boolean hasNextState(char ch) {
        var current_state_transactions = transitions.get(state);
        boolean contain = false;
        for (var transaction : current_state_transactions) {
            if (transaction.ch == ch) {
                contain = true;
                break;
            }
        }

        if (!contain) {
            return false;
        }

        return true;
    }

    public static Automat InitAutomat(String str) {
        var automat = new Automat();
        var input = str.split("\n");
        automat.state = Integer.parseInt(input[2]);
        var final_states = input[3].split(" ");
        for (var state : final_states) {
            automat.final_states.add(Integer.parseInt(state));
        }
        for (int i = 4; i < input.length; ++i) {
            var line = input[i].split(" ");
            int from = Integer.parseInt(line[0]);
            char ch = line[1].charAt(0);
            int to = Integer.parseInt(line[2]);
            automat.transitions.putIfAbsent(from, new ArrayList<Transition>());
            automat.transitions.get(from).add(new Transition(ch, to));
        }
        return automat;
    }

}

public class Main {
    private static final String PATH = "test.txt";

    public static void main(String[] args) {
        String content;
        try {
            content = Files.readString(Paths.get(PATH), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Automat automat = Automat.InitAutomat(content);
        String letters = "abcdifgh";
        ArrayList ans = new ArrayList();
        for (int i = 0; i < letters.length(); i++) {
            Character ch = letters.charAt(i);
            if (!automat.hasNextState(ch)) {
                ans.add(ch);
            }
        }

        System.out.println("\nDoesn't accept: " + ans);
    }
}
