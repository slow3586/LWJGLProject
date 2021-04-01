package lwjglproject.gl.shaders;

import lwjglproject.gl.vertexarrays.VertexArray;
import java.nio.FloatBuffer;
import java.util.HashMap;
import lwjglproject.entities.Camera;
import lwjglproject.entities.Entity;
import lwjglproject.entities.Mesh;
import lwjglproject.gl.materials.Material;
import lwjglproject.gl.materials.MaterialSolidColor;
import lwjglproject.gl.materials.MaterialVertexColor;
import org.joml.*;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.system.MemoryStack.stackPush;

final public class SPVertexColor extends ShaderProgram {

    final public static SPVertexColor ins = new SPVertexColor();
    static Matrix4f tempMat = new Matrix4f();
    static int uCameraEntityMat;

    protected SPVertexColor() {
        addShader(ShaderProgram.class, "PIC.vert", GL_VERTEX_SHADER);
        addShader(ShaderProgram.class, "PIC.frag", GL_FRAGMENT_SHADER);
        link();
        uCameraEntityMat = getUniformPos("cameraEntityMat");
    };  

    public void draw(Camera cam, Entity ent, VertexArray varr, Material mat){
        MaterialVertexColor m = (MaterialVertexColor)mat;
        draw(cam.getMat(), ent.getMat(), varr);
    }
    
    public static void draw(Matrix4f camMat, Matrix4f entMat, VertexArray varr) {
        ins.use();
        
        try ( MemoryStack stack = stackPush() ) {
            FloatBuffer u4fb = stack.mallocFloat(4);
        
            tempMat.identity();
            tempMat.mul(camMat);
            tempMat.mul(entMat);
            FloatBuffer mat4fb = stack.mallocFloat(16);
            glUniformMatrix4fv(uCameraEntityMat, false, tempMat.get(mat4fb));
            
            varr.draw();
        }
    }
}
