package simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {
	
	protected Handler handler;

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		volX = 5;
		volY = 5;
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getid() == ID.PlayerBullet) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					handler.removeObject(this);
				}
			}
		}
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
		
		handler.addObject(new Trail(x,y, ID.Trail, Color.red, 16, 16, .01f, handler));
		
		collision();
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 16);
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y, 16, 16);
	}

	
}
