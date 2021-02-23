package lwjglproject.entities.gui;

import lwjglproject.TextToTexture;
import lwjglproject.entities.*;
import lwjglproject.gl.*;
import org.joml.*;
public abstract class ButtonText extends ButtonRect {

    Label label;
    
    public ButtonText(Panel parent, String text, TextToTexture font) {
        super(parent);
        label = new Label(this, text, font);
        arrange(Panel.ArrangeDir.HOR, Panel.ArrangeType.RESIZE, new Vector2i(5,5));
    }
    
    public String getText(){
        return label.getText();
    }
    
    public void setText(String text){
        label.setText(text);
        arrange(Panel.ArrangeDir.HOR, Panel.ArrangeType.RESIZE, new Vector2i(5,5));
    }
}
