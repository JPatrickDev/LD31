package me.jack.ld31.Item;

import me.jack.ld31.Level.Level;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

/**
 * Created by Jack on 06/12/2014.
 */
public class BulletItem extends Item{

    public BulletItem() {
        super("Bullet", ImageUtil.loadImage("/res/bullet.png"));
    }

}
