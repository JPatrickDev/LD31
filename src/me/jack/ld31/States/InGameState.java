package me.jack.ld31.States;

import me.jack.ld31.GUI.UpgradesGUI;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Upgrades.Upgrade;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.JEngine;
import uk.co.jdpatrick.JEngine.JEngineGameState;

/**
 * Created by Jack on 06/12/2014.
 */
public class InGameState extends BasicGameState{


    public static Level level;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setShowFPS(false);
        load();
    }

    int oldValue = -1;
    private Image weaponIcon;
    @Override
    public void mouseWheelMoved(int newValue) {
        super.mouseWheelMoved(newValue);
        if(oldValue == -1){
            oldValue = newValue;
            return;
        }

        if(oldValue > newValue){
            if(level.getPlayer().currentWeapon !=0) {
                level.getPlayer().currentWeapon--;
            }else{
               level.getPlayer().currentWeapon =  level.getPlayer().weaponWheel.size()-1;
            }
        }else{
            if(level.getPlayer().currentWeapon !=level.getPlayer().weaponWheel.size()-1) {
                level.getPlayer().currentWeapon++;
            }else{
                level.getPlayer().currentWeapon = 0;
            }
        }
    }

    //DO ALL LOADING HERE, ALLOWS GAME TO BE REPLAYED WITHOUT RESTARTING THE PROGRAM
    public void load(){
        level = new Level(50,37);
        level.initLevel();
        weaponIcon = ImageUtil.loadImage("/res/weapon-gui.png").getScaledCopy(2f);
        UpgradesGUI.init(level);
    }


    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        UpgradesGUI.mouseClick(x,y,level);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        level.render(graphics);
        graphics.drawImage(weaponIcon,100,560);
        graphics.drawImage(level.getPlayer().weaponWheel.get(level.getPlayer().currentWeapon).getIcon(),100,560);

        graphics.drawString("AMMO: " + level.getPlayer().weaponWheel.get(level.getPlayer().currentWeapon).getAmmo(),150,560);

        graphics.drawString("Current round: " + level.getRound(),250,560);

        graphics.drawString("Kills: " + level.getPlayer().kills,450,560);

        //current/max * 100

        float height = (level.getPlayer().getHealth()/level.getPlayer().maxHealth) * JEngine.getHeight();
       graphics.fillRect(0,0,8,height);

     /*   if(paused){
            graphics.setColor(Color.black);
            graphics.drawString("GAME PAUSED",400,300);
        }

      */

        UpgradesGUI.render(graphics);


    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if(key == Keyboard.KEY_ESCAPE) paused = !paused;

        if(key == Keyboard.KEY_U){
            if(UpgradesGUI.isOpen()){
            UpgradesGUI.close();
                paused = false;
            }else{
                UpgradesGUI.open(level.getPlayer().weaponWheel.get(level.getPlayer().currentWeapon));
                paused = true;
            }

        }
    }

    public boolean paused = false;
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(paused)return;
         level.update(gameContainer);
        if(Mouse.isButtonDown(0)){
            Input input = gameContainer.getInput();
            level.mouseClick(input.getMouseX(),input.getMouseY(),0);
        }
    }

    @Override
    public int getID() {
        return 0;
    }

}
