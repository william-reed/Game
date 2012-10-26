package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
   
   Image background;
   
   public Menu(int state){
   }
   
   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
      background = new Image("res/menuImage.png");
   }
   
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
      background.draw(0,0);
   }
   
   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
      int posX = Mouse.getX();
      int posY = Mouse.getY();
      //play button
      if((posX>439 && posX<561)&&(posY>282 && posY<304)){
         if(Mouse.isButtonDown(0)){
            sbg.enterState(1);
         }
      }
      //exit game
      if((posX>452 && posX<547)&&(posY>216 && posY<241)){
         if(Mouse.isButtonDown(0)){
            System.exit(0);
         }
      }
   }
   
   public int getID(){
      return 0;
   }
}