package me.jack.ld31.Upgrades;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 07/12/2014.
 */
public class PistolSpeedUpgrade extends Upgrade{

    long newSpeed;
    private int i;
    public PistolSpeedUpgrade(Image icon, String name,String description, int cost,int i) {
        super(icon,name, description, cost);
        if(i == 2){
            newSpeed = 50;
        }else if(i == 3){
            newSpeed = 30;
        }else{
            newSpeed = 25;
        }
        this.i = i;
    }

    @Override
    public void use(Level level) {
        level.getPlayer().weaponWheel.get(0).shotDelayMilis = newSpeed;
    }

    @Override
    public Upgrade nextUpgrade() {
        return new PistolSpeedUpgrade(icon,"Pistol speed " + (i+1),"Further increase the fire rate of the pistol",cost+50,i+1);
    }
}
