package me.jack.ld31.States;

import me.jack.ld31.Level.Level;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.JEngineGameState;

/**
 * Created by Jack on 06/12/2014.
 */
public class InGameState extends BasicGameState{


    //tilesize = 8px?

    private Level level;
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        load();
    }

    //DO ALL LOADING HERE, ALLOWS GAME TO BE REPLAYED WITHOUT RESTARTING THE PROGRAM
    public void load(){
        level = new Level(100,75);
        level.initLevel();
    }


    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        level.render(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
         level.update();
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
