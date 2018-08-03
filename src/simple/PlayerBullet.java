package simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends GameObject {

	private Handler handler;
	
	public PlayerBullet(float x, float y, ID id, Handler handler ) {
		super(x, y, id);
		this.handler = handler;
		
		volX = 0;
		volY = -5;
		
	}
	
//	private void collision() {
//		for (int i = 0; i < handler.object.size(); i++) {
//			GameObject tempObject = handler.object.get(i);
//			if(tempObject.getid() == ID.BasicEnemy || tempObject.getid() == ID.FastEnemy || 
//					tempObject.getid() == ID.SmartEnemy || tempObject.getid() == ID.Boss1Bullet ||
//					tempObject.getid() == ID.Boss1) {
//				//collision
//				if(getBounds().intersects(tempObject.getBounds())) {
//					//collision code
//					handler.removeObject(this);
//				}
//			}
//		}
//	}

	public void tick() {
		
		x += volX;
		y += volY;
		
		if(y <= 0) {
			handler.removeObject(this);
		}
		
		handler.addObject(new Trail(x,y, ID.Trail, Color.WHITE, 10, 10, 1f, handler));
		
		//collision();
		
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x,(int) y, 10, 10);
		
	}

	public Rectangle getBounds() {

		return new Rectangle((int)x,(int)y, 10, 10);
	}

}
