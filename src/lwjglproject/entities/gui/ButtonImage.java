package lwjglproject.entities.gui;

import lwjglproject.gl.*;


public abstract class ButtonImage extends ButtonRect {

    Image image;
    
    public ButtonImage(Panel parent, Texture texture) {
        super(parent);
        image = new Image(this, texture);
        size = image.size;
    }
    
    public void setTexture(Texture t){
        image.setTexture(t);
    }
    
    public Texture getTexture(){
        return image.getTexture();
    }
}
