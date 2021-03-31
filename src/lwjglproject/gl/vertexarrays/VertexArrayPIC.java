package lwjglproject.gl.vertexarrays;

import lwjglproject.BufHelp;
import lwjglproject.gl.VertexBuffer;
import org.joml.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class VertexArrayPIC extends VertexArrayPI {
    VertexBuffer vertexColorBuffer = null;
    
    public VertexArrayPIC(){
        super();
    }
    
    public VertexArrayPIC(float[] p, int[] i, Vector4f[] c) {
        super(p, i);
        
        setVertexColorBuf(c);
    }
    
    public void setVertexColorBuf(Vector4f[] v4fArray){
        float[] floatArray = new float[v4fArray.length*4];
        for (int i = 0; i < v4fArray.length; i++) {
            Vector4f v4f = v4fArray[i];
            floatArray[i*4]=v4f.x;
            floatArray[i*4+1]=v4f.y;
            floatArray[i*4+2]=v4f.z;
            floatArray[i*4+3]=v4f.w;
        }
        
        bind();
        vertexColorBuffer = new VertexBuffer(GL_ARRAY_BUFFER, floatArray.length);
        vertexColorBuffer.bind();
        glBufferData(GL_ARRAY_BUFFER, BufHelp.toBuffer(floatArray), GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
    }

}
