import javax.swing.*;
import java.awt.*;

/** Represents a Screen that shows the current state of the game.
 * The Class is used to for changing into new Screens while playing the game.
 * For example moving from the MainMenu to the game.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class Screen
{
    /** Represents the window of the game.
     */
    private JFrame mainFrame;

    /** Font for multiple JLabels' text.
     */
    private Font mainFont;

    /** Creates a JFrame for the current state of the game
     * and adding a JMenuBar with the options of going back to the Main-Menu or exiting the game.
     */
    Screen()
    {
        mainFrame = new JFrame("Memory Game");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Sets the size of the frame to Full - Screen
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setResizable(true);
        mainFrame.setMinimumSize(new Dimension(300, 350));

        JMenuBar menuBar = new JMenuBar();

        JMenuItem backToMainMenuItem = new JMenuItem(Gui.getMessages().getString("backToMainMenu" ));
        backToMainMenuItem.addActionListener(e -> {
            Gui.setCurrentScreen(new MainMenu());
            mainFrame.dispose();
        });

        JMenuItem exitItem = new JMenuItem(Gui.getMessages().getString("exit"));
        exitItem.addActionListener(e -> getMainFrame().dispose());

        JMenu menu = new JMenu(Gui.getMessages().getString("menu"));
        menu.add(backToMainMenuItem);
        menu.add(exitItem);

        menuBar.add(menu);

        mainFrame.setJMenuBar(menuBar);
        mainFont = new Font(("Arial"), Font.PLAIN, 40);
    }

    /** Returns the mainFrame.
     * @return A JFrame representing the main JFrame of the game.
     */
    JFrame getMainFrame()
    {
        return mainFrame;
    }

    /** Returns the mainFont.
     * @return A Font for any usage on components' text.
     */
    Font getMainFont()
    {
        return mainFont;
    }
}