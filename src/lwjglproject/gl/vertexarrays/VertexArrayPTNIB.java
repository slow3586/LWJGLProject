package lwjglproject.gl.vertexarrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedHashMap;
import lwjglproject.BufHelp;
import lwjglproject.BufHelp;
import lwjglproject.gl.VertexBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArrayPTNIB extends VertexArrayPTNI {
    public VertexBuffer boneWeightBuffer = null;
    public VertexBuffer boneIndexBuffer = null;

    public VertexArrayPTNIB() {
        glGenVertexArrays(id);
    }

    public VertexArrayPTNIB(float[] p, float[] t, float[] n, int[] i) {
        glGenVertexArrays(id);
        setPosBuf(p);
        setTexBuf(t);
        setNorBuf(n);
        setIndBuf(i);
    }
    
    public void setBoneWeightBuffer(float[] array){
        bind();
        boneWeightBuffer = new VertexBuffer(GL_ARRAY_BUFFER, array.length);
        boneWeightBuffer.bind();
        glBufferData(GL_ARRAY_BUFFER, BufHelp.toBuffer(array), GL_STATIC_DRAW);
        glEnableVertexAttribArray(3);
        glVertexAttribPointer(3, 4, GL_FLOAT, false, 0, 0);
    }
    
    public void setBoneIndexBuffer(float[] array){
        bind();
        boneIndexBuffer = new VertexBuffer(GL_ARRAY_BUFFER, array.length);
        boneIndexBuffer.bind();
        glBufferData(GL_ARRAY_BUFFER, BufHelp.toBuffer(array), GL_STATIC_DRAW);
        glEnableVertexAttribArray(4);
        glVertexAttribPointer(4, 4, GL_FLOAT, false, 0, 0);
    }
}
