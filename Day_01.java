import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day_01 {

    public static void main(String[] args){ new Day_01().Program(); }

    void Program(){
        String input = "";
        try{
            input = Files.readString((Paths.get("Day_01_input.txt")));
        } catch (IOException e){
            e.printStackTrace();
        }

        //PartOne(input);
        PartTwo(input);
    }

    int[][] ParseInput(String input){
        String[] lines = input.split("[\\r\\n]+");
        System.out.println(Arrays.toString(lines));

        int[] lNumbers = new int[lines.length];
        int[] rNumbers = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split("\\s+");
            lNumbers[i] = Integer.parseInt(line[0]);
            rNumbers[i] = Integer.parseInt(line[1]);
        }
        return new int[][]{lNumbers, rNumbers};
    }

    void PartOne(String input){
        int[][] numbers = ParseInput(input);

        Arrays.sort(numbers[0]);
        Arrays.sort(numbers[1]);

        int output = 0;
        for (int i = 0; i < numbers[0].length; i++) {
            output += Math.abs(numbers[0][i] - numbers[1][i]);
        }

        System.out.println(output);
    }

    void PartTwo(String input){
        int[][] numbers = ParseInput(input);
        int output = 0;
        for (int lNum : numbers[0]){
            for (int rNum : numbers[1]){
                if (lNum == rNum)
                    output += lNum;
            }
        }
        System.out.println(output);
    }
}
