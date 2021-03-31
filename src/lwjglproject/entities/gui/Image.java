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
    public void draw(Camera cam) {
        if (!visible) return;
        
        SPTexture.draw(cam.mat, mat, texture, panelVA);
            
        children.forEach((t) -> {
            ((Panel) t).draw(cam);
        });
    }
    
    public void setTexture(Texture t){
        if(t==null) return;
        texture = t;
        size = new Vector2i(t.size);
        updateMatrix();
    }
    
    public Texture getTexture(){
        return texture;
    }
}
