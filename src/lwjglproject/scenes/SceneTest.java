package lwjglproject.scenes;

import lwjglproject.App;
import lwjglproject.Keyboard;
import lwjglproject.Mouse;
import lwjglproject.entities.Camera;
import lwjglproject.entities.primitives.Box;
import lwjglproject.scenes.Scene;
import lwjglproject.entities.primitives.Plane;
import org.joml.*;
import static org.lwjgl.glfw.GLFW.*;

public class SceneTest extends Scene {

    public static SceneTest ins = new SceneTest();
    Plane p;
    Box b;
    @Override
    public void start() {
        b = new Box(root);
        b.scaleL = new Vector3f(10,10,10);
        b.updateMatrix();
        Camera.main.posL= new Vector3f(0,-10,0);
    }

    @Override
    public void update() {
        Vector3f movement = new Vector3f();
        if(Keyboard.isDown(GLFW_KEY_W)){
            movement.y+=1;
        }
        if(Keyboard.isDown(GLFW_KEY_S)){
            movement.y-=1;
        }
        if(Keyboard.isDown(GLFW_KEY_A)){
            movement.x-=1;
        }
        if(Keyboard.isDown(GLFW_KEY_D)){
            movement.x+=1;
        }
        if(Keyboard.isDown(GLFW_KEY_SPACE)){
            movement.z+=1;
        }
        if(Keyboard.isDown(GLFW_KEY_LEFT_CONTROL)){
            movement.z-=1;
        }
        if(Mouse.moved){
            //Camera.main.rotL.x+=Mouse.rel.y*0.01f;
            Camera.main.rotL.z+=Mouse.rel.x*0.01f;
        }
        b.rotL.z+=0.01f;
        b.updateMatrix();
        Camera.main.posL.add(movement);
        Camera.main.look();
        root.draw();
        
        if(Keyboard.isDown(GLFW_KEY_ESCAPE))
            App.close = true;
    }

    @Override
    public void reshape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop() {
    }

}
