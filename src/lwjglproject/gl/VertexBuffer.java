package lwjglproject.gl;

import java.nio.IntBuffer;
import lwjglproject.BufHelp;
import static org.lwjgl.opengl.GL15.*;

public class VertexBuffer {
    public IntBuffer id = IntBuffer.allocate(1);
    public int type;
    public int length;
    
    public VertexBuffer(int type, int length){ 
        this.type = type;
        this.length = length;
        id.put(glGenBuffers());
    }

    public void bind() {
        glBindBuffer(type, id.get(0));
    }
}
