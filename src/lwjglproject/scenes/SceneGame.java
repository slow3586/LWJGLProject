package lwjglproject.scenes;

import java.util.ArrayList;
import lwjglproject.App;
import lwjglproject.Keyboard;
import lwjglproject.Mouse;
import lwjglproject.TextToTexture;
import lwjglproject.entities.gui.Label;
import lwjglproject.logic.GameTerrain;
import lwjglproject.logic.GameTileMap;
import org.joml.Vector2i;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

public class SceneGame extends Scene {

    public static SceneGame ins = new SceneGame();
    Label l;
    
    @Override
    public void start() {
        /*
        l = new Label(null, "hi", TextToTexture.def);
        l.name = "LABEL";
        l.setPosL(new Vector2i(100,100));
        
        GameTerrain t = new GameTerrain(root);
        */
        GameTileMap m = new GameTileMap();
        for(int x=0; x<6; x++){
            m.setFree(new Vector2i(x, 4), false);
        }
        m.setFree(new Vector2i(6, 4), false);
        m.setFree(new Vector2i(6, 2), false);
        m.setFree(new Vector2i(6, 3), false);
        ArrayList<Vector2i> path = m.findPath(new Vector2i(0,0), new Vector2i(7,7));
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
            root.camera.setRotL(root.camera.getRotL().add(0, 0, Mouse.rel.x*App.delta));
        }
        //b.setRotL(b.getRotL().add(0, 0, 0.01f));
        movement.mul(10);
        movement.mul(App.delta);
        root.camera.getPosL().add(movement);
        l.setText(String.valueOf(App.fps));
        
        root.camera.look();
        
        if(Keyboard.isDown(GLFW_KEY_ESCAPE))
            App.close = true;
    }

    @Override
    public void reshape() {
    }

    @Override
    public void stop() {
    }

}
