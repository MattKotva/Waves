package simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{

	Random r = new Random();
	Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		
	}
	
	
	public void tick() {
		x += volX;
		y += volY;
		
		x = Game.clamp(x,  0, Game.WIDTH-32);
		y = Game.clamp(y, 0, Game.HEIGHT-32);
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getid() == ID.BasicEnemy || tempObject.getid() == ID.FastEnemy || 
					tempObject.getid() == ID.SmartEnemy || tempObject.getid() == ID.Boss1Bullet) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,(int) y, 64, 64);
	}


	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
}
