package lwjglproject.entities;

import lwjglproject.gl.materials.Material;
import lwjglproject.gl.vertexarrays.VertexArray;


public class MeshPart {
    public VertexArray vertexArray;
    public Material material;

    public MeshPart(VertexArray vertexArray, Material material) {
        this.vertexArray = vertexArray;
        this.material = material;
    }
}
