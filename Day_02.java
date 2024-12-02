import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day_02 {

    public static void main(String[] args){ new Day_02().Program(); }

    void Program(){
        String input = "";
        try{
            input = Files.readString((Paths.get("Day_02_input.txt")));
        } catch (IOException e){
            e.printStackTrace();
        }

        //PartOne(input);
        PartTwo(input);
    }

    int[][] ParseInput(String input){
        String[] lines = input.split("[\\r\\n]+");
        System.out.println(Arrays.toString(lines));

        int[][] numbers = new int[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split("\\s+");
            numbers[i] = new int[line.length];
            for (int j = 0; j < line.length; j++) {
                numbers[i][j] = Integer.parseInt(line[j]);
            }
        }
        return numbers;
    }

    void PartOne(String input){
        int[][] numbers = ParseInput(input);
        int output = 0;

        for (int[] nums : numbers){
            int dif = nums[1] - nums[0];
            if (dif == 0)
                continue;
            int op = dif / Math.abs(dif);
            boolean valid = true;
            for (int i = 1; i < nums.length; i++) {
                dif = nums[i] - nums[i-1];
                if (!(dif * op > 0 && Math.abs(dif) <= 3)){
                    valid = false;
                    break;
                }
            }
            if (valid)
                output++;
        }


        System.out.println(Arrays.deepToString(numbers));
        System.out.println(output);
    }

    void PartTwo(String input){
        int[][] numbers = ParseInput(input);
        int output = 0;

        for (int[] nums : numbers){
            System.out.println(Arrays.toString(nums));
            int dif = nums[1] - nums[0];
            if (dif == 0)
                continue;
            int op = dif / Math.abs(dif);
            int valid = 0;
            for (int i = 1; i < nums.length; i++) {
                dif = nums[i] - nums[i-1];
                //System.out.println("here3 " + dif + " : i-" + i);
                if (!(dif * op > 0 && Math.abs(dif) <= 3)){
                    if (i+1 > nums.length-1){
                        valid++;
                        //System.out.println("here1");
                        continue;
                    }
                    dif = nums[i+1] - nums[i-1];
                    //System.out.println("here4 " + dif);
                    if (!(dif * op > 0 && Math.abs(dif) <= 3)){
                        valid += 100;
                    }else {
                        i++;
                        //System.out.println("here2");
                        valid++;
                    }
                }
            }
            if (valid <= 1){
                output++;
                System.out.println("Valid : " + valid);
            }
            else{
                System.out.println("INValid : " + valid);

            }

        }


        System.out.println(Arrays.deepToString(numbers));
        System.out.println(output);
    }
}
