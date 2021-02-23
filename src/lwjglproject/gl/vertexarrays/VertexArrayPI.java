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

public class VertexArrayPI extends VertexArray {
    public VertexBuffer posBuf=null;
    public VertexBuffer indBuf=null;

    public VertexArrayPI(){
        super();
    }
    
    public VertexArrayPI(float[] p, int[] i) {
        super();
        setPosBuf(p);
        setIndBuf(i);
    }
    
    public void setPosBuf(float[] array){
        bind();
        posBuf = new VertexBuffer(GL_ARRAY_BUFFER, array.length);
        posBuf.bind();
        glBufferData(GL_ARRAY_BUFFER, BufHelp.toBuffer(array), GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    }
    
    public void setIndBuf(int[] array){
        bind();
        indBuf = new VertexBuffer(GL_ELEMENT_ARRAY_BUFFER, array.length);
        indBuf.bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,
            BufHelp.toBuffer(array),
            GL_STATIC_DRAW
        );
    }

    @Override
    public void draw(){
        bind();
        if(indBuf!=null)
            glDrawElements(GL_TRIANGLES, indBuf.length, GL_UNSIGNED_INT, 0);
        else
            glDrawArrays(GL_TRIANGLES, 0, posBuf.length);
    }
}
