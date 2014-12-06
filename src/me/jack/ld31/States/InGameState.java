package me.jack.ld31.States;

import me.jack.ld31.Level.Level;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.JEngineGameState;

/**
 * Created by Jack on 06/12/2014.
 */
public class InGameState extends BasicGameState{



    private Level level;
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
    }


    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        level.render(graphics);
        graphics.drawImage(weaponIcon,100,100);
        graphics.drawImage(level.getPlayer().weaponWheel.get(level.getPlayer().currentWeapon).getIcon(),100,100);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
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
