package simple;

import java.awt.Color;
import java.awt.Graphics;

public class FastEnemy extends BasicEnemy {

	public FastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		volX = 10;
		volY = 10;
	}
	
	@Override
	public void tick() {
		x += volX;
		y += volY;
		
		if(y <= 0 || y >= Game.HEIGHT) {
			volY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH) {
			volX *= -1;
		}
		
		handler.addObject(new Trail(x,y, ID.Trail, Color.blue, 16, 16, .01f, handler));
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x,(int) y, 16, 16);
	}

}
