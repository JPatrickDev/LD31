package me.jack.ld31.Level;

import me.jack.ld31.Entity.Entity;
import me.jack.ld31.Entity.EntityBaseEnemy;
import me.jack.ld31.Entity.EntityBullet;
import me.jack.ld31.Entity.EntityPlayer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jack on 06/12/2014.
 */
public class Level {

    private int[][] tiles;
    private int width;
    private int height;


    public static final int TILE_SIZE = 8;


    public ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();


    private EntityPlayer player;

    public Rectangle gameWorld;

    public CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>();


    private ArrayList<MobSpawner> spawners = new ArrayList<MobSpawner>();

    public Level(int width, int height){
        this.width = width;
        this.height = height;

    }

    public void initLevel(){
        this.tiles = new int[width][height];
        populateWalls();
        player = new EntityPlayer(5*TILE_SIZE,5*TILE_SIZE);
        gameWorld = new Rectangle(1*TILE_SIZE,1*TILE_SIZE,(width * TILE_SIZE)-2*TILE_SIZE,(height * TILE_SIZE)-2 *TILE_SIZE);

        entities.clear();
        for(int i = 0;i!= 10;i++){
                entities.add(new EntityBaseEnemy(50,50));
        }
        spawners.clear();
        spawners.add(new MobSpawner(2,2));
        spawners.add(new MobSpawner(width - 4,2));


        spawners.add(new MobSpawner(2,height -4));
        spawners.add(new MobSpawner(width - 4,height-4));
    }

    private void populateWalls() {
        for(int x = 0;x!=width;x++){
            tiles[x][0]=1;
            tiles[x][height-1]=1;
        }

        hitboxes.add(new Rectangle(0,0,width*TILE_SIZE,TILE_SIZE));
        hitboxes.add(new Rectangle(0,height* TILE_SIZE - TILE_SIZE,width*TILE_SIZE,TILE_SIZE));

        for(int y= 0;y!=height;y++){
            tiles[0][y]=1;
            tiles[width-1][y]=1;
        }

        hitboxes.add(new Rectangle(0,0,TILE_SIZE,height*TILE_SIZE));
        hitboxes.add(new Rectangle(width * TILE_SIZE - TILE_SIZE,0,TILE_SIZE,height*TILE_SIZE));
    }

    public boolean solid(int x,int y){
        return false;//TODO implement
    }

    public void update(){
        for(Entity e : entities)e.update(this);
        player.update(this);

        for(MobSpawner spawner: spawners)
            spawner.update(this);
    }

    public void render(Graphics g){
        for(int x =0;x!=width;x++){
            for(int y= 0;y!=height;y++){
                if(tiles[x][y] == 0)
                g.setColor(Color.white);
                else
                g.setColor(Color.darkGray);
                g.fillRect(x*TILE_SIZE,y*TILE_SIZE,TILE_SIZE,TILE_SIZE);
                if(tiles[x][y] == 1) {
                    g.setColor(Color.black);
                    g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
                g.setColor(Color.white);
            }
        }

        for(Entity e : entities)e.render(g);

        player.render(g);

        for(MobSpawner spawner: spawners)
            spawner.render(g);
    }

    public int getWidth() {
        return width;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void mouseClick(int x,int y,int button){
        player.mousePressed(x,y,button,this);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public EntityPlayer getPlayer() {
        return player;
    }
}
