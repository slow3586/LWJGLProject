package lwjglproject;

import lwjglproject.scenes.SceneTest;
import lwjglproject.scenes.Scene;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.Version;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.GL_FUNC_ADD;
import static org.lwjgl.opengl.GL14.glBlendEquation;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;
import sun.awt.DisplayChangedListener;

public class App {
    private long window;
    public static int w = 640;
    public static int h = 480;
    public static App ins = new App();
    public static boolean close = false;
    public static double lastFrameTime;
    public static float fpsRequired = 60;
    public static float delta = 0;
    public static float fps = 0;

    public static void main(String[] args) {
            App.ins.run();
    }

    public void run() {
            init();
            double lasttime = glfwGetTime();
            while ( !close ) {
                loop();
            }

            glfwSetWindowShouldClose(window, true);
            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);

            glfwTerminate();
            glfwSetErrorCallback(null).free();
    }

    private void init() {
            GLFWErrorCallback.createPrint(System.err).set();

            if ( !glfwInit() )
                    throw new IllegalStateException("Unable to initialize GLFW");

            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

            window = glfwCreateWindow(w, h, "Hello World!", NULL, NULL);
            if ( window == NULL )
                    throw new RuntimeException("Failed to create the GLFW window");

            glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
                Keyboard.onKeyPress(window, key, scancode, action, mods);
            });

            glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
                Mouse.onMouseMove(window, xpos, ypos);
            });

            glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
                Mouse.onMouseButtonPress(window, button, action, mods);
            });

            glfwSetCursorEnterCallback(window, (window, entered) -> {
                Mouse.onMouseEnter(window, entered);
            });

            try ( MemoryStack stack = stackPush() ) {
                    IntBuffer pWidth = stack.mallocInt(1);
                    IntBuffer pHeight = stack.mallocInt(1);

                    glfwGetWindowSize(window, pWidth, pHeight);

                    GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

                    glfwSetWindowPos(
                            window,
                            (vidmode.width() - pWidth.get(0)) / 2,
                            (vidmode.height() - pHeight.get(0)) / 2
                    );
            }
            glfwMakeContextCurrent(window);
            //glfwSwapInterval(1);

            glfwShowWindow(window);
            GL.createCapabilities();

            glClearColor(0.0f, 0.1f, 0.0f, 0.0f);
            glEnable(GL_TEXTURE_2D);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            glBlendEquation(GL_FUNC_ADD);
            glDepthFunc(GL_LEQUAL);
            glEnable(GL_CULL_FACE);
            glCullFace(GL_BACK);
            glFrontFace(GL_CCW); 
            glEnable(GL_DEPTH_TEST);
            Scene.setScene(SceneTest.ins);
    }

    private void loop() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        Scene.getCurrent().update();

        glfwSwapBuffers(window);
        Keyboard.reset();
        Mouse.reset();
        glfwPollEvents();

        double nextFrameTime = lastFrameTime + 1.0f/fpsRequired;
        double currentFrameTime = glfwGetTime();
        if(currentFrameTime < nextFrameTime){
            try {
                Thread.sleep((long) ((nextFrameTime - currentFrameTime)*1000.0));
            } catch (InterruptedException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        delta = (float) (glfwGetTime() - lastFrameTime);
        fps = 1/delta;
        lastFrameTime = glfwGetTime();
    }
}