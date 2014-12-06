package me.jack.ld31.Projectile;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 06/12/2014.
 */
public class BulletProjectile extends Projectile{


    public BulletProjectile() {
        super(5f,10,"Bullet");

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
