package lwjglproject.entities;

import java.util.ArrayList;

public class Node {
    public ArrayList<Node> children = new ArrayList<>();
    public Node parent = null;
    public boolean visible = true;
    
    public Node(){        
    }
    
    public Node(Node parent) {
        if(parent!=null)
            parent.add(this);
    }
    
    final public void add(Node e){
        children.add(e);
        e.parent = this;
    }
    
    final public void addAll(ArrayList nodes){
        for (Object o : nodes) {
            Node node = (Node)o;
            children.add(node);
            node.parent = this;
        }
    }
    
    final public void remove(Node e){
        children.remove(e);
        e.parent = null;
    }
    
    final public void clearChildren(){
        children.forEach((child) -> {
            child.parent = null;
        });
        children.clear();
    }
    
    public void draw(Camera cam){
        if(!visible)return;
        children.forEach((t) -> {
            t.draw(cam);
        });
    }
    
    public void update(){
        if (!visible) return;  
        
        children.forEach((t) -> {
            t.update();
        });
    }
}
