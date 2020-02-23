package shader;

import org.joml.Matrix4f;

public class BasicShader extends Shader {
	
	private static final String Vertex_FILE = "src/shader/BasicVertexShader.vs";
	private static final String FRAGMENT_FILE = "src/shader/BasicFragmentShader.fs";
	
	private int transformationLocation;
	
	public BasicShader() {
		super(Vertex_FILE, FRAGMENT_FILE);
	}

	@Override
	public void bindAllAttributes() {
		super.bindAttribute(0, "vertices");
	}

	@Override
	protected void getAllUniforms() {
		transformationLocation = super.getUniform("transformation");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrixUniform(transformationLocation, matrix);
	}
}
