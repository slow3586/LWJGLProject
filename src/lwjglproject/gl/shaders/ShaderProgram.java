package lwjglproject.gl.shaders;

import lwjglproject.gl.vertexarrays.VertexArray;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import lwjglproject.entities.Camera;
import lwjglproject.entities.Entity;
import lwjglproject.entities.Mesh;
import lwjglproject.gl.materials.Material;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author lia
 */
public abstract class ShaderProgram {
    final int id = GL20.glCreateProgram();
    final List<Integer> shaders = new ArrayList<>();
    
    protected ShaderProgram(){};
    
    /**
     *  The shader program that is currently used in drawing.
     */
    protected static ShaderProgram current;
    
    abstract public void draw(Camera cam, Entity ent, VertexArray varr, Material mat);
    
    /**
     * Reads the text file resource and adds it to the shader program as shader code.
     * @param resourceClass Resource root class.
     * @param name Name of the text file resource that contains the shader code.
     * @param type GL.GL_VERTEX_SHADER or GL.GL_FRAGMENT_SHADER
     */
    protected void addShader(Class resourceClass, String name, int type) {
        int shaderID = glCreateShader(type);

        InputStream stream = resourceClass.getResourceAsStream(name);
        if(stream == null)
            throw new IllegalArgumentException("no such shader file "+name);

        Scanner scan = new Scanner(stream);
        ArrayList<String> text = new ArrayList<>();
        while(scan.hasNextLine())
            text.add(scan.nextLine()+"\n");
        String[] shaderCode = text.toArray(new String[0]);
       
        glShaderSource(shaderID, shaderCode);
        glCompileShader(shaderID);
        
        IntBuffer isCompiled = IntBuffer.allocate(1);
        isCompiled.put(glGetShaderi(shaderID, GL_COMPILE_STATUS));
        if (isCompiled.get(0) == GL_FALSE) {
            IntBuffer max_length = IntBuffer.allocate(1);
            ByteBuffer errorLog = ByteBuffer.allocate(4096);
            max_length.put(glGetShaderi(shaderID, GL_INFO_LOG_LENGTH));
            glGetProgramInfoLog(id, max_length, errorLog);
            System.err.println("Shader comp failed! " + new String(errorLog.array(), StandardCharsets.UTF_8));
        }
        glAttachShader(id, shaderID);
        shaders.add(shaderID);
    }

    /**
     * @param name Name of the uniform as set in the shader.
     * @return Position of the uniform.
     */
    final public int getUniformPos(String name){
        int i = glGetUniformLocation(id, name);
        if(i==-1)
            throw new IllegalStateException();
        return i;
    }

    /**
     * Links the program for use in rendering. Must be done after adding shaders.
     * Will throw an error on failure.
     * @throws IllegalStateException
     */
    final public void link() {
        glLinkProgram(id);
        IntBuffer isLinked = IntBuffer.allocate(1);
        isLinked.put(glGetProgrami(id, GL_LINK_STATUS));
        if (isLinked.get(0) == GL_FALSE) {
            IntBuffer max_length = IntBuffer.allocate(1);
            max_length.put(glGetProgrami(id, GL_INFO_LOG_LENGTH));
            ByteBuffer errorLog = ByteBuffer.allocate(4096);
            glGetProgramInfoLog(id, max_length, errorLog);
            throw new IllegalStateException("Shader linking failed! " + id + " " + new String(errorLog.array()));
        }
    }


    /**
     *  Binds this program for use in rendering. Must be done before calling draw functions.
     */
    final public void use() {
        if(current != this){
            glUseProgram(id);
            current = this;
        }
    }
}
