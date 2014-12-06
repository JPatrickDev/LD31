package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 06/12/2014.
 */
public abstract class Entity{

    protected float x;
    protected float y;

    public Entity(float x,float y){
        this.x = x;
        this.y = y;
    }

    public abstract void init();
    public abstract void update(Level level);
    public abstract void render(Graphics g);

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract void onPlayerIntersect(Level level);
}
