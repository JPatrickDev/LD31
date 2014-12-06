package me.jack.ld31.Entity;

import me.jack.ld31.Item.Item;
import me.jack.ld31.Level.Level;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityItem extends Entity{

    Item item;
    public EntityItem(float x, float y,Item item) {
        super(x, y);
        this.item = item;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(Level level) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(item.getSprite(),x,y);
    }

    @Override
    public void onPlayerIntersect(Level level) {
        level.removeEntity(this);
        level.getPlayer().ammo +=5;
    }
}
