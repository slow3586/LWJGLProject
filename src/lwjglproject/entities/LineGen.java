package lwjglproject.entities;

import java.util.ArrayList;
import lwjglproject.gl.materials.Material;
import lwjglproject.gl.shaders.SPSolidColor;
import lwjglproject.gl.vertexarrays.VertexArray;
import lwjglproject.gl.vertexarrays.VertexArrayPIC;
import org.joml.Matrix2f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class LineGen extends Entity {

    public LineGen(Entity parent) {
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
        
        SPSolidColor.draw(cam.mat, this.mat, new Vector4f(1,0,0,1), vertexArray);
        
        if(children.isEmpty()) return;
        
        children.forEach((t) -> {
            ((Entity)t).draw(cam);
        });
    } 
    
    private void render(){
        if(points.size()<2) return;
        
        LinePoint prevPoint = null;
        ArrayList<Vector2f> verts = new ArrayList<>();
        if(loops){
            points.add(points.get(0));
        }
        for (LinePoint currPoint : points) {
            if(prevPoint==null){
                prevPoint = currPoint;
                continue;
            }
            
            Vector2f vecDir = new Vector2f(currPoint.pos).sub(prevPoint.pos).normalize();
            Matrix2f rotCW = new Matrix2f().rotation((float) Math.toRadians(90));
            Matrix2f rotCCW = new Matrix2f().rotation((float) Math.toRadians(-90));
            Vector2f v0new = new Vector2f(prevPoint.pos)
                    .add(new Vector2f(vecDir).mul(prevPoint.size));
            verts.add(
                    new Vector2f(v0new)
                            .add(new Vector2f(vecDir).mul(rotCW).mul(prevPoint.size)) 
            );
            verts.add(
                    new Vector2f(v0new)
                            .add(new Vector2f(vecDir).mul(rotCCW).mul(prevPoint.size)) 
            );
            
            Vector2f v1new = new Vector2f(currPoint.pos)
                    .sub(new Vector2f(vecDir).mul(currPoint.size));
            verts.add(
                    new Vector2f(v1new)
                            .add(new Vector2f(vecDir).mul(rotCW).mul(currPoint.size)) 
            );
            verts.add(
                    new Vector2f(v1new)
                            .add(new Vector2f(vecDir).mul(rotCCW).mul(currPoint.size)) 
            );
            
            prevPoint = currPoint;
        }
        
        
        float[] posArr = new float[verts.size()*3];
        for (int i = 0; i < verts.size(); i++) {
            Vector2f v = verts.get(i);
            posArr[i*3] = v.x;
            posArr[i*3+1] = v.y;
            posArr[i*3+2] = 0;
        }
        vertexArray.setPosBuf(posArr);
        
        int indArraySize = (points.size()*2-3)*6;
        if(loops)
            indArraySize +=6;
        int[] indArr = new int[indArraySize];
        for (int i = 0; i < points.size()*2-3; i++) {
            indArr[i*6] = i*2;
            indArr[i*6+1] = i*2+1;
            indArr[i*6+2] = i*2+2;
            indArr[i*6+3] = i*2+1;
            indArr[i*6+4] = i*2+2;
            indArr[i*6+5] = i*2+3;
        }
        if(loops){
            int i = (points.size()*2-3);
            indArr[i*6] = i*2;
            indArr[i*6+1] = i*2+1;
            indArr[i*6+2] = 0;
            indArr[i*6+3] = i*2+1;
            indArr[i*6+4] = 0;
            indArr[i*6+5] = 1;
        }
        vertexArray.setIndBuf(indArr);
        
        Vector4f[] colorArr = new Vector4f[points.size()*2];
        for (int i = 0; i < points.size(); i++) {
            colorArr[i*2]=points.get(i).color;
            colorArr[i*2+1]=points.get(i).color;
        }
        vertexArray.setVertexColorBuf(colorArr);
        
        if(loops){
            points.remove(points.size()-1);
        }
    }
}
