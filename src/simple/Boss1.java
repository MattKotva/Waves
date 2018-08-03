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
	
	private float health = 1000;
	private int greenValue;
	private Game game;

	public Boss1(float x, float y, ID id, Game game, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		this.game = game;
		
		volX = 0;
		volY = 2;
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getid() == ID.PlayerBullet) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					health --;
				}
			}
		}
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
		
		health = Game.clamp(health, 0, 1000);
		greenValue = (int)health/5;
		greenValue = (int)Game.clamp(greenValue, 0, 255);
		
		collision();	
		
		handler.addObject(new Trail(x,y, ID.Trail, Color.red, 96, 96, 1f, handler));
		if(health <= 0) {
			game.getSpawner().setBossFight(false);
			handler.removeObject(this);
		}
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 96, 96);
		
		
		// Health bar
		g.setColor(Color.gray);
		g.fillRect((int)x -2,(int) y - 30, 100, 20);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect((int)x -2,(int) y - 30, (int)health/10, 20);
		g.setColor(Color.white);
		g.drawRect((int)x - 2,(int) y - 30, 100, 20);
		
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y, 96, 96);
	}

	
}
