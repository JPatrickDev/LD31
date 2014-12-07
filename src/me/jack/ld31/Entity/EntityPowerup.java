package me.jack.ld31.Entity;

import me.jack.ld31.Item.BulletItem;
import me.jack.ld31.Item.Item;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Powerups.Powerup;
import me.jack.ld31.Weapon.Pistol;
import org.newdawn.slick.*;

import java.awt.*;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityPowerup extends Entity{

    Powerup powerup;
    public EntityPowerup(float x, float y,Powerup powerup) {
        super(x, y);
       this.powerup = powerup;
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
    public void render(org.newdawn.slick.Graphics g) {
        g.drawImage(powerup.getIcon(),x,y);
    }

    @Override
    public void onPlayerIntersect(Level level) {
        level.removeEntity(this);
        powerup.use(level);
    }
}
