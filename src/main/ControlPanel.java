package main;
import Backgrounds.Background1;
import Levels.LevelLoader;
import SaveLoad.ResourceManager;
import SaveLoad.SaveData;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author jenai beyene
 * Class for the control panel.
 */
public class ControlPanel
{
    private LevelLoader world;
    /**
     * Stores whether or not the game is paused.
     */
    private boolean isPause = false;
    /**
     * Stores the panel of the game.
     */
    private JPanel panel;
    JButton button = new JButton("Pause");
    JButton button2 = new JButton("Restart");
    JButton button3 = new JButton("Select level");
    JButton button4 = new JButton("Save");
    JButton button5 = new JButton("Load");
    String[] levelSelection = {"Level 1", "Level 2", "Level 3"};
    JComboBox<String> comboBox = new JComboBox<>(levelSelection);

    /**
     * Creates the control panel to provide the GUI.
     * @param world Stores details of the world.
     */
    public ControlPanel(LevelLoader world)
    {
        this.world = world;
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        addButton(button, Color.gray);
        button.addActionListener(new Pause());
        addButton(button2, Color.gray);
        button2.addActionListener(new Restart());
        addComboBox(comboBox, Color.gray);
        addButton(button3, Color.gray);
        button3.addActionListener(new SelectLevel());
        addButton(button4, Color.gray);
        button4.addActionListener(new Save());
        addButton(button5, Color.gray);
        button5.addActionListener(new Load());
    }

    /**
     * Used to add a button to the control panel.
     * @param button The button.
     * @param color The color of the button.
     */
    public void addButton(JButton button, Color color)
    {
        button.setFocusable(false);
        button.setBackground(color);
        panel.add(button);
    }

    /**
     * Used to add a combo box to the control panel.
     * @param comboBox The combo box.
     * @param color The color of the combo box.
     */
    public void addComboBox(JComboBox<String> comboBox, Color color)
    {
        panel.add(comboBox);
        comboBox.setVisible(true);
        comboBox.setFocusable(false);
        comboBox.setBackground(color);
    }

    /**
     * Used to check to see if the game is paused or not.
     * @return Returns true if the game is paused.
     */
    public Boolean getPause()
    {
        return isPause;
    }

    /**
     * Used to return the panel.
     * @return Returns the panel.
     */
    public JPanel getPanel()
    {
        return panel;
    }

    /**
     * Used to return the currently selected option on the combo box.
     * @return Returns the string value of the option selected on the combo box.
     */
    public String returnCurrent()
    {
        return (String)comboBox.getSelectedItem();
    }

    /**
     * @author jenai beyene
     * Class for pausing the game.
     */
    class Pause implements ActionListener
    {
        /**
         * Used to pause and unpause the game when pause button is pressed.
         * @param e Stores the action.
         */
        public void actionPerformed(ActionEvent e)
        {
            if(!isPause)
            {
                world.stop();
                isPause = true;
                world.getKeyThing().setAllowCreation(false);
                button.setText("Resume");
            }
            else
            {
                world.start();
                isPause = false;
                world.getKeyThing().setAllowCreation(true);
                button.setText("Pause");
            }
            panel.setFocusable(false);
            button.setFocusable(false);
        }
    }

    /**
     * @author jenai beyene
     * Class to restart the game.
     */
    class Restart implements ActionListener
    {
        /**
         * Used to restart the game when restart button is pressed.
         * @param e Stores the action.
         */
        public void actionPerformed(ActionEvent e)
        {
            world.restartGame();
            button2.setFocusable(false);
        }
    }

    /**
     * @author jenai beyene
     * Class used to select a level to play.
     */
    class SelectLevel implements ActionListener
    {
        /**
         * Used to select a level when the select level button is pressed.
         * @param e Stores the action.
         */
        public void actionPerformed(ActionEvent e)
        {
            if(returnCurrent().equals("Level 1"))
            {
                world.restartGame();
            }
            else if(returnCurrent().equals("Level 2"))
            {
                world.restartLvl2();
            }
            else if(returnCurrent().equals("Level 3"))
            {
                world.restartLvl3();
            }
        }
    }

    /**
     * @author jenai beyene
     * Class used to save the game.
     */
    class Save implements ActionListener
    {
        /**
         * Used to save the game when save button is pressed.
         * @param e Stores the action.
         */
        public void actionPerformed(ActionEvent e)
        {
            SaveData data = new SaveData();
            data.health = world.getWalker().getHealth();
            data.no = world.returnNo();
            data.position = world.getWalker().getPosition();
            try
            {
                ResourceManager.save(data, "savefile.txt");
                System.out.println(data.no);
            }
            catch (Exception ex)
            {
                System.out.println("Couldn't save: " + ex.getMessage());
            }
        }
    }

    /**
     * @author jenai beyene
     * Class to load the game from a save file.
     */
    class Load implements ActionListener
    {
        /**
         * Used to load the game when load button is pressed.
         * @param e Stores the action.
         */
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                SaveData data = (SaveData) ResourceManager.load("savefile.txt");
                switch(data.no)
                {
                    case 1:
                    {
                        world.restartGame();
                        world.getWalker().setHealth(data.health);
                        world.getWalker().setPosition(data.position);
                        break;
                    }
                    case 2:
                    {
                        world.restartLvl2();
                        world.getWalker().setHealth(data.health);
                        world.getWalker().setPosition(data.position);
                        break;
                    }
                    case 3:
                    {
                        world.restartLvl3();
                        world.getWalker().setHealth(data.health);
                        world.getWalker().setPosition(data.position);
                        break;
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println("Couldn't load save data: " + ex.getMessage());
            }
        }
    }
}

