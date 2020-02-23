package model;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class UntexturedModel extends Model {
	
	private int vertexArrayID, vertexBufferID, indicesBufferID, vertexCount;
	
	public UntexturedModel (float[] vertices, int[] indices) {
		vertexArrayID = super.createVertexArray();
		indicesBufferID = super.bindIndicesBuffer(indices);
		vertexBufferID = super.storeData(0, 3, vertices);
		vertexCount = indices.length;
		glBindVertexArray(0);
	}
	
	public void remove() {
		glDeleteVertexArrays(vertexArrayID);
		glDeleteBuffers(vertexBufferID);
		glDeleteBuffers(indicesBufferID);
	}

	public int getVertexArrayID() {
		return vertexArrayID;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
}
