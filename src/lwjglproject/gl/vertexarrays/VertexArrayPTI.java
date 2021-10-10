package lwjglproject.gl.vertexarrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import lwjglproject.BufHelp;
import lwjglproject.BufHelp;
import lwjglproject.gl.VertexBuffer;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArrayPTI extends VertexArrayPI {
    public VertexBuffer texBuf=null;

    public VertexArrayPTI(){
        super();
    }
    
    public VertexArrayPTI(float[] p, float[] t, int[] i) {
        super();
        setPosBuf(p);
        setIndBuf(i);
        setTexBuf(t);
    }
    
    public VertexArrayPTI(ArrayList<Vector3f> p, ArrayList<Vector2f> t, ArrayList<Integer> i) {
        super();
        setPosBuf(p);
        setIndBuf(i);
        setTexBuf(t);
    }
    
    public void setTexBuf(ArrayList<Vector2f> list){
        float[] posArr = new float[list.size()*2];
        for (int i = 0; i < list.size(); i++) {
            Vector2f v = list.get(i);
            posArr[i*2] = v.x;
            posArr[i*2+1] = v.y;
        }
        setTexBuf(posArr);
    }
    
    public void setTexBuf(float[] array){
        bind();
        texBuf = new VertexBuffer(GL_ARRAY_BUFFER, array.length);
        texBuf.bind();
        glBufferData(GL_ARRAY_BUFFER, BufHelp.toBuffer(array), GL_STATIC_DRAW);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
    }
}
