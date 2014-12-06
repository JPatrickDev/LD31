package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Particle.ExplosionParticle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import uk.co.jdpatrick.JEngine.Particle.BloodParticle;

import java.awt.*;
import java.awt.Rectangle;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityBasicGrenade extends EntityGrenade{

    float vX;
    float vY;
    private Rectangle stopAt;
    public EntityBasicGrenade(float x, float y, int mx,int my) {
        super(x, y, 1, 0.5f, Level.sprites.getSprite(2,0), 10);
        stopAt = new Rectangle(mx,my,8,8);
        this.spawn = System.currentTimeMillis();
        float xSpeed = (mx - x);
        float ySpeed = (my - y);

        float factor = (float) (5/ Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        vX = xSpeed;
        vY = ySpeed;

    }


    long fuse = 2000;
    long spawn = -1;
    @Override
    public void init() {

    }

    @Override
    public void update(Level level) {
            if(System.currentTimeMillis() - spawn >= fuse){
                level.removeEntity(this);
                for(int i =0;i!= 50;i++){
                    level.particleSystem.addParticle(new ExplosionParticle((int)x,(int)y,splashRadius));
                }
                Circle circle = new Circle(x,y,splashRadius);
                for(Entity e : level.entities){
                    if(!(e instanceof Mob))continue;
                    org.newdawn.slick.geom.Rectangle rect = new org.newdawn.slick.geom.Rectangle(e.getX(),e.getY(),16,16);
                    if(rect.intersects(circle)){
                        ((Mob)e).removeHealth(initialDamage);
                    }
                }

            }

        x+=vX;
        y+=vY;
        Rectangle me = new Rectangle((int)x,(int)y,8,8);
        if(me.intersects(stopAt)){
            vX = 0;
            vY = 0;
        }
      //  vX /=1.1;
       // vY /=1.1;
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(sprite,x,y);
    }

    @Override
    public void onPlayerIntersect(Level level) {

    }
}
