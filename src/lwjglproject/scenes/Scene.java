package lwjglproject.scenes;

import lwjglproject.entities.Entity;

public abstract class Scene {
    private static Scene current=null;
    public Entity root = new Entity(null);
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
    
    public static Scene getCurrent(){
        if(current == null){
            throw new IllegalStateException("CURRENT SCENE IS NULL");
        }
        
        return current;
    }
}

