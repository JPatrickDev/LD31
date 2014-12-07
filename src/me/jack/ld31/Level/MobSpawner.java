package me.jack.ld31.Level;

import me.jack.ld31.Entity.Entity;
import me.jack.ld31.Entity.EntityBaseEnemy;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jack on 06/12/2014.
 */
public class MobSpawner {

    private int x;
    private int y;





    private ArrayList<Entity> mobQueue = new ArrayList<Entity>();

    public MobSpawner(int x,int y){
        this.x = x;
        this.y = y;
    }


    private long lastSpawn = System.currentTimeMillis();
    private long spawnDelay = 500;
    public void update(Level level){

        if(mobQueue.size() == 0)return;
        if(System.currentTimeMillis() - lastSpawn >= spawnDelay){
            Entity i = mobQueue.get(0);
            i.init();
            level.addEntity(i);
            mobQueue.remove(0);
            lastSpawn = System.currentTimeMillis();
        }
    }

    public void spawnWave(Level level){

        System.out.println("Spawning wave");

        Random r = new Random();
        int minX = x* Level.TILE_SIZE - Level.TILE_SIZE;
        int minY = y* Level.TILE_SIZE - Level.TILE_SIZE;

        int maxX = x* Level.TILE_SIZE +  Level.TILE_SIZE;
        int maxY = y* Level.TILE_SIZE +  Level.TILE_SIZE;

        for(int i = 0;i!= level.getRound() * 3;i++){
            int spawnX = minX + r.nextInt(maxX - minX + 1);
            int spawnY = minY + r.nextInt(maxY - minY + 1);
            mobQueue.add(new EntityBaseEnemy(spawnX, spawnY, level.getRound() >= 5,level.getRound()));
        }
    }

    public void render(Graphics g){
       // g.setColor(Color.red);
       // g.fillRect(x*Level.TILE_SIZE,y*Level.TILE_SIZE,Level.TILE_SIZE*2,Level.TILE_SIZE*2);
       // g.setColor(Color.white);
    }

}
