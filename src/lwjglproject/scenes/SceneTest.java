package lwjglproject.scenes;

import java.io.File;
import lwjglproject.App;
import lwjglproject.Keyboard;
import lwjglproject.Mouse;
import lwjglproject.TextToTexture;
import lwjglproject.entities.Camera;
import lwjglproject.entities.LineGen;
import lwjglproject.entities.gui.Gui;
import lwjglproject.entities.gui.Label;
import lwjglproject.entities.primitives.Box;
import lwjglproject.scenes.Scene;
import lwjglproject.entities.primitives.Plane;
import lwjglproject.gl.Texture;
import lwjglproject.gl.materials.MaterialTexture;
import org.joml.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class SceneTest extends Scene {

    public static SceneTest ins = new SceneTest();
    Plane p;
    Box b;
    Texture t;
    Label l;
    
    @Override
    public void start() {
        
        LineGen lg = new LineGen(Gui.ins);
        lg.points.add(new LineGen.LinePoint(new Vector2f(50,-50), 10f, new Vector4f(1,0,0,1)));
        //lg.points.add(new LineGen.LinePoint(new Vector2f(50,-50), 10f, new Vector4f(1,0,0,1)));
        lg.points.add(new LineGen.LinePoint(new Vector2f(200,-50), 10f, new Vector4f(1,0,0,1)));
        lg.points.add(new LineGen.LinePoint(new Vector2f(200,-200), 10f, new Vector4f(1,0,0,1)));
        lg.points.add(new LineGen.LinePoint(new Vector2f(50,-200), 10f, new Vector4f(1,0,0,1)));
        //lg.points.add(new LineGen.LinePoint(new Vector2f(50,-50), 10f, new Vector4f(1,0,0,1)));
        lg.loops=true;
        lg.needsRender = true;
        
        b = new Box(root);
        b.scaleL = new Vector3f(10,10,10);
        b.updateMatrix();
        l = new Label(null, "hi", TextToTexture.def);
        l.posL = new Vector2i(100,100);
        l.updateMatrix();
        
        t = Texture.fromFile(new File("img.png"));
        MaterialTexture m = new MaterialTexture(t);
        //t.setAsFullscreenImage();
        b.forEachMesh((t) -> {
            t.setMaterial(m);
        });
        
        Gui.ins.reshape();
        root.camera.posL= new Vector3f(0,-10,0);
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
            root.camera.rotL.z+=Mouse.rel.x*App.delta;
        }
        b.rotL.z+=0.01f;
        b.updateMatrix();
        movement.mul(10);
        movement.mul(App.delta);
        root.posL.add(movement);
        l.setText(String.valueOf(App.fps));
        
        root.camera.look();
        
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
