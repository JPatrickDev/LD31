package me.jack.ld31.Weapon;

import me.jack.ld31.Entity.Entity;
import me.jack.ld31.Entity.EntityMissileProjectile;
import me.jack.ld31.Entity.EntityProjectile;
import me.jack.ld31.Entity.Mob;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Projectile.BulletProjectile;
import me.jack.ld31.Projectile.MissileProjectile;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

import java.util.Random;

/**
 * Created by Jack on 06/12/2014.
 */
public class Missile extends Weapon{



    long lastShot = 0;

    public boolean splash = false;

    public float splashRadius = 0.5f;
    public boolean split = false;

    public Missile() {
        super(Level.weapons.getSprite(2,0));
        ammo = 10;
        shotDelayMilis = 200;

    }

    @Override
    public void use(Level level, int mx, int my) {
        if(lastShot ==0 && ammo > 0) {
            lastShot = System.currentTimeMillis();
            shoot(level);
            if(split)
                shoot(level);
            return;
        }

        long currentTime = System.currentTimeMillis();

        if(currentTime - lastShot >=shotDelayMilis && ammo > 0)  {
            shoot(level);
            if(split)
                shoot(level);
            lastShot = System.currentTimeMillis();
        }
    }

    private void shoot(Level level){

        Mob rand = getRandomMob(level);
        if(rand == null)return;
        SoundEngine.getInstance().play("launch");
        EntityMissileProjectile bullet = new EntityMissileProjectile(level.getPlayer().getX(),level.getPlayer().getY(),new MissileProjectile(),rand,splash,splashRadius);
        level.addEntity(bullet);
        bullet.getProjectile().onSpawn(level);
        ammo-=1;
    }

    private Mob getRandomMob(Level level) {
        if(level.entities.size() == 0)return null;
        Random r = new Random();
        boolean found = false;
        int tries = 0;//Don't try for ever
        while(!found){
           int i = r.nextInt(level.entities.size());
            Entity e = level.entities.get(i);
            if(e instanceof Mob){
                found = true;
                return (Mob) e;
            }
            tries++;
            if(tries>= 500)return null;
        }

        return null;
    }
}
