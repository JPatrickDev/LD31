package me.jack.ld31.Entity;

import org.newdawn.slick.Image;

/**
 * Created by Jack on 06/12/2014.
 */
public abstract class EntityGrenade extends Entity{


    protected int splashRadius;
    protected float damageDrop;
    protected Image sprite;
    protected float initialDamage;
    public EntityGrenade(float x, float y, int splashRadius, float damageDrop,Image sprite, float initialDamage) {
        super(x, y);
        this.splashRadius = splashRadius;
        this.damageDrop = damageDrop;
        this.sprite = sprite;
        this.initialDamage = initialDamage;
    }

}
