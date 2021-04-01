package lwjglproject.entities.prim3d;

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
        l.setPosL(new Vector3f(0.5f,-0.5f,-0.5f));
        l.updateMatrix();
        
        r = Plane.planeXN(this);
        r.setPosL(new Vector3f(-0.5f,-0.5f,-0.5f));
        r.updateMatrix();
        
        b = Plane.planeYP(this);
        b.setPosL(new Vector3f(-0.5f,0.5f,-0.5f));
        b.updateMatrix();
        
        f = Plane.planeYN(this);
        f.setPosL(new Vector3f(-0.5f,-0.5f,-0.5f));
        f.updateMatrix();
        
        u = Plane.planeZP(this);
        u.setPosL(new Vector3f(-0.5f,-0.5f,0.5f));
        u.updateMatrix();
        
        d = Plane.planeZN(this);
        d.setPosL(new Vector3f(-0.5f,-0.5f,-0.5f));
        d.updateMatrix();
        
        
    }
}
