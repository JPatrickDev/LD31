package me.jack.ld31.Weapon;

import me.jack.ld31.Entity.EntityBasicGrenade;
import me.jack.ld31.Entity.EntityProjectile;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Projectile.BulletProjectile;

/**
 * Created by Jack on 06/12/2014.
 */
public class Grenade extends Weapon{


    long lastShot = 0;



    public float splashRadius;
    public Grenade(float splashRadius) {
        super(Level.weapons.getSprite(1,0));
        ammo = 5;
        shotDelayMilis = 100;
        this.splashRadius = splashRadius;
    }

    @Override
    public void use(Level level, int mx, int my) {
        if(lastShot ==0 && ammo > 0) {
            lastShot = System.currentTimeMillis();
            EntityBasicGrenade bullet = new EntityBasicGrenade(level.getPlayer().getX(),level.getPlayer().getY(),mx,my,splashRadius);
            level.addEntity(bullet);
            ammo-=1;
            return;
        }

        long currentTime = System.currentTimeMillis();

        if(currentTime - lastShot >=shotDelayMilis && ammo > 0)  {
            EntityBasicGrenade bullet = new EntityBasicGrenade(level.getPlayer().getX(),level.getPlayer().getY(),mx,my,splashRadius);
            level.addEntity(bullet);
            lastShot = System.currentTimeMillis();
            ammo-=1;
        }
    }

}
