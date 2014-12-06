package me.jack.ld31.Projectile;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 06/12/2014.
 */
public abstract class Projectile {

    private float damage;
    private int moveSpeed;
    private String name;
    public static Image i;

    public Projectile(float damage, int moveSpeed,String name){
        this.damage = damage;
        this.moveSpeed = moveSpeed;
        this.name = name;

    }

    public abstract void onSpawn(Level level);

    public abstract void onHitEntity(Level level);

    public abstract void onDestroy(Level level);

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public float getDamage() {
        return damage;
    }

    public Image getImage() {
        return i;
    }
}
