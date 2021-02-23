package lwjglproject.entities.gui;

import lwjglproject.entities.*;
import static lwjglproject.entities.gui.PanelRect.panelVA;
import lwjglproject.gl.*;
import lwjglproject.gl.shaders.SPSolidColor;
import lwjglproject.gl.shaders.SPTexture;
import org.joml.*;

public class FullscreenImage extends Panel {

    public static FullscreenImage ins = new FullscreenImage();
    public static Texture texture = null;
    
    private FullscreenImage() {
        super(Gui.root);
    }

    @Override
    public void draw() {
        if(!visible) return;
        if(texture!=null){
            SPTexture.draw(Gui.cam.mat, mat, texture, panelVA);
            //SPSolidColor.draw(Gui.cam.mat, mat, new Vector4f(1,0,0,1), panelVA);
        }
    }

}
