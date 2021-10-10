package lwjglproject.entities.prim3d;

import lwjglproject.entities.*;
import lwjglproject.gl.materials.MaterialSolidColor;
import lwjglproject.gl.vertexarrays.VertexArrayPNI;
import lwjglproject.gl.vertexarrays.VertexArrayPTNI;
import org.joml.*;



public class Line extends Mesh {
    
    boolean turnTowardsCamera = false;
    
    public Line(Entity parent) {
        super(parent);
        meshParts.add(new MeshPart(lineVertexArray, new MaterialSolidColor()));
    }

    @Override
    public void draw(Camera cam) {
        if(!visible) return;
        
        MeshPart part = meshParts.get(0);
        part.material.getShader().draw(cam, this, part.vertexArray, part.material);
    }    

    private Vector3f size = new Vector3f(0,0,0);
    
    public void setLine(Vector3f start, Vector3f end){
        setPosL(start);
        setSize(new Vector3f(end).sub(start));
    }
    
    public void setSize(Vector3f s){
        float y = new Vector3f(s.x,s.y,0).angle(new Vector3f(s.x,s.y,s.z));
        if(s.x == 0 && s.y == 0){
            if(s.z>0)
                y = (float) java.lang.Math.toRadians(90);
            else
                y = (float) java.lang.Math.toRadians(-90);
        }
        float z = new Vector2f(1,0).angle(new Vector2f(s.x,s.y));
        setRotL(new Vector3f(0,-y,z));
        setScaleL(new Vector3f(s.length(),getWidth(),1));
        size = s;
    }
    
    public Vector3f getSize(){
        return size;
    }
    
    public void setWidth(float w){
        scaleL.y = w;
    }
    
    public float getWidth(){
        return getScaleL().y;
    }
    
    @Override
    public void updateMatrix() {
        updateVectors();
        
        getMat().identity();
        
        Vector3f newPos = (Vector3f) new Vector3f(getPosG()).rotateZ(-rotG.z).rotateY(-rotG.y);
        getMat().rotateLocalZ(getRotG().z);
        getMat().rotateY(getRotG().y);
        getMat().translate(newPos);
        
        if(turnTowardsCamera){
            Vector3f lineCenter = new Vector3f(getPosG()).add(new Vector3f(size).div(2));
            //Vector3f cameraPos = new Vector3f(Camera.main.posG);
            Vector3f dir =  new Vector3f().sub(lineCenter);
            Vector3f plane = new Vector3f(1,0,1).rotateZ(getRotG().z).rotateY(getRotG().y);

            if(plane.x == 0 && plane.y ==0)
                plane = new Vector3f(1,0,1);
            else if(plane.x == 0 && plane.z ==0)
                plane = new Vector3f(0,1,1);
            else if(plane.y == 0 && plane.z ==0)
                plane = new Vector3f(1,1,0);

            Vector3f up = (Vector3f) new Vector3f(0,0,1).rotateZ(getRotG().z).rotateY(getRotG().y);
            float angleX = up.angleSigned(dir, plane);

            
            getMat().rotateX(angleX);
        }
        
        getMat().scale(getScaleG());
    }
    
    final public static VertexArrayPTNI lineVertexArray = new VertexArrayPTNI(
            new float[]{
                0,-0.5f,0,
                1,-0.5f,0,
                1,0.5f,0,
                0,0.5f,0
            }, Plane.planeTexCoords, Plane.planeNormalsYP, Plane.planeIndices
    );
    
    /*
    final public static VertexArray lineVertexArray = new VertexArray(
            new float[]{
                0,-0.5f,0,
                1,-0.5f,0,
                1,0.5f,0,
                0,0.5f,0
            },
            new float[]{
                0,0,
                1,0,
                1,1,
                0,1,
                0,1,
                1,1,
                1,0,
                0,0
            },
            new int[]{
                0,1,2,
                0,2,3,
                3,2,0,
                0,2,1
            }
    );
    */
}
