package lwjglproject;

import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.glfw.GLFW.*;

public class Keyboard{

    public static Keyboard ins = new Keyboard();
    
    public static List<Integer> pressed = new ArrayList<>();
    public static List<Integer> released = new ArrayList<>();
    public static List<Integer> down = new ArrayList<>();
    public static String typed = "";

    private Keyboard(){}
    
    public static void reset() {
        released.clear();
        pressed.clear();
        typed = "";
    }
    
    public static boolean isDown(Integer key){
        return down.contains(key);
    }
    
    public static boolean isPressed(Integer key){
        return pressed.contains(key);
    }
    
    public static boolean isReleased(Integer key){
        return released.contains(key);
    }
    
    public static void onKeyPress(long window, int key, int scancode, int action, int mods){
        if(action == GLFW_PRESS){
            pressed.add(key);
            down.add(key);
        }
        else if(action == GLFW_RELEASE){
            released.add(key);
            pressed.remove((Integer)key);
            down.remove((Integer)key);
        }
    }
}