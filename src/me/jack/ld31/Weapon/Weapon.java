package me.jack.ld31.Weapon;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 06/12/2014.
 */
public abstract class Weapon {

    private Image icon;


    public Weapon(Image icon){
        icon.setFilter(Image.FILTER_NEAREST);
        this.icon = icon.getScaledCopy(2f);
    }
    public abstract void use(Level level, int mx,int my);

    public Image getIcon() {
        return icon;
    }
}
