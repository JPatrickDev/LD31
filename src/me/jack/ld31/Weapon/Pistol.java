package me.jack.ld31.Weapon;

import me.jack.ld31.Entity.EntityProjectile;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Projectile.BulletProjectile;
import org.lwjgl.Sys;
import org.newdawn.slick.Sound;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

/**
 * Created by Jack on 06/12/2014.
 */
public class Pistol extends Weapon {



    long lastShot = 0;
    public int shotsPerUse = 1;


    public Pistol() {
        super(Level.weapons.getSprite(0, 0));
        ammo = 100;
        shotDelayMilis = 100;
    }

    @Override
    public void use(Level level, int mx, int my) {


        if (lastShot == 0 && ammo > 0) {
            lastShot = System.currentTimeMillis();
            for(int i = 0;i!= shotsPerUse;i++)
                fireWeapon(level,mx,my);
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShot >= shotDelayMilis && ammo > 0) {
            fireWeapon(level,mx,my);
        }

    }

    private void fireWeapon(Level level, int mx,int my) {
        SoundEngine.getInstance().play("shot");
        for (int i = 0; i != shotsPerUse; i++){
            EntityProjectile bullet = new EntityProjectile(level.getPlayer().getX(), level.getPlayer().getY(), mx + i * 32, my, new BulletProjectile());
        level.addEntity(bullet);
        bullet.getProjectile().onSpawn(level);
             }
        ammo -= 1;
        lastShot = System.currentTimeMillis();
    }
}
