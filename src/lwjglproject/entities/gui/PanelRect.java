package lwjglproject.entities.gui;

import lwjglproject.entities.*;
import lwjglproject.entities.prim3d.Plane;
import lwjglproject.gl.*;
import lwjglproject.gl.shaders.SPSolidColor;
import lwjglproject.gl.vertexarrays.*;
import org.joml.*;

public class PanelRect extends Panel {

    public Vector4f color = new Vector4f(0.6f,0.6f,0.6f,0.5f);
    
    public PanelRect(Panel parent) {
        super(parent);
    }
    
    @Override
    public void draw(Camera cam){
        if (!visible) return;
        
        SPSolidColor.draw(cam.getMat(), mat, color, panelVA);
            
        children.forEach((t) -> {
            ((Panel) t).draw(cam);
        });
    }

    final public static VertexArrayPTNI panelVA = new VertexArrayPTNI(
            Plane.planePosCoordsZP, 
            Plane.planeTexCoords, Plane.planeNormalsZP, Plane.planeIndices
    );
}
