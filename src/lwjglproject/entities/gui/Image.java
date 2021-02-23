package lwjglproject.entities.gui;

import lwjglproject.entities.*;
import static lwjglproject.entities.gui.PanelRect.panelVA;
import lwjglproject.gl.*;
import lwjglproject.gl.shaders.SPTexture;
import org.joml.*;

public class Image extends Panel {

    private Texture texture = null;

    public Image(Panel parent, Texture t) {
        super(parent);
        setTexture(t);
    }

    @Override
    public void draw() {
        if (!visible) return;
        
        SPTexture.draw(Gui.cam.mat, mat, texture, panelVA);
            
        children.forEach((t) -> {
            ((Panel) t).draw();
        });
    }
    
    public void setTexture(Texture t){
        texture = t;
        size = new Vector2i(t.size);
    }
    
    public Texture getTexture(){
        return texture;
    }
}
