package engine;

import org.joml.Vector3f;

import model.ModelEntity;
import model.TexturedModel;
import render.Renderer;
import shader.BasicShader;

//Removes need to reference the static state and behaviors of the class.
//import static org.lwjgl.opengl.GL11.*;

//import org.lwjgl.glfw.GLFWVidMode; 
//import org.lwjgl.opengl.GL;

public class Main {
	
	//Base boiler plate code
	
	/*
	public Main() {
		if(!glfwInit()) {
			System.err.println("GLFW failed to initialize!");
			System.exit(1);
		}
		
		long window = glfwCreateWindow(640, 480, "LWJGL", 0, 0);
		
		if(window == 0) {
			throw new IllegalStateException("Failed to create window!");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - 640) / 2, (videoMode.height() - 480) / 2);
		
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glClearColor(0.5f, 0.0f, 0.0f, 0.0f);
		
		while(!glfwWindowShouldClose(window)) {
			glfwPollEvents();
			
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBegin(GL_QUADS);
				glColor4f(1,0,0,0);
				glVertex2f(-0.5f, 0.5f);
				glColor4f(0,1,1,0);
				glVertex2f(0.5f, 0.5f);
				glVertex2f(0.5f, -0.5f);
				glColor4f(1,0,1,0);
				glVertex2f(-0.5f, -0.5f);
			glEnd();
			
			glfwSwapBuffers(window);
		}
		
		glfwTerminate();
	}
	*/
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int FPS = 60;
	
	public static BasicShader shader = new BasicShader();
	public static Renderer renderer = new Renderer(shader);
	
	public static void main(String[] args) {
		//new Main();
		Window window = new Window(800, 600, 60, "LWJGL");
		window.create();
		window.setBackgroundColor(1.0f, 0.3f, 0.4f);
		
		shader.create();
		
		TexturedModel model = new TexturedModel(
			new float[] {
				-0.5f, 0.5f, 0.0f, //Top left 0
				0.5f, 0.5f, 0.0f,  //Top right 1
				0.5f, -0.5f, 0.0f, //Bot right 2
				-0.5f, -0.5f, 0.0f,//Bot left 3
			}, 
			new float[] {
				0, 0,
				1, 0,
				1, 1,
				0, 1
			},
			new int[] {
				0, 1, 2,
				2, 3, 0
			}, "beautiful.png");
		
		ModelEntity entity = new ModelEntity(model, new Vector3f(-1, 0, 0), new Vector3f(0,0,0), new Vector3f(1, 0.5f, 1));
		
		while(!window.closed()) {
			if(window.isUpdating()) {
				window.update();
				shader.bind();
				renderer.renderModelEntity(entity);
				window.swapBuffers();
			}
		}
		
		shader.remove();
		model.remove();
		window.stop();
	}
}
