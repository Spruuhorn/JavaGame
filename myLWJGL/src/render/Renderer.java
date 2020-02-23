package render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import model.ModelEntity;
import model.TexturedModel;
import model.UntexturedModel;
import shader.BasicShader;

public class Renderer {
	
	private BasicShader shader;
	
	public Renderer(BasicShader shader) {
		this.shader = shader;
	}
	
	public void renderModel(UntexturedModel model) {
		glBindVertexArray(model.getVertexArrayID());
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
	
	public void renderTexturedModel(TexturedModel model) {
		glBindVertexArray(model.getVertexArrayID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glActiveTexture(GL_TEXTURE);
		glBindTexture(GL_TEXTURE_2D, model.getMaterial().getTextureID());
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
	
	public void renderModelEntity(ModelEntity entity) {
		glBindVertexArray(entity.getModel().getVertexArrayID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		shader.loadTransformationMatrix(entity.getTransformationMatrix());
		glActiveTexture(GL_TEXTURE);
		glBindTexture(GL_TEXTURE_2D, entity.getModel().getMaterial().getTextureID());
		glDrawElements(GL_TRIANGLES, entity.getModel().getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
}
