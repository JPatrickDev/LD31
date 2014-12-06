package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import uk.co.jdpatrick.JEngine.Entity.*;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

/**
 * Created by Jack on 06/12/2014.
 */
public class EntityBullet extends Entity{


    private float tX;
    private float tY;


    private float vX;
    private float vY;
    public EntityBullet(float x, float y, float targetX, float targetY) {
        super(x, y);
        float xSpeed = (targetX - x);
        float ySpeed = (targetY - y);

        float factor = (float) (5 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        vX = xSpeed;
        vY = ySpeed;
    }


    @Override
    public void init() {

    }

    @Override
    public void update(Level level) {

        x+=vX;
        y+=vY;

        if(x<0|| x > 800 || y < 0 || y > 600){
            level.removeEntity(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x,y,4,4);
        g.setColor(Color.white);
    }
}
