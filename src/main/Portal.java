package main;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * @author jenai beyene
 * Class used to create a portal.
 */
public class Portal extends StaticBody
{
    //initialize a body image for projectile 'fireball'
    /**
     * Image for the portal.
     */
    private BodyImage portal = new BodyImage("data/endingpoints/phantomportal.gif", 5f);

    //creating an instance of projectile image
    /**
     * An instance of the image.
     */
    private AttachedImage thePortal = new AttachedImage(this, portal, 1f, 0f, new Vec2(0f, 1f));


    /**
     * Constructor to create a portal.
     * @param world Stores details of the world.
     */
    public Portal(World world)
    {
        super(world, new CircleShape(1.8f, 0f, 0.8f));

    }
}

