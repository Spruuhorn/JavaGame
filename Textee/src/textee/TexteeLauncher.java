package textee;

import java.io.File;
import java.util.Iterator;

import javax.swing.JFileChooser;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TexteeLauncher extends Application {
	
	public final static int WIDTH = 1280;
	public static final int HEIGHT = 960;
	
	private static double xView;
	private static double yView;
	
	private static double mouseX;
	private static double mouseY;
	
	private static Group group;
	private Scene scene;
	private Canvas canvas;
	
	private Button createFile;
	private Button saveFile;
	private Button loadFile;
	
	private BorderPane pane;
	private ToolBar toolbar;
	
	private ContextMenu contextMenu;
    private MenuItem textLeaf; 
    
    private static Line connectingLine;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		
		group = new Group();
		scene = new Scene(group, WIDTH, HEIGHT);
		canvas = new Canvas(WIDTH, HEIGHT);
		
		xView = 0;
		yView = 0;
		
		contextMenu = new ContextMenu(); 
        textLeaf = new MenuItem("New Text Leaf");
        contextMenu.getItems().add(textLeaf);
        
        createFile = new Button("New");
        saveFile = new Button("Save");
        loadFile = new Button("Load");
        
        toolbar = new ToolBar();
        toolbar.getItems().addAll(createFile, saveFile, loadFile);
        
        pane = new BorderPane();
        pane.setTop(toolbar);
        
        createFile.setOnAction(event -> newFileListener(event));
		saveFile.setOnAction(event -> saveFileListener(event));
		loadFile.setOnAction(event -> loadFileListener(event));
		
		canvas.setOnMouseClicked(event -> mouseClicked(event));
		canvas.setOnMouseMoved(event -> trackMouse(event));
		canvas.setOnMousePressed(event -> canvasOnMousePressed(event));
		canvas.setOnMouseReleased(event -> canvasOnMouseReleased(event));
		canvas.setOnMouseDragged(event -> canvasOnMouseDragged(event));
		
        textLeaf.setOnAction(event -> newTextLeafClicked(event));
		
		group.getChildren().addAll(canvas, pane);
		
		AnimationTimer mainLoop = new AnimationTimer() {

			@Override
			public void handle(long time) {
				
				LeafManager.update();
				
			}
			
		};
		
		mainLoop.start();
		
		stage.setTitle("Textee");
		stage.setScene(scene);
		stage.show();
	}
	
	public static Group getGroup() {
		return group;
	}
	
	private void newFileListener(ActionEvent event) {
		LeafManager.clear();
	}
	
	private void saveFileListener(ActionEvent event) {
		LeafManager.saveTree();
	}
	
	private void loadFileListener(ActionEvent event) {
    	JFileChooser chooser= new JFileChooser();

    	int choice = chooser.showOpenDialog(chooser);

    	if (choice != JFileChooser.APPROVE_OPTION) return;

    	File file = chooser.getSelectedFile();
    	
    	LeafManager.loadTree(file);
	}
	
	public static void createNewLine(double x, double y) {
		connectingLine = new Line(x, y, x, y);
        connectingLine.setOnMouseClicked(event -> lineMouseClicked(event));
		group.getChildren().add(connectingLine);
	}
	
	private void newTextLeafClicked(ActionEvent event) {
		
		createTextLeaf(mouseX, mouseY);
		
	}
	
	private void canvasOnMousePressed(MouseEvent event) {
		
		MouseButton button = event.getButton();
		
		if(button == MouseButton.PRIMARY) {
			for(TextLeaf leaf : LeafManager.leaves) {
				leaf.setDelta(event);
			}
		}
		
		canvas.setCursor(Cursor.MOVE);
	}
	
	private void canvasOnMouseReleased(MouseEvent event ) {
		canvas.setCursor(Cursor.DEFAULT);
	}
	
	private void canvasOnMouseDragged(MouseEvent event) {
		
		MouseButton button = event.getButton();
		
		if(button == MouseButton.PRIMARY) {
			for(TextLeaf leaf : LeafManager.leaves) {
				leaf.translate(event);
			}
		}
			
	}
	
	private void trackMouse(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
		
		if(connectingLine != null) {
			connectingLine.setEndX(mouseX);
			connectingLine.setEndY(mouseY);
		}
	}
	
	private void mouseClicked(MouseEvent event) {
		
		MouseButton button = event.getButton();
		
		if(button == MouseButton.SECONDARY) {
		
			contextMenu.show(canvas, event.getScreenX(), event.getScreenY());

		}
		
		if(button == MouseButton.PRIMARY) {
			
			if(contextMenu.isShowing()) {
				contextMenu.hide();
			}
			
		}
	}
	
	private static void lineMouseClicked(MouseEvent event) {
		
		removeLine();
		
	}
	
	private void createTextLeaf(double x, double y) {
		
		new TextLeaf(x, y);
		
	}
	
	public static double getXView() {
		return xView;
	}
	
	public static double getYView() {
		return yView;
	}
	
	public static double getMouseX() {
		return mouseX;
	}
	
	public static double getMouseY() {
		return mouseY;
	}

	public static void removeLine() {
		
		LeafManager.setConnecting(false);
		LeafManager.setCurrentTextLeaf(null);
		
		group.getChildren().remove(connectingLine);
		connectingLine = null;

		
	}
}
