package me.jack.ld31.Level;

import me.jack.ld31.Entity.Entity;
import me.jack.ld31.Entity.EntityBaseEnemy;
import me.jack.ld31.Entity.EntityPlayer;
import me.jack.ld31.Entity.Mob;
import me.jack.ld31.Projectile.BulletProjectile;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.Particle.ParticleSystem;

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


    public static final int TILE_SIZE = 16;


    public ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();


    private EntityPlayer player;

    public Rectangle gameWorld;

    public CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>();


    private ArrayList<MobSpawner> spawners = new ArrayList<MobSpawner>();

    private int round = 1;

   public ParticleSystem particleSystem =new ParticleSystem();

    public static SpriteSheet sprites;
    public static SpriteSheet powerups;
    public static SpriteSheet tileImages;
    public static SpriteSheet weapons;


    public Level(int width, int height){
        this.width = width;
        this.height = height;

    }

    public void initLevel(){
        this.tiles = new int[width][height];
        populateWalls();
        player = new EntityPlayer(5*TILE_SIZE,5*TILE_SIZE);

        sprites = new SpriteSheet(ImageUtil.loadImage("/res/sprites.png"),8,8);
        powerups = new SpriteSheet(ImageUtil.loadImage("/res/powerups.png"),16,16);
        tileImages = new SpriteSheet(ImageUtil.loadImage("/res/tiles.png"),16,16);
        weapons = new SpriteSheet(ImageUtil.loadImage("/res/weapons.png"),16,16);
        gameWorld = new Rectangle(1*TILE_SIZE,1*TILE_SIZE,(width * TILE_SIZE)-2*TILE_SIZE,(height * TILE_SIZE)-2 *TILE_SIZE);
        player.init();
        entities.clear();

        spawners.clear();
        spawners.add(new MobSpawner(2,2));
        spawners.add(new MobSpawner(width - 4,2));


        spawners.add(new MobSpawner(2,height -4));
        spawners.add(new MobSpawner(width - 4,height-4));
        round = 1;

        for(MobSpawner spawner : spawners)spawner.spawnWave(this);

        BulletProjectile.i = Level.sprites.getSprite(0,0);



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

    boolean roundInProgress = false;
    public void update(GameContainer gc){

        if(entities.size() > 0)roundInProgress = true;
        for(Entity e : entities)e.update(this);
        player.update(this);

        for(MobSpawner spawner: spawners)
            spawner.update(this);


        player.angle = (float) -(Math.atan2(player.getX() - gc.getInput().getMouseX(), player.getY() - gc.getInput().getMouseY()) * 180 / Math.PI);

        particleSystem.update();

    }



    public void render(Graphics g){
        for(int x =0;x!=width;x++){
            for(int y= 0;y!=height;y++){
                if(tiles[x][y] == 0)
                    g.drawImage(tileImages.getSubImage(1,0),x*TILE_SIZE,y*TILE_SIZE);
                else
                g.drawImage(tileImages.getSubImage(0,0),x*TILE_SIZE,y*TILE_SIZE);

            }
        }

        for(Entity e : entities)e.render(g);

        player.render(g);

        for(MobSpawner spawner: spawners)
            spawner.render(g);

        particleSystem.render(g,0,0);
        g.setColor(Color.red);
     //   g.drawString("Ammo:" + player.ammo,100,50);
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
        if(entity instanceof Mob){
            boolean found = false;
            for(Entity e : entities){
                if(!(e instanceof Mob))continue;
                found = true;
            }
            if(!found){
                round+=1;
                for(MobSpawner spawner: spawners){
                    spawner.spawnWave(this);
                }
            }
        }
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public int getRound() {
        return round;
    }
}
