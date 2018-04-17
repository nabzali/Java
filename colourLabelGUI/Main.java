import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

//creating my main class...
public class Main implements ActionListener
{
    //three things initialised - a frame object, an array of label objects, and a random number object
    JFrame guiFrame = new JFrame();
    ColorLabel[] arrayLabels = new ColorLabel[64];
    Random rand = new Random();


    //my main method is going to create a MAIN object which can then call create GUI - necessary as createGUI non-static
    public static void main(String[] args)
    {
        Main myGUI = new Main();
        myGUI.createGUI();

    }

    public void createGUI()
    {
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Label demo");
        //guiFrame.setLocationRelativeTo(null); // centre on screen
        guiFrame.getContentPane().setLayout(new BorderLayout()); //setting layout method for frame should take a FlowLayout object

        // appending arrayLabels with ColorLabel objects
        for (int i = 0; i < 64; i++)
        {

            int r = rand.nextInt(255) + 1;
            int g = rand.nextInt(255) + 1;
            int b = rand.nextInt(255) + 1;
            arrayLabels[i] = new ColorLabel(100,100, new Color(r, g, b)); //generates random colour

        }

        //creating an object reference for the grid panel (which will be added to guiframe in centre)
        JPanel grid = new JPanel();
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Press me to refresh labels");
        button.setFont(new Font("Calibri",Font.BOLD, 30));
        //setting layout as appropriate (Grid layout) for the central panel
        grid.setLayout(new GridLayout(8,8));
        buttonPanel.setLayout(new BorderLayout());

        for (int j = 0; j < 64; j++)
        {
            grid.add(arrayLabels[j]);
        }

        //add button to buttonPanel
        buttonPanel.add(button, BorderLayout.SOUTH);
        //adding the south (button) and central panels to guiFrame
        guiFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        guiFrame.getContentPane().add(grid, BorderLayout.NORTH); //grid (Jpanel object reference) added to guiFrame in centre

        button.addActionListener(this);

        //mandatory instructions to
        guiFrame.pack(); // Resize frame to fit content
        guiFrame.setVisible(true); //make sure its visible
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        createGUI();
    }
}