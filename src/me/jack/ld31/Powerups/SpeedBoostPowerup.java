package me.jack.ld31.Powerups;

import me.jack.ld31.Entity.EntityPlayer;
import me.jack.ld31.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

/**
 * Created by Jack on 07/12/2014.
 */
public class SpeedBoostPowerup extends TimeBasedPowerup{


    private static Image effect;
    public SpeedBoostPowerup(Image icon, long lifeSpan) {
        super(icon, lifeSpan);
        if(effect == null)effect = effects.getSprite(0,0);
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
          level.getPlayer().moveSpeed = 4f;
        level.getPlayer().removePowerup(this);
    }

    @Override
    public void renderEffect(EntityPlayer player,Graphics g,float angle) {
        effect.setRotation(angle);
            g.drawImage(effect,player.getX(),player.getY());
        effect.setRotation(0);
    }

    @Override
    public void use(Level level) {
        this.startTime = System.currentTimeMillis();
        level.getPlayer().moveSpeed = 6f;
        level.getPlayer().addPowerup(this);
    }
}
