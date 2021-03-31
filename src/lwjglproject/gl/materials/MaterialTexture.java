package lwjglproject.gl.materials;

import lwjglproject.entities.Camera;
import lwjglproject.gl.Texture;
import lwjglproject.gl.shaders.SPTexture;
import lwjglproject.gl.shaders.ShaderProgram;
import org.joml.Vector4f;

public class MaterialTexture extends Material {
    
    public Texture tex;
    
    public MaterialTexture() {
    }

    public MaterialTexture(Texture texture) {
        this.tex = texture;
    }

    @Override
    public ShaderProgram getShader() {
        return SPTexture.ins;
    }

}
