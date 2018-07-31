package simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
	
	private Handler handler;
	private HUD hud;
	
	private int upHealth = 1000;
	private int upSpeed = 1000;
	private int reHealth = 1000;
	
	
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 1, 48));
		g.drawString("SHOP", Game.WIDTH/2-50, 100);
		
		// Upgrade 1
		g.setFont(new Font("arial", 1, 24));
		g.drawString("Upgrade Health", 170, 175);
		g.drawString("Cost: "+ upHealth,190 , 205);
		g.drawRect(150, 150, 250, 100);
		
		// Upgrade 2
		g.drawString("Upgrade Speed", 570, 175);
		g.drawString("Cost: "+ upSpeed,590 , 205);
		g.drawRect(550, 150, 250, 100);

		// Upgrade 3
		g.drawString("Refill Health", 970, 175);
		g.drawString("Cost: "+ reHealth,990 , 205);
		g.drawRect(950, 150, 250, 100);
		
		g.drawString("SCORE: " + hud.getScore(), Game.WIDTH/2-50, 400);
		g.drawString("Press S to go back", Game.WIDTH/2-50, 500);

		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//box 1
		if(mx >= 150 && mx <= 400 && my >= 150 && my <= 250) {
			if(hud.getScore() >= upHealth) {
				hud.setScore(hud.getScore()-upHealth);
				upHealth += 1000;
				hud.bounds += 20;
				hud.HEALTH = 100 + hud.bounds/2;
			}
		}
		//box 2
		if(mx >= 550 && mx <= 800 && my >= 150 && my <= 250) {
			if(hud.getScore() >= upSpeed) {
				hud.setScore(hud.getScore()-upSpeed);
				upSpeed += 1000;
				handler.setSpeed(handler.getSpeed()+2);
			}
		}
		
		//box 3
		if(mx >= 950 && mx <= 1200 && my >= 150 && my <= 250) {
			if(hud.getScore() >= reHealth) {
				hud.setScore(hud.getScore()-reHealth);
				hud.HEALTH = 100 + hud.bounds/2;
			}
		}
		
	}
	
	
}
