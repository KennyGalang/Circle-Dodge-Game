package boxgame;			//Later in game, have it so if u hold down space bar , the box will go two times FASTER

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class Player extends GameObject{ 		//Player class, and player ID and object

	Random r = new Random();			//Initialize a random placement for a "player" or just an object
	Handler handler;
	public Player(int x, int y, ID id, Handler handler) {		//speed or placement of the player
		super(x, y, id);
		this.handler = handler;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick() {	//x is velocity so moving right, y is same
		x += velX;
		y += velY;
		
		x = Game.clamp(x,  0,  Game.WIDTH - 37);
		y = Game.clamp(y,  0,  Game.HEIGHT - 60);
		
		
		collision();
		
	}
	
	private void collision(){
		for(int i=0; i< handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy){			//tempobject is basicenemy
				//collision!
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -=2;
				}
			}
		}
	}

	public void render(Graphics g) {		//square boxes

		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		
		g.fillRect(x,  y,  32,  32);
		
	}


	
	
	
	
	

}
