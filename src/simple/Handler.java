package simple;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	private int speed = 5;
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearAll() {
		object.clear();
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int s) {
		speed = s;
	}
	
	public void clearEnemies() {
		for(int i = 0; i <object.size(); i++) {
			if(object.get(i).getid() == ID.BasicEnemy || object.get(i).getid() == ID.MenuParticle || object.get(i).getid() == ID.FastEnemy
					|| object.get(i).getid() == ID.SmartEnemy || object.get(i).getid() == ID.Boss1 ) {
				object.remove(i);
				i--;
			}
		} 
	}

}
