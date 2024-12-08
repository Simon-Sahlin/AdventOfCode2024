import java.util.List;

public class Vector2Int {

    public int x;
    public int y;

    public Vector2Int(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Vector2Int add(Vector2Int a, Vector2Int b){
        return new Vector2Int(a.x+b.x,a.y+b.y);
    }

    public static Vector2Int subtract(Vector2Int a, Vector2Int b){
        return new Vector2Int(a.x-b.x,a.y-b.y);
    }

    public static boolean contains(List<Vector2Int> list, Vector2Int look){
        for (Vector2Int item : list){
            if (item.x == look.x && item.y == look.y)
                return true;
        }
        return false;
    }
}
