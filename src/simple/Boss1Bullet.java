package simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1Bullet extends GameObject {
	
	protected Handler handler;
	Random r = new Random();

	public Boss1Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		volX = (r.nextInt(5 - -5)+ -5);
		volY = 7;
	}

	public void tick() {
		x += volX;
		y += volY;
		
//		if(y <= 0 || y >= Game.HEIGHT) {
//			volY *= -1;
//		}
//		if(x <= 0 || x >= Game.WIDTH) {
//			volX *= -1;
//		}
		
		if(y >= Game.HEIGHT) {
			handler.removeObject(this);
		}
		
		handler.addObject(new Trail(x,y, ID.Trail, Color.yellow, 16, 16, .01f, handler));
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillRect((int)x,(int) y, 16, 16);
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y, 16, 16);
	}

	
}
