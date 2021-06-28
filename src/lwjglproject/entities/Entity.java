package lwjglproject.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.joml.*;

public class Entity extends Node {
    public String name = "Entity";
    protected Vector3f posG = new Vector3f();
    protected Vector3f rotG = new Vector3f();
    protected Vector3f scaleG = new Vector3f(1,1,1);
    protected Vector3f posL = new Vector3f();
    protected Vector3f rotL = new Vector3f();
    protected Vector3f scaleL = new Vector3f(1,1,1);
    protected Matrix4f mat = new Matrix4f();
    protected boolean needsUpdate = false;

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
    */
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
    
    @Override
    public void draw(Camera cam){
        if(!visible)return;
        
        drawChildren(cam);
    }
    
    @Override
    public void update(){
        if (!visible) return; 

        checkUpdate();
        
        updateChildren();
    }
    
    protected void checkUpdate(){
        if(needsUpdate){
            updateMatrix();
            needsUpdate = false;
        }
    }
    
    final protected void updateVectors(){
        if(parent!=null){
            if(parent instanceof Entity){
                Entity parent = (Entity) this.parent;
                posG=new Vector3f(parent.getPosG()).add(new Vector3f(getPosL()).mul(parent.getScaleG()));
                rotG=new Vector3f(parent.getRotG()).add(getRotL());
                scaleG=(new Vector3f(parent.getScaleG()).add(getScaleL()).sub(new Vector3f(1,1,1)));
            }
        } else {
            posG=getPosL();
            rotG=getRotL();
            scaleG=getScaleL();
        }
    }
    
    /**
     *  Updates the PosRotScale matrix, should be called after changing the vectors.
     */
    protected void updateMatrix(){
        updateVectors();
        
        getMat().identity();
        getMat().translate(getPosG());
        getMat().rotateZYX(getRotG());
        getMat().scale(getScaleG());
    }
    
    protected void requestUpdate(){
        needsUpdate = true;
        children.forEach((t) -> {
            if(t instanceof Entity){
                ((Entity) t).needsUpdate = true;
            }
        });
    }

    public Vector3f getPosG() {
        return posG;
    }

    public Vector3f getRotG() {
        return rotG;
    }

    public Vector3f getScaleG() {
        return scaleG;
    }

    public Vector3f getPosL() {
        return posL;
    }

    public void setPosL(Vector3f posL) {
        this.posL = posL;
        requestUpdate();
    }

    public Vector3f getRotL() {
        return rotL;
    }

    public void setRotL(Vector3f rotL) {
        this.rotL = rotL;
        requestUpdate();
    }

    public Vector3f getScaleL() {
        return scaleL;
    }

    public void setScaleL(Vector3f scaleL) {
        this.scaleL = scaleL;
        requestUpdate();
    }

    public Matrix4f getMat() {
        return mat;
    }
}
