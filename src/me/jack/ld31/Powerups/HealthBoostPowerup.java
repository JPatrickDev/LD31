package me.jack.ld31.Powerups;

import me.jack.ld31.Entity.EntityPlayer;
import me.jack.ld31.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 07/12/2014.
 */
public class HealthBoostPowerup extends TimeBasedPowerup{


    private static Image effect;
    public HealthBoostPowerup(Image icon, long lifeSpan) {
        super(icon, lifeSpan);
        if(effect == null)effect = effects.getSprite(1,0);
    }

    @Override
    public void update(Level level) {

    }



    @Override
    public void removeEffects(Level level){

    }

    @Override
    public void renderEffect(EntityPlayer player,Graphics g,float angle) {

    }

    float oldMax = -1f;

    @Override
    public void use(Level level) {
        level.getPlayer().addHealth(50f);

    }
}
