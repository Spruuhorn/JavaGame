package shader;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjglx.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public abstract class Shader {
	private int vertexShaderID, fragmentShaderID, programID;
	private String vertexFile, fragmentFile;
	public Shader(String vertexFile, String fragmentFile) {
		this.vertexFile = vertexFile;
		this.fragmentFile = fragmentFile;
	}
	
	public void create() {
		programID = glCreateProgram();
		
		vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShaderID, readFile(vertexFile));
		glCompileShader(vertexShaderID);
		
		if(glGetShaderi(vertexShaderID,  GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Error: Vertex shader - " + glGetShaderInfoLog(vertexShaderID));
		}
		
		fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShaderID, readFile(fragmentFile));
		glCompileShader(fragmentShaderID);
		
		if(glGetShaderi(fragmentShaderID,  GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Error: Fragment shader - " + glGetShaderInfoLog(fragmentShaderID));
		}
		
		glAttachShader(programID,  vertexShaderID);
		glAttachShader(programID,  fragmentShaderID);
		
		bindAllAttributes();
		
		glLinkProgram(programID);
		if(glGetShaderi(programID, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println("Error: Program Linking - " + glGetShaderInfoLog(programID));
		}
		
		glValidateProgram(programID);
		if(glGetShaderi(programID, GL_VALIDATE_STATUS) == GL_FALSE) {
			System.err.println("Error: Program Validating - " + glGetShaderInfoLog(programID));
		}
		
		getAllUniforms();
		
	}
	
	public abstract void bindAllAttributes();
	
	public void bindAttribute(int index, String location) {
		glBindAttribLocation(programID, index, location);
	}
	
	protected abstract void getAllUniforms();
	
	protected int getUniform(String name) {
		return glGetUniformLocation(programID, name);
	}
	
	protected void loadFloatUniform(int location, float value) {
		glUniform1f(location, value);
	}
	
	protected void loadIntUniform(int location, int value) {
		glUniform1i(location, value);
	}
	
	protected void loadVectorUniform(int location, Vector3f value) {
		glUniform3f(location, value.x, value.y, value.z);
	}
	
	protected void loadMatrixUniform(int location, Matrix4f value) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);
		value.get(buffer);
		glUniformMatrix4fv(location, false, buffer);
	}
	
	public void bind() {
		glUseProgram(programID);
	}
	
	public void remove() {
		glDetachShader(programID,  vertexShaderID);
		glDetachShader(programID, fragmentShaderID);
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
		glDeleteProgram(programID);
	}
	
	private String readFile(String file) {
		BufferedReader reader = null;
		StringBuilder string = new StringBuilder();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				string.append(line).append("\n");
			}
		} catch (IOException e) {
			System.err.println("Error: Couldn't find file!");
		}
		return string.toString();
	}
}
