package me.jack.ld31.GUI;

import me.jack.ld31.Level.Level;
import me.jack.ld31.States.InGameState;
import me.jack.ld31.Upgrades.MissileSplashUpgrade;
import me.jack.ld31.Upgrades.PistolShotUpgrade;
import me.jack.ld31.Upgrades.PistolSpeedUpgrade;
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
        pistolUpgrades.add(new PistolSpeedUpgrade(Level.upgrades.getSprite(0, 0), "Rate of fire 1","Increases the rate of fire\nof the pistol", 10,1));
        pistolUpgrades.add(new PistolShotUpgrade(Level.upgrades.getSprite(0, 0), "Mutli-shot 1", "Increases the rate of fire\nof the pistol", 200, 2,1));
        upgrades.put(level.getPlayer().weaponWheel.get(0), pistolUpgrades);


        ArrayList<Upgrade> missileUpgrades = new ArrayList<Upgrade>();
        missileUpgrades.add(new MissileSplashUpgrade(Level.weapons.getSprite(2,0),"Splash damage:  " + (1),"Activate splash damage for the missile",150,0.8f,1));

        upgrades.put(level.getPlayer().weaponWheel.get(2), missileUpgrades);

    }

    private static boolean open = false;
    private static Weapon displaying;

    public static void open(Weapon weapon) {
        displaying = weapon;
        open = true;
        if(upgrades.get(weapon) != null) {
            height = upgrades.get(weapon).size() * 64;
            y = 300 - (height / 2);
        }else{
            height = 20;
            y = 300-(height/2);
        }
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

        if(upgradesToShow == null || upgradesToShow.size() == 0){
        g.setColor(Color.white);
            g.drawString("No upgrades available",x,y);
            return;
        }
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

           if(upgrade.getCost() > InGameState.level.getPlayer().kills){
                  Color grayedOut = new Color(255,0,0,58);
                g.setColor(grayedOut);
                g.fillRect(x,yy,width,heightPerUpgrade);
                g.setColor(Color.white);
            }
            yy+=heightPerUpgrade;
        }
    }

    public static void mouseClick(int mouseX,int mouseY,Level level){
        if(!open)return;
        if(mouseX < x || mouseX > x + width  || mouseY < y || mouseY > y+height){
            return;
        }

        int pos = (mouseY - y)/64;
        if(pos > upgrades.get(displaying).size())return;

        if(upgrades.get(displaying).size() == 0)return;
        Upgrade i = upgrades.get(displaying).get(pos);

        if(i.getCost() <=  level.getPlayer().kills) {
            i.use(level);


            level.getPlayer().kills-= i.getCost();
            height = upgrades.get(displaying).size() * 64;
            y = 300-(height/2);
            Upgrade next = i.nextUpgrade();
        if(next != null)
            upgrades.get(displaying).add(next);
                    upgrades.get(displaying).remove(i);
       }else{
           System.out.println("Can't afford");//TODO add proper feedback
        }
    }

    public static void close() {
        displaying = null;
        open = false;

    }

    public static boolean isOpen() {
        return open;
    }
}
