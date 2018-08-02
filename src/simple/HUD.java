package simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public int bounds = 0;
	public static float HEALTH = 100;
	private int greenValue = 255;
	
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		
		HEALTH = Game.clamp(HEALTH, 0, 100 + bounds/2);
		greenValue = (int)HEALTH * 2;
		greenValue = (int)Game.clamp(greenValue, 0, 255);
		
		score++;
		
	}
	
	public void render(Graphics g) {
		
		// Health bar
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect((int)15,(int)15, (int)HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200+ bounds, 32);
		
		Font fnt = new Font("arial", 1, 20);
		g.setFont(fnt);
		g.drawString("Score: " + score, 15, 68);
		g.drawString("Level: " + level, 15, 93);
		g.drawString("S for Shop", 15, 119);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
}
