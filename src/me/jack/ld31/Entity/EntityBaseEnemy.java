package me.jack.ld31.Entity;

import me.jack.ld31.Item.BulletItem;
import me.jack.ld31.Level.Level;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import uk.co.jdpatrick.JEngine.Entity.*;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

import java.awt.Rectangle;
import java.util.Random;


/**
 * Created by Jack on 06/12/2014.
 */
public class EntityBaseEnemy extends Mob{


    float moveSpeed = 1;
    private static Image i;
    public EntityBaseEnemy(float x, float y) {
        super(x, y, 10);
        moveSpeed+= new Random().nextInt(3);
    }

    @Override
    public void init() {
        if(i == null){
            i = ImageUtil.loadImage("/res/snowman.png");
            i.setCenterOfRotation(17,5);
        }
    }

    @Override
    public void update(Level level) {
        if(getHealth() <= 0){
            level.removeEntity(this);
            level.entities.add(new EntityItem(x,y,new BulletItem()));
        }

        /*if(x > level.getPlayer().x){
            x--;
        }
        if(x < level.getPlayer().x){
            x++;
        }

        if(y > level.getPlayer().y){
            y--;
        }
        if(y < level.getPlayer().y){
            y++;
        }*/

        float xSpeed = (level.getPlayer().x - x);
        float ySpeed = (level.getPlayer().y - y);
        Random r = new Random();
        float factor = (float) (moveSpeed/ Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        x+=xSpeed;
        y+=ySpeed;



        Rectangle me = new Rectangle((int)x,(int)y,32,32);
        for(Entity e : level.entities){
            if(!(e instanceof EntityProjectile))continue;
            Rectangle eRect = new Rectangle((int)e.getX(),(int)e.getY(),8,8);
            if(eRect.intersects(me)){
                this.removeHealth(((EntityProjectile)e).getProjectile().getDamage());
                ((EntityProjectile)e).notifyHitEntity(this,level);
            }
        }

         angle = (float) -(Math.atan2(this.x - level.getPlayer().getX(), this.y - level.getPlayer().getY()) * 180 / Math.PI);
    }
    float angle;
    public float rand(){
        float rnd = (float) (Math.random() * (0.15-0.1) + 0.1);
        System.out.println(rnd);
        return rnd;
    }

    private Color color = Color.cyan;
    @Override
    public void render(Graphics g) {
       i.setRotation(angle);
       g.drawImage(i,x,y);
        i.setRotation(0f);
    }

    @Override
    public void onPlayerIntersect(Level level) {
        System.out.println("Player taking damage");
    }
}
