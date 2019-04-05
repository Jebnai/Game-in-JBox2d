package main;
import Collisions.MinionCollisions;
import Collisions.ProjectileCollisions;
import Levels.LevelLoader;
import StepEvents.DeathAnim;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * @author jenai beyene
 * Class to create the enemy minion.
 */
public class Minion extends Walker
{
    //declaring minion health
    /**
     * Health of minion
     */
    private int health;
    //initializing body image for minion
    /**
     * Image for the minion.
     */
    private BodyImage minionWalk = new BodyImage("data/Skeleton/GIFS/Skeleton Idle.gif", 2.5f);
    // position of the minion
    /**
     * Position of the minion.
     */
    private Vec2 minionPos = new Vec2(0, 0f);

    //creating instance of minion image
    private AttachedImage minionWalker = new AttachedImage(this, minionWalk, 1f, 0f, minionPos);

    //variable that holds speed of minion and use it anywhere else in class
    /**
     * Current speed of the minion
     */
    private float checkSpeed;
    public float walkerX;


    /**
     * Constructor to create a minion.
     * @param world Stores details of the world.
     * @param health Health of the minion.
     */
    public Minion(LevelLoader world, int health)
    {
        //creating hit box for minion
        super(world, new BoxShape(1.05f, 1.26f));

        //initializing health for minion
        this.health = health;

        //setting the gravity for minion
        this.setGravityScale(2);

        //adding a collision listener to the minion
        this.addCollisionListener(new MinionCollisions(this));

        //adding a step listener to the minion to allow a death animation to occur.
        world.addStepListener(new DeathAnim(this));






    }

    /**
     * Used to return the minion.
     * @return Returns the minion
     */
    public Minion getMinion()
    {
        return this;
    }

    /**
     * Used to get the current health of the minion
     * @return Returns the current health of the minion.
     */
    public int getHealth()
    {
        return health;
    }

    //setter for minion health

    /**
     * Used to set the health of the minion.
     * @param health Health of the minion.
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    //death animation when minion dies

    /**
     * Used to display the death animation of the minion.
     */
    public void deathImg()
    {
        this.removeAllImages();
        AttachedImage minionRun = new AttachedImage(this, new BodyImage("data/Skeleton/GIFS/Skeleton Dead.gif", 2.5f), 1.0f, 0.0f, new Vec2(0, 0));
        if (checkSpeed < 0)
        {
            minionRun.flipHorizontal();
        }


    }

    /**
     * Used to display the attacking animation of the minion.
     */
    public void attackImg()
    {
        this.removeAllImages();
        AttachedImage minionRun = new AttachedImage(this, new BodyImage("data/Skeleton/GIFS/Skeleton Attack.gif", 2.5f), 1.0f, 0.0f, new Vec2(0, 0));
        if(checkSpeed < 0)
        {
            minionRun.flipHorizontal();
        }
    }

    /**
     * Used to make the minion walk and flips it when going in the opposite direction.
     * @param speed The speed at which the minion walks.
     */
    @Override
    public void startWalking(float speed)
    {
        checkSpeed = speed;
        super.startWalking(speed);
        this.removeAllImages();
        AttachedImage minionRun = new AttachedImage(this, new BodyImage("data/Skeleton/GIFS/Skeleton Walk.gif", 2.5f), 1.0f, 0.0f, new Vec2(0, 0));

        if (speed < 0)
        {
            minionRun.flipHorizontal();
        }

    }

    @Override
    public void stopWalking()
    {
        super.stopWalking();
        this.setLinearVelocity(new Vec2(0,this.getPosition().y));
        this.removeAllImages();
        AttachedImage minionRun = new AttachedImage(this, new BodyImage("data/Skeleton/GIFS/Skeleton Idle.gif", 2.5f), 1.0f, 0.0f, new Vec2(0, 0));
        if (checkSpeed < 0)
        {
            minionRun.flipHorizontal();
        }

    }

    //returns the minions horizontal position

    /**
     * Used to return the x position of the minion.
     * @return
     */
    public float getMinionX()
    {
        return this.getPosition().x;
    }
}

