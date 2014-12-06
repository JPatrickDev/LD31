package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Projectile.BulletProjectile;
import me.jack.ld31.Weapon.Grenade;
import me.jack.ld31.Weapon.Pistol;
import me.jack.ld31.Weapon.Weapon;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityPlayer extends Mob{

    private static Image i;
    public EntityPlayer(float x, float y) {
        super(x, y,50);
    }

    public ArrayList<Weapon> weaponWheel = new ArrayList<Weapon>();

    @Override
    public void init() {
        if(i == null){
            i = ImageUtil.loadImage("/res/player.png");
            i.setCenterOfRotation(17,5);
        }

        weaponWheel.add(new Pistol());
        weaponWheel.add(new Grenade());
    }


    float moveSpeed = 4f;

    public int currentWeapon = 1;


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

        weaponWheel.get(currentWeapon).use(level,mx,my);

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
