package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.*;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityPlayer extends Entity{


    public EntityPlayer(float x, float y) {
        super(x, y);

        System.out.println(x + ":" + y);
    }

    @Override
    public void init() {

    }


    float moveSpeed = 4f;
    @Override
    public void update(Level level) {

        float newX = x;
        float newY = y;

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            newY-=moveSpeed;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            newX-=moveSpeed;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            newY+=moveSpeed;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            newX+=moveSpeed;
        }

        Rectangle newPlayer = new Rectangle((int)newX,(int)newY,8,8);


        boolean canMove = true;
        for(Rectangle h : level.hitboxes){
            if(newPlayer.intersects(h)){
                canMove = false;
            }
        }
        if(canMove){
            x = newX;
            y = newY;
        }



    }


    public void mousePressed(int mx,int my, int button,Level level){
        EntityBullet bullet = new EntityBullet(x,y,mx,my);

        level.addEntity(bullet);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x,y,Level.TILE_SIZE,Level.TILE_SIZE);
        g.setColor(Color.white);
    }
}
