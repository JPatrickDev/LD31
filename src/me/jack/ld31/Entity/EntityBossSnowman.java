package me.jack.ld31.Entity;

import me.jack.ld31.Item.BulletItem;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Particle.ExplosionParticle;
import me.jack.ld31.Particle.SmallBloodParticle;
import me.jack.ld31.Powerups.HealthBoostPowerup;
import me.jack.ld31.Powerups.IncreaseMaxHealth;
import me.jack.ld31.Powerups.SpeedBoostPowerup;
import me.jack.ld31.Projectile.SnowballProjectile;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.Particle.BloodParticle;
import uk.co.jdpatrick.JEngine.Particle.Particle;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Created by Jack on 07/12/2014.
 */
public class EntityBossSnowman extends Mob {


    float moveSpeed = 10f;
    private static Image i;
    private boolean canShoot = false;
    float xSpeed =0;
    float ySpeed = 0;

    Rectangle reach = null;
    public EntityBossSnowman(float x, float y, boolean canShoot, int currentRound) {
        super(x, y, 10);
        moveSpeed += new Random().nextInt(2);
        this.canShoot = canShoot;
        this.addHealth(1000f);

        if (currentRound >= 10) addHealth(10);
    }

    @Override
    public void init() {
        if (i == null) {
            i = ImageUtil.loadImage("res/bosssnowman.png");
            i.setCenterOfRotation(128, 128);
        }
    }

    @Override
    public void update(Level level) {

        if (getHealth() <= 0) {
            level.getPlayer().kills++;
            level.getPlayer().money += 10;
            level.removeEntity(this);
            if (Math.random() > 0.2 ){
                for(int i = 0;i!= 20;i++)
                level.entities.add(new EntityItem(x, y, new BulletItem()));
            }


            for (int i = 0; i != 100; i++)
                level.particleSystem.addParticle(new BloodParticle((int) x, (int) y));


        }
        if (new Random(System.nanoTime()).nextInt(50) == 0 && canShoot) {
            EntityProjectile snowBall = new EntityProjectile(x, y, level.getPlayer().getX(), level.getPlayer().getY(), new SnowballProjectile());
            level.addEntity(snowBall);
        }


        Rectangle me = new Rectangle((int) x, (int) y, 256, 256);

        for (Entity e : level.entities) {
            if (!(e instanceof EntityProjectile)) continue;
            EntityProjectile eP = (EntityProjectile) e;
            if (eP.getProjectile() instanceof SnowballProjectile) continue;
            Rectangle eRect = new Rectangle((int) e.getX(), (int) e.getY(), 8, 8);
            if (eRect.intersects(me)) {
                this.removeHealth(((EntityProjectile) e).getProjectile().getDamage());
                ((EntityProjectile) e).notifyHitEntity(this, level);
            }
        }

        org.newdawn.slick.geom.Rectangle rectangle = new org.newdawn.slick.geom.Rectangle(x, y, 32, 32);

        for (Particle p : level.particleSystem.getParticles()) {
            if (!(p instanceof ExplosionParticle)) continue;

            if (rectangle.intersects(p.getParticle())) {
                p.remove = true;
                removeHealth(2);
            }

        }


        if(new Random().nextInt(250) == 0){
            charge(level);
        }
        Rectangle eRectangle = new Rectangle((int) getX(), (int) getY(), 256, 256);
        Rectangle pRectangle = new Rectangle((int) level.getPlayer().getX(), (int) level.getPlayer().getY(), 32, 32);
        if (eRectangle.intersects(pRectangle)) {
            this.onPlayerIntersect(level);
            xSpeed= 0;
            ySpeed = 0;
        }


        x += xSpeed;
        y += ySpeed;
        angle = (float) -(Math.atan2(this.x + 128 - level.getPlayer().getX(), this.y+128 - level.getPlayer().getY()) * 180 / Math.PI);

        if(new Random().nextInt(25) == 0){
            BabySnowman bs = new BabySnowman(x,y,true,1);
            bs.init();
            level.addEntity(bs);
        }

        if(reach != null){
            if(eRectangle.intersects(reach)){
                xSpeed =0;
                ySpeed = 0;
            }
        }
        for(Rectangle hitbox : level.hitboxes){
            if(hitbox.intersects(eRectangle)){
                xSpeed = 0;
                ySpeed = 0;
            }
        }

    }

    float angle;

    public float rand() {
        float rnd = (float) (Math.random() * (0.15 - 0.1) + 0.1);
        System.out.println(rnd);
        return rnd;
    }

    private org.newdawn.slick.Color color = org.newdawn.slick.Color.cyan;

    @Override
    public void render(org.newdawn.slick.Graphics g) {
        i.setRotation(angle);
        g.drawImage(i, x, y);
        i.setRotation(0f);
    }

    long attackDelay = 1000;
    long lastAttack = -1;

    @Override
    public void onPlayerIntersect(Level level) {


        if (lastAttack == -1) {
            lastAttack = System.currentTimeMillis();
            level.getPlayer().removeHealth(5);
            return;
        }


        if (System.currentTimeMillis() - lastAttack >= attackDelay) {
            level.getPlayer().removeHealth(5);
            lastAttack = System.currentTimeMillis();
        }

    }


    private void charge(Level level){
         xSpeed = (level.getPlayer().x - x);
         ySpeed = (level.getPlayer().y - y);
        Random r = new Random();
        float factor = (float) (moveSpeed / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;

        reach = new Rectangle((int)level.getPlayer().getX(),(int)level.getPlayer().getY(),32,32);


    }
}
