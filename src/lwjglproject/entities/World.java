package lwjglproject.entities;

import com.sun.istack.internal.NotNull;
import java.util.Objects;

public class World extends Entity{
    public Camera camera = null;

    protected World() {
    }
    
    public World(Camera camera){
        Objects.nonNull(camera);
        this.camera = camera;
    }
    
    public void draw(){
        if(!visible)return;
        children.forEach((t) -> {
            t.draw(camera);
        });
    }
}
