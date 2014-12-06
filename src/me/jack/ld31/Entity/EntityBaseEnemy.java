package me.jack.ld31.Entity;

import me.jack.ld31.Level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import uk.co.jdpatrick.JEngine.Entity.*;

import java.awt.*;


/**
 * Created by Jack on 06/12/2014.
 */
public class EntityBaseEnemy extends Mob{


    public EntityBaseEnemy(float x, float y) {
        super(x, y, 10);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(Level level) {
        if(getHealth() <= 0)level.removeEntity(this);

        /*if(x > level.getPlayer().x){
            x--;
        }
        if(x < level.getPlayer().x){
            x++;
        }

        if(y > level.getPlayer().y){
            y--;
        }
        if(y < level.getPlayer().y){
            y++;
        }*/

        float xSpeed = (level.getPlayer().x - x);
        float ySpeed = (level.getPlayer().y - y);

        float factor = (float) (0.5/ Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        x+=xSpeed;
        y+=ySpeed;



        Rectangle me = new Rectangle((int)x,(int)y,Level.TILE_SIZE,Level.TILE_SIZE);
        for(Entity e : level.entities){
            if(!(e instanceof EntityBullet))continue;
            Rectangle eRect = new Rectangle((int)e.getX(),(int)e.getY(),8,8);
            if(eRect.intersects(me)){
                this.removeHealth(5f);
            }
        }
    }

    private Color color = Color.cyan;
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,Level.TILE_SIZE,Level.TILE_SIZE);
        g.setColor(Color.white);
    }
}
