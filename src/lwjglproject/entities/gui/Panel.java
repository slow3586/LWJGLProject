package lwjglproject.entities.gui;

import lwjglproject.entities.*;
import lwjglproject.gl.*;
import org.joml.*;

public class Panel extends Node {
    public Vector2i posL = new Vector2i();
    public Vector2i posG = new Vector2i();
    public Vector2i size = new Vector2i();
    public Matrix4f mat = new Matrix4f();
    
    public Panel(Panel parent) {
        if(parent!=null)
            parent.add(this);
        else{
            Gui.ins.add(this);
        }
    }

    public Vector2i getBiggestChild(){
        Vector2i b = new Vector2i();
        for (Object node : children) {
            Panel c = (Panel)node;
            if(c.size.x>b.x || c.size.y>b.y)
                b = c.size;
        }
        return b;
    }
    
    public void fitParent(Vector2i margin){
        if(parent==null)return;
        Panel p = (Panel)parent;
        posL = new Vector2i(margin);
        size.x = p.size.x-margin.x*2;
        size.y = p.size.y-margin.y*2;
    }
    
    @Deprecated
    public void fitChildren(Vector2i margin){
        Vector2i b = new Vector2i();
        for (Object node : children) {
            Panel c = (Panel)node;
            if(c.posL.x+c.size.x>b.x || c.posL.y+c.size.y>b.y)
                b = new Vector2i(c.posL.x+c.size.x, c.posL.y+c.size.y);
        }
        size = new Vector2i(b.x+margin.x, b.y+margin.y);
    }

    public void arrange(ArrangeDir dir, ArrangeType type, Vector2i margin){
        margin = new Vector2i(margin);
        if(type == ArrangeType.SPREAD){
            Vector2i ov = new Vector2i();
            for (Object node : children) {
                ov.add(((Panel) node).size);
            }
            if(dir == ArrangeDir.HOR){
                margin.x = (size.x - ov.x) / (children.size()+1);
            }else{
                margin.y = (size.y - ov.y) / (children.size()+1);
            }
        }
        Vector2i all = new Vector2i(margin);
        for (int i = 0; i < children.size(); i++) {
            Panel p = (Panel)children.get(i);
            if(dir == ArrangeDir.HOR){
                p.posL = new Vector2i(all.x, margin.y);
            }else{
                p.posL = new Vector2i(margin.x, all.y);
            }
            p.updateMatrix();
            all.add(margin);
            all.add(p.size);
        }
        if(type == ArrangeType.RESIZE){
            Vector2i b = getBiggestChild();
            if(dir == ArrangeDir.HOR){
                size.x = all.x;
                size.y = b.y + margin.y * 2;
            }else{
                size.x = b.x + margin.x * 2;
                size.y = all.y;
            }
        }
    }
    
    public enum ArrangeDir{
        HOR,
        VERT
    }
    
    public enum ArrangeType{
        PLAIN,
        SPREAD,
        RESIZE
    }
    
    public void updateRects(){
        if(parent!=null){
            if(parent instanceof Panel)
                posG = new Vector2i(posL).add(((Panel)parent).posG);
            else{
                Entity p = (Entity)parent;
                posG = new Vector2i(posL).add(new Vector2i((int)p.posG.x, (int)p.posG.y));
            }
        } else {
            posG = posL;
        }
    }
    
    /**
     *  Updates the PosRotScale matrix, should be called after changing the vectors.
     */
    public void updateMatrix(){
        updateRects();
        
        mat.identity();
        mat.translate(posG.x, -posG.y, 0);
        mat.scale(size.x, -size.y, 1);
        
        children.forEach((t) -> {
            ((Panel) t).updateMatrix();
        });
    }
}
