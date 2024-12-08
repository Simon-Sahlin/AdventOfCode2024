import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day_07 {

    public static void main(String[] args){ new Day_07().Program(); }

    void Program(){
        String input = "";
        try{
            input = Files.readString((Paths.get("Day_07_input.txt")));
        } catch (IOException e){
            e.printStackTrace();
        }

        PartOne(input);
        //PartTwo(input);
    }

    List<Long> getCombinations(List<Long> list, int[] nums){
        if (nums.length == 0)
            return list;

        List<Long> newResults = new ArrayList<>();

        for (Long item : list){
            newResults.add((long) (item + nums[0]));
            newResults.add((long) (item * (nums[0] > 0 ? nums[0] : 1)));
            newResults.add(Long.parseLong(item.toString() + nums[0]));
        }

        newResults = getCombinations(newResults, Arrays.copyOfRange(nums, 1, nums.length));

        return newResults;
    }

    void PartOne(String input){
        long output = 0;

        String[] lines = input.split("\\r?\\n");
        for (String line : lines){
            long result = Long.parseLong(line.split(":")[0]);
            String[] numsRaw = line.split(": ")[1].split(" ");
            int[] nums = new int[numsRaw.length];
            System.out.println("line " + line);
            System.out.println("raw nums " + Arrays.toString(numsRaw));
            for (int i = 0; i < numsRaw.length; i++) {
                nums[i] = Integer.parseInt(numsRaw[i]);
            }

            List<Long> startValue = new ArrayList<>();
            startValue.add((long) nums[0]);
            int[] startNums = Arrays.copyOfRange(nums, 1, nums.length);
            List<Long> combinations = getCombinations(startValue, startNums);
            System.out.println(Arrays.toString(combinations.toArray()));

            if (combinations.contains(result))
                output += result;
        }

        System.out.println(output);
    }
}
