package lwjglproject.scenes;

import java.io.File;
import lwjglproject.App;
import lwjglproject.Keyboard;
import lwjglproject.Mouse;
import lwjglproject.TextToTexture;
import lwjglproject.entities.Camera;
import lwjglproject.entities.prim2d.Line2D;
import lwjglproject.entities.gui.Gui;
import lwjglproject.entities.gui.Label;
import lwjglproject.entities.gui.PanelRect;
import lwjglproject.entities.prim3d.Box;
import lwjglproject.scenes.Scene;
import lwjglproject.entities.prim3d.Plane;
import lwjglproject.gl.Texture;
import lwjglproject.gl.materials.MaterialTexture;
import org.joml.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class SceneEditor extends Scene {

    public static SceneEditor ins = new SceneEditor();
    
       PanelRect editSpace;
       PanelRect menuPanel;
       int space = 16;
       PanelRect cursor;
       int cursorSize = 16;
       PanelRect drawLine;
       
       
    @Override
    public void start() {
        menuPanel = new PanelRect(null);
        menuPanel.setPosL(0,0);
        menuPanel.setSize(640,100);
        menuPanel.color = new Vector4f(0.8f,0.8f,0.8f,1);
        
        editSpace = new PanelRect(null);
        editSpace.setPosL(0,100);
        editSpace.setSize(640,300);
        editSpace.color = new Vector4f(1,1,1,1);

        cursor = new PanelRect(null);
        cursor.setSize(cursorSize, cursorSize);
        cursor.color = new Vector4f(1,0,0,1);
        cursor.visible = false;
        
        drawLine = new PanelRect(null);
        drawLine.color = new Vector4f(1,0,0,1);
        drawLine.visible = false;
    }
    
    public void isInside(){
        
    }

    boolean isDrawing=false;
    Vector2i dp0 = new Vector2i();
    Vector2i dp1 = new Vector2i();
    @Override
    public void update() {
        
        Vector2i esp = editSpace.getPosGInt();
        
        int x = Mouse.pos.x - esp.x;
        int y = Mouse.pos.y - esp.y;
        
        Vector2i mgp = new Vector2i((x / space), (y / space));
        
        int jx = mgp.x * space + esp.x;
        int jy = mgp.y * space + esp.y;
        
        cursor.visible=false;
        if(editSpace.isInside(new Vector2i(jx,jy))){
            cursor.visible=true;
        }
        
        cursor.setPosL(jx, jy);
        
        if(Mouse.isDown(Mouse.LEFT)){
            if(!isDrawing){
                isDrawing = true;
                dp0 = new Vector2i(mgp);
                drawLine.setPosL(dp0);
            }
            dp1 = new Vector2i(mgp);
            if(java.lang.Math.abs(jx)>=java.lang.Math.abs(jy))
                dp1.y = dp0.y;
            else
                dp1.x = dp0.x;
            drawLine.setSize(new Vector2i(gpToSp(dp1)).sub(gpToSp(dp0)));
            drawLine.visible = true;
        }else{
            dp0 = new Vector2i(0,0);
            dp1 = new Vector2i(0,0);
            drawLine.setPosL(0, 0);
            drawLine.setSize(0, 0);
            drawLine.visible = false;
            isDrawing = false;
        }
        
        if(Keyboard.isDown(GLFW_KEY_ESCAPE))
            App.close = true;
    }
    
    public Vector2i gpToSp(Vector2i v){
        v.mul(space);
        return v;
    }

    @Override
    public void reshape() {
        System.out.println("reshpae");
    }

    @Override
    public void stop() {
    }

}
