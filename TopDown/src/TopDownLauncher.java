import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class TopDownLauncher extends Application{
	
	final static int WIDTH = 500, HEIGHT = 500, SCALE = 4;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		
		GameManager.init();
		
		Group group = new Group();
		
		Scene scene = new Scene(group, WIDTH, HEIGHT);
		
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		
		Camera camera = new Camera(canvas, GameManager.player);
		
		group.getChildren().add(canvas);
		
		//Move to player class? With static scene
		scene.setOnKeyPressed(e -> GameManager.player.handleMovement(e));
		scene.setOnKeyReleased(e -> GameManager.player.handleMove(e));
		
		stage.setTitle("Top Down Adventure");
		stage.setScene(scene);
		stage.show();
	}


}
