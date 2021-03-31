package lwjglproject.entities;

import lwjglproject.gl.materials.Material;
import lwjglproject.gl.vertexarrays.VertexArray;


public class MeshPart {
    public VertexArray vertexArray = null;
    public Material material = null;
    
    public MeshPart(){
    }

    public MeshPart(VertexArray vertexArray, Material material) {
        this.vertexArray = vertexArray;
        this.material = material;
    }
}
