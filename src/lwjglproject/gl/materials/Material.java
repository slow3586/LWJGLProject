package lwjglproject.gl.materials;

import lwjglproject.entities.Camera;
import lwjglproject.gl.shaders.SPSolidColor;
import lwjglproject.gl.shaders.ShaderProgram;
import java.util.HashMap;
import org.joml.*;

public abstract class Material {
    public Material() {
    }
    
    abstract public ShaderProgram getShader();
    
}
