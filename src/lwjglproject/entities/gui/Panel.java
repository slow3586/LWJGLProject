package lwjglproject.entities.gui;

import lwjglproject.entities.*;
import lwjglproject.gl.*;
import org.joml.*;

public class Panel extends Entity {
    protected Vector2i size = new Vector2i();

    public void setPosL(Vector2i posL) {
        setPosL(posL.x, posL.y);
    }
    
    public void setPosL(int x, int y) {
        this.posL.x = x;
        this.posL.y = y;
        requestUpdate();
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        setSize(size.x, size.y);
    }
    
    public Vector2i getPosGInt(){
        return new Vector2i((int)posG.x, (int)posG.y);
    }
    
    public void setSize(int x, int y) {
        this.size.x = x;
        this.size.y = y;
        requestUpdate();
    }
    
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
        setPosL(new Vector2i(margin));
        size.x = p.size.x-margin.x*2;
        size.y = p.size.y-margin.y*2;
    }
    
    public boolean isInside(Vector2i point){
        if(point.x>=this.posG.x && point.y>=this.posG.y
                && point.x<this.posG.x+this.size.x && point.y<this.posG.y+this.size.y)
            return true;
                    
        return false;
    }
    
    /*
    @Deprecated
    public void fitChildren(Vector2i margin){
        Vector2i b = new Vector2i();
        for (Object node : children) {
            Panel c = (Panel)node;
            if(c.posL.x+c.size.x>b.x || c.posL.y+c.size.y>b.y)
                b = new Vector2i(c.posL.x+c.size.x, c.posL.y+c.size.y);
        }
        size = new Vector2i(b.x+margin.x, b.y+margin.y);
    }*/
    
    @Override
    protected void updateMatrix(){
        updateVectors();
        
        mat.identity();
        mat.translate(posG.x, -posG.y, posG.z);
        mat.scale(size.x, -size.y, 1);
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
                p.setPosL(new Vector2i(all.x, margin.y));
            }else{
                p.setPosL(new Vector2i(margin.x, all.y));
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
}
