package me.jack.ld31.Entity;

/**
 * Created by Jack on 06/12/2014.
 */
public abstract class Mob extends Entity {

    private float health;

    public Mob(float x, float y, float startHealth) {
        super(x, y);
        this.health = startHealth;
    }

    public float getHealth() {
        return this.health;
    }

    public void removeHealth(float remove) {
        health -= remove;
    }

    public void addHealth(float add) {
        health += add;
    }
}
