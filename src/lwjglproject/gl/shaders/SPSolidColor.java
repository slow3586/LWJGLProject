package lwjglproject.gl.shaders;

import lwjglproject.gl.vertexarrays.VertexArray;
import java.nio.FloatBuffer;
import java.util.HashMap;
import lwjglproject.entities.Camera;
import lwjglproject.entities.Entity;
import lwjglproject.entities.Mesh;
import lwjglproject.gl.materials.Material;
import lwjglproject.gl.materials.MaterialSolidColor;
import org.joml.*;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.system.MemoryStack.stackPush;

final public class SPSolidColor extends ShaderProgram {

    final public static SPSolidColor ins = new SPSolidColor();
    static Matrix4f tempMat = new Matrix4f();
    static int uColor;
    static int uCameraEntityMat;

    protected SPSolidColor() {
        addShader(ShaderProgram.class, "fast.vert", GL_VERTEX_SHADER);
        addShader(ShaderProgram.class, "solidColor.frag", GL_FRAGMENT_SHADER);
        link();
        uColor = getUniformPos("solidColor");
        uCameraEntityMat = getUniformPos("cameraEntityMat");
    };  

    public void draw(Camera cam, Entity ent, VertexArray varr, Material mat){
        MaterialSolidColor m = (MaterialSolidColor)mat;
        draw(cam.getMat(), ent.getMat(), m.color, varr);
    }
    
    public static void draw(Matrix4f camMat, Matrix4f entMat, Vector4f color, VertexArray varr) {
        ins.use();
        
        try ( MemoryStack stack = stackPush() ) {
            FloatBuffer u4fb = stack.mallocFloat(4);
            glUniform4fv(uColor, color.get(u4fb));
        
            tempMat.identity();
            tempMat.mul(camMat);
            tempMat.mul(entMat);
            FloatBuffer mat4fb = stack.mallocFloat(16);
            glUniformMatrix4fv(uCameraEntityMat, false, tempMat.get(mat4fb));
            
            varr.draw();
        }
    }
}
