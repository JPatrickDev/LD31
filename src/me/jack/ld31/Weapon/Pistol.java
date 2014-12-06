package me.jack.ld31.Weapon;

import me.jack.ld31.Entity.EntityProjectile;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Projectile.BulletProjectile;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 06/12/2014.
 */
public class Pistol extends Weapon{


    long shotDelayMilis = 100;
    long lastShot = 0;

    public int ammo = 5;

    public Pistol() {
        super(Level.weapons.getSprite(0,0));
    }

    @Override
    public void use(Level level, int mx, int my) {
        if(lastShot ==0 && ammo > 0) {
            lastShot = System.currentTimeMillis();
            EntityProjectile bullet = new EntityProjectile(level.getPlayer().getX(),level.getPlayer().getY(), mx, my, new BulletProjectile());
            level.addEntity(bullet);
            bullet.getProjectile().onSpawn(level);
            ammo-=1;
            return;
        }

        long currentTime = System.currentTimeMillis();

        if(currentTime - lastShot >=shotDelayMilis && ammo > 0)  {
            EntityProjectile bullet = new EntityProjectile(level.getPlayer().getX(),level.getPlayer().getY(), mx, my, new BulletProjectile());
            level.addEntity(bullet);
            bullet.getProjectile().onSpawn(level);
            lastShot = System.currentTimeMillis();
            ammo-=1;
        }
    }
}
