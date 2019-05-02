import java.util.Locale;
import java.util.ResourceBundle;

/** Represents the Graphical user-interface.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class Gui
{
    /** Represents the current screen of the game.
     */
    private static Screen gameScreen;

    /** Represent the messages appearing on the screen and for the game.
     */
    private static ResourceBundle messages;

    /**Creates a Gui for the user, gets the current value of the default locale, so the messages will be displayed with the User's system language.
     * Sets the gameScreen to the MainMenu.
     */
    Gui()
    {
        Locale locale = Locale.getDefault();
        messages = ResourceBundle.getBundle("MessageBundle", locale);
        gameScreen = new MainMenu();
    }

    /**Sets the gameScreen to the given Screen aScreen.
     * @param aScreen A Screen representing the screen that is going to be displayed next.
     */
    static void setCurrentScreen(Screen aScreen)
    {
        gameScreen = aScreen;
        gameScreen.getMainFrame().setVisible(true);
    }

    /**Returns the locale ResourceBundle for the messages that are going to be displayed.
     * @return A ResourceBundle representing the messages appearing on the screen and for the game.
     */
    public static ResourceBundle getMessages()
    {
        return messages;
    }
}

