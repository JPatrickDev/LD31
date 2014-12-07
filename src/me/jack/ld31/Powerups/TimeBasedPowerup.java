package me.jack.ld31.Powerups;

import me.jack.ld31.Entity.EntityPlayer;
import me.jack.ld31.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

/**
 * Created by Jack on 07/12/2014.
 */
public abstract class TimeBasedPowerup extends Powerup{

    long lifeSpan = 0;
    long startTime = -1;

    public static SpriteSheet effects;
    public TimeBasedPowerup(Image icon,long lifeSpan) {
        super(icon);
        this.lifeSpan = lifeSpan;
        if(effects == null)effects = new SpriteSheet(ImageUtil.loadImage("/res/powerupeffects.png"),32,32);
    }

    public abstract void update(Level level);

    public abstract void removeEffects(Level level);

    public abstract void renderEffect(EntityPlayer player,Graphics g,float rotation);

}
