package GameStates;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entities.AssetsManager;
import Entities.GameManager;

public class Menu extends BasicGameState {
	
	private String description = "[Press ENTER to select]";
	private String description2 = "[Navigate with ARROW keys]";
	private String escape = "[Press ESCAPE to go back]";
	
	private String onePlayer = "Single Player";
	private String twoPlayer = "Two Player";
	private String howToPlay = "How to Play";
	private String credits = "Credits";
	
	private String creditsDesc = "Why are you reading this?";
	
	private String player1Controls = "Player 1\nMove Up - W\nMove Down - S\nMove Left - A\nMove Right - S\nShoot - Spacebar";
	private String player2Controls = "Player 2\nMove Up - Up Arrow\nMove Down - Down Arrow\nMove Left - Left Arrow\nMove Right - Right Arrow\nShoot - Left Control";
	
	private Input input;
	
	private Image spriteSheet;
	private Animation selector;
	
	private int selectionIndex = 0;
	
	private TrueTypeFont f;
	
	private int currentRender = 0;
	
	public Menu(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.setShowFPS(false);
		
		input = gc.getInput();
		
		f = AssetsManager.font;
		
		try {
			spriteSheet = new Image("MenuSelector.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		selector = new Animation(new SpriteSheet(spriteSheet, spriteSheet.getWidth() / 4, spriteSheet.getHeight()), 100);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		if(currentRender == 0) {
			
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(description)/2, (GameManager.GAME_HEIGHT/2 - f.getHeight(description)) + 300, description, Color.yellow);
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(description2)/2, (GameManager.GAME_HEIGHT/2 - f.getHeight(description2)) + 325, description2, Color.yellow);	
			
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(howToPlay)/2, (GameManager.GAME_HEIGHT/2 - f.getHeight(onePlayer)), onePlayer, Color.yellow);
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(howToPlay)/2, (GameManager.GAME_HEIGHT/2 - f.getHeight(twoPlayer)) + 40, twoPlayer, Color.yellow);
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(howToPlay)/2, (GameManager.GAME_HEIGHT/2 - f.getHeight(howToPlay)) + 80, howToPlay, Color.yellow);
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(howToPlay)/2, (GameManager.GAME_HEIGHT/2 - f.getHeight(credits)) + 120, credits, Color.yellow);
			
			selector.draw(GameManager.GAME_WIDTH/2 - f.getWidth(howToPlay)/2 - 50, GameManager.GAME_HEIGHT/2 - f.getHeight(onePlayer) + (selectionIndex * 40));
			
		} else if(currentRender == 1) {
			
			float y = 0;
			
		    for (String line : player1Controls.split("\n")) {
		        f.drawString(0, y, line, Color.yellow);
		        y += f.getHeight();
		    }
		    
		    y = 0;
		    
		    for (String line : player2Controls.split("\n")) {
		        f.drawString(GameManager.GAME_WIDTH/2, y, line, Color.yellow);
		        y += f.getHeight();
		    }
		    
		    f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(escape)/2, (GameManager.GAME_HEIGHT - f.getHeight(escape) * 2), escape, Color.yellow);
			
		} else if(currentRender == 2) {
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(creditsDesc)/2, (GameManager.GAME_HEIGHT/2 - f.getHeight(creditsDesc)), creditsDesc, Color.yellow);
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(escape)/2, (GameManager.GAME_HEIGHT - f.getHeight(escape) * 2), escape, Color.yellow);
		}
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int num) throws SlickException {
		
		if(currentRender == 0) {
			if(input.isKeyPressed(Input.KEY_UP)) {
				
				AssetsManager.select.play();
				
				if(selectionIndex == 0) {
					selectionIndex = 3;
				} else {
					selectionIndex--;
				}
			}
			
			if(input.isKeyPressed(Input.KEY_DOWN)) {
				
				AssetsManager.select.play();
				
				if(selectionIndex == 3) {
					selectionIndex = 0;
				} else {
					selectionIndex++;
				}
			}
			
			if(input.isKeyDown(Input.KEY_ENTER)) {
				switch(selectionIndex) {
				case 0:
					Game.createPlayers(gc, 1);
					sbg.enterState(GalalagaLauncher.game);
					break;
					
				case 1:
					Game.createPlayers(gc, 2);
					sbg.enterState(GalalagaLauncher.game);
					break;
					
				case 2:
					currentRender = 1;
					break;
					
				case 3:
					currentRender = 2;
				break;
				}
			}
		} else if(currentRender == 1 || currentRender == 2) {
			if(input.isKeyPressed(Input.KEY_ESCAPE)) {
				
				AssetsManager.select.play();
			
				currentRender = 0;
				
			}
		}
	}

	@Override
	public int getID() {
		
		return 0;
		
	}
	
}
