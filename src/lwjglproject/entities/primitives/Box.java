package lwjglproject.entities.primitives;

import lwjglproject.entities.Entity;
import org.joml.*;

public class Box extends Entity {
    public Plane f;
    public Plane b;
    public Plane l;
    public Plane r;
    public Plane u;
    public Plane d;

    public Box(Entity parent) {
        super(parent);
        
        l = Plane.planeXP(this);
        l.posL = new Vector3f(0.5f,-0.5f,-0.5f);
        l.updateMatrix();
        
        r = Plane.planeXN(this);
        r.posL = new Vector3f(-0.5f,-0.5f,-0.5f);
        r.updateMatrix();
        
        b = Plane.planeYP(this);
        b.posL = new Vector3f(-0.5f,0.5f,-0.5f);
        b.updateMatrix();
        
        f = Plane.planeYN(this);
        f.posL = new Vector3f(-0.5f,-0.5f,-0.5f);
        f.updateMatrix();
        
        u = Plane.planeZP(this);
        u.posL = new Vector3f(-0.5f,-0.5f,0.5f);
        u.updateMatrix();
        
        d = Plane.planeZN(this);
        d.posL = new Vector3f(-0.5f,-0.5f,-0.5f);
        d.updateMatrix();
        
        
    }
}
