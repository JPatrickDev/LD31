package me.jack.ld31.Entity;

import me.jack.ld31.Item.BulletItem;
import me.jack.ld31.Item.Item;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Weapon.Grenade;
import me.jack.ld31.Weapon.Missile;
import me.jack.ld31.Weapon.Pistol;
import org.newdawn.slick.Graphics;

import java.awt.*;

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
        Rectangle eRectangle = new Rectangle((int) getX(), (int) getY(), 16, 16);
        Rectangle pRectangle = new Rectangle((int) level.getPlayer().getX(), (int) level.getPlayer().getY(), 16, 16);
        if (eRectangle.intersects(pRectangle)) {
            this.onPlayerIntersect(level);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(item.getSprite(),x,y);
    }

    @Override
    public void onPlayerIntersect(Level level) {

        level.removeEntity(this);
       // level.getPlayer().weaponWheel.get(current)//TODO remimplement
        if(item instanceof BulletItem){
            ((Pistol) level.getPlayer().weaponWheel.get(0)).ammo+=5;
            ((Grenade) level.getPlayer().weaponWheel.get(1)).ammo+=1;
            ((Missile) level.getPlayer().weaponWheel.get(2)).ammo+=1;
        }
    }
}
