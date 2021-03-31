package lwjglproject.scenes;

import lwjglproject.entities.Camera;
import lwjglproject.entities.Entity;
import lwjglproject.entities.World;

public abstract class Scene {
    private static Scene current=null;
    public World root = new World(Camera.cam3D());
    abstract public void start();
    abstract public void update();
    abstract public void reshape();
    abstract public void stop();
    
    public static void setScene(Scene s){
        if(current!=null){
            current.stop();
        }
        current = s;
        current.start();
    }
    
    public static boolean isNull(){
        return current==null;
    }
    
    public static Scene getCurrent(){
        if(current == null){
            throw new IllegalStateException("CURRENT SCENE IS NULL");
        }
        
        return current;
    }
}

