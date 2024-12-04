import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_04 {

    public static void main(String[] args){ new Day_04().Program(); }

    void Program(){
        String input = "";
        try{
            input = Files.readString((Paths.get("Day_04_input.txt")));
        } catch (IOException e){
            e.printStackTrace();
        }

        //PartOne(input);
        PartTwo(input);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //Don't look here, this is by FAR the worst solution this year. I just didn't have the energy...//
    //////////////////////////////////////////////////////////////////////////////////////////////////

    void PartOne(String input){
        int output = 0;

        String[] lines = input.split("[\\r\\n]+");
        int width = lines[0].length() + 2;
        System.out.println(width);

        int max = input.length()+1;
        for (int i = 0; i < input.length(); i++) {
            boolean[] tests = {
                    (i+4<max && input.substring(i, i+4).equals("XMAS")),
                    (i+4<max && input.substring(i, i+4).equals("SAMX")),
                    (width*3+i<max && input.charAt(i) == 'X' && input.charAt(i+width) == 'M' && input.charAt(i+width*2) == 'A' && input.charAt(i+width*3) == 'S'),
                    (width*3+i<max && input.charAt(i) == 'S' && input.charAt(i+width) == 'A' && input.charAt(i+width*2) == 'M' && input.charAt(i+width*3) == 'X'),
                    ((width+1)*3+i<max && input.charAt(i) == 'X' && input.charAt(i+width+1) == 'M' && input.charAt(i+(width+1)*2) == 'A' && input.charAt(i+(width+1)*3) == 'S'),
                    ((width+1)*3+i<max && input.charAt(i) == 'S' && input.charAt(i+width+1) == 'A' && input.charAt(i+(width+1)*2) == 'M' && input.charAt(i+(width+1)*3) == 'X'),
                    ((width-1)*3+i<max && input.charAt(i) == 'X' && input.charAt(i+width-1) == 'M' && input.charAt(i+(width-1)*2) == 'A' && input.charAt(i+(width-1)*3) == 'S'),
                    ((width-1)*3+i<max && input.charAt(i) == 'S' && input.charAt(i+width-1) == 'A' && input.charAt(i+(width-1)*2) == 'M' && input.charAt(i+(width-1)*3) == 'X')
            };
            for (boolean test : tests){
                if (test)
                    output++;
            }
        }

        System.out.println(output);
    }


    void PartTwo(String input){
        int output = 0;

        String[] lines = input.split("[\\r\\n]+");
        int width = lines[0].length() + 2;

        int max = input.length()+1;
        for (int i = 0; i < input.length(); i++) {
            boolean[] tests = {
                    ((width+1)*2+i<max && input.charAt(i) == 'M' && input.charAt(i+2) == 'M' && input.charAt(i+width+1) == 'A' && input.charAt(i+width*2) == 'S' && input.charAt(i+(width+1)*2) == 'S'),
                    ((width+1)*2+i<max && input.charAt(i) == 'M' && input.charAt(i+2) == 'S' && input.charAt(i+width+1) == 'A' && input.charAt(i+width*2) == 'M' && input.charAt(i+(width+1)*2) == 'S'),
                    ((width+1)*2+i<max && input.charAt(i) == 'S' && input.charAt(i+2) == 'M' && input.charAt(i+width+1) == 'A' && input.charAt(i+width*2) == 'S' && input.charAt(i+(width+1)*2) == 'M'),
                    ((width+1)*2+i<max && input.charAt(i) == 'S' && input.charAt(i+2) == 'S' && input.charAt(i+width+1) == 'A' && input.charAt(i+width*2) == 'M' && input.charAt(i+(width+1)*2) == 'M')
            };
            for (boolean test : tests){
                if (test)
                    output++;
            }
        }

        System.out.println(output);
    }
}
