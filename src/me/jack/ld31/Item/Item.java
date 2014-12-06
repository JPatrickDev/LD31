package me.jack.ld31.Item;

import org.newdawn.slick.Image;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

/**
 * Created by Jack on 06/12/2014.
 */
public class Item {

    private Image sprite;
    private String name;
    public Item(String name,String res){
       this(name,ImageUtil.loadImage(res));
    }
    public Item(String name,Image res){
        this.name = name;
        this.sprite =res;
    }

    public Image getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }
}
