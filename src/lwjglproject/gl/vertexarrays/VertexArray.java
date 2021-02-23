package lwjglproject.gl.vertexarrays;

import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL30.*;

public abstract class VertexArray {
    public IntBuffer id = IntBuffer.allocate(1);
    public static VertexArray current;
    public abstract  void draw();
    public VertexArray() {
        id.put(glGenVertexArrays());
    }
    public void bind(){
        if(VertexArray.current != this){
            glBindVertexArray(id.get(0));
            VertexArray.current = this;
        }
    }
}
