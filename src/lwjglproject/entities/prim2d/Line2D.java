package lwjglproject.entities.prim2d;

import java.util.ArrayList;
import lwjglproject.entities.Camera;
import lwjglproject.entities.Entity;
import lwjglproject.gl.materials.Material;
import lwjglproject.gl.shaders.SPSolidColor;
import lwjglproject.gl.shaders.SPVertexColor;
import lwjglproject.gl.vertexarrays.VertexArray;
import lwjglproject.gl.vertexarrays.VertexArrayPIC;
import org.joml.Matrix2f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Line2D extends Entity {

    public Line2D(Entity parent) {
        super(parent);
    }
    
    public static class LinePoint{
        Vector2f pos;
        Float size;
        Vector4f color;

        public LinePoint(Vector2f pos, Float size, Vector4f color) {
            this.pos = pos;
            this.size = size;
            this.color = color;
        }
    }
    
    VertexArrayPIC vertexArray = new VertexArrayPIC();
    public ArrayList<LinePoint> points = new ArrayList<>();
    public boolean needsRender = false;
    public boolean loops = false;
    
    @Override
    public void draw(Camera cam) {
        if(!visible) return;

        if(needsRender) {
            render();
            needsRender = false;
        }
        
        SPVertexColor.draw(cam.getMat(), this.getMat(), vertexArray);
        
        if(children.isEmpty()) return;
        
        children.forEach((t) -> {
            ((Entity)t).draw(cam);
        });
    } 
    
    private void render(){
        if(points.size()<2) return;
        
        ArrayList<LinePoint> newPoints = new ArrayList<>(points);
        if(loops)
            newPoints.add(newPoints.get(0));
        
        ArrayList<Vector2f> verts = new ArrayList<>();
        ArrayList<Vector4f> colors = new ArrayList<>();
        LinePoint prevPoint = null;
        Matrix2f rotCW = new Matrix2f().rotation((float) Math.toRadians(90));
        Matrix2f rotCCW = new Matrix2f().rotation((float) Math.toRadians(-90));
        for (int i = 0; i < newPoints.size(); i++) {
            LinePoint currPoint = newPoints.get(i);
            if(prevPoint==null){
                prevPoint = currPoint;
                continue;
            }
            
            Vector2f vecDir = new Vector2f(currPoint.pos).sub(prevPoint.pos).normalize();
            Vector2f v0new = new Vector2f(prevPoint.pos);
            if(i==1 && !loops){ //extend first point back
                v0new.add(new Vector2f(vecDir).mul(prevPoint.size));
            }else{
                v0new.sub(new Vector2f(vecDir).mul(prevPoint.size));
            }
            verts.add(
                    new Vector2f(v0new)
                            .add(new Vector2f(vecDir).mul(rotCW).mul(prevPoint.size)) 
            );
            colors.add(prevPoint.color);
            verts.add(
                    new Vector2f(v0new)
                            .add(new Vector2f(vecDir).mul(rotCCW).mul(prevPoint.size)) 
            );
            colors.add(prevPoint.color);
            
            Vector2f v1new = new Vector2f(currPoint.pos);
            if(i==newPoints.size()-1 && !loops){
                v1new.sub(new Vector2f(vecDir).mul(currPoint.size));
            }else{
                v1new.add(new Vector2f(vecDir).mul(currPoint.size));
            }
            verts.add(
                    new Vector2f(v1new)
                            .add(new Vector2f(vecDir).mul(rotCW).mul(currPoint.size)) 
            );
            colors.add(currPoint.color);
            verts.add(
                    new Vector2f(v1new)
                            .add(new Vector2f(vecDir).mul(rotCCW).mul(currPoint.size)) 
            );
            colors.add(currPoint.color);
            
            prevPoint = currPoint;
        }        
        vertexArray.setPosBuf(verts, 0);
        
        int indArraySize = (newPoints.size()*2-3)*6;
        if(loops)
            indArraySize +=6;
        int[] indArr = new int[indArraySize];
        for (int i = 0; i < newPoints.size()*2-3; i++) {
            indArr[i*6] = i*2;
            indArr[i*6+1] = i*2+1;
            indArr[i*6+2] = i*2+2;
            indArr[i*6+3] = i*2+1;
            indArr[i*6+4] = i*2+2;
            indArr[i*6+5] = i*2+3;
        }
        if(loops){
            int i = (newPoints.size()*2-3);
            indArr[i*6] = i*2;
            indArr[i*6+1] = i*2+1;
            indArr[i*6+2] = 0;
            indArr[i*6+3] = i*2+1;
            indArr[i*6+4] = 0;
            indArr[i*6+5] = 1;
        }
        vertexArray.setIndBuf(indArr);
        
        vertexArray.setVertexColorBuf(colors);
    }
}
