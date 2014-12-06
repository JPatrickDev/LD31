package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Projectile.Projectile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityProjectile  extends Entity{


    private float vX;
    private float vY;

    private float life = 1f;

    private Projectile projectile;
    public EntityProjectile(float x, float y, float targetX, float targetY, Projectile projectile) {
        super(x, y);
        float xSpeed = (targetX - x);
        float ySpeed = (targetY - y);

        float factor = (float) (projectile.getMoveSpeed()/ Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        vX = xSpeed;
        vY = ySpeed;

        this.projectile = projectile;


    }


    @Override
    public void init() {

    }

    boolean firstTick = true;

    @Override
    public void update(Level level) {

        if(firstTick){
            firstTick = false;
            projectile.onSpawn(level);
        }

        x+=vX;
        y+=vY;

        if(x<0|| x > 800 || y < 0 || y > 600){
            level.removeEntity(this);
        }
        life-=0.025;
        if(life <=0){
            level.removeEntity(this);
            projectile.onDestroy(level);
        }
    }

    public void notifyHitEntity(Entity hit,Level level){
        projectile.onHitEntity(level);
        level.removeEntity(this);
    }

    @Override
    public void render(Graphics g) {
       g.drawImage(projectile.getImage(),x,y);
    }

    @Override
    public void onPlayerIntersect(Level level) {

    }

    public Projectile getProjectile() {
        return projectile;
    }
}
