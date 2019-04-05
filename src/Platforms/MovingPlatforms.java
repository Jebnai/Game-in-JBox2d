package Platforms;
import StepEvents.MovingPlat;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.lang.Math;
import java.util.ArrayList;

/**
 * @author jenai beyene
 * Class used to create platforms that are randomly generated.
 */
public class MovingPlatforms extends StaticBody
{
    /**
     * The randomly generated platform.
     */
    private MovingPlatforms mp;
    /**
     * Position of the highest platform.
     */
    private Vec2 lastPlatform;
    private Boolean finished = false;
    /**
     * Image of the platform.
     */
    private BodyImage normalPlat = new BodyImage("data/platform/normal.png", 3);
    /**
     * An array list of platforms generated.
     */
    private ArrayList<MovingPlatforms> platformList = new ArrayList<>();
    private Vec2 playerPos = new Vec2(0, 0f);
    private int j;
    private AttachedImage playerWalker = new AttachedImage(this, normalPlat, 1f, 0f, playerPos);

    /**
     * Constructor used to create a platform.
     * @param world Stores details of the world.
     */
    public MovingPlatforms(World world)
    {
        super(world, new BoxShape(1.8f, 0.7f));
        this.setPosition(new Vec2(100, 100));
    }

    /**
     * Used to set a platform to a randomly generated position.
     * @param world Stores details of the world.
     */
    public void movingPlat(World world)
    {
        j = -1;
        for(float i = -5f; i < 40; i+=5)
        {
            j = j + 1;
            mp = new MovingPlatforms(world);
            mp.setPosition(new Vec2(randomWittRange(-24, 12), i));
            platformList.add(mp);
            if(j > 0)
            {
                if(((platformList.get(j).getPosition().x - platformList.get(j-1).getPosition().x) < 7) && (platformList.get(j).getPosition().x - platformList.get(j-1).getPosition().x) > -7)
                {
                    platformList.get(j).setPosition(new Vec2(platformList.get(j).getPosition().x + 5, platformList.get(j).getPosition().y));
                }
                if(((platformList.get(j).getPosition().x - platformList.get(j-1).getPosition().x) > 6))
                {
                    platformList.get(j).setPosition(new Vec2(platformList.get(j).getPosition().x - 15, platformList.get(j).getPosition().y));
                }

            }
            if(i == 35)
            {
                lastPlatform = mp.getPosition();
                System.out.println(lastPlatform);
            }

        }
        System.out.println(platformList.size());

    }

    /**
     * Used to generate a random number between the two parameters.
     * @param min Minimum number.
     * @param max Maximum number.
     * @return Returns the random number.
     */
    public int randomWittRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    /**
     * Used to get the position of the highest platform that is randomly generated.
     * @return Returns the position of the highest platform.
     */
    public Vec2 getLastPlatform()
    {
        return lastPlatform;
    }
    public Boolean returnFinished()
    {
        return finished;
    }
}

