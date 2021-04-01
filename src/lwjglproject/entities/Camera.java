package lwjglproject.entities;

import lwjglproject.App;
import lwjglproject.Mouse;
import lwjglproject.entities.Entity;
import org.joml.*;


public class Camera extends Entity{

    public static final Vector3f up2D = new Vector3f(0,1,0);
    public static final Vector3f down2D = new Vector3f(0,-1,0);
    public static final Vector3f forward2D = new Vector3f(0,0,-1);
    public static final Vector3f back2D = new Vector3f(0,0,1);
    public static final Vector3f right2D = new Vector3f(1,0,0);
    public static final Vector3f left2D = new Vector3f(-1,0,0);
    
    public static final Vector3f up3D = new Vector3f(0,0,1);
    public static final Vector3f down3D = new Vector3f(0,0,-1);
    public static final Vector3f forward3D = new Vector3f(0,1,0);
    public static final Vector3f back3D = new Vector3f(0,-1,0);
    public static final Vector3f right3D = new Vector3f(1,0,0);
    public static final Vector3f left3D = new Vector3f(-1,0,0);
    
    public Vector3f up;
    public Vector3f down;
    public Vector3f left;
    public Vector3f right;
    public Vector3f forward;
    public Vector3f back;
    
    public Matrix4f proj = new Matrix4f();
    public Matrix4f view = new Matrix4f();
    public PROJ_TYPE projType;
    public FrustumIntersection fi  = new FrustumIntersection();
    
    private Camera() {
        super(null);
    }
    
    public static Camera cam2D(){
        Camera c = new Camera();
        c.setPosL(new Vector3f(0,0,100));
        c.up = new Vector3f(up2D);
        c.down = new Vector3f(down2D);
        c.left = new Vector3f(left2D);
        c.right = new Vector3f(right2D);
        c.forward = new Vector3f(forward2D);
        c.back = new Vector3f(back2D);
        c.orthoCenter(1.0f);
        return c;
    }
    
    public static Camera cam2DFlipped(){
        Camera c = Camera.cam2D();
        c.setPosL(new Vector3f(0,0,-100));
        c.up.negate();
        c.down.negate();
        c.forward.negate();
        c.back.negate();
        c.orthoCenter(1.0f);
        return c;
    }
    
    public static Camera cam3D(){
        Camera c = new Camera();
        c.up = new Vector3f(up3D);
        c.down = new Vector3f(down3D);
        c.left = new Vector3f(left3D);
        c.right = new Vector3f(right3D);
        c.forward = new Vector3f(forward3D);
        c.back = new Vector3f(back3D);
        c.perspective(75);
        return c;
    }
    
    @Override
    public void updateMatrix() {
        updateVectors();
        
        getMat().identity();
        getMat().mul(proj);
        getMat().mul(view);
        
        fi.set(getMat(), false);
    }
    
    public void look(){
        Vector3f f = new Vector3f(forward);
        f.rotateZ(getRotL().z);
        f.rotateY(getRotL().y);
        f.rotateX(getRotL().x);
        
        lookAlong(f);
    }
    
    public void lookAt(Vector3f dir){
        lookAt(getPosL(), dir);
    }
    
    public void lookAlong(Vector3f dir){
        lookAlong(getPosL(), dir);
    }
    
    public void lookAt(Vector3f pos, Vector3f dir){
        setPosL(pos);
        view.identity();
        view.lookAt(getPosL(), dir, up);
        updateMatrix();
    }
    
    public void lookAlong(Vector3f pos, Vector3f dir){
        setPosL(pos);
        view.identity();
        view.lookAt(getPosL(), new Vector3f(getPosL()).add(dir), up);
        updateMatrix();
    }
    
    public RayAabIntersection mouseRay(){
        Vector3f o = new Vector3f();
        Vector3f d = new Vector3f();
        getMat().unprojectRay(Mouse.pos.x, App.h - Mouse.pos.y, new int[]{0, 0, App.w, App.h}, o, d);
        return new RayAabIntersection(o.x, o.y, o.z, d.x, d.y, d.z);
    }
    
    public void perspective(float fovDegrees){
        proj.identity();
        proj.perspective((float)java.lang.Math.toRadians(fovDegrees), (float)App.w/App.h, 0.01f, 1000.0f);
        projType = PROJ_TYPE.PERSPECTIVE;
        updateMatrix();
    }
    
    public void orthoCenter(float scale){
        proj.identity();
        proj.ortho(-App.w*0.5f * scale, App.w*0.5f * scale, -App.h*0.5f * scale, App.h*0.5f * scale, 0.01f, 1000);
        projType = PROJ_TYPE.ORTHOGONAL;
        updateMatrix();
    }
    
    public void orthoLeftBottom(float scale){
        proj.identity();
        proj.ortho(0, App.w * scale, 0, App.h * scale, 0.01f, 1000);
        projType = PROJ_TYPE.ORTHOGONAL;
        updateMatrix();
    }
    
    public enum PROJ_TYPE {
        PERSPECTIVE,
        ORTHOGONAL
    }
    
}
