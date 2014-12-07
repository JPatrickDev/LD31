package me.jack.ld31.States;

import me.jack.ld31.GUI.UpgradesGUI;
import me.jack.ld31.Level.Level;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.JEngine;

/**
 * Created by Jack on 06/12/2014.
 */
public class InGameState extends BasicGameState {


    public static Level level;
    public static boolean gameOver= false;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setShowFPS(false);
        load();
        controlTutorial = ImageUtil.loadImage("res/control-tutorial.png");
        gameOverImage = ImageUtil.loadImage("res/gameover.png");
    }

    int oldValue = -1;
    private Image weaponIcon;

    private Image controlTutorial = null;
    private boolean showingTutorial = false;

    private Image gameOverImage = null;


    @Override
    public void mouseWheelMoved(int newValue) {
        super.mouseWheelMoved(newValue);
        if (oldValue == -1) {
            oldValue = newValue;
            return;
        }

        if (oldValue > newValue) {
            if (level.getPlayer().currentWeapon != 0) {
                level.getPlayer().currentWeapon--;
            } else {
                level.getPlayer().currentWeapon = level.getPlayer().weaponWheel.size() - 1;
            }
        } else {
            if (level.getPlayer().currentWeapon != level.getPlayer().weaponWheel.size() - 1) {
                level.getPlayer().currentWeapon++;
            } else {
                level.getPlayer().currentWeapon = 0;
            }
        }
    }

    //DO ALL LOADING HERE, ALLOWS GAME TO BE REPLAYED WITHOUT RESTARTING THE PROGRAM
    public void load() {
        level = new Level(50, 37);
        level.initLevel();
        weaponIcon = ImageUtil.loadImage("res/weapon-gui.png").getScaledCopy(2f);
        UpgradesGUI.init(level);
        showingTutorial = true;

    }


    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        UpgradesGUI.mouseClick(x, y, level);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        level.render(graphics);

        graphics.drawImage(weaponIcon, 100, 560);
        graphics.drawImage(level.getPlayer().weaponWheel.get(level.getPlayer().currentWeapon).getIcon(), 100, 560);
        graphics.setColor(Color.red);
        graphics.drawString("AMMO: " + level.getPlayer().weaponWheel.get(level.getPlayer().currentWeapon).getAmmo(), 150, 560);

        graphics.drawString("Current round: " + level.getRound(), 250, 560);

        graphics.drawString("Kills: " + level.getPlayer().kills, 450, 560);
        graphics.setColor(Color.white);
        //current/max * 100

        float height = (level.getPlayer().getHealth() / level.getPlayer().maxHealth) * JEngine.getHeight();
        graphics.setColor(Color.red);
        graphics.fillRect(0, 0, 8, height);
        graphics.setColor(Color.white);

     /*   if(paused){
            graphics.setColor(Color.black);
            graphics.drawString("GAME PAUSED",400,300);
        }

      */

        UpgradesGUI.render(graphics);


        if(showingTutorial) graphics.drawImage(controlTutorial,0,0);

        if(gameOver){
            graphics.setColor(new Color(255,0,0,40));
            graphics.fillRect(0,0,800,600);

            graphics.drawImage(gameOverImage,0,0);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);

        if(showingTutorial){
            showingTutorial = false;
            paused = false;
            return;
        }
        if (key == Keyboard.KEY_ESCAPE) paused = !paused;

        if (key == Keyboard.KEY_U) {
            if (UpgradesGUI.isOpen()) {
                UpgradesGUI.close();
                paused = false;
            } else {
                UpgradesGUI.open(level.getPlayer().weaponWheel.get(level.getPlayer().currentWeapon));
                paused = true;
            }
        }
        if(gameOver){
            resetGame();
        }
    }

    private void resetGame() {
        gameOver = false;
        load();
        paused = false;
    }

    public static boolean paused = false;

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        System.out.println(level.timeTillNextRound);
        if (paused || showingTutorial) return;
        level.update(gameContainer,i);
        if (Mouse.isButtonDown(0)) {
            Input input = gameContainer.getInput();
            level.mouseClick(input.getMouseX(), input.getMouseY(), 0);
        }
    }

    @Override
    public int getID() {
        return 0;
    }

}
