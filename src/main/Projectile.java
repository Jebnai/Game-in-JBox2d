package main;
import Collisions.ProjectileCollisions;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;


/**
 * @author jenai beyene
 * Class to create a fire ball.
 */
public class Projectile extends DynamicBody
{
    //initialize a body image for projectile 'fireball'
    /**
     * Image of the fireball
     */
    private BodyImage fireBall = new BodyImage("data/fireball.gif", 3.2f);

    //creating an instance of projectile image
    /**
     * Instance of the image.
     */
    private AttachedImage theFire = new AttachedImage(this, fireBall, 1f, 0f, new Vec2(0, 0.6f));


    /**
     * Constructor to create a fire ball.
     * @param world Stores the details of the world.
     */
    public Projectile(World world)
    {
        //creating hit box for projectile
        super(world, new CircleShape(0.5f, 0f, 0.8f));

        //setting the gravity of projectile to zero
        this.setGravityScale(0);


        //setting angular velocity to zero
        this.setAngularVelocity(0);

        //adding a collision listener so it can interact with certain collisions
        this.addCollisionListener(new ProjectileCollisions(this));


    }

    //flipping the image horizontally
    /**
     * Used to flip the fire ball image when moving in the opposite direction.
     */
    public void fireFlip()
    {
        theFire.flipHorizontal();
    }










}

