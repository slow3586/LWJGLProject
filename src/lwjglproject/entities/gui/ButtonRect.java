package lwjglproject.entities.gui;

import lwjglproject.entities.*;
import lwjglproject.gl.*;
import lwjglproject.gl.shaders.SPSolidColor;
import org.joml.*;
public abstract class ButtonRect extends Button {

    Vector4f color = new Vector4f(0.6f,0.6f,0.6f,0.5f);
    Vector4f colorActive = new Vector4f(0.7f,0.7f,0.7f,0.7f);
    
    public ButtonRect(Panel parent) {
        super(parent);
    }

    @Override
    public void draw(Camera cam){
        if (!visible) return;
        
        if(isMouseOver && !isDown){
            SPSolidColor.draw(cam.mat, mat, colorActive, PanelRect.panelVA);
        } else {
            SPSolidColor.draw(cam.mat, mat, color, PanelRect.panelVA);
        }
            
        children.forEach((t) -> {
            ((Panel) t).draw(cam);
        });
    }
}
