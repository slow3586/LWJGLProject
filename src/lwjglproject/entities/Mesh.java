package lwjglproject.entities;

import lwjglproject.gl.materials.Material;
import lwjglproject.entities.Entity;
import lwjglproject.gl.shaders.ShaderProgram;
import java.io.File;
import java.util.ArrayList;

public class Mesh extends Entity {

    public ArrayList<MeshPart> meshParts = new ArrayList<>(); 
    
    public Mesh(Entity parent) {
        super(parent);
    }
    
    public void setMaterial(Material mat){
        for (MeshPart meshPart : meshParts) {
            meshPart.material = mat;
        }
    }
    
    @Override
    public void draw() {
        if(!visible) return;
        for(MeshPart part : meshParts){
            part.material.getShader().draw(this, part.vertexArray, part.material);
        }
        
        if(children.isEmpty()) return;
        
        children.forEach((t) -> {
            ((Entity)t).draw();
        });
    }    
}
