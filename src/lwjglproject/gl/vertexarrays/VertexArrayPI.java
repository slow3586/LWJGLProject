package lwjglproject.gl.vertexarrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import lwjglproject.BufHelp;
import lwjglproject.BufHelp;
import lwjglproject.gl.VertexBuffer;
import org.joml.*;
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
    
    public void setPosBuf(ArrayList<Vector2f> list, float z){
        float[] posArr = new float[list.size()*3];
        for (int i = 0; i < list.size(); i++) {
            Vector2f v = list.get(i);
            posArr[i*3] = v.x;
            posArr[i*3+1] = v.y;
            posArr[i*3+2] = z;
        }
        setPosBuf(posArr);
    }
    
    public void setPosBuf(ArrayList<Vector3f> list){
        float[] posArr = new float[list.size()*3];
        for (int i = 0; i < list.size(); i++) {
            Vector3f v = list.get(i);
            posArr[i*3] = v.x;
            posArr[i*3+1] = v.y;
            posArr[i*3+2] = 0;
        }
        setPosBuf(posArr);
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
