package lwjglproject.logic;

import java.util.ArrayList;
import org.joml.Vector2f;
import org.joml.Vector2i;



public class GameTileMap {
    boolean[][] free;
    int size = 8;
    Vector2i[] directions = new Vector2i[]{
        new Vector2i(1,0),new Vector2i(1,1),new Vector2i(0,1),
        new Vector2i(-1,1),new Vector2i(-1,0),new Vector2i(-1,-1),
        new Vector2i(0,-1),new Vector2i(1,-1)};
    
    public GameTileMap() {
        free =new boolean[size][size];
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                free[x][y]=true;
            }
        }
    }
    
    public void setFree(Vector2i p, boolean b){
        if(doesTileExist(p)) free[p.y][p.x] = b;
    }
    
    public boolean doesTileExist(Vector2i p){
        return (p.x>=0 && p.y>=0 && p.x<size && p.y<size);
    }
    
    public boolean isTileFree(Vector2i p){
        return doesTileExist(p) && free[p.y][p.x];
    }
    
    public Vector2i getClosestNeighboringTile(Vector2i s, Vector2i e, ArrayList<Vector2i> ignore){
        double d = e.distance(s);
        Vector2i closest = null;
        double d1 = 0;
        for(Vector2i dir : directions){
            Vector2i tile = new Vector2i(s).add(dir);
            if(!isTileFree(tile)) continue;
            if(ignore.stream().anyMatch((t) -> {
                return tile.equals(t);
            })) continue;
            
            if(closest==null) {
                closest = tile;
                d = closest.distance(e);
                continue;
            }
            d1 = tile.distance(e);
            if(d1 < d){
                closest = tile;
                d = d1;
            }
        }
        return closest;
    }
    
    public ArrayList<Vector2i> findPath(Vector2i s, Vector2i e){
        if(!isTileFree(s) || !isTileFree(e)) return null;
        
        Vector2i currentTile = s;
        ArrayList<Vector2i> passedTiles = new ArrayList<>();
        ArrayList<Vector2i> currentPath = new ArrayList<>();
        int backtrack = 0;
        passedTiles.add(currentTile);
        
        while(!currentTile.equals(e)){
                String tileString="";
            for(int y=0; y<size; y++){
                for(int x=0; x<size; x++){
                    Vector2i p = new Vector2i(x,y);
                    tileString="_";
                    if(!isTileFree(p)) tileString="X";
                    if(currentTile.equals(p)) tileString="+";
                    if(p.equals(e)) tileString="D";
                    for (Vector2i passedTile : passedTiles) {
                        if(p.equals(passedTile)) {
                            tileString="-";
                            break;
                        }
                    }
                    for (Vector2i t : currentPath) {
                        if(p.equals(t)) {
                            tileString="^";
                            break;
                        }
                    }
                    System.out.print(tileString);
                }
                System.out.println("");
            }
            System.out.println("");
            Vector2i c = getClosestNeighboringTile(currentTile, e, passedTiles);
            if(c!=null){
                currentTile = c;
                passedTiles.add(currentTile);
                currentPath.add(currentTile);
                backtrack = 0;
            }else{
                backtrack -= 1;
                if(currentPath.size()-backtrack<=0) return null;
                currentPath.remove(currentTile);
                currentTile = currentPath.get(currentPath.size()-backtrack);
            }
        }
        return currentPath;
    }
    
}
