package me.jack.ld31.Upgrades;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 07/12/2014.
 */
public class PistolSpeed1Upgrade extends Upgrade{

    public PistolSpeed1Upgrade(Image icon, String name,String description, int cost) {
        super(icon,name, description, cost);
    }

    @Override
    public void use(Level level) {
        level.getPlayer().weaponWheel.get(0).shotDelayMilis-=10;
    }
}
