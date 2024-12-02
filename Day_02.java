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
            boolean valid = validNums(nums, 0);
            if (valid)
                output++;
        }


        System.out.println(output);
    }

    boolean validNums(int[] nums, int depth){

        int opDif = nums[1] - nums[0];
        int op = 0;
        if (opDif != 0)
            op = opDif / Math.abs(opDif);

        for (int i = 1; i < nums.length; i++) {
            int dif = nums[i] - nums[i-1];
            if (!(dif * op > 0 && Math.abs(dif) <= 3)){
                if (depth > 0)
                    return false;
                else{
                    boolean valid = false;
                    for (int j = 0; j < nums.length; j++) {
                        valid = validNums(removeElement(nums, j), 1);
                        if (valid){
                            return true;
                        }
                    }
                    return valid;
                }
            }
            opDif = nums[1] - nums[0];
            op = 0;
            if (opDif != 0)
                op = opDif / Math.abs(opDif);
        }

        return true;
    }


    int[] removeElement(int[] nums, int remove){
        int[] newNums = new int[nums.length - 1];
        for (int i = 0, k = 0; i < nums.length; i++) {
            if (i != remove){
                newNums[k] = nums[i];
                k++;
            }
        }
        return newNums;
    }
}
