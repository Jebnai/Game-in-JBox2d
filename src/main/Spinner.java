package main;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * @author jenai beyene
 * Class to create a enemy spinner.
 */
public class Spinner extends Walker
{
    /**
     * Health of the spinner.
     */
    private int health;
    /**
     * Image of the spinner.
     */
    private BodyImage spinWalk = new BodyImage("data/Obstacles/spinning.gif", 5);
    /**
     * Position of the spinner.
     */
    private Vec2 spinnerPos = new Vec2(0, 0f);
    /**
     * Instance of the spinner image.
     */
    private AttachedImage theSpinner = new AttachedImage(this, spinWalk, 1f, 0f, spinnerPos);

    /**
     * Constructor to create an enemy spinner.
     * @param world Stores the details of the world.
     * @param health Health of the spinner.
     */
    public Spinner(World world, int health)
    {
        super(world, new CircleShape(1.5f));
        this.health = health;
        this.setPosition(new Vec2(-30, 10.5f));
        this.setGravityScale(100f);
    }

    //getter for walker health

    /**
     * Used to return the health of the spinner.
     * @return Returns the health of the spinner.
     */
    public int getHealth()
    {
        return health;
    }

    //setter for walker health

    /**
     * Used to set the health of the spinner.
     * @param health Health of the spinner.
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    @Override
    public void startWalking(float speed)
    {
        super.startWalking(speed);
    }
}

