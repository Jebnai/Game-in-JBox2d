package Platforms;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * @author jenai beyene
 * Class used to create a platform.
 */
public class NormalPlatform extends StaticBody
{
    /**
     * The platform.
     */
    private NormalPlatform platform;
    //starting pivot number to determine position.
    private int t = -6;
    /**
     * Image of platform.
     */
    private BodyImage normalPlat = new BodyImage("data/platform/normal.png", 4);
    private Vec2 playerPos = new Vec2(0, 0f);
    private AttachedImage playerWalker = new AttachedImage(this, normalPlat, 1f, 0f, playerPos);

    /**
     * Used to create a platform.
     * @param world Stores details of the world.
     */
    public NormalPlatform(World world)
    {
        super(world, new BoxShape(2.0f, 0.7f));
        this.setPosition(new Vec2(-5, -8.5f));
    }

    /**
     * Used to create platforms in certain positions.
     * @param world Stores details of the world.
     */
    public void drawPlat1(World world)
    {
        for(float i = -4.15f; i < 40.15; i+=4)
        {
            platform = new NormalPlatform(world);
            platform.setPosition(new Vec2(8f+t, i));
            t = t + 8;
            if(t >= 12)
            {
                t = -12;
            }
        }
    }

    /**
     * Used to create platforms to create a straight floor starting from left to right.
     * @param world Stores details of the world.
     * @param y The y position of the platform.
     */
    public void straightPlat(World world, float y)
    {
        for(float i = -30; i <= 10; i = i+5f){
            platform = new NormalPlatform(world);
            platform.setPosition(new Vec2(i, y));
        }


    }

    /**
     * Creates a platform at a specific position.
     * @param world Stores details of the world.
     * @param x x position of the platform.
     * @param y y position of the platform.
     */
    public void draw1Plat(World world, float x , float y)
    {
        platform = new NormalPlatform(world);
        platform.setPosition(new Vec2(x, y));
    }

    /**
     * Used to create platforms to create a straight floor starting from right to left.
     * @param world Stores details of the world.
     */
    public void reversePlat(World world)
    {
        for(float i = 10; i >= -30; i = i-5f)
        {
            platform = new NormalPlatform(world);
            platform.setPosition(new Vec2(i, 8.5f));
        }
    }
}

