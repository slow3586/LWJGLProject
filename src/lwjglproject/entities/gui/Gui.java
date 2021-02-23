package lwjglproject.entities.gui;

import lwjglproject.App;
import lwjglproject.entities.*;
import lwjglproject.gl.*;
import org.joml.*;

public class Gui {

    public static Gui ins = new Gui();
    
    public static Camera cam;
    public static Panel root;
    public static Vector2i defMargin = new Vector2i(5,5);
    public static Image fullscreenImage;
    
    private Gui() {
        cam = Camera.cam2D();
        
        root = new Panel(null);
        root.updateMatrix();
    }
    
    public static void draw(){
        root.draw();
    }

    public static void update(){
        cam.look();
        root.update();
    }
    
    public static void reshape(){
        cam.posL.y = -App.h;
        cam.orthoLeftBottom(1);
        
        FullscreenImage.ins.size = new Vector2i(App.w, App.h);
        FullscreenImage.ins.updateMatrix();
    }
    
}
