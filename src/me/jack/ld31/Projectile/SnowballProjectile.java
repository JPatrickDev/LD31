package me.jack.ld31.Projectile;

import me.jack.ld31.Level.Level;

/**
 * Created by Jack on 06/12/2014.
 */
public class SnowballProjectile  extends Projectile{


    public SnowballProjectile() {
        super(4f,5,"Snowball",Level.sprites.getSprite(3,0));

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
