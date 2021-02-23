package lwjglproject.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.joml.*;

public class Entity extends Node {
    public String name = "Entity";
    public Vector3f posG = new Vector3f();
    public Vector3f rotG = new Vector3f();
    public Vector3f scaleG = new Vector3f(1,1,1);
    public Vector3f posL = new Vector3f();
    public Vector3f rotL = new Vector3f();
    public Vector3f scaleL = new Vector3f(1,1,1);
    public Matrix4f mat = new Matrix4f();

    public Entity() {
    }
    
    public Entity(Entity parent) {
        super(parent);
    }
    /*
    public Entity(Entity parent, File mesh){
        super(parent);
        this.addAll(MeshParser.meshFromFile(mesh, false));
        if(this.children.isEmpty()) throw new IllegalArgumentException("Mesh file has no mesh data!");
    } 
    
    public void forEachMesh(Consumer<Mesh> run){
        for (Node node : children) {
            if (node instanceof Mesh) {
                Mesh m = (Mesh)node;
                run.accept(m);
            }
            if(node instanceof Entity){
                Entity e = (Entity)node;
                e.forEachMesh(run);
            }
        }
    }
    */
    
    final public void updateVectors(){
        if(parent!=null){
            if(parent instanceof Entity){
                Entity parent = (Entity) this.parent;
                posG = new Vector3f(parent.posG).add(new Vector3f(posL).mul(parent.scaleG));
                rotG = new Vector3f(parent.rotG).add(rotL);
                scaleG = new Vector3f(parent.scaleG).add(scaleL).sub(new Vector3f(1,1,1));
            }
        } else {
            posG = posL;
            rotG = rotL;
            scaleG = scaleL;
        }
    }
    
    /**
     *  Updates the PosRotScale matrix, should be called after changing the vectors.
     */
    public void updateMatrix(){
        updateVectors();
        
        mat.identity();
        mat.translate(posG);
        mat.rotateZYX(rotG);
        mat.scale(scaleG);
        
        children.forEach((t) -> {
            ((Entity) t).updateMatrix();
        });
    }
}
