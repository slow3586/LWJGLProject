package lwjglproject.gl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import org.joml.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL14.*;

final public class Texture {

    public static Texture current = null;
    IntBuffer id = IntBuffer.allocate(1);
    public Vector2i size = null;
    
    private Texture(){
        glGenTextures(id);
        glBindTexture(GL_TEXTURE_2D, id.get(0));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_GENERATE_MIPMAP, GL_TRUE);
    }
    
    public Texture(Vector2i size) {
        this();
        newData(size);
    }
    
    public Texture(Texture t){
        this();
        newData(t.size, null);
        t.copyData(this);
    }
    
    /*
    public void setAsFullscreenImage(){
        FullscreenImage.texture = this;
    }
    
    public static Texture createDepthTexture(Vector2i size){
        Texture t = new Texture();
        t.size = size;
        gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, size.x, size.y, 0, GL_DEPTH_COMPONENT, GL_FLOAT, 0);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT); 
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        return t;
    }
*/    

    public static Texture fromFile(File file){
        try {
            BufferedImage img = ImageIO.read(file);
            return fromBufferedImage(img);
        } catch (IOException ex) {
            throw new IllegalStateException();
        }
    }
    
    public static Texture fromBufferedImage(BufferedImage img){
        int w = img.getWidth();
        int h = img.getHeight();
        Texture t = new Texture(new Vector2i(w,h));
        byte[] data = new byte[w*h*4];
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                //Color c = new Color(img.getRGB(y, h-x-1), true);
                Color c = new Color(img.getRGB(y, x), true);
                data[0 + y*4 + x*w*4] = (byte)c.getRed();
                data[1 + y*4 + x*w*4] = (byte)c.getGreen();
                data[2 + y*4 + x*w*4] = (byte)c.getBlue();
                data[3 + y*4 + x*w*4] = (byte)c.getAlpha();
            }
        }
        t.newData(new Vector2i(w,h), data);
        return t;
    }
    
    public Texture(Vector2i size, byte[] data) {
        this();
        newData(size, data);
    }
    
    public void newData(Vector2i size){
        newData(size, null);
    }
    
    public void fillData(byte[] texel){
        fillData(0, 0, size.x, size.y, texel);
    }
    
    public void fillData(int x, int y, int w, int h, byte[] texel){
        if(x < 0 || y < 0 || w < 0 || h < 0){
            throw new IllegalArgumentException();
        }
        if(x > size.x || y > size.y || w > size.x || h > size.y){
            throw new IllegalArgumentException();
        }
        if(x+w > size.x || y+h > size.y){
            throw new IllegalArgumentException();
        }
        byte[] data = new byte[w*h*4];
        for (int i = 0; i < w*h; i++) {
            data[i*4] = texel[0];
            data[i*4+1] = texel[1];
            data[i*4+2] = texel[2];
            data[i*4+3] = texel[3];
        }
        updateData(x, y, w, h, data);
    }
    
    public void newData(Vector2i size, byte[] data){
        bind();
        this.size = size;
        if(data == null){
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, size.x, size.y, 0, GL_RGBA, GL_UNSIGNED_BYTE, (ByteBuffer)null);
        } else {
            ByteBuffer b = ByteBuffer.wrap(data);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, size.x, size.y, 0, GL_RGBA, GL_UNSIGNED_BYTE, b);
        }
    }
    
    public void updateData(byte[] data){
        updateData(0, 0, size.x, size.y, data);
    }
    
    public void updateData(int x, int y, int w, int h, byte[] data){
        if(x < 0 || y < 0 || w < 0 || h < 0){
            throw new IllegalArgumentException();
        }
        if(x > size.x || y > size.y || w > size.x || h > size.y){
            throw new IllegalArgumentException();
        }
        if(x+w > size.x || y+h > size.y){
            throw new IllegalArgumentException();
        }
        bind();
        ByteBuffer b = ByteBuffer.wrap(data);
        glTexSubImage2D(GL_TEXTURE_2D, 0, x, y, w, h, GL_RGBA, GL_UNSIGNED_BYTE, b);
    }
    
    public void copyData(Texture to, int xto, int yto, int x, int y, int w, int h){
        //FrameBuffer.copyTexture(this, to, xto, yto, x, y, w, h);
    }
    
    public void copyData(Texture to){
        if(to.size.equals(size))
            copyData(to, 0, 0, 0, 0, size.x, size.y);
        else
            throw new IllegalArgumentException();
    }
    
    public void bind(){
        if(current != this){
            glBindTexture(GL_TEXTURE_2D, id.get(0));
            current = this;
        }
    }
    
    public static void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
        current = null;
    }
    
}
