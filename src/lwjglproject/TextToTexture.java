package lwjglproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import lwjglproject.gl.Texture;
import org.joml.*;

public class TextToTexture {

    public static TextToTexture def = fromAWT(new Font("Arial", Font.PLAIN, 16));
    
    public Texture tex;
    final public Vector2i letterSize = new Vector2i(17,23);
    
    public Font awtFont = null;
    public TextToTexture() {
    }
    
    public static TextToTexture fromAWT(Font awtfont){
        TextToTexture n = new TextToTexture();
        n.awtFont = awtfont;
        return n;
    }
    
    public Texture renderAWT(String text, Color color){
        FontRenderContext c = new FontRenderContext(awtFont.getTransform(), true, true);
        Rectangle2D r = awtFont.getStringBounds(text, c);
        LineMetrics m = awtFont.getLineMetrics(text, c);
        int w = (int)r.getWidth();
        int h = (int)m.getHeight();
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0, 0, w, h);
        g.setColor(color);
        g.setFont(awtFont);
        g.drawString(text, 0, h - (int)m.getDescent() - 1);
        
        return Texture.fromBufferedImage(img);
    }
    
    public Texture render(String txt){
        Vector2i size = new Vector2i((txt.length())*letterSize.x, letterSize.y);
        Texture txtTex = new Texture(size);
        for (int i = 0; i < txt.length(); i++) {
            Vector2i lp = new Vector2i(0,0);
            int li = (int) txt.charAt(i) - 32;
            int count = li;
            while(count >= 16){
                count -= 16;
                lp.y++;
            }
            lp.x=count;
            tex.copyData(txtTex, i * letterSize.x, 0, lp.x * letterSize.x, lp.y * letterSize.y, letterSize.x, letterSize.y);
        }
        return txtTex;
    }
    
}
