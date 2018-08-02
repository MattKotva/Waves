package simple;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import simple.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getid() == ID.Player) {
				//Key Events for player 
				
				if (key == KeyEvent.VK_UP) {tempObject.setVolY(-handler.getSpeed()); keyDown[0] = true;}
				if (key == KeyEvent.VK_DOWN) {tempObject.setVolY(handler.getSpeed()); keyDown[1] = true;}
				if (key == KeyEvent.VK_RIGHT) {tempObject.setVolX(handler.getSpeed()); keyDown[2] = true;}
				if (key == KeyEvent.VK_LEFT) {tempObject.setVolX(-handler.getSpeed()); keyDown[3] = true;}
				
				if(key == KeyEvent.VK_SPACE) {
					handler.addObject(new PlayerBullet(tempObject.getX() + 32, tempObject.getY(), ID.PlayerBullet, handler));
				}
			}
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game) {
				if(Game.paused) {
					Game.paused = false;
				} else {
					Game.paused = true;
				}
			}
		}
		
		
		if(key == KeyEvent.VK_S) {
			if(game.gameState == STATE.Game) {
				game.gameState = STATE.Shop;
			} else if(game.gameState == STATE.Shop) {
				game.gameState = STATE.Game;
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getid() == ID.Player) {
				if (key == KeyEvent.VK_UP)  keyDown[0] = false; //tempObject.setVolY(0);
				if (key == KeyEvent.VK_DOWN) keyDown[1] = false; // tempObject.setVolY(0);
				if (key == KeyEvent.VK_RIGHT) keyDown[2] = false; //tempObject.setVolX(0);
				if (key == KeyEvent.VK_LEFT) keyDown[3] = false; //tempObject.setVolX(0);
				
				if(!keyDown[0] && !keyDown[1]) tempObject.setVolY(0);
				if(!keyDown[2] && !keyDown[3]) tempObject.setVolX(0);
			}
			
		}
		
	}

}
