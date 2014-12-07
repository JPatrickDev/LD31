package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Particle.ExplosionParticle;
import me.jack.ld31.Projectile.Projectile;
import org.newdawn.slick.Color;
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
            for(int i =0;i!= 50;i++){
                level.particleSystem.addParticle(new ExplosionParticle((int)x,(int)y,5));
            }
            return;
        }
        if(firstTick){
            firstTick = false;
            projectile.onSpawn(level);
        }

        if(x<0|| x > 800 || y < 0 || y > 600){
            level.removeEntity(this);
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

        if(target.getHealth() <= 0){
            for(int i =0;i!= 200;i++){
                level.particleSystem.addParticle(new ExplosionParticle((int)x,(int)y,1));
            }
           level.removeEntity(this);
        }


    }

    public void notifyHitEntity(Entity hit,Level level){
        projectile.onHitEntity(level);
        level.removeEntity(this);
        for(int i =0;i!= 200;i++){
            level.particleSystem.addParticle(new ExplosionParticle((int)x,(int)y,1));
        }
    }

    @Override
    public void render(Graphics g) {
        if(angle == 0)return;
        projectile.getImage().setRotation(angle);
        g.drawImage(projectile.getImage(), x, y);
        projectile.getImage().setRotation(0);
    }

}
