package lwjglproject.entities.gui;

import java.awt.Color;
import lwjglproject.TextToTexture;
import lwjglproject.entities.*;
import lwjglproject.gl.*;

public class Label extends Image {

    private String text;
    public TextToTexture font;

    public Label(Panel parent, String text, TextToTexture font){
        super(parent,null);
        this.font = font;
        setText(text);
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        setTexture(font.renderAWT(text, Color.white));
    }

}
