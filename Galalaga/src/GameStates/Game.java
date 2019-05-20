package GameStates;

import Entities.*;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	
	public static int score = 0;
	
	public int timer = 0;
	
	public boolean gameOver = false;
	
	private TrueTypeFont f;
	
	public static Player player1, player2;
	
	public static int numOfPlayers;
	
	public Game(int state) {}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		gc.setShowFPS(false);

		f = AssetsManager.font;
		
	}
	
	public static void createPlayers(GameContainer gc, int num) {
		
		if(num == 2) {
			
			player1 = new Player(GameManager.GAME_WIDTH/2 - 64, GameManager.GAME_HEIGHT - 100, 3, "PlayerShip.png");
			player1.configureControls(Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_RCONTROL);
			player1.setInput(gc);
			
			player2 = new Player(GameManager.GAME_WIDTH/2 + 32, GameManager.GAME_HEIGHT - 100, 3, "PlayerShip2.png");
			player2.configureControls(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D, Input.KEY_SPACE);
			player2.setInput(gc);
			
			numOfPlayers = 2;
			
		} else {
			
			player1 = new Player(GameManager.GAME_WIDTH/2 - 32, GameManager.GAME_HEIGHT - 100, 3, "PlayerShip.png");
			player1.configureControls(Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_RCONTROL);
			player1.setInput(gc);
			
			numOfPlayers = 1;
			
			//new BasicAlien(350, -50, 2, .25f, "Alien1.png");
			//new SlidingAlien(350, 350, 2, .25f, "SlidingAlien.png");
			//new TrackingAlien(GameManager.GAME_WIDTH/2 - 32, 100, 2, .25f, "LongAlien.png");
			new MirageAlien(GameManager.GAME_WIDTH/2 - 32, 100, 2, .25f, "MirageAlien.png");
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		for(GameObject obj : GameManager.getGameObjects()) {
			
			//g.draw(obj.getHitBox());
			
	        List<Projectile> projectiles = obj.getProjectiles();
	        
	        obj.draw(g);

	        for (Projectile proj : projectiles) {
	            proj.getAnimatedSprite().draw(proj.getX(), proj.getY());
	            
	            //g.draw(proj.getHitBox());
	        }
	        
			if(obj.isVisible()) {
				obj.getAnimatedSprite().draw(obj.getX(), obj.getY());
			}
		}
		
		if(gameOver) {
			
			String over = "GAME OVER";
			String finalScore = "Final Score " + score;
			
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(over)/2, GameManager.GAME_HEIGHT/2 - f.getHeight(over), over);
			f.drawString(GameManager.GAME_WIDTH/2 - f.getWidth(finalScore)/2, (GameManager.GAME_HEIGHT/2 - f.getHeight(finalScore)) + 25, finalScore);
			
		} else {
			f.drawString(GameManager.GAME_WIDTH - (5 + f.getWidth("Score " + score)), GameManager.GAME_HEIGHT - 25, "Score " + score);
			f.drawString(5, GameManager.GAME_HEIGHT - 25, "Health " + player1.getHealth());
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time) throws SlickException {
		
		ArrayList<GameObject> gameObjects = GameManager.getGameObjects();
		
		ListIterator<GameObject> objIt = gameObjects.listIterator();
		
		while(objIt.hasNext()) {
			
			GameObject obj = objIt.next();
			
			if(obj.isVisible()) {
				obj.update(time);
			}
			
			ArrayList<Projectile> projectiles = obj.getProjectiles();
			
			if(obj.isDestroyed() && projectiles.isEmpty()) {
				if(obj.getCollider().getTag().equals("Player")) {
					numOfPlayers--;
					if(numOfPlayers == 0) {
						gameOver = true;
						break;
					}
				}
				score += obj.getScore();
				objIt.remove();
			}
			
			ListIterator<Projectile> projIt = projectiles.listIterator();

	        while(projIt.hasNext()) {
	        	
	        	Projectile proj = projIt.next();
	        	
	        	if(proj.isDestroyed()) {
	        		projIt.remove();
	        	}
	        	
	            proj.update(time);
	           
	            for(GameObject collider : GameManager.getGameObjects()) {
		            if(proj.getHitBox().intersects(collider.getHitBox()) && !(collider.getCollider().getTag().equals(proj.getTagToIgnore())) && collider.isVisible()) {
		            	collider.onCollision();
		            	projIt.remove();
		            	break;
		            }
	            }
	        }
		}
		
		if(!gameOver) {

			//GameManager.update(time);
			
		} else {
			if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
				gameOver = false;
				GameManager.reset();
				sbg.enterState(0);
			}
		}
		
	}

	@Override
	public int getID() {
		
		return 1;
		
	}

}
