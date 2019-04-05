package main;

import Collisions.WalkerCollisions;
import Levels.LevelLoader;
import StepEvents.AttackAnim;
import StepEvents.DeathAnim;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * @author jenai beyene
 * Class to create the main character.
 */

public class TheWalker extends Walker
{
    //initialize player health
    /**
     * Health of the main character.
     */
    private int health;

    //creating image for walker
    /**
     * Image for the main character.
     */
    private BodyImage playerWalk = new BodyImage("data/Jungle Asset Pack/Character with outline/sprites/idle outline.gif", 3);

    //initialize player position
    /**
     * Position of the main character.
     */
    private Vec2 playerPos = new Vec2(0, 0f);

    //new instance of the player image.
    /**
     * New instance of main character image.
     */
    private AttachedImage playerWalker = new AttachedImage(this, playerWalk, 1f, 0f, playerPos);

    //this holds current speed of walker
    /**
     * Holds the current speed of the main character.
     */
    private float checkSpeed;
    //stores linear velocity
    /**
     * Stores the linear velocity of the main character.
     */
    Vec2 resultantVel;

    //declaring a minion of type Minion
    /**
     * Stores the enemy minion.
     */
    private Minion minion;

    /**
     * The view of the game.
     */
    private UserView view;
    /**
     * Stores the current state of the main character depending on which direction he's facing.
     */
    private int state;


    /**
     * Constructor for the main character
     * @param world The world that the character gets created in.
     * @param health Health of the main character.
     */
     public TheWalker(LevelLoader world, int health)
     {
         //creating a hit box for walker
         super(world, new BoxShape(0.74f, 1.55f));


         //initializing health for walker
         this.health = health;

         this.view = view;



         //creating a new minion with health of 1
         minion = new Minion(world, 1);



         //setting starting position of minion
         minion.setPosition(new Vec2(-15, -6.5f));

         //method which gives minion AI and follows walker
         minionFollow();

         //setting gravity of walker
         this.setGravityScale(2f);

         //adding a collision listener to walker
         //this.addCollisionListener(new WalkerCollisions(this));

         //world.addStepListener(new DeathAnim(view, minion));
         world.addStepListener(new AttackAnim(this));
         this.addCollisionListener(new AttackAnim(this));







     }

    /**
     * Used to get the state of the main character.
     * @return Returns the state of the main character.
     */
     public int getState()
     {
         return state;
     }

     //getter for walker health

    /**
     * Used to get the current health of the main character.
     * @return Returns the current health of the character.
     */
     public int getHealth()
     {
         return health;
     }

     //setter for walker health

    /**
     * Used to set the main characters health to a given value.
     * @param health Health of the main character.
     */
     public void setHealth(int health)
     {
         this.health = health;
     }

    /**
     * Used to return the minion object.
     * @return Returns the minion object.
     */
     public Minion returnMinion()
     {
         return minion;
     }







     //method to make minion follow the walker depending on walker position

    /**
     * Allows the minion to follow the main character
     */
     public void minionFollow()
     {
         if(minion.getHealth() > 0)
         {
             if (this.getPosition().x - minion.getMinionX() < 0)
             {
                 minion.startWalking(-4f);
             }
             else if(this.getPosition().x - minion.getMinionX() > 0)
             {
                 minion.startWalking(4f);
             }
         }


     }
     public float getSpeed()
     {
         return checkSpeed;
     }


    /**
     * Used to make the main character walk and flips it when going in the opposite direction.
     * @param speed The speed at which the main character walks.
     */
     @Override
    public void startWalking(float speed)
     {
         checkSpeed = speed;
         minionFollow();

         super.startWalking(speed);
         this.setLinearVelocity(new Vec2(this.getLinearVelocity().x, 0f));
         this.removeAllImages();
         AttachedImage playerRun = new AttachedImage(this, new BodyImage("data/Jungle Asset Pack/Character with outline/sprites/run outline.gif", 3.0f), 1.0f, 0.0f, new Vec2(0, 0));
         state = 0;
         if (speed < 0)
         {
             playerRun.flipHorizontal();
             state = 1;
         }

     }


    /**
     * Used to make the main character jump.
     * @param speed The vertical velocity of the main character.
     */
     @Override
    public void jump(float speed)
     {
         minionFollow();
         super.jump(speed);
         resultantVel = this.getLinearVelocity();
         this.removeAllImages();




         AttachedImage playerRun = new AttachedImage(this, new BodyImage("data/Jungle Asset Pack/Character with outline/sprites/jump outline.png", 3.0f), 1.0f, 0.0f, new Vec2(0, 0));



         if(checkSpeed < 0 )
         {
             playerRun.flipHorizontal();

         }
     }
     @Override
    public void stopWalking()
     {

         minionFollow();

         super.stopWalking();
         this.setLinearVelocity(new Vec2(5,this.getPosition().y));


         this.removeAllImages();
         AttachedImage playerRun = new AttachedImage(this, new BodyImage("data/Jungle Asset Pack/Character with outline/sprites/idle outline.gif", 3.0f), 1.0f, 0.0f, new Vec2(0, 0));

         if(checkSpeed < 0)
         {
             playerRun.flipHorizontal();
             this.setLinearVelocity(new Vec2(-5,this.getPosition().y));
         }
     }


}
