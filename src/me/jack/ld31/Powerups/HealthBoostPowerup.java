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
        if(System.currentTimeMillis() - startTime >= lifeSpan){
            System.out.println("Removing effect");
            removeEffects(level);
        }
    }



    @Override
    public void removeEffects(Level level){
        level.getPlayer().removeHealth(50f);
        level.getPlayer().maxHealth = oldMax;
        level.getPlayer().removePowerup(this);
    }

    @Override
    public void renderEffect(EntityPlayer player,Graphics g,float angle) {
        effect.setRotation(angle);
        g.drawImage(effect,player.getX(),player.getY());
        effect.setRotation(0);
    }

    float oldMax = -1f;

    @Override
    public void use(Level level) {
        this.startTime = System.currentTimeMillis();
        oldMax = level.getPlayer().maxHealth;
        level.getPlayer().addHealth(50f);
        level.getPlayer().maxHealth = level.getPlayer().maxHealth + 50f;
        level.getPlayer().addPowerup(this);
    }
}
