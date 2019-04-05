package SaveLoad;
import java.io.*;
import java.nio.file.*;

/**
 * @author jenai beyene
 * Class used to be able to save and load the game.
 */
public class ResourceManager
{
    /**
     * Used to save data to a file.
     * @param data The save data.
     * @param fileName The file name that the data is saved to.
     * @throws Exception The exception.
     */
    public static void save(Serializable data, String fileName) throws Exception
    {
        try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName))))
        {
            os.writeObject(data);
        }
    }

    /**
     * Used to load data from a file.
     * @param fileName The file name that the data is loaded from.
     * @return Returns the data that is read from the save file.
     * @throws Exception The exception.
     */
    public static Object load(String fileName) throws Exception
    {
        try(ObjectInputStream is = new ObjectInputStream(Files.newInputStream(Paths.get(fileName))))
        {
            return is.readObject();
        }
    }
}
