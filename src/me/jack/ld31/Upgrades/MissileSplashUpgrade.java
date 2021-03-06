package me.jack.ld31.Upgrades;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Weapon.Missile;
import me.jack.ld31.Weapon.Pistol;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 07/12/2014.
 */
public class MissileSplashUpgrade extends Upgrade{


    private float radius;
    int i = 0;
    public MissileSplashUpgrade(Image icon, String name, String description, int cost,float radius, int i) {
        super(icon,name, description, cost);
       this.radius = radius;
        this.i = i;
        if(i > 3)i =3;
    }

    @Override
    public void use(Level level) {
        ((Missile)level.getPlayer().weaponWheel.get(2)).splash = true;
        ((Missile)level.getPlayer().weaponWheel.get(2)).splashRadius = radius;

    }

    @Override
    public Upgrade nextUpgrade() {
        if(i >=3)return null;
        return new MissileSplashUpgrade(icon,"Splash damage:  " + (i+1),"Increase the area of the splash damage",cost+50,(radius+0.5f),i+1);
    }
}
