package boxgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH /12*9; //ratio of width and height
	
	
	private Thread thread;
	private boolean running = false;
	
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	
	public Game(){
		this.setFocusable(true);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new window(WIDTH, HEIGHT, "Let's BUILD A GAME!", this);		//Window
		
		hud = new HUD();
		
		spawner = new Spawn(handler, hud);
		this.r = new Random();
				//50 Players
			
//		handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));		//Adds player to random place
		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));				//Adds a player, all at same place
		
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
//		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
//		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
//		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
			
		
	}
	
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){  		//GameLoop!!!
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    while(delta >=1)
                            {
                                tick();
                                
                                delta--;
                            }
                            if(running)
                                render();
                            frames++;
                            
                            if(System.currentTimeMillis() - timer > 1000)
                            {
                                timer += 1000;
//                                System.out.println("FPS: "+ frames); 
                                frames = 0;
                            }
        }
                stop();
    }
    
    private void tick(){
    	handler.tick();
    	hud.tick();
    	spawner.tick();
    }
    private void render(){
    	BufferStrategy bs = this.getBufferStrategy();
    	if(bs == null){
    		this.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	g.setColor(Color.black);
    	g.fillRect(0,  0,  WIDTH,  HEIGHT);		//THIS controls the placement of it, adding to first 0 is X! so x coordinates
    	
    	handler.render(g);
    	
    	hud.render(g);
    	
    	g.dispose();
    	bs.show();
    	
    }
    
    public static int clamp(int var, int min, int max){		//Stops box from leaving room
    	if(var >= max){
    		return var = max;
    	}
    	else if(var <= min){
    		return var = min;
    		
    	}
    	else{
    		return var;
    	}
    }
    
	public static void main(String args[]){
		new Game();
	}

}
