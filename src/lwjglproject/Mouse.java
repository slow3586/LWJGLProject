package lwjglproject;

import java.util.*;
import org.joml.*;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse {
    public static Mouse ins = new Mouse();
    
    public static Vector2i pos = new Vector2i();
    public static Vector2i rel = new Vector2i();
    public static List<Integer> pressed = new ArrayList<>();
    public static List<Integer> released = new ArrayList<>();
    public static List<Integer> down = new ArrayList<>();
    public static List<Integer> consumed = new ArrayList<>();
    public static Boolean consumedMouseOver = false;
    public static Boolean moved = false;
    public static Float wheel = 0f;
    public static Boolean ignoreOnce = true;
    
    private Mouse(){}
    
    public static void reset() {
        moved = false;
        rel.zero();
        pressed.clear();
        released.clear();
        consumed.clear();
        consumedMouseOver = false;
        wheel = 0f;
    }

    public static final Short LEFT = GLFW_MOUSE_BUTTON_LEFT;
    public static final Short MIDDLE = GLFW_MOUSE_BUTTON_MIDDLE;
    public static final Short RIGHT = GLFW_MOUSE_BUTTON_RIGHT;
    
    public static boolean isDown(Integer key){
        return down.contains(key);
    }
    
    public static boolean isPressed(Integer key){
        return pressed.contains(key);
    }
    
    public static boolean isReleased(Integer key){
        return released.contains(key);
    }

    public static void onMouseMove(long window, double xpos, double ypos){
        if(ignoreOnce){
            ignoreOnce=false;
        }else{
            rel.x = (int) (pos.x - xpos);
            rel.y = (int) (pos.y - ypos);
        }
        pos.x = (int) xpos;
        pos.y = (int) ypos;
        moved=true;
    }
    
    public static void onMouseButtonPress(long window, int key, int action, int mods){
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
    
    public static void onMouseEnter(long window, boolean entered){
        if(!entered){
            ignoreOnce=true;
        }
    }
    
}
