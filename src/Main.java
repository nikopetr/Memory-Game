import javax.swing.*;
/** Class that starts the program by calling a new Gui.
 * @author Nikolas Petrou
 * @version 2.0
 */
public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui();
            }
        });
    }
}
