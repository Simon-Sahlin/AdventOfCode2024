import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day_08 {

    public static void main(String[] args){ new Day_08().Program(); }

    void Program(){
        String input = "";
        try{
            input = Files.readString((Paths.get("Day_08_input.txt")));
        } catch (IOException e){
            e.printStackTrace();
        }

        //PartOne(input);
        PartTwo(input);
    }

    void PartOne(String input){
        long output = 0;

        String[] lines = input.split("\\r?\\n");
        List<Vector2Int> positions = new ArrayList<>();
        List<String> antennas = new ArrayList<>();
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (lines[y].charAt(x) != '.'){
                    positions.add(new Vector2Int(x,y));
                    antennas.add(Character.toString(lines[y].charAt(x)));
                }
            }
        }

        List<Vector2Int> antinodes = new ArrayList<>();
        for (int i = 0; i < antennas.size(); i++) {
            for (int j = 0; j < antennas.size(); j++) {
                if (i==j) continue;
                if (antennas.get(i).equals(antennas.get(j))){
                    Vector2Int diff = Vector2Int.subtract(positions.get(j), positions.get(i));
                    Vector2Int antiPos = Vector2Int.add(positions.get(j), diff);
                    if (!Vector2Int.contains(antinodes, antiPos) && antiPos.x < lines[0].length() && antiPos.x >= 0 && antiPos.y < lines.length && antiPos.y >= 0){
                        antinodes.add(antiPos);
                    }
                }
            }
        }

        System.out.println(antinodes.size());
    }

    void PartTwo(String input){
        long output = 0;

        String[] lines = input.split("\\r?\\n");
        List<Vector2Int> positions = new ArrayList<>();
        List<String> antennas = new ArrayList<>();
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (lines[y].charAt(x) != '.'){
                    positions.add(new Vector2Int(x,y));
                    antennas.add(Character.toString(lines[y].charAt(x)));
                }
            }
        }

        List<Vector2Int> antinodes = positions;
        for (int i = 0; i < antennas.size(); i++) {
            for (int j = 0; j < antennas.size(); j++) {
                if (i==j) continue;
                if (antennas.get(i).equals(antennas.get(j))){
                    Vector2Int diff = Vector2Int.subtract(positions.get(j), positions.get(i));
                    Vector2Int antiPos = positions.get(j);
                    while (true){
                        antiPos = Vector2Int.add(antiPos, diff);
                        if (antiPos.x < lines[0].length() && antiPos.x >= 0 && antiPos.y < lines.length && antiPos.y >= 0){
                            if (!Vector2Int.contains(antinodes, antiPos))
                                antinodes.add(antiPos);
                        }
                        else
                            break;
                    }
                }
            }
        }

        System.out.println(antinodes.size());
    }
}
