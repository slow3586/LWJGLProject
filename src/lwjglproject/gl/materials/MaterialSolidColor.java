package lwjglproject.gl.materials;

import lwjglproject.entities.Camera;
import lwjglproject.gl.shaders.SPSolidColor;
import lwjglproject.gl.shaders.ShaderProgram;
import org.joml.Vector4f;

public class MaterialSolidColor extends Material {
    public Vector4f color=new Vector4f(1.0f,0.0f,0.0f,1.0f);
    public Camera cam = Camera.main;

    public MaterialSolidColor() {
    }

    public MaterialSolidColor(Vector4f color, Camera cam) {
        this.color = color;
        this.cam = cam;
    }

    @Override
    public ShaderProgram getShader() {
        return SPSolidColor.ins;
    }
}
