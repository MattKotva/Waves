package simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import simple.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//Game
			if(mouseOver(mx,my,450, 300, 400, 120)) {	
				game.gameState = STATE.Select;
				AudioPlayer.getSound("menu_sound").play();
			}
			//Help
			if(mouseOver(mx,my,450, 450, 400, 120)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
			}
			//Quit
			if(mouseOver(mx,my,450, 600, 400, 120)) {
				System.exit(1);
			}	
		} else if(game.gameState == STATE.Help || game.gameState == STATE.End) {
			if(mouseOver(mx,my, 450, 650, 400, 120)){
				AudioPlayer.getSound("menu_sound").play();
				game.gameState = STATE.Menu;
				game.getHud().setScore(0);
				game.getHud().setLevel(1);
			}
		} else if(game.gameState == STATE.Select) {
			if(mouseOver(mx,my,450, 300, 400, 120)) {
				game.gameState = STATE.Game;
				game.diff = 0;
				AudioPlayer.getSound("menu_sound").play();
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			
			if(mouseOver(mx,my,450, 450, 400, 120)) {
				game.gameState = STATE.Game;
				game.diff = 1;
				AudioPlayer.getSound("menu_sound").play();
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			
			if(mouseOver(mx,my, 450, 650, 400, 120)){
				AudioPlayer.getSound("menu_sound").play();
				game.gameState = STATE.Menu;
				game.getHud().setScore(0);
				game.getHud().setLevel(1);
			}
			
		}
		
	}
	
//	public void mouseReleased(MouseEvent e) {
//		
//	}
	
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		// Default Menu
		
		if(game.gameState == STATE.Menu) {
			Font font = new Font("arial", 1, 120);
			Font font2 = new Font("arial", 1, 70);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Waves", 440, 150);
			
			//g.setColor(Color.white);
			g.drawRect(450, 300, 400, 120);
			g.setFont(font2);
			g.drawString("Play", 560, 380);
			
			g.setColor(Color.white);
			g.drawRect(450, 450, 400, 120);
			g.drawString("Help", 560, 530);
			
			g.setColor(Color.white);
			g.drawRect(450, 600, 400, 120);
			g.drawString("Quit", 560, 680);
		} else if(game.gameState == STATE.Help) {
			Font font = new Font("arial", 1, 120);
			Font font2 = new Font("arial", 1, 70);
			Font font3 = new Font("arial", 1, 40);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Help", 475, 150);
			
			g.setFont(font3);
			g.drawString("Use the arrow keys to avoid the enemies", 170, 400);
			g.drawString("Press the P key to pause", 170, 460);
			g.drawString("Press the S key to buy upgrades", 170, 520);
			g.drawString("Press Space to shoot", 170, 580);
			
			
			g.setFont(font2);
			g.drawRect(450, 650, 400, 120);
			g.drawString("Back", 560, 730);
		} else if(game.gameState == STATE.End) {
			Font font = new Font("arial", 1, 120);
			Font font2 = new Font("arial", 1, 70);
			Font font3 = new Font("arial", 1, 40);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Game Over", 275, 150);
			
			g.setFont(font3);
			g.drawString("Your final Level: "+ game.getHud().getLevel(), 395, 400);
			g.drawString("Your final Score: "+ game.getHud().getScore(), 395, 500);
			
			g.setFont(font2);
			g.drawRect(450, 650, 400, 120);
			g.drawString("Menu", 560, 730);
		} else if(game.gameState == STATE.Select) {
			Font font = new Font("arial", 1, 120);
			Font font2 = new Font("arial", 1, 70);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 110, 170);
			
			g.drawRect(450, 300, 400, 120);
			g.setFont(font2);
			g.drawString("Normal", 500, 380);
			
			g.setColor(Color.white);
			g.drawRect(450, 450, 400, 120);
			g.drawString("Hard", 560, 530);
			
			g.setColor(Color.white);
			g.drawRect(450, 600, 400, 120);
			g.drawString("Back", 560, 680);
		}
	}
}
