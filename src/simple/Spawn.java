package simple;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Game game;
	
	private int scoreKeep = 0;
	private boolean bossFight = false;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep++;


		if(scoreKeep >= 500) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel()+1);
		}	
		
		// Spawn normal
		if(game.diff == 0) {
			if(scoreKeep % 200 == 0 && !bossFight) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			if(scoreKeep % 400 == 0 && !bossFight) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			}

			if(hud.getLevel() == 3 && scoreKeep == 0) {
				handler.clearEnemies();
				handler.addObject(new Boss1(Game.WIDTH/2 - 48, -120, ID.Boss1, game, handler));
				bossFight = true;
			}
			if(hud.getLevel() >= 3 && !bossFight && scoreKeep == 0) {
				//handler.clearEnemies();
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
			}

			if(hud.getLevel() == 10 && scoreKeep == 0) {
				handler.clearEnemies();
				bossFight = false;
				for(int i = 0; i < 5; i++) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
				}
			}

			
		} else if(game.diff == 1) { // Spawn hard

			if(scoreKeep % 200 == 0 && !bossFight) {
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
			}
			if(scoreKeep % 400 == 0 && !bossFight) {
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			}

			if(hud.getLevel() == 3 && scoreKeep == 0) {
				handler.clearEnemies();
				handler.addObject(new Boss1(Game.WIDTH/2 - 48, -120, ID.Boss1, game, handler));
				bossFight = true;
			}
			if(hud.getLevel() == 5 && scoreKeep == 0) {
				handler.clearEnemies();
				bossFight = false;
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
			}

			if(hud.getLevel() == 8 && scoreKeep == 0) {
				handler.clearEnemies();
				bossFight = false;
				for(int i = 0; i < 7; i++) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
				}
			}
		}	
	}
	
	
	public boolean getBossFight() {
		return bossFight;
	}
	
	public void setBossFight(boolean b) {
		this.bossFight = b;
	}
	
	
}
