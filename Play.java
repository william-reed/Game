package javagame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	Animation bucky, movingUp, movingDown, movingLeft, movingRight; // 4
																	// animations,
																	// bucky
																	// will be
																	// set to
																	// one
	Image worldMap, menu, sprite1, spritetest, sprite01,sprite02,sprite03,sprite04;
	boolean quit = false;
	int[] duration = { 200, 200 }; // duration or length of the frame
	float buckyPositionX = 0; // bucky will start at coordinates 0,0
	float buckyPositionY = 0;
	float shiftX = buckyPositionX + 500; // this will shift the screen so bucky
											// appears in middle
	float shiftY = buckyPositionY + 281; // half the length and half the width
											// of the screen
	//int sprite01,sprite02,sprite03,sprite04;
	float spritex = 675;
	float spritey = 743;

	public Play(int state) {
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		worldMap = new Image("res/world.png");
		menu = new Image("res/menu.png");
		sprite1 = new Image("res/sprite1.png");
		spritetest = new Image("res/spritetest.png");
		Image[] walkUp = { new Image("res/buckysBack.png"),
				new Image("res/buckysBack.png") }; // these are the images to be
													// used in the "walkUp"
													// animation
		Image[] walkDown = { new Image("res/buckysFront.png"),
				new Image("res/buckysFront.png") };
		Image[] walkLeft = { new Image("res/buckysLeft.png"),
				new Image("res/buckysLeft.png") };
		Image[] walkRight = { new Image("res/buckysRight.png"),
				new Image("res/buckysRight.png") };

		movingUp = new Animation(walkUp, duration, false); // each animation
															// takes array of
															// images, duration
															// for each image,
															// and autoUpdate
															// (just set to
															// false)
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		bucky = movingDown; // by default as soon as game loads, bucky will be
							// facing down
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		
		worldMap.draw(buckyPositionX, buckyPositionY); // draw the map at 0,0 to
														// start
		bucky.draw(shiftX, shiftY); // draw bucky at 320, 160 (center of the
									// screen)4
		sprite1.draw(buckyPositionX + spritex, buckyPositionY + spritey);// first
																			// sprite
		//spritesheet test
		
		sprite01.draw(0, 0);
		sprite02.draw(80, 0);
		sprite03.draw(150, 0);
		sprite04.draw(400, 0);
		// test

		g.drawString("X Position: " + buckyPositionX + "\nY Position: "
				+ buckyPositionY + "\nSpritex: " + spritex + "\nSpritey: "
				+ spritey, 750, 20); // indicator
		// to
		// see
		// where
		// bucky
		// is
		// in his world

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
		// CHANGE TO SWITCH STATEMENT
		// sprite 1
		if (spritex < 900) {
			spritex += delta * .05f;
		}
		if (spritex > 900) {
			spritex = 900;
		}
		if(spritex == 900){
			spritex -= delta * .05f;
		}
		
		//sprite sheet set up
		int spriteWidth = 40; //width of each sprite
		int spriteHeight = 40; //height of each sprite
		int spacing = 0; //0 px between sprites
		SpriteSheet sheet = new SpriteSheet(spritetest, spriteWidth, spriteHeight, spacing); //creates multiple Image2D objects via getSubImage
		sprite01 = sheet.getSprite(0, 0); //gets sprite at index (0, 0)
		sprite02 = sheet.getSprite(1, 0);
		sprite03 = sheet.getSprite(2, 0);
		sprite04 = sheet.getSprite(3, 0);
		
		
		// during the game if the user hits the up arrow...
		if (quit == false) {
			if (input.isKeyDown(Input.KEY_UP)) {
				bucky = movingUp; // change bucky to up image
				buckyPositionY += delta * .1f; // increase the Y coordinates of
												// bucky (move him up)
				/*
				 * if (buckyPositionY > -321) { buckyPositionY -= delta * .1f;
				 * // dont let him keep going up // if he reaches the top }
				 */
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				bucky = movingDown;
				buckyPositionY -= delta * .1f;
				/*
				 * if (buckyPositionY < -1080) { buckyPositionY += delta * .1f;
				 * }
				 */
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				bucky = movingLeft;
				buckyPositionX += delta * .1f;
				/*
				 * if (buckyPositionX > 99) { buckyPositionX -= delta * .1f; }
				 */
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				bucky = movingRight;
				buckyPositionX -= delta * .1f;
				/*
				 * if (buckyPositionX < -1060) { buckyPositionX += delta * .1f;
				 * }
				 */
			}
		} else {
		}

		// escape
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
		}

		// when they hit escape
		if (quit == true) {
			if (input.isKeyDown(Input.KEY_R)) {
				quit = false;
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