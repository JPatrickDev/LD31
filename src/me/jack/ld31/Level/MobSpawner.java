package me.jack.ld31.Level;

import me.jack.ld31.Entity.EntityBaseEnemy;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 06/12/2014.
 */
public class MobSpawner {

    private int x;
    private int y;


    private long spawnDelay = 500;
    private int mobsToSpawn;



    public MobSpawner(int x,int y){
        this.x = x;
        this.y = y;
    }


    private long lastSpawn = 0;
    public void update(Level level){
        if(lastSpawn == 0){
            lastSpawn = System.currentTimeMillis();
            return;
        }
        if(System.currentTimeMillis() - lastSpawn >= spawnDelay){
            level.addEntity(new EntityBaseEnemy(x * Level.TILE_SIZE,y*Level.TILE_SIZE));
            lastSpawn = System.currentTimeMillis();
        }


    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x*Level.TILE_SIZE,y*Level.TILE_SIZE,Level.TILE_SIZE*2,Level.TILE_SIZE*2);
        g.setColor(Color.white);
    }
}
