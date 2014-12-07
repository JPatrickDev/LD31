package me.jack.ld31.Powerups;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 06/12/2014.
 */
public abstract class Powerup {

    protected Image icon;

    public Powerup(Image icon){
        this.icon = icon;
    }

    public abstract void use(Level level);

    public Image getIcon() {
        return icon;
    }
}
