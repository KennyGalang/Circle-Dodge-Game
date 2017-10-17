package boxgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	public Handler handler;
	
	public KeyInput(Handler handler){		//Creates connect to handler
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();			//Press a key, returns ASCII value!
		
		for(int i =0; i< handler.object.size();i++){		//For all objects
			GameObject tempObject = handler.object.get(i);	//Create new object, get the first ONE which is 0
			
			if(tempObject.getId() == ID.Player){		//If the ID of the object is player
				//key events for player 
				if(key == KeyEvent.VK_UP)	tempObject.setVelY(-5);	 //Whatever is pressed, go whatever way
				if(key == KeyEvent.VK_DOWN)	tempObject.setVelY(5);	
				if(key == KeyEvent.VK_RIGHT)	tempObject.setVelX(5);	
				if(key == KeyEvent.VK_LEFT)	tempObject.setVelX(-5);	
				
			}
			
			
		}
		if(key== KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
//		System.out.println(key);
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();			//Press a key, returns ASCII value!
		
		for(int i =0; i< handler.object.size();i++){		//For all objects
			GameObject tempObject = handler.object.get(i);	//Create new object, get the first ONE which is 0
			
			if(tempObject.getId() == ID.Player){		//If the ID of the object is player
				//key events for player 
				if(key == KeyEvent.VK_UP)	tempObject.setVelY(0);	 //Whatever is pressed, go whatever way
				if(key == KeyEvent.VK_DOWN)	tempObject.setVelY(0);	
				if(key == KeyEvent.VK_RIGHT)	tempObject.setVelX(0);	
				if(key == KeyEvent.VK_LEFT)	tempObject.setVelX(0);	
				
				
			}
			
		}
		if(key== KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
//		System.out.println(key);
		
	}

}
