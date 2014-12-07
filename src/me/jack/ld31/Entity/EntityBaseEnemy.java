package me.jack.ld31.Entity;

import me.jack.ld31.Item.BulletItem;
import me.jack.ld31.Level.Level;
import me.jack.ld31.Particle.ExplosionParticle;
import me.jack.ld31.Particle.SmallBloodParticle;
import me.jack.ld31.Powerups.HealthBoostPowerup;
import me.jack.ld31.Powerups.IncreaseMaxHealth;
import me.jack.ld31.Powerups.SpeedBoostPowerup;
import me.jack.ld31.Powerups.TimeBasedPowerup;
import me.jack.ld31.Projectile.SnowballProjectile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.Particle.Particle;

import java.awt.*;
import java.util.Random;


/**
 * Created by Jack on 06/12/2014.
 */
public class EntityBaseEnemy extends Mob {


    float moveSpeed = 0.5f;
    private static Image i;
    private boolean canShoot = false;

    public EntityBaseEnemy(float x, float y, boolean canShoot, int currentRound) {
        super(x, y, 10);
        moveSpeed += new Random().nextInt(2);
        this.canShoot = canShoot;

        if (currentRound >= 10) addHealth(10);
    }

    @Override
    public void init() {
        if (i == null) {
            i = ImageUtil.loadImage("res/snowman.png");
            i.setCenterOfRotation(17, 5);
        }
    }

    @Override
    public void update(Level level) {

        if (getHealth() <= 0) {
            level.getPlayer().kills++;
            level.removeEntity(this);
            if (Math.random() > 0.3 ){
                level.entities.add(new EntityItem(x, y, new BulletItem()));
            }

            if(Math.random() > 0.8)
                level.entities.add(new EntityPowerup(x, y, new IncreaseMaxHealth()));

            if(Math.random() > 0.4){
                level.entities.add(new EntityPowerup(x, y, new SpeedBoostPowerup(Level.powerups.getSprite(1,0),5000) {
                }));
            }
            if(Math.random() > 0.4){
                level.entities.add(new EntityPowerup(x, y, new HealthBoostPowerup(Level.powerups.getSprite(0,0),5000) {
                }));
            }
            for (int i = 0; i != 100; i++)
                level.particleSystem.addParticle(new SmallBloodParticle((int) x, (int) y));


        }
        if (new Random(System.nanoTime()).nextInt(1000) == 0 && canShoot) {
            EntityProjectile snowBall = new EntityProjectile(x, y, level.getPlayer().getX(), level.getPlayer().getY(), new SnowballProjectile());
            level.addEntity(snowBall);
        }

        float xSpeed = (level.getPlayer().x - x);
        float ySpeed = (level.getPlayer().y - y);
        Random r = new Random();
        float factor = (float) (moveSpeed / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;


        Rectangle me = new Rectangle((int) x, (int) y, 32, 32);
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


        Rectangle eRectangle = new Rectangle((int) getX(), (int) getY(), 16, 16);
        Rectangle pRectangle = new Rectangle((int) level.getPlayer().getX(), (int) level.getPlayer().getY(), 16, 16);
        if (eRectangle.intersects(pRectangle)) {
            this.onPlayerIntersect(level);
            xSpeed= 0;
            ySpeed = 0;
        }


        x += xSpeed;
        y += ySpeed;
        angle = (float) -(Math.atan2(this.x - level.getPlayer().getX(), this.y - level.getPlayer().getY()) * 180 / Math.PI);
    }

    float angle;

    public float rand() {
        float rnd = (float) (Math.random() * (0.15 - 0.1) + 0.1);
        System.out.println(rnd);
        return rnd;
    }

    private Color color = Color.cyan;

    @Override
    public void render(Graphics g) {
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
}
