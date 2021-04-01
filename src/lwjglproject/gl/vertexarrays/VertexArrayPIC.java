package lwjglproject.gl.vertexarrays;

import java.util.ArrayList;
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
    
    public VertexArrayPIC(float[] p, int[] i, float[] c) {
        super(p, i);
        
        setVertexColorBuf(c);
    }
    
    public void setVertexColorBuf(ArrayList<Vector4f> list){
        float[] posArr = new float[list.size()*4];
        for (int i = 0; i < list.size(); i++) {
            Vector4f v = list.get(i);
            posArr[i*4] = v.x;
            posArr[i*4+1] = v.y;
            posArr[i*4+2] = v.z;
            posArr[i*4+3] = v.w;
        }
        
        setVertexColorBuf(posArr);
    }
    
    public void setVertexColorBuf(float[] array){        
        bind();
        vertexColorBuffer = new VertexBuffer(GL_ARRAY_BUFFER, array.length);
        vertexColorBuffer.bind();
        glBufferData(GL_ARRAY_BUFFER, BufHelp.toBuffer(array), GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
    }

}
