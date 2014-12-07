package me.jack.ld31.GUI;

import me.jack.ld31.Level.Level;
import me.jack.ld31.Upgrades.PistolSpeed1Upgrade;
import me.jack.ld31.Upgrades.Upgrade;
import me.jack.ld31.Weapon.Weapon;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jack on 07/12/2014.
 */
public class UpgradesGUI {

    public static HashMap<Weapon, ArrayList<Upgrade>> upgrades = new HashMap<Weapon, ArrayList<Upgrade>>();


    public static void init(Level level) {
        ArrayList<Upgrade> pistolUpgrades = new ArrayList<Upgrade>();
        pistolUpgrades.add(new PistolSpeed1Upgrade(Level.upgrades.getSprite(0, 0), "Rate of fire 1","Increases the rate of fire\nof the pistol", 50));
        upgrades.put(level.getPlayer().weaponWheel.get(0), pistolUpgrades);
    }

    private static boolean open = false;
    private static Weapon displaying;

    public static void open(Weapon weapon) {
        displaying = weapon;
        open = true;
    }

    private static int width = 256;
    private static int height = 512;
    private static int x = 400 - 128;
    private static int y = 300-256;
    public static void render(Graphics g){
        if(!open){
            return;
        }
        g.fillRect(x,y,width,height);
        ArrayList<Upgrade> upgradesToShow = upgrades.get(displaying);

        int yy = y;
        int heightPerUpgrade = 64;
        for(Upgrade upgrade  :upgradesToShow){
            g.setColor(Color.gray);
            g.fillRect(x,yy,width,heightPerUpgrade);
            g.setColor(Color.white);

            g.drawImage(upgrade.getIcon(),x,yy);
            g.drawString(upgrade.getName(),x + 18,yy);
            g.drawString("- Cost: " + upgrade.getCost(),x+150,yy);
            g.drawString(upgrade.getDescription(),x,yy+16);
        }
    }
}
