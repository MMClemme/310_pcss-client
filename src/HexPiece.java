

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class HexPiece {
	
	Image img;
	int x;
	int y;
	int index;
	
	HexPiece(int x, int y, int index) throws SlickException{
		this.x = x;
		this.y = y;
		this.index = index;
		img = new Image("images/tile"+index+".png");
		
	}
	
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(img,x,y);	
	}
}