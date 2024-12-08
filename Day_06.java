import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day_06 {

    public static void main(String[] args){ new Day_06().Program(); }

    void Program(){
        String input = "";
        try{
            input = Files.readString((Paths.get("Day_06_input.txt")));
        } catch (IOException e){
            e.printStackTrace();
        }

        //PartOne(input);
        PartTwo(input);
    }

    void PartTwo(String input){
        int output = 0;

        String[] map = input.split("\\r?\\n");

        Vector2Int startPos = new Vector2Int(0,0);
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length(); x++) {
                if (map[y].charAt(x) == '^'){
                    startPos = new Vector2Int(x,y);
                }
            }
        }
        Vector2Int pos = startPos;

        List<Vector2Int> visited = new ArrayList<>();
        Vector2Int dir = new Vector2Int(0,-1);
        while (pos.x + dir.x >= 0 && pos.x + dir.x < map[0].length() &&
                pos.y + dir.y >= 0 && pos.y + dir.y < map.length){
            Vector2Int newPos = Vector2Int.add(pos, dir);
            if (map[newPos.y].charAt(newPos.x) == '#'){
                dir = new Vector2Int(-dir.y, dir.x);
            }
            else{
                if (!Vector2Int.contains(visited, pos))
                    visited.add(pos);
                pos = newPos;
            }
        }


        for (Vector2Int vis : visited){
            String[] newMap = map.clone();
            StringBuilder temp = new StringBuilder(newMap[vis.y]);
            temp.setCharAt(vis.x, '#');
            newMap[vis.y] = temp.toString();

            System.out.println("");
            System.out.println(Arrays.deepToString(newMap));


            List<Vector2Int> visited2 = new ArrayList<>();
            dir = new Vector2Int(0,-1);
            pos = startPos;
            Vector2Int lastPos = new Vector2Int(99999,99999);
            int fuckme = 0;
            while (pos.x + dir.x >= 0 && pos.x + dir.x < newMap[0].length() &&
                    pos.y + dir.y >= 0 && pos.y + dir.y < newMap.length){
                Vector2Int newPos = Vector2Int.add(pos, dir);
                if (newMap[newPos.y].charAt(newPos.x) == '#'){
                    dir = new Vector2Int(-dir.y, dir.x);
                }
                else{
                    if (fuckme>100000){
                        output++;
                        System.out.println("VALID");
                        break;
                    }
                    visited2.add(lastPos);
                    lastPos = pos;
                    pos = newPos;
                }
                fuckme++;
            }
        }


        System.out.println(pos.x + " " + pos.y);
        System.out.println(visited.size());
        System.out.println(output);
    }

    void PartOne(String input){
        int output = 0;

        String[] map = input.split("\\r?\\n");

        Vector2Int pos = new Vector2Int(0,0);
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length(); x++) {
                if (map[y].charAt(x) == '^'){
                    pos = new Vector2Int(x,y);
                }
            }
        }

        List<String> visited = new ArrayList<>();
        Vector2Int dir = new Vector2Int(0,-1);
        while (pos.x + dir.x >= 0 && pos.x + dir.x < map[0].length() &&
                pos.y + dir.y >= 0 && pos.y + dir.y < map.length){
            Vector2Int newPos = Vector2Int.add(pos, dir);
            if (map[newPos.y].charAt(newPos.x) == '#'){
                dir = new Vector2Int(-dir.y, dir.x);
            }
            else{
                String posAsString = pos.x + "," + pos.y;
                if (!visited.contains(posAsString))
                    visited.add(pos.x + "," + pos.y);
                pos = newPos;
            }
        }


        System.out.println(pos.x + " " + pos.y);
        System.out.println(visited.size() + 1);
        System.out.println(visited.get(0));
        System.out.println(output);
    }
}
