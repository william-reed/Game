package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
   
   public static final String gamename = "Game...";
   public static final int menu = 0;
   public static final int play = 1;
   
   public Game(String gamename){
      super(gamename);
      this.addState(new Menu(menu));
      this.addState(new Play(play));
   }
   
   public void initStatesList(GameContainer gc) throws SlickException{
     //gc.setIcon("res/icon.png");
	  gc.setTargetFrameRate(59);
      this.getState(menu).init(gc, this);
      this.getState(play).init(gc, this);
      this.enterState(menu);
   }
   
   public static void main(String[] args) {
      AppGameContainer appgc;
      try{
         appgc = new AppGameContainer(new Game(gamename));
         appgc.setDisplayMode(1000, 563, false);
         appgc.start();
      }catch(SlickException e){
         e.printStackTrace();
      }
   }

}