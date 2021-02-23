package lwjglproject;

import java.nio.*;
import java.util.ArrayList;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public class BufHelp {

    public static FloatBuffer toBuffer(float[] array){
        return (FloatBuffer)BufferUtils.createFloatBuffer(array.length).put(array).rewind();
    }
    
    public static FloatBuffer toBuffer(ArrayList<Vector3f> data){
        int i = 0;
        float[] array = new float[data.size()*3];
        for (int j = 0; j < data.size()*3; j+=3) {
            Vector3f v = data.get(j);
            array[j]=v.x;
            array[j+1]=v.y;
            array[j+2]=v.z;
        }
        return (FloatBuffer)BufferUtils.createFloatBuffer(array.length).put(array).rewind();
    }
    
    public static IntBuffer toBuffer(int[] array){
        return (IntBuffer)BufferUtils.createIntBuffer(array.length).put(array).rewind();
    }
}
