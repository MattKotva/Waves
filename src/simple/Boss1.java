package simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1 extends GameObject {
	
	protected Handler handler;
	Random r = new Random();
	
	private int timer = 90;
	private int timer2 = 80;

	public Boss1(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		volX = 0;
		volY = 2;
	}

	public void tick() {
		x += volX;
		y += volY;
		
		if(timer<=0) {
			volY = 0;
			timer2--;
		} else {
			timer--;
		}
		
		if(timer2 <= 0) {
			if(volX == 0) {
				volX = 2;
			}
			int spawn  = r.nextInt(15);
			if(spawn == 0) {
				handler.addObject(new Boss1Bullet(x+48, y+95, ID.Boss1Bullet, handler));
			}
		}
		
		if(x <= 0 || x >= Game.WIDTH - 96) {
			volX *= -1;
		}
		
		handler.addObject(new Trail(x,y, ID.Trail, Color.red, 96, 96, 1f, handler));
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 96, 96);
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y, 96, 96);
	}

	
}
