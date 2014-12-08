package me.jack.ld31.States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jack on 07/12/2014.
 */
public class SplashScreenState extends BasicGameState{


    private ArrayList<SnowFlake> snowFlakes = new ArrayList<SnowFlake>();

    @Override
    public int getID() {
        return 1;
    }


    Image splashImage = null;
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {


        splashImage = ImageUtil.loadImage("res/splashScreen.png");



    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(splashImage,0,0);
            for(SnowFlake flake : snowFlakes){
                flake.render(graphics);
            }
    }

    boolean next = false;
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        for(SnowFlake flake : snowFlakes){
            flake.update();
        }

        if(new Random().nextInt(2)!=0)return;
        int x = new Random().nextInt(800);
        SnowFlake flake = new SnowFlake(x,-16);
        snowFlakes.add(flake);

        if(next)stateBasedGame.enterState(0);
    }

    @Override
    public void keyReleased(int key, char c) {
        next= true;
    }
}

class SnowFlake{

    int x;
    int y;
    private float velocity = 1;

    private float xVel = 1;
    public static Image icon;
    public SnowFlake(int x,int y){
        this.x = x;
        this.y = y;
        this.icon = ImageUtil.loadImage("res/snowflake.png");
    }

    public void render(Graphics g){
        g.drawImage(icon,x,y);
    }

    public void update(){
        y+=velocity;
        velocity+=0.025;

     //   xVel+= 0.005;
    //    x+=xVel;
    }
}