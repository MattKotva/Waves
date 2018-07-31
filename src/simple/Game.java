package simple;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Shop shop;
	private Spawn spawner;
	private Menu menu;
	
	public static boolean paused = false;
	
	// 0 normal
	// 1 hard
	public int diff = 0;
	
	public enum STATE{
		Menu,
		Game,
		Help,
		Select,
		Shop,
		End
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler();
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		this.addKeyListener(new KeyInput(handler, this));
		new Window(WIDTH, HEIGHT, "Waves", this);
		
		hud = new HUD();
		shop = new Shop(handler, hud);
		this.addMouseListener(shop);
		spawner = new Spawn(handler, hud, this);
		
		menu = new Menu(this, handler);
		this.addMouseListener(menu);
		r = new Random();
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		
		if(gameState == STATE.Menu || gameState == STATE.Help) {
			for(int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
	}
	

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Common Game loop
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		if(!(paused)) {
			if(gameState == STATE.Game) {
				hud.tick();
				handler.tick();
				if(hud.HEALTH <=0) {
					hud.HEALTH = 100;
					handler.clearAll();
					gameState = STATE.End;
				}
				
				
				spawner.tick();
			} 
		 else if(gameState == STATE.Menu || gameState == STATE.Select || gameState == STATE.Help || gameState == STATE.End) {
//			menu.tick();
			handler.tick();
		}
		}
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused) {
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", 200, 200);
		}
		
		if(gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
		} else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
			handler.render(g);
			menu.render(g);
		} else if(gameState == STATE.Shop) {
			shop.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, int min, int max) {
		if(var >= max) {
			return var = max;
		} else if(var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public HUD getHud() {
		return hud;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
