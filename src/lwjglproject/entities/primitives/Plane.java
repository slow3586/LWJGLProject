package lwjglproject.entities.primitives;

import lwjglproject.entities.Entity;
import lwjglproject.entities.Mesh;
import lwjglproject.entities.MeshPart;
import lwjglproject.gl.materials.Material;
import lwjglproject.gl.materials.MaterialSolidColor;
import lwjglproject.gl.vertexarrays.VertexArrayPTNI;
import org.joml.*;


public class Plane extends Mesh {
        
    private Plane(Entity parent){
        super(parent);
    }
    
    public static Plane planeXP(Entity parent){
        Plane p = new Plane(parent);        
        p.meshParts.add(new MeshPart(planeVArrXP, new MaterialSolidColor()));
        
        MaterialSolidColor m = new MaterialSolidColor();
        m.color = new Vector4f(1, 0, 0, 1);
        p.setMaterial(m);
        return p;
    }
    
    public static Plane planeXN(Entity parent){
        Plane p = new Plane(parent);        
        p.meshParts.add(new MeshPart(planeVArrXN, new MaterialSolidColor()));
        
        MaterialSolidColor m = new MaterialSolidColor();
        m.color = new Vector4f(0.5f, 0, 0, 1);
        p.setMaterial(m);
        return p;
    }
    
    public static Plane planeYP(Entity parent){
        Plane p = new Plane(parent);        
        p.meshParts.add(new MeshPart(planeVArrYP, new MaterialSolidColor()));
        
        MaterialSolidColor m = new MaterialSolidColor();
        m.color = new Vector4f(0, 1, 0, 1);
        p.setMaterial(m);
        return p;
    }
    
    public static Plane planeYN(Entity parent){
        Plane p = new Plane(parent);        
        p.meshParts.add(new MeshPart(planeVArrYN, new MaterialSolidColor()));
        
        MaterialSolidColor m = new MaterialSolidColor();
        m.color = new Vector4f(0, 0.5f, 0, 1);
        p.setMaterial(m);
        return p;
    }
    
    public static Plane planeZP(Entity parent){
        Plane p = new Plane(parent);        
        p.meshParts.add(new MeshPart(planeVArrZP, new MaterialSolidColor()));
        
        MaterialSolidColor m = new MaterialSolidColor();
        m.color = new Vector4f(0, 0, 1.0f, 1);
        p.setMaterial(m);
        return p;
    }
    
    
    public static Plane planeZN(Entity parent){
        Plane p = new Plane(parent);        
        p.meshParts.add(new MeshPart(planeVArrZN, new MaterialSolidColor()));
        
        MaterialSolidColor m = new MaterialSolidColor();
        m.color = new Vector4f(0, 0, 0.5f, 1);
        p.setMaterial(m);
        return p;
    }
    
    public static Plane plane2D(Entity parent){
        return planeZP(parent);
    }  
    
    @Override
    public void updateMatrix(){
        updateVectors();
        
        mat.identity();
        mat.translate(posG);
        mat.rotateLocalZ(rotG.z);
        mat.rotateY(rotG.y);
        mat.rotateX(rotG.x);
        mat.scale(new Vector3f(scaleG.x, scaleG.y, scaleG.z));
        
        children.forEach((t) -> {
            if(t instanceof Entity){
                ((Entity) t).updateMatrix();
            }
        });
    }
    
    final public static float[] planePosCoordsXP = 
            new float[]{
                0,0,0,
                0,1,0,
                0,1,1,
                0,0,1
            };
    
    final public static float[] planePosCoordsXN = 
            new float[]{
                0,0,0,
                0,0,1,
                0,1,1,
                0,1,0
            };
    
    final public static float[] planePosCoordsYP = 
            new float[]{
                0,0,0,
                0,0,1,
                1,0,1,
                1,0,0
            };
    
    final public static float[] planePosCoordsYN = 
            new float[]{
                0,0,0,
                1,0,0,
                1,0,1,
                0,0,1
            };
    
    final public static float[] planePosCoordsZP = 
            new float[]{
                0,0,0,
                1,0,0,
                1,1,0,
                0,1,0
            };
    
    final public static float[] planePosCoordsZN = 
            new float[]{
                0,0,0,
                0,1,0,
                1,1,0,
                1,0,0
            };
    
    final public static float[] planeTexCoords = 
            new float[]{
                0,0,
                1,0,
                1,1,
                0,1
            };
    
    final public static int[] planeIndices = 
            new int[]{
                0,1,2,
                0,2,3
            };
    
    final public static float[] planeNormalsXP = 
            new float[]{
                1,0,0,
                1,0,0,
                1,0,0,
                1,0,0
            };
    
    final public static float[] planeNormalsXN = 
            new float[]{
                -1,0,0,
                -1,0,0,
                -1,0,0,
                -1,0,0
            };
    
    final public static float[] planeNormalsYP = 
            new float[]{
                0,1,0,
                0,1,0,
                0,1,0,
                0,1,0
            };
    
    final public static float[] planeNormalsYN = 
            new float[]{
                0,-1,0,
                0,-1,0,
                0,-1,0,
                0,-1,0
            };
    
    final public static float[] planeNormalsZP = 
            new float[]{
                0,0,1,
                0,0,1,
                0,0,1,
                0,0,1
            };
    
    final public static float[] planeNormalsZN = 
            new float[]{
                0,0,-1,
                0,0,-1,
                0,0,-1,
                0,0,-1
            };
    
    final public static VertexArrayPTNI planeCenteredVA = new VertexArrayPTNI(
            new float[]{
                -0.5f,-0.5f,0,
                0.5f,-0.5f,0,
                0.5f,0.5f,0,
                -0.5f,0.5f,0
            }, planeTexCoords, planeNormalsZP, planeIndices
    );
    
    final public static VertexArrayPTNI planeVArrXP = new VertexArrayPTNI(
            planePosCoordsXP, planeTexCoords, planeNormalsXP, planeIndices
    );
    
    final public static VertexArrayPTNI planeVArrXN = new VertexArrayPTNI(
            planePosCoordsXN, planeTexCoords, planeNormalsXN, planeIndices
    );
    
    final public static VertexArrayPTNI planeVArrYP = new VertexArrayPTNI(
            planePosCoordsYP, planeTexCoords, planeNormalsYP, planeIndices
    );
    
    final public static VertexArrayPTNI planeVArrYN = new VertexArrayPTNI(
            planePosCoordsYN, planeTexCoords, planeNormalsYN, planeIndices
    );
    
    final public static VertexArrayPTNI planeVArrZP = new VertexArrayPTNI(
            planePosCoordsZP, planeTexCoords, planeNormalsZP, planeIndices
    );
    
    final public static VertexArrayPTNI planeVArrZN = new VertexArrayPTNI(
            planePosCoordsZN, planeTexCoords, planeNormalsZN, planeIndices
    );
}
