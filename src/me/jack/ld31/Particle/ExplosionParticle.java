package me.jack.ld31.Particle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.w3c.dom.css.Rect;
import uk.co.jdpatrick.JEngine.Particle.Particle;
import uk.co.jdpatrick.JEngine.Particle.ParticleSystem;

/**
 * Created by Jack on 06/12/2014.
 */
public class ExplosionParticle extends Particle {


    /**
     * Create a new BloodParticle
     *  @param x Starting X coordinate
     * @param y Starting Y coordinate
     * @param splashRadius
     */
    public ExplosionParticle(int x, int y, int splashRadius) {
        super(x, y);
        xx = x;
        yy = y;

        xa = random.nextGaussian() * splashRadius;
        ya = random.nextGaussian() * splashRadius;

        particle = new Rectangle((float) xx, (float) yy, 3, 3);
    }

    /**
     * Update the particle
     *
     * @param system The {@code ParticleSystem} the Particle belongs to
     */
    @Override
    public void update(ParticleSystem system) {
        time++;
        if (time > 50 && random.nextBoolean() && random.nextBoolean()) {
            system.removeParticle(this);
            xa /= 20;
            ya /= 20;
        }
        if(remove)
            system.removeParticle(this);
        xx += xa;
        yy += ya;

        x = (int) xx;
        y = (int) yy;

        if (xa > 0) {
            xa -= 0.05;
        } else {
            xa += 0.05;
        }

        if (ya > 0) {
            ya -= 0.1;
        } else {
            ya += 0.1;
        }

        particle.setX(x);
        particle.setY(y);


    }

    /**
     * The start colour of the gradient
     */
    Color start = Color.decode("#000000");
    /**
     * The end colour of the gradient
     */
    Color end = Color.decode("#001109");

    /**
     * Draw the particle
     *
     * @param g       Graphics object to use for rendering
     * @param offsetX The offset along the X axis
     * @param offsetY The offset along the Y axis
     */
    @Override
    public void render(Graphics g, int offsetX, int offsetY) {
        particle.setX(x - offsetX);
        particle.setY(y - offsetY);
        GradientFill gradient = new GradientFill(particle.getX(), particle.getY(), start, particle.getX() + particle.getWidth(), particle.getY() + particle.getHeight(), end);
        g.fill(particle, gradient);
        particle.setX(x);
        particle.setY(y);
    }

}