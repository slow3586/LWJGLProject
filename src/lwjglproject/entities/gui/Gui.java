package lwjglproject.entities.gui;

import lwjglproject.App;
import lwjglproject.entities.*;
import lwjglproject.gl.*;
import org.joml.*;

public final class Gui extends World {

    public static Gui ins = new Gui();
    public static Vector2i defMargin = new Vector2i(5,5);
    public static Image fullscreenImage;
    
    protected Gui() {
        camera = Camera.cam2D();
        
        super.updateMatrix();
    }

    @Override
    public void update(){
        camera.look();
        super.update();
    }
    
    public void reshape(){
        camera.posL.y = -App.h;
        camera.orthoLeftBottom(1);
        
        FullscreenImage.ins.size = new Vector2i(App.w, App.h);
        FullscreenImage.ins.updateMatrix();
    }
    
}
