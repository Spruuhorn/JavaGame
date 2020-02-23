package model;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

import render.Material;

public class TexturedModel extends Model {
	
	private int vertexArrayID, vertexBufferID, textureCoordinatesBufferID, indicesBufferID, vertexCount;
	private Material material;
	
	public TexturedModel (float[] vertices, float[] textureCoords, int[] indices, String file) {
		vertexArrayID = super.createVertexArray();
		indicesBufferID = super.bindIndicesBuffer(indices);
		vertexBufferID = super.storeData(0, 3, vertices);
		textureCoordinatesBufferID = super.storeData(1, 2, textureCoords);
		vertexCount = indices.length;
		glBindVertexArray(0);
		material = new Material(file);
	}
	
	public void remove() {
		glDeleteVertexArrays(vertexArrayID);
		glDeleteBuffers(vertexBufferID);
		glDeleteBuffers(textureCoordinatesBufferID);
		glDeleteBuffers(indicesBufferID);
		material.remove();
	}

	public int getVertexArrayID() {
		return vertexArrayID;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public Material getMaterial() {
		return material;
	}
}
