import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import renderEngine.DisplayManager;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    public static void updateDisplay(long windowID) {
        GL.createCapabilities();
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        while ( !glfwWindowShouldClose(windowID) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            glfwSwapBuffers(windowID); // swap the color buffers
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        long windowID = DisplayManager.createDisplay();


        // Given that the escape key has been release, the window will be asked to close down.
        glfwSetKeyCallback(windowID, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        Main.updateDisplay(windowID);

        // Clean up and destroy all windows ever created.
        DisplayManager.destroyDisplay(windowID);
    }
}