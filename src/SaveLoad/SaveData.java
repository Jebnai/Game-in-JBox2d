package SaveLoad;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * @author jenai beyene
 * Class used to store the data that is to be saved.
 */
public class SaveData implements java.io.Serializable
{
    /**
     * Serializable version number.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Health of main character.
     */
    public int health;
    /**
     * The level number.
     */
    public int no;
    /**
     * The main characters position.
     */
    public Vec2 position;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }
}
