package Collisions;

import Platforms.MovingPlatforms;
import main.Main;
import city.cs.engine.*;
import main.TheWalker;
import org.jbox2d.common.Vec2;

public class MovingPlatCollisions implements CollisionListener
{
    private Main main;
    private MovingPlatforms movingPlatforms;

    public MovingPlatCollisions(Main main, MovingPlatforms movingPlatforms)
    {
        this.main = main;
        this.movingPlatforms = movingPlatforms;
    }

    @Override
    public void collide(CollisionEvent e)
    {
        /*if(e.getOtherBody() instanceof TheWalker)
        {
            movingPlatforms.setLinearVelocity(new Vec2(movingPlatforms.getLinearVelocity().x, 0));
            movingPlatforms.setGravityScale(0);
            movingPlatforms.setAngularVelocity(0);


        }*/
    }
}

