package me.jack.ld31.Upgrades;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Weapon.Missile;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 07/12/2014.
 */
public class MissileSplitUpgrade extends Upgrade{


    private float radius;
    int i = 0;
    public MissileSplitUpgrade(Image icon, String name, String description, int cost,float radius, int i) {
        super(icon,name, description, cost);
        this.radius = radius;
        this.i = i;
        if(i > 3)i =3;
    }

    @Override
    public void use(Level level) {
        ((Missile)level.getPlayer().weaponWheel.get(2)).split = true;

    }

    @Override
    public Upgrade nextUpgrade() {
     return null;
    }
}
