package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Projectile.BulletProjectile;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

import java.awt.*;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityPlayer extends Mob{

    private static Image i;
    public EntityPlayer(float x, float y) {
        super(x, y,50);
    }

    @Override
    public void init() {
        if(i == null){
            i = ImageUtil.loadImage("/res/player.png");
            i.setCenterOfRotation(17,5);
        }
    }


    float moveSpeed = 4f;

    long shotDelayMilis = 25;
    long lastShot = 0;

    public int ammo = 500;

    public float angle;
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

        for(Entity e : level.entities){
            Rectangle eRectangle = new Rectangle((int)e.getX(),(int)e.getY(),16,16);
            if(eRectangle.intersects(newPlayer) || newPlayer.intersects(eRectangle)){
                e.onPlayerIntersect(level);
            }
        }
        if(canMove){
            x = newX;
            y = newY;
        }



    }


    public void mousePressed(int mx,int my, int button,Level level){
        if(lastShot ==0 && ammo > 0) {
            lastShot = System.currentTimeMillis();
            EntityProjectile bullet = new EntityProjectile(x,y,mx,my, new BulletProjectile());
            level.addEntity(bullet);
            bullet.getProjectile().onSpawn(level);
            ammo-=1;
            return;
        }

        long currentTime = System.currentTimeMillis();

        if(currentTime - lastShot >=shotDelayMilis && ammo > 0)  {
            EntityProjectile bullet = new EntityProjectile(x, y, mx, my, new BulletProjectile());
            level.addEntity(bullet);
            bullet.getProjectile().onSpawn(level);
            lastShot = System.currentTimeMillis();
            ammo-=1;
        }
    }

    @Override
    public void render(Graphics g) {
        i.setRotation(angle);
      g.drawImage(i,x,y);
    }

    @Override
    public void onPlayerIntersect(Level level) {

    }
}
