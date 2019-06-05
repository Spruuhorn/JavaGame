package textee;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class TextLeaf {
	
	private final int PADDING = 10;
	
	private final double TEXTAREA_WIDTH = 200;
	private final double TEXTAREA_HEIGHT = 120;
	
	private final double ADD_WIDTH = 100;
	private final double ADD_HEIGHT = 25;
	
	private final double REMOVE_WIDTH = 25;
	private final double REMOVE_HEIGHT = 25;
	
	private double x;
	private double y;
	
	private double width;
	private double height;
	
	double dragDeltaX;
	double dragDeltaY;
	
	private String nodeID;
	private List<TextOption> options;
	private TextOption selectedTextOption;
	
	private Rectangle container;
	private TextArea text;
	private Button add;
	private Button remove;
	
	private Group group;
	
	public TextLeaf(double x, double y) {
		
		this.nodeID = String.valueOf(LeafManager.getID());
		
		this.x = x;
		this.y = y;
		this.group = TexteeLauncher.getGroup();
		this.options = new ArrayList<>();
		
		/* Instantiate TextLeaf's individual widgets */
	    container = new Rectangle();  
	    text = new TextArea("Type something...");
	    add = new Button("Add Option");
	    remove = new Button("X");
		
		group.getChildren().addAll(container, text, add, remove);
		
		/* Calculate dimensions of container */
	    width = TEXTAREA_WIDTH + REMOVE_WIDTH + (PADDING * 2);
	    height = TEXTAREA_HEIGHT + ADD_HEIGHT + (PADDING * 2);
	    
	    /* Set container properties */
	    container.setLayoutX(x); 
	    container.setLayoutY(y); 
	    container.setWidth(width); 
	    container.setHeight(height);  
	    container.setArcWidth(PADDING);
	    container.setArcHeight(PADDING);
	    container.setOpacity(0.15);
	    container.setFill(Color.rgb(5, 100, 155));
	    
		/* Set TextArea's properties */
		text.setLayoutX(x + PADDING);
		text.setLayoutY(y + PADDING);
		text.setPrefWidth(TEXTAREA_WIDTH);
		text.setPrefHeight(TEXTAREA_HEIGHT);
		text.setWrapText(true);
	    
		/* Set add button properties */
		add.setLayoutX(x + width/2 - ADD_WIDTH/2);
		add.setLayoutY(y + height - ADD_HEIGHT - PADDING);
		add.setPrefSize(ADD_WIDTH, ADD_HEIGHT);
		add.setOnAction(event -> addEventListener(event));
		
		/* Set remove button properties */
		remove.setLayoutX(x + width - REMOVE_WIDTH - PADDING);
		remove.setLayoutY(y + PADDING);
		remove.setPrefSize(REMOVE_WIDTH, REMOVE_HEIGHT);
		remove.setOnAction(event -> removeEventListener(event));
		
		/* Set container's event listeners */
		container.setOnMouseClicked(event -> containerOnMouseClicked(event));
		container.setOnMousePressed(event -> containerOnMousePressed(event));
		container.setOnMouseReleased(event -> containerOnMouseReleased(event));
		container.setOnMouseDragged(event -> containerOnMouseDragged(event));
		container.setOnMouseEntered(event -> containerOnMouseEntered(event));
		
		LeafManager.add(this);
		
	}
	
	public void setNodeID(String id) {
		nodeID = id;
	}
	
	public TextOption getSelectedTextOption() {
		return selectedTextOption;
	}
	
	public List<TextOption> getTextOptions() {
		return options;
	}
	
	public String getNodeID() {
		return nodeID;
	}
	
	public String getText() {
		return text.getText();
	}
	
	public void setText(String t) {
		text.setText(t);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void clear() {
		while(!options.isEmpty()) {
			TextOption option = options.get(0);
			option.remove();
		}
		group.getChildren().removeAll(container, text, add, remove);
	}
	
	public void drawLines() {
		for(TextOption option : options) {
			option.drawLine();
		}
	}

	private void containerOnMouseClicked(MouseEvent event) {
		if(LeafManager.isConnecting()) {
			LeafManager.connect(LeafManager.getCurrentTextLeaf(), this);
		}
	}
	
	private void addEventListener(ActionEvent event) {
		
		new TextOption(x, y);
		
		height += ADD_HEIGHT;
		
		container.setHeight(height);
		
	}
	
	private void removeEventListener(ActionEvent event) {
		
		while(!options.isEmpty()) {
			TextOption option = options.get(0);
			option.remove();
		}
		
		LeafManager.remove(this);
		group.getChildren().removeAll(container, text, add, remove);
		
	}
	
	public void addOption(String t, String n) {
		new TextOption(x, y, t, n);
		
		height += ADD_HEIGHT;
		
		container.setHeight(height);
	}
	
	public void setDelta(MouseEvent event) {
		dragDeltaX = container.getLayoutX() - event.getSceneX();
		dragDeltaY = container.getLayoutY() - event.getSceneY();
	}
	
	public void translate(MouseEvent event) {
		
		container.setLayoutX(event.getSceneX() + dragDeltaX);
		container.setLayoutY(event.getSceneY() + dragDeltaY);
		
		TextLeaf.this.x = container.getLayoutX();
		TextLeaf.this.y = container.getLayoutY();
		
		text.setLayoutX(TextLeaf.this.x + PADDING);
		text.setLayoutY(TextLeaf.this.y + PADDING);
		
		add.setLayoutX(TextLeaf.this.x + width/2 - ADD_WIDTH/2);
		add.setLayoutY(TextLeaf.this.y + PADDING + TEXTAREA_HEIGHT);
		
		remove.setLayoutX(TextLeaf.this.x + width - REMOVE_WIDTH - PADDING);
		remove.setLayoutY(TextLeaf.this.y + PADDING);
		
		int counter = 0;
		for(TextOption option : options) {
			option.translate(TextLeaf.this.x, TextLeaf.this.y, counter);
			counter++;
		}
		
	}
	
	/**
	 * Methods for dragging around an individual leaf.
	 */
	private void containerOnMousePressed(MouseEvent event) {
		dragDeltaX = container.getLayoutX() - event.getSceneX();
		dragDeltaY = container.getLayoutY() - event.getSceneY();
		container.setCursor(Cursor.MOVE);
	}
	
	private void containerOnMouseReleased(MouseEvent event) {

		container.setCursor(Cursor.HAND);
		
	}
	
	private void containerOnMouseDragged(MouseEvent event) {
		
		container.setLayoutX(event.getSceneX() + dragDeltaX);
		container.setLayoutY(event.getSceneY() + dragDeltaY);
		
		TextLeaf.this.x = container.getLayoutX();
		TextLeaf.this.y = container.getLayoutY();
		
		text.setLayoutX(TextLeaf.this.x + PADDING);
		text.setLayoutY(TextLeaf.this.y + PADDING);
		
		add.setLayoutX(TextLeaf.this.x + width/2 - ADD_WIDTH/2);
		add.setLayoutY(TextLeaf.this.y + PADDING + TEXTAREA_HEIGHT);
		
		remove.setLayoutX(TextLeaf.this.x + width - REMOVE_WIDTH - PADDING);
		remove.setLayoutY(TextLeaf.this.y + PADDING);
		
		int counter = 0;
		for(TextOption option : options) {
			option.translate(TextLeaf.this.x, TextLeaf.this.y, counter);
			counter++;
		}
			
	}
	
	private void containerOnMouseEntered(MouseEvent event) {
		
		container.setCursor(Cursor.HAND);
	}
	
	/**
	 * 
	 * @author Jason
	 * 
	 * Text Option class. This class represents a text option for a node that can connect to 
	 * another text option.
	 *
	 */
	class TextOption {
		
		private final double REMOVE_WIDTH = 25;
		private final double REMOVE_HEIGHT = 25;
		
		private final double CONNECT_WIDTH = 25;
		private final double CONNECT_HEIGHT = 25;
		
		private double x;
		private double y;
		
		private Button remove;
		private Button connect;
		private TextField text;
		
		private Line connectionLine;
		private TextLeaf node;
		
		/* Constructor used when creating a new option in the program */
		public TextOption(double xo, double yo) {
			
			this.x = xo;
			this.y = yo;
			
			remove = new Button("X");
			connect = new Button("O");
			text = new TextField("Type something...");
			
			remove.setLayoutX(x + PADDING);
			remove.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + options.size() * REMOVE_HEIGHT + ADD_HEIGHT);
			remove.setPrefSize(REMOVE_WIDTH, REMOVE_HEIGHT);
			remove.setOnAction(event -> removeOption(event));
			
			connect.setLayoutX(x + width - CONNECT_WIDTH - PADDING);
			connect.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + options.size() * REMOVE_HEIGHT + ADD_HEIGHT);
			connect.setPrefSize(CONNECT_WIDTH, CONNECT_HEIGHT);
			connect.setOnAction(event -> connectOption(event));
			
			text.setLayoutX(x + PADDING + REMOVE_WIDTH);
			text.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + options.size() * REMOVE_HEIGHT + ADD_HEIGHT);
			text.setPrefWidth(width - (PADDING * 2 + REMOVE_WIDTH + CONNECT_WIDTH));
			
			group.getChildren().addAll(remove, connect, text);
			
			options.add(this);
			
		}
		
		/* Constructor used ONLY when loading in data from a JSON file */
		public TextOption(double xo, double yo, String t, String n) {
			this.x = xo;
			this.y = yo;
			
			remove = new Button("X");
			connect = new Button("o");
			text = new TextField(t);
			
			remove.setLayoutX(x + PADDING);
			remove.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + options.size() * REMOVE_HEIGHT + ADD_HEIGHT);
			remove.setPrefSize(REMOVE_WIDTH, REMOVE_HEIGHT);
			remove.setOnAction(event -> removeOption(event));
			
			connect.setLayoutX(x + width - CONNECT_WIDTH - PADDING);
			connect.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + options.size() * REMOVE_HEIGHT + ADD_HEIGHT);
			connect.setPrefSize(CONNECT_WIDTH, CONNECT_HEIGHT);
			connect.setOnAction(event -> connectOption(event));
			
			text.setLayoutX(x + PADDING + REMOVE_WIDTH);
			text.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + options.size() * REMOVE_HEIGHT + ADD_HEIGHT);
			text.setPrefWidth(width - (PADDING * 2 + REMOVE_WIDTH + CONNECT_WIDTH));
			
			group.getChildren().addAll(remove, connect, text);
			
			options.add(this);
			
			makeConnection(LeafManager.getLeaf(n));
		}
		
		public void drawLine() {
			
			if(!LeafManager.isActive(node) && connectionLine != null) {
				group.getChildren().remove(connectionLine);
			}
			
			if(connectionLine != null) {
				connectionLine.setStartX(connect.getLayoutX() + connect.getWidth()/2);
				connectionLine.setStartY(connect.getLayoutY() + connect.getHeight()/2);
				connectionLine.setEndX(node.x + 5);
				connectionLine.setEndY(node.y + 5);
			}
		}

		private void removeOption(ActionEvent event) {
			TextLeaf.this.height -= ADD_HEIGHT;
			TextLeaf.this.container.setHeight(height);
			
			int removeIndex = options.indexOf(this);
			TextLeaf.this.options.remove(this);
			
			for(int i = removeIndex; i < options.size(); i++) {
				
				TextOption option = options.get(i);
				
				option.remove.setLayoutY(option.remove.getLayoutY() - ADD_HEIGHT);
				
				option.connect.setLayoutY(option.connect.getLayoutY() - ADD_HEIGHT);
				
				option.text.setLayoutY(option.text.getLayoutY() - ADD_HEIGHT);
				
				option.text.setText(String.valueOf(i));
				
			}
			
			group.getChildren().removeAll(remove, connect, text, connectionLine);
		}
		
		private void connectOption(ActionEvent event) {
			
			TextLeaf.this.selectedTextOption = this;
			
			LeafManager.setConnecting(true);
			
			LeafManager.setCurrentTextLeaf(TextLeaf.this);
			
			TexteeLauncher.createNewLine(connect.getLayoutX()  + connect.getWidth()/2, connect.getLayoutY() + connect.getHeight()/2);
			
		}
		
		public void makeConnection(TextLeaf leaf) {
			
			LeafManager.setConnecting(false);
			
			LeafManager.setCurrentTextLeaf(null);
			
			TexteeLauncher.removeLine();
			
			connectionLine = new Line(connect.getLayoutX(), connect.getLayoutY(), leaf.x, leaf.y);
			
			group.getChildren().add(connectionLine);
			
			node = leaf;
			
		}
		
		public void translate(double deltaX, double deltaY, int a) {
			
			x = deltaX;
			y = deltaY;
			
			remove.setLayoutX(x + PADDING);
			remove.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + a * REMOVE_HEIGHT + ADD_HEIGHT);
			
			connect.setLayoutX(x + width - CONNECT_WIDTH - PADDING);
			connect.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + a * REMOVE_HEIGHT + ADD_HEIGHT);
			
			text.setLayoutX(x + PADDING + REMOVE_WIDTH);
			text.setLayoutY(y + PADDING + TEXTAREA_HEIGHT + a * REMOVE_HEIGHT + ADD_HEIGHT);
		}
		
		public String getNode() {
			
			if(node == null) {
				return "NO CONNECTION";
			}
			
			return node.nodeID;
		}
		
		public String getText() {
			return text.getText();
		}
		
		public void remove() {
			TextLeaf.this.options.remove(this);
			group.getChildren().removeAll(remove, connect, text, connectionLine);
		}
		
		public String toString() {
			return "Text: " + text.getText();
		}
	}
	
	/**
	 * EQUALS METHOD
	 */
	public boolean equals(Object leaf) {
		if(!(leaf instanceof TextLeaf)) {
			return false;
		}
		
		if(this == leaf) {
			return true;
		}
		
		TextLeaf other = (TextLeaf) leaf;
		return other.nodeID == this.nodeID;
	}
	
	/**
	 * TO STRING
	 */
	public String toString() {
		return "Node ID: " + nodeID;
	}
	
}
