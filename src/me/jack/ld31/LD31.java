package me.jack.ld31;

import me.jack.ld31.States.InGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 06/12/2014.
 */
public class LD31 extends StateBasedGame{


    public LD31(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new InGameState());
    }
}
