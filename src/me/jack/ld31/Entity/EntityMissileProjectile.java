package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Projectile.Projectile;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityMissileProjectile extends EntityProjectile{
    private Mob target;
    public EntityMissileProjectile(float x, float y,Projectile projectile,Mob target) {
        super(x, y,0,0, projectile);
        this.target = target;

    }


    boolean firstTick = true;

    float angle;
    @Override
    public void update(Level level) {
        if(target == null){
            level.entities.remove(this);
            return;
        }
        if(firstTick){
            firstTick = false;
            projectile.onSpawn(level);
        }

        if(x<0|| x > 800 || y < 0 || y > 600){
            level.removeEntity(this);
        }
        life-=0.025;
        if(life <=0){
            level.removeEntity(this);
            projectile.onDestroy(level);
        }

        float xSpeed = (target.getX() - x);
        float ySpeed = (target.getY() - y);

        float factor = (float) (projectile.getMoveSpeed()/ Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        vX = xSpeed;
        vY = ySpeed;

        x+=vX;
        y+=vY;

        angle = (float) -(Math.atan2(this.x - target.getX(), this.y - target.getY()) * 180 / Math.PI);


    }

    public void notifyHitEntity(Entity hit,Level level){
        projectile.onHitEntity(level);
        level.removeEntity(this);
    }

    @Override
    public void render(Graphics g) {
        if(angle == 0)return;
        projectile.getImage().setRotation(angle);
        g.drawImage(projectile.getImage(),x,y);
        projectile.getImage().setRotation(0);
    }

}
