package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
/**
	 * This class manages the key commands and stores an ArrayList of ControlListeners
	 */
public class ControlPanel extends JPanel{

	
	private ArrayList<ControlListener> listeners;
	private boolean leftKey, rightKey, upKey, downKey;
	private boolean leftKey2, rightKey2, upKey2, downKey2;
	/**
	 * @post ControlListener ArrayList initialized
	 */
	public ControlPanel() {
		super();
		listeners = new ArrayList<ControlListener>();
		
		
	}
	
	/**
	 * @post ControlListener added to ArrayList of ControlListeners
	 */		
	public void addControlListener(ControlListener l)
	{
		listeners.add(l);
	}
	
	/**
	 * This class gets user key input and makes its ControlListener act based on input
	 */	
	public class KeyMovementHandler implements KeyListener {
	
		/**
		 * @post ControlListeners move the character in a certain direction based on input
		 */		
		public void keyPressed(KeyEvent e) {
		  	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		  		leftKey = true;
		  		moveCharacterLeft();
		  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		  		rightKey = true;
		  		moveCharacterRight();
		  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
		  		upKey = true;
		  		moveCharacterUp();
		  	} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		  		downKey = true;
		  		moveCharacterDown();
		  	} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		  		for(ControlListener l: listeners)
		  			l.createBomb(1);
		  	}
		  	
		  	
		  	
		  	
		  	if (e.getKeyCode() == KeyEvent.VK_A) {
		  		leftKey2 = true;
		  		moveCharacter2Left();
		  	} else if (e.getKeyCode() == KeyEvent.VK_D) {
		  		rightKey2 = true;
		  		moveCharacter2Right();
		  	} else if (e.getKeyCode() == KeyEvent.VK_W) {
		  		upKey2 = true;
		  		moveCharacter2Up();
		  	} else if (e.getKeyCode() == KeyEvent.VK_S) {
		  		downKey2 = true;
		  		moveCharacter2Down();
		  	} else if (e.getKeyCode() == KeyEvent.VK_Q) {
		  		for(ControlListener l: listeners)
		  			l.createBomb(2);
		  	}
		}
		   
		/**
		 * @post ControlListeners stop character movement
		 */
		public void keyReleased(KeyEvent e) {
			 if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				 leftKey = false;
			    	if(rightKey) {
			    		moveCharacterRight();
			    	} else if(upKey) {
			    		moveCharacterUp();
			    	} else if(downKey) {
			    		moveCharacterDown();
			    	} else {
			    		moveCharacterNowhere();
			    	}
			  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			  		rightKey = false;
			  		if(leftKey){
			  			moveCharacterLeft();
			  		} else if(upKey) {
			    		moveCharacterUp();
			    	} else if(downKey) {
			    		moveCharacterDown();
			    	} else {
			    		moveCharacterNowhere();
			    	}
			  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			  		upKey = false;
			    	if(leftKey){
			    		moveCharacterLeft();
			    	} else if(rightKey) {
			    		moveCharacterRight();
			    	} else if(downKey) {
			    		moveCharacterDown();
			    	} else {
			    		moveCharacterNowhere();
			    	}
			  	} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			  		downKey = false;
			  		if(leftKey){
			    		moveCharacterLeft();
			    	} else if(rightKey) {
			    		moveCharacterRight();
			    	} else if(upKey){
			  			moveCharacterUp();
			    	} else {
			    		moveCharacterNowhere();
			    	}
			  	}
		    
		    if (e.getKeyCode() == KeyEvent.VK_A) {
		    	leftKey2 = false;
		    	if(rightKey2) {
		    		moveCharacter2Right();
		    	} else if(upKey2) {
		    		moveCharacter2Up();
		    	} else if(downKey2) {
		    		moveCharacter2Down();
		    	} else {
		    		moveCharacter2Nowhere();
		    	}
		  	} else if (e.getKeyCode() == KeyEvent.VK_D) {
		  		rightKey2 = false;
		  		if(leftKey2){
		  			moveCharacter2Left();
		  		} else if(upKey2) {
		    		moveCharacter2Up();
		    	} else if(downKey2) {
		    		moveCharacter2Down();
		    	} else {
		    		moveCharacter2Nowhere();
		    	}
		  	} else if (e.getKeyCode() == KeyEvent.VK_W) {
		  		upKey2 = false;
		    	if(leftKey2){
		    		moveCharacter2Left();
		    	} else if(rightKey2) {
		    		moveCharacter2Right();
		    	} else if(downKey2) {
		    		moveCharacter2Down();
		    	} else {
		    		moveCharacter2Nowhere();
		    	}
		  	} else if (e.getKeyCode() == KeyEvent.VK_S) {
		  		downKey2 = false;
		  		if(leftKey2){
		    		moveCharacter2Left();
		    	} else if(rightKey2) {
		    		moveCharacter2Right();
		    	} else if(upKey2){
		  			moveCharacter2Up();
		    	} else {
		    		moveCharacter2Nowhere();
		    	}
		  	}
		 }

		 public void keyTyped(KeyEvent e) {
		  	
		 }
		 
		private void moveCharacterLeft() {
			for(ControlListener l: listeners)
				l.moveCharacter(1,ControlListener.LEFT);
		}
		private void moveCharacterRight() {
			for(ControlListener l: listeners)
		  		l.moveCharacter(1,ControlListener.RIGHT);
		}		
		private void moveCharacterUp() {
			for(ControlListener l: listeners)
		 		l.moveCharacter(1,ControlListener.UP);
		}		
		private void moveCharacterDown() {
			for(ControlListener l: listeners)
				l.moveCharacter(1,ControlListener.DOWN);
		}
		private void moveCharacterNowhere() {
			for(ControlListener l: listeners)
	    		l.moveCharacter(1,ControlListener.NO_MOVEMENT);
		}
		
		private void moveCharacter2Left() {
			for(ControlListener l: listeners)
				l.moveCharacter(2,ControlListener.LEFT);
		}
		private void moveCharacter2Right() {
			for(ControlListener l: listeners)
		  		l.moveCharacter(2,ControlListener.RIGHT);
		}		
		private void moveCharacter2Up() {
			for(ControlListener l: listeners)
		 		l.moveCharacter(2,ControlListener.UP);
		}
		private void moveCharacter2Down() {
			for(ControlListener l: listeners)
				l.moveCharacter(2,ControlListener.DOWN);
		}
		private void moveCharacter2Nowhere() {
			for(ControlListener l: listeners)
	    		l.moveCharacter(2,ControlListener.NO_MOVEMENT);
		}
		
	}
}
