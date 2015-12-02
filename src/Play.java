import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	HexMap map;
	Game game;
	HouseClickArea houseArea;
	RoadClickArea roadArea;
	Controller control;
	OnScreenButton buttons;
	OnScreenTextField textField;
	
	Image playBackground;
	
	public Card infoCard;
	
	
	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		control = new Controller();
		map = new HexMap();
		houseArea = new HouseClickArea(control);
		roadArea = new RoadClickArea(control);
		buttons = new OnScreenButton(control);
		textField = new OnScreenTextField(control);
		textField.create(gc);
		
		playBackground = new Image("images/PlayBackground.png");
		
		infoCard = new Card(control);
		infoCard.cardType = new Image ("images/info.png");
		infoCard.x = 990;
		infoCard.y = 170;

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.drawImage(playBackground, 0,0);
		
		map.render(gc, g);
		roadArea.render(gc, g);
		houseArea.render(gc, g);
		buttons.render(gc, g);
		textField.render(gc, g);
		infoCard.render(gc, g);
		control.resources.render(gc, g);
		
	}
	
	

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
				
		if(game.client.obj.roundCount == 0){
			sbg.enterState(2);
		}

		Thread t1 = new Thread() {
			@Override
			public void run() {
				synchronized (houseArea) {
					try {
						houseArea.update(gc, delta);
					} catch (SlickException e) {
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				synchronized (roadArea) {
					try {
						roadArea.update(gc, delta);
					} catch (SlickException e) {
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t3 = new Thread(){
			@Override
			public void run() {
				synchronized (textField) {
					try {
						textField.update(gc, delta);
					} catch (SlickException | IOException e) {
						//e.printStackTrace();
					}
				}	
			}
			
		};

		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
		}

	}

	public int getID() {
		return 1;
	}

}