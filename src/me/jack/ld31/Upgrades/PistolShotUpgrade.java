package me.jack.ld31.Upgrades;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Weapon.Pistol;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 07/12/2014.
 */
public class PistolShotUpgrade extends Upgrade{

    int newShots;
    int i;
    public PistolShotUpgrade(Image icon, String name,String description, int cost,int newShots, int i) {
        super(icon,name, description, cost);
        this.newShots = newShots;

        this.i = i;
        if(i > 4)i =4;
    }

    @Override
    public void use(Level level) {
        ((Pistol)level.getPlayer().weaponWheel.get(0)).shotsPerUse = newShots;
    }

    @Override
    public Upgrade nextUpgrade() {
        if(i > 4)return null;
        return new PistolShotUpgrade(icon,"Multi-Shot " + (i+1),"Further increase the fire rate of the pistol",cost+50,newShots+1,i+1);
    }
}
