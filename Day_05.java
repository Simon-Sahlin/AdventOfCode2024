import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day_05 {

    public static void main(String[] args){ new Day_05().Program(); }

    void Program(){
        String input = "";
        try{
            input = Files.readString((Paths.get("Day_05_input.txt")));
        } catch (IOException e){
            e.printStackTrace();
        }

        //PartOne(input);
        PartTwo(input);
    }

    void PartOne(String input){
        int output = 0;

        String[] components = input.split("\\r?\\n\\r?\\n");
        String rules = components[0];
        String[] rawUpdates = components[1].split("\\r?\\n");
        String[][] updates = new String[rawUpdates.length][];
        for (int i = 0; i < rawUpdates.length; i++) {
            updates[i] = rawUpdates[i].split(",");
        }

        for (String[] update : updates){
            boolean valid = true;
            for (int i = 0; i < update.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (rules.contains(update[i] + "|" + update[j])){
                        valid = false;
                        break;
                    }
                }
                for (int j = i+1; j < update.length; j++) {
                    if (rules.contains(update[j] + "|" + update[i])){
                        valid = false;
                        break;
                    }
                }
            }
            if (valid)
                output += Integer.parseInt(update[update.length/2]);
        }

        System.out.println(output);
    }

    int findNewCenter(String rules, String[] update, int depth){
        for (int i = 0; i < update.length; i++) {
            for (int j = 0; j < i; j++) {
                if (rules.contains(update[i] + "|" + update[j])){
                    String temp = update[i];
                    update[i] = update[j];
                    update[j] = temp;
                    return findNewCenter(rules, update, 1);
                }
            }
            for (int j = i+1; j < update.length; j++) {
                if (rules.contains(update[j] + "|" + update[i])){
                    String temp = update[i];
                    update[i] = update[j];
                    update[j] = temp;
                    return findNewCenter(rules, update, 1);
                }
            }
        }
        if (depth != 0)
            return Integer.parseInt(update[update.length/2]);

        return 0;
    }

    void PartTwo(String input){
        int output = 0;

        String[] components = input.split("\\r?\\n\\r?\\n");
        String rules = components[0];
        String[] rawUpdates = components[1].split("\\r?\\n");
        String[][] updates = new String[rawUpdates.length][];
        for (int i = 0; i < rawUpdates.length; i++) {
            updates[i] = rawUpdates[i].split(",");
        }

        for (String[] update : updates){
            output += findNewCenter(rules, update, 0);
        }

        System.out.println(output);
    }
}
