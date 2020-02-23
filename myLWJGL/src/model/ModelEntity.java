package model;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import maths.Matrix;

public class ModelEntity {
	private TexturedModel model;
	private Vector3f position, rotation, scale;
	public ModelEntity(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Matrix4f getTransformationMatrix() {
		return Matrix.createTransformationMatrix(position, rotation, scale);
	}
	
	public TexturedModel getModel() {
		return model;
	}
	
	public void setModel(TexturedModel model) {
		this.model = model;
	}
	
	public float getPositionX() {
		return position.x;
	}
	
	public float getPositionY() {
		return position.y;
	}
	
	public float getPositionZ() {
		return position.z;
	}
	
	public float getScaleX() {
		return scale.x;
	}
	
	public float getScaleY() {
		return scale.y;
	}
	
	public float getScaleZ() {
		return scale.z;
	}
	
	public float getRotationX() {
		return rotation.x;
	}
	
	public float getRotationY() {
		return rotation.y;
	}
	
	public float getRotationZ() {
		return rotation.z;
	}
	
	//Set components ***
	
}
