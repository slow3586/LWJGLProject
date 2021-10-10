package lwjglproject.gl.shaders;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Objects;
import lwjglproject.entities.Camera;
import lwjglproject.entities.Entity;
import lwjglproject.entities.Mesh;
import lwjglproject.gl.Texture;
import lwjglproject.gl.materials.Material;
import lwjglproject.gl.materials.MaterialTexture;
import static lwjglproject.gl.shaders.SPSolidColor.uColor;
import lwjglproject.gl.vertexarrays.VertexArray;
import org.joml.*;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.system.MemoryStack.stackPush;

final public class SPTexture extends ShaderProgram {

    final public static SPTexture ins = new SPTexture();
    static Matrix4f tempMat = new Matrix4f();
    static int uCameraEntityMat;

    protected SPTexture() {
        addShader(ShaderProgram.class, "fast.vert", GL_VERTEX_SHADER);
        addShader(ShaderProgram.class, "texture.frag", GL_FRAGMENT_SHADER);
        link();
        uCameraEntityMat = getUniformPos("cameraEntityMat");
    };  
    
    public static void draw(Matrix4f camMat, Matrix4f entMat, Texture tex, VertexArray varr) {
        ins.use();
        
        try ( MemoryStack stack = stackPush() ) {
            FloatBuffer u4fb = stack.mallocFloat(4);
        
            tempMat.identity();
            tempMat.mul(camMat);
            tempMat.mul(entMat);
            FloatBuffer mat4fb = stack.mallocFloat(16);
            glUniformMatrix4fv(uCameraEntityMat, false, tempMat.get(mat4fb));
        
            tex.bind();
            varr.draw();
        }
        
    }

    @Override
    public void draw(Camera cam, Entity ent, VertexArray varr, Material mat) {
        MaterialTexture m = (MaterialTexture)mat;
        draw(cam.getMat(), ent.getMat(), m.tex, varr);
    }
}
