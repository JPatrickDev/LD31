package me.jack.ld31.Powerups;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 06/12/2014.
 */
public class IncreaseMaxHealth extends Powerup{

    public IncreaseMaxHealth() {
        super(Level.powerups.getSprite(0,0));
    }

    @Override
    public void use(Level level) {
        level.getPlayer().maxHealth += 20;
        level.getPlayer().addHealth(level.getPlayer().maxHealth - level.getPlayer().getHealth());
    }
}
