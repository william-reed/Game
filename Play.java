package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {
	Image worldMap, menu, sprite1, spritetest, spriteDown, spriteUp,
			spriteRight, spriteLeft, test, spriteUp01, spriteUp02;
	boolean quit = false;
	boolean go = true;
	boolean play = true;
	boolean move01 = true;
	boolean down = true;
	boolean move02, move03, move04, move05, move06, move07, move08, up, left,
			right;
	int[] duration = { 200, 200 }; // duration or length of the frame
	double count = 0;
	float buckyPositionX = -1200; // bucky will start at coordinates 0,0
	float buckyPositionY = -1200;
	float shiftX = buckyPositionX + 1700; // this will shift the screen so bucky
											// appears in middle
	float shiftY = buckyPositionY + 1481; // half the length and half the width
											// of the screen
	float homelessX = 600;
	float homelessY = 743;

	public Play(int state) {
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		worldMap = new Image("res/level1.png");
		menu = new Image("res/menu.png");
		sprite1 = new Image("res/homeless.png");
		spritetest = new Image("res/sprite1.png");
		// sprite sheet set up
		int spriteWidth = 60; // width of each sprite
		int spriteHeight = 80; // height of each sprite
		int spacing = 0; // 0 px between sprites
		SpriteSheet sheet = new SpriteSheet(spritetest, spriteWidth,
				spriteHeight, spacing); // creates multiple Image2D objects via
										// getSubImage
		spriteDown = sheet.getSprite(1, 2); // gets sprite at index (0, 0)
		spriteUp = sheet.getSprite(1, 0);
		spriteRight = sheet.getSprite(2, 1);
		spriteLeft = sheet.getSprite(2, 3);
		spriteUp01 = sheet.getSprite (0,0);
		spriteUp02 = sheet.getSprite(2,0);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// map
		worldMap.draw(buckyPositionX, buckyPositionY);
		// main sprite
		if (down == true) {
			spriteDown.draw(shiftX, shiftY);
		}
		if (up == true) {
			//spriteUp.draw(shiftX, shiftY);
			test.draw(shiftX, shiftY);
		}
		if (right == true) {
			spriteRight.draw(shiftX, shiftY);
		}
		if (left == true) {
			spriteLeft.draw(shiftX, shiftY);
		}
		// first sprite
		sprite1.draw(buckyPositionX + homelessX, buckyPositionY + homelessY);
		g.drawString("X Position: " + buckyPositionX + "\nY Position: "
				+ buckyPositionY + "\nSpritex: " + homelessX + "\nSpritey: "
				+ homelessY + "\nCount " + count, 750, 20);
		// when they press escape
		if (quit == true) {
			menu.draw(0, 0);
			if (quit == false) {
				g.clear();
			}
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if(count<2){
			count+= .05;
		}else{
			count = 0;
		}
		if(count < 1 ){
			test = spriteUp01;
		}else{
			test = spriteUp02;
		}
		// Homless man path
		if (play == true) {
			if (move01 == true) {
				homelessX += .04f;
				if (homelessX > 700) {
					move01 = false;
					move02 = true;
				}
			}
			if (move02 == true) {
				homelessY += .04f;
				if (homelessY > 900) {
					move02 = false;
					move03 = true;
				}
			}
			if (move03 == true) {
				homelessX += .04f;
				if (homelessX > 800) {
					move03 = false;
					move04 = true;
				}
			}
			if (move04 == true) {
				homelessY += .04f;
				if (homelessY > 1000) {
					move04 = false;
					move05 = true;
				}
			}
			if (move05 == true) {
				homelessX -= .04f;
				if (homelessX < 675) {
					move05 = false;
					move06 = true;
				}
			}
			if (move06 == true) {
				homelessY += .04f;
				if (homelessY > 1100) {
					move06 = false;
					move07 = true;
				}
			}
			if (move07 == true) {
				homelessX -= .04f;
				if (homelessX < 600) {
					move07 = false;
					move08 = true;
				}
			}
			if (move08 == true) {
				homelessY -= .04f;
				if (homelessY < 743) {
					move08 = false;
					move01 = true;
				}
			}

		}

		// during the game if the user hits the up arrow...
		if (quit == false) {
			if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
				up = true;
				right = false;
				left = false;
				down = false;
				buckyPositionY += delta * .1f; // increase the Y coordinates of
												// bucky (move him up)

				if (buckyPositionY > -1059) {
					buckyPositionY -= delta * .1f;

				}

			}
			if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
				down = true;
				up = false;
				left = false;
				right = false;
				buckyPositionY -= delta * .1f;

				if (buckyPositionY < -2289) {
					buckyPositionY += delta * .1f;
				}

			}
			if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
				left = true;
				right = false;
				up = false;
				down = false;
				buckyPositionX += delta * .1f;

				if (buckyPositionX > -853) {
					buckyPositionX -= delta * .1f;
				}

			}
			if (input.isKeyDown(Input.KEY_RIGHT)
					|| input.isKeyDown(Input.KEY_D)) {
				right = true;
				left = false;
				down = false;
				up = false;
				buckyPositionX -= delta * .1f;

				if (buckyPositionX < -2094) {
					buckyPositionX += delta * .1f;
				}

			}
		} else {
		}

		// escape
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
			play = false;
		}

		// when they hit escape
		if (quit == true) {
			if (input.isKeyDown(Input.KEY_R)) {
				quit = false;
				play = true;
			}
			if (input.isKeyDown(Input.KEY_M)) {
				quit = false;
				sbg.enterState(0);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (input.isKeyDown(Input.KEY_Q)) {
				System.exit(0);
			}
		}
	}

	public int getID() {
		return 1;
	}
}