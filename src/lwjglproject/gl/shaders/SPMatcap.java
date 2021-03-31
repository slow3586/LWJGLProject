package lwjglproject.gl.shaders;


import lwjglproject.gl.vertexarrays.VertexArray;
import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import lwjglproject.entities.Camera;
import lwjglproject.entities.Entity;
import lwjglproject.entities.Mesh;
import lwjglproject.gl.Texture;
import lwjglproject.gl.materials.Material;
import org.joml.*;
import static org.lwjgl.opengl.GL20.*;

final public class SPMatcap extends ShaderProgram {
    
    final public static SPMatcap ins = new SPMatcap();
    static int uModelMat;
    static int uViewMat;
    static int uProjMat;
    static int uCamPos;

    public static Texture defaultMatcapTexture;
    
    protected SPMatcap() {
        try {
            defaultMatcapTexture = Texture.fromFile(new File(SPMatcap.class.getResource("matcapDefault.png").toURI()));
        } catch (URISyntaxException ex) {
            throw new IllegalStateException();
        }
        
        addShader(ShaderProgram.class, "matcap.vert", GL_VERTEX_SHADER);
        addShader(ShaderProgram.class, "matcap.frag", GL_FRAGMENT_SHADER);
        link();
        uModelMat = getUniformPos("modelMat");
        uViewMat = getUniformPos("viewMat");
        uProjMat = getUniformPos("projMat");
        uCamPos = getUniformPos("camPos");
    };  
    
    public void draw(Camera cam, Entity ent, VertexArray varr, Material mat){
        //draw((Camera)params.get("camera"), mesh.mat, (Texture)params.get("matcapTexture"), varr);
    }
    
    public static void draw(Camera camera, Matrix4f entMat, Texture matcap, VertexArray varr) {
        ins.use();
        
        glUniform3f(uCamPos, camera.posL.x, camera.posL.y, camera.posL.z);
        float[] temp = new float[16];
        glUniformMatrix4fv(uModelMat, false, entMat.get(temp));
        glUniformMatrix4fv(uViewMat, false, camera.view.get(temp));
        glUniformMatrix4fv(uProjMat, false, camera.proj.get(temp));
        
        if(matcap != null){
            matcap.bind();
        }else{
            defaultMatcapTexture.bind();
        }
        
        varr.draw();
    }
}
