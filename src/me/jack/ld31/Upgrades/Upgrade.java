package me.jack.ld31.Upgrades;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 07/12/2014.
 */
public abstract class Upgrade {

    protected Image icon;
    protected String description;
    protected int cost;
    protected String name;

    public Upgrade(Image icon,String name,String description, int cost){
        this.icon = icon;
        this.description = description;
        this.cost = cost;
        this.name = name;
    }

    public abstract void use(Level level);

    public Image getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
