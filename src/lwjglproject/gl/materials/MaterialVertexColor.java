package lwjglproject.gl.materials;

import lwjglproject.entities.Camera;
import lwjglproject.gl.shaders.SPSolidColor;
import lwjglproject.gl.shaders.SPVertexColor;
import lwjglproject.gl.shaders.ShaderProgram;
import org.joml.Vector4f;

public class MaterialVertexColor extends Material {
    Vector4f color;
    
    public MaterialVertexColor() {
    }

    public MaterialVertexColor(Vector4f color) {
        this.color = color;
    }

    @Override
    public ShaderProgram getShader() {
        return SPVertexColor.ins;
    }
}
