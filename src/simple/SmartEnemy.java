package simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	
	protected Handler handler;
	private GameObject player;

	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getid() == ID.Player) {
				player = handler.object.get(i);
			}
		}
		
		volX = 1;
		volY = 1;
	}

	public void tick() {
		x += volX;
		y += volY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float)Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		volX = ((-1/distance) * diffX);
		volY = ((-1/distance) * diffY);
		
		if(y <= 0 || y >= Game.HEIGHT) {
			volY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH) {
			volX *= -1;
		}
		
		handler.addObject(new Trail(x,y, ID.Trail, Color.green, 16, 16, .01f, handler));
		
		collision();
		
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
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.green);
		g.fillRect((int)x,(int) y, 16, 16);
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y, 16, 16);
	}

	
}
