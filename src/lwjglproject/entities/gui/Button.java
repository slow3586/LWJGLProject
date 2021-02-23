package lwjglproject.entities.gui;
import lwjglproject.Mouse;
import lwjglproject.gl.*;
public abstract class Button extends Panel {
    
    boolean isMouseOver = false;
    boolean isDown = false;
    boolean isClicked = false;
    boolean isClickable = true;
    
    public Button(Panel parent) {
        super(parent);
    }
    
    @Override
    public void update() {
        if (!visible) return;
        
        //isMouseOver = Rect.contains(posG, size, Mouse.pos);// && !Mouse.consumedMouseOver;
        if(isMouseOver && isClickable){
            Mouse.consumedMouseOver = true;
            //isDown = !Mouse.consumed.contains(Mouse.LEFT) && Mouse.down.contains(Mouse.LEFT);
            //isClicked = !Mouse.consumed.contains(Mouse.LEFT) && Mouse.released.contains(Mouse.LEFT);
        } else {
            isDown = false;
            isClicked = false;
        }
        
        if(isClicked){
            onClick();
            //Mouse.consumed.add(Mouse.LEFT);
        }
        
        children.forEach((t) -> {
            ((Panel) t).update();
        });
    }
    
    public abstract void onClick();
}
