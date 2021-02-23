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

public class VertexArrayPTNI extends VertexArrayPNI {
    public VertexBuffer texBuf=null;

    public VertexArrayPTNI(){
        super();
    }
    
    public VertexArrayPTNI(float[] p, float[] t, float[] n, int[] i) {
        super();
        setPosBuf(p);
        setNorBuf(n);
        setIndBuf(i);
    }
    
    public void setTexBuf(float[] array){
        bind();
        texBuf = new VertexBuffer(GL_ARRAY_BUFFER, array.length);
        texBuf.bind();
        glBufferData(GL_ARRAY_BUFFER, BufHelp.toBuffer(array), GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
    }
}
