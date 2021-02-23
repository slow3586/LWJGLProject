package lwjglproject.gl.vertexarrays;

import java.nio.FloatBuffer;
import lwjglproject.BufHelp;
import lwjglproject.BufHelp;
import lwjglproject.gl.VertexBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArrayPNI extends VertexArrayPI {
    public VertexBuffer norBuf=null;

    public VertexArrayPNI(){
        super();
    }
    
    public VertexArrayPNI(float[] p, float[] n, int[] i) {
        super();
        setPosBuf(p);
        setNorBuf(n);
        setIndBuf(i);
    }
    
    public void setNorBuf(float[] array){
        bind();
        norBuf = new VertexBuffer(GL_ARRAY_BUFFER, array.length);
        norBuf.bind();
        glBufferData(GL_ARRAY_BUFFER, BufHelp.toBuffer(array), GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
    }
}
