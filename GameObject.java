package boxgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {			//Getters and setters, basically initialization
	
	protected int x, y;
	protected ID id;
	protected int velX, velY;
	
	public GameObject(int x, int y, ID id){		//Whatever parameter is given here, then it will be protected to be a part of its own object
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();		//Allowing it to be a part of the game by having tick and render
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	public ID getId(){
		return id;
	}
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public void setVelY(int velY){
		this.velY = velY;
	}
	public int getVelX(){
		return velX;
	}
	public int getVelY(){
		return velY;
	}
	
	
	
	
}
