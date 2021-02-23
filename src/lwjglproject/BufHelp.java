package lwjglproject;

import java.nio.*;
import java.util.ArrayList;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public class BufHelp {

    public static FloatBuffer toBuffer(float[] array){
        return (FloatBuffer)BufferUtils.createFloatBuffer(array.length).put(array).rewind();
    }
    
    public static IntBuffer toBuffer(int[] array){
        return (IntBuffer)BufferUtils.createIntBuffer(array.length).put(array).rewind();
    }
    
    public static ByteBuffer toBuffer(byte[] array){
        return (ByteBuffer)BufferUtils.createByteBuffer(array.length).put(array).rewind();
    }
}
