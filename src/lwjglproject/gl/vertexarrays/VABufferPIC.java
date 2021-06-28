package lwjglproject.gl.vertexarrays;

import java.util.ArrayList;
import java.util.Collections;
import org.joml.Vector4f;

public class VABufferPIC extends VertexArrayPIC {
    
    public static class VABufferPartPIC {
        public float[] p;
        public int[] i;
        public float[] c;
        public boolean needsUpdate = false;
    }
    
    public ArrayList<VABufferPartPIC> arrays = new ArrayList<>();
    public VertexArrayPIC vArr = new VertexArrayPIC();
    
    public void render(){
        int pAllLength = 0;
        int iAllLength = 0;
        int cAllLength = 0;
        for (int i = 0; i < arrays.size(); i++) {
            VABufferPartPIC p = arrays.get(i);
            pAllLength += p.p.length;
            iAllLength += p.i.length;
            cAllLength += p.c.length;
        }
        float[] pAll = new float[pAllLength];
        int[] iAll = new int[iAllLength];
        float[] cAll = new float[cAllLength];
        int pAllInd = 0;
        int iAllInd = 0;
        int cAllInd = 0;
        int largestIndex = 0;
        int indexAdd = 0;
        for (int i = 0; i < arrays.size(); i++) {
            VABufferPartPIC p = arrays.get(i);
            indexAdd = largestIndex;
            for (int j = 0; j < p.p.length; j++) {
                pAll[pAllInd] = p.p[j];
                pAllInd++;
            }
            for (int j = 0; j < p.i.length; j++) {
                iAll[iAllInd] = p.i[j] + indexAdd;
                iAllInd++;
                if(iAll[iAllInd]>largestIndex)
                    largestIndex=iAll[iAllInd];
            }
            for (int j = 0; j < p.c.length; j++) {
                cAll[cAllInd] = p.c[j];
                cAllInd++;
            }
        }
        
        vArr.setPosBuf(pAll);
        vArr.setIndBuf(iAll);
        vArr.setVertexColorBuf(cAll);
    }
}
