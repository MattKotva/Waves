package simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
	
	private Handler handler;
	Random r = new Random();
	
	private Color col;
	
	int dir = 0;

	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		dir = r.nextInt(2);
		if(dir == 0) {
			volX = 2;
			volY = 9;
		} else if (dir==1) {
			volX = 9;
			volY = 2;
		}
		
		col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		
	}
	
	public void tick() {
		x += volX;
		y += volY;
		
		if(y <= 0 || y >= Game.HEIGHT) {
			volY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH) {
			volX *= -1;
		}
		
		handler.addObject(new Trail(x,y, ID.Trail, col, 16, 16, .01f, handler));
		
	}
	
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x,(int) y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
