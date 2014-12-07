package me.jack.ld31.Projectile;

import me.jack.ld31.Level.Level;

/**
 * Created by Jack on 06/12/2014.
 */
public class MissileProjectile  extends Projectile{



    public MissileProjectile() {
        super(15f,25,"Missile", Level.weapons.getSprite(2,0),1f);

    }



    @Override
    public void onSpawn(Level level) {
        //  System.out.println("Spawned");
    }

    @Override
    public void onHitEntity(Level level) {

    }

    @Override
    public void onDestroy(Level level) {
        //  System.out.println("Destroyed");
    }
}
