package lwjglproject.logic;

import java.io.File;
import java.util.ArrayList;
import lwjglproject.entities.Entity;
import lwjglproject.entities.Mesh;
import lwjglproject.entities.MeshPart;
import lwjglproject.gl.Texture;
import lwjglproject.gl.materials.MaterialSolidColor;
import lwjglproject.gl.materials.MaterialTexture;
import lwjglproject.gl.vertexarrays.VertexArrayPI;
import lwjglproject.gl.vertexarrays.VertexArrayPIC;
import lwjglproject.gl.vertexarrays.VertexArrayPTI;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class GameTerrain extends Mesh {

    int size = 4;
    
    public void wat2(){
        ArrayList<Vector3f> v = new ArrayList<>();
        ArrayList<Vector2f> u = new ArrayList<>();
        ArrayList<Integer> i = new ArrayList<>();
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                v.add(new Vector3f(x+0,y+0,0));
                v.add(new Vector3f(x+1,y+0,0));
                v.add(new Vector3f(x+0,y+1,0));
                v.add(new Vector3f(x+1,y+1,0));
                u.add(new Vector2f(0,0));
                u.add(new Vector2f(1,0));
                u.add(new Vector2f(0,1));
                u.add(new Vector2f(1,1));
                i.add((size*y+x)*4+0);
                i.add((size*y+x)*4+1);
                i.add((size*y+x)*4+2);
                i.add((size*y+x)*4+1);
                i.add((size*y+x)*4+3);
                i.add((size*y+x)*4+2);
            }
        }
        VertexArrayPTI va = new VertexArrayPTI(v, u, i);
        Texture t = Texture.fromFile(new File("img.png"));
        MaterialTexture m = new MaterialTexture(t);
        meshParts.add(new MeshPart(va, m));
    }
    /*
    public void wat(){
        float[] tilevcs = new float[(size+1)*(size+1)*3*4];
        float[] tileuvs = new float[(size+1)*(size+1)*2*4];
        
        for(int y=0; y<=size; y++){
            for(int x=0; x<=size; x++){
               tilevcs[(x+y*size)*3*4+0]=x;
               tilevcs[(x+y*size)*3*4+0+1]=y;
               tilevcs[(x+y*size)*3*4+0+2]=0;
               tileuvs[(x+y*size)*2*4+0]=0;
               tileuvs[(x+y*size)*2*4+0+1]=0;
               
               tilevcs[(x+y*size)*3*4+3]=x;
               tilevcs[(x+y*size)*3*4+3+1]=y;
               tilevcs[(x+y*size)*3*4+3+2]=0;
               tileuvs[(x+y*size)*2*4+2]=1;
               tileuvs[(x+y*size)*2*4+2+1]=0;
               
               tilevcs[(x+y*size)*3*4+6]=x;
               tilevcs[(x+y*size)*3*4+6+1]=y;
               tilevcs[(x+y*size)*3*4+6+2]=0;
               tileuvs[(x+y*size)*2*4+4]=0;
               tileuvs[(x+y*size)*2*4+4+1]=1;
               
               tilevcs[(x+y*size)*3*4+9]=x;
               tilevcs[(x+y*size)*3*4+9+1]=y;
               tilevcs[(x+y*size)*3*4+9+2]=0;
               tileuvs[(x+y*size)*2*4+6]=1;
               tileuvs[(x+y*size)*2*4+6+1]=1;
            }
        }
        
        int[] tileindcs = new int[size*size*6];
        int tilecnt = 0;
        for(int y=0; y<size; y+=2){
            for(int x=0; x<size; x+=2){
                for(int i=0; i<4; i++){
                    if(i<2){
                        tileindcs[tilecnt*24+0+i*6]=((y*size)+i+x+0)*4+0;
                        tileindcs[tilecnt*24+1+i*6]=((y*size)+i+x+1)*4+1;
                        tileindcs[tilecnt*24+2+i*6]=((y*size)+i+x+size)*4+2;
                        tileindcs[tilecnt*24+3+i*6]=((y*size)+i+x+1)*4+1;
                        tileindcs[tilecnt*24+4+i*6]=((y*size)+i+x+1+size)*4+3;
                        tileindcs[tilecnt*24+5+i*6]=((y*size)+i+x+size)*4+2;
                    }else{
                        tileindcs[tilecnt*24+0+i*6]=(((y+1)*size)+(i-2)+x+0)*4+0;
                        tileindcs[tilecnt*24+1+i*6]=(((y+1)*size)+(i-2)+x+1)*4+1;
                        tileindcs[tilecnt*24+2+i*6]=(((y+1)*size)+(i-2)+x+size)*4+2;
                        tileindcs[tilecnt*24+3+i*6]=(((y+1)*size)+(i-2)+x+1)*4+1;
                        tileindcs[tilecnt*24+4+i*6]=(((y+1)*size)+(i-2)+x+1+size)*4+3;
                        tileindcs[tilecnt*24+5+i*6]=(((y+1)*size)+(i-2)+x+size)*4+2;
                    }
                    
                    System.out.print(tileindcs[tilecnt*24+0+i*6]+",");
                    System.out.print(tileindcs[tilecnt*24+1+i*6]+",");
                    System.out.print(tileindcs[tilecnt*24+2+i*6]+",");
                    System.out.print(tileindcs[tilecnt*24+3+i*6]+",");
                    System.out.print(tileindcs[tilecnt*24+4+i*6]+",");
                    System.out.print(tileindcs[tilecnt*24+5+i*6]+"\n");
                    
                    System.out.print("("+tilevcs[tileindcs[tilecnt*24+0+i*6]*3+0]+","
                            +tilevcs[tileindcs[tilecnt*24+0+i*6]*3+1]+","
                            +tilevcs[tileindcs[tilecnt*24+0+i*6]*3+2]+"),");
                    System.out.print("("+tilevcs[tileindcs[tilecnt*24+1+i*6]*3+0]+","
                            +tilevcs[tileindcs[tilecnt*24+1+i*6]*3+1]+","
                            +tilevcs[tileindcs[tilecnt*24+1+i*6]*3+2]+"),");
                    System.out.print("("+tilevcs[tileindcs[tilecnt*24+2+i*6]*3+0]+","
                            +tilevcs[tileindcs[tilecnt*24+2+i*6]*3+1]+","
                            +tilevcs[tileindcs[tilecnt*24+2+i*6]*3+2]+"),");
                    System.out.print("("+tilevcs[tileindcs[tilecnt*24+3+i*6]*3+0]+","
                            +tilevcs[tileindcs[tilecnt*24+3+i*6]*3+1]+","
                            +tilevcs[tileindcs[tilecnt*24+3+i*6]*3+2]+"),");
                    System.out.print("("+tilevcs[tileindcs[tilecnt*24+4+i*6]*3+0]+","
                            +tilevcs[tileindcs[tilecnt*24+4+i*6]*3+1]+","
                            +tilevcs[tileindcs[tilecnt*24+4+i*6]*3+2]+"),");
                    System.out.print("("+tilevcs[tileindcs[tilecnt*24+5+i*6]*3+0]+","
                            +tilevcs[tileindcs[tilecnt*24+5+i*6]*3+1]+","
                            +tilevcs[tileindcs[tilecnt*24+5+i*6]*3+2]+")\n");
                    
                    System.out.print("("+tileuvs[tileindcs[tilecnt*24+0+i*6]*2+0]+","
                            +tileuvs[tileindcs[tilecnt*24+0+i*6]*2+1]+"),");
                    System.out.print("("+tileuvs[tileindcs[tilecnt*24+1+i*6]*2+0]+","
                            +tileuvs[tileindcs[tilecnt*24+1+i*6]*2+1]+"),");
                    System.out.print("("+tileuvs[tileindcs[tilecnt*24+2+i*6]*2+0]+","
                            +tileuvs[tileindcs[tilecnt*24+2+i*6]*2+1]+"),");
                    System.out.print("("+tileuvs[tileindcs[tilecnt*24+3+i*6]*2+0]+","
                            +tileuvs[tileindcs[tilecnt*24+3+i*6]*2+1]+"),");
                    System.out.print("("+tileuvs[tileindcs[tilecnt*24+4+i*6]*2+0]+","
                            +tileuvs[tileindcs[tilecnt*24+4+i*6]*2+1]+"),");
                    System.out.print("("+tileuvs[tileindcs[tilecnt*24+5+i*6]*2+0]+","
                            +tileuvs[tileindcs[tilecnt*24+5+i*6]*2+1]+")\n");
                }
                
               tilecnt++;
            }
        }
        
        VertexArrayPTI va = new VertexArrayPTI(tilevcs, tileuvs, tileindcs);
        Texture t = Texture.fromFile(new File("img.png"));
        MaterialTexture m = new MaterialTexture(t);
        meshParts.add(new MeshPart(va, m));
        
    }
    */
    
    public GameTerrain(Entity parent) {
        super(parent);
           
        wat2();
        /*
        float[] tilevcs = new float[(size+1)*(size+1)*3];
        
        for(int y=0; y<=size; y++){
            for(int x=0; x<=size; x++){
               tilevcs[(x+y*size)*3]=x;
               tilevcs[(x+y*size)*3+1]=y;
               tilevcs[(x+y*size)*3+2]=0;
            }
        }
        
        int[] tileindcs = new int[size*size*6];
        int tilecnt = 0;
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
               tileindcs[tilecnt*6+0]=(y*size)+x+0;
               tileindcs[tilecnt*6+1]=(y*size)+x+1;
               tileindcs[tilecnt*6+2]=(y*size)+x+(0+size);
               tileindcs[tilecnt*6+3]=(y*size)+x+1;
               tileindcs[tilecnt*6+4]=(y*size)+x+(1+size);
               tileindcs[tilecnt*6+5]=(y*size)+x+(0+size);
               System.out.print(tileindcs[tilecnt*6+0]+",");
               System.out.print(tileindcs[tilecnt*6+1]+",");
               System.out.print(tileindcs[tilecnt*6+2]+",");
               System.out.print(tileindcs[tilecnt*6+3]+",");
               System.out.print(tileindcs[tilecnt*6+4]+",");
               System.out.print(tileindcs[tilecnt*6+5]+"\n");
               System.out.print("("+tilevcs[tileindcs[tilecnt*6+0]*3+0]+","
                       +tilevcs[tileindcs[tilecnt*6+0]*3+1]+","
                       +tilevcs[tileindcs[tilecnt*6+0]*3+2]+"),");
               System.out.print("("+tilevcs[tileindcs[tilecnt*6+1]*3+0]+","
                       +tilevcs[tileindcs[tilecnt*6+1]*3+1]+","
                       +tilevcs[tileindcs[tilecnt*6+1]*3+2]+"),");
               System.out.print("("+tilevcs[tileindcs[tilecnt*6+2]*3+0]+","
                       +tilevcs[tileindcs[tilecnt*6+2]*3+1]+","
                       +tilevcs[tileindcs[tilecnt*6+2]*3+2]+"),");
               System.out.print("("+tilevcs[tileindcs[tilecnt*6+3]*3+0]+","
                       +tilevcs[tileindcs[tilecnt*6+3]*3+1]+","
                       +tilevcs[tileindcs[tilecnt*6+3]*3+2]+"),");
               System.out.print("("+tilevcs[tileindcs[tilecnt*6+4]*3+0]+","
                       +tilevcs[tileindcs[tilecnt*6+4]*3+1]+","
                       +tilevcs[tileindcs[tilecnt*6+4]*3+2]+"),");
               System.out.print("("+tilevcs[tileindcs[tilecnt*6+5]*3+0]+","
                       +tilevcs[tileindcs[tilecnt*6+5]*3+1]+","
                       +tilevcs[tileindcs[tilecnt*6+5]*3+2]+")\n");
               tilecnt++;
            }
        }
        
        float[] tileuvs = new float[]
        
        VertexArrayPTI va = new VertexArrayPTI(tilevcs, tileindcs);
        MaterialSolidColor m = new MaterialSolidColor();
        m.color = new Vector4f(1, 0, 0, 1);
        meshParts.add(new MeshPart(va, m));
        */
    }

}
